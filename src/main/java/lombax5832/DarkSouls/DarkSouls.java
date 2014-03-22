package lombax5832.DarkSouls;

import lombax5832.DarkSouls.common.CommonProxy;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.lib.ModInfo;
import lombax5832.DarkSouls.network.PacketPipeline;
import lombax5832.DarkSouls.network.PacketSpawnParticles;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.modid, name = ModInfo.name, version = ModInfo.version)
public class DarkSouls {
	
	@SidedProxy(clientSide = "lombax5832.DarkSouls.client.ClientProxy", serverSide="lombax5832.DarkSouls.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.modid)
	public static DarkSouls instance;
	
	public static final PacketPipeline packetPipeline = new PacketPipeline();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModItems.initItems();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		packetPipeline.initialize();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		packetPipeline.postInitialize();
	}

}
