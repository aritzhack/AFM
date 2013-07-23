package aritzh.afm.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * TETestChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TETestChest extends TEAFM implements IInventory {

    private final ItemStack[] inventory;

    public TETestChest() {
        this.inventory = new ItemStack[27];
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(final int slotIndex) {
        return this.inventory[slotIndex];
    }

    @Override
    public void setInventorySlotContents(final int slot, final ItemStack stack) {
        this.inventory[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public ItemStack decrStackSize(final int slotIndex, final int amount) {

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
    public ItemStack getStackInSlotOnClosing(final int slotIndex) {

        final ItemStack stack = this.getStackInSlot(slotIndex);

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
    public boolean isUseableByPlayer(final EntityPlayer player) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) < 64;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    @Override
    public void readFromNBT(final NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        final NBTTagList tagList = tagCompound.getTagList("Inventory");

        for (int i = 0; i < tagList.tagCount(); i++) {
            final NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);

            final byte slot = tag.getByte("Slot");

            if (slot >= 0 && slot < this.inventory.length) {
                this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        final NBTTagList itemList = new NBTTagList();

        for (int i = 0; i < this.inventory.length; i++) {
            final ItemStack stack = this.inventory[i];

            if (stack != null) {
                final NBTTagCompound tag = new NBTTagCompound();

                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }

        tagCompound.setTag("Inventory", itemList);
    }

    @Override
    public String getInvName() {
        return "inventory.afm.testChest";
    }

    @Override
    public boolean isInvNameLocalized() {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return false;
    }
}