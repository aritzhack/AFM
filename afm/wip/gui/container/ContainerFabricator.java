package afm.wip.gui.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.core.UtilAFM;
import afm.gui.container.AFMContainer;
import afm.wip.tileEntity.TEFabricator;

public class ContainerFabricator extends AFMContainer {

	private final TEFabricator tileEntity;
	private final InventoryCrafting craftMatrix = new InventoryCrafting(this,
			3, 3);
	private final InventoryCraftResult result = new InventoryCraftResult();
	private final World world;

	boolean fulfilled = false;
	private final List<ItemStack> neededStacks = new ArrayList<ItemStack>();

	public ContainerFabricator(TEFabricator tileEntity,
								InventoryPlayer inventoryPlayer, World world) {
		this.tileEntity = tileEntity;
		this.world = world;

		tileEntity.setContainer(this);

		// Left-inventory: Crafting grid. IDs 0-8
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(this.craftMatrix, x + y * 3,
						8 + x * 18, 17 + y * 18));
			}
		}

		// Crafting result. ID 9
		this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player,
				this.craftMatrix, this.result, 9, 80, 35));

		// Right-inventory: Storage. IDs 10-18
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(tileEntity, 10 + x + 3 * y,
						116 + x * 18, 17 + y * 18));
			}
		}

		// Bind player inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(inventoryPlayer,
						9 + 9 * y + x, 8 + 18 * x, 84 + 18 * y));
			}
		}

		// Bind player hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, x, 8 + 18 * x,
					142));
		}

		this.readFromTileEntityNBT();
		 this.onCraftMatrixChanged(null);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}

	@Override
	public ItemStack slotClick(int slotIndex, int button, int par3,
			EntityPlayer player) {

		this.update();

		if (this.fulfilled) {
			AFM.proxy.writeChatMessageToPlayer("Fulfilled");
		} else {
			for (ItemStack s : this.neededStacks)
				AFM.proxy.writeChatMessageToPlayer(s.toString());
		}
		AFM.proxy.writeChatMessageToPlayer("------------------");

		if (slotIndex == 9) return null; // Can't modify result slot
		if (slotIndex > 9 || slotIndex < 0) // If storage or none, handle them
											// as usual
			return super.slotClick(slotIndex, button, par3, player);

		// If craft-matrix:
		ItemStack is = player.inventory.getItemStack(); // Get stack in the
														// cursor
		Slot slot = this.getSlot(slotIndex);
		if (button == 1) { // Right click -> Clear
			slot.putStack(null);
		} else if (is != null && button == 0) { // Left click & has stack in
												// cursor -> Put stack
			ItemStack stack = is.copy();
			stack.stackSize = 1;
			slot.putStack(stack);
		}
		// TODO Make saving the recipe work
		this.onCraftMatrixChanged(null); // Called to "save" to tile entity, not
										 // working

		return slot.getStack();
	}

	private void readFromTileEntityNBT() {
		for (int x = 0; x < 9; x++) {
			this.getSlot(x).putStack(this.tileEntity.getStackInSlot(x));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inv) {
		this.result.setInventorySlotContents(0, CraftingManager.getInstance()
				.findMatchingRecipe(this.craftMatrix, this.world));
		this.tileEntity.saveStacks(this.craftMatrix);
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer) {
		this.tileEntity.saveStacks(this.craftMatrix);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	public void update() {

		this.neededStacks.clear();

		this.fulfilled = false;

		if (this.getSlot(9).getStack() == null || this.getSlot(9).getStack().stackSize <= 0) {
			this.fulfilled = true;
			return;
		}

		for (int x = 0; x < 9; x++) {
			ItemStack craftStack = this.craftMatrix.getStackInSlot(x);
			this.checkInventoryforNeeded(craftStack);
			this.checkNeighboughrsForNeeded(craftStack);
			if (craftStack != null && craftStack.stackSize > 0)
				this.neededStacks.add(craftStack);
		}

		this.fulfilled = this.neededStacks.size() <= 0
				|| this.neededStacks.isEmpty();
	}

	private ItemStack checkInventoryforNeeded(ItemStack neededStack) {
		if(neededStack == null) return null;
		for (int j = 0; j < 9; j++) {
			ItemStack curr = this.getSlot(9 + j).getStack();
			if (ItemStack.areItemStacksEqual(curr, neededStack) && neededStack.stackSize > 0) {
				neededStack.splitStack(curr.stackSize);
			}
		}
		return neededStack;
	}

	private ItemStack checkNeighboughrsForNeeded(ItemStack neededStack) {
		return neededStack;
	}

	public void setDataFromTE(ItemStack[] stacks) {
		for(int x = 0; x<9; x++){
			this.craftMatrix.setInventorySlotContents(x, stacks[x]);
		}
	}
}
