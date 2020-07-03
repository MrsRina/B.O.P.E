package rina.turok.bope.bopemod.commands;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeCommand;
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
public class BopeFriends extends BopeCommand {
	public BopeFriends() {
		super("friends", "For get list friends.");
	}

	public boolean get_message(String[] message) {
		if (message.length > 1) {
			BopeMessage.send_client_error_message("friends");

			return true;
		}

		if (Bope.get_friend_manager().get_array_friends().isEmpty()) {
			BopeMessage.send_client_message("No friends :(");
		}

		int count = 0;
		int size  = Bope.get_friend_manager().get_array_friends().size();

		StringBuilder names = new StringBuilder();

		for (BopeFriend friends : Bope.get_friend_manager().get_array_friends()) {
			count++;

			if (count >= size) {
				names.append(Bope.dg + friends.get_name() + Bope.r + ".");
			} else {
				names.append(Bope.dg + friends.get_name() + Bope.r + ", ");
			}
		}

		BopeMessage.send_client_message(names.toString());

		return true;
	}
}