package rina.turok.bope.external;

// Zero Alpine.
import me.zero.alpine.EventManager;
import me.zero.alpine.EventBus;

// Rina.
// This content a event_bus to send for zero alpine manager.
public class BopeEventBus {
	// EVENT_BUS from ZeroAlpine.
	public static final EventBus ZERO_ALPINE_EVENT_BUS = new EventManager();
}