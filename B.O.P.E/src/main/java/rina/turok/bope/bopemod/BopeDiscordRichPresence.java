package rina.turok.bope.bopemod;

import net.minecraft.client.Minecraft;

// Discord.
import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordRPC;

import java.util.*;

// Bope.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 15/06/2020.
*
*/
public class BopeDiscordRichPresence {
	public final DiscordRPC discord_rpc;
	public       DiscordRichPresence discord_presence;

	public final Minecraft mc = Minecraft.getMinecraft();

	// Detail.
	public String detail_option_1;
	public String detail_option_2;
	public String detail_option_3;
	public String detail_option_4;

	// State.
	public String state_option_1;
	public String state_option_2;
	public String state_option_3;
	public String state_option_4;

	public BopeDiscordRichPresence(String tag) {
		this.discord_rpc      = DiscordRPC.INSTANCE;
		this.discord_presence = new DiscordRichPresence();

		// Gay.
		this.detail_option_1 = "";
		this.detail_option_2 = "";
		this.detail_option_3 = "";
		this.detail_option_4 = "";

		// Stuff.
		this.state_option_1 = "";
		this.state_option_2 = "";
		this.state_option_3 = "";
		this.state_option_4 = "";
	}

	public void stop() {
		this.discord_rpc.Discord_Shutdown();
	}

	public void run() {
		this.discord_presence = new DiscordRichPresence();

		final DiscordEventHandlers handler_ = new DiscordEventHandlers();

		this.discord_rpc.Discord_Initialize("722173364227014756", handler_, true, "");

		// this.discord_presence.startTimestamp = System.currentTimeMillis() / 1000l;
		this.discord_presence.largeImageText = "B.O.P.E" + Bope.BOPE_SPACE + Bope.BOPE_VERSION;
		this.discord_presence.largeImageKey  = "splash";

		new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					String detail = this.detail_option_1 + this.detail_option_2 + this.detail_option_3 + this.detail_option_4;
					String state  = this.state_option_1  + this.state_option_2  + this.state_option_3  + this.state_option_4;

					this.discord_rpc.Discord_RunCallbacks();

					this.discord_presence.details = detail;
					this.discord_presence.state   = state;

					this.discord_rpc.Discord_UpdatePresence(this.discord_presence);
				} catch (Exception exc) {
					exc.printStackTrace();
				}

				try {
					Thread.sleep(4000L);
				}

				catch (InterruptedException exc_) {
					exc_.printStackTrace();
				}
			}
		}, "RPC-Callback-Handler").start();
	}

	public String set(String presume) {
		return " " + presume;
	}
}