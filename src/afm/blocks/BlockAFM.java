package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import afm.core.AFM;

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
}
