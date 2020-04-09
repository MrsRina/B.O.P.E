package rina.turok.bope.bopemod;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

import com.google.gson.*;

import rina.turok.bope.bopemod.manager.BopeModuleManager;
import rina.turok.bope.bopemod.backgui.BopeButton;
import rina.turok.bope.bopemod.backgui.BopeSlider;

// Rina.
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

	public static void BOPE_SAVE_WIDGETS() throws IOException {
		ArrayList BOPE_LIST_WIDGETS = new ArrayList();

		BOPE_LIST_WIDGETS.add(BopeModuleManager.convert_to_list());

		BOPE_DELETE_FILES_WIDGETS();
		BOPE_VERIFY_FILES_WIDGETS();

		Gson        BOPE_PLVIDID       = new GsonBuilder().setPrettyPrinting().create();
		JsonParser  BOPE_LEAFY         = new JsonParser();
		JsonElement BOPE_EXPLOSIVE_CAM = BOPE_LEAFY.parse(new Gson().toJson(BOPE_LIST_WIDGETS));
		String      BOPE_DEMTIO        = BOPE_PLVIDID.toJson(BOPE_EXPLOSIVE_CAM);

		FileWriter file;

		file = new FileWriter(BOPE_ABS_WIDGETS);
		file.write(BOPE_DEMTIO);

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

			BOPE_SAVE_WIDGETS();
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