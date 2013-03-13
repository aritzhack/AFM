package afm.blocks;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import afm.data.BlockData;
import afm.items.Items;
import afm.network.AFMPacket;
import afm.network.RandomNumberPacket;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockOreAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockOreAFM extends BlockAFM {
	
	Icon icon;

	public BlockOreAFM() {
		super(BlockData.ID_ORE, BlockData.NAME_ORE_AFM);
		this.setLightValue(1.0F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister iconRegister) {
			icon = iconRegister.func_94245_a(String.format("afm:%s", BlockData.NAME_ORE_AFM));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return icon;
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