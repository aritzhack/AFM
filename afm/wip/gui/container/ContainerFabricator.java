package afm.wip.gui.container;

import afm.core.util.UtilAFM;
import afm.wip.tileEntity.BoundInvCrafting;
import afm.wip.tileEntity.TEFabricator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aritz Lopez
 */
public class ContainerFabricator extends Container {
	TEFabricator tileEntity;
	BoundInvCrafting matrix;
	InvUpdater storage;
	InventoryCraftResult result;

	public List<ItemStack> needed = new ArrayList<ItemStack>();
	public boolean fulfilled;

	public ContainerFabricator(TEFabricator teFabricator, InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = teFabricator;
		this.matrix = new BoundInvCrafting(this, teFabricator);
		this.storage = new InvUpdater(teFabricator);
		this.result = new InventoryCraftResult();

		this.tileEntity.containerFabricator = this;

		// Crafting inventory IDs: 0-8
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.matrix, x + y * 3, 8 + x * 18, 17 + y * 18));
			}
		}

		// Result ID: 9
		addSlotToContainer(new Slot(this.result, 9, 80, 35));

		// Storage IDs: 10-18
		// TODO Change where the +10 happens to here (10 + x + y * 3) instead of in InvUpdater
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.storage, x + y * 3, 116 + x * 18, 17 + y * 18));
			}
		}

		// Player Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(inventoryPlayer, 9 + x + y * 9, 8 + 18 * x, 84 + 18 * y));
			}
		}

		// Player hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 142));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack slotClick(int slotIndex, int button, int par3, EntityPlayer player) {
		this.onCraftMatrixChanged(this.tileEntity);
		if (this.fulfilled) {
			System.out.println(this.tileEntity.worldObj.getWorldTime() + " - Fulfilled");
		} else {
			for (ItemStack is : this.needed) {
				System.out.println(this.tileEntity.worldObj.getWorldTime() + " - " + is.getItemName() + "x" + is.stackSize);
			}
		}
		System.out.println("---------------------------");

		if (slotIndex == 9) return null; // Can't modify result slot
		if (slotIndex > 9 || slotIndex < 0) // If storage or none, handle them as usual
			return super.slotClick(slotIndex, button, par3, player);
		// If craft-matrix:
		ItemStack is = player.inventory.getItemStack(); // Get stack in the cursor
		Slot slot = this.getSlot(slotIndex);
		if (button == 1) { // Right click -> Clear
			slot.putStack(null);
		} else if (is != null && button == 0) { // Left click & has stack in cursor -> Put stack
			ItemStack stack = is.copy();
			stack.stackSize = 1;
			slot.putStack(stack);
		}

		return slot.getStack();
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		this.result.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(matrix, this.tileEntity.worldObj));
		this.needed.clear();
		this.fulfilled = true;

		if (this.result.getStackInSlot(0) == null) return;

		for (int x = 0; x < 9; x++) {
			ItemStack is = (ItemStack) this.inventoryItemStacks.get(x);
			if (is == null) continue;

			// TODO clone storage to an array/list, and check from there, removing the amount used
			if (findItemStackInStorage(is) == -1) {
				needed.add(is); // If not found, add it to needed;
				this.fulfilled = false;
			}
		}

		super.onCraftMatrixChanged(par1IInventory);
	}

	/**
	 * Called when putStackInSlot player shift-clicks on putStackInSlot slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);    //To change body of overridden methods use File | Settings | File Templates.
	}


	// TODO check from the (to be done) cloned storage, so that we can then remove from it
	private int findItemStackInStorage(ItemStack stack) {
		for (int x = 0; x < 9; x++) {
			ItemStack curr = this.tileEntity.getStackInSlot(x + 10);
			if (UtilAFM.isSameItemOreDict(curr, stack)) {
				return x;
			}
		}
		return -1;
	}

	public class InvUpdater implements IInventory {

		private IInventory inv;

		public InvUpdater(IInventory inventory) {
			inv = inventory;
		}

		@Override
		public int getSizeInventory() {
			return 9;
		}

		@Override
		public ItemStack getStackInSlot(int slot) {
			return inv.getStackInSlot(slot + 10);
		}

		@Override
		public ItemStack decrStackSize(int slot, int amount) {
			ItemStack ret = inv.decrStackSize(slot + 10, amount);
			if (ret != null) ContainerFabricator.this.onCraftMatrixChanged(this);
			return ret;
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int slot) {
			return this.inv.getStackInSlotOnClosing(slot + 10);
		}

		@Override
		public void setInventorySlotContents(int slot, ItemStack stack) {
			this.inv.setInventorySlotContents(slot + 10, stack);
			ContainerFabricator.this.onCraftMatrixChanged(this);
		}

		@Override
		public String getInvName() {
			return this.inv.getInvName();
		}

		@Override
		public int getInventoryStackLimit() {
			return this.inv.getInventoryStackLimit();
		}

		@Override
		public void onInventoryChanged() {
			ContainerFabricator.this.onCraftMatrixChanged(this);
			this.inv.onInventoryChanged();
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer var1) {
			return false;
		}

		@Override
		public void openChest() {
		}

		@Override
		public void closeChest() {
		}
	}
}
