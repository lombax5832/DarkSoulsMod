package lombax5832.DarkSouls.client.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class PlayerGUIHandler {
	
	@SubscribeEvent
	public void onClientTick(TickEvent event){
		if(event.side == Side.CLIENT){
			System.out.println("test");
		}
	}
}
