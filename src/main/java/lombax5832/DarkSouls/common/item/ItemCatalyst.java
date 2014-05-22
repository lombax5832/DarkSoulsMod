package lombax5832.DarkSouls.common.item;

import java.util.List;

import lombax5832.DarkSouls.creativetab.CreativeTabDarkSouls;
import lombax5832.DarkSouls.lib.Colors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCatalyst extends Item{
	
	private final ToolMaterial material;
	public static int magicDmg;
	public static int lightningDmg;
	public static int darkDmg;
	
	public ItemCatalyst(Item.ToolMaterial material, int magicDmg, int lightningDmg, int darkDmg){
		this.material = material;
		this.setCreativeTab(CreativeTabDarkSouls.tabDarkSouls);
		this.setMaxDamage(material.getMaxUses());
		this.maxStackSize = 1;
		this.magicDmg = magicDmg;
		this.lightningDmg = lightningDmg;
		this.darkDmg = darkDmg;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4){
		list.clear();
		list.add(new StringBuilder().append(Colors.COLOR_PURPLE).append(itemstack.getDisplayName()).toString());
		list.add(new StringBuilder().append("Magic Dmg: "+magicDmg).toString());
		list.add(new StringBuilder().append("Lightning Dmg: "+lightningDmg).toString());
		list.add(new StringBuilder().append("Dark Dmg: "+darkDmg).toString());
	}
	
	public boolean canCastSpell(String spellName){
		
		return false;
	}
}
