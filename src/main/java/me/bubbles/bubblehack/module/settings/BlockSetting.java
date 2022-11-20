package me.bubbles.bubblehack.module.settings;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockSetting extends Setting {

    private List<String> blockSet = new ArrayList<>();
    private List<String> defaultSet = new ArrayList<>();

    public BlockSetting(String name) {
        super(name);
    }

    public BlockSetting(String name, String... defaultSet) {
        super(name);
        defaultAddBlocks(defaultSet);
    }

    public void defaultAddBlocks(String... blocks) {
        for(String block : blocks) {
            addDefaultBlock(block);
        }
    }

    public void addDefaultBlock(String block) {
        defaultSet.add(block);
    }

    public void addBlocks(String... blocks) {
        for(String block : blocks) {
            addBlock(block);
        }
    }

    public void addBlock(String block) {
        blockSet.add(block);
    }

    public List<String> getBlockSet() {
        return blockSet;
    }

    private List<String> getDefaultSet() {
        return defaultSet;
    }

    public void reset() {
        blockSet.clear();
        for(String block : defaultSet) {
            addBlock(block);
        }
    }

}
