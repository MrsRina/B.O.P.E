package rina.turok.bope.mixins;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

// Events.
//import rina.turok.bope.bopemod.events.BopeEventPlayerDamageBlock;

// Core.
import rina.turok.bope.Bope;

/**
* @author Rina
*
* Created by Rina.
* 17/05/2020.
*
* - It were referenced with KAMI mixins, 086 thanks for help me.
*
*/
@Mixin(value = PlayerControllerMP.class, priority = 998)
public class BopeMixinPlayerControllerMP {
//	@Inject(method = "onPlayerDamageBlockHook", at = @At(value = "INVOKE", target = "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z"))
//	private boolean onPlayerDamageBlockHook(BlockPos pos, EnumFacing face) {
//		BopeEventPlayerDamageBlock event = new BopeEventPlayerDamageBlock(pos, face);

//		Bope.ZERO_ALPINE_EVENT_BUS.post(event);

//		return event.isCancelled();
//	}
}