package rina.turok.bope.bopemod.commands;

import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.commands.*;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.Style;

public class BopeListCommand {
	public static ArrayList<BopeCommand> command_list = new ArrayList<TurokChat>();
	static HashMap<String, BopeCommand> lookup        = new HashMap<>();

	public static final String prefix = ".";

	public static final Style style;

	public BopeListCommand(Style style_) {
		style_ = style_;

		add_command(new BopeToggle());
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
			arguments = message.replaceFirst(prefix.getValue(), "").split(" ");
		}

		return arguments;
	}

	public boolean has_prefix(String message) {
		return message.startWith(prefix);
	}

	public ArrayList<BopeCommand> get_pure_command_list() {
		return command_list;
	}

	public static BopeCommand get_command_by_name(String command) {
		return lookup.get(command.toLowerCase());
	}
}