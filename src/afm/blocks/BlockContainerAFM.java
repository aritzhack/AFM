package afm.blocks;

import afm.core.AFM;
import afm.data.BlockData;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * BlockContainerAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public abstract class BlockContainerAFM extends BlockContainer {

	protected BlockContainerAFM(int id, String blockName, Material material) {
		super(id, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	BlockContainerAFM(int id, String blockName) {
		this(id, blockName, Material.rock);
	}

	BlockContainerAFM(int id, String blockName, int indexInTexture, Material material) {
		super(id, indexInTexture, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	BlockContainerAFM(int id, String blockName, int indexInTexture) {
		this(id, blockName, indexInTexture, Material.rock);
	}

	@Override
	public String getTextureFile() {
		return BlockData.TEXTURE;
	}

}
