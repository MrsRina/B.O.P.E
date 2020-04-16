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

// Data.
import rina.turok.bope.bopemod.BopeSaveModule;

// Framework.
import rina.turok.bope.framework.TurokBoolean;
import rina.turok.bope.framework.TurokString;
import rina.turok.bope.framework.TurokEnum;
import rina.turok.bope.framework.TurokBind;
import rina.turok.bope.framework.TurokInt;

// Core.
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

	public void module_info(String name_module, String tag_module, String description_module, int default_key) {
		name         = new TurokString  (name_module, tag_module, name_module);
		name_tag     = new TurokString  (name_module, tag_module, tag_module);
		description  = new TurokString  (name_module, tag_module, description_module);
		state_module = new TurokBoolean (name_module, tag_module, false);
		bind         = new TurokBind    (name_module, tag_module, default_key);

		Bope.get_module_manager().register_module(new BopeSaveModule (
			this,
			name_module,
			tag_module
		));

		BopeConfig.load_bind(tag_module);

		BopeSaveModule load = Bope.get_module_manager().get_save_module(tag_module);

		set_active(load.get_state());

		if (load.get_int_bind() != default_key) {
			set_bind(load.get_int_bind());
		}
	}

	protected BopeSetting.TypeButton create_button(String name, boolean default_) {
		BopeSetting.TypeButton button_setting = new BopeSetting.TypeButton(name, this, default_);

		Bope.get_setting_manager().register(button_setting);

		return button_setting;
	}

	protected BopeSetting.TypeDouble create_double(String name, double value, double min, double max) {
		BopeSetting.TypeDouble double_setting = new BopeSetting.TypeDouble(name, this, value, min, max);
		
		Bope.get_setting_manager().register(double_setting);

		return double_setting;
	}

	protected BopeSetting.TypeInteger create_integer(String name, int value, int min, int max) {
		BopeSetting.TypeInteger integer_setting = new BopeSetting.TypeInteger(name, this, value, min, max);

		Bope.get_setting_manager().register(integer_setting);

		return integer_setting;
	}

	protected BopeSetting.TypeString create_custom_string(String name, String default_) {
		BopeSetting.TypeString custom_setting = new BopeSetting.TypeString(name, this, default_);

		Bope.get_setting_manager().register(custom_setting);

		return custom_setting;
	}

	protected BopeSetting.TypeCombobox create_combobox(String name, List<String> modes, String default_) {
		BopeSetting.TypeCombobox combobox_setting = new BopeSetting.TypeCombobox(name, this, modes, default_);

		Bope.get_setting_manager().register(combobox_setting);

		return combobox_setting;
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

	public void set_bind(int key) {
		bind.set_key(key);

		Bope.get_module_manager().get_save_module(get_name_tag()).set_int_bind(key);
	}

	public void set_string_bind(String key) {
		int new_bind = Keyboard.getKeyIndex(key);

		bind.set_key(new_bind);

		Bope.get_module_manager().get_save_module(get_name_tag()).set_int_bind(new_bind);
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

	public TurokBind get_bind() {
		return bind;
	}

	public int get_key_bind() {
		return bind.get_key();
	}

	public String get_string_bind() {
		return Bope.get_module_manager().get_save_module(get_name_tag()).get_string_bind();
	}

	public BopeCategory.Category get_category() {
		return category;
	}
}