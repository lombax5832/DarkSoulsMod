package lombax5832.DarkSouls.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSyncProps extends AbstractPacket{

	private NBTTagCompound data;
	
	public PacketSyncProps(){}
	
	public PacketSyncProps(EntityPlayer player){
		data = new NBTTagCompound();
		DarkSoulsExtendedPlayer.get(player).saveNBTData(data);
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		ByteBufUtils.writeTag(buffer, data);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		data = ByteBufUtils.readTag(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		DarkSoulsExtendedPlayer.get(player).loadNBTData(data);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {}

}
