package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import afm.core.AFM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
abstract class BlockAFM extends Block {
	
	Icon[] icons = new Icon[6];
	boolean hasMetadata = false;
	

	public BlockAFM(int id, String blockName) {
		this(id, blockName, Material.rock);
	}

	BlockAFM(int id, String blockName, Material material) {
		super(id, material);
		this.setUnlocalizedName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public abstract void func_94332_a(IconRegister par1IconRegister);
}
