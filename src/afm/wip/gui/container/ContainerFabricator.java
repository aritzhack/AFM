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
import java.util.ListIterator;

/**
 * ContainerFabricator
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ContainerFabricator extends Container {
	TEFabricator tileEntity;
	BoundInvCrafting matrix;
	InvUpdater storage;
	InventoryCraftResult result;

	List<ItemStack> tempStorage = new ArrayList<ItemStack>();

	public boolean fulfilled;

	public ContainerFabricator(TEFabricator teFabricator, InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = teFabricator;
		this.matrix = new BoundInvCrafting(this, teFabricator);
		this.storage = new InvUpdater(teFabricator);
		this.result = new InventoryCraftResult();

		teFabricator.setContainer(this);

		this.tileEntity.containerFabricator = this;

		// Crafting inventory IDs: 0-8
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.matrix, x + y * 3, 8 + x * 18, 17 + y * 18));
			}
		}

		// Result ID: 9
		addSlotToContainer(new Slot(this.result, 9, 80, 35));

		// Storage IDs: 10-18 (10 get added in InvUpdater)
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

		this.fulfilled = true;

		if (this.result.getStackInSlot(0) == null) return;

		checkIfFulfilled();
	}

	private void checkIfFulfilled() {

		this.tempStorage.clear();
		ItemStack resultStack = this.result.getStackInSlot(0);
		if (resultStack == null) return; // Not result in recipe -> Not check everything

		// Clone the storage to a list, so that it can be modified
		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = this.storage.getStackInSlot(i);
			if (itemStack == null) continue;
			this.tempStorage.add(itemStack);
		}

		// Iterate over every itemStack needed
		for (int i = 0; i < 9; i++) {
			ItemStack is = this.matrix.getStackInSlot(i); // Get from the matrix
			if (is == null) continue;
			ItemStack neededStack = is.copy();

			// Iterate over every itemStack in the storage
			ListIterator<ItemStack> iter = this.tempStorage.listIterator();
			if(!iter.hasNext()){
				this.fulfilled = false;
				return;
			}
			while (iter.hasNext()){
				ItemStack curr = iter.next();
				if (UtilAFM.isSameItemOreDict(curr, neededStack) && curr.stackSize >= 1) {
					ItemStack newIS = curr.copy();
					newIS.splitStack(1); // 1, so that if in the recipe the stackSize is bigger, it doesn't affect
					iter.remove();
					if(newIS.stackSize >= 1){
						iter.add(newIS);
					}
					break;
				} else {
					this.fulfilled = false;
				}
			}
		}
		if (this.fulfilled) {
			if(!addToTempStorage(resultStack)) return;
			for(int i = 0; i < 9; i++){
				try{
					this.storage.setInventorySlotContents(i, this.tempStorage.get(i));
				} catch (IndexOutOfBoundsException e){
					this.storage.setInventorySlotContents(i, null);
				}
			}
		}
	}

	private boolean addToTempStorage(ItemStack itemStack) {
		int firstEmpty = -1;
		try {
			for (int i = 1; i < 10; i++) {
					ItemStack storageStack = this.tempStorage.get(i);
					if (storageStack == null && firstEmpty == -1) {
						firstEmpty = i;
					} else if ((storageStack != null && storageStack.isItemEqual(itemStack) && itemStack.stackSize + storageStack.stackSize <= this.storage.getInventoryStackLimit())) {
						ItemStack stack = storageStack.copy();
						stack.stackSize+=itemStack.stackSize;
						this.tempStorage.set(i, stack);
						return true;
					}
			}
		} catch (IndexOutOfBoundsException e) {
			if(this.tempStorage.size() >= 9) return false;
			this.tempStorage.add(itemStack);
			return true;
		}
		if(firstEmpty == -1)return false;
		this.tempStorage.set(firstEmpty, itemStack);
		return true;
	}

	/**
	 * Called when putStackInSlot player shift-clicks on putStackInSlot slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}

	// TODO Don't use a different class, since it doesn't longer update
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
			return inv.getStackInSlot(slot+10);
		}

		@Override
		public ItemStack decrStackSize(int slot, int amount) {
			return inv.decrStackSize(slot+10, amount);
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int slot) {
			return this.inv.getStackInSlotOnClosing(slot+10);
		}

		@Override
		public void setInventorySlotContents(int slot, ItemStack stack) {
			this.inv.setInventorySlotContents(slot+10, stack);
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
