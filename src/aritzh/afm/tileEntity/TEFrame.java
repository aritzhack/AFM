package aritzh.afm.tileEntity;

import aritzh.afm.core.AFMLogger;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author Aritz Lopez
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TEFrame extends TileEntity {

    public void onMove(int fromX, int fromY, int fromZ, ForgeDirection dir, TEFrameMotor motor){
        AFMLogger.debug("Moving from" + Vec3.createVectorHelper(fromX, fromY, fromZ).toString() + "towards " + dir.toString() + " Using a motor at: " + Vec3.createVectorHelper(motor.xCoord, motor.yCoord, motor.zCoord).toString());
    }
}
