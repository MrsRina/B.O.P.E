package rina.turok.bope.bopemod.manager;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraftforge.client.event.*;
import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

// Zero alpine manager.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Data.
import rina.turok.bope.bopemod.BopeCommand;
import rina.turok.bope.bopemod.BopeMessage;

// External.
import rina.turok.bope.external.BopeEventBus;

// Events.
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.events.BopeEventLeave;
import rina.turok.bope.bopemod.events.BopeEventJoin;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 08/04/20.
*
*/
public class BopeEventManager {
	private String tag;

	private Minecraft mc = Minecraft.getMinecraft();

	public BopeEventManager(String tag) {
		this.tag = tag;
	}

	@SubscribeEvent
	public void onUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.isCanceled()) {
			return;
		}
	}

	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (mc.player == null) {
			return;
		}

		Bope.get_module_manager().update();
	}

	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent event) {
		if (event.isCanceled()) {
			return;
		}

		Bope.get_module_manager().render(event);
	}

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (event.isCanceled()) {
			return;
		}

		RenderGameOverlayEvent.ElementType target = RenderGameOverlayEvent.ElementType.EXPERIENCE;

		if (!mc.player.isCreative() && mc.player.getRidingEntity() instanceof AbstractHorse) {
			target = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
		}

		if (event.getType() == target) {
			Bope.get_module_manager().render();

			if (Bope.get_module_manager().get_module_with_tag("GUI").is_active() == false) {
				Bope.get_hud_manager().render();
			}

			GL11.glPushMatrix();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);

			GlStateManager.enableBlend();

			GL11.glPopMatrix();

			TurokRenderHelp.release_gl();
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (Keyboard.getEventKeyState()) {
			Bope.get_module_manager().bind(Keyboard.getEventKey());
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onChat(ClientChatEvent event) {
		String   message      = event.getMessage();
		String[] message_args = Bope.get_command_manager().command_list.get_message(event.getMessage());

		boolean true_command = false;

		if (message_args.length > 0) {
			if (mc.player != null) {
				Bope.send_client_log(mc.player.getName() + ": " + message);
			}

			event.setCanceled(true);

			mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());

			for (BopeCommand command : Bope.get_command_manager().command_list.get_pure_command_list()) {
				try {
					if (Bope.get_command_manager().command_list.get_message(event.getMessage())[0].equalsIgnoreCase(command.get_name())) {
						true_command = command.get_message(Bope.get_command_manager().command_list.get_message(event.getMessage()));
					}
				} catch (Exception exc) {} // Somes gays problems.
			}

			if (!true_command && Bope.get_command_manager().command_list.has_prefix(event.getMessage())) {
				BopeMessage.send_client_message("Try use .help to get list of commands.");

				true_command = false;
			}
		}
	}

	@SubscribeEvent
	public void onInputUpdate(InputUpdateEvent event) {
		BopeEventBus.ZERO_ALPINE_EVENT_BUS.post(event);
	}

	@SubscribeEvent
	public void onClientChatReceivedEvent(ClientChatReceivedEvent event) {
		BopeEventBus.ZERO_ALPINE_EVENT_BUS.post(event);
	}

	@SubscribeEvent
	public void onPlayerPush(PlayerSPPushOutOfBlocksEvent event) {
		BopeEventBus.ZERO_ALPINE_EVENT_BUS.post(event);
	}

	public String get_tag() {
		return this.tag;
	}
}