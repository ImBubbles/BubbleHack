package me.bubbles.bubblehack.module.categories.movement;

import me.bubbles.bubblehack.mixinterface.IMinecraftClient;
import me.bubbles.bubblehack.module.Mod;
import me.bubbles.bubblehack.module.settings.NumberSetting;

import java.util.stream.Stream;

import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;

public class Parkour extends Mod {

    private NumberSetting minDepth = new NumberSetting("Min Depth", 0.05, 0.5, 0.05, 0.05);
    private NumberSetting edgeDistance = new NumberSetting("Edge Dis", 0.001, 0.25, 0.001, 0.001);

    public Parkour() {
        super("Parkour","Autojumps at edge",Category.MOVEMENT);
        addSettings(minDepth, edgeDistance);
    }

    @Override
    public void onTick() {

        if(!mc.player.isOnGround() || mc.options.jumpKey.isPressed())
        return;

        if(mc.player.isSneaking() || mc.options.sneakKey.isPressed())
            return;

        Box box = mc.player.getBoundingBox();
        Box adjustedBox = box.stretch(0, -minDepth.getValue(), 0)
                .expand(-edgeDistance.getValue(), 0, -edgeDistance.getValue());

        IMinecraftClient IMC = (IMinecraftClient)mc;

        Stream<VoxelShape> blockCollisions = IMC.getWorld().getBlockCollisionsStream(mc.player, adjustedBox);

        if(blockCollisions.findAny().isPresent())
            return;

        mc.player.jump();

        super.onTick();
    }
}
