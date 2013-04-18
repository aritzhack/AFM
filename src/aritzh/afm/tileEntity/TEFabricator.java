package aritzh.afm.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import aritzh.afm.core.util.UtilAFM;
import aritzh.afm.inventory.ContainerFabricator;

/**
 * TEFabricator
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TEFabricator extends TEAFM {

	public ContainerFabricator containerFabricator;
	private ItemStack[] inventory;
	private int updateCount = 0;

	ItemStack[] tempStorage;

	IInventory[] neighbors = new IInventory[6];
	ItemStack[][] neighArrays = new ItemStack[6][];

	public TEFabricator() {
		this.inventory = new ItemStack[19];
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		if (slotIndex >= this.inventory.length) return null;
		return this.inventory[slotIndex];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot >= this.inventory.length) return;
		this.inventory[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {
		ItemStack stack = this.getStackInSlot(slotIndex);
		if (stack == null) return null;
		if (stack.stackSize <= amount) {
			this.setInventorySlotContents(slotIndex, null);
		} else {
			stack = stack.splitStack(amount);
			if (stack.stackSize == 0) {
				this.setInventorySlotContents(slotIndex, null);
			}
		}
		this.onInventoryChanged();
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		ItemStack ret = this.getStackInSlot(slotIndex);
		if (ret == null) return null;
		this.setInventorySlotContents(slotIndex, null);
		return ret;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) < 64D;
	}

	@Override
	public void updateEntity() {
		if (this.worldObj.isRemote) return;
		this.updateCount++;
		if (this.updateCount == 100) {
			this.updateCount = 0;
		}
		if (this.updateCount % 1 == 0 && this.containerFabricator != null) { // Every 1/20 sec, if
																				// tps if 100%
			this.containerFabricator.onCraftMatrixChanged(this);
			this.tryCraft();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Fabricator");
		this.inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

			byte slot = tag.getByte("Slot");

			if (slot >= 0 && slot < this.inventory.length) {
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();

		for (int i = 0; i < this.inventory.length; i++) {
			ItemStack stack = this.inventory[i];

			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();

				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}

		tagCompound.setTag("Fabricator", itemList);
	}

	@Override
	public String getInvName() {
		return "inventory.afm.fabricator";
	}

	public void dropItems() {
		for (int i = 9; i < this.inventory.length; i++) {
			ItemStack stack = this.getStackInSlot(i);
			if (stack != null && stack.stackSize > 0) {
				UtilAFM.dropEntityItem(stack, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	public void setContainer(ContainerFabricator container) {
		this.containerFabricator = container;
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	// FIXME Doesn't save changes to NBT
	public void clearMatrix() {
		for (int i = 0; i < 9; i++) {
			this.setInventorySlotContents(i, null);
		}
		this.onInventoryChanged();
		this.containerFabricator.onCraftMatrixChanged(this);
	}

	public ItemStack getResult() {
		return this.getStackInSlot(9);
	}

	public boolean hasResult() {
		return this.getResult() != null;
	}

	public ItemStack getCraftSlot(int i) {
		return this.getStackInSlot(i);
	}

	public void tryCraft() {

		this.updateArrays();

		if (!this.hasResult()) return;

		ItemStack resultStack = this.getResult().copy();

		for (int i = 0; i < 9; i++) {
			ItemStack neededStack = this.getCraftSlot(i);
			if (neededStack == null) {
				continue;
			}

			ItemStack notFound = this.seekItem(neededStack.copy());
			if (notFound != null) return;
		}

		// I have everything I need & can add stack to the storage7
		if (this.addResult(resultStack)) {
			this.updateInventories();
		}
	}

	private void updateArrays() {
		this.tempStorage = this.invToISArray(this, 10, 9, ForgeDirection.UNKNOWN);

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			IInventory inv = this.getInventoryInSide(dir);
			if (inv == null) {
				continue;
			}
			this.neighbors[dir.ordinal()] = inv;
			this.neighArrays[dir.ordinal()] = this.invToISArray(inv, 0, 0, dir.getOpposite());
		}
	}

	private ItemStack[] invToISArray(IInventory inv, int startIndex, int length, ForgeDirection dir) {

		TileEntityChest chest2 = null;

		// Negative or zero length -> Whole inventory
		if (inv instanceof TEFabricator) {
			length = inv.getSizeInventory();
			startIndex = 0;
		} else if (inv instanceof ISidedInventory) {
			int[] slots = ((ISidedInventory) inv).getSizeInventorySide(dir.ordinal());
			ItemStack[] ret = new ItemStack[slots.length];
			int i = 0;
			for (int s : slots) {
				ret[i++] = inv.getStackInSlot(s);
			}
			return ret;
		} else if (length <= 0 || length > inv.getSizeInventory()) {
			length = 9;
			startIndex = 10;
		}

		ItemStack[] retArray = new ItemStack[length];

		// If it's a chest, get the adjacent
		if (inv instanceof TileEntityChest) {
			TileEntityChest chest1 = (TileEntityChest) inv;
			chest2 = chest1.adjacentChestXNeg != null ? chest1.adjacentChestXNeg : chest1.adjacentChestXPos != null ? chest1.adjacentChestXPos : chest1.adjacentChestZNeg != null ? chest1.adjacentChestZNeg : chest1.adjacentChestZPosition != null ? chest1.adjacentChestZPosition : null;
			if (chest2 != null) {
				retArray = new ItemStack[chest1.getSizeInventory() + chest2.getSizeInventory()];
			}
		}

		for (int i = startIndex; i < startIndex + length; i++) {
			ItemStack itemStack = inv.getStackInSlot(i);
			retArray[i - startIndex] = itemStack == null ? null : itemStack.copy();
		}
		if (chest2 == null) return retArray;

		// If there's a chest adjacent to the chest adjacent to the fabricator, add it to the array
		for (int i = startIndex; i < startIndex + length; i++) {
			ItemStack itemStack = chest2.getStackInSlot(i);
			retArray[i - startIndex + inv.getSizeInventory()] = itemStack == null ? null : itemStack.copy();
		}

		return retArray;
	}

	private IInventory getInventoryInSide(ForgeDirection dir) {
		World w = this.worldObj;
		int x = this.xCoord + dir.offsetX;
		int y = this.yCoord + dir.offsetY;
		int z = this.zCoord + dir.offsetZ;

		TileEntity te = w.getBlockTileEntity(x, y, z);
		if (te instanceof IInventory) return (IInventory) te;
		return null;
	}

	/**
	 * Look for the IS in inventories
	 * 
	 * @param needed
	 *            ItemStack to look for
	 * @return null if found, {@code needed} if not
	 */
	private ItemStack seekItem(ItemStack needed) {

		needed = this.seekAndRemoveItemFromArray(needed, this.tempStorage);
		if (needed == null) return null;

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			IInventory inv = this.neighbors[dir.ordinal()];
			if (inv == null) {
				continue;
			}
			if (this.seekAndRemoveItemFromArray(needed, this.neighArrays[dir.ordinal()]) == null) return null;
		}
		return needed;
	}

	/**
	 * Looks for an ItemStack in a IS[], and removes it from the array.
	 * 
	 * @param stack
	 *            The IS to bee looked for
	 * @param inv
	 *            The array into which look for the IS
	 * @return if found, null. Else return {@code stack}
	 */
	private ItemStack seekAndRemoveItemFromArray(ItemStack stack, ItemStack[] inv) {
		for (int i = 0; i < inv.length; i++) {
			ItemStack curr = inv[i];
			if (curr == null) {
				continue;
			}
			// FIXME OreDict might lead to unwanted recipes. How to check??
			if (UtilAFM.isSameItemOreDict(curr, stack) && curr.stackSize >= 1) {
				curr.stackSize -= 1;
				if (curr.stackSize < 1) {
					inv[i] = null;
				}
				return null;
			}
		}
		return stack;
	}

	private boolean addResult(ItemStack stack) {
		int firstEmpty = -1;
		try {
			for (int i = 0; i < 9; i++) {
				ItemStack storageStack = this.tempStorage[i];
				if (storageStack == null && firstEmpty == -1) {
					firstEmpty = i;
				} else if (storageStack != null && UtilAFM.isSameItem(storageStack, stack) && stack.stackSize + storageStack.stackSize <= this.getInventoryStackLimit()) {
					ItemStack newStack = storageStack.copy();
					newStack.stackSize += stack.stackSize;
					this.tempStorage[i] = newStack;
					return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
		if (firstEmpty != -1 && firstEmpty < 9) {
			this.tempStorage[firstEmpty] = stack;
			return true;
		}
		return false;
	}

	private void updateInventories() {

		// Update internal storage
		for (int i = 0; i < 9; i++) {
			try {
				this.setInventorySlotContents(i + 10, this.tempStorage[i]);
			} catch (IndexOutOfBoundsException e) {
				this.setInventorySlotContents(i + 10, null);
			}
		}

		// Update neighbors' inventories
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {

			IInventory inv = this.neighbors[dir.ordinal()];
			ItemStack[] tempInv = this.neighArrays[dir.ordinal()];

			if (inv == null) {
				continue;
			}

			int startIndex = 0;

			if (inv instanceof TEFabricator) {
				startIndex = 10; // If it's a fabricator, skip the crafting and result slots
			}

			for (int i = 0; i < inv.getSizeInventory(); i++) {
				try {
					inv.setInventorySlotContents(i + startIndex, tempInv[i]);
				} catch (IndexOutOfBoundsException e) {
					inv.setInventorySlotContents(i + startIndex, null);
				}
			}
			if (inv instanceof TileEntityChest && tempInv.length > inv.getSizeInventory()) {
				TileEntityChest chest1 = (TileEntityChest) inv;
				TileEntityChest chest2 = null;
				chest2 = chest1.adjacentChestXNeg != null ? chest1.adjacentChestXNeg : chest1.adjacentChestXPos != null ? chest1.adjacentChestXPos : chest1.adjacentChestZNeg != null ? chest1.adjacentChestZNeg : chest1.adjacentChestZPosition != null ? chest1.adjacentChestZPosition : null;
				if (chest2 != null) {
					for (int i = 0; i < +chest2.getSizeInventory(); i++) {
						try {
							chest2.setInventorySlotContents(i, tempInv[i + inv.getSizeInventory()]);
						} catch (IndexOutOfBoundsException e) {
							chest2.setInventorySlotContents(i, null);
						}
					}
				}
			}
		}
	}

}
