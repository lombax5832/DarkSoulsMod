package lombax5832.DarkSouls.common.block;

import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import lombax5832.DarkSouls.lib.NBTInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpellBlock extends Block{

	public static World world;
	public static int thisx;
	public static int thisy;
	public static int thisz;
	
	protected SpellBlock() {
		super(Material.iron);
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
		this.setHardness(5.6F);
        this.setResistance(56.34F);
        this.setStepSound(Block.soundTypeMetal);
	}
	
	 @Override
	    public boolean onBlockActivated(World world, int x, int y, int z,
	                    EntityPlayer player, int idk, float what, float these, float are) {
		 
	            this.world = world;
	            this.thisx = x;
	            this.thisy = y;
	            this.thisz = z;
	            
	            player.getEntityData().setBoolean(NBTInfo.SoulArrow, true);
	            
	            return true;
	    }
}