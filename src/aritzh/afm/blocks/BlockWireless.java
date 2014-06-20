package aritzh.afm.blocks;

import aritzh.afm.data.BlockData;
import aritzh.afm.tileEntity.TEWireless;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockWireless extends BlockContainerAFM {
    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return -1;
    }

    public BlockWireless() {
        super(BlockData.ID_WIRELESS, BlockData.NAME_WIRELESS);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TEWireless();
    }
}
