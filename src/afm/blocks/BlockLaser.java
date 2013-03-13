package afm.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import afm.core.AFM;
import afm.data.BlockData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockLaser
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockLaser extends BlockAFM {
	
	Icon icon;

	public BlockLaser() {
		super(BlockData.ID_LASER, BlockData.NAME_LASER);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister iconRegister) {
			icon = iconRegister.func_94245_a(String.format("afm:%s", BlockData.NAME_LASER));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return icon;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayerMP) {
			((EntityPlayerMP) entity).sendChatToPlayer("You touched it!");
		}
		AFM.proxy.writeChatMessageToPlayer("Entity: " + entity.getEntityName());
	}

}
