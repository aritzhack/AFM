package aritzh.afm.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import aritzh.afm.core.util.UtilNBT;
import aritzh.afm.data.BlockData;

/**
 * TEPortableChest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TEPortableChest extends TileEntityChest {

	public void setContentFromIS(ItemStack is) {
		NBTTagCompound tagC = UtilNBT.getISTagCompound(is);
		NBTTagList stacksTag = tagC.getTagList("AFMPortChestStacks");
		for (int i = 0; i < stacksTag.tagCount(); i++) {
			ItemStack chestStack = new ItemStack(0, 0, 0);
			NBTTagCompound chestStackTag = (NBTTagCompound) stacksTag.tagAt(i);
			chestStack.readFromNBT(chestStackTag);
			if (chestStack.itemID <= 0 || chestStack.stackSize <= 0) {
				continue;
			}
			String name = chestStackTag.getName();
			int slot;
			try {
				slot = Integer.valueOf(name);
			} catch (Exception e) {
				continue;
			}
			this.setInventorySlotContents(slot, chestStack);
		}
	}

	public ItemStack crateISFromContent() {
		ItemStack ret = new ItemStack(BlockData.ID_PORTABLE_CHEST, 1, 0);

		NBTTagCompound tagC = UtilNBT.getISTagCompound(ret);
		tagC.setTag("AFMPortChestStacks", new NBTTagList());
		NBTTagList stacksTag = tagC.getTagList("AFMPortChestStacks");
		for (int i = 0; i < 27; i++) {
			ItemStack currStack = this.getStackInSlot(i);
			if (currStack == null) {
				stacksTag.appendTag(new NBTTagCompound(String.valueOf(i)));
				continue;
			}
			NBTTagCompound stTag = new NBTTagCompound(String.valueOf(i));
			stacksTag.appendTag(currStack.writeToNBT(stTag));
			UtilNBT.addDescriptionToStack(ret, "Stack: " + currStack.getItemName() + "x" + currStack.stackSize);
		}
		return ret;
	}

	@Override
	public String getInvName() {
		return "inventory.afm.portableChest";
	}

}
