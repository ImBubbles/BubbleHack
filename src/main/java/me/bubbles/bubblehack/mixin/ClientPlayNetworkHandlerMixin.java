package me.bubbles.bubblehack.mixin;

import me.bubbles.bubblehack.Client;
import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.ModuleManager;
import me.bubbles.bubblehack.module.categories.liveoverflow.Demo;
import me.bubbles.bubblehack.module.categories.liveoverflow.ForceSurvival;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {

    @Inject(method="onGameStateChange", at = @At("HEAD"), cancellable = true)
    public void onGameStateChange(GameStateChangeS2CPacket packet, CallbackInfo ci) {
        for(Mod mod : ModuleManager.instance.getEnabledModules()) {
            if(mod instanceof Demo) {
                if(packet.getReason()==GameStateChangeS2CPacket.DEMO_MESSAGE_SHOWN) {
                    ci.cancel();
                }
            } else if (mod instanceof ForceSurvival) {
                if(packet.getReason()==GameStateChangeS2CPacket.GAME_MODE_CHANGED) {
                    Client.mc.interactionManager.setGameMode(GameMode.SURVIVAL);
                    ci.cancel();
                }
            }
        }
    }
}