package me.vhbob.airidalemilestones.util;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MilestoneManager {

    private ArrayList<Milestone> activeMilestones;

    public MilestoneManager() {
        this.activeMilestones = new ArrayList<>();
    }

    public ArrayList<Milestone> getActiveMilestones() {
        return activeMilestones;
    }

    public void registerMilestone(Milestone milestone) {
        activeMilestones.add(milestone);
    }

    public boolean removeMilestone(int requirement, boolean recurring) {
        Milestone toRemove = null;
        for (Milestone milestone : activeMilestones) {
            if (milestone.isRecurring() == recurring && milestone.getRequirement() == requirement) {
                toRemove = milestone;
                break;
            }
        }
        return activeMilestones.remove(toRemove);
    }

    public void registerReward(int requirement, boolean recurring, String command) {
        // Try to add it to existing milestone data
        for (Milestone milestone : activeMilestones) {
            if (milestone.getRequirement() == requirement) {
                if (milestone.isRecurring() == recurring) {
                    milestone.addReward(command);
                    return;
                }
            }
        }
        // Create new milestone if we didn't find one
        Milestone milestone = new Milestone(requirement, recurring);
        milestone.addReward(command);
    }

    public void registerReward(int requirement, boolean recurring, ItemStack itemStack) {
        // Try to add it to existing milestone data
        for (Milestone milestone : activeMilestones) {
            if (milestone.getRequirement() == requirement) {
                if (milestone.isRecurring() == recurring) {
                    milestone.addReward(itemStack);
                    return;
                }
            }
        }
        // Create new milestone if we didn't find one
        Milestone milestone = new Milestone(requirement, recurring);
        milestone.addReward(itemStack);
    }

    public void checkForRewards(Player player, int broken) {
        // Give applicable rewards
        for (Milestone milestone : activeMilestones) {
            if (milestone.getRequirement() == broken) {
                milestone.giveRewards(player);
            } else if (milestone.isRecurring() && broken % milestone.getRequirement() == 0) {
                milestone.giveRewards(player);
            }
        }
    }

    public void saveMilestones() throws IOException {
        // Load file
        File milestonesFile = new File(AiridaleMilestones.getPlugin().getDataFolder(), "milestones.yml");
        if (!milestonesFile.exists()) {
            milestonesFile.createNewFile();
        }
        YamlConfiguration config = new YamlConfiguration();
        for (Milestone milestone : activeMilestones) {
            milestone.save(config);
        }
        config.save(milestonesFile);
    }

    public void loadMilestones() throws IOException, InvalidConfigurationException {
        File milestonesFile = new File(AiridaleMilestones.getPlugin().getDataFolder(), "milestones.yml");
        if (milestonesFile.exists()) {
            YamlConfiguration config = new YamlConfiguration();
            config.load(milestonesFile);
            // load all recurring milestones
            if (config.contains("recurring")) {
                for (String blocksString : config.getConfigurationSection("recurring").getKeys(false)) {
                    loadMilestone(config, "recurring." + blocksString, true, Integer.parseInt(blocksString));
                }
            }
            if (config.contains("once")) {
                // load non recurring
                for (String blocksString : config.getConfigurationSection("once").getKeys(false)) {
                    loadMilestone(config, "once." + blocksString, false, Integer.parseInt(blocksString));
                }
            }
        }
    }

    private void loadMilestone(YamlConfiguration config, String path, boolean recurring, int requirement) {
        Milestone milestone = new Milestone(requirement,recurring);
        for (ItemStack item : (ArrayList<ItemStack>) config.get(path + ".items")) {
            milestone.addReward(item);
        }
        for (String cmd : config.getStringList(path + ".commands")) {
            milestone.addReward(cmd);
        }
    }

}
