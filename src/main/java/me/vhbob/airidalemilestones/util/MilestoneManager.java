package me.vhbob.airidalemilestones.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class MilestoneManager {

    private ArrayList<Milestone> activeMilestones;

    public MilestoneManager() {
        this.activeMilestones = new ArrayList<>();
    }

    public void registerMilestone(Milestone milestone) {
        activeMilestones.add(milestone);
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

}
