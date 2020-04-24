package rina.turok.bope.bopemod.hacks.chat;

import org.lwjgl.input.Keyboard;

import java.util.*;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeModuleTest extends BopeModule {
	BopeSetting button_1   = create("Button 1", "button1", true);
	BopeSetting button_2   = create("Button 2", "button2", true);
	BopeSetting label_1    = create("Label 1", "label1", "ok");
	BopeSetting combobox_1 = create("Types Combobox 1", "combobox1", Arrays.asList("1", "2", "3"), "3");
	BopeSetting double_1   = create("Double 1", "double1", 1.0, 0.5, 2.0);
	BopeSetting integer_1  = create("Integer 1", "integer1", 7, 5, 10);

	public BopeModuleTest() {
		super(BopeCategory.BOPE_CHAT);

		this.name        = "Module Test";
		this.tag         = "moduletest";
		this.description = "This module is for test.";

		release("B.O.P.E - module - B.O.P.E");
	}

	@Override
	public void update() {
		if (button_1.get_value(true)) {
			BopeMessage.send_client_message("The string value is " + label_1.get_value("") + " and button 2 is " + Boolean.toString(button_2.get_value(true)));
		}
	}
}