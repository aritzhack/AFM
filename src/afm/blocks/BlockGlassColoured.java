package afm.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import afm.core.util.UtilAFM;
import afm.data.BlockData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockGlassColoured
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGlassColoured extends BlockAFM {

	Icon[] icons = new Icon[16];

	public BlockGlassColoured() {
		super(BlockData.ID_COLOURED_GLASS, BlockData.NAME_COLOUREDGLASS, Material.glass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		for (int meta = 0; meta < 16; meta++) {
			this.icons[meta] = iconRegister.registerIcon(String.format("afm:%s-%s", BlockData.NAME_COLOUREDGLASS, UtilAFM.colorNames[meta].toLowerCase().replace(" ", "")));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return this.icons[meta];
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 16; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	/**
	 * Adds the following recipes:
	 * <ul>
	 * 2 Wool of same color -> 1 Multiblock of that color
	 * </ul>
	 * <ul>
	 * 1 stone + 1 dye -> 1 MultiBlock of the color of the dye
	 * </ul>
	 * <ul>
	 * 1 Multiblock of a color -> 2 Wool of that color
	 * </ul>
	 * <ul>
	 * </ul>
	 */
	public void initRecipes() {
		for (int meta = 0; meta < 16; meta++) {
			ItemStack glass = new ItemStack(Block.glass, 1);
			ItemStack output = new ItemStack(this, 1, meta);
			ItemStack dye = new ItemStack(Item.dyePowder, 1, 15 - meta);

			GameRegistry.addShapelessRecipe(output, glass, dye);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
		return var6 != this.blockID && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, 1 - par5);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getMobilityFlag() {
		return 0;
	}

}
