package rina.turok.bope.bopemod.hacks;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Hacks.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 05/05/20.
 *
 */
public class BopeClickGUI extends BopeModule {
	BopeSetting label_frame = create("info", "ClickGUIInfoFrame", "Frames");

	BopeSetting name_frame_r = create("Name R", "ClickGUINameFrameR", 255, 0, 255);
	BopeSetting name_frame_g = create("Name G", "ClickGUINameFrameG", 255, 0, 255);
	BopeSetting name_frame_b = create("Name B", "ClickGUINameFrameB", 255, 0, 255);

	BopeSetting background_frame_r = create("Background R", "ClickGUIBackgroundFrameR", 0, 0, 255);
	BopeSetting background_frame_g = create("Background G", "ClickGUIBackgroundFrameG", 0, 0, 255);
	BopeSetting background_frame_b = create("Background B", "ClickGUIBackgroundFrameB", 0, 0, 255);
	BopeSetting background_frame_a = create("Background A", "ClickGUIBackgroundFrameA", 50, 0, 255);

	BopeSetting border_frame_r = create("Border R", "ClickGUIBorderFrameR", 255, 0, 255);
	BopeSetting border_frame_g = create("Border G", "ClickGUIBorderFrameG", 255, 0, 255);
	BopeSetting border_frame_b = create("Border B", "ClickGUIBorderFrameB", 255, 0, 255);

	BopeSetting label_widget = create("info", "ClickGUIInfoWidget", "Widgets");

	BopeSetting name_widget_r = create("Name R", "ClickGUINameWidgetR", 255, 0, 255);
	BopeSetting name_widget_g = create("Name G", "ClickGUINameWidgetG", 255, 0, 255);
	BopeSetting name_widget_b = create("Name B", "ClickGUINameWidgetB", 255, 0, 255);

	BopeSetting background_widget_r = create("Background R", "ClickGUIBackgroundWidgetR", 255, 0, 255);
	BopeSetting background_widget_g = create("Background G", "ClickGUIBackgroundWidgetG", 255, 0, 255);
	BopeSetting background_widget_b = create("Background B", "ClickGUIBackgroundWidgetB", 255, 0, 255);
	BopeSetting background_widget_a = create("Background A", "ClickGUIBackgroundWidgetA", 100, 0, 255);

	BopeSetting border_widget_r = create("Border R", "ClickGUIBorderWidgetR", 255, 0, 255);
	BopeSetting border_widget_g = create("Border G", "ClickGUIBorderWidgetG", 255, 0, 255);
	BopeSetting border_widget_b = create("Border B", "ClickGUIBorderWidgetB", 255, 0, 255);

	public BopeClickGUI() {
		super(BopeCategory.BOPE_GUI);

		// Info.
		this.name        = "GUI";
		this.tag         = "GUI";
		this.description = "B.O.P.E GUI for enbable or disable modules.";

		release("B.O.P.E");

		set_bind(Bope.BOPE_KEY_GUI);
	}

	@Override
	public void update() {
		// Update frame colors.
		Bope.click_gui.theme_frame_name_r = name_frame_r.get_value(1);
		Bope.click_gui.theme_frame_name_g = name_frame_g.get_value(1);
		Bope.click_gui.theme_frame_name_b = name_frame_b.get_value(1);

		Bope.click_gui.theme_frame_background_r = background_frame_r.get_value(1);
		Bope.click_gui.theme_frame_background_g = background_frame_g.get_value(1);
		Bope.click_gui.theme_frame_background_b = background_frame_b.get_value(1);
		Bope.click_gui.theme_frame_background_a = background_frame_a.get_value(1);

		Bope.click_gui.theme_frame_border_r = border_frame_r.get_value(1);
		Bope.click_gui.theme_frame_border_g = border_frame_g.get_value(1);
		Bope.click_gui.theme_frame_border_b = border_frame_b.get_value(1);

		// Update widget colors.
		Bope.click_gui.theme_widget_name_r = name_widget_r.get_value(1);
		Bope.click_gui.theme_widget_name_g = name_widget_g.get_value(1);
		Bope.click_gui.theme_widget_name_b = name_widget_b.get_value(1);

		Bope.click_gui.theme_widget_background_r = background_widget_r.get_value(1);
		Bope.click_gui.theme_widget_background_g = background_widget_g.get_value(1);
		Bope.click_gui.theme_widget_background_b = background_widget_b.get_value(1);
		Bope.click_gui.theme_widget_background_a = background_widget_a.get_value(1);

		Bope.click_gui.theme_widget_border_r = border_widget_r.get_value(1);
		Bope.click_gui.theme_widget_border_g = border_widget_g.get_value(1);
		Bope.click_gui.theme_widget_border_b = border_widget_b.get_value(1);
	}

	@Override
	public void enable() {
		if (mc.world != null && mc.player != null) {
			mc.displayGuiScreen(Bope.click_gui);
		}
	}

	@Override
	public void disable() {
		if (mc.world != null && mc.player != null) {
			mc.displayGuiScreen(null);
		}
	}
}