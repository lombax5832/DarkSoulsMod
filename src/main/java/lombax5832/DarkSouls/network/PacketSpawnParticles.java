package lombax5832.DarkSouls.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.awt.Color;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.client.render.RadialSmokeFX;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSpawnParticles extends AbstractPacket{

	private double x,y,z;
	private Entity parent;
	private int parentID;
	private Color Color;
	private double distance;
	private int particlesPerTick;
	public PacketSpawnParticles(){}
	
	public PacketSpawnParticles(double x,double y,double z, double distance, Color Color, int parentID, int particlesPerSec){
		this.x = x;
		this.y = y;
		this.z = z;
		this.distance = distance;
		this.parentID = parentID; 
		this.Color = Color;
		this.particlesPerTick = particlesPerSec;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeInt(parentID);
		buffer.writeDouble(distance);
		buffer.writeInt(particlesPerTick);
		ByteBufUtils.writeUTF8String(buffer, Integer.toString(Color.getRGB()));
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		parentID = buffer.readInt();
		distance = buffer.readDouble();
		particlesPerTick = buffer.readInt();
		Color = new Color(Integer.parseInt(ByteBufUtils.readUTF8String(buffer)));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleClientSide(EntityPlayer player) {
		World world = player.worldObj;
		
		Entity parent = world.getEntityByID(parentID);
		
		for(int i =0;i<particlesPerTick;i++){
			double xDisplacement = Math.random() - 0.5;
			double zDisplacement = Math.random() - 0.5;
			
			Vector v = new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1);
            v.normalize();
            
            v.y *= 1;
            
            RadialSmokeFX fx;
            
            if(player == parent){
            	fx = new lombax5832.DarkSouls.client.render.RadialSmokeFX(world, v.x * distance + x + xDisplacement, v.y * (distance + 0.5) + y, v.z * distance + z + zDisplacement, Color.BLACK, (EntityPlayer) parent);
            }else{
            	fx = new lombax5832.DarkSouls.client.render.RadialSmokeFX(world, v.x * distance + x + xDisplacement, v.y * (distance + 0.5) + y-0.5, v.z * distance + z + zDisplacement, Color.BLACK, (EntityPlayer) parent);
            }
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
		}
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		// TODO Auto-generated method stub
	}
	
}
