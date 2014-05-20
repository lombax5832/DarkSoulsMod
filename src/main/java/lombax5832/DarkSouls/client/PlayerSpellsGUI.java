package lombax5832.DarkSouls.client;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerSpellsGUI extends Gui{
	
	private Minecraft mc;
	
	private FontRenderer fr;
	
	private ScaledResolution res;
	
	private int height,width;
	
	public PlayerSpellsGUI(Minecraft mc){
		super();
		this.mc=mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void  onRenderExperienceBar(RenderGameOverlayEvent event){
		
		this.fr = mc.fontRenderer;
		this.res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		this.height = res.getScaledHeight();
		this.width	= res.getScaledWidth();
		
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
		return;
		}
		
		DarkSoulsExtendedPlayer props = DarkSoulsExtendedPlayer.get(this.mc.thePlayer);
		
//		if(props==null || props.getMaxSoulArrow()==0){
//			return;
//		}
		
		int xPos = 2;
		int yPos = 2;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(4, 4, -1);
		fr.drawStringWithShadow("Player has Soul Arrow: "+props.getHasSoulArrow(), 10, 10, 0xFFFFFF);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
		
	}
}
