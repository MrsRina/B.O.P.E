package rina.turok.bope.bopemod.commands;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.Style;

import java.util.ArrayList;
import java.util.HashMap;

import rina.turok.bope.framework.TurokString;
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.commands.*;

// Rina.
public class BopeListCommand {
	public static ArrayList<BopeCommand> command_list = new ArrayList<BopeCommand>();
	static HashMap<String, BopeCommand> list_command  = new HashMap<>();

	public static final TurokString prefix = new TurokString("Prefix", "Prefix", ".");

	public final Style style;

	public BopeListCommand(Style style_) {
		style = style_;

		add_command(new BopePrefix());
		add_command(new BopeSettings());
		add_command(new BopeToggle());
	}

	public static void init_bope_command_lookup() {
		list_command.clear();

		for (BopeCommand commands : command_list) {
			list_command.put(commands.get_name().toLowerCase(), commands);
		}
	}

	public static void add_command(BopeCommand command) {
		command_list.add(command);
	}

	public String[] get_message(String message) {
		String[] arguments = {};

		if (has_prefix(message)) {
			arguments = message.replaceFirst(prefix.get_value(), "").split(" ");
		}

		return arguments;
	}

	public boolean has_prefix(String message) {
		return message.startsWith(prefix.get_value());
	}

	public void set_prefix(String new_prefix) {
		prefix.set_value(new_prefix);
	}

	public String get_prefix() {
		return prefix.get_value();
	}

	public ArrayList<BopeCommand> get_pure_command_list() {
		return command_list;
	}

	public static BopeCommand get_command_by_name(String command) {
		return list_command.get(command.toLowerCase());
	}
}