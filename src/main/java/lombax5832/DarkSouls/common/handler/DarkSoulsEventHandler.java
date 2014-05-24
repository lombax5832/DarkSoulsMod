package lombax5832.DarkSouls.common.handler;

import java.util.List;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.entity.EntitySouls;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.network.PacketSpawnSouls;
import lombax5832.DarkSouls.util.RandomRange;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class DarkSoulsEventHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		if(event.entity instanceof EntityPlayer && DarkSoulsExtendedPlayer.get((EntityPlayer)event.entity) == null)
			DarkSoulsExtendedPlayer.register((EntityPlayer) event.entity);
		
		if(event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(DarkSoulsExtendedPlayer.EXT_PROP_NAME)==null)
			event.entity.registerExtendedProperties(DarkSoulsExtendedPlayer.EXT_PROP_NAME, new DarkSoulsExtendedPlayer((EntityPlayer) event.entity));
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event){
		
		if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
			DarkSoulsExtendedPlayer.get((EntityPlayer) event.entity).sync();
		}
		if(event.entity instanceof EntityPlayer){
			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get((EntityPlayer) event.entity);
			EntityPlayer player = (EntityPlayer) event.entity;
			if(!player.inventory.hasItem(ModItems.EstusFlask)){
			ItemStack stack = new ItemStack(ModItems.EstusFlask,1,1);
				stack.stackSize = 1;
				stack.setItemDamage(1);
				player.inventory.addItemStackToInventory(stack);
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingEntityDeath(LivingDeathEvent event){
		if(!event.entity.worldObj.isRemote&&event.source!=null&&!(event.entity instanceof EntityPlayer)){
//			EntityPlayer sourcePlayer = (EntityPlayer) event.source.getEntity();
			EntityLiving killedEntity = (EntityLiving) event.entity;
			int toAdd = 0;
			
			if(event.entity instanceof EntitySkeleton){
				EntitySkeleton entity = (EntitySkeleton) event.entity;
				if(!(entity.dimension == -1))
					toAdd = RandomRange.randomRange(200,400);
				else
					toAdd = RandomRange.randomRange(1500, 2000);
			}else{
				toAdd = RandomRange.randomRange(0,10);
			}
			
			int homingRadius = 32;
			
			List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(killedEntity, AxisAlignedBB.getBoundingBox(killedEntity.posX - homingRadius, killedEntity.posY - homingRadius,
					killedEntity.posZ - homingRadius, killedEntity.posX + homingRadius, killedEntity.posY + homingRadius, killedEntity.posZ + homingRadius));
			
        	for(Entity e : entityList){
        		if(e instanceof EntityPlayer){
        			EntityPlayer player = (EntityPlayer) e;
        			System.out.println("spawn attempted");
        			DarkSouls.packetPipeline.sendTo(new PacketSpawnSouls(player.worldObj,killedEntity.posX,killedEntity.posY+(killedEntity.getEyeHeight()/2),killedEntity.posZ,Math.min(Math.max(toAdd/10,1),200)), (EntityPlayerMP) player);
        			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
        			props.addSoulsToQueue(toAdd);
        			DarkSoulsExtendedPlayer.get(player).sync();
        		}
        	}
		}
	}
}
