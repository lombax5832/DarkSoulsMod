package lombax5832.DarkSouls.common.item;

import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class DragonslayerSpear extends ItemSword{

	public DragonslayerSpear(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack){
        return null;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity player, int par4, boolean par5){
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player){
		return par1ItemStack;
	}

}
