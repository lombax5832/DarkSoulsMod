package lombax5832.DarkSouls.common.block;

import lombax5832.DarkSouls.lib.BlockNames;
import lombax5832.DarkSouls.lib.ModInfo;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	public static Block SpellBlock;
	public static Block BlockBloodstain;
	
	public static void initBlocks(){
		SpellBlock = new SpellBlock().setBlockTextureName(ModInfo.modid+":"+BlockNames.Spell_Block_NAME).setBlockName(BlockNames.Spell_Block_NAME);
		GameRegistry.registerBlock(SpellBlock, BlockNames.Spell_Block_NAME);
		
		BlockBloodstain = new BlockBloodstain().setBlockTextureName(ModInfo.modid+":"+BlockNames.BLOCK_BLOODSTAIN_NAME).setBlockName(BlockNames.BLOCK_BLOODSTAIN_NAME);
		GameRegistry.registerBlock(BlockBloodstain, BlockNames.BLOCK_BLOODSTAIN_NAME);
	}
}
