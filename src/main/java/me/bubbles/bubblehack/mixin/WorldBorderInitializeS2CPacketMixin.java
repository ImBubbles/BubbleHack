package me.bubbles.bubblehack.mixin;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.ModuleManager;
import me.bubbles.bubblehack.module.categories.liveoverflow.WorldBorder;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.WorldBorderInitializeS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderInitializeS2CPacket.class)
public class WorldBorderInitializeS2CPacketMixin {
    @Inject(method="apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V",at = @At(value="INVOKE"), cancellable = true)
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        for(Mod mod : ModuleManager.instance.getEnabledModules()) {
            if(mod instanceof WorldBorder) {
                ci.cancel();
            }
        }
    }
}
