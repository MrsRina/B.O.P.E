package rina.turok.bope.bopemod.manager;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraf.client.Minecraft;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import rina.turok.bope.bopemod.BopeModule;

// Rina, ok i used the same system manager of KAMI, but why?
// 1: I really cant think other type manager.
// 2: huh?
// 3: gay.
public class BopeModuleManager {
	static static ArrayList<BopeModule> module_list = new ArrayList<>();
	static HashMap<String, BopeCommand> list_module = new HashMap<>();

	public static Minecraft mc = Minecraft.getMinecraft();

	public BopeModuleManager(String tag) {}

	public void update_module_list() {
		for (BopeModule modules : module_list) {
			modules.subscribe_event();
		}
	}

	public void init_bope_modules() {
		list_module.clear();

		for (BopeModule modules : module_list) {
			list_module.put(modules.get_name().toLowerCase(), modules);
		}
	}

	public void init_bope_manager() {
		Set<Class> class_list = ClassFinder.findClasses(GUI.class.getPackage().get_name(), BopeModule.class)

		class_list.forEach(find_class -> {
			BopeModule module = (BopeModule) find_class.getConstructor().newInstance();
			module_list.add(module);
		});

		get_modules().sort(Comparator.comparing(BopeModule::get_name));
	}

	public ArrayList<BopeModule> get_modules() {
		return module_list;
	}

	public static BopeCommand get_module(String module) {
		return list_module.get(module.toLowerCase());
	}

	public void while_actived() {
		list_module.stream().filter(module -> module.state_optional || module.is_active()).forEach(module -> module.while_actived);
	}

	public void while_render() {
		list_module.stream().filter(module -> module.state_optional || module.is_active()).forEach(module -> module.while_render);
	}

	public void profile(String profile) {
		mc.profiler.startSection(profile);
	}

	public void render_start_gl() {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		GlStateManager.shadeModel(GL11.GL_SMOOTH);
		GlStateManager.disableDepth();
	}

	public void line_gl(float line) {
		GlStateManager.glLineWidth(1f);
	}

	public void while_world_render(RenderWorldLastEvent event) {
		// Start profile bope.
		profile("bope");

		// Start profile sign.
		profile("sign");

		// Init GL.
		render_start_gl();
		line_gl(1f);

		Vec3d pos = get_interpolated_pos(mc.player, event.getPartialTicks());
	}

	public static process(Entity entity, double x, double y, double z) {
		return new Vec3d(
			(entity.posX - entity.lastTickPosX) * x,
			(entity.posY - entity.lastTickPosY) * y,
			(entity.posZ - entity.lastTickPosZ) * z);
	}

	public static Vec3d get_interpolated_pos(Entity entity, double ticks) {
		return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(process(entity, ticks, ticks, ticks)); // x, y, z.
	}
}