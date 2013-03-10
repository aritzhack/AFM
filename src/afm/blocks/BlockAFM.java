package afm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import afm.core.AFM;
import afm.core.util.UtilAFM;
import afm.data.BlockData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
abstract class BlockAFM extends Block {
	
	TextureType type = TextureType.DEFAULT;
	Icon[] icons = new Icon[6];
	boolean hasMetadata = false;
	

	public BlockAFM(int id, String blockName, TextureType type) {
		this(id, blockName, Material.rock, type);
	}

	BlockAFM(int id, String blockName, Material material, TextureType type) {
		super(id, material);
		this.setUnlocalizedName(blockName);
		this.setCreativeTab(AFM.tabAFM);
		this.type = type;
	}

	@Override
	public String getTextureFile() {
		return BlockData.TEXTURE; // FIXME Not needed?
	}

	/**
	 * Depending on the type of texture, registers the icons
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister iconRegister) {
		if(this.hasMetadata) {
			this.registerCustomIcons(iconRegister);
			return;
		}
		switch (this.type) {
		case DEFAULT:
			super.func_94332_a(iconRegister);
		case ONE_TEX:
			for(int i = 0; i<6; i++){
				icons[i] = iconRegister.func_94245_a(String.format("afm:%s", UtilAFM.getBlockUnlocName(this)));
			}
			break;
		case TOP_BOTT_SIDES:
			for(ForgeDirection d : ForgeDirection.values()){ // For each side
				switch (d){
				case UP:
					icons[d.ordinal()] = iconRegister.func_94245_a(String.format("afm:%s_%s", UtilAFM.getBlockUnlocName(this), d.toString()));
					break;
				case DOWN:
					icons[d.ordinal()] = iconRegister.func_94245_a(String.format("afm:%s_%s", UtilAFM.getBlockUnlocName(this), d.toString()));
					break;
				case NORTH:
				case SOUTH:
				case WEST:
				case EAST:
					icons[d.ordinal()] = iconRegister.func_94245_a(String.format("afm:%s_%s", UtilAFM.getBlockUnlocName(this), "side"));
					break;
				default:
					break;
				}
			}
			break;
		case SIX_TEX:
			for(ForgeDirection d : ForgeDirection.values()){ // For each side
				if(d == ForgeDirection.UNKNOWN) break;
				icons[d.ordinal()] = iconRegister.func_94245_a(String.format("afm:%s_%s", UtilAFM.getBlockUnlocName(this), d.toString().toLowerCase()));
			}
			break;
		case CUSTOM:
			this.registerCustomIcons(iconRegister);
			break;
		case NONE:
			return;
		default:
			throw new UnsupportedOperationException("Texture type of blockAFM should be one of TextureType: " + TextureType.values().toString());
		}
	}
	
	/**
	 * If texture type is CUSTOM, this must be overriden <br />
	 * (Do not call super!)
	 * @param register Icon register used to register the icons
	 */
	protected void registerCustomIcons(IconRegister register){
		throw new NotImplementedException();
	};
	
	protected Icon getIcon(ForgeDirection dir, int metadata){
		if(dir == ForgeDirection.UNKNOWN) return null;
		if(this.type == TextureType.DEFAULT || this.type == TextureType.NONE) return this.getBlockTextureFromSideAndMetadata(dir.ordinal(), metadata);
		if(this.type == TextureType.CUSTOM || this.hasMetadata) return this.getCustomIcon(dir, metadata);
		return icons[dir.ordinal()];
	}
	
	protected Icon getCustomIcon(ForgeDirection dir, int metadata){
		throw new NotImplementedException();
	}
}
