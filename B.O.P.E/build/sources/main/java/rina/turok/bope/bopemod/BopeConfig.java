package rina.turok.bope.bopemod;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

// Json manager.
import com.google.gson.*;

// Managers.
import rina.turok.bope.bopemod.manager.BopeSettingManager;
import rina.turok.bope.bopemod.manager.BopeModuleManager;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Framework.
import rina.turok.bope.framework.TurokString;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina.
 *
 * Created by Rina.
 * 08/04/2020.
 *
 */
public class BopeConfig {
	public static String BOPE_FOLDER_CONFIG = "B.O.P.E/";
	public static String BOPE_FILE_CONFIGS  = "Configs.json";
	public static String BOPE_FILE_BINDS    = "Binds.json";

	public static String BOPE_ABS_CONFIGS = (BOPE_FOLDER_CONFIG + BOPE_FILE_CONFIGS);
	public static String BOPE_ABS_FOLDER  = (BOPE_FOLDER_CONFIG);
	public static String BOPE_ABS_BINDS   = (BOPE_FOLDER_CONFIG + BOPE_FILE_BINDS);

	public static Path PATH_CONFIGS = Paths.get(BOPE_ABS_CONFIGS);
	public static Path PATH_FOLDER  = Paths.get(BOPE_ABS_FOLDER);
	public static Path PATH_BINDS   = Paths.get(BOPE_ABS_BINDS);

	public static void BOPE_VERIFY_FOLDER_CONFIGS() throws IOException {
		if (!Files.exists(PATH_FOLDER)) {
			Files.createDirectories(PATH_FOLDER);
		}
	}

	public static void BOPE_VERIFY_CONFIG_FILES() throws IOException {
		if (!Files.exists(PATH_CONFIGS)) {
			Files.createFile(PATH_CONFIGS);
		}
	}

	public static void BOPE_VERIFY_FILES_BINDS() throws IOException {
		if (!Files.exists(PATH_BINDS)) {
			Files.createFile(PATH_BINDS);
		}
	}

	public static void BOPE_DELETE_CONFIGS() throws IOException {
		File file = new File(BOPE_ABS_CONFIGS);

		file.delete();
	}

	public static void BOPE_DELETE_BINDS() throws IOException {
		File file = new File(BOPE_ABS_BINDS);

		file.delete();
	}

	public static JsonObject BOPE_CONFIG_OBJECT(JsonParser parse) {
		JsonObject BOPE_MAIN_OBJECT    = new JsonObject();
		JsonObject BOPE_MAIN_BUTTONS   = new JsonObject();
		JsonObject BOPE_MAIN_COMBOBOXS = new JsonObject();
		JsonObject BOPE_MAIN_LABELS    = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_D = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_I = new JsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			boolean button = false;

			JsonObject BOPE_BUTTON_SETTING = new JsonObject();

			if (is(buttons, "label")          ||
				is(buttons, "combobox")       ||
				is(buttons, "doubleslider")   ||
				is(buttons, "integerslider")) {
				continue;
			}

			BOPE_BUTTON_SETTING.add("master", new JsonPrimitive(buttons.get_master().get_tag()));
			BOPE_BUTTON_SETTING.add("name", new JsonPrimitive(buttons.get_name()));
			BOPE_BUTTON_SETTING.add("tag", new JsonPrimitive(buttons.get_tag()));
			BOPE_BUTTON_SETTING.add("value", new JsonPrimitive(buttons.get_value(button)));
			BOPE_BUTTON_SETTING.add("type", new JsonPrimitive(buttons.get_type()));

			BOPE_MAIN_BUTTONS.add(buttons.get_tag(), BOPE_BUTTON_SETTING);

			BOPE_MAIN_OBJECT.add("buttons", BOPE_MAIN_BUTTONS);
		}

