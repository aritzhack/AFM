package aritzh.afm.blocks;

import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import aritzh.afm.data.BlockData;
import aritzh.afm.items.Items;
import aritzh.afm.network.AFMPacket;
import aritzh.afm.network.RandomNumberPacket;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockOreAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockOreAFM extends BlockAFM {

    public BlockOreAFM() {
        super(BlockData.ID_ORE, BlockData.NAME_ORE_AFM);
        this.setLightValue(1.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_ORE_AFM));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
        return this.blockIcon;
    }

    public void initRecipes() {
        GameRegistry.addSmelting(Blocks.oreAFM.blockID, new ItemStack(Items.quartz), 0.1F);
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int i, final float f, final float g, final float t) {

        final Random r = new Random();

        final RandomNumberPacket rnPacket = new RandomNumberPacket(r.nextInt(), r.nextInt());
        AFMPacket.sendPacketToServer(rnPacket);

        return true;
    }

}