package lombax5832.DarkSouls.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import lombax5832.DarkSouls.common.CommonProxy;

public class ClientProxy extends CommonProxy{
	@Override
	public boolean isClient(){
		return true;
	}
	
	@Override
    public void registerRenderTickHandler() {
//		FMLCommonHandler.instance().bus().register(new PlayerGUIHandler());
//		new PlayerGUIHandler();
//		MinecraftForge.EVENT_BUS.register(new PlayerSpellsGUI(Minecraft.getMinecraft()));
    }
}
