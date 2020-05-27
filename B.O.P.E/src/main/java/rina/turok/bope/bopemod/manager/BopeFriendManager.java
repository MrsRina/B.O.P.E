package rina.turok.bope.bopemod.manager;

/**
 * @author Rina
 *
 * Created by Rina.
 * 22/05/2020.
 *
 **/
public class BopeFriendManager {
	String tag;

	public BopeFriendManager(String tag) {
		this.tag = tag;
	}

	public String get_tag() {
		return this.tag;
	}
}