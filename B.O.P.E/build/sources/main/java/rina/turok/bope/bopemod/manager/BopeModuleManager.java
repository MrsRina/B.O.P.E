package rina.turok.bope.bopemod.manager;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.util.Comparator;
import java.util.*;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules chat.
import rina.turok.bope.bopemod.hacks.chat.*;

// Modules combat.
import rina.turok.bope.bopemod.hacks.combat.*;

// Modules exploit.
import rina.turok.bope.bopemod.hacks.exploit.*;

// Modules movement.
import rina.turok.bope.bopemod.hacks.movement.*;

// Modules render.
import rina.turok.bope.bopemod.hacks.render.*;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeFinderModule;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.hacks.BopeClickGUI;
import rina.turok.bope.bopemod.hacks.BopeClickHUD;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleManager {
	private String tag;

	public static ArrayList<BopeModule> array_module = new ArrayList<BopeModule>();

	public static Minecraft mc = Minecraft.getMinecraft();

	public BopeModuleManager(String tag) {
		this.tag = tag;

		// CLick GUI and HUD.
		add_module(new BopeClickGUI());
		add_module(new BopeClickHUD());

		// Chat.
		add_module(new BopeVisualRange());
		add_module(new BopeChatSuffix());

		// Combat.
		add_module(new BopeAutoGapple());
		add_module(new BopeAutoTotem());
		add_module(new BopeCriticals());
		add_module(new BopeFastUtil());
		add_module(new BopeKillAura());
		add_module(new BopeSorround());
		add_module(new BopeVelocity());

		// Exploit.
		add_module(new BopeNoEntityTrace());
		add_module(new BopeXCarry());

		// Movement.
		add_module(new BopeEatMovement());
		add_module(new BopeStrafe());

		// Render.
		add_module(new BopeBrightness());
		add_module(new BopeHighlight());
		add_module(new BopeNoHurtCam());
		add_module(new BopeHoleColor());
		add_module(new BopeSwing());

		// Configure.
		array_module.sort(Comparator.comparing(BopeModule::get_name));
	}

	public void add_module(BopeModule module) {
		array_module.add(module);
	}

	public ArrayList<BopeModule> get_array_modules() {
		return array_module;
	}

	public ArrayList<BopeModule> get_array_active_modules() {
		ArrayList<BopeModule> actived_modules = new ArrayList<>();

		for (BopeModule modules : get_array_modules()) {
			if (modules.is_active()) {
				actived_modules.add(modules);
			}
		}

		return actived_modules;
	}

	public Vec3d process(Entity entity, double x, double y, double z) {
		return new Vec3d(
			(entity.posX - entity.lastTickPosX) * x,
			(entity.posY - entity.lastTickPosY) * y,
			(entity.posZ - entity.lastTickPosZ) * z);
	}

	public Vec3d get_interpolated_pos(Entity entity, double ticks) {
		return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(process(entity, ticks, ticks, ticks)); // x, y, z.
	}

	public void render(RenderWorldLastEvent event) {
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

		for (BopeModule modules : get_array_modules()) {
			if (modules.is_active()) {
				mc.profiler.startSection(modules.get_tag());

				modules.render(event_render);

				mc.profiler.endSection();
			}
		}

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

	public void update() {
		for (BopeModule modules : get_array_modules()) {
			if (modules.is_active()) {
				modules.update();
			}
		}
	}

	public void render() {
		for (BopeModule modules : get_array_modules()) {
			if (modules.is_active()) {
				modules.render();
			}
		}
	}

	public void bind(int event_key) {
		if (event_key == 0) {
			return;
		}

		for (BopeModule modules : get_array_modules()) {
			if (modules.get_bind(0) == event_key) {
				modules.toggle();
			}
		}
	}

	public BopeModule get_module_with_tag(String tag) {
		BopeModule module_requested = null;

		for (BopeModule module : get_array_modules()) {
			if (module.get_tag().equalsIgnoreCase(tag)) {
				module_requested = module;
			}
		}

		return module_requested;
	}

	public ArrayList<BopeModule> get_modules_with_category(BopeCategory category) {
		ArrayList<BopeModule> module_requesteds = new ArrayList<>();

		for (BopeModule modules : get_array_modules()) {
			if (modules.get_category().equals(category)) {
				module_requesteds.add(modules);
			}
		}

		return module_requesteds;
	}

	public String get_tag() {
		return this.tag;
	}
}