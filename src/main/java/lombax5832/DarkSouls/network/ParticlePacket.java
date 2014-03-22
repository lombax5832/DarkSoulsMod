package lombax5832.DarkSouls.network;

import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;

public class ParticlePacket implements IPacket{

	int i;
	
	public ParticlePacket(){}
	public ParticlePacket(int i){
		this.i = 1;
	}
	
	@Override
	public void readBytes(ByteBuf bytes) {
		i = bytes.readInt();
		System.out.println("Recieved packet with the int i = " + i);
	}

	@Override
	public void writeBytes(ByteBuf bytes) {
		bytes.writeInt(i);
	}
	@Override
	public void executeClient(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void executeServer(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
