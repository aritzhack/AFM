package aritzh.afm.blocks;

import aritzh.afm.data.BlockData;
import aritzh.afm.tileEntity.TEFrame;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockFrame extends BlockContainerAFM {

    protected BlockFrame() {
        super(BlockData.ID_FRAME, BlockData.NAME_FRAME);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("afm:frame");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TEFrame();
    }
}
