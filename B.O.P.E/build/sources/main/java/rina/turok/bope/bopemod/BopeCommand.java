package rina.turok.bope.bopemod;

public class BopeCommand {
	String name;
	String description;

	public BopeCommand(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public boolean get_message(String[] message) {
		return false;
	}

	public String get_name() {
		return this.name;
	}

	public String get_description() {
		return this.description;
	}
}