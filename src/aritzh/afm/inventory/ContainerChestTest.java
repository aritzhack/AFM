package aritzh.afm.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import aritzh.afm.tileEntity.TETestChest;

/**
 * ContainerChestTest
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ContainerChestTest extends Container {

	private TETestChest tileEntity;

	public ContainerChestTest(TETestChest tileEntity, InventoryPlayer player_inventory) {
		this.tileEntity = tileEntity;
		// Add chest's slots
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {

				Slot s = new Slot(tileEntity, x + y * 9, 8 + x * 18, 18 + y * 18);
				s.setBackgroundIcon(Item.diamond.getIconFromDamage(0)); // Diamond with meta=0
				this.addSlotToContainer(s);
			}
		}

		// Bind player's inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player_inventory, x + y * 9 + 9, 8 + x * 18, 86 + y * 18));
			}
		}

		// Bind player's hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player_inventory, x, 8 + x * 18, 144));
		}

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack ret = null;
		Slot slot = (Slot) this.inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack slotStack = slot.getStack();
			ret = slotStack.copy();

			if (slotIndex < 27) {
				if (!this.mergeItemStack(slotStack, 27, this.inventorySlots.size(), true)) return null;
			} else if (!this.mergeItemStack(slotStack, 0, 27, false)) return null;

			if (slotStack.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
		}
		return ret;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

}