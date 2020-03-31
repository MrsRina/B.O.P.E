package rina.turok.bope.bopemod;

import rina.turok.bope.framework.BopeWrapper;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

//
// Rina.
// Coded by Rina.
// 31/03/2020.
//
public class BopeModule {
	public String name;
	public String descritpion;
	public String category;

	public boolean active;

	public KeyBinding module;

	public int bind = - 1;

	public final BopeWrapper mc = BopeWrapper.get_minecraft();

	public BopeModule(String name) {
		module = new KeyBinding(name, key, "");

		ClientRegistry.registerKeyBinding(module);
	}

	// While actived.
	public void while_actived() {}

	// Actived.
	public void actived() {}

	// Disabled.
	public void disabled() {}

	// Set active.
	public void set_active(boolean value) {
		this.active = value;
	}

	// Enable hack.
	public void toggle() {
		set_active(!is_active());

		if (is_active()) {
			actived();
		} else {
			disabled();
		}
	}

	// Disable.
	public void disable() {
		this.active = false;
	}

	// Enable.
	public void enable() {
		this.active = true;
	}

	// Is active.
	public boolean is_active() {
		return this.active;
	}

	public String get_name() {
		return this.name;
	}

	public String get_description() {
		return this.descritpion;
	}
}