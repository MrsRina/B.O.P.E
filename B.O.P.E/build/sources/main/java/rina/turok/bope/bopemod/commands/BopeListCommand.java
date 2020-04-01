package rina.turok.bope.bopemod.commands;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.Style;

import java.util.ArrayList;
import java.util.HashMap;

import rina.turok.bope.framework.TurokString;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.commands.*;

public class BopeListCommand {
	public static ArrayList<BopeCommand> command_list = new ArrayList<BopeCommand>();
	static HashMap<String, BopeCommand> lookup        = new HashMap<>();

	public static final TurokString prefix = new TurokString(".");

	public final Style style;

	public BopeListCommand(Style style_) {
		style = style_;

		add_command(new BopePrefix());
	}

	public static void init_bope_command_lookup() {
		lookup.clear();

		for (BopeCommand command : command_list) {
			lookup.put(command.get_name().toLowerCase(), command);
		}
	}

	public static void add_command(BopeCommand command) {
		command_list.add(command);
	}

	public String[] get_message(String message) {
		String[] arguments = {};

		if (has_prefix(message)) {
			arguments = message.replaceFirst(prefix.get_string(), "").split(" ");
		}

		return arguments;
	}

	public boolean has_prefix(String message) {
		return message.startsWith(prefix.get_string());
	}

	public void set_prefix(String new_prefix) {
		prefix.set_string(new_prefix);
	}

	public String get_prefix() {
		return prefix.get_string();
	}

	public ArrayList<BopeCommand> get_pure_command_list() {
		return command_list;
	}

	public static BopeCommand get_command_by_name(String command) {
		return lookup.get(command.toLowerCase());
	}
}