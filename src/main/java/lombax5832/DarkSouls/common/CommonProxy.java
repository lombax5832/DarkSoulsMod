package lombax5832.DarkSouls.common;

import lombax5832.DarkSouls.common.handler.DarkSoulsEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public boolean isClient() {
		return false;
	}

	public void registerRenderTickHandler() {
	}

	public void registerEventHandler(){
		MinecraftForge.EVENT_BUS.register(new DarkSoulsEventHandler());
	}
	
}
