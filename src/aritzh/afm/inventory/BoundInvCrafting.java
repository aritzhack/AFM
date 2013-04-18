package aritzh.afm.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

/**
 * BoundInvCrafting
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BoundInvCrafting extends InventoryCrafting {

	private IInventory boundTo;

	public BoundInvCrafting(ContainerFabricator container) {
		super(container, 3, 3);
		this.boundTo = container.tileEntity;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int index) {
		return index >= this.getSizeInventory() ? null : this.boundTo.getStackInSlot(index);
	}

	/**
	 * Removes from an inventory slot (first arg) up to putStackInSlot specified number (second arg)
	 * of items and returns them in putStackInSlot new stack.
	 */
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return this.boundTo.decrStackSize(slot, amount);
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor
	 * sections).
	 */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= 9) return;
		this.boundTo.setInventorySlotContents(index, stack);
	}
}
