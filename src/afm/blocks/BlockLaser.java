package afm.blocks;

import afm.core.AFM;
import afm.data.BlockData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/**
 * BlockLaser
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockLaser extends BlockAFM {

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayerMP) {
			((EntityPlayerMP) entity).sendChatToPlayer("You touched it!");
		}
		AFM.proxy.writeChatMessageToPlayer("Entity: " + entity.getEntityName());
	}

	public BlockLaser() {
		super(BlockData.ID_LASER, BlockData.NAME_LASER, TextureType.ONE_TEX);
	}

}
