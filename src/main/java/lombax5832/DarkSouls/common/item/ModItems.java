package lombax5832.DarkSouls.common.item;

import lombax5832.DarkSouls.lib.ItemNames;
import lombax5832.DarkSouls.lib.ModInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	
	public static Item AbyssGreatSword;
	public static Item DragonslayerSpear;
	
	//ToolMaterials
	public static ToolMaterial POWERWEAPONS_T1 = EnumHelper.addToolMaterial("POWERWEAONS_T1", 2, 400, 6.0F, 2.5F, 14);
	
	public static void initItems(){
		
		
		AbyssGreatSword = new AbyssGreatsword().setTextureName(ModInfo.modid+":"+ItemNames.SWORDS_NAME).setUnlocalizedName(ItemNames.SWORDS_NAME);
		GameRegistry.registerItem(AbyssGreatSword, ItemNames.SWORDS_NAME);
		
//		DragonslayerSpear = new DragonslayerSpear(ToolMaterial.IRON).setTextureName(ModInfo.modid+":"+ItemNames.SWORDS_NAME).setUnlocalizedName(ItemNames.SWORDS_NAME);
//		GameRegistry.registerItem(DragonslayerSpear, ItemNames.SWORDS_NAME);
	}
}
