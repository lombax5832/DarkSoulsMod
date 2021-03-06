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
	public static Item EstusFlask;
	public static Item EstusFlaskShard;
	
	//ToolMaterials
	public static ToolMaterial POWERWEAPONS_T1 = EnumHelper.addToolMaterial("POWERWEAONS_T1", 2, 400, 6.0F, 2.5F, 14);
	public static ToolMaterial MAGICWEAPONS_T1 = EnumHelper.addToolMaterial("MAGICWEAPONS_T1", 0, 400, 0.0F, 0.0F, 0);
	
	public static void initItems(){
		AbyssGreatSword = new AbyssGreatsword().setTextureName(ModInfo.modid+":"+ItemNames.ABYSS_SWORD_NAME).setUnlocalizedName(ItemNames.ABYSS_SWORD_NAME);
		GameRegistry.registerItem(AbyssGreatSword, ItemNames.ABYSS_SWORD_NAME);
		
		DragonslayerSpear = new DragonslayerSpear().setTextureName(ModInfo.modid+":"+ItemNames.DRAGONSLAYER_SPEAR_NAME).setUnlocalizedName(ItemNames.DRAGONSLAYER_SPEAR_NAME);
		GameRegistry.registerItem(DragonslayerSpear, ItemNames.DRAGONSLAYER_SPEAR_NAME);
		
		EstusFlask = new ItemEstusFlask().setUnlocalizedName(ItemNames.ESTUS_FLASK_NAME);
		GameRegistry.registerItem(EstusFlask, ItemNames.ESTUS_FLASK_NAME);
		
		EstusFlaskShard = new ItemEstusFlaskShard().setTextureName(ModInfo.modid+":"+ItemNames.ESTUS_FLASK_SHARD_NAME).setUnlocalizedName(ItemNames.ESTUS_FLASK_SHARD_NAME);
		GameRegistry.registerItem(EstusFlaskShard, ItemNames.ESTUS_FLASK_SHARD_NAME);
	}
}
