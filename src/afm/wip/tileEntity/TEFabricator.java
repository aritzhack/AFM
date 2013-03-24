package afm.wip.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import afm.core.util.UtilAFM;
import afm.tileEntity.TEAFM;
import afm.wip.gui.container.ContainerFabricator;

/**
 * TEFabricator
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TEFabricator extends TEAFM {

	public ContainerFabricator containerFabricator;
	private ItemStack[] inventory;
	private int updateCount = 0;

	public TEFabricator() {
		this.inventory = new ItemStack[19];
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		if (slotIndex >= this.inventory.length)
			return null;
		return this.inventory[slotIndex];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot >= this.inventory.length)
			return;
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
		onInventoryChanged();
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
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
				&& player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) < 64D;
	}

	@Override
	public void updateEntity() {
		if(this.worldObj.isRemote) return;
		this.updateCount++;
		if(updateCount == 100) updateCount = 0;
		if (updateCount % 2 == 0 && this.containerFabricator != null){ // Every 1/10 sec, if tps if 100%
			this.containerFabricator.onCraftMatrixChanged(this);
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
		for (int i = 0; i < this.inventory.length; i++) {
			ItemStack stack = this.getStackInSlot(i);
			if ((stack != null) && (stack.stackSize > 0))
				UtilAFM.dropEntityItem(stack, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
	}

	public void setContainer(ContainerFabricator container){
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
	
}
