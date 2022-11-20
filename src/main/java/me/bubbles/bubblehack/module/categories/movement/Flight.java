package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.settings.ModeSetting;
import me.bubbles.bubblehack.module.settings.NumberSetting;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Flight extends Mod {

    private NumberSetting speed = new NumberSetting("Speed",0.1,10,1,0.1);
    private ModeSetting type = new ModeSetting("Type","Vanilla","Vanilla","AntiKick");
    private NumberSetting antiKickInterval = new NumberSetting("Interval",30,160,80,5);

    private int tickCounter=0;

    public Flight() {
        super("Flight","Fly Hack", Category.MOVEMENT);
        addSettings(speed,type,antiKickInterval);
    }

    @Override
    public void onEnable() {
        tickCounter=0;
        super.onEnable();
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = mc.player;

        player.getAbilities().flying = false;
        player.airStrafingSpeed = (float)speed.getValue();

        player.setVelocity(0, 0, 0);
        Vec3d velocity = player.getVelocity();

        if(mc.options.jumpKey.isPressed())
            player.setVelocity(velocity.x, speed.getValue(), velocity.z);

        if(mc.options.sneakKey.isPressed())
        {
            player.airStrafingSpeed = Math.min((float) speed.getValue(), 0.85F);
            player.setVelocity(velocity.x, -speed.getValue(), velocity.z);
        }

        if(type.isMode("AntiKick")&&mc.isInSingleplayer())
            doAntiKick(velocity);
        super.onTick();
    }

    private void doAntiKick(Vec3d velocity)
    {
        if(tickCounter > antiKickInterval.getValue() + 1)
            tickCounter = 0;

        switch(tickCounter)
        {
            case 0 ->
            {
                if(mc.options.sneakKey.isPressed())
                    tickCounter = 2;
                else
                    mc.player.setVelocity(velocity.x, -0.07, velocity.z);
            }

            case 1 -> mc.player.setVelocity(velocity.x, 0.07, velocity.z);
        }

        tickCounter++;
    }

    @Override
    public void onDisable() {
        if(!mc.player.getAbilities().creativeMode) mc.player.getAbilities().allowFlying=false;
        mc.player.getAbilities().flying=false;
        super.onTick();
    }

}
