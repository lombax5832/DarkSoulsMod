package lombax5832.DarkSouls.common.item;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEstusFlaskShard extends Item{
	
	public ItemEstusFlaskShard(){
		super();
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
		this.setMaxStackSize(64);
	}
	
}
