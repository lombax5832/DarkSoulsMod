package lombax5832.DarkSouls.common.item;

import java.awt.Color;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.lib.ItemProperties;
import lombax5832.DarkSouls.network.PacketSpawnParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DragonslayerSpear extends ItemSwordEpic{
	
	private int speedTicker = 0;
	private final int speedTickerMax = 1;
	double distance = 0.25;
	
	public DragonslayerSpear() {
		super();
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack){
        return null;
    }
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity player, int par4, boolean par5){
		if(playerHoldingItem((EntityPlayer) player, par1ItemStack)){
			PotionEffect effect = null;
			int damageToDo;
			effect=new PotionEffect(Potion.moveSpeed.id, speedTickerMax, ItemProperties.DRAGON_SLAYER_SPEAR_SPEED_AMP);
			if(effect!=null){
				if(speedTicker == 0){
					((EntityLivingBase) player).addPotionEffect(effect);
				}else if(speedTicker>=speedTickerMax){
					speedTicker=0;
				}
			}
		}
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase entityHit, EntityLivingBase userEntity){
		DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(entityHit.posX, entityHit.posY+2, entityHit.posZ, distance, Color.YELLOW, entityHit.getEntityId(), 50), new TargetPoint(userEntity.dimension, userEntity.posX, userEntity.posY, userEntity.posZ, 64));
		return super.hitEntity(par1ItemStack, entityHit, userEntity);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player){
		return par1ItemStack;
	}
	
	public boolean playerHoldingItem(EntityPlayer player, ItemStack stack){
		if(player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == stack.getItem())){
			return true;
		}
		return false;
	}

}
