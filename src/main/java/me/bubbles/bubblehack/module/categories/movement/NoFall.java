package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.module.Mod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Mod {

    public NoFall() {
        super("No Fall","Take 0 fall damage",Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = mc.player;

        // If the player's fall distance is less than or equal to 1 (if flying) or 2 (if not flying) or the player is in creative then return nothing because nothing has to happen
        if(player.fallDistance <= (player.isFallFlying() ? 1 : 2)||player.getAbilities().creativeMode) {
            return;
        }

        player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));

        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onTick();
    }

}
