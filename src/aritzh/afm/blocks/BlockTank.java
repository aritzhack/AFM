package aritzh.afm.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import aritzh.afm.core.AFMLogger;
import aritzh.afm.data.BlockData;
import aritzh.afm.data.Config;
import aritzh.afm.tileEntity.TEAFMTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockTank
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockTank extends BlockContainerAFM {

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float bx, float by, float bz) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || !(te instanceof TEAFMTank)) return false;

		TEAFMTank tank = (TEAFMTank) te;

		FluidStack ls = tank.getFluid();

		if (ls != null) {
			AFMLogger.debug("Before " + ls.amount);
		}

		ItemStack current = player.inventory.getCurrentItem();
		if (current != null) {

			FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(current);

			// Handle filled containers
			if (liquid != null) {

				int amount = tank.fill(liquid, true);

				if (amount != 0 && (!player.capabilities.isCreativeMode || Config.debug)) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, current.getItem().getContainerItemStack(current));
				}
				if (ls != null) {
					AFMLogger.debug("After " + ls.amount);
				}
				return true;

				
			} else { // Handle empty containers

				if (ls != null) {
					ItemStack filled = FluidContainerRegistry.fillFluidContainer(ls, current);

					liquid = FluidContainerRegistry.getFluidForFilledItem(filled);

					if (liquid != null) {
						if (!player.capabilities.isCreativeMode || Config.debug) {
							if (current.stackSize > 1) {
								if (!player.inventory.addItemStackToInventory(filled)) {
									AFMLogger.debug("After " + ls.amount + " - Nope");
									return false;
								} else {
									player.inventory.setInventorySlotContents(player.inventory.currentItem, current.getItem().getContainerItemStack(current));
								}
							} else {
								player.inventory.setInventorySlotContents(player.inventory.currentItem, current.getItem().getContainerItemStack(current));
								player.inventory.setInventorySlotContents(player.inventory.currentItem, filled);
							}
						}
						tank.drain(liquid.amount, true);

						AFMLogger.debug("After " + ls.amount);

						return true;
					}
				}
			}
		}

		if (ls != null) {
			AFMLogger.debug("After " + ls.amount + " - Nope");
		}

		return false;

	}

	public BlockTank() {
		super(BlockData.ID_TANK, BlockData.NAME_TANK, Material.glass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_TANK));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return this.blockIcon;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEAFMTank();
	}

}
