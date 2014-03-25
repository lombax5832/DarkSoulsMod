package lombax5832.DarkSouls.common.item;

import lombax5832.DarkSouls.lib.ItemNames;
import lombax5832.DarkSouls.lib.ModInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ModItems {
	
	public static Item AbyssGreatSword;
	
	public static void initItems(){
		AbyssGreatSword = new AbyssGreatsword(ToolMaterial.IRON).setTextureName(ModInfo.modid+":"+ItemNames.SWORDS_NAME).setUnlocalizedName(ItemNames.SWORDS_NAME);
		GameRegistry.registerItem(AbyssGreatSword, ItemNames.SWORDS_NAME);
	}
}
