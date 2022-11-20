package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.settings.NumberSetting;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BoatFly extends Mod {

    private NumberSetting vertSpeed = new NumberSetting("V-Speed",1,10,1,0.1);
    private NumberSetting hortSpeed = new NumberSetting("H-Speed", 1,10,1,0.1);

    public BoatFly() {
        super("Boat Fly", "Allows you to fly in boats", Category.MOVEMENT);

        addSettings(vertSpeed, hortSpeed);
    }

    @Override
    public void onTick() {
        if(!mc.player.hasVehicle())
            return;

        Entity vehicle = mc.player.getVehicle();
        Vec3d velocity = vehicle.getVelocity();

        // default motion
        double motionX = velocity.x;
        double motionY = 0;
        double motionZ = velocity.z;

        // up/down
        if(mc.options.jumpKey.isPressed()) {
            motionY = vertSpeed.getValue();
        } else if(mc.options.sprintKey.isPressed()) {
            motionY = velocity.y;
        }


        // forward
        if(mc.options.forwardKey.isPressed())
        {
            double speed = hortSpeed.getValue();
            float yawRad = vehicle.getYaw() * MathHelper.RADIANS_PER_DEGREE;

            motionX = MathHelper.sin(-yawRad) * speed;
            motionZ = MathHelper.cos(yawRad) * speed;
        }

        // apply motion
        vehicle.setVelocity(motionX, motionY, motionZ);
        super.onTick();

    }
}
