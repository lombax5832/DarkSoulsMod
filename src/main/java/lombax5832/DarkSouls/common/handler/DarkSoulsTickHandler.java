package lombax5832.DarkSouls.common.handler;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class DarkSoulsTickHandler {

	public DarkSoulsTickHandler() {
		
	}

	
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event){
		if (event.phase == Phase.START) {
			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get((EntityPlayer) event.player);
			int toChange = 0;
			if(props.soulQueue>0&&props.soulQueueFrozen==0){
				props.soulQueueFrozen = props.soulQueue;
			}
			if(props.soulQueue>100000){
				toChange = 50000;
			}else if(props.soulQueue>10000){
				toChange = 5000;
			}else if(props.soulQueue>1000){
				toChange = 500;
			}else if(props.soulQueue>100){
				toChange = 50;
			}else if(props.soulQueue>10){
				toChange = 5;
			}else if(props.soulQueue>0){
				toChange = 1;
			}
			if(toChange>0){
				props.soulQueue -= toChange;
				props.addSouls(toChange);
			}else{
				props.soulQueueFrozen = 0;
			}
		} else {
			
		}
	}
}
