package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.core.Properties;
import afm.tileEntity.TESharedCrafting;

public class BlockSharedCraftingTable extends BlockContainerAFM {

	public BlockSharedCraftingTable() {
		super(Properties.Block.ID_SHAREDCRAFTING,
				Block.workbench.blockIndexInTexture, Material.wood);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TESharedCrafting();
	}

	@Override
	public String getTextureFile() {
		return Block.workbench.getTextureFile();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int i, float f, float g, float t) {

		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te == null || player.isSneaking()) return false;

		player.openGui(AFM.afm, Properties.GUI.ID_SHAREDCRAFTING, world, x, y,
				z);
		return true;
	}

	@Override
	public int getBlockTextureFromSide(int side) {
		return Block.workbench.getBlockTextureFromSide(side);
	}

}
