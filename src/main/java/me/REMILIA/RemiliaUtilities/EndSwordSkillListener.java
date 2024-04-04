package me.REMILIA.RemiliaUtilities;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EndSwordSkillListener implements Listener {
    private final JavaPlugin plugin;
    private final long cooldownMillis = 20000; // 20 seconds in milliseconds
    private final int teleportationRange = 12; // Teleportation range in blocks
    private final SlimefunItemStack endSwordMaterial;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public EndSwordSkillListener(JavaPlugin plugin, SlimefunItemStack endSwordMaterial) {
        this.plugin = plugin;
        this.endSwordMaterial = endSwordMaterial;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return; // Not a right-click event
        }

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains("It's screaming at you")) {
            long remainingCooldown = isOnCooldown(player);
            if (remainingCooldown > 0) {
                player.sendMessage("Skill on cooldown. Wait " + (remainingCooldown / 1000) + " seconds");
                return;
            }

            // Attempt to teleport the player forward
            if (canTeleport(player)) {
                // Apply cooldown only if teleportation is successful
                setCooldown(player, System.currentTimeMillis() + cooldownMillis);

                // Play enderman teleport sound
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);

                // Teleport the player forward
                teleportPlayerForward(player);

            } else {
                player.sendMessage("There are obstacles in the way.");
            }
        }
    }

    private boolean isEndSword(ItemStack item) {
        // Check if the item has the custom lore indicating it's the End Sword
        return item.getItemMeta().getLore().contains("It's screaming at you");
    }

    private void teleportPlayerForward(Player player) {
        Location location = player.getLocation();
        Vector direction = location.getDirection().normalize().multiply(12);
        Location destination = location.add(direction);

        // Teleport the player after a short delay to avoid issues with other plugins
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(destination);
            }
        }.runTaskLater(plugin, 1);
    }

    private long isOnCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            long cooldownEndTime = cooldowns.get(player.getUniqueId());
            long remainingTime = cooldownEndTime - System.currentTimeMillis();
            return Math.max(remainingTime, 0); // Ensure remaining time is non-negative
        }
        return 0; // Not on cooldown
    }

    private void setCooldown(Player player, long cooldownEndTime) {
        cooldowns.put(player.getUniqueId(), cooldownEndTime);
    }

    private boolean canTeleport(Player player) {
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection().normalize();

        for (int i = 0; i < teleportationRange; i++) {
            Location checkLocation = playerLocation.add(direction);
            Block block = checkLocation.getBlock();

            if (!block.isPassable()) {
                return false;
            }
        }

        return true;
    }
}
