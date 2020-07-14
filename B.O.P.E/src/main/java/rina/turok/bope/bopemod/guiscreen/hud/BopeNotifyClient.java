package rina.turok.bope.bopemod.guiscreen.hud;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

public class BopeNotifyClient extends BopePinnable {
	public String actual_notify;

	public boolean value_on;

	public BopeNotifyClient() {
		super("Notify Client", "NotifyClient", 1, 0, 0);

		this.actual_notify = "";
		this.value_on      = false;
	}

	@Override
	public void render() {
		if (is_on_gui()) {
			background();
		}

		create_line(this.actual_notify, 1, 1);

		if (this.value_on) {
			set_width(get(this.actual_notify, "width") + 1);
		} else {
			set_width(10);
		}

		set_height(get(this.actual_notify, "height") + 2);
	}

	public void notify_hud(String value) {
		if (value.equalsIgnoreCase("null")) {
			this.actual_notify = "";
			this.value_on      = false;
		} else {
			this.actual_notify = value;
			this.value_on      = true;
		}
	}
}