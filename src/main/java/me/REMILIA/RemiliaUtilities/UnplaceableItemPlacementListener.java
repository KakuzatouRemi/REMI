package me.REMILIA.RemiliaUtilities;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class UnplaceableItemPlacementListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null) {
            if (!isUnplaceableItem(item)) {
                // Allow placement if the item is not unplaceable
                return;
            }
            // Cancel placement if the item is a player head
            if (item.getType() == Material.PLAYER_HEAD) {
                event.setCancelled(true);
            }
        }
    }

    private boolean isUnplaceableItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            String displayName = meta.getDisplayName();
            String[] unplaceableNames = {
                    "APHE Missile", "Teleportation Core", "Ice Core", "Dense Obsidian", "Condensed Ice",
                    "Stable Pearl", "Consolidated Ice", "Activated Obsidian", "Ender Eye", "Heavy Pearl",
                    "Flattened Pearl", "Unstable Pearl", "Compressed Ice", "Ice Accelerator", "Linear Accelerator",
                    "Concentrated Explosives", "APHE", "Launcher Tube", "Missile Launcher", "Power Core",
                    "Explosive Core", "Protection Core", "Magical Container", "Lapis Crystal", "Redstone Crystal",
                    "Jade", "Moonstone", "Onyx", "Rhodonite", "Amethyst", "Tanzanite", "Sapphire", "Kyanite",
                    "Turquoise", "Emerald", "Peridot", "Topaz", "Citrine", "Garnet", "Jade Cluster",
                    "Moonstone Cluster", "Onyx Cluster", "Rhodonite Cluster", "Amethyst Cluster", "Tanzanite Cluster",
                    "Sapphire Cluster", "Kyanite Cluster", "Turquoise Cluster", "Emerald Cluster", "Peridot Cluster",
                    "Topaz Cluster", "Citrine Cluster", "Garnet Cluster", "Jade Crystal", "Moonstone Crystal",
                    "Onyx Crystal", "Rhodonite Crystal", "Amethyst Crystal", "Tanzanite Crystal", "Sapphire Crystal",
                    "Kyanite Crystal", "Turquoise Crystal", "Emerald Crystal", "Peridot Crystal", "Topaz Crystal",
                    "Citrine Crystal", "Garnet Crystal"
            };
            for (String name : unplaceableNames) {
                if (displayName.contains(name)) return true;
            }
        }
        return false;
    }
}
