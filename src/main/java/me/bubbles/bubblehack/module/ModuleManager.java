package me.bubbles.bubblehack.module;

import me.bubbles.bubblehack.module.categories.liveoverflow.BotMovement;
import me.bubbles.bubblehack.module.categories.liveoverflow.Demo;
import me.bubbles.bubblehack.module.categories.liveoverflow.ForceSurvival;
import me.bubbles.bubblehack.module.categories.liveoverflow.WorldBorder;
import me.bubbles.bubblehack.module.categories.movement.*;
import me.bubbles.bubblehack.module.categories.render.ModList;
import me.bubbles.bubblehack.module.categories.render.Watermark;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager instance = new ModuleManager();
    private List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        addModules();
    }

    public List<Mod> getModules() {
        return modules;
    }

    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();
        for(Mod mod : modules) {
            if(mod.isEnabled()) enabled.add(mod);
        }
        return enabled;
    }

    public List<Mod> getModulesInCategory(Mod.Category category) {
        List<Mod> categoryModules = new ArrayList<>();

        for(Mod mod : modules) {
            if(mod.getCategory()==category) {
                categoryModules.add(mod);
            }
        }

        return categoryModules;

    }

    private void addModules() {
        modules.add(new Flight());
        modules.add(new Sprint());
        modules.add(new NoFall());
        modules.add(new BoatFly());
        modules.add(new Watermark());
        modules.add(new ModList());
        modules.add(new BotMovement());
        modules.add(new Teleport());
        modules.add(new WorldBorder());
        modules.add(new Demo());
        modules.add(new ForceSurvival());
    }

}
