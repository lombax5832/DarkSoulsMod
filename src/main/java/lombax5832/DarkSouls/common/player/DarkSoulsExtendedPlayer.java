package lombax5832.DarkSouls.common.player;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.common.item.ItemEstusFlask;
import lombax5832.DarkSouls.lib.NBTInfo;
import lombax5832.DarkSouls.network.PacketSyncProps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class DarkSoulsExtendedPlayer implements IExtendedEntityProperties{

	public final static String EXT_PROP_NAME = "DarkSoulsExtendedPlayer";
	
	private final EntityPlayer player;
	
	//Souls
	private long currentSouls = 0;
	public long soulQueue = 0;
	public long soulQueueFrozen = 0;
	public final long maxSouls = 9999999999L;
	
	//Estus Flask
	private int currentFlasks = 1;
	private int maxFlasks = 1;
	
	//Soul Arrow
	private boolean hasSoulArrow = false;
	private int currentSoulArrow = 0;
	private int maxSoulArrow = 10;
	
	public DarkSoulsExtendedPlayer(EntityPlayer player){
		this.player = player;
		this.hasSoulArrow = false;
		this.currentSoulArrow = 0;
	}
	
	public static final void register(EntityPlayer player){
		player.registerExtendedProperties(DarkSoulsExtendedPlayer.EXT_PROP_NAME, new DarkSoulsExtendedPlayer(player));
	}
	
	public static final DarkSoulsExtendedPlayer get(EntityPlayer player){
		return(DarkSoulsExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		//Souls
		properties.setLong(NBTInfo.currentSouls, this.currentSouls);
		properties.setLong(NBTInfo.soulQueue, this.soulQueue);
		//Flasks
		properties.setInteger(NBTInfo.maxFlasks, this.maxFlasks);
		properties.setInteger(NBTInfo.currentFlasks, this.currentFlasks);
		//Soul Arrow
		properties.setBoolean(NBTInfo.hasSoulArrow, this.hasSoulArrow);
		properties.setInteger(NBTInfo.maxSoulArrow, this.maxSoulArrow);
		properties.setInteger(NBTInfo.currentSoulArrow, this.currentSoulArrow);
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		//Souls
		this.currentSouls = properties.getLong(NBTInfo.currentSouls);
		this.soulQueue = properties.getLong(NBTInfo.soulQueue);
		//Flasks
		this.maxFlasks = properties.getInteger(NBTInfo.maxFlasks);
		this.currentFlasks = properties.getInteger(NBTInfo.currentFlasks);
		//Soul Arrow
		this.hasSoulArrow = properties.getBoolean(NBTInfo.hasSoulArrow);
		this.maxSoulArrow = properties.getInteger(NBTInfo.maxSoulArrow);
		this.currentSoulArrow = properties.getInteger(NBTInfo.currentSoulArrow);
		
	}

	@Override
	public void init(Entity entity, World world) {}
	
	public boolean consumeSpell(){
		if(this.currentSoulArrow>0){
			this.currentSoulArrow--;
			return true;
		}
		return false;
	}
	
	public final void sync(){
		DarkSouls.packetPipeline.sendTo(new PacketSyncProps(player), (EntityPlayerMP) player);
		ItemStack stack = null;
		for(int i=0;i<36;i++){
			stack = player.inventory.getStackInSlot(i);
			if(stack!= null && stack.getItem() != null && stack.getItem() instanceof ItemEstusFlask){
			}
		}
	}

	
	public void replenishSpells(){
		this.currentSoulArrow = this.maxSoulArrow;
	}
	
	public void replenishFlasks(){
		this.setCurrentFlasks(this.getMaxFlasks());
	}
	
	//Souls
	public long getCurrentSouls(){
		return this.currentSouls;
	}
	public void setSouls(long setting){
		this.currentSouls = Math.min(setting, this.maxSouls);
	}
	public void addSouls(long setting){
		this.currentSouls = Math.min(setting+this.currentSouls,this.maxSouls);
	}
	public void addSoulsToQueue(long souls){
		this.soulQueue = Math.min(this.soulQueue+souls,this.maxSouls-this.currentSouls);
		this.soulQueueFrozen += souls;
	}
	//Current Flasks
	public int getMaxFlasks(){
		return this.maxFlasks;
	}
	public void setMaxFlasks(int setting){
		this.maxFlasks = setting;
	}
	public void addMaxFlasks(int setting){
		this.maxFlasks = Math.min(this.maxFlasks+setting, 64);
	}
	
	//Current Flasks
	public int getCurrentFlasks(){
		return this.currentFlasks;
	}
	public void setCurrentFlasks(int setting){
		this.currentFlasks = setting;
	}
	
	//Max Soul Arrow
	public int getMaxSoulArrow(){
		return this.maxSoulArrow;
	}
	public void setMaxSoulArrow(int setting){
		this.maxSoulArrow = setting;
	}
	//Current Soul Arrow
	public void setCurrentSoulArrow(int setting){
		this.currentSoulArrow = setting;
	}
	public int getCurrentSoulArrow(){
		return this.currentSoulArrow;
	}
	//Has Soul Arrow
	public boolean getHasSoulArrow(){
		return this.hasSoulArrow;
	}
	public void setHasSoulArrow(boolean setting){
		this.hasSoulArrow = setting;
	}
	
	

}