		for (BopeSetting comboboxs : Bope.get_setting_manager().get_array_settings()) {
			JsonObject BOPE_COMBOBOX_SETTING = new JsonObject();

			if (is(comboboxs, "button")         ||
				is(comboboxs, "label")          ||
				is(comboboxs, "doubleslider")   ||
				is(comboboxs, "integerslider")) {
				continue;
			}

			JsonElement BOPE_COMBOBOX_SETTING_VALUES = parse.parse(new Gson().toJson(comboboxs.get_values()));

			BOPE_COMBOBOX_SETTING.add("master", new JsonPrimitive(comboboxs.get_master().get_tag()));
			BOPE_COMBOBOX_SETTING.add("name", new JsonPrimitive(comboboxs.get_name()));
			BOPE_COMBOBOX_SETTING.add("tag", new JsonPrimitive(comboboxs.get_tag()));
			BOPE_COMBOBOX_SETTING.add("value", new JsonPrimitive(comboboxs.get_current_value()));
			BOPE_COMBOBOX_SETTING.add("values", BOPE_COMBOBOX_SETTING_VALUES);
			BOPE_COMBOBOX_SETTING.add("type", new JsonPrimitive(comboboxs.get_type()));

			BOPE_MAIN_COMBOBOXS.add(comboboxs.get_tag(), BOPE_COMBOBOX_SETTING);

			BOPE_MAIN_OBJECT.add("comboboxs", BOPE_MAIN_COMBOBOXS);
		}

		for (BopeSetting labels : Bope.get_setting_manager().get_array_settings()) {
			String label = "ue";

			JsonObject BOPE_LABELS_SETTING = new JsonObject();

			if (is(labels, "button")         ||
				is(labels, "combobox")       ||
				is(labels, "doubleslider")   ||
				is(labels, "integerslider")) {
				continue;
			}

			BOPE_LABELS_SETTING.add("master", new JsonPrimitive(labels.get_master().get_tag()));
			BOPE_LABELS_SETTING.add("name", new JsonPrimitive(labels.get_name()));
			BOPE_LABELS_SETTING.add("tag", new JsonPrimitive(labels.get_tag()));
			BOPE_LABELS_SETTING.add("value", new JsonPrimitive(labels.get_value(label)));
			BOPE_LABELS_SETTING.add("type", new JsonPrimitive(labels.get_type()));

			BOPE_MAIN_LABELS.add(labels.get_tag(), BOPE_LABELS_SETTING);

			BOPE_MAIN_OBJECT.add("labels", BOPE_MAIN_LABELS);
		}

		for (BopeSetting slider_doubles : Bope.get_setting_manager().get_array_settings()) {
			double double_ = 1.2;

			JsonObject BOPE_SLIDER_DOUBLES_SETTING = new JsonObject();

			BOPE_SLIDER_DOUBLES_SETTING.add("master", new JsonPrimitive(slider_doubles.get_master().get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("name", new JsonPrimitive(slider_doubles.get_name()));
			BOPE_SLIDER_DOUBLES_SETTING.add("tag", new JsonPrimitive(slider_doubles.get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("value", new JsonPrimitive(slider_doubles.get_value(double_)));
			BOPE_SLIDER_DOUBLES_SETTING.add("min", new JsonPrimitive(slider_doubles.get_min(double_)));
			BOPE_SLIDER_DOUBLES_SETTING.add("max", new JsonPrimitive(slider_doubles.get_max(double_)));
			BOPE_SLIDER_DOUBLES_SETTING.add("type", new JsonPrimitive(slider_doubles.get_type()));

			BOPE_MAIN_SLIDERS_D.add(slider_doubles.get_tag(), BOPE_SLIDER_DOUBLES_SETTING);

			BOPE_MAIN_OBJECT.add("slidersDoubles", BOPE_MAIN_SLIDERS_D);
		}

		for (BopeSetting slider_integers : Bope.get_setting_manager().get_array_settings()) {
			double integer = 1;

			JsonObject BOPE_SLIDER_INTEGERS_SETTING  = new JsonObject();

			if (is(slider_integers, "button")         ||
				is(slider_integers, "combobox")       ||
				is(slider_integers, "label")          ||
				is(slider_integers, "doubleslider")) {
				continue;
			}

			BOPE_SLIDER_INTEGERS_SETTING.add("master", new JsonPrimitive(slider_integers.get_master().get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("name", new JsonPrimitive(slider_integers.get_name()));
			BOPE_SLIDER_INTEGERS_SETTING.add("tag", new JsonPrimitive(slider_integers.get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("value", new JsonPrimitive(slider_integers.get_value(integer)));
			BOPE_SLIDER_INTEGERS_SETTING.add("min", new JsonPrimitive(slider_integers.get_min(integer)));
			BOPE_SLIDER_INTEGERS_SETTING.add("max", new JsonPrimitive(slider_integers.get_max(integer)));
			BOPE_SLIDER_INTEGERS_SETTING.add("type", new JsonPrimitive(slider_integers.get_type()));

			BOPE_MAIN_SLIDERS_I.add(slider_integers.get_tag(), BOPE_SLIDER_INTEGERS_SETTING);

			BOPE_MAIN_OBJECT.add("slidersIntegers", BOPE_MAIN_SLIDERS_I);
		}

		return BOPE_MAIN_OBJECT;
	}

	public static void BOPE_SAVE_CONFIGS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser(); 

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		BOPE_MAIN_JSON.add("settings", BOPE_CONFIG_OBJECT(BOPE_PARSER));

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_CONFIGS();
		BOPE_VERIFY_CONFIG_FILES();

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_CONFIGS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public static void BOPE_LOAD_CONFIGS() throws IOException {
		InputStream BOPE_JSON_FILE    = Files.newInputStream(PATH_CONFIGS);
		JsonObject  BOPE_JSON         = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_JSON    = BOPE_JSON.get("settings").getAsJsonObject();
		JsonObject  BOPE_MAIN_BUTTONS = BOPE_MAIN_JSON.get("buttons").getAsJsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			if (is(buttons, "label")          ||
				is(buttons, "combobox")       ||
				is(buttons, "doubleslider")   ||
				is(buttons, "integerslider")) {
				continue;
			}

			JsonObject BOPE_BUTTONS_INFO = BOPE_MAIN_BUTTONS.get(buttons.get_tag()).getAsJsonObject();

			Bope.get_setting_manager().get_setting_with_tag(BOPE_BUTTONS_INFO.get("master").getAsString(), BOPE_BUTTONS_INFO.get("tag").getAsString()).set_value(BOPE_BUTTONS_INFO.get("value").getAsBoolean());
		}

		BOPE_JSON_FILE.close();
	}

	public static void BOPE_SAVE_BINDS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON   = new JsonObject();
		JsonObject BOPE_MODULE_JSON = new JsonObject();

		for (BopeModule module : Bope.get_module_manager().get_array_modules()) {
			JsonObject BOPE_MODULE_INFO = new JsonObject();

			BOPE_MODULE_INFO.add("int",    new JsonPrimitive(module.get_int_bind()));
			BOPE_MODULE_INFO.add("string", new JsonPrimitive(module.get_string_bind()));
			BOPE_MODULE_INFO.add("state",  new JsonPrimitive(module.is_active()));

			BOPE_MODULE_JSON.add(module.get_tag(), BOPE_MODULE_INFO);
		}

		BOPE_MAIN_JSON.add("modules", BOPE_MODULE_JSON);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_BINDS();
		BOPE_VERIFY_FILES_BINDS();

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_BINDS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public static void save() {
		try {
			BOPE_VERIFY_FOLDER_CONFIGS();

			BOPE_SAVE_CONFIGS();
			BOPE_SAVE_BINDS();

		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public static void load() {
		try {
			BOPE_LOAD_CONFIGS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public static boolean is(BopeSetting setting, String type) {
		return setting.get_type().equalsIgnoreCase(type);
	}
}