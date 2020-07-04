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
import rina.turok.bope.bopemod.guiscreen.render.pinnables.BopePinnable;
import rina.turok.bope.bopemod.guiscreen.render.components.BopeFrame;
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.bopemod.BopeFriend;

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

	public String BOPE_FILE_COMBOBOXS = "comboboxs.txt";
	public String BOPE_FILE_INTEGERS  = "integers.txt";
	public String BOPE_FOLDER_CONFIG  = "B.O.P.E/";
	public String BOPE_FILE_FRIENDS   = "friends.txt";
	public String BOPE_FILE_BUTTONS   = "buttons.txt";
	public String BOPE_FILE_DOUBLES   = "doubles.txt";
	public String BOPE_FILE_CLIENT    = "client.json";
	public String BOPE_FILE_LABELS    = "labels.txt";
	public String BOPE_FOLDER_LOG     = "logs/";
	public String BOPE_FOLDER_VALUES  = "values/";
	public String BOPE_FOLDER_MODULES = "modules/";
	public String BOPE_FILE_BINDS     = "binds.txt";
	public String BOPE_FILE_HUD       = "HUD.json";
	public String BOPE_FILE_LOG       = "log";

	public String BOPE_ABS_FOLDER_LOG = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_LOG);
	public String BOPE_ABS_COMBOBOXS  = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_VALUES + BOPE_FILE_COMBOBOXS);
	public String BOPE_ABS_INTEGERS   = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_VALUES + BOPE_FILE_INTEGERS);
	public String BOPE_ABS_DOUBLES    = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_VALUES + BOPE_FILE_DOUBLES);
	public String BOPE_ABS_BUTTONS    = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_VALUES + BOPE_FILE_BUTTONS);
	public String BOPE_ABS_FRIENDS    = (BOPE_FOLDER_CONFIG + BOPE_FILE_FRIENDS);
	public String BOPE_ABS_LABELS     = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_VALUES + BOPE_FILE_LABELS);
	public String BOPE_ABS_FOLDER     = (BOPE_FOLDER_CONFIG);
	public String BOPE_ABS_CLIENT     = (BOPE_FOLDER_CONFIG + BOPE_FILE_CLIENT);
	public String BOPE_ABS_BINDS      = (BOPE_FOLDER_CONFIG + BOPE_FOLDER_MODULES + BOPE_FILE_BINDS);
	public String BOPE_ABS_HUD        = (BOPE_FOLDER_CONFIG + BOPE_FILE_HUD);
	public String BOPE_ABS_LOG        = (BOPE_ABS_FOLDER_LOG + BOPE_FILE_LOG);

	public Path PATH_FOLDER_LOG = Paths.get(BOPE_ABS_FOLDER_LOG);
	public Path PATH_COMBOBOXS  = Paths.get(BOPE_ABS_COMBOBOXS);
	public Path PATH_INTEGERS   = Paths.get(BOPE_ABS_INTEGERS);
	public Path PATH_DOUBLES    = Paths.get(BOPE_ABS_DOUBLES);
	public Path PATH_FRIENDS    = Paths.get(BOPE_ABS_FRIENDS);
	public Path PATH_BUTTONS    = Paths.get(BOPE_ABS_BUTTONS);
	public Path PATH_CLIENT     = Paths.get(BOPE_ABS_CLIENT);
	public Path PATH_LABELS     = Paths.get(BOPE_ABS_LABELS);
	public Path PATH_FOLDER     = Paths.get(BOPE_ABS_FOLDER);
	public Path PATH_BINDS      = Paths.get(BOPE_ABS_BINDS);
	public Path PATH_HUD        = Paths.get(BOPE_ABS_HUD);

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

	public void BOPE_SAVE_SETTINGS() {
		File file;
		BufferedWriter buffer;

		Iterator iterator;

		BopeSetting settings;

		try {
			BOPE_DELETE_FILES(BOPE_ABS_BUTTONS);
			BOPE_VERIFY_FILES(PATH_BUTTONS);

			file   = new File(BOPE_ABS_BUTTONS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_setting_manager().get_array_settings().iterator();

			while (iterator.hasNext()) {
				settings = (BopeSetting) iterator.next();

				if (is(settings, "button")) {
					buffer.write(settings.get_tag() + ":" + settings.get_value(true) + ":" + settings.get_master().get_tag() + "\r\n");
				}
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			BOPE_DELETE_FILES(BOPE_ABS_COMBOBOXS);
			BOPE_VERIFY_FILES(PATH_COMBOBOXS);

			file   = new File(BOPE_ABS_COMBOBOXS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_setting_manager().get_array_settings().iterator();

			while (iterator.hasNext()) {
				settings = (BopeSetting) iterator.next();

				if (is(settings, "combobox")) {
					buffer.write(settings.get_tag() + ":" + settings.get_current_value() + ":" + settings.get_master().get_tag() + "\r\n");
				}
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			BOPE_DELETE_FILES(BOPE_ABS_LABELS);
			BOPE_VERIFY_FILES(PATH_LABELS);

			file   = new File(BOPE_ABS_LABELS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_setting_manager().get_array_settings().iterator();

			while (iterator.hasNext()) {
				settings = (BopeSetting) iterator.next();

				if (is(settings, "label")) {
					buffer.write(settings.get_tag() + ":" + settings.get_value("label") + ":" + settings.get_master().get_tag() + "\r\n");
				}
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			BOPE_DELETE_FILES(BOPE_ABS_DOUBLES);
			BOPE_VERIFY_FILES(PATH_DOUBLES);

			file   = new File(BOPE_ABS_DOUBLES);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_setting_manager().get_array_settings().iterator();

			while (iterator.hasNext()) {
				settings = (BopeSetting) iterator.next();

				if (is(settings, "doubleslider")) {
					buffer.write(settings.get_tag() + ":" + settings.get_value(1.0) + ":" + settings.get_master().get_tag() + "\r\n");
				}
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			BOPE_DELETE_FILES(BOPE_ABS_INTEGERS);
			BOPE_VERIFY_FILES(PATH_INTEGERS);

			file   = new File(BOPE_ABS_INTEGERS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_setting_manager().get_array_settings().iterator();

			while (iterator.hasNext()) {
				settings = (BopeSetting) iterator.next();

				if (is(settings, "integerslider")) {
					buffer.write(settings.get_tag() + ":" + settings.get_value(1) + ":" + settings.get_master().get_tag() + "\r\n");
				}
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_LAOD_SETTINGS() {
		File file;
		FileInputStream input_stream;
		DataInputStream data_stream;
		BufferedReader buffer;

		String line;
		String colune;

		String tag;
		String value;
		String module;

		BopeSetting module_req;

		try {
			file         = new File(BOPE_ABS_BUTTONS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				value  = colune.split(":")[1];
				module = colune.split(":")[2];

				Bope.get_setting_manager().get_setting_with_tag(module, tag).set_value(Boolean.parseBoolean(value));
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			file         = new File(BOPE_ABS_COMBOBOXS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				value  = colune.split(":")[1];
				module = colune.split(":")[2];

				Bope.get_setting_manager().get_setting_with_tag(module, tag).set_current_value(value);
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			file         = new File(BOPE_ABS_LABELS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				value  = colune.split(":")[1];
				module = colune.split(":")[2];

				Bope.get_setting_manager().get_setting_with_tag(module, tag).set_value(value);
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			file         = new File(BOPE_ABS_DOUBLES);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				value  = colune.split(":")[1];
				module = colune.split(":")[2];

				Bope.get_setting_manager().get_setting_with_tag(module, tag).set_value(Double.parseDouble(value));
			}

			buffer.close();
		} catch (Exception exc) {}

		try {
			file         = new File(BOPE_ABS_INTEGERS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				value  = colune.split(":")[1];
				module = colune.split(":")[2];

				Bope.get_setting_manager().get_setting_with_tag(module, tag).set_value(Integer.parseInt(value));
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_SAVE_BINDS() {
		File file;
		BufferedWriter buffer;

		Iterator iterator;

		BopeModule modules;

		try {
			BOPE_DELETE_FILES(BOPE_ABS_BINDS);
			BOPE_VERIFY_FILES(PATH_BINDS);

			file   = new File(BOPE_ABS_BINDS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_module_manager().get_array_modules().iterator();

			while (iterator.hasNext()) {
				modules = (BopeModule) iterator.next();

				buffer.write(modules.get_tag() + ":" + modules.get_bind(1) + ":" + modules.is_active() + ":" + modules.alert() + "\r\n");
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_LOAD_BINDS() {
		File file;
		FileInputStream input_stream;
		DataInputStream data_stream;
		BufferedReader buffer;

		String line;
		String colune;

		String tag;
		String bind;
		String active;
		String alert;

		try {
			file         = new File(BOPE_ABS_BINDS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				colune = line.trim();

				// Get values.
				tag    = colune.split(":")[0];
				bind   = colune.split(":")[1];
				active = colune.split(":")[2];
				alert  = colune.split(":")[3];

				BopeModule module = Bope.get_module_manager().get_module_with_tag(tag);

				module.set_bind(Integer.parseInt(bind));
				module.alert(Boolean.parseBoolean(alert));
				module.set_active(Boolean.parseBoolean(active));
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_SAVE_FRIENDS() {
		File file;
		BufferedWriter buffer;

		Iterator iterator;

		BopeFriend friends;

		try {
			BOPE_DELETE_FILES(BOPE_ABS_FRIENDS);
			BOPE_VERIFY_FILES(PATH_FRIENDS);

			file   = new File(BOPE_ABS_FRIENDS);
			buffer = new BufferedWriter(new FileWriter(file));

			iterator = Bope.get_friend_manager().get_array_friends().iterator();

			while (iterator.hasNext()) {
				friends = (BopeFriend) iterator.next();

				buffer.write(friends.get_name() + "\r\n");
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_LOAD_FRIENDS() {
		File file;
		FileInputStream input_stream;
		DataInputStream data_stream;
		BufferedReader buffer;

		String line;

		try {
			file         = new File(BOPE_ABS_FRIENDS);
			input_stream = new FileInputStream(file.getAbsolutePath());
			data_stream  = new DataInputStream(input_stream);
			buffer       = new BufferedReader(new InputStreamReader(data_stream));

			while ((line = buffer.readLine()) != null) {
				Bope.get_friend_manager().add_friend(line);
			}

			buffer.close();
		} catch (Exception exc) {}
	}

	public void BOPE_SAVE_CLIENT() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		JsonObject BOPE_MAIN_CONFIGS = new JsonObject();
		JsonObject BOPE_MAIN_GUI     = new JsonObject();

		BOPE_MAIN_CONFIGS.add("name",    new JsonPrimitive(Bope.get_name()));
		BOPE_MAIN_CONFIGS.add("version", new JsonPrimitive(Bope.get_version()));
		BOPE_MAIN_CONFIGS.add("user",    new JsonPrimitive(Bope.get_actual_user()));
		BOPE_MAIN_CONFIGS.add("prefix",  new JsonPrimitive(Bope.get_command_manager().get_prefix()));

		for (BopeFrame frames_gui : Bope.click_gui.get_array_frames()) {
			JsonObject BOPE_FRAMES_INFO = new JsonObject();

			BOPE_FRAMES_INFO.add("name", new JsonPrimitive(frames_gui.get_name()));
			BOPE_FRAMES_INFO.add("tag",  new JsonPrimitive(frames_gui.get_tag()));
			BOPE_FRAMES_INFO.add("x",    new JsonPrimitive(frames_gui.get_x()));
			BOPE_FRAMES_INFO.add("y",    new JsonPrimitive(frames_gui.get_y()));

			BOPE_MAIN_GUI.add(frames_gui.get_tag(), BOPE_FRAMES_INFO);
		}

		BOPE_MAIN_JSON.add("configuration", BOPE_MAIN_CONFIGS);
		BOPE_MAIN_JSON.add("gui",           BOPE_MAIN_GUI);

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
		JsonObject  BOPE_MAIN_GUI           = BOPE_MAIN_CLIENT.get("gui").getAsJsonObject();

		Bope.get_command_manager().set_prefix(BOPE_MAIN_CONFIGURATION.get("prefix").getAsString());

		for (BopeFrame frames : Bope.click_gui.get_array_frames()) {
			JsonObject BOPE_FRAME_INFO = BOPE_MAIN_GUI.get(frames.get_tag()).getAsJsonObject();

			BopeFrame frame_requested = Bope.click_gui.get_frame_with_tag(BOPE_FRAME_INFO.get("tag").getAsString());

			frame_requested.set_x(BOPE_FRAME_INFO.get("x").getAsInt());
			frame_requested.set_y(BOPE_FRAME_INFO.get("y").getAsInt());
		}

		BOPE_JSON_FILE.close();
	}

	public void BOPE_SAVE_HUD() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser();

		JsonObject BOPE_MAIN_JSON = new JsonObject();

		JsonObject BOPE_MAIN_FRAME = new JsonObject();
		JsonObject BOPE_MAIN_HUD   = new JsonObject();

		BOPE_MAIN_FRAME.add("name", new JsonPrimitive(Bope.click_hud.get_frame_hud().get_name()));
		BOPE_MAIN_FRAME.add("tag",  new JsonPrimitive(Bope.click_hud.get_frame_hud().get_tag()));
		BOPE_MAIN_FRAME.add("x",    new JsonPrimitive(Bope.click_hud.get_frame_hud().get_x()));
		BOPE_MAIN_FRAME.add("y",    new JsonPrimitive(Bope.click_hud.get_frame_hud().get_y()));

		for (BopePinnable pinnables_hud : Bope.get_hud_manager().get_array_huds()) {
			JsonObject BOPE_FRAMES_INFO = new JsonObject();

			BOPE_FRAMES_INFO.add("title", new JsonPrimitive(pinnables_hud.get_title()));
			BOPE_FRAMES_INFO.add("tag",   new JsonPrimitive(pinnables_hud.get_tag()));
			BOPE_FRAMES_INFO.add("state", new JsonPrimitive(pinnables_hud.is_active()));
			BOPE_FRAMES_INFO.add("dock",  new JsonPrimitive(pinnables_hud.get_dock()));
			BOPE_FRAMES_INFO.add("x",     new JsonPrimitive(pinnables_hud.get_x()));
			BOPE_FRAMES_INFO.add("y",     new JsonPrimitive(pinnables_hud.get_y()));

			BOPE_MAIN_HUD.add(pinnables_hud.get_tag(), BOPE_FRAMES_INFO);
		}

		BOPE_MAIN_JSON.add("frame", BOPE_MAIN_FRAME);
		BOPE_MAIN_JSON.add("hud",   BOPE_MAIN_HUD);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES(BOPE_ABS_HUD);
		BOPE_VERIFY_FILES(PATH_HUD);

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_HUD), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
	}

	public void BOPE_LOAD_HUD() throws IOException {
		InputStream BOPE_JSON_FILE  = Files.newInputStream(PATH_HUD);
		JsonObject  BOPE_MAIN_HUD   = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_MAIN_FRAME = BOPE_MAIN_HUD.get("frame").getAsJsonObject();
		JsonObject  BOPE_MAIN_HUDS  = BOPE_MAIN_HUD.get("hud").getAsJsonObject();

		Bope.click_hud.get_frame_hud().set_x(BOPE_MAIN_FRAME.get("x").getAsInt());
		Bope.click_hud.get_frame_hud().set_y(BOPE_MAIN_FRAME.get("y").getAsInt());

		for (BopePinnable pinnables : Bope.get_hud_manager().get_array_huds()) {
			JsonObject BOPE_HUD_INFO = BOPE_MAIN_HUDS.get(pinnables.get_tag()).getAsJsonObject();

			BopePinnable pinnable_requested = Bope.get_hud_manager().get_pinnable_with_tag(BOPE_HUD_INFO.get("tag").getAsString());

			pinnable_requested.set_active(BOPE_HUD_INFO.get("state").getAsBoolean());
			pinnable_requested.set_dock(BOPE_HUD_INFO.get("dock").getAsBoolean());

			pinnable_requested.set_x(BOPE_HUD_INFO.get("x").getAsInt());
			pinnable_requested.set_y(BOPE_HUD_INFO.get("y").getAsInt());
		}

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

	public void save_settings() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FOLDER(Paths.get(BOPE_ABS_FOLDER + BOPE_FOLDER_VALUES));

			BOPE_SAVE_SETTINGS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}	
	}

	public void load_settings() {
		BOPE_LAOD_SETTINGS();
	}

	public void save_binds() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FOLDER(Paths.get(BOPE_ABS_FOLDER + BOPE_FOLDER_MODULES));

			BOPE_SAVE_BINDS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}	
	}

	public void load_binds() {
		BOPE_LOAD_BINDS();
	}

	public void save_friends() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);

			BOPE_SAVE_FRIENDS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}	
	}

	public void load_friends() {
		BOPE_LOAD_FRIENDS();
	}

	public void save_client() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);

			BOPE_SAVE_CLIENT();
			BOPE_SAVE_HUD();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void load_client() {
		try {
			BOPE_LOAD_CLIENT();
			BOPE_LOAD_HUD();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public void save_log() {
		try {
			BOPE_VERIFY_FOLDER(PATH_FOLDER);
			BOPE_VERIFY_FOLDER(PATH_FOLDER_LOG);

			BOPE_SAVE_LOG();
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
