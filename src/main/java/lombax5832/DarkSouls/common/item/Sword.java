package lombax5832.DarkSouls.common.item;

import java.awt.Color;
import java.util.List;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.client.render.RadialSmokeFX;
import lombax5832.DarkSouls.lib.ItemProperties;
import lombax5832.DarkSouls.network.PacketSpawnParticles;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;

public class Sword extends ItemSword{

	EntityLiving current = null;
	public boolean homing = false;
	public static final int homingTimeout = 60;
	public static int timeoutTimer = 0;
	
	public Sword(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return null;
    }
	
	double distance = 0.25;
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity player, int par4, boolean par5){
		if(homing){
			if(current != null && !(timeoutTimer>homingTimeout)){
        		double x = current.posX - player.posX;
        		double y = current.posY + current.getEyeHeight() - player.posY;
        		double z = current.posZ - player.posZ;
        		
        		double length = Math.sqrt(x * x + y * y + z * z);
        		
        		x /= length * 1;
                y /= length * 1;
                z /= length * 1;
                
                player.moveEntity(x, y, z);
                if((player.getDistance(current.posX, current.posY+current.getEyeHeight(), current.posZ))<1){
                	double xKnockback = 0;
                	double zKnockback = 0;
                	current.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), ItemProperties.ABYSS_HOMING_HIT_DAMAGE);
                	current.knockBack(player, 0F, -x, -z);
                	par1ItemStack.damageItem(ItemProperties.ABYSS_HOMING_DAMAGE, (EntityLivingBase) player);
                	homing = false;
                	current = null;
                	DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(player.posX, player.posY-player.getEyeHeight(), player.posZ, distance, Color.BLACK, 100), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 100));
                	
                }
                timeoutTimer++;
        	}
			if(timeoutTimer>homingTimeout){
        		timeoutTimer=0;
        		homing=false;
        		current=null;
			}
			if(current!=null){
				DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(player.posX, player.posY-1, player.posZ, distance, Color.BLACK, 20), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 100));
			}
		}else if(player.isSprinting()){
			DarkSouls.packetPipeline.sendToAllAround(new PacketSpawnParticles(player.posX, player.posY-1, player.posZ, distance, Color.BLACK, 3), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 100));
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
    {
        PotionEffect speedEffect = null;
        int damageToDo = 0;
        if(player.isSprinting()){
        	speedEffect = new PotionEffect(Potion.moveSpeed.id, ItemProperties.ABYSS_SPEED_DURATION, ItemProperties.ABYSS_SPEED_AMP);
        	damageToDo = ItemProperties.ABYSS_SPEED_DAMAGE;
        }else if(player.onGround){
        	speedEffect = new PotionEffect(Potion.jump.id, ItemProperties.ABYSS_JUMP_DURATION, ItemProperties.ABYSS_JUMP_AMP);
        	damageToDo = ItemProperties.ABYSS_JUMP_DAMAGE;
        }
        subtractItemDamage(par1ItemStack,damageToDo);
        if(speedEffect != null){
        	player.addPotionEffect(speedEffect);
        }
        
        if(!player.onGround){
        	int homingRadius = ItemProperties.ABYSS_HOMING_RADIUS;
        	int homingHeight = ItemProperties.ABYSS_HOMING_HEIGHT;
        	List<Entity> entityList = par2World.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(player.posX - homingRadius, player.posY - homingHeight,
                    player.posZ - homingRadius, player.posX + homingRadius, player.posY, player.posZ + homingRadius));
        	
        	
        	double minDisSq=Integer.MAX_VALUE;
        	
        	for(Entity e : entityList){
        		if(e instanceof EntityLiving && !(e instanceof EntityPlayer)){
        			if(e.getDistanceSqToEntity(player)<minDisSq){
        				current=(EntityLiving)e;
        				homing = true;
        				minDisSq = e.getDistanceSqToEntity(player);
        			}
        		}
        	}       	
        }
        return par1ItemStack;
    }
	
	public void subtractItemDamage(ItemStack stack, int damage){
		stack.setItemDamage(stack.getItemDamage()-damage);
	}
	
	public void addItemDamge(ItemStack stack, int damage){
		stack.setItemDamage(stack.getItemDamage()+damage);
	}
	
}
