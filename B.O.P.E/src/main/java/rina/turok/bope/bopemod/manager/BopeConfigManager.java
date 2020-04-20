package rina.turok.bope.bopemod.manager;

// Data.
import rina.turok.bope.bopemod.BopeConfig;

// Core.
import rina.turok.bope.Bope;

/**
 *
 * @author Rina
 * Created by Rina.
 *
 * 19/04/2020.
 *
 **/
public class BopeConfigManager {
	private String tag;

	public BopeConfigManager(String tag) {
		this.tag = tag;

		
	}

	public String get_tag() {
		return tag;
	}
}