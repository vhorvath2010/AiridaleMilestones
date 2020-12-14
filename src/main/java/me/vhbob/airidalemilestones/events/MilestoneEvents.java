package me.vhbob.airidalemilestones.events;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import me.vhbob.airidalemilestones.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MilestoneEvents implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        int broken = Util.getBlocksBroken(player.getInventory().getItemInMainHand());
        if (broken > 0) {
            AiridaleMilestones.getPlugin().getMilestoneManager().checkForRewards(player, broken);
        }
    }

}
