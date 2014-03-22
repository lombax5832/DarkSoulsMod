package lombax5832.DarkSouls;

import java.util.EnumMap;

import lombax5832.DarkSouls.common.CommonProxy;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.lib.ModInfo;
import lombax5832.DarkSouls.network.ChannelHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class DarkSouls {
	
	@SidedProxy(clientSide = "lombax5832.DarkSouls.client.ClientProxy", serverSide="lombax5832.DarkSouls.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.modid)
	public static DarkSouls instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		EnumMap<Side, FMLEmbeddedChannel> channels = NetworkRegistry.INSTANCE.newChannel(ModInfo.channelName, new ChannelHandler());
		ModItems.initItems();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}

}
