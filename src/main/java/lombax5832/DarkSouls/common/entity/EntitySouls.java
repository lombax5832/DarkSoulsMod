package lombax5832.DarkSouls.common.entity;

import java.awt.Color;

import lombax5832.DarkSouls.DarkSouls;
import lombax5832.DarkSouls.client.render.RadialSmokeFX;
import lombax5832.DarkSouls.network.PacketSpawnSouls;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySouls extends EntityThrowable{
	
	private final EntityPlayer homing;
	private double x,y,z;
	
	public EntitySouls(World par1World, EntityPlayer player, double x,double y,double z) {
		super(par1World);
		this.homing = player;
		this.noClip = true;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit != null&&mop.entityHit==(Entity)homing){
//			this.setDead();
		}
	}
	
	@Override
	public void onUpdate(){
		
		System.out.println("test");
		double minDisSq = Integer.MAX_VALUE;
		minDisSq = homing.getDistanceSqToEntity(this);
		if (homing != null) {
            double x = homing.posX - posX;
            double y = homing.posY - posY;
            double z = homing.posZ - posZ;

            double length = Math.sqrt(x * x + y * y + z * z);

            x /= length * 1;
            y /= length * 1;
            z /= length * 1;
            this.moveEntity(x, y, z);
        }
	}
	
}
