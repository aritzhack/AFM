package afm.wip.gui.container;

import java.util.ArrayList;
import java.util.Iterator;
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
import afm.gui.container.AFMContainer;
import afm.wip.tileEntity.TEFabricator;

public class ContainerFabricator extends AFMContainer {

	private final TEFabricator tileEntity;
	private final InventoryCrafting craftMatrix = new InventoryCrafting(this,
			3, 3);
	private final InventoryCraftResult result = new InventoryCraftResult();
	private final World world;

	boolean fulfilled = false;
	private List<Integer> neededStacks = new ArrayList<Integer>();
	private List<ItemStack> tempInv;

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
			for (int i : this.neededStacks){
				ItemStack needed = this.getSlot(i).getStack();
				AFM.proxy.writeChatMessageToPlayer(needed.getItemName() + "x" + needed.stackSize);
			}
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
		this.onCraftMatrixChanged(null); // Called to "save" to tile entity

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

	/**
	 * Updated the container, checking for the recipe, and it's ingredients. <br />
	 * Called when the TileEntity is updated
	 */
	public void update() {
		this.neededStacks.clear();

		this.fulfilled = true;

		if (this.getSlot(9).getStack() == null || this.getSlot(9).getStack().stackSize <= 0) {
			return;
		}
		
		this.tempInv = this.inventoryItemStacks.subList(0, 9);

		for (int x = 0; x < this.craftMatrix.getSizeInventory(); x++) {
			ItemStack craftStack = this.craftMatrix.getStackInSlot(x);
			if(craftStack == null) continue;
			ItemStack needed = craftStack.copy();
			if(needed == null) continue;
			
			if (!this.checkInventoryforNeeded(needed) &&
				!this.checkNeighboughrsForNeeded(needed))
				this.neededStacks.add(x); // If not found, add the slot index to the needed
				this.fulfilled = false; // And state that we're not fulfilled, not to check the list
		}
	}

	private boolean checkInventoryforNeeded(ItemStack neededStack) {
		Iterator<ItemStack> iter = this.tempInv.iterator();
		return checkForISinIterator(iter, neededStack);
	}
	
	private boolean checkNeighboughrsForNeeded(ItemStack neededStack) {
		return checkForISinIterator(null, neededStack);
	}
	
	/**
	 * Iterates over the iterator, looking for the needed ItemStack
	 * @param iter The iterator to iterate on
	 * @param needed The IS to look for in the iterator
	 * @return if the IS is found
	 */
	private boolean checkForISinIterator(Iterator<ItemStack> iter, ItemStack needed){ 
		if(iter==null) return false;
		while(iter.hasNext()){
			ItemStack curr = iter.next();
			if(curr == null) continue;
			if (curr.isItemEqual(needed) && ItemStack.areItemStackTagsEqual(curr, needed) && needed.stackSize > 0) {
				curr.stackSize--;
				return true;
			}
		}
		return false;
	}

	public void setDataFromTE(ItemStack[] stacks) {
		for(int x = 0; x<9; x++){
			this.craftMatrix.setInventorySlotContents(x, stacks[x]);
		}
	}
}
