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

// Data.
import rina.turok.bope.bopemod.BopeSetting;
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

	public static void BOPE_SAVE_CONFIGS() throws IOException {
		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser(); 

		JsonObject BOPE_MAIN_JSON    = new JsonObject();
		JsonObject BOPE_SETTING_JSON = new JsonObject();

		// INT, DOUBLE, BUTTON, STRING, COMBOBOX

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_CONFIGS();
		BOPE_VERIFY_CONFIG_FILES();

		OutputStreamWriter file;

		file = new OutputStreamWriter(new FileOutputStream(BOPE_ABS_CONFIGS), "UTF-8");
		file.write(BOPE_JSON);

		file.close();
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

	public static void BOPE_LOAD_CONFIGS() throws IOException {
		InputStream BOPE_JSON_FILE    = Files.newInputStream(PATH_CONFIGS);
		JsonObject  BOPE_JSON         = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();

		BOPE_JSON_FILE.close();
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

	public static void load_bind(String module) {
		try {
			InputStream BOPE_JSON_FILE = Files.newInputStream(PATH_CONFIGS);
			JsonObject  BOPE_JSON      = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
			
			JsonObject BOPE_MODULES_JS = BOPE_JSON.get("modules").getAsJsonObject();
			// JsonObject BOPE_LOAD_BINDS = BOPE_MODULES_JS.get(module).getAsJsonObject();

			// BopeModuleManager.get_module(module).set_int_bind(BOPE_LOAD_BINDS.get("int").getAsInt());

			BOPE_JSON_FILE.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	public static String convert_s(String value) {
		return TurokString.to_string(value);
	}

	public static String convert_i(int value) {
		return TurokString.to_string(value);
	}

	public static String convert_d(double value) {
		return TurokString.to_string(value);
	}

	public static String convert_b(boolean value) {
		return TurokString.to_string(value);
	}
}