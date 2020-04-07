package rina.turok.bope.bopemod;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

import com.google.gson.*;

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

	public static void verify_files() throws IOException {
		if (!Files.exists(PATH_FOLDER)) {
			Files.createDirectories(PATH_FOLDER);
		}

		if (!Files.exists(PATH_WIDGETS)) {
			Files.createFile(PATH_WIDGETS);
		}

		if (!Files.exists(PATH_BINDS)) {
			Files.createFile(PATH_BINDS);
		}
	}

	public static void delet_widgets_file() throws IOException {
		File file = new File(BOPE_ABS_WIDGETS);

		file.delete();
	}

	public static void save() {
		try {
			verify_files();
			save_buttons();
		} catch (IOException exc) {
			exc.printStackTrace();
		}		
	}

	public static void save_buttons() throws IOException {
		ArrayList widgets = new ArrayList();

		widgets.add(BopeButton.get_buttons());
		widgets.add(BopeSlider.get_sliders_double());
		widgets.add(BopeSlider.get_sliders_float());
		widgets.add(BopeSlider.get_sliders_int());

		delet_widgets_file();
		verify_files();

		Gson plivid = new GsonBuilder().setPrettyPrinting().create();

		JsonParser leafy = new JsonParser();
		JsonElement cam  = leafy.parse(new Gson().toJson(widgets));

		String demtio = plivid.toJson(cam);

		FileWriter file;

		file = new FileWriter(BOPE_ABS_WIDGETS);
		file.write(demtio);

		file.close();
	}

	public static void load_configs() throws IOException {
		InputStream stream = Files.newInputStream(PATH_WIDGETS);

		JsonObject json = new JsonParser().parse(new InputStreamReader(stream)).getAsJsonObject();

		stream.close();
	}
}