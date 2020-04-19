package rina.turok.bope.bopemod.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules chat.
import rina.turok.bope.bopemod.hacks.chat.*;

// Modules combat.
// import rina.turok.bope.bopemod.hacks.combat.*;

// Modules exploit.
import rina.turok.bope.bopemod.hacks.exploit.*;

// Modules render.
// import rina.turok.bope.bopemod.hacks.render.*;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeFinderModule;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Framework.
import rina.turok.bope.framework.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleManager {
	String tag;

	public static ArrayList<BopeModule> array_module = new ArrayList<BopeModule>();

	public static Minecraft mc = Minecraft.getMinecraft();

	public BopeModuleManager(String tag) {
		this.tag = tag;

		// Chat.
		add_module(new BopeModuleTest());

		// Combat.
		// init_bope_combat_modules();

		// Exploit.
		add_module(new BopeExtraInventory());

		// Render.
		// init_bope_render_modules();
	}

	public void add_module(BopeModule module) {
		array_module.add(module);
	}

	public ArrayList<BopeModule> get_array_modules() {
		return array_module;
	}

	public static Vec3d process(Entity entity, double x, double y, double z) {
		return new Vec3d(
			(entity.posX - entity.lastTickPosX) * x,
			(entity.posY - entity.lastTickPosY) * y,
			(entity.posZ - entity.lastTickPosZ) * z);
	}

	public static Vec3d get_interpolated_pos(Entity entity, double ticks) {
		return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(process(entity, ticks, ticks, ticks)); // x, y, z.
	}

	public void onWorldRender(RenderWorldLastEvent event) {
		mc.profiler.startSection("bope");
		mc.profiler.startSection("setup");

		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.disableDepth();

		GlStateManager.glLineWidth(1f);

		Vec3d pos = get_interpolated_pos(mc.player, event.getPartialTicks());

		BopeEventRender event_render = new BopeEventRender(TurokRenderHelp.INSTANCE, pos);

		event_render.reset_translation();

		mc.profiler.endSection();

		get_array_modules().stream().filter(module -> module.is_active()).forEach(module -> {
			mc.profiler.startSection(module.get_name());

			module.onWorldRender(event_render);

			mc.profiler.endSection();
		});

		mc.profiler.startSection("release");

		GlStateManager.glLineWidth(1f);

		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.enableDepth();
		GlStateManager.enableCull();

		TurokRenderHelp.release_gl();

		mc.profiler.endSection();
		mc.profiler.endSection();
	}

	public void onUpdate() {
		get_array_modules().stream().filter(BopeModule::is_active).forEach(BopeModule::onUpdate);
	}

	public void onRender() {
		get_array_modules().stream().filter(BopeModule::is_active).forEach(BopeModule::onRender);
	}

	public String get_tag() {
		return this.tag;
	}

	public void onBind(int event_key) {
		if (event_key == 0) {
			return;
		}

		get_array_modules().forEach(module -> {
			if (module.get_bind().pressed(event_key)) {
				module.toggle();
			}
		});
	}

	public static BopeModule get_module_with_tag(String tag) {
		BopeModule module_requested = null;

		for (BopeModule module : get_array_modules()) {
			if (module.get_tag().equalsIgnoreCase(tag)) {
				module_requested = module.class;
			}
		}

		return module_requested;
	}
}