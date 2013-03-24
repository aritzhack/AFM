package afm.wip.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import afm.core.AFMLogger;

public class TEAFMTank extends TileEntity implements ILiquidTank {
	
	LiquidTank liquid = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME*4);

	@Override
	public LiquidStack getLiquid() {
		return liquid.getLiquid();
	}

	@Override
	public int getCapacity() {
		return liquid.getCapacity();
	}

	@Override
	public int fill(LiquidStack resource, boolean doFill) {
		return liquid.fill(resource, doFill);
	}

	@Override
	public LiquidStack drain(int maxDrain, boolean doDrain) {
		return liquid.drain(maxDrain, doDrain);
	}

	@Override
	public int getTankPressure() {
		return liquid.getTankPressure();
	}

	public void clicked(EntityPlayer player) {
		if(this.getLiquid() == null) AFMLogger.log("Liquid before: empty");
		else AFMLogger.log("Liquid before: " + this.getLiquid().amount);
		
		if(LiquidContainerRegistry.isFilledContainer(player.inventory.getCurrentItem())){
			AFMLogger.log("Filling...");
			LiquidStack stack = LiquidContainerRegistry.getLiquidForFilledItem(player.inventory.getCurrentItem());
			int filled = this.fill(stack, true);
			if(filled != 0){
				//if(!player.capabilities.isCreativeMode){
					ItemStack containerIS = player.inventory.getCurrentItem().getItem().getContainerItemStack(player.inventory.getCurrentItem());
					player.inventory.setInventorySlotContents(player.inventory.currentItem, containerIS);
				//}
			}
		} else if (LiquidContainerRegistry.isEmptyContainer(player.inventory.getCurrentItem())){
			AFMLogger.log("Emptying...");
			boolean canDrainOneBucket = this.drain(LiquidContainerRegistry.BUCKET_VOLUME, false).amount == LiquidContainerRegistry.BUCKET_VOLUME;
			if(this.getLiquid() != null && canDrainOneBucket){
				this.drain(LiquidContainerRegistry.BUCKET_VOLUME, false);
				ItemStack full = LiquidContainerRegistry.fillLiquidContainer(this.getLiquid(), player.inventory.getCurrentItem());
				player.inventory.setInventorySlotContents(player.inventory.currentItem, full);
			}
		}
		
		if(this.getLiquid() == null) AFMLogger.log("Liquid after: empty");
		else AFMLogger.log("Liquid after: " + this.getLiquid().amount);
	}
	
	

}
