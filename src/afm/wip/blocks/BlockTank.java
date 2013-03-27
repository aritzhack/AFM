package afm.wip.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import afm.blocks.BlockContainerAFM;
import afm.core.AFMLogger;
import afm.data.BlockData;
import afm.data.Properties;
import afm.wip.tileEntity.TEAFMTank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTank extends BlockContainerAFM {
	
	Icon icon;

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float bx, float by, float bz) {

		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(te == null || !(te instanceof TEAFMTank)){
			return false;
		}
		
		TEAFMTank tank = (TEAFMTank) te;
		
		LiquidStack ls = tank.getTank(null, null).getLiquid();
		
		if(ls != null) AFMLogger.debug("Before " + ls.amount);
		
		ItemStack current = player.inventory.getCurrentItem();
		if (current != null) {

			LiquidStack liquid = LiquidContainerRegistry.getLiquidForFilledItem(current);

			// Handle filled containers
			if (liquid != null) {
				
				int amount = tank.fill(ForgeDirection.UNKNOWN, liquid, true);

				if (amount != 0 && (!player.capabilities.isCreativeMode || Properties.DEBUG)) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, current.getItem().getContainerItemStack(current));
				}
				if(ls != null) AFMLogger.debug("After " + ls.amount);
				return true;

				// Handle empty containers
			} else {

				if (ls != null) {
					ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(ls, current);

					liquid = LiquidContainerRegistry.getLiquidForFilledItem(filled);

					if (liquid != null) {
						if (!player.capabilities.isCreativeMode || Properties.DEBUG) {
							if (current.stackSize > 1) {
								if (!player.inventory.addItemStackToInventory(filled)){
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
						tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);
						
						AFMLogger.debug("After " + ls.amount);
						
						return true;
					}
				}
			}
		}

		if(ls != null) AFMLogger.debug("After " + ls.amount + " - Nope");
		
		return false;

	}

	public BlockTank() {
		super(BlockData.ID_TANK, BlockData.NAME_TANK, Material.glass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.icon = iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_TANK));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return icon;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TEAFMTank();
	}

}
