package rina.turok.bope.bopemod.hacks;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeCategory {
	/**
	 * Hidden.
	 * @param y_sw yes show.
	 * @param n_sw no  show.
	 *
	 * Beta.
	 * @param y_bt yes beta.
	 * @param n_bt no  beta.
	 **/

	public enum Category {
		BOPE_CHAT     ("B.O.P.E Chat"    , "BopeChat"    , "y_sw", "y_bt"),
		BOPE_COMBAT   ("B.O.P.E Combat"  , "BopeCombat"  , "y_sw", "y_bt"),
		BOPE_MOVEMENT ("B.O.P.E Movement", "BopeMovement", "y_sw", "y_bt"),
		BOPE_RENDER   ("B.O.P.E Render"  , "BopeRender"  , "y_sw", "y_bt"),
		BOPE_EXPLOIT  ("B.O.P.E Exploit" , "BopeExploit" , "y_sw", "y_bt"),
		BOPE_GUI      ("B.O.P.E GUI"     , "BopeGUI"     , "y_sw", "y_bt"),
		BOPE_BETA     ("B.O.P.E Beta"    , "BopeBeta"    , "y_sw", "n_bt"),
		BOPE_HIDDEN   ("B.O.P.E Hidden"  , "BopeHidden"  , "n_sw", "y_bt");

		String name;
		String tag;

		String hidden;
		String beta;

		Category(String name, String tag, String hidden, String beta) {
			this.name   = name;
			this.tag    = tag;
			this.hidden = hidden;
			this.beta   = beta;
		}

		public boolean is_hidden() {
			if (this.hidden.equals("n_sw")) {
				return true;
			}

			return false;
		}

		public boolean is_beta() {
			if (this.beta.equals("y_bt")) {
				return true;
			}

			return false;
		}

		public String get_name() {
			return this.name;
		}

		public String get_tag() {
			return this.tag;
		}
	}
}