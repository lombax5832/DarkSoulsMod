package lombax5832.DarkSouls.common.tileentity;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBloodstain extends TileEntity{
	public String owner;
	public long souls;
	
	public void init(EntityPlayer player, long toBloodStain){
		this.owner = player.getDisplayName();
		this.souls = toBloodStain;
	}
	
	public boolean clicked(EntityPlayer player){
		if(player.getDisplayName() == owner){
			DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
			props.addSoulsToQueue(souls);
			DarkSoulsExtendedPlayer.get((EntityPlayer) player).sync();
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			return true;
		}
		return false;
	}
}
