package lombax5832.DarkSouls.common.block;

import lombax5832.DarkSouls.common.item.ItemEstusFlaskShard;
import lombax5832.DarkSouls.common.item.ModItems;
import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
	            
//	            if(world.isRemote){
	            	
	            	DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
	            	if(player.getHeldItem()!=null&&player.getHeldItem().getItem() == ModItems.EstusFlaskShard){
	            		ItemStack stack = player.getHeldItem();
	            		props.addMaxFlasks(stack.stackSize);
	            		System.out.println("Attempted to add "+stack.stackSize+" shards.");
	            		stack.stackSize=0;
	            	}
	            	if(player.isSneaking()){
	            		props.setHasSoulArrow(false);
	            		props.setCurrentSoulArrow(0);
	            		props.setCurrentFlasks(1);
	            	}else{
	            		props.setHasSoulArrow(true);
	            		props.setCurrentSoulArrow(props.getMaxSoulArrow());
	            		props.replenishFlasks();
	            		props.addSoulsToQueue(20000);
	            	}
	            	
	            	
//	            }
	            
	            return true;
	    }
}