package me.bubbles.bubblehack;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.ModuleManager;
import me.bubbles.bubblehack.ui.screens.clickgui.ClickGUI;
import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class Client implements ModInitializer {

    public static final Client instance = new Client();
    public Logger logger = LoggerFactory.getLogger(Client.class);
    public static MinecraftClient mc = MinecraftClient.getInstance();
    private boolean isEnabled=false;

    @Override
    public void onInitialize() {
        this.isEnabled=true;
    }

    public void onKeyPress(int key, int action) {
        if(action == GLFW.GLFW_PRESS) {
            for(Mod mod : ModuleManager.instance.getModules()) {
                if(key==mod.getKey()) mod.toggle();
            }

            if(key == GLFW.GLFW_KEY_RIGHT_SHIFT) {
                mc.setScreen(ClickGUI.instance);
            }

        }
    }

    public void onTick() {
        if (mc.player!=null) {
            for(Mod mod : ModuleManager.instance.getEnabledModules()) {
                mod.onTick();
            }
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public static double roundCoordinate(double n) {
        n = Math.round(n * 100) / 100d;  // Round to 1/100th
        return Math.nextAfter(n, n + Math.signum(n));  // Fix floating point errors
    }

}
