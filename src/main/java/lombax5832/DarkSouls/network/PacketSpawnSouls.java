package lombax5832.DarkSouls.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.awt.Color;

import lombax5832.DarkSouls.client.render.FollowSmokeFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSpawnSouls extends AbstractPacket{

private double x;
private double y;
private double z;
private int spawnAmt;
//private int parentid;
	
	public PacketSpawnSouls(){}
	
	public PacketSpawnSouls(World world, double posX,double posY,double posZ,int spawnAmt){
		this.x = posX;
		this.y = posY;
		this.z = posZ;
		this.spawnAmt = spawnAmt;
//		this.parentid = parent.getEntityId();
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeInt(spawnAmt);
//		buffer.writeInt(parentid);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		spawnAmt = buffer.readInt();
//		parentid = buffer.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer player) {
		for(int i=0;i<spawnAmt;i++){
			
			double xDisplacement = Math.random() - 0.5;
			double yDisplacement = Math.random() - 0.5;
			double zDisplacement = Math.random() - 0.5;
			
			FollowSmokeFX fx = new FollowSmokeFX(player.worldObj, x+xDisplacement, y+yDisplacement, z+zDisplacement, Color.GRAY, player);
			net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {}
}
