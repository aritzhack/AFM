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

	public ContainerFabricator(TEFabricator teFabricator, InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = teFabricator;
		this.matrix = new BoundInvCrafting(this);
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

		// Storage IDs: 10-18
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new Slot(this.tileEntity, 10 + x + y * 3, 116 + x * 18, 17 + y * 18));
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

		this.tryCraft();
	}

	public void tryCraft() {

		ItemStack resultStack = this.result.getStackInSlot(0); // Get result stack
		if (resultStack == null) return; // Not result in recipe -> Cannot craft

		// Clone the storage, so that it can be modified without affecting the original one
		this.tempStorage = this.cloneStorage();

		// Iterate over every itemStack needed
		checkEveryNeeded();
		
		// I have everything I need & can add stack to the storage
		if (this.fulfilled && addToTempStorage(resultStack.copy())) {
			this.updateStorageFromTemp(); // Craft & remove needed items!
		}
	}
	
	private ItemStack[] cloneStorage(){
		ItemStack[] retArray = new ItemStack[9];
		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = this.getSlot(i+10).getStack();
			if (itemStack == null){
				retArray[i] = null;
			} else {
				retArray[i] = itemStack.copy();
			}
		}
		return retArray;
	}
	
	private boolean checkEveryNeeded(){
		for (int i = 0; i < 9; i++) {
			
			ItemStack neededStack = this.matrix.getStackInSlot(i); // Get from the matrix
			if (neededStack == null) continue;
			boolean done = false; // we need something, so not done

			// Iterate over every itemStack in the storage
			done = this.seekAndRemoveItemFromTempStorage(neededStack);
			
			if(!done) return false; // If I didn't fulfill the needed, don't continue
		}
		return true;
	}
	
	private boolean seekAndRemoveItemFromTempStorage(ItemStack needed){
		
		for(int i = 0; i<this.tempStorage.length; i++){
			ItemStack curr = this.tempStorage[i];
			if(curr == null) continue;
			// Check if both itemStacks are equal, and if the stack in the storage is valid
			if (UtilAFM.isSameItemOreDict(curr, needed) && curr.stackSize >= 1) {
				curr.stackSize-=1; // -1, so that if the stackSize of the recipe is bigger, it doesn't affect
				if(curr.stackSize < 1) this.tempStorage[i] = null;
				return true; // Found, stop looking for it!
			}
		}
		return false;
	}

	public boolean addToTempStorage(ItemStack itemStack) {
		int firstEmpty = -1;
		try {
			for (int i = 1; i < 9; i++) {
					ItemStack storageStack = this.tempStorage[i];
					if (storageStack == null && firstEmpty == -1) {
						firstEmpty = i;
					} else if ((storageStack != null && storageStack.isItemEqual(itemStack) && itemStack.stackSize + storageStack.stackSize <= this.tileEntity.getInventoryStackLimit())) {
						ItemStack stack = storageStack.copy();
						stack.stackSize+=itemStack.stackSize;
						this.tempStorage[i] = stack;
						return true;
					}
			}
		} catch (IndexOutOfBoundsException e) {}
		if(firstEmpty != -1 && firstEmpty < 9){
			this.tempStorage[firstEmpty] = itemStack;
			return true;
		}
		return false;
	}
	
	private void updateStorageFromTemp(){
		for(int i = 0; i < 9; i++){
			try{
				this.putStackInSlot(i+10, this.tempStorage[i]);
			} catch (IndexOutOfBoundsException e){
				this.putStackInSlot(i+10, null);
			}
		}
	}

	/**
	 * Called when putStackInSlot player shift-clicks on putStackInSlot slot. You must override this or you will crash when someone does that.
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}
	
}
