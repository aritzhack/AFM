package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import afm.core.AFM;
import afm.core.Properties;

public abstract class BlockAFM extends Block {

	public BlockAFM(int id, Material material) {
		super(id, material);
		this.setCreativeTab(AFM.tabAFM);
	}

	public BlockAFM(int id) {
		this(id, Material.rock);
	}

	public BlockAFM(int id, int indexInTexture, Material material) {
		super(id, indexInTexture, material);
		this.setCreativeTab(AFM.tabAFM);
	}

	public BlockAFM(int id, int indexInTexture) {
		this(id, indexInTexture, Material.rock);
	}

	@Override
	public String getTextureFile() {
		return Properties.Block.TEXTURE;
	}

}
