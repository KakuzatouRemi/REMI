package me.REMILIA.RemiliaUtilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class APHEMissileListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<Player, Long> cooldownMap;

    public APHEMissileListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.cooldownMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidAPHEMissile(item)) {
                long currentTime = System.currentTimeMillis();
                long lastCastTime = cooldownMap.getOrDefault(player, 0L);
                long timeSinceLastCast = currentTime - lastCastTime;

                // 1 minute in milliseconds (60 * 1000) default setting, 10 sec
                long cooldownDuration = 10*1000;
                if (timeSinceLastCast < cooldownDuration) {
                    // Player is still on cooldown
                    long remainingCooldown = cooldownDuration - timeSinceLastCast;
                    long remainingCooldownSeconds = remainingCooldown / 1000;

                    player.sendMessage("The APHE Missile is on cooldown. Remaining cooldown: " + remainingCooldownSeconds + " seconds.");
                    return;
                }

                // Player is not on cooldown, proceed with casting

                // Consume one item
                int amount = item.getAmount();
                if (amount > 1) {
                    item.setAmount(amount - 1);
                } else {
                    player.getInventory().remove(item);
                }

                Location spawnLoc = getSpawnLocation(player);

                Fireball fireball = player.launchProjectile(Fireball.class);
                fireball.setIsIncendiary(false); // Ensure the fireball doesn't set blocks on fire
                fireball.setYield(2); // Set the size of the explosion (2 is default size)

                // Set velocity to make the fireball travel faster
                Vector direction = player.getLocation().getDirection().normalize();
                fireball.setVelocity(direction.multiply(2)); // Adjust the multiplier as needed for faster or slower travel speed

                // Set cooldown
                cooldownMap.put(player, currentTime);
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Fireball fireball) {
            Location explosionLoc = fireball.getLocation();

            // Prevent block explosions
            event.blockList().clear();

            // Simulate the explosion of an end crystal without affecting blocks
            Objects.requireNonNull(explosionLoc.getWorld()).createExplosion(explosionLoc.getX(), explosionLoc.getY(), explosionLoc.getZ(), 6.0f, false, false);

            // Spawn particles for the explosion
            Objects.requireNonNull(explosionLoc.getWorld()).spawnParticle(Particle.EXPLOSION_LARGE, explosionLoc, 1);

            // Cause damage within a radius of 6 blocks on the point of impact
            explosionLoc.getWorld().getNearbyEntities(explosionLoc, 6, 6, 6).forEach(entity -> {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(30); // Adjust damage as needed
                }
            });
        }
    }

    private boolean isValidAPHEMissile(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("DO NOT USE INDOORS");
    }

    private Location getSpawnLocation(Player player) {
        return player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize());
    }
}
