package me.bubbles.bubblehack.ui.screens.clickgui;

import me.bubbles.bubblehack.module.Mod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {

    public static final ClickGUI instance = new ClickGUI();

    private List<Frame> frames;

    private ClickGUI() {
        super(Text.literal("ClickGUI"));

        frames=new ArrayList<>();

        int offsetX = 20;

        for(Mod.Category category : Mod.Category.values()) {
            frames.add(new Frame(category, offsetX, 20, 100, 20));
            offsetX+=120;
        }

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        for(Frame frame : frames) {
            frame.render(matrices, mouseX, mouseY, delta);
            frame.updatePosition(mouseX,mouseY);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, button);
            frame.updatePosition(mouseX,mouseY);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
            frame.updatePosition(mouseX,mouseY);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}
