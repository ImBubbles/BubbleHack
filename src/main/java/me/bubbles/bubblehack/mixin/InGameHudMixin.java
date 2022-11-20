package me.bubbles.bubblehack.mixin;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.ModuleManager;
import me.bubbles.bubblehack.module.categories.render.ModList;
import me.bubbles.bubblehack.module.categories.render.Watermark;
import me.bubbles.bubblehack.ui.screens.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    private static MinecraftClient mc = MinecraftClient.getInstance();

    @Inject(method = "render", at = @At("RETURN"), cancellable=true)
    public void renderHud(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        for(Mod mod : ModuleManager.instance.getEnabledModules()) {
            if(mod instanceof Watermark) {
                MatrixStack watermark = matrices;
                watermark.push();
                watermark.scale(2F,2F,2F);
                int sWidth=mc.getWindow().getScaledWidth();
                int sHeight=mc.getWindow().getScaledHeight();
                mc.textRenderer.drawWithShadow(watermark, Text.literal(mod.getDisplayName()),2, 4, Color.BLUE.getRGB());
                watermark.pop();
            } else if (mod instanceof ModList) {
                Hud.render(matrices, tickDelta);
            }
        }
    }
}
