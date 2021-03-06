package aritzh.afm.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import aritzh.afm.AFM;

/**
 * ItemAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ItemAFM extends Item {

    ItemAFM(final int id, final String itemName, final CreativeTabs tab) {
        super(id);
        this.setCreativeTab(tab);
        this.setUnlocalizedName(itemName);
        this.addRecipes();
    }

    ItemAFM(final int id, final String itemName) {
        this(id, itemName, AFM.tabAFM);
    }

    @Override
    public abstract void registerIcons(IconRegister par1IconRegister);

    protected void addRecipes(){

    }

}
