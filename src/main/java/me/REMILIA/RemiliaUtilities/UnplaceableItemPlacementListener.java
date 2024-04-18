package me.REMILIA.RemiliaUtilities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class UnplaceableItemPlacementListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        // Check if the item being interacted with is unplaceable
        if (isUnplaceableItem(item)) {
            // Cancel the event to prevent placement
            event.setCancelled(true);
        }
    }

    // Method to check if the item is unplaceable
    private boolean isUnplaceableItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        String displayName = Objects.requireNonNull(item.getItemMeta()).getDisplayName();

        // Check if the item display name matches any of the unplaceable items
        return  displayName.contains("APHE Missile") ||
                displayName.contains("Teleportation Core") ||
                displayName.contains("Ice Core") ||
                displayName.contains("Dense Obsidian") ||
                displayName.contains("Condensed Ice") ||
                displayName.contains("Stable Pearl") ||
                displayName.contains("Consolidated Ice") ||
                displayName.contains("Activated Obsidian") ||
                displayName.contains("Ender Eye") ||
                displayName.contains("Heavy Pearl") ||
                displayName.contains("Flattened Pearl") ||
                displayName.contains("Unstable Pearl") ||
                displayName.contains("Compressed Ice") ||
                displayName.contains("Ice Accelerator") ||
                displayName.contains("Concentrated Explosives") ||
                displayName.contains("APHE") ||
                displayName.contains("Power Core") ||
                displayName.contains("Explosive Core") ||
                displayName.contains("Protection Core") ||
                displayName.contains("Magical Container") ||
                displayName.contains("Lapis Crystal") ||
                displayName.contains("Redstone Crystal") ||
                displayName.contains("Jade") ||
                displayName.contains("Moonstone") ||
                displayName.contains("Onyx") ||
                displayName.contains("Rhodonite") ||
                displayName.contains("Amethyst") ||
                displayName.contains("Tanzanite") ||
                displayName.contains("Sapphire") ||
                displayName.contains("Kyanite") ||
                displayName.contains("Turquoise") ||
                displayName.contains("Emerald") ||
                displayName.contains("Peridot") ||
                displayName.contains("Topaz") ||
                displayName.contains("Citrine") ||
                displayName.contains("Garnet");
    }
}
