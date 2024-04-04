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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AugmentedEndSwordSkillListener implements Listener {
    private final JavaPlugin plugin;
    private final long cooldownMillis = 5000; // 5 seconds in milliseconds
    private final int teleportationRange = 20; // Teleportation range in blocks
    private final SlimefunItemStack augmentedEndSwordMaterial;
    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public AugmentedEndSwordSkillListener(JavaPlugin plugin, SlimefunItemStack augmentedEndSwordMaterial) {
        this.plugin = plugin;
        this.augmentedEndSwordMaterial = augmentedEndSwordMaterial;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return; // Not a right-click event
        }

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (isAugmentedEndSword(item)) {
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

                // Apply resistance potion effect
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 60, 0));

            } else {
                player.sendMessage("There are obstacles in the way.");
            }
        }
    }

    private boolean isAugmentedEndSword(ItemStack item) {
        // Check if the item has lore and contains the custom lore indicating it's the Augmented End Sword
        if (item != null && item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return item.getItemMeta().getLore().contains("It's staring at you");
        }
        return false;
    }

    private void teleportPlayerForward(Player player) {
        Location location = player.getLocation();
        Vector direction = location.getDirection().normalize().multiply(teleportationRange);
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
