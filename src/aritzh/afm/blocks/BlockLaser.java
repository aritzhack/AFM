package aritzh.afm.blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import aritzh.afm.core.AFMLogger;
import aritzh.afm.data.BlockData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockLaser
 * 
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockLaser extends BlockAFM {

	public BlockLaser() {
		super(BlockData.ID_LASER, BlockData.NAME_LASER);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(String.format("afm:%s", BlockData.NAME_LASER));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return this.blockIcon;
	}

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayerMP) {
			((EntityPlayerMP) entity).sendChatToPlayer("You touched it!");
		}
		AFMLogger.debug("Entity touched it: " + entity.toString());
	}

}
