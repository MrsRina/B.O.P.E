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
	public BopeCategory category;

	public String name;
	public String tag;
	public String description;

	public int bind;

	public boolean state_module;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeModule(BopeCategory category) {
		this.name        = "";
		this.tag         = "";
		this.description = "";
		this.bind        = -1;

		// Category.
		this.category = category;
	}

	public void release(String tag) {}

	public void set_bind(int key) {
		this.bind = (key);
	}

	public void set_bind(String key) {
		this.bind = Keyboard.getKeyIndex(key.toLowerCase());
	}

	public boolean is_active() {
		return this.state_module;
	}

	public String get_name() {
		return this.name;
	}

	public String get_tag() {
		return this.tag;
	}

	public String get_description() {
		return this.description;
	}

	public int get_bind(int type) {
		return this.bind;
	}

	public String get_bind(String type) {
		String converted_bind = "null";

		if (get_bind(0) < 0) {
			converted_bind = "NONE";
		}

		if (!(converted_bind.equals("NONE"))) {
			String key     = Keyboard.getKeyName(get_bind(0));
			converted_bind = Character.toUpperCase(key.charAt(0)) + (key.length() != 1 ? key.substring(1).toLowerCase() : "");
		}

		return converted_bind;
	}

	public BopeCategory get_category() {
		return this.category;
	}

	public void set_disable() {
		this.state_module = false;

		disable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.unsubscribe(this);
	}

	public void set_enable() {
		this.state_module = true;

		enable();

		BopeEventBus.ZERO_ALPINE_EVENT_BUS.subscribe(this);
	}

	public void set_active(boolean value) {
		if (this.state_module != value) {
			if (value) {
				set_enable();
			} else {
				set_disable();
			}
		}
	}

	public void toggle() {
		set_active(!is_active());
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

	public void render(BopeEventRender event) {
		// For draw 3D.
	}

	public void render() {
		// For draw 2D.
	}

	public void update() {
		// Update main tick to modules when enabled.
	}

	protected void disable() {
		// Disable, like the modules have disabled.
	}

	protected void enable() {
		// If module enbled, before tick it will run one time only, like onDisable()
	}
}