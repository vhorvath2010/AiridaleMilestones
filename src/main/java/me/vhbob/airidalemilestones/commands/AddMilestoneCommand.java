package me.vhbob.airidalemilestones.commands;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AddMilestoneCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("AddMilestoneCommand")) {
            if (strings.length >= 3) {
                // Build info
                int requirement = Integer.parseInt(strings[0]);
                boolean recurring = strings[1].equalsIgnoreCase("true");
                String cmd = strings[2];
                for (int i = 3; i < strings.length; ++i) {
                    cmd += " " + strings[i];
                }
                // Register info
                AiridaleMilestones.getPlugin().getMilestoneManager().registerReward(requirement, recurring, cmd);
                commandSender.sendMessage(ChatColor.GREEN + "Added the command as a reward for " + requirement + " blocks");
                if (recurring) {
                    commandSender.sendMessage(ChatColor.GREEN + "Milestone is recurring");
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Milestone is not recurring");
                }
                return true;
            }
        }
        return false;
    }
}
