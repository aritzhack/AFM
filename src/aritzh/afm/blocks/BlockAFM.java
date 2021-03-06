package aritzh.afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import aritzh.afm.AFM;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockAFM
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class BlockAFM extends Block {

    public BlockAFM(final int id, final String blockName) {
        this(id, blockName, Material.rock);
    }

    public BlockAFM(final int id, final String blockName, final Material material) {
        super(id, material);
        this.setUnlocalizedName(blockName);
        this.setCreativeTab(AFM.tabAFM);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public abstract void registerIcons(IconRegister iconRegister);
}
