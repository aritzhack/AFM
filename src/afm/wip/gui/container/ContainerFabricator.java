package afm.wip.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import afm.core.AFMLogger;
import afm.core.util.UtilAFM;
import afm.wip.tileEntity.BoundInvCrafting;
import afm.wip.tileEntity.TEFabricator;

/**
 * ContainerFabricator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerFabricator extends Container {
	public TEFabricator tileEntity;
	BoundInvCrafting matrix;
	InventoryCraftResult result;

	ItemStack[] tempStorage;

	public boolean fulfilled;

	public ContainerFabricator(TEFabricator teFabricator,
			InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = teFabricator;
		this.matrix = new BoundInvCrafting(this);
		this.result = new InventoryCraftResult();

		teFabricator.setContainer(this);

		this.tileEntity.containerFabricator = this;

		// Crafting inventory IDs: 0-8
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.matrix, x + y * 3, 8 + x * 18,
						17 + y * 18));
			}
		}

		// Result ID: 9
		addSlotToContainer(new Slot(this.result, 9, 80, 35));

		// Storage IDs: 10-18
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.tileEntity, 10 + x + y * 3,
						116 + x * 18, 17 + y * 18));
			}
		}

		// Player Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(inventoryPlayer, 9 + x + y * 9,
						8 + 18 * x, 84 + 18 * y));
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
	public ItemStack slotClick(int slotIndex, int button, int par3,
			EntityPlayer player) {

		if (slotIndex < 19 && slotIndex >= 0
				&& this.getSlot(slotIndex).getHasStack()) {
			AFMLogger.log("Slot stack id: "
					+ this.getSlot(slotIndex).getStack().itemID + ", meta: "
					+ this.getSlot(slotIndex).getStack().getItemDamage());
		}

		// Can't modify result slot
		if (slotIndex == 9)
			return null;

		// If storage or none, handle them as usual
		if (slotIndex > 9 || slotIndex < 0)
			return super.slotClick(slotIndex, button, par3, player);

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

		this.result.setInventorySlotContents(0, CraftingManager.getInstance()
				.findMatchingRecipe(matrix, this.tileEntity.worldObj));
		this.fulfilled = true;

		AFMLogger.log("--------Start----------");
		if (this.result.getStackInSlot(0) == null) {
			AFMLogger.log("Not result");
			AFMLogger.log("--------End-----------");
			return;
		}

		this.tryCraft();
		AFMLogger.log("--------End-----------");

	}

	public void tryCraft() {

		// Get result stack
		ItemStack resultStack = this.result.getStackInSlot(0);

		// Clone the storage, so that it can be modified without affecting the
		// original one
		this.tempStorage = this.cloneStorage();

		// Iterate over every itemStack needed
		for (int i = 0; i < 9; i++) {
			// Get from the matrix
			ItemStack neededStack = this.matrix.getStackInSlot(i);
			if (neededStack == null) {
				// AFMLogger.log("Stack in slot " + i + " is null");
				continue; // If null, check next
			}

			// Iterate over every itemStack in the storage
			if (!this.seekAndRemoveItem(neededStack)) {
				AFMLogger.log("Cannot remove IS from tStorage, not fulfilled");
				this.fulfilled = false; // If I didn't fulfill the needed,
				return; // don't continue
			}
		}
		AFMLogger.log("Fulfilled every stack(?)");

		// I have everything I need & can add stack to the storage
		if (this.fulfilled && addToTempStorage(resultStack.copy())) {
			AFMLogger.log("added to temp storage");
			this.updateStorageFromTemp(); // Craft & remove needed items!
			AFMLogger.log("Swapped storages");
		}
	}

	// TODO Make this modular, so that it accepts a IInventory as an argument
	private ItemStack[] cloneStorage() {
		ItemStack[] retArray = new ItemStack[9];
		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = this.getSlot(i + 10).getStack();
			retArray[i] = (itemStack == null ? null : itemStack.copy());
		}
		return retArray;
	}

	/**
	 * Look for the IS in inventories, in this order:
	 * Internal>North>South>West>East>Top>Bottom
	 * @param needed ItemStack to look for
	 * @return whether or not it was found
	 */
	private boolean seekAndRemoveItem(ItemStack needed){

		return 	seekAndRemoveItemFromTempStorage(needed) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.NORTH) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.SOUTH) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.WEST) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.EAST) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.UP) ||
				seekAndRemoveItemFromSide(needed, ForgeDirection.DOWN);
	}

	private boolean seekAndRemoveItemFromSide(ItemStack needed, ForgeDirection dir) {
		World w = this.tileEntity.worldObj;
		int x = this.tileEntity.xCoord + dir.offsetX;
		int y = this.tileEntity.yCoord + dir.offsetY;
		int z = this.tileEntity.zCoord + dir.offsetZ;
		
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(!(te instanceof IInventory)) return false;
		
		IInventory inv = (IInventory) te;
		
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack curr = inv.getStackInSlot(i);
			// TODO Do the magic!
			// if found return true
		}
		return false;
	}

	private boolean seekAndRemoveItemFromTempStorage(ItemStack needed) {

		for (int i = 0; i < this.tempStorage.length; i++) {
			ItemStack curr = this.tempStorage[i];
			if (curr == null)
				continue;
			// Check if both itemStacks are equal, and if the stack in the
			// storage is valid
			// FIXME OreDict might lead to unwanted recipes. How to check??
			if (UtilAFM.isSameItemOreDict(curr, needed) && curr.stackSize >= 1) {
				// -1, so that if the stackSize of the recipe is bigger, it
				// doesn't affect
				curr.stackSize -= 1;
				if (curr.stackSize < 1)
					this.tempStorage[i] = null;
				AFMLogger.log("Found it in slot " + i);
				return true; // Found, stop looking for it!
			}
		}
		return false;
	}

	// TODO Make this modular, so that it accepts a IS array as argument
	public boolean addToTempStorage(ItemStack itemStack) {
		int firstEmpty = -1;
		try {
			for (int i = 8; i > 0; i--) {
				ItemStack storageStack = this.tempStorage[i];
				if (storageStack == null && firstEmpty == -1) {
					firstEmpty = i;
				}
				else if ((storageStack != null
						&& storageStack.isItemEqual(itemStack) && itemStack.stackSize
						+ storageStack.stackSize <= this.tileEntity
							.getInventoryStackLimit())) {
					AFMLogger.log("Added result to already slot " + i + ". Prev size: " + storageStack.stackSize);
					ItemStack stack = storageStack.copy();
					stack.stackSize += itemStack.stackSize;
					this.tempStorage[i] = stack;
					return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		if (firstEmpty != -1 && firstEmpty < 9) {
			this.tempStorage[firstEmpty] = itemStack;
			AFMLogger.log("Added result to null slot " + firstEmpty);
			return true;
		}
		return false;
	}

	// TODO Make this modular, so that it accepts a IS array and IInventory as argument
	private void updateStorageFromTemp() {
		for (int i = 0; i < 9; i++) {
			try {
				this.putStackInSlot(i + 10, this.tempStorage[i]);
			} catch (IndexOutOfBoundsException e) {
				this.putStackInSlot(i + 10, null);
			}
		}
	}

	/**
	 * Called when putStackInSlot player shift-clicks on putStackInSlot slot.
	 * You must override this or you will crash when someone does that.
	 */
	// TODO Make this work
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}

}
