package lombax5832.DarkSouls.common.block;

import java.util.Random;

import lombax5832.DarkSouls.common.tileentity.TileEntityBloodstain;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBloodstain extends Block implements ITileEntityProvider{

	protected BlockBloodstain(){
		super(Material.cloth);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta){
		return new TileEntityBloodstain();
	}
	
	/**
     * Called upon block activation (right click on the block.)
     */
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_){
        return ((TileEntityBloodstain)world.getTileEntity(x, y, z)).clicked(player);
    }
    
    @Override
    public int quantityDropped(Random p_149745_1_){
        return 0;
    }
}
