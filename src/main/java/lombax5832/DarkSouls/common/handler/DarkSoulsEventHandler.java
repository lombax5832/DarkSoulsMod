package lombax5832.DarkSouls.common.handler;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
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
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
			DarkSoulsExtendedPlayer.get((EntityPlayer) event.entity).sync();
	}
}
