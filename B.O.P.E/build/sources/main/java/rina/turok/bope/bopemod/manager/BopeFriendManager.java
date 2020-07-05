package rina.turok.bope.bopemod.manager;

import java.util.*;

// Data.
import rina.turok.bope.bopemod.BopeFriend;

// Core.
import rina.turok.bope.Bope;

/**
 * @author Rina
 *
 * Created by Rina.
 * 30/05/2020.
 *
 **/
public class BopeFriendManager {
	private ArrayList<BopeFriend> array_friend = new ArrayList<>();

	String tag;

	public BopeFriendManager(String tag) {
		this.tag = tag;
	}

	public void add_friend(String name) {
		array_friend.add(new BopeFriend(name));
	}

	public void remove_friend(BopeFriend friend) {
		array_friend.remove(friend); // :(
	}

	public void clear() {
		array_friend.clear();
	}

	public ArrayList<BopeFriend> get_array_friends() {
		return array_friend;
	}

	public BopeFriend get_friend_with_name(String name) {
		BopeFriend friend_requested = null;

		for (BopeFriend friends : get_array_friends()) {
			if (friends.get_name().equalsIgnoreCase(name)) {
				friend_requested = friends;
			}
		}

		return friend_requested;
	}

	public boolean is_friend(String name) {
		boolean state = false;

		BopeFriend friend_requested = get_friend_with_name(name);

		if (friend_requested != null) {
			state = true;
		}

		return state;
	}

	public String get_tag() {
		return this.tag;
	}
}