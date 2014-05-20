package lombax5832.DarkSouls.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import lombax5832.DarkSouls.lib.BlockNames;
import lombax5832.DarkSouls.lib.ModInfo;
import net.minecraft.block.Block;

public class ModBlocks {
	public static Block SpellBlock;
	
	public static void initBlocks(){
		SpellBlock = new SpellBlock().setBlockTextureName(ModInfo.modid+":"+BlockNames.Spell_Block_NAME).setBlockName(BlockNames.Spell_Block_NAME);
		GameRegistry.registerBlock(SpellBlock, BlockNames.Spell_Block_NAME);
	}
}
