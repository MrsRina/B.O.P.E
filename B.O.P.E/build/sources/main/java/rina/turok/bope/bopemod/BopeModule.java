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
	public String detail_option;

	public int bind;

	public boolean state_module;
	public boolean toggle_message;
	public boolean widget_usage;

	public final Minecraft mc = Minecraft.getMinecraft();

	public BopeModule(BopeCategory category) {
		this.name           = "";
		this.tag            = "";
		this.description    = "";
		this.bind           = -1;
		this.toggle_message = true;
		this.widget_usage   = false;

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

	public void set_if_can_send_message_toggle(boolean value) {
		this.toggle_message = value;
	}

	public void set_usage_widget(boolean value) {
		this.widget_usage = value;
	}

	public boolean is_active() {
		return this.state_module;
	}

	public boolean using_widget() {
		return this.widget_usage;
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
		} else {
			converted_bind = "None";
		}

		return converted_bind;
	}

	public BopeCategory get_category() {
		return this.category;
	}

	public boolean can_send_message_when_toggle() {
		return this.toggle_message;
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

		if (!(this.tag.equals("GUI") || this.tag.equals("HUD")) && this.toggle_message) {
			BopeMessage.toggle_message(this);
		}
	}

	public void toggle() {
		set_active(!is_active());
	}

	// To create intenger slider.
	protected BopeSetting create(String name, String tag, int value, int min, int max) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value, min, max));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	// To create double slider.
	protected BopeSetting create(String name, String tag, double value, double min, double max) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value, min, max));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	// To create button.
	protected BopeSetting create(String name, String tag, boolean value) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	// To create label.
	protected BopeSetting create(String name, String tag, String value) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	// To create the combobox.
	protected BopeSetting create(String name, String tag, String value, List<String> values) {
		Bope.get_setting_manager().register(tag, new BopeSetting(this, name, tag, values, value));

		return Bope.get_setting_manager().get_setting_with_tag(this, tag);
	}

	// Comobobox values.
	protected List<String> combobox(String... item) {
		ArrayList<String> item_requested = new ArrayList<>();

		for (String items : item) {
			item_requested.add(items);
		}

		return item_requested;
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

	public void event_widget() {
		// If use some widget.
	}

	protected void disable() {
		// Disable, like the modules have disabled.
	}

	protected void enable() {
		// If module enbled, before tick it will run one time only, like onDisable()
	}

	// If have a detail option.
	public String detail_option() {
		return null;
	}

	// Event to state type boolean.
	public String string_state() {
		return null;
	}

	// Event to state type boolean.
	public boolean boolean_state() {
		return false;
	}

	// Event to state type double.
	public double double_state() {
		return 0.0;
	}

	// Event to state type int.
	public int int_state() {
		return 0;
	}
}