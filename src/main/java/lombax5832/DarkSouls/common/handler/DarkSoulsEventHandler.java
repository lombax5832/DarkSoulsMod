package lombax5832.DarkSouls.common.handler;

import java.util.List;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.block.ModBlocks;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.common.tileentity.TileEntityBloodstain;
import lombax5832.DarkSouls.network.PacketSpawnSouls;
import lombax5832.DarkSouls.util.RandomRange;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
			EntityLiving killedEntity = (EntityLiving) event.entity;
			int toAdd = 0;
			
			if(event.entity instanceof EntitySkeleton){
				EntitySkeleton entity = (EntitySkeleton) event.entity;
				if(!(entity.dimension == -1))
					toAdd = RandomRange.randomRange(200,400);
				else
					toAdd = RandomRange.randomRange(1500, 2000);
			}else if(event.entity instanceof EntityCreeper){
				if(!(event.source.getEntity() == event.entity)){
					toAdd = RandomRange.randomRange(500,1000);
				}else{
					toAdd = RandomRange.randomRange(50,100);
				}
			}else if(event.entity instanceof EntityZombie){
				toAdd = RandomRange.randomRange(100,200);
			}else if(event.entity instanceof EntitySpider){
				toAdd = RandomRange.randomRange(200,300);
			}else if(event.entity instanceof EntityEnderman){
				toAdd = RandomRange.randomRange(3000, 3500);
			}else if(event.entity instanceof EntityWither){
				toAdd = RandomRange.randomRange(50000, 75000);
			}else{
				toAdd = (int) RandomRange.randomRange(event.entityLiving.getMaxHealth()*20, event.entityLiving.getMaxHealth()*25);
			}
			
			int homingRadius = 32;
			
			List<Entity> entityList = event.entity.worldObj.getEntitiesWithinAABBExcludingEntity(killedEntity, AxisAlignedBB.getBoundingBox(killedEntity.posX - homingRadius, killedEntity.posY - homingRadius,
					killedEntity.posZ - homingRadius, killedEntity.posX + homingRadius, killedEntity.posY + homingRadius, killedEntity.posZ + homingRadius));
			
        	for(Entity e : entityList){
        		if(e instanceof EntityPlayer){
        			EntityPlayer player = (EntityPlayer) e;
        			DarkSouls.packetPipeline.sendTo(new PacketSpawnSouls(player.worldObj,killedEntity.posX,killedEntity.posY+(killedEntity.getEyeHeight()/2),killedEntity.posZ,Math.min(Math.max(toAdd/10,1),200)), (EntityPlayerMP) player);
        			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
        			props.addSoulsToQueue(toAdd);
        			DarkSoulsExtendedPlayer.get(player).sync();
        		}
        	}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event){
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
			
			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get((EntityPlayer) event.entity);
			long toBloodStain = props.getCurrentSouls();
			props.setSouls(0);
			NBTTagCompound playerData = new NBTTagCompound();
			event.entity.getExtendedProperties(DarkSoulsExtendedPlayer.EXT_PROP_NAME).saveNBTData(playerData);
			DarkSouls.proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);

			int x = (int)event.entity.posX;
			int y = (int)event.entity.posY;
			int z = (int)event.entity.posZ;
			
			while(!event.entity.worldObj.isAirBlock(x, y, z)){
				y++;
			}
			event.entity.worldObj.setBlock(x, y, z, ModBlocks.BlockBloodstain);
			((TileEntityBloodstain)event.entity.worldObj.getTileEntity(x, y, z)).init((EntityPlayer) event.entity, toBloodStain);
			DarkSoulsExtendedPlayer.get((EntityPlayer) event.entity).sync();
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoin(EntityJoinWorldEvent event){
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer){
			NBTTagCompound playerData = DarkSouls.proxy.getEntityData(((EntityPlayer) event.entity).getDisplayName());
			if (playerData != null) {
				event.entity.getExtendedProperties(DarkSoulsExtendedPlayer.EXT_PROP_NAME).loadNBTData(playerData);
			}
		}
	}
}
