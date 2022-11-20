package me.bubbles.bubblehack.mixin;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.ModuleManager;
import me.bubbles.bubblehack.module.categories.liveoverflow.BotMovement;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static me.bubbles.bubblehack.Client.roundCoordinate;

@Mixin(PlayerMoveC2SPacket.Full.class)
public abstract class PlayerPositionFullPacketMixin {
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE",target = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V"))
    private static void init(Args args) {
        for(Mod mod : ModuleManager.instance.getEnabledModules()) {
            if(mod instanceof BotMovement) {
                args.set(0, roundCoordinate(args.get(0)));  // Round x
                args.set(2, roundCoordinate(args.get(2)));  // Round z
            }
        }
    }
}
