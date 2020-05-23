package rina.turok.bope.bopemod.guiscreen.render;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

/**
* @author Rina
*
* Created by Rina.
* 22/05/20.
*
*/
public class BopeString extends FontRenderer {
	Minecraft mc = Minecraft.getMinecraft();

	public BopeString(boolean use) {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), use);
	}
}