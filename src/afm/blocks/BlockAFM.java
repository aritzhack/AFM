package afm.blocks;

import afm.core.AFM;
import afm.data.BlockData;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
/**
 * BlockAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
abstract class BlockAFM extends Block {

	public BlockAFM(int id, String blockName) {
		this(id, blockName, Material.rock);
	}

	BlockAFM(int id, String blockName, int indexInTexture) {
		this(id, blockName, indexInTexture, Material.rock);
	}

	BlockAFM(int id, String blockName, Material material) {
		super(id, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	public BlockAFM(int id, String blockName, int indexInTexture, Material material) {
		super(id, indexInTexture, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	@Override
	public String getTextureFile() {
		return BlockData.TEXTURE;
	}

}
