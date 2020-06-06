package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 05/06/2020.
*
*/
public class BopeESP extends BopeModule {
	public BopeESP() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "ESP";
		this.tag         = "ESP";
		this.description = "ESP - Extra Sensory Perception.";

		// Release or launch the module.
		release("B.O.P.E - Module - B.O.P.E");	
	}

	@Override
	public void render(BopeEventRender event) {}
}