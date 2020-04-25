package rina.turok.bope.bopemod.manager;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.*;
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
public class BopeConfigManager {
	public String tag;

	public String BOPE_FOLDER_CONFIG = "B.O.P.E/";
	public String BOPE_FOLDER_LOG    = "logs/";
	public String BOPE_FILE_CLIENT   = "Client.json";
	public String BOPE_FILE_BINDS    = "Binds.json";
	public String BOPE_FILE_CONFIGS  = "Configs.json";
	public String BOPE_FILE_HUD      = "HUD.json";
	public String BOPE_FILE_FRIENDS  = "Friends.json";
	public String BOPE_FILE_LOG      = "log";

	public String BOPE_ABS_CONFIGS    = (BOPE_FOLDER_CONFIG + BOPE_FILE_CONFIGS);
	public String BOPE_ABS_FOLDER     = (BOPE_FOLDER_CONFIG);
	public String BOPE_ABS_FOLDER_LOG = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_LOG);
	public String BOPE_ABS_BINDS      = (BOPE_FOLDER_CONFIG + BOPE_FILE_BINDS);
	public String BOPE_ABS_HUD        = (BOPE_FOLDER_CONFIG + BOPE_FILE_HUD);
	public String BOPE_ABS_FRIENDS    = (BOPE_FOLDER_CONFIG + BOPE_FILE_FRIENDS);
	public String BOPE_ABS_CLIENT     = (BOPE_FOLDER_CONFIG + BOPE_FILE_CLIENT);
	public String BOPE_ABS_LOG        = (BOPE_ABS_FOLDER_LOG + BOPE_FILE_LOG);

	public Path PATH_CONFIGS    = Paths.get(BOPE_ABS_CONFIGS);
	public Path PATH_FOLDER     = Paths.get(BOPE_ABS_FOLDER);
	public Path PATH_BINDS      = Paths.get(BOPE_ABS_BINDS);
	public Path PATH_HUD        = Paths.get(BOPE_ABS_HUD);
	public Path PATH_FRIENDS    = Paths.get(BOPE_ABS_FRIENDS);
	public Path PATH_CLIENT     = Paths.get(BOPE_ABS_CLIENT);
	public Path PATH_FOLDER_LOG = Paths.get(BOPE_ABS_FOLDER_LOG);

	public Path PATH_LOG;

	public StringBuilder log;

	public BopeConfigManager(String tag) {
		this.tag  = tag;
		this.log = new StringBuilder();

		Date   hora = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy' - 'HH:mm:ss:").format(hora);

		send_log("****** Files have started. ******");
		send_log("- The author of this log is SrRina.");
		send_log("- Any crash or problem its here.");
		send_log("- (GoT) Rina#8637");
		send_log("****** File information. ******");
		send_log("- Client name: " + Bope.get_name());
		send_log("- Client version: " + Bope.get_version());
		send_log("- File created in: " + data);
		send_log("- ");
		send_log("- >");

	}

	public void BOPE_VERIFY_FOLDER(Path path) throws IOException {
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	public void BOPE_VERIFY_FILES(Path path) throws IOException {
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
	}

	public void BOPE_DELETE_FILES(String abs_path) throws IOException {
		File file = new File(abs_path);

		file.delete();
	}

	public JsonObject BOPE_CONFIG_OBJECT() {
		JsonObject BOPE_MAIN_OBJECT    = new JsonObject();
		JsonObject BOPE_MAIN_BUTTONS   = new JsonObject();
		JsonObject BOPE_MAIN_COMBOBOXS = new JsonObject();
		JsonObject BOPE_MAIN_LABELS    = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_D = new JsonObject();
		JsonObject BOPE_MAIN_SLIDERS_I = new JsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			boolean button = false;

			JsonObject BOPE_MODULE_BUTTON  = new JsonObject();
			JsonObject BOPE_BUTTON_SETTING = new JsonObject();

			if (is(buttons, "label")          ||
				is(buttons, "combobox")       ||
				is(buttons, "doubleslider")   ||
				is(buttons, "integerslider")) {
				continue;
			}

			BOPE_BUTTON_SETTING.add("master", new JsonPrimitive(buttons.get_master().get_tag()));
			BOPE_BUTTON_SETTING.add("name",   new JsonPrimitive(buttons.get_name()));
			BOPE_BUTTON_SETTING.add("tag",    new JsonPrimitive(buttons.get_tag()));
			BOPE_BUTTON_SETTING.add("value",  new JsonPrimitive(buttons.get_value(button)));
			BOPE_BUTTON_SETTING.add("type",   new JsonPrimitive(buttons.get_type()));

			BOPE_MAIN_BUTTONS.add(buttons.get_tag(), BOPE_BUTTON_SETTING);
			BOPE_MODULE_BUTTON.add(buttons.get_master().get_tag(), BOPE_MAIN_BUTTONS);

			BOPE_MAIN_OBJECT.add("buttons", BOPE_MODULE_BUTTON);
		}

		for (BopeSetting comboboxs : Bope.get_setting_manager().get_array_settings()) {
			JsonObject BOPE_MODULE_COMBOBOX  = new JsonObject();
			JsonObject BOPE_COMBOBOX_SETTING = new JsonObject();

			if (is(comboboxs, "button")         ||
				is(comboboxs, "label")          ||
				is(comboboxs, "doubleslider")   ||
				is(comboboxs, "integerslider")) {
				continue;
			}

			BOPE_COMBOBOX_SETTING.add("master", new JsonPrimitive(comboboxs.get_master().get_tag()));
			BOPE_COMBOBOX_SETTING.add("name",   new JsonPrimitive(comboboxs.get_name()));
			BOPE_COMBOBOX_SETTING.add("tag",    new JsonPrimitive(comboboxs.get_tag()));
			BOPE_COMBOBOX_SETTING.add("value",  new JsonPrimitive(comboboxs.get_current_value()));
			BOPE_COMBOBOX_SETTING.add("type",   new JsonPrimitive(comboboxs.get_type()));

			BOPE_MAIN_COMBOBOXS.add(comboboxs.get_tag(), BOPE_COMBOBOX_SETTING);

			BOPE_MODULE_COMBOBOX.add(comboboxs.get_master().get_tag(), BOPE_MAIN_COMBOBOXS);

			BOPE_MAIN_OBJECT.add("comboboxs", BOPE_MODULE_COMBOBOX);
		}

		for (BopeSetting labels : Bope.get_setting_manager().get_array_settings()) {
			String label = "ue";

			JsonObject BOPE_MODULE_LABEL   = new JsonObject();
			JsonObject BOPE_LABELS_SETTING = new JsonObject();

			if (is(labels, "button")         ||
				is(labels, "combobox")       ||
				is(labels, "doubleslider")   ||
				is(labels, "integerslider")) {
				continue;
			}

			BOPE_LABELS_SETTING.add("master", new JsonPrimitive(labels.get_master().get_tag()));
			BOPE_LABELS_SETTING.add("name",   new JsonPrimitive(labels.get_name()));
			BOPE_LABELS_SETTING.add("tag",    new JsonPrimitive(labels.get_tag()));
			BOPE_LABELS_SETTING.add("value",  new JsonPrimitive(labels.get_value(label)));
			BOPE_LABELS_SETTING.add("type",   new JsonPrimitive(labels.get_type()));

			BOPE_MAIN_LABELS.add(labels.get_tag(), BOPE_LABELS_SETTING);
			BOPE_MODULE_LABEL.add(labels.get_master().get_tag(), BOPE_MAIN_LABELS);

			BOPE_MAIN_OBJECT.add("labels", BOPE_MODULE_LABEL);
		}

		for (BopeSetting slider_doubles : Bope.get_setting_manager().get_array_settings()) {
			double double_ = 1.2;

			if (is(slider_doubles, "button")         ||
				is(slider_doubles, "combobox")       ||
				is(slider_doubles, "label")          ||
				is(slider_doubles, "integerslider")) {
				continue;
			}

			JsonObject BOPE_MODULE_LABEL_DOUBLE    = new JsonObject();
			JsonObject BOPE_SLIDER_DOUBLES_SETTING = new JsonObject();			

			BOPE_SLIDER_DOUBLES_SETTING.add("master", new JsonPrimitive(slider_doubles.get_master().get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("name",   new JsonPrimitive(slider_doubles.get_name()));
			BOPE_SLIDER_DOUBLES_SETTING.add("tag",    new JsonPrimitive(slider_doubles.get_tag()));
			BOPE_SLIDER_DOUBLES_SETTING.add("value",  new JsonPrimitive(slider_doubles.get_value(double_)));
			BOPE_SLIDER_DOUBLES_SETTING.add("type",   new JsonPrimitive(slider_doubles.get_type()));

			BOPE_MAIN_SLIDERS_D.add(slider_doubles.get_tag(), BOPE_SLIDER_DOUBLES_SETTING);
			BOPE_MODULE_LABEL_DOUBLE.add(slider_doubles.get_master().get_tag(), BOPE_MAIN_SLIDERS_D);

			BOPE_MAIN_OBJECT.add("slidersD", BOPE_MODULE_LABEL_DOUBLE);
		}

		for (BopeSetting slider_integers : Bope.get_setting_manager().get_array_settings()) {
			double integer = 1;

			JsonObject BOPE_MODULE_SLIDER_INTEGER   = new JsonObject();
			JsonObject BOPE_SLIDER_INTEGERS_SETTING = new JsonObject();

			if (is(slider_integers, "button")         ||
				is(slider_integers, "combobox")       ||
				is(slider_integers, "label")          ||
				is(slider_integers, "doubleslider")) {
				continue;
			}

			BOPE_SLIDER_INTEGERS_SETTING.add("master", new JsonPrimitive(slider_integers.get_master().get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("name",   new JsonPrimitive(slider_integers.get_name()));
			BOPE_SLIDER_INTEGERS_SETTING.add("tag",    new JsonPrimitive(slider_integers.get_tag()));
			BOPE_SLIDER_INTEGERS_SETTING.add("value",  new JsonPrimitive(slider_integers.get_value(integer)));
			BOPE_SLIDER_INTEGERS_SETTING.add("type",   new JsonPrimitive(slider_integers.get_type()));

			BOPE_MAIN_SLIDERS_I.add(slider_integers.get_tag(), BOPE_SLIDER_INTEGERS_SETTING);
			BOPE_MODULE_SLIDER_INTEGER.add(slider_integers.get_master().get_tag(), BOPE_MAIN_SLIDERS_I);

			BOPE_MAIN_OBJECT.add("slidersI", BOPE_MODULE_SLIDER_INTEGER);
		}

		return BOPE_MAIN_OBJECT;
	}

	public void BOPE_SAVE_CONFIGS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser(); 

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		BOPE_MAIN_JSON.add("settings", BOPE_CONFIG_OBJECT());

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_CONFIGS);
		BOPE_VERIFY_FILES(PATH_CONFIGS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_CONFIGS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_CONFIGS() throws IOException {
		InputStream BOPE_JSON_FILE      = Files.newInputStream(PATH_CONFIGS);
		JsonObject  BOPE_JSON           = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_JSON      = BOPE_JSON.get("settings").getAsJsonObject();
		JsonObject  BOPE_MAIN_BUTTONS   = BOPE_MAIN_JSON.get("buttons").getAsJsonObject();
		JsonObject  BOPE_MAIN_COMBOBOXS = BOPE_MAIN_JSON.get("comboboxs").getAsJsonObject();
		JsonObject  BOPE_MAIN_LABELS    = BOPE_MAIN_JSON.get("labels").getAsJsonObject();
		JsonObject  BOPE_MAIN_SLIDERS_D = BOPE_MAIN_JSON.get("slidersD").getAsJsonObject();
		JsonObject  BOPE_MAIN_SLIDERS_I = BOPE_MAIN_JSON.get("slidersI").getAsJsonObject();

		for (BopeSetting buttons : Bope.get_setting_manager().get_array_settings()) {
			if (is(buttons, "label")          ||
				is(buttons, "combobox")       ||
				is(buttons, "doubleslider")   ||
				is(buttons, "integerslider")) {
				continue;
			}

			JsonObject BOPE_BUTTONS_MAIN = BOPE_MAIN_BUTTONS.get(buttons.get_master().get_tag()).getAsJsonObject();

			JsonObject BOPE_BUTTONS_INFO = BOPE_BUTTONS_MAIN.get(buttons.get_tag()).getAsJsonObject();
			
			Bope.get_setting_manager().get_setting_with_tag(BOPE_BUTTONS_INFO.get("master").getAsString(), BOPE_BUTTONS_INFO.get("tag").getAsString()).set_value(BOPE_BUTTONS_INFO.get("value").getAsBoolean());
		}

		for (BopeSetting comboboxs : Bope.get_setting_manager().get_array_settings()) {
			if (is(comboboxs, "label")          ||
				is(comboboxs, "button")         ||
				is(comboboxs, "doubleslider")   ||
				is(comboboxs, "integerslider")) {
				continue;
			}

			JsonObject BOPE_COMBOBOXS_MAIN = BOPE_MAIN_COMBOBOXS.get(comboboxs.get_master().get_tag()).getAsJsonObject();
			JsonObject BOPE_COMBOBOXS_INFO = BOPE_COMBOBOXS_MAIN.get(comboboxs.get_tag()).getAsJsonObject();
		
			Bope.get_setting_manager().get_setting_with_tag(BOPE_COMBOBOXS_INFO.get("master").getAsString(), BOPE_COMBOBOXS_INFO.get("tag").getAsString()).set_current_value(BOPE_COMBOBOXS_INFO.get("value").getAsString());
		}

		for (BopeSetting labels : Bope.get_setting_manager().get_array_settings()) {
			if (is(labels, "combobox")       ||
				is(labels, "button")         ||
				is(labels, "doubleslider")   ||
				is(labels, "integerslider")) {
				continue;
			}

			JsonObject BOPE_LABELS_MAIN = BOPE_MAIN_LABELS.get(labels.get_master().get_tag()).getAsJsonObject();
			JsonObject BOPE_LABELS_INFO = BOPE_LABELS_MAIN.get(labels.get_tag()).getAsJsonObject();
		
			Bope.get_setting_manager().get_setting_with_tag(BOPE_LABELS_INFO.get("master").getAsString(), BOPE_LABELS_INFO.get("tag").getAsString()).set_value(BOPE_LABELS_INFO.get("value").getAsString());
		}

		for (BopeSetting slider_doubles : Bope.get_setting_manager().get_array_settings()) {
			if (is(slider_doubles, "combobox")       ||
				is(slider_doubles, "button")         ||
				is(slider_doubles, "label")          ||
				is(slider_doubles, "integerslider")) {
				continue;
			}

			JsonObject BOPE_SLIDER_D_MAIN = BOPE_MAIN_SLIDERS_D.get(slider_doubles.get_master().get_tag()).getAsJsonObject();
			JsonObject BOPE_SLIDER_D_INFO = BOPE_SLIDER_D_MAIN.get(slider_doubles.get_tag()).getAsJsonObject();
		
			Bope.get_setting_manager().get_setting_with_tag(BOPE_SLIDER_D_INFO.get("master").getAsString(), BOPE_SLIDER_D_INFO.get("tag").getAsString()).set_value(BOPE_SLIDER_D_INFO.get("value").getAsDouble());
		}

		for (BopeSetting slider_integers : Bope.get_setting_manager().get_array_settings()) {
			if (is(slider_integers, "combobox")       ||
				is(slider_integers, "button")         ||
				is(slider_integers, "label")          ||
				is(slider_integers, "doubleslider")) {
				continue;
			}

			JsonObject BOPE_SLIDER_I_MAIN = BOPE_MAIN_SLIDERS_I.get(slider_integers.get_master().get_tag()).getAsJsonObject();
			JsonObject BOPE_SLIDER_I_INFO = BOPE_SLIDER_I_MAIN.get(slider_integers.get_tag()).getAsJsonObject();
		
			Bope.get_setting_manager().get_setting_with_tag(BOPE_SLIDER_I_INFO.get("master").getAsString(), BOPE_SLIDER_I_INFO.get("tag").getAsString()).set_value(BOPE_SLIDER_I_INFO.get("value").getAsInt());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_BINDS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON   = new JsonObject();
		JsonObject BOPE_MODULE_JSON = new JsonObject();

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			JsonObject BOPE_MODULE_INFO = new JsonObject();

			BOPE_MODULE_INFO.add("name",   new JsonPrimitive(modules.get_name()));
			BOPE_MODULE_INFO.add("tag",    new JsonPrimitive(modules.get_tag()));
			BOPE_MODULE_INFO.add("int",    new JsonPrimitive(modules.get_bind(0)));
			BOPE_MODULE_INFO.add("string", new JsonPrimitive(modules.get_bind("0")));
			BOPE_MODULE_INFO.add("state",  new JsonPrimitive(modules.is_active()));

			BOPE_MODULE_JSON.add(modules.get_tag(), BOPE_MODULE_INFO);
		}

		BOPE_MAIN_JSON.add("modules", BOPE_MODULE_JSON);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_BINDS);
		BOPE_VERIFY_FILES(PATH_BINDS);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_BINDS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_BINDS() throws IOException {
		InputStream BOPE_JSON_FILE      = Files.newInputStream(PATH_BINDS);
		JsonObject  BOPE_JSON           = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_MODULES   = BOPE_JSON.get("modules").getAsJsonObject();

		for (BopeModule modules : Bope.get_module_manager().get_array_modules()) {
			JsonObject BOPE_MODULES_JSON = BOPE_MAIN_MODULES.get(modules.get_tag()).getAsJsonObject();

			BopeModule module_requested = Bope.get_module_manager().get_module_with_tag(BOPE_MODULES_JSON.get("tag").getAsString());
			
			module_requested.set_bind(BOPE_MODULES_JSON.get("int").getAsInt());
			module_requested.set_active(BOPE_MODULES_JSON.get("state").getAsBoolean());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_CLIENT() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		JsonObject BOPE_MAIN_CONFIGS = new JsonObject();

		BOPE_MAIN_CONFIGS.add("name",    new JsonPrimitive(Bope.get_name()));
		BOPE_MAIN_CONFIGS.add("version", new JsonPrimitive(Bope.get_version()));
		BOPE_MAIN_CONFIGS.add("user",    new JsonPrimitive(Bope.get_actual_user()));
		BOPE_MAIN_CONFIGS.add("prefix",  new JsonPrimitive(Bope.get_command_manager().get_prefix()));

		BOPE_MAIN_JSON.add("configuration", BOPE_MAIN_CONFIGS);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_CLIENT);
		BOPE_VERIFY_FILES(PATH_CLIENT);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_CLIENT), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_CLIENT() throws IOException {
		InputStream BOPE_JSON_FILE          = Files.newInputStream(PATH_CLIENT);
		JsonObject  BOPE_MAIN_CLIENT        = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_CONFIGURATION = BOPE_MAIN_CLIENT.get("configuration").getAsJsonObject();

		Bope.get_command_manager().set_prefix(BOPE_MAIN_CONFIGURATION.get("prefix").getAsString());

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_LOG() throws IOException {
		Date hora = new Date();

		String cache = "-";
		String year  = new SimpleDateFormat("yyyy").format(hora);
		String month = new SimpleDateFormat("MM").format(hora);
		String day   = new SimpleDateFormat("dd").format(hora);
		String hour  = new SimpleDateFormat("HH").format(hora);
		String mm    = new SimpleDateFormat("mm").format(hora);
		String ss    = new SimpleDateFormat("ss").format(hora);
		String path  = (BOPE_ABS_LOG + cache + year + cache + month + cache + day + cache + hour + cache + mm + cache + ss + cache + ".txt");

		PATH_LOG = Paths.get(path);

		BOPE_VERIFY_FILES(PATH_LOG);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
		file.write(this.log.toString());

		file.close();
	}

	public void save() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FOLDER(PATH_FOLDER_LOG);

			BOPE_SAVE_CONFIGS();
			BOPE_SAVE_BINDS();
			BOPE_SAVE_CLIENT();
			BOPE_SAVE_LOG();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void load() {
		try {
			BOPE_LOAD_CONFIGS();
			BOPE_LOAD_BINDS();
			BOPE_LOAD_CLIENT();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public boolean is(BopeSetting setting, String type) {
		return setting.get_type().equalsIgnoreCase(type);
	}

	public void send_log(String log) {
		this.log.append(log + "\n");
	}
}