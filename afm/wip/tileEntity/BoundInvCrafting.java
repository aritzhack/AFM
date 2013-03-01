package afm.wip.tileEntity;

import afm.wip.gui.container.ContainerFabricator;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

/**
 * @author Aritz Lopez
 */
public class BoundInvCrafting extends InventoryCrafting {

	private ContainerFabricator container;
	private IInventory boundTo;

	public BoundInvCrafting(ContainerFabricator container, IInventory boundTo) {
		super(container, 3, 3);
		this.container = container;
		this.boundTo = boundTo;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int index) {
		return (index >= this.getSizeInventory() ? null : this.boundTo.getStackInSlot(index));
	}

	/**
	 * Removes from an inventory slot (first arg) up to putStackInSlot specified number (second arg) of items and returns them in putStackInSlot
	 * new stack.
	 */
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack ret = this.boundTo.decrStackSize(slot, amount);
		if (ret != null) {
//			this.container.onCraftMatrixChanged(this);
		}
		return ret;
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index >= 9) return;
		this.boundTo.setInventorySlotContents(index, stack);
//		this.container.onCraftMatrixChanged(this);

	}
}
