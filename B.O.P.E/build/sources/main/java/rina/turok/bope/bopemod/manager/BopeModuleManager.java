package rina.turok.bope.bopemod.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import org.reflections.Reflections;
import org.lwjgl.opengl.GL11;

import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.hacks.BopeFinderModule;
import rina.turok.bope.framework.TurokRenderHelp;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.bopemod.BopeModule;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleManager {
	/**
	* @author Rina.
	* It manager was referenced like KAMI.
	* Thanks 086!
	*
	*/

	public static ArrayList<BopeModule> module_list = new ArrayList<BopeModule>();
	public static ArrayList<BopeSaveModule>    save_module = new ArrayList<BopeSaveModule>();

	static HashMap<String, BopeModule>     list_module = new HashMap<>();
	static HashMap<String, BopeSaveModule> list_save_m = new HashMap<>();

	public static Minecraft mc = Minecraft.getMinecraft();

	public BopeModuleManager(String tag) {}

	public void init_bope_modules() {
		list_module.clear();

		for (BopeModule modules : module_list) {
			list_module.put(modules.get_name_tag().toLowerCase(), modules);
		}
	}

	public void update_save_modules() {
		list_save_m.clear();

		for (BopeSaveModule modules_save : save_module) {
			list_save_m.put(modules_save.get_tag(), modules_save);
		}
	}

	public static Set<Class> find_class(String pack, Class subType) {
		Reflections reflections = new Reflections(pack);

		return reflections.getSubTypesOf(subType);
	}

	public void init_bope_manager() {
		Set<Class> class_list = find_class(BopeFinderModule.class.getPackage().getName(), BopeModule.class);

		class_list.forEach(found_class -> {
			try {
				module_list.add((BopeModule) found_class.getConstructor().newInstance());
			} catch (InvocationTargetException exc) {
				exc.getCause().printStackTrace();
			} catch (Exception exc) {
				exc.getCause().printStackTrace();
			}
		});

		get_modules().sort(Comparator.comparing(BopeModule::get_name_tag));
	}

	public ArrayList<BopeModule> get_modules() {
		return module_list;
	}

	public static ArrayList<BopeSaveModule> get_save_modules() {
		return save_module;
	}

	public void register_module(BopeSaveModule save) {
		save_module.add(save);

		update_save_modules();
	}

	public static BopeModule get_module(String module) {
		return list_module.get(module.toLowerCase());
	}

	public static BopeSaveModule get_save_module(String save_module) {
		return list_save_m.get(save_module.toLowerCase());
	}

	public void onBind(int event_key) {
		if (event_key == 0) {
			return;
		}

		module_list.forEach(module -> {
			if (module.get_bind().pressed(event_key)) {
				module.toggle();
			}
		});
	}

	public void onUpdate() {
		module_list.stream().filter(module -> module.is_active()).forEach(module -> module.onUpdate());
	}

	public void onRender() {
		module_list.stream().filter(module -> module.is_active()).forEach(module -> module.onRender());
	}

	public void render_start_gl() {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.disableDepth();
	}

	public void release_gl() {
		GlStateManager.shadeModel(GL11.GL_FLAT);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.enableDepth();
		GlStateManager.enableCull();
	}

	public void line_gl(float line) {
		GlStateManager.glLineWidth(1f);
	}

	public void onWorldRender(RenderWorldLastEvent event) {
		// Init GL.
		render_start_gl();
		line_gl(1f);

		Vec3d pos = get_interpolated_pos(mc.player, event.getPartialTicks());

		BopeEventRender event_render = new BopeEventRender(TurokRenderHelp.INSTANCE, pos);

		event_render.reset_translation();

		module_list.stream().filter(module -> module.is_active()).forEach(module -> {
			module.onWorldRender(event_render);
		});

		line_gl(1f);

		release_gl();

		TurokRenderHelp.release_gl();
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
}