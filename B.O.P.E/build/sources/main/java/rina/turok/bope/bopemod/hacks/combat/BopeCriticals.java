package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.entity.EntityLivingBase;

// Zero alpine manager.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeMessage;
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 16/05/20.
*
*/
public class BopeCriticals extends BopeModule {
	BopeSetting event_mode = create("Event Mode", "CriticalsEventMode", "Packet", combobox("Packet", "Jump"));

	public BopeCriticals() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Criticals";
		this.tag         = "Criticals";
		this.description = "You can hit with criticals when attack.";

		// Release the module.
		release("B.O.P.E - Module - B.O.P.E");
	}

	@EventHandler
	private Listener<BopeEventPacket.SendPacket> listener = new Listener<>(event -> {
		if (event.get_packet() instanceof CPacketUseEntity) {
			CPacketUseEntity packet = (CPacketUseEntity) event.get_packet();

			if (packet.getAction() == CPacketUseEntity.Action.ATTACK && mc.player.onGround && !mc.gameSettings.keyBindJump.isKeyDown() && packet.getEntityFromWorld(mc.world) instanceof EntityLivingBase) {
				if (event_mode.in("Packet")) {
					mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY + 0.1f, mc.player.posZ, false));
					mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ, false));
				} else if (event_mode.in("Jump")) {
					mc.player.jump();
				}
			}
		}
	});

	@Override
	public String detail_option() {
		return event_mode.get_current_value();
	}
}