package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;

public class Sprint extends Mod {

    public Sprint() {
        super("Sprint", "Autosprint", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        mc.player.setSprinting(true);
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onTick();
    }

}
