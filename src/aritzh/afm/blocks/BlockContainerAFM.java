package aritzh.afm.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import aritzh.afm.AFM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockContainerAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class BlockContainerAFM extends BlockContainer {

    protected BlockContainerAFM(final int id, final String blockName, final Material material) {
        super(id, material);
        this.setUnlocalizedName(blockName);
        this.setCreativeTab(AFM.tabAFM);
    }

    protected BlockContainerAFM(final int id, final String blockName) {
        this(id, blockName, Material.rock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IconRegister par1IconRegister);

}
