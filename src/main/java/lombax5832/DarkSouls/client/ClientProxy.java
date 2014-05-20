package lombax5832.DarkSouls.client;

import lombax5832.DarkSouls.client.render.PlayerGUIHandler;
import lombax5832.DarkSouls.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	@Override
	public boolean isClient(){
		return true;
	}
	
	@Override
    public void registerRenderTickHandler() {
		MinecraftForge.EVENT_BUS.register(new PlayerGUIHandler());
    }
}
