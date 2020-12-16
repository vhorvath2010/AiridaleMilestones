package me.vhbob.airidalemilestones.util;

import me.vhbob.airidalemilestones.AiridaleMilestones;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final String loreFormat = ChatColor.translateAlternateColorCodes('&',
            AiridaleMilestones.getPlugin().getConfig().getString("lore"));

    public static int getBlocksBroken(ItemStack item) {
        // Try and find blocks
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
            List<String> lore = item.getItemMeta().getLore();
            String loreNoAmt = loreFormat.replace("%amt%", "");
            for (String line : lore) {
                if (line.contains(loreNoAmt)) {
                    String blocksAsString = line.replace(loreNoAmt, "");
                    return Integer.parseInt(blocksAsString.replace(",", ""));
                }
            }
        }
        // 0 if we cant find blocks in the lore
        return 0;
    }

    public static void incrementBlocksBroken(ItemStack item) {
        // Update count
        int broken = getBlocksBroken(item);
        broken++;
        // Update lore
        ArrayList<String> newLore = new ArrayList<>();
        String loreNoAmt = loreFormat.replace("%amt%", "");
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            for (String line : item.getItemMeta().getLore()) {
                if (!line.contains(loreNoAmt)) {
                    newLore.add(line);
                }
            }
        } else {
            newLore.add("");
            newLore.add("");
        }
        DecimalFormat formatter = new DecimalFormat("#.##");
        formatter.setGroupingUsed(true);
        formatter.setGroupingSize(3);
        newLore.add(loreFormat.replaceAll("%amt%", formatter.format(broken)));
        System.out.println("Updating " + formatter.format(broken));
        ItemMeta meta = item.getItemMeta();
        meta.setLore(newLore);
        item.setItemMeta(meta);
    }

}
