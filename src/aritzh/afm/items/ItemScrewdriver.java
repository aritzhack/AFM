package aritzh.afm.items;

import aritzh.afm.data.ItemData;
import aritzh.afm.tileEntity.TEFrameMotor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class ItemScrewdriver extends ItemAFM {

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if(te == null || !(te instanceof TEFrameMotor)) return false;

        TEFrameMotor tef = (TEFrameMotor) te;

        tef.rotate(player.isSneaking());

        player.sendChatToPlayer(new ChatMessageComponent().func_111079_a("Dir: " + tef.getDir() + " -- Face: " + tef.getFace()));
        return true;
    }

    public ItemScrewdriver() {
        super(ItemData.ID_SCREWDRIVER, ItemData.NAME_SCREWDRIVER);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("afm:" + ItemData.NAME_SCREWDRIVER);
    }

    @Override
    protected void addRecipes() {
        ItemStack is = new ItemStack(this);

        GameRegistry.addRecipe(is, "Q", "I", "I", 'I', Item.ingotIron, 'Q', Item.netherQuartz);
    }
}
