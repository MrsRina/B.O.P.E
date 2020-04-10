package rina.turok.bope.bopemod;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

// Json manager.
import com.google.gson.*;

import rina.turok.bope.bopemod.manager.BopeModuleManager;
import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.backgui.BopeSlider;
import rina.turok.bope.bopemod.BopeSaveModule;
import rina.turok.bope.bopemod.BopeModule;
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
	public static String BOPE_FILE_WIDGETS  = "Configs.json";
	public static String BOPE_FILE_BINDS    = "Binds.json";

	public static String BOPE_ABS_WIDGETS = (BOPE_FOLDER_CONFIG + BOPE_FILE_WIDGETS);
	public static String BOPE_ABS_FOLDER  = (BOPE_FOLDER_CONFIG);
	public static String BOPE_ABS_BINDS   = (BOPE_FOLDER_CONFIG + BOPE_FILE_BINDS);

	public static Path PATH_WIDGETS = Paths.get(BOPE_ABS_WIDGETS);
	public static Path PATH_FOLDER  = Paths.get(BOPE_ABS_FOLDER);
	public static Path PATH_BINDS   = Paths.get(BOPE_ABS_BINDS);

	public static void BOPE_VERIFY_FOLDER_CONFIGS() throws IOException {
		if (!Files.exists(PATH_FOLDER)) {
			Files.createDirectories(PATH_FOLDER);
		}
	}

	public static void BOPE_VERIFY_FILES_WIDGETS () throws IOException {
		if (!Files.exists(PATH_WIDGETS)) {
			Files.createFile(PATH_WIDGETS);
		}
	}

	public static void BOPE_VERIFY_FILES_BINDS() throws IOException {
		if (!Files.exists(PATH_BINDS)) {
			Files.createFile(PATH_BINDS);
		}
	}

	public static void BOPE_DELETE_FILES_WIDGETS() throws IOException {
		File file = new File(BOPE_ABS_WIDGETS);

		file.delete();
	}

	public static void BOPE_SAVE_CONFIGS() throws IOException {
		HashMap<String, BopeSaveModule> modules_state = new HashMap<String, BopeSaveModule>();

		Gson       BOPE_GSON   = new GsonBuilder().setPrettyPrinting().create();
		JsonParser BOPE_PARSER = new JsonParser(); 

		// String BOPE_DEMTIO        = BOPE_PLVIDID.toJson(BOPE_EXPLOSIVE_CAM);

		JsonObject BOPE_MAIN_JSON   = new JsonObject();
		JsonObject BOPE_MODULE_JSON = new JsonObject();
		JsonObject BOPE_MODULE_INFO = new JsonObject();

		for (BopeSaveModule module : Bope.get_module_manager().get_save_modules()) {
			modules_state.put(module.get_tag(), module);

			JsonElement BOPE_MODULES = BOPE_PARSER.parse(new Gson().toJson(modules_state));

			BOPE_MODULE_JSON.add(module.get_tag(), BOPE_MODULES);
		}

		BOPE_MAIN_JSON.add("Modules", BOPE_MODULE_JSON);

		JsonElement BOPE_MAIN_PRETTY_JSON = BOPE_PARSER.parse(BOPE_MAIN_JSON.toString());

		String BOPE_JSON = BOPE_GSON.toJson(BOPE_MAIN_PRETTY_JSON);

		BOPE_DELETE_FILES_WIDGETS();
		BOPE_VERIFY_FILES_WIDGETS();

		FileWriter file;

		file = new FileWriter(BOPE_ABS_WIDGETS);
		file.write(BOPE_JSON);

		file.close();
	}

	public static void BOPE_LOAD_WIDGETS() throws IOException {
		InputStream BOPE_JSON_FILE    = Files.newInputStream(PATH_WIDGETS);
		JsonObject  BOPE_JSON         = new JsonParser().parse(new InputStreamReader(BOPE_JSON_FILE)).getAsJsonObject();
		JsonObject  BOPE_JSON_BUTTONS = BOPE_JSON.get("BUTTON_TYPE_BOOLEAN").getAsJsonObject();

		BOPE_JSON_FILE.close();
	}

	public static void save() {
		try {
			BOPE_VERIFY_FOLDER_CONFIGS();

			BOPE_SAVE_CONFIGS();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public static void load() {
		try {
			BOPE_LOAD_WIDGETS();
		} catch (Exception exc) {
			exc.printStackTrace();			
		}
	}
}