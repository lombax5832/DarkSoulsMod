package lombax5832.DarkSouls.client.render;

import java.awt.Color;

import lombax5832.DarkSouls.util.RandomRange;
import lombax5832.DarkSouls.util.Vector;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FollowSmokeFX extends EntityFX{
    private Vector v;
    float smokeParticleScale;
    double parX = 0;
    double parY = 0;
    double parZ = 0;
    float speedMod = 0;
    
    Entity parent = null;
    
	public FollowSmokeFX(World par1World, double xCoord,
            double yCoord, double zCoord, Color c, Entity parent) {
        super(par1World, xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
        particleRed = c.getRed() / 255F;
        particleGreen = c.getGreen() / 255F;
        particleBlue = c.getBlue() / 255F;
        particleScale *= 0.75F;
        smokeParticleScale = particleScale;
        particleMaxAge = 200;
        noClip = true;
        this.parent=parent;
    }
	
	@Override
    public void renderParticle(Tessellator par1Tessellator, float par2,
            float par3, float par4, float par5, float par6, float par7) {
        float var8 = (particleAge + par2) / 200 * 32.0F;

        if (var8 < 0.0F) {
            var8 = 0.0F;
        }

        if (var8 > 1.0F) {
            var8 = 1.0F;
        }

        particleScale = smokeParticleScale * var8;
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6,
                par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;        
        
        double x = parent.posX - posX;
        double y = parent.posY - posY;
        double z = parent.posZ - posZ;

        double length = Math.sqrt(x * x + y * y + z * z);

        speedMod=(float) RandomRange.randomRange(2.0, 10.0);
        
        x /= length * speedMod;
        y /= length * speedMod;
        z /= length * speedMod;

        if(length<0.5)
        	this.setDead();
        
        moveEntity(x, y, z);
        
        
        if (particleAge++ >= particleMaxAge) {
            this.setDead();
        }

        this.setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
    }
}
