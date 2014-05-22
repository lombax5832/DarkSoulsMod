package lombax5832.DarkSouls.common.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import lombax5832.DarkSouls.lib.ItemNames;
import lombax5832.DarkSouls.lib.ModInfo;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEstusFlask extends Item{
	
	IIcon[] icons = new IIcon[2];
	
	public ItemEstusFlask(){
		super();
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
	
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player){
		
		FlaskAtributes atr = new FlaskAtributes(stack);
		DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
		
		if(props.getCurrentFlasks()>0){
			player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 25, 4));
			player.addPotionEffect(new PotionEffect(Potion.hunger.getId(), 25, 4));
			props.setCurrentFlasks(props.getCurrentFlasks()-1);
			stack.stackSize = props.getCurrentFlasks();
			System.out.println(props.getMaxFlasks()+" flasks left.");	
		}
		
		if(props.getCurrentFlasks()==0){
			return new ItemStack(stack.getItem(), 1, 1);
		}
		
		return stack;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
		
		FlaskAtributes atr = new FlaskAtributes(stack);
		DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get((EntityPlayer) player);
		
		if(props.getCurrentFlasks()>0){
			stack.stackSize = props.getCurrentFlasks();
			stack.setItemDamage(0);
			this.setUnlocalizedName(ItemNames.ESTUS_FLASK_NAME);
		}
		if(props.getCurrentFlasks()==0){
			stack = new ItemStack(this, 1, 1);
			this.setUnlocalizedName(ItemNames.ESTUS_FLASK_EMPTY_NAME);
		}
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer player) {
		
		DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(player);
//		stack.stackSize = props.getCurrentFlasks();
//		System.out.println("Player has "+stack.stackSize+" flasks.");
//		System.out.println("Player should have "+props.getCurrentFlasks()+" flasks.");
		if(stack.getItemDamage()==0){
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}
        return stack;
        
    }

	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
        return EnumAction.drink;
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack){
        return 32;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
	
	@Override
	public void registerIcons(IIconRegister iconReg){
		icons[0] = iconReg.registerIcon(ModInfo.modid+":darkSoulsEstusFlaskFull");
		icons[1] = iconReg.registerIcon(ModInfo.modid+":darkSoulsEstusFlaskEmpty");
	}
	
	public static class FlaskAtributes{
		public boolean notEmpty = true;
		
		public FlaskAtributes(ItemStack stack){
			load(stack);
		}
		
		public void save(ItemStack stack) {
            boolean newTag = false;
            NBTTagCompound tag = stack.getTagCompound();
            if (tag == null) {
                tag = new NBTTagCompound();
                newTag = true;
            }
            
            tag.setBoolean("notEmpty", notEmpty);
            
            if (newTag) {
                stack.setTagCompound(tag);
            }
		}
		
		public void load(ItemStack it) {
            NBTTagCompound tag = it.getTagCompound();

            if (tag == null)
                return;
            
            notEmpty = tag.getBoolean("notEmpty");
            
		}
	}
	
}
