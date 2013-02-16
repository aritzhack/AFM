package afm.wip.tileEntity;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import afm.tileEntity.TEAFM;
import afm.wip.blocks.BlockFabricator;
import afm.wip.gui.container.ContainerFabricator;

public class TEFabricator extends TEAFM {

	private final ItemStack[] inventory;
	private ContainerFabricator container;

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
		if (slotIndex >= this.inventory.length) return null;
		ItemStack stack = this.getStackInSlot(slotIndex);

		if (stack != null) {

			if (stack.stackSize <= amount) {
				this.setInventorySlotContents(slotIndex, null);
			} else {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					this.setInventorySlotContents(slotIndex, null);
				}
			}
		}

		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		if (slotIndex >= this.inventory.length) return null;
		ItemStack stack = this.getStackInSlot(slotIndex);

		if (stack != null) {
			this.setInventorySlotContents(slotIndex, null);
		}

		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) == this
				&& player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5,
						this.zCoord + 0.5) < 64;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Fabricator");

		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

			byte slot = tag.getByte("Slot");

			if (slot >= 0 && slot < this.inventory.length) {
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		if(this.container != null) container.setDataFromTE(this.inventory);
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
	public void updateEntity() {
		if (this.container != null) this.container.update();
	}

	public void saveStacks(InventoryCrafting craftMatrix) {
		for (int x = 0; x < 9; x++) {
			this.inventory[x] = craftMatrix
					.getStackInRowAndColumn(x % 3, x / 3);
		}
	}

	public void setContainer(ContainerFabricator container) {
		this.container = container;
	}

	@Override
	public String getInvName() {
		return "inventory.afm.fabricator";
	}
}
