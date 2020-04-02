package rina.turok.bope.bopemod;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.framework.TurokBoolean;
import rina.turok.bope.framework.TurokString;
import rina.turok.bope.framework.TurokEnum;
import rina.turok.bope.framework.TurokInt;
import rina.turok.bope.Bope;

//
// Rina.
// Coded by Rina.
// 31/03/2020.
//
public class BopeModule {
	public TurokString name        = new TurokString(get_info().name());
	public TurokString description = new TurokString(get_info().description());

	public BopeCategory.Category category = get_info().category();

	public TurokBoolean state_module = new TurokBoolean(false);

	public boolean state_optional = get_info().state_active();

	public TurokInt bind = new TurokInt(-1);

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeModule() {
		state_optional = get_info().state_active();;
	}

	private NewModule get_info() {
		if (getClass().isAnnotationPresent(NewModule.class)) {
			return getClass().getAnnotation(NewModule.class);
		}

		throw new IllegalStateException("You need create @NewModule(...) before start module. Error in class -> " + this.getClass().getCanonicalName() + "");
	}

	public void subscribe_event() {
		if (state_module.get_value()) {
			if (Keyboard.isKeyDown(bind.get_value())) {
				toggle();
			}

			Bope.EVENT_BUS.subscribe(this);
		} else {
			Bope.EVENT_BUS.unsubscribe(this);
		}
	}

	public void while_world_render(RenderEvent event) {} // Render event into module.

	public void while_actived() {} // While module.

	public void while_render() {} // While render.

	public void disabled() {} // Disable effect.

	public void actived() {} // While actived.

	public void set_active(boolean value) {
		state_module.set_value(value);
	}

	public void toggle() {
		set_active(!is_active());

		if (is_active()) {
			actived();
		} else {
			disabled();
		}
	}

	public void disable() {
		state_module.set_value(false);
	}

	public void enable() {
		state_module.set_value(true);
	}

	public boolean is_active() {
		return state_module.get_value();
	}

	public String get_name() {
		return name.get_value();
	}

	public String get_description() {
		return description.get_value();
	}

	public void set_bind(int key) {
		bind.set_value(key);
	}

	public int get_bind() {
		return bind.get_value();
	}

	public BopeCategory.Category get_category() {
		return category;
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface NewModule {
		String name();
		String description();

		BopeCategory.Category category();

		boolean state_active() default false;
	}
}