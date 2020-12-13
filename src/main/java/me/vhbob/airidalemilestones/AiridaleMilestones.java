package me.vhbob.airidalemilestones;

import me.vhbob.airidalemilestones.events.ToolLoreEvent;
import me.vhbob.airidalemilestones.util.MilestoneManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AiridaleMilestones extends JavaPlugin {

    private static AiridaleMilestones plugin;
    private MilestoneManager milestoneManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new ToolLoreEvent(), this);
    }

    public static AiridaleMilestones getPlugin() {
        return plugin;
    }

    public MilestoneManager getMilestoneManager() {
        return milestoneManager;
    }
}
