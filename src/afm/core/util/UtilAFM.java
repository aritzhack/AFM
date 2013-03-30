package afm.core.util;

import java.util.Comparator;
import java.util.TreeMap;

import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * UtilAFM
 *
 * @author aritzh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public final class UtilAFM {

	private static Comparator<ItemStack> itemStackComparator = new Comparator<ItemStack>() {
		@Override
		public int compare(ItemStack o1, ItemStack o2) {
			return UtilAFM.sortItemStack(o1, o2);
		}
	};
	private static TreeMap<ItemStack, String> oreDictMap = new TreeMap<ItemStack, String>(itemStackComparator);

	private static int sortItemStack(ItemStack o1, ItemStack o2) {
		switch (UtilAFM.compareItemStacks(o1, o2, false, false)) {
			case 1: // If different ID
				return o1.itemID-o2.itemID;
			case 2: // If different metadata
				return o1.getItemDamage()-o2.getItemDamage();
			case -2: // If different amount (Should happen?)
				return o1.stackSize-o2.stackSize;
			default: // Else, equal
				return 0;
		}
	}

	public static void initOreDict() {
		for (String s : OreDictionary.getOreNames()) {
			for (ItemStack is : OreDictionary.getOres(s)) {
				oreDictMap.put(is, s);
			}
		}
	}

	public static EntityPlayerMP getPlayer(String username) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null)
			return null;
		EntityPlayerMP ret = server.getConfigurationManager().getPlayerForUsername(username);
		if (ret == null)
			throw new PlayerNotFoundException();
		return ret;
	}

	public static void dropEntityItem(ItemStack stack, World world, int x, int y, int z) {
		if (world.isRemote) return;
		double xD = world.rand.nextFloat() * 0.7D + (1.0D - 0.7D) * 0.5D;
		double yD = world.rand.nextFloat() * 0.7D + (1.0D - 0.7D) * 0.5D;
		double zD = world.rand.nextFloat() * 0.7D + (1.0D - 0.7D) * 0.5D;
		EntityItem item = new EntityItem(world, x + xD, y + yD, z + zD, stack);
		world.spawnEntityInWorld(item);
	}
	
	/**
	 * Compares two ItemStacks, and tells where the difference is
	 *
	 * @param s1           First stack to compare
	 * @param s2           Second stack to compare
	 * @param amountStrict Whether or not amount should be taken into account
	 * @return -2 If different amount (but same stack) <br />
	 *         -1 If exactly equal (both null means equal) (NBT equal or both null)<br /> <br />
	 *         <p/>
	 *         1 If different ID <br />
	 *         2 If different metadata (same id -> doesn't check OreDict) <br />
	 *         3 If different NBTTag (at least one has NBT data) <br />
	 *         4 If one is null <br />
	 */
	private static int compareItemStacks(ItemStack s1, ItemStack s2, boolean amountStrict, boolean notNull) {
		if ((!notNull && (s1 == null && s2 == null)) || s1 == s2) return -1;
		if ((s1 == null && s2 != null) || (s2 == null && s1 != null)) return 4;
		if (s1.itemID == s2.itemID) {
			if (s1.getItemDamage() == s2.getItemDamage()) {
				if (!(s1.hasTagCompound()) && (!s2.hasTagCompound()) || s1.getTagCompound().equals(s2.getTagCompound())) {
					if (s1.stackSize == s2.stackSize || !amountStrict) return -2;
					return -1;
				}
				return 3;
			}
			return 2;
		}
		return 1;
	}
	
	public static boolean isSameItem(ItemStack s1, ItemStack s2){
		return compareItemStacks(s1, s2, false, true)==-1;
	}

	/**
	 * Compares two ItemStacks, and tells where the difference is.
	 * OreDictionary compatible
	 *
	 * @param s1 First stack to compare
	 * @param s2 Second stack to compare
	 * @return -3 If same item (OreDict) <br />
	 *         -2 If same but different amount (only if amountStrict) <br />
	 *         -1 If exactly equal (both null means equal) <br /> <br />
	 *         <p/>
	 *         1 If different ID <br />
	 *         2 If different metadata (same id -> doesn't check OreDict) <br />
	 *         3 If different NBTTag <br />
	 *         4 If one is null <br />
	 */
	public static int compareItemStacksWithOreDict(ItemStack s1, ItemStack s2, boolean amountStrict, boolean notNull) {
		int compare = UtilAFM.compareItemStacks(s1, s2, amountStrict, notNull);
		switch (compare) {
			case 1:
			case 2:
			case 3:
				// If same in OreDict, return -3, else, tell the difference
				return UtilAFM.compareOreDict(s1, s2) ? -3 : compare;
			default:
				return compare; // If one null, already equal, or different amounts, return it
		}
	}

	/**
	 * Checks if both ISs are the same item, regardless the stack size. (Both null returns false)
	 * @param i1 First IS to compare
	 * @param i2 Second IS to compare
	 * @return Whether both IS are same item or not <br />
	 * {@link UtilAFM#compareItemStacksWithOreDict(ItemStack, ItemStack, boolean, boolean) UtilAFM.compareItemStacksWithOreDict(i1, i2, false, true)<0}
	 */
	public static boolean isSameItemOreDict(ItemStack i1, ItemStack i2) {
		return UtilAFM.compareItemStacksWithOreDict(i1, i2, false, true) < 0;
	}

	private static boolean compareOreDict(ItemStack s1, ItemStack s2) {
		String n1 = getOreDictOreName(s1);
		String n2 = getOreDictOreName(s2);
		return !(n1 == null || n2 == null) && n1.equals(n2);
	}

	public static String getOreDictOreName(ItemStack is) {
		String ret = oreDictMap.get(is);
		if (ret != null) return ret;
		return oreDictMap.get(new ItemStack(is.itemID, 1, -1));
	}

	public static final String[] colorNames = {"White", "Orange", "Magenta", "Light Blue", "Yellow", "Light Green", "Pink", "Dark Grey", "Light Grey", "Cyan",
			"Purple", "Blue", "Brown", "Green", "Red", "Black"};
}
