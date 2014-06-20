package aritzh.afm.tileEntity;

import aritzh.afm.core.AFMLogger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TEFrameMotor extends TileEntity {

    private ForgeDirection dir = ForgeDirection.NORTH;
    private ForgeDirection face = ForgeDirection.NORTH;

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.dir = ForgeDirection.values()[nbt.getByte("Dir")];
        this.face = ForgeDirection.values()[nbt.getByte("Face")];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        nbt.setByte("Face", (byte) this.face.ordinal());
        nbt.setByte("Dir", (byte) this.dir.ordinal());
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        this.move();
        return true;
    }
    
    public ForgeDirection getDir() {
        return dir;
    }

    public ForgeDirection getFace() {
        return face;
    }

    public boolean isActive(ForgeDirection face){
        return face == this.face;
    }
    
    public ForgeDirection[] rotate(boolean face){
        ForgeDirection[] ret = new ForgeDirection[2];
        if(face) System.out.println("Sii!!");
        if(face) nextFace();
        else nextDirection();
        
        ret[0] = this.face;
        ret[1] = this.dir;

        this.worldObj.markBlockForRenderUpdate(this.xCoord, this.yCoord, this.zCoord);
        return ret;
    }
    
    private void nextFace(){
        switch(this.face){
            case NORTH:
                this.face = ForgeDirection.SOUTH;
                break;
            case SOUTH:
                this.face = ForgeDirection.EAST;
                break;
            case EAST:
                this.face = ForgeDirection.WEST;
                break;
            case WEST:
                this.face = ForgeDirection.UP;
                break;
            case UP:
                this.face = ForgeDirection.DOWN;
                break;
            case DOWN:
            case UNKNOWN:
                this.face = ForgeDirection.NORTH;
                break;
        }
    }
    
    private void nextDirection(){

        switch(this.dir){
            case NORTH:
                this.dir = ForgeDirection.SOUTH;
                break;
            case SOUTH:
                this.dir = ForgeDirection.EAST;
                break;
            case EAST:
                this.dir = ForgeDirection.WEST;
                break;
            case WEST:
            default:
                this.dir = ForgeDirection.NORTH;
                break;
        }
    }
    
    private ForgeDirection getPointingDirection(){

        AFMLogger.debug("Face: " + this.face + " -- Dir: " + this.dir);

        if(this.face == ForgeDirection.NORTH || this.face == ForgeDirection.SOUTH || this.face == ForgeDirection.EAST || this.face == ForgeDirection.WEST)
            if(this.dir == ForgeDirection.NORTH) return ForgeDirection.UP;
            else if(this.dir == ForgeDirection.SOUTH) return ForgeDirection.DOWN;

        switch (this.face){
            case UP:
            case DOWN:
                return this.dir;
            case NORTH:
                switch (this.dir){
                    case EAST:
                        return ForgeDirection.WEST;
                    case WEST:
                        return ForgeDirection.EAST;
                }
            case SOUTH:
                return this.dir;
            case EAST:
                switch (this.dir){
                    case EAST:
                        return ForgeDirection.NORTH;
                    case WEST:
                        return ForgeDirection.SOUTH;
                }
            case WEST:
                switch (this.dir){
                    case EAST:
                        return ForgeDirection.SOUTH;
                    case WEST:
                        return ForgeDirection.NORTH;
                }
        }

        return ForgeDirection.NORTH;
    }

    public void move(){
        int beforeX = this.xCoord+this.face.offsetX;
        int beforeY = this.yCoord+this.face.offsetY;
        int beforeZ = this.zCoord+this.face.offsetZ;

        ForgeDirection dir = this.getPointingDirection();
        int afterX = beforeX + dir.offsetX;
        int afterY = beforeY + dir.offsetY;
        int afterZ = beforeZ + dir.offsetZ;

        int blockID = this.worldObj.getBlockId(beforeX, beforeY, beforeZ);
        int blockMeta = this.worldObj.getBlockMetadata(beforeX, beforeY, beforeZ);

        AFMLogger.debug("Face: " + this.getFace());

        AFMLogger.debug("Moving ID: " + blockID + " -- Meta: " + blockMeta + " -- Dir: " + dir);

        TileEntity te = this.worldObj.getBlockTileEntity(xCoord,yCoord,zCoord);

        if(te != null && te instanceof TEFrame){
            ((TEFrame)te).onMove(beforeX, beforeY, beforeZ, dir, this);
        }

        this.worldObj.setBlock(afterX, afterY, afterZ, blockID, blockMeta, 3);
        this.worldObj.setBlockToAir(beforeX, beforeY, beforeZ);
    }
}
