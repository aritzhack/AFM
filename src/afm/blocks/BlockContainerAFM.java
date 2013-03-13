package afm.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import afm.core.AFM;

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
		this.setUnlocalizedName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	BlockContainerAFM(int id, String blockName) {
		this(id, blockName, Material.rock);
	}

}
