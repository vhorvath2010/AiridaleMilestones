package me.vhbob.airidalemilestones.commands;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearMilestone implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("ClearMilestone")) {
            if (strings.length == 2) {
                // Build info
                int requirement = Integer.parseInt(strings[0]);
                boolean recurring = strings[1].equalsIgnoreCase("true");
                if (AiridaleMilestones.getPlugin().getMilestoneManager().removeMilestone(requirement, recurring)) {
                    commandSender.sendMessage(ChatColor.GREEN + "Removed milestone");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Milestone not found");
                }
                return true;
            }
        }
        return false;
    }
}
