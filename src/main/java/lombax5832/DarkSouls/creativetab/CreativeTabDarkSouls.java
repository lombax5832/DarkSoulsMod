package lombax5832.DarkSouls.creativetab;

import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabDarkSouls extends CreativeTabs{

	public static final CreativeTabs tabDarkSouls = new CreativeTabDarkSouls(ModInfo.modid);
	
	public CreativeTabDarkSouls(String label) {
		super(label);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return ModItems.AbyssGreatSword;
	}
	
}
