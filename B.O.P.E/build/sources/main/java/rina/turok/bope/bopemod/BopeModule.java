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

import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.framework.TurokBoolean;
import rina.turok.bope.framework.TurokString;
import rina.turok.bope.framework.TurokBind;
import rina.turok.bope.framework.TurokEnum;
import rina.turok.bope.framework.TurokInt;
import rina.turok.bope.Bope;

// External:
import rina.turok.bope.external.BopeEventBus;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModule {
	public TurokString name;
	public TurokString name_tag;
	public TurokString description;

	public BopeCategory.Category category;

	public TurokBoolean state_module;

	public TurokBind bind;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeModule(BopeCategory.Category category_module) {
		category = category_module;
	}

	public void set_module_info(String name_module, String tag_module, String description_module) {
		name         = new TurokString  (name_module, tag_module, name_module);
		name_tag     = new TurokString  (name_module, tag_module, tag_module);
		description  = new TurokString  (name_module, tag_module, description_module);
		state_module = new TurokBoolean (name_module, tag_module, false);
		bind         = new TurokBind    (name_module, tag_module, -1);
	}

	public void onWorldRender(BopeEventRender event) {} // Render event into module.

	public void onUpdate() {} // While module.

	public void onRender() {} // While render.

	protected void onDisable() {} // Disable effect.

	protected void onEnable() {} // While actived.

	public void set_active(boolean value) {
		boolean is = state_module.get_value();

		if (is != value) {
			if (value) {
				enable();
			} else {
				disable();
			}
		}
	}

	public void toggle() {
		set_active(!is_active());
	}

	public void disable() {
		state_module.set_value(false);

		onDisable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.unsubscribe(this);
	}

	public void enable() {
		state_module.set_value(true);

		onEnable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.subscribe(this);
	}

	public boolean is_active() {
		return state_module.get_value();
	}

	public String get_name() {
		return name.get_value();
	}

	public String get_name_tag() {
		return name_tag.get_value();
	}

	public String get_description() {
		return description.get_value();
	}

	public void set_bind(int key) {
		bind.set_key(key);
	}

	public TurokBind get_bind() {
		return bind;
	}

	public int get_key_bind() {
		return bind.get_key();
	}

	public BopeCategory.Category get_category() {
		return category;
	}
}