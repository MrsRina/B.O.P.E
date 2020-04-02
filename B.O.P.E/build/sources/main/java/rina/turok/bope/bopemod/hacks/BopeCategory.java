package rina.turok.bope.bopemod.hacks;

// Rina.
public class BopeCategory {
	public enum Category {
		BOPE_CHAT     ("B.O.P.E Chat",     false, false),
		BOPE_COMBAT   ("B.O.P.E Combat",   false, false),
		BOPE_MOVEMENT ("B.O.P.E Movement", false, false),
		BOPE_RENDER   ("B.O.P.E Render",   false, false),
		BOPE_EXPLOIT  ("B.O.P.E Exploit",  false, false),
		BOPE_GUI      ("B.O.P.E GUI",      false, false),
		BOPE_BETA     ("B.O.P.E Beta",     false, true),
		BOPE_HIDDEN   ("B.O.P.E Hidden",   true, false);

		String name;

		boolean hidden;
		boolean beta;

		Category(String name, boolean hidden, boolean beta) {
			this.name   = name;
			this.hidden = hidden;
			this.beta   = beta;
		}

		public boolean is_hidden() {
			return this.hidden;
		}

		public boolean is_beta() {
			return this.beta;
		}

		public String get_name() {
			return this.name;
		}
	}
}