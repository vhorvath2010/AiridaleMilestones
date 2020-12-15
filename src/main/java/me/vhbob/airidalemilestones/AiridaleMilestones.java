package me.vhbob.airidalemilestones;

import me.vhbob.airidalemilestones.commands.AddMilestoneCommand;
import me.vhbob.airidalemilestones.commands.AddMilestoneItem;
import me.vhbob.airidalemilestones.commands.ClearMilestone;
import me.vhbob.airidalemilestones.commands.ListMilestones;
import me.vhbob.airidalemilestones.events.MilestoneEvents;
import me.vhbob.airidalemilestones.events.ToolLoreEvent;
import me.vhbob.airidalemilestones.util.MilestoneManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AiridaleMilestones extends JavaPlugin {

    private static AiridaleMilestones plugin;
    private MilestoneManager milestoneManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new ToolLoreEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MilestoneEvents(), this);
        getCommand("AddMilestoneCommand").setExecutor(new AddMilestoneCommand());
        getCommand("AddMilestoneItem").setExecutor(new AddMilestoneItem());
        getCommand("ClearMilestone").setExecutor(new ClearMilestone());
        getCommand("ListMilestones").setExecutor(new ListMilestones());
        this.milestoneManager = new MilestoneManager();
        try {
            milestoneManager.loadMilestones();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            milestoneManager.saveMilestones();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AiridaleMilestones getPlugin() {
        return plugin;
    }

    public MilestoneManager getMilestoneManager() {
        return milestoneManager;
    }
}
