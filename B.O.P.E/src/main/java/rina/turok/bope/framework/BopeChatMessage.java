package rina.turok.bope.framework;

import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.ITextComponent;

public static class BopeChatMessage extends TextComponentBase {
	String message_input;
	
	public BopeChatMessage(String message_input) {		
		Pattern p       = Pattern.compile("&[0123456789abcdefrlosmk]");
		Matcher m       = p.matcher(message_input);
		StringBuffer sb = new StringBuffer();

		while (m.find()) {
			String replacement = "\u00A7" + m.group().substring(1);
			m.appendReplacement(sb, replacement);
		}

		m.appendTail(sb);
		this.message_input = sb.toString();
	}
	
	public String get_unformatted_component_text() {
		return this.message_input;
	}
	
	@Override
	public ITextComponent createCopy() {
		return new BopeChatMessage(this.message_input);
	}
}