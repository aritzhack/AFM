package aritzh.afm.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import aritzh.afm.AFM;
import aritzh.afm.core.util.UtilNBT;
import aritzh.afm.data.ItemData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemQuartz
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemQuartz extends ItemAFM {

    public ItemQuartz() {
        super(ItemData.ID_QUARTZ, ItemData.NAME_QUARTZ, AFM.tabAFM);
        this.addRecipe();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("afm:" + ItemData.NAME_QUARTZ);
    }

    @Override
    public boolean onItemUse(final ItemStack is, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side, final float hitX, final float hitY, final float hitZ) {
        AFM.proxy.writeChatMessageToPlayer(String.format("Block hit at: X:%f, Y:%f, Z:%f", hitX, hitY, hitZ));
        return false;
    }

    private void addRecipe() {
        final ItemStack out = new ItemStack(this);

        UtilNBT.addDescriptionToStack(out, "Funciona!!");

        GameRegistry.addShapelessRecipe(out, new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond), new ItemStack(Item.diamond));
    }

}
