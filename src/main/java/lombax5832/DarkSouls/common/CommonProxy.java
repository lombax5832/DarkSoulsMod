package lombax5832.DarkSouls.common;

import java.util.HashMap;
import java.util.Map;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.entity.EntitySouls;
import lombax5832.DarkSouls.common.handler.DarkSoulsEventHandler;
import lombax5832.DarkSouls.common.handler.DarkSoulsTickHandler;
import lombax5832.DarkSouls.lib.EntityInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;

public class CommonProxy {
	
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

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
	
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}
	
	public static NBTTagCompound getEntityData(String name){
		return extendedEntityData.remove(name);
	}

}
