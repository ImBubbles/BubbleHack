package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;

public class AutoDismount extends Mod {

    public AutoDismount() {
        super("AutoDismount","Auto dismounts",Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        mc.player.dismountVehicle();
        super.onTick();
    }
}
