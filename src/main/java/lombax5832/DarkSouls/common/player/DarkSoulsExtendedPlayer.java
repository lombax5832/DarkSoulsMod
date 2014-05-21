package lombax5832.DarkSouls.common.player;

import ibxm.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.lib.NBTInfo;
import lombax5832.DarkSouls.network.PacketSyncProps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class DarkSoulsExtendedPlayer implements IExtendedEntityProperties{

	public final static String EXT_PROP_NAME = "DarkSoulsExtendedPlayer";
	
	private final EntityPlayer player;
	
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
		
		properties.setBoolean(NBTInfo.hasSoulArrow, this.hasSoulArrow);
		properties.setInteger(NBTInfo.maxSoulArrow, this.maxSoulArrow);
		properties.setInteger(NBTInfo.currentSoulArrow, this.currentSoulArrow);
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

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
	}

	
	public void replenishSpells(){
		this.currentSoulArrow = this.maxSoulArrow;
	}
	
	public int getMaxSoulArrow(){
		return this.maxSoulArrow;
	}
	
	public int getCurrentSoulArrow(){
		return this.currentSoulArrow;
	}
	
	public boolean getHasSoulArrow(){
		return this.hasSoulArrow;
	}
	
	public void setHasSoulArrow(boolean setting){
		this.hasSoulArrow = setting;
	}
	
	public void setCurrentSoulArrow(int setting){
		this.currentSoulArrow = setting;
	}
	
	public void setMaxSoulArrow(int setting){
		this.maxSoulArrow = setting;
	}

}
