package aritzh.afm.core;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import aritzh.afm.blocks.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * TabAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TabAFM extends CreativeTabs {

    public TabAFM(final int par1) {
        super(par1, "tabAFM");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "Tab AFM";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Blocks.oreAFM);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(final List list) {
        super.displayAllReleventItems(list);
        // Here are added the items that have some special properties

        // ItemStack i1 = new ItemStack(Blocks.tintedGlass, 1, 0);
        // UtilAFM.addDescriptionToStack(i1, i1.getItemName());
        // list.add(i1);
    }

}
