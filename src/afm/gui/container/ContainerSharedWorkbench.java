package afm.gui.container;

import afm.tileEntity.TESharedWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerSharedWorkbench extends AFMContainer {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftResult = new InventoryCraftResult();
	private final World world;
	private final TESharedWorkbench tileEntity;

	public ContainerSharedWorkbench(TESharedWorkbench tileEntity,
									InventoryPlayer invPlayer, World world) {
		this.tileEntity = tileEntity;

		this.world = world;

		this.addSlotToContainer(new SlotCrafting(invPlayer.player,
				this.craftMatrix, this.craftResult, 0, 124, 35));

		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				this.addSlotToContainer(new Slot(this.craftMatrix, y + x * 3,
						30 + y * 18, 17 + x * 18));
			}
		}

		// Bind player's inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9,
						8 + x * 18, 84 + y * 18));
			}
		}

		// Bind player's hotbar
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142));
		}

		this.readFromTileEntityNBT();
		this.onCraftMatrixChanged(null);
	}

	private void readFromTileEntityNBT() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				this.craftMatrix.setInventorySlotContents(x + y * 3,
						this.tileEntity.getStackInSlot(x + y * 3));
			}
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1iInventory) {
		this.craftResult
				.setInventorySlotContents(0, CraftingManager.getInstance()
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

}
