package lombax5832.DarkSouls.common;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.entity.EntitySouls;
import lombax5832.DarkSouls.common.handler.DarkSoulsEventHandler;
import lombax5832.DarkSouls.common.handler.DarkSoulsTickHandler;
import lombax5832.DarkSouls.lib.EntityInfo;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CommonProxy {

	public boolean isClient() {
		return false;
	}

	public void registerRenderTickHandler() {
	}

	public void registerEventHandler(){
		MinecraftForge.EVENT_BUS.register(new DarkSoulsEventHandler());
		FMLCommonHandler.instance().bus().register(new DarkSoulsTickHandler());
	}
	
	public void registerEntities(){
		EntityRegistry.registerModEntity(EntitySouls.class, EntityInfo.entitySouls, 1, DarkSouls.instance, 64, 10, false);
	}
	
}
