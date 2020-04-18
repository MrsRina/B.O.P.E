package rina.turok.bope.bopemod.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;

import org.reflections.Reflections;
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

	static HashMap<String, BopeModule> hash_module = new HashMap<>();

	public static Minecraft mc = Minecraft.getMinecraft();

	public BopeModuleManager(String tag) {
		this.tag = tag;

		// Chat.
		init_bope_chat_modules();

		// Combat.
		// init_bope_combat_modules();

		// Exploit.
		init_bope_exploit_modules();

		// Render.
		// init_bope_render_modules();

		// Update hash modules.
		initialize_name_modules();
	}

	public void init_bope_chat_modules() {
		add_module(new BopeModuleTest());
	}

	public void init_bope_combat_modules() {}

	public void init_bope_exploit_modules() {
		add_module(new BopeExtraInventory());
	}

	public void init_bope_render_modules() {}

	public void add_module(BopeModule module) {
		array_module.add(module);
	}

	public void initialize_name_modules() {
		hash_module.clear();

		for (BopeModule modules : array_module) {
			hash_module.put(modules.get_tag().toLowerCase(), modules);
		}
	}

	public ArrayList<BopeModule> get_array_modules() {
		return array_module;
	}

	public static BopeModule get_module_with_tag(String module) {
		return hash_module.get(module.toLowerCase());
	}

	public void onBind(int event_key) {
		if (event_key == 0) {
			return;
		}

		array_module.forEach(module -> {
			if (module.get_bind().pressed(event_key)) {
				module.toggle();
			}
		});
	}

	public void onWorldRender(RenderWorldLastEvent event) {
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

		array_module.stream().filter(module -> module.is_active()).forEach(module -> {
			module.onWorldRender(event_render);
		});

		GlStateManager.glLineWidth(1f);

		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.enableDepth();
		GlStateManager.enableCull();

		TurokRenderHelp.release_gl();
	}

	public void onUpdate() {
		array_module.stream().filter(module -> module.is_active()).forEach(module -> module.onUpdate());
	}

	public void onRender() {
		array_module.stream().filter(module -> module.is_active()).forEach(module -> module.onRender());
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

	public String get_tag() {
		return this.tag;
	}
}