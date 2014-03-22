package lombax5832.DarkSouls.common.item;

import java.awt.Color;
import java.util.List;

import lombax5832.DarkSouls.client.render.RadialSmokeFX;
import lombax5832.DarkSouls.lib.ItemProperties;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
                if(length<1){
                	double xKnockback = 0;
                	double zKnockback = 0;
                	if(x>0){
                		xKnockback = -2;
                	}else{
                		xKnockback = 2;
                	}
                	if(z>0){
                		zKnockback = -2;
                	}else{
                		zKnockback = 2;
                	}
                	current.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), ItemProperties.ABYSS_HOMING_HIT_DAMAGE);
                	current.knockBack(player, 0F, xKnockback, zKnockback);
                	par1ItemStack.damageItem(ItemProperties.ABYSS_HOMING_DAMAGE, (EntityLivingBase) player);
                	homing = false;
                	
                }
                timeoutTimer++;
        	}else{
        		timeoutTimer=0;
        		homing=false;
        		current=null;
        		
        	}
			for(int i =0;i<20;i++){
				double xDisplacement = Math.random() - 0.5;
				double zDisplacement = Math.random() - 0.5;
				Vector v = new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1);
                v.normalize();
                if(Side.CLIENT.isClient()){
	                RadialSmokeFX fx = new RadialSmokeFX(par2World, v.x * distance + player.prevPosX + xDisplacement, v.y * (distance + .5) + player.prevPosY, v.z * distance + player.prevPosZ + zDisplacement, Color.BLACK, (EntityPlayer) player);
	                Minecraft.getMinecraft().effectRenderer.addEffect(fx);
                }
//                System.out.println("X: "+(int)(v.x * distance + player.prevPosX)+"Y: "+(int)(v.y * (distance + .5) + player.prevPosY)+"Z: "+(int)(v.z * distance + player.prevPosZ)); 
			}
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
        par1ItemStack.damageItem(damageToDo, player);
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
	
}
