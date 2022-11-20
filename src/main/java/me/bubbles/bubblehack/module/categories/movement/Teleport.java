package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.settings.NumberSetting;

public class Teleport extends Mod {

    NumberSetting xVal = new NumberSetting("X-Val",-15,15,0,1);
    NumberSetting yVal = new NumberSetting("Y-Val",-15,15,0,1);
    NumberSetting zVal = new NumberSetting("Z-Val",-15,15,0,1);

    public Teleport() {
        super("Teleport","Force TP",Category.MOVEMENT);

        addSettings(xVal,yVal,zVal);
    }

    @Override
    public void onEnable() {
        double x = mc.player.getX();
        double y = mc.player.getY();
        double z = mc.player.getZ();
        mc.player.setPosition(x+xVal.getValue(),y+yVal.getValue(),z+zVal.getValue());
        this.toggle();
        super.onEnable();
    }

}
