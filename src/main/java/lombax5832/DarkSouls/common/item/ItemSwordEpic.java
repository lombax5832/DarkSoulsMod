package lombax5832.DarkSouls.common.item;

import java.util.List;

import com.google.common.collect.Multimap;

import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import lombax5832.DarkSouls.lib.Colors;
import lombax5832.DarkSouls.lib.ItemProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemSwordEpic extends ItemSword{
	
	public ItemSwordEpic() {
		super(ModItems.POWERWEAPONS_T1);
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4){
		list.clear();
		list.add(new StringBuilder().append(Colors.COLOR_PURPLE).append(itemstack.getDisplayName()).toString());
	}
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity player, int par4, boolean par5){}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player){
		return par1ItemStack;
	}

}
