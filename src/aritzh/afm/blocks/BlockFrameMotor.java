package aritzh.afm.blocks;

import aritzh.afm.data.BlockData;
import aritzh.afm.tileEntity.TEFrameMotor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFrameMotor extends BlockContainerAFM {

    Icon normal = null;
    Icon activeN = null;
    Icon activeS = null;
    Icon activeE = null;
    Icon activeW = null;

    boolean activated = false;

    public BlockFrameMotor() {
        super(BlockData.ID_FRAME_MOTOR, BlockData.NAME_FRAME_MOTOR);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighBlockID) {

        if(world.isBlockIndirectlyGettingPowered(x, y, z) && !this.activated){
            world.addBlockEvent(x, y, z, this.blockID, 0, 0);
        }
        this.activated = world.isBlockIndirectlyGettingPowered(x, y, z);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister iconRegister) {
        this.normal = this.blockIcon = iconRegister.registerIcon("afm:frameMotorNormal");
        this.activeN = iconRegister.registerIcon("afm:frameMotorActiveN");
        this.activeS = iconRegister.registerIcon("afm:frameMotorActiveS");
        this.activeE = iconRegister.registerIcon("afm:frameMotorActiveE");
        this.activeW = iconRegister.registerIcon("afm:frameMotorActiveW");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(final IBlockAccess blockAccess, final int x, final int y, final int z, final int side) {
        final ForgeDirection face = ForgeDirection.getOrientation(side);
        
        TileEntity te = blockAccess.getBlockTileEntity(x, y, z);
        if(te == null || !(te instanceof TEFrameMotor)) {
            return this.normal;
        }
        
        TEFrameMotor tef = (TEFrameMotor) te;
        if(tef.isActive(face)){
            switch(tef.getDir()){
                case NORTH:
                    return this.activeN;
                case SOUTH:
                    return this.activeS;
                case EAST:
                    return this.activeE;
                case WEST:
                    return this.activeW;
                default:
                    break;
            }
        }
        return this.normal;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TEFrameMotor();
    }

}
