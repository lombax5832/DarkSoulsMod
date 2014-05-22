package lombax5832.DarkSouls.common.handler;

import lombax5832.DarkSouls.common.item.ItemEstusFlask;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
}
