package afm.blocks;

import afm.data.BlockData;
import afm.items.Items;
import afm.network.AFMPacket;
import afm.network.RandomNumberPacket;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOreAFM extends BlockAFM {

	public BlockOreAFM() {
		super(BlockData.ID_ORE, BlockData.NAME_ORE_AFM, BlockData.TEXTUREINDEX_ORE);

		this.setLightValue(1.0F);
	}

	public void initRecipes() {
		GameRegistry.addSmelting(Blocks.oreAFM.blockID, new ItemStack(Items.quartz), 0.1F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {

		Random r = new Random();

		RandomNumberPacket rnPacket = new RandomNumberPacket(r.nextInt(), r.nextInt());
		AFMPacket.sendPacketToServer(rnPacket);

		return true;
	}

}