package lombax5832.DarkSouls.common.item;

import lombax5832.DarkSouls.lib.ItemNames;
import lombax5832.DarkSouls.lib.ModInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ModItems {
	public static void initItems(){
		Item Swords = new Sword(ToolMaterial.IRON).setTextureName(ModInfo.modid+":"+ItemNames.SWORDS_NAME).setUnlocalizedName(ItemNames.SWORDS_NAME);
		GameRegistry.registerItem(Swords, ItemNames.SWORDS_NAME);
	}
}
