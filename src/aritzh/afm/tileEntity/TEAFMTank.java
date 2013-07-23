package aritzh.afm.tileEntity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

/**
 * TEAFMTank
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TEAFMTank extends TileEntity implements IFluidTank {

	FluidTank liquid = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME * 4);

	@Override
	public FluidStack getFluid() {
		return this.liquid.getFluid();
	}

	@Override
	public int getFluidAmount() {
		return this.liquid.getFluidAmount();
	}

	@Override
	public int getCapacity() {
		return this.liquid.getFluidAmount();
	}

	@Override
	public FluidTankInfo getInfo() {
		return this.liquid.getInfo();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return this.liquid.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return this.liquid.drain(maxDrain, doDrain);
	}

}
