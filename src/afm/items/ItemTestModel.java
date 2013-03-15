package afm.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import afm.data.BlockData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTestModel extends ItemBlock {

	public ItemTestModel(int par1) {
		super(par1);
	}
	
    @Override
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister iconRegister)
    {
        this.iconIndex = iconRegister.func_94245_a("afm:" + BlockData.NAME_TESTMODEL + "_item");
    }

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return this.iconIndex;
	}

}
