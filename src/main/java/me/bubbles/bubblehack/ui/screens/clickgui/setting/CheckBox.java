package me.bubbles.bubblehack.ui.screens.clickgui.setting;

import me.bubbles.bubblehack.module.settings.BooleanSetting;
import me.bubbles.bubblehack.module.settings.Setting;
import me.bubbles.bubblehack.ui.screens.clickgui.ModuleButton;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.Color;

public class CheckBox extends Component {

    private BooleanSetting boolSet = (BooleanSetting) setting;

    public CheckBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.boolSet=(BooleanSetting) setting;
    }

    @Override
    public void render(MatrixStack matrices, double mouseX, double mouseY, float delta) {
        DrawableHelper.fill(matrices,parent.parent.x,parent.parent.y+parent.offset+offset,parent.parent.x+parent.parent.width,parent.parent.y+parent.offset+offset+parent.parent.height, new Color(0,0,0,50).getRGB());
        int textOffset = ((parent.parent.height/2)-mc.textRenderer.fontHeight/2);
        mc.textRenderer.drawWithShadow(matrices,boolSet.getName()+": "+boolSet.isEnabled(), parent.parent.x+textOffset, parent.parent.y+parent.offset+offset+textOffset, Color.WHITE.getRGB());
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isHovered(mouseX,mouseY)&&button==0) {
            boolSet.toggle();
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

}
