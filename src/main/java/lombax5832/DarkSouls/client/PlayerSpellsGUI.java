package lombax5832.DarkSouls.client;

import lombax5832.DarkSouls.common.player.DarkSoulsExtendedPlayer;
import lombax5832.DarkSouls.lib.ModInfo;
import lombax5832.DarkSouls.util.WriteString;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerSpellsGUI extends Gui{
	
	private Minecraft mc;
	
	private static final ResourceLocation texturepath = new ResourceLocation(ModInfo.modid, "textures/gui/guiSoulArrow.png");
	
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
		
		int xPos = (int) ((width/2)-70);
		int yPos = (int) ((height)-95);
		
		String toWrite = "";
		
		if(props.getHasSoulArrow())
			toWrite =props.getCurrentSoulArrow()+"/"+props.getMaxSoulArrow();
		else
			toWrite=width+"x"+height;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		WriteString.shadowString(fr, toWrite, xPos-(fr.getStringWidth(toWrite)/2)+12, yPos+30, 0xFFFFFF);
		
		if(props.getHasSoulArrow())
			toWrite ="Soul Arrow";
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		WriteString.shadowString(fr, toWrite, xPos-(fr.getStringWidth(toWrite)/2)+12, yPos-13, 0xFFFFFF);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(xPos, yPos, 0);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.getTextureManager().bindTexture(texturepath);
		this.drawTexturedModalRect(0, 0, 0, 0, 65, 65);
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glPopMatrix();
		
	}
}
