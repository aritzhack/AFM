package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import afm.core.AFM;
import afm.data.BlockData;

public abstract class BlockAFM extends Block {

	public BlockAFM(int id, String blockName, Material material) {
		super(id, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	public BlockAFM(int id, String blockName) {
		this(id, blockName, Material.rock);
	}

	public BlockAFM(int id, String blockName, int indexInTexture, Material material) {
		super(id, indexInTexture, material);
		this.setBlockName(blockName);
		this.setCreativeTab(AFM.tabAFM);
	}

	public BlockAFM(int id, String blockName, int indexInTexture) {
		this(id, blockName, indexInTexture, Material.rock);
	}

	@Override
	public String getTextureFile() {
		return BlockData.TEXTURE;
	}

}
