package me.vhbob.airidalemilestones.events;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import me.vhbob.airidalemilestones.util.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ToolLoreEvent implements Listener {

    private final FileConfiguration config = AiridaleMilestones.getPlugin().getConfig();

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (isValidTool(hand)) {
            Util.incrementBlocksBroken(hand);
        }
    }

    private boolean isValidTool(ItemStack item) {
        // Check for valid tool type
        if (item != null) {
            String itemType = item.getType().toString();
            for (String type : config.getStringList("valid_tools")) {
                if (itemType.contains("_" + type) || itemType.equalsIgnoreCase(type)) {
                    return true;
                }
            }
        }
        return false;
    }

}
