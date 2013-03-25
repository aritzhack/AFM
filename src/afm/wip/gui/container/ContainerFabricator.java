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
	
	IInventory[] neighbors = new IInventory[6];
	ItemStack[][] neighArrays = new ItemStack[6][];

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

		if (this.result.getStackInSlot(0) == null) {
			this.fulfilled = true;
			return;
		}

		this.tryCraft();
	}

	public void tryCraft() {
		
		updateArrays();

		ItemStack resultStack = this.result.getStackInSlot(0).copy();

		for (int i = 0; i < 9; i++) {
			ItemStack neededStack = this.matrix.getStackInSlot(i);
			if (neededStack == null) {
				continue;
			}

			ItemStack notFound = this.seekItem(neededStack.copy());
			if (notFound != null) {
				this.fulfilled = false;
				return;
			}
		}

		// I have everything I need & can add stack to the storage7
		if (addResult(resultStack)) {
			this.updateInventories();
		}
	}
	
	private ItemStack[] invToISArray(IInventory inv, int startIndex, int length){
		if(length <= 0 || length > inv.getSizeInventory()){ // Negative or zero length -> Whole inventory
			length = inv.getSizeInventory();
			startIndex = 0;
		}
		
		ItemStack[] retArray = new ItemStack[length];
		for (int i = startIndex; i < startIndex+length; i++) {
			ItemStack itemStack = inv.getStackInSlot(i);
			retArray[i-startIndex] = (itemStack == null ? null : itemStack.copy());
		}
		return retArray;
	}
	
	private void updateArrays() {
		this.tempStorage = this.invToISArray(this.tileEntity, 10, 9);
		
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			IInventory inv = getInventoryInSide(dir);
			if(inv == null) continue;
			neighbors[dir.ordinal()] = inv;
			neighArrays[dir.ordinal()] = invToISArray(inv, 0, 0);
		}
	}

	private IInventory getInventoryInSide(ForgeDirection dir) {
		World w = this.tileEntity.worldObj;
		int x = this.tileEntity.xCoord + dir.offsetX;
		int y = this.tileEntity.yCoord + dir.offsetY;
		int z = this.tileEntity.zCoord + dir.offsetZ;
		
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te instanceof IInventory) return (IInventory) te;
		return null;
	}

	/**
	 * Look for the IS in inventories
	 * @param needed ItemStack to look for
	 * @return null if found, {@code needed} if not
	 */
	private ItemStack seekItem(ItemStack needed){
		
		needed = seekAndRemoveItemFromArray(needed, this.tempStorage);
		if(needed == null) return null;
		
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			IInventory inv = this.neighbors[dir.ordinal()];
			if(inv == null) continue;
			if(seekAndRemoveItemFromArray(needed, this.neighArrays[dir.ordinal()]) == null){
				return null;
			}
		}
		return needed;
	}
	
	/**
	 * Looks for an ItemStack in a IS[], and removes it from the array.
	 * @param stack The IS to bee looked for
	 * @param inv The array into which look for the IS
	 * @return if found, null. Else return {@code stack}
	 */
	private ItemStack seekAndRemoveItemFromArray(ItemStack stack, ItemStack[] inv){
		for (int i = 0; i < inv.length; i++) {
			ItemStack curr = inv[i];
			if (curr == null) continue;
			// FIXME OreDict might lead to unwanted recipes. How to check??
			if (UtilAFM.isSameItemOreDict(curr, stack) && curr.stackSize >= 1) {
				curr.stackSize -= 1;
				if (curr.stackSize < 1) inv[i] = null;
				return null;
			}
		}
		return stack;
	}
	
	private boolean addResult(ItemStack stack){
		int firstEmpty = -1;
		try {
			for (int i = 0; i < 9; i++) {
				ItemStack storageStack = this.tempStorage[i];
				if (storageStack == null && firstEmpty == -1) {
					firstEmpty = i;
				}
				// FIXME ItemStack.isItemEqual doesn't handle NBT!
				else if ((storageStack != null
						&& storageStack.isItemEqual(stack) && stack.stackSize
						+ storageStack.stackSize <= this.tileEntity
							.getInventoryStackLimit())) {
					ItemStack newStack = storageStack.copy();
					newStack.stackSize += stack.stackSize;
					this.tempStorage[i] = newStack;
					return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {}
		if (firstEmpty != -1 && firstEmpty < 9) {
			AFMLogger.log("Last option: " + firstEmpty);
			this.tempStorage[firstEmpty] = stack;
			return true;
		}
		return false;
	}

	private void updateInventories() {
		
		// Update internal storage
		for (int i = 0; i < 9; i++) {
			try {
				this.tileEntity.setInventorySlotContents(i + 10, this.tempStorage[i]);
			} catch (IndexOutOfBoundsException e) {
				this.tileEntity.setInventorySlotContents(i + 10, null);
			}
		}
		
		// Update neighbors' inventories
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			
			IInventory inv = this.neighbors[dir.ordinal()];
			ItemStack[] tempInv = this.neighArrays[dir.ordinal()];
			
			if(inv == null) continue;
			
			for(int i = 0; i<inv.getSizeInventory();i++){
				try {
					inv.setInventorySlotContents(i, tempInv[i]);
				} catch (IndexOutOfBoundsException e) {
					inv.setInventorySlotContents(i, null);
				}
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
