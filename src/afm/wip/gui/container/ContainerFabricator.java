package afm.wip.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import afm.wip.tileEntity.BoundInvCrafting;
import afm.wip.tileEntity.TEFabricator;

/**
 * ContainerFabricator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ContainerFabricator extends Container {
	public TEFabricator tileEntity;
	BoundInvCrafting matrix;
	InventoryCraftResult result;

	ItemStack[] tempStorage;

	IInventory[] neighbors = new IInventory[6];
	ItemStack[][] neighArrays = new ItemStack[6][];

	public ContainerFabricator(TEFabricator teFabricator, InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = teFabricator;
		this.matrix = new BoundInvCrafting(this);
		this.result = new InventoryCraftResult();

		teFabricator.setContainer(this);

		this.tileEntity.containerFabricator = this;

		// Crafting inventory IDs: 0-8
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(this.matrix, x + y * 3, 8 + x * 18, 17 + y * 18));
			}
		}

		// Result ID: 9
		this.addSlotToContainer(new Slot(this.result, 9, 80, 35));

		// Storage IDs: 10-18
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(this.tileEntity, 10 + x + y * 3, 116 + x * 18, 17 + y * 18));
			}
		}

		// Player Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, 9 + x + y * 9, 8 + 18 * x, 84 + 18 * y));
			}
		}

		// Player hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x, 142));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	@Override
	public ItemStack slotClick(int slotIndex, int button, int shift, EntityPlayer player) {

		// Can't modify result slot
		if (slotIndex == 9) return null;

		// If storage or none, handle them as usual
		if (slotIndex > 9 || slotIndex < 0) return super.slotClick(slotIndex, button, shift, player);

		// Craft-matrix -> get stack in the cursor
		ItemStack is = player.inventory.getItemStack();
		Slot slot = this.getSlot(slotIndex);

		// Right click -> Clear
		if (button == 1) {
			slot.putStack(null);
			this.onCraftMatrixChanged(null);

			// Left click & has stack in cursor -> Put stack
		} else if (is != null && button == 0) {
			ItemStack stack = is.copy();
			stack.stackSize = 1;
			slot.putStack(stack);
			this.onCraftMatrixChanged(null);
		}

		return slot.getStack();
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void onCraftMatrixChanged(IInventory inv) {

		this.result.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.matrix, this.tileEntity.worldObj));
		
		for(int i = 0; i<this.tileEntity.getSizeInventory(); i++){
			if(this.getSlot(i).getHasStack()){
				this.tileEntity.setInventorySlotContents(i, this.getSlot(i).getStack());
			} else {
				this.tileEntity.setInventorySlotContents(i, null);
			}
		}
		super.onCraftMatrixChanged(inv);
	}

	/**
	 * Called when putStackInSlot player shift-clicks on putStackInSlot slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex) {
		
		Slot slot = (Slot) this.inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();

			if (slotIndex >= 10 && slotIndex < 19) {// If storage
				if (!this.mergeItemStack(itemstack1, 19, this.inventorySlots.size(), true)) return null;
			} else if (slotIndex > 18) { // If player inventory
				if (!this.mergeItemStack(itemstack1, 10, 19, false)) return null;
			}

			if (itemstack1.stackSize <= 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return null;
	}

}
