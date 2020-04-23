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

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Framework.
import rina.turok.bope.framework.TurokBoolean;
import rina.turok.bope.framework.TurokString;
import rina.turok.bope.framework.TurokEnum;
import rina.turok.bope.framework.TurokBind;
import rina.turok.bope.framework.TurokInt;

// External:
import rina.turok.bope.external.BopeEventBus;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModule {
	public String name;
	public String name_tag;
	public String description;

	public BopeCategory category;

	public boolean state_module;

	public TurokBind bind;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeModule(String name, BopeCategory category_module) {
		name     = name;
		bind     = new TurokBind(name, "tag", -1);
		category = category_module;
	}

	public void module_info(String tag, String description) {
		name_tag     = tag;
		description  = description;
		state_module = false;
	}

	public void onWorldRender(BopeEventRender event) {} // Render event into module.

	public void onUpdate() {} // While module.

	public void onRender() {} // While render.

	protected void onDisable() {} // Disable effect.

	protected void onEnable() {} // While actived.

	public void set_active(boolean value) {
		boolean is = state_module;

		if (is != value) {
			if (value) {
				enable();
			} else {
				disable();
			}
		}
	}

	public void set_int_bind(int key) {
		bind.set_key(key);
	}

	public void set_string_bind(String key) {
		int new_bind = Keyboard.getKeyIndex(key.toLowerCase());

		bind.set_key(new_bind);
	}

	public void toggle() {
		set_active(!is_active());
	}

	public void disable() {
		state_module = false;

		onDisable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.unsubscribe(this);
	}

	public void enable() {
		state_module = true;

		onEnable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.subscribe(this);
	}

	public boolean is_active() {
		return state_module;
	}

	public String get_name() {
		return name;
	}

	public String get_tag() {
		return name_tag;
	}

	public String get_description() {
		return description;
	}

	public TurokBind get_bind() {
		return bind;
	}

	public int get_int_bind() {
		return bind.get_key();
	}

	public String get_string_bind() {
		if (get_int_bind() < 0) {
			return ("null");
		} else {
			String key = Keyboard.getKeyName(get_int_bind());

			return (Character.toUpperCase(key.charAt(0)) + (key.length() != 1 ? key.substring(1).toLowerCase() : ""));
		}
	}

	public BopeCategory get_category() {
		return category;
	}

	protected BopeSetting create(String name, String tag, int value, int min, int max) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value, min, max));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	protected BopeSetting create(String name, String tag, double value, double min, double max) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value, min, max));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	protected BopeSetting create(String name, String tag, boolean value) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	protected BopeSetting create(String name, String tag, String value) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	protected BopeSetting create(String name, String tag, List<String> values, String value) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, values, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}
}