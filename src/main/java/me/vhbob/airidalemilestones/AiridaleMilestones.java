package me.vhbob.airidalemilestones;

import org.bukkit.plugin.java.JavaPlugin;

public class AiridaleMilestones extends JavaPlugin {

    private AiridaleMilestones plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public AiridaleMilestones getPlugin() {
        return plugin;
    }
}
