package lombax5832.DarkSouls.network;

import java.awt.Color;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import lombax5832.DarkSouls.client.render.RadialSmokeFX;
import lombax5832.DarkSouls.common.entity.EntitySouls;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSpawnSouls extends AbstractPacket{

private double x;
private double y;
private double z;
private int parentid;
	
	public PacketSpawnSouls(){}
	
	public PacketSpawnSouls(World world, double posX,double posY,double posZ,EntitySouls parent){
		this.x = posX;
		this.y = posY;
		this.z = posZ;
		this.parentid = parent.getEntityId();
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeInt(parentid);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		parentid = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
//		player.worldObj.spawnEntityInWorld(new EntitySouls(player.worldObj, player, x, y, z));
//		System.out.println("test");
		Vector v = new Vector(Math.random() - 1, Math.random() - 1, Math.random() - 1);
        v.normalize();
        double distance = 0.1;
        for(int i =0;i<10;i++){
            double xDisplacement = Math.random() - 0.5;
			double zDisplacement = Math.random() - 0.5;
			double yDisplacement = Math.random() - 0.5;
			RadialSmokeFX fx;
			fx = new lombax5832.DarkSouls.client.render.RadialSmokeFX(player.worldObj, v.x + x + xDisplacement, v.y + y + yDisplacement, v.z + z + zDisplacement, Color.GRAY, player.worldObj.getEntityByID(parentid).posX,player.worldObj.getEntityByID(parentid).posY,player.worldObj.getEntityByID(parentid).posZ, false, Integer.MAX_VALUE);
			net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
	}

	@Override
	public void handleServerSide(EntityPlayer player) {}
}
