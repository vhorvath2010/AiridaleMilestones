package me.vhbob.airidalemilestones.commands;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddMilestoneItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("AddMilestoneItem")) {
            if (strings.length == 2 && commandSender instanceof Player) {
                Player player = (Player) commandSender;
                // Build info
                int requirement = Integer.parseInt(strings[0]);
                boolean recurring = strings[1].equalsIgnoreCase("true");
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack != null) {
                    // Register info
                    AiridaleMilestones.getPlugin().getMilestoneManager().registerReward(requirement, recurring, itemStack);
                    player.sendMessage(ChatColor.GREEN + "Added the item in your hand as a reward for " + requirement + " blocks");
                    if (recurring) {
                        player.sendMessage(ChatColor.GREEN + "Milestone is recurring");
                    } else {
                        player.sendMessage(ChatColor.RED + "Milestone is not recurring");
                    }
                    return true;
                }
            }
        }
        return false;
    }

}
