package rina.turok.bope.bopemod.events;

// External.
import rina.turok.bope.external.BopeEventCancellable;

/**
 * @author Rina
 *
 * Created by Rina.
 * 30/05/20.
 *
 **/
public class BopeEventJoin extends BopeEventCancellable {
	String name;

	public BopeEventJoin(String name) {
		super();

		this.name = name;
	}

	public String get_name() {
		return this.name;
	}
}