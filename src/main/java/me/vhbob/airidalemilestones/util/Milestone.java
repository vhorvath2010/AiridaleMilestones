package me.vhbob.airidalemilestones.util;

import me.clip.placeholderapi.PlaceholderAPI;
import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Milestone {

    private int requirement;
    private boolean recurring;
    private ArrayList<String> commands;
    private ArrayList<ItemStack> items;

    public Milestone(int requirement, boolean recurring) {
        this.requirement = requirement;
        this.recurring = recurring;
        this.commands = new ArrayList<>();
        this.items = new ArrayList<>();
        AiridaleMilestones.getPlugin().getMilestoneManager().registerMilestone(this);
    }

    public int getRequirement() {
        return requirement;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void addReward(String command) {
        commands.add(command);
    }

    public void addReward(ItemStack item) {
        items.add(item);
    }

    public void giveRewards(Player player) {
        // Dispatch commands
        for (String command : commands) {
            command = PlaceholderAPI.setPlaceholders(player, command);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
        // Give items
        for (ItemStack item : items) {
            player.getWorld().dropItem(player.getLocation(), item);
        }
        String message = AiridaleMilestones.getPlugin().getConfig().getString("messages.milestone");
        message = ChatColor.translateAlternateColorCodes('&', message);
        DecimalFormat formatter = new DecimalFormat("#,###");
        message.replace("%blocks%", formatter.format(requirement));
        player.sendMessage(message);
    }

}
