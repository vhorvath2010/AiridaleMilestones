package me.vhbob.airidalemilestones.commands;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import me.vhbob.airidalemilestones.util.Milestone;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListMilestones implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("ListMilestones")) {
            commandSender.sendMessage(ChatColor.GREEN + "Active milestones");
            for (Milestone milestone : AiridaleMilestones.getPlugin().getMilestoneManager().getActiveMilestones()) {
                if (milestone.isRecurring()) {
                    commandSender.sendMessage(ChatColor.GREEN + "Recurring milestone: " + milestone.getRequirement() + " blocks");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "One-time milestone: " + milestone.getRequirement() + " blocks");
                }
            }
            return true;
        }
        return false;
    }
}
