package me.vhbob.airidalemilestones.commands;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddMilestoneItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("AddMilestoneCommand")) {
            if (strings.length >= 3 && commandSender instanceof Player) {
                Player player = (Player) commandSender;
                // Build info
                int requirement = Integer.parseInt(strings[0]);
                boolean recurring = strings[1].equalsIgnoreCase("true");
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack != null) {
                    // Register info
                    AiridaleMilestones.getPlugin().getMilestoneManager().registerReward(requirement, recurring, itemStack);
                    return true;
                }
            }
        }
        return false;
    }

}
