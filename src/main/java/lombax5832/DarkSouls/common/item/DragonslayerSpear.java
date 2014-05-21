package lombax5832.DarkSouls.common.item;

import java.awt.Color;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
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
	private static double userPrevX, userPrevY, userPrevZ = 0;
	private int particleOnPlayerTicker,particleOnPlayerSubtractTicker = 0;
	private final int particleOnPlayerMax = 5;
	private final int particleOnPlayerSubtractMax = 10;
	
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
			if(effect!=null&&player.isSprinting()){
				if(speedTicker == 0){
					((EntityLivingBase) player).addPotionEffect(effect);
				}else if(speedTicker>=speedTickerMax){
					speedTicker=0;
				}
			}
			if(particleOnPlayerTicker > 0){
					DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(player.posX, player.posY,player.posZ, distance, Color.YELLOW, player.getEntityId(), particleOnPlayerTicker, true, 6), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 64));
				}
			if(player.onGround&&player.isSprinting()){
				particleOnPlayerTicker = particleOnPlayerMax;
				particleOnPlayerSubtractTicker = 0;
			}
			if(particleOnPlayerTicker<=particleOnPlayerMax&&particleOnPlayerTicker>0){
				particleOnPlayerSubtractTicker++;
				if(particleOnPlayerSubtractTicker>=particleOnPlayerSubtractMax){
					particleOnPlayerTicker--;
					particleOnPlayerSubtractTicker = 0;
				}
			}
		}
		
		userPrevX = player.posX;
		userPrevY = player.posY;
		userPrevZ = player.posZ;
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase entityHit, EntityLivingBase userEntity){
		DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(entityHit.posX, entityHit.posY+entityHit.getEyeHeight(), entityHit.posZ, distance, Color.YELLOW, entityHit.getEntityId(), 50, true, 1.5), new TargetPoint(userEntity.dimension, userEntity.posX, userEntity.posY, userEntity.posZ, 64));
		return super.hitEntity(par1ItemStack, entityHit, userEntity);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player){
		DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
		props.consumeSpell();
		return par1ItemStack;
	}
	
	public boolean playerHoldingItem(EntityPlayer player, ItemStack stack){
		if(player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() == stack.getItem())){
			return true;
		}
		return false;
	}
	
	public boolean entityMoved(Entity entity){
		if(entity.posX != userPrevX){
			return true;
		}
		if(entity.posZ != userPrevZ){
			return true;
		}
		return false;
	}

}
