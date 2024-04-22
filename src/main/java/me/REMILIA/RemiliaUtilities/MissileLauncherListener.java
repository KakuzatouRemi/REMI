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

public class MissileLauncherListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<Player, Long> cooldownMap;

    public MissileLauncherListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.cooldownMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidMissileLauncher(item)) {
                long currentTime = System.currentTimeMillis();
                long lastCastTime = cooldownMap.getOrDefault(player, 0L);
                long timeSinceLastCast = currentTime - lastCastTime;

                // 3 seconds cooldown
                long cooldownDuration = 3 * 1000;
                if (timeSinceLastCast < cooldownDuration) {
                    long remainingCooldown = cooldownDuration - timeSinceLastCast;
                    long remainingCooldownSeconds = remainingCooldown / 1000;

                    player.sendMessage("Missile Launcher is on cooldown. Remaining cooldown: " + remainingCooldownSeconds + " seconds.");
                    return;
                }

                // Check if the player has APHEMissile in hand
                if (hasAPHEMissile(player)) {
                    consumeAPHEMissile(player);

                    // Launch the fireball
                    Location spawnLoc = getSpawnLocation(player);
                    Fireball fireball = player.launchProjectile(Fireball.class);
                    fireball.setIsIncendiary(false);
                    fireball.setYield(2);
                    Vector direction = player.getLocation().getDirection().normalize();
                    fireball.setVelocity(direction.multiply(2));

                    // Set cooldown
                    cooldownMap.put(player, currentTime);
                } else {
                    player.sendMessage("You need APHE Missiles to use the Missile Launcher!");
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof Fireball fireball) {
            Location explosionLoc = fireball.getLocation();

            event.blockList().clear();

            Objects.requireNonNull(explosionLoc.getWorld()).createExplosion(explosionLoc.getX(), explosionLoc.getY(), explosionLoc.getZ(), 6.0f, false, false);
            Objects.requireNonNull(explosionLoc.getWorld()).spawnParticle(Particle.EXPLOSION_LARGE, explosionLoc, 1);

            explosionLoc.getWorld().getNearbyEntities(explosionLoc, 6, 6, 6).forEach(entity -> {
                if (entity instanceof LivingEntity) {
                    ((LivingEntity) entity).damage(30);
                }
            });
        }
    }

    private boolean isValidMissileLauncher(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("FREEEEBIRD!!!");
    }

    private boolean hasAPHEMissile(Player player) {
        ItemStack[] inventory = player.getInventory().getContents();
        for (ItemStack itemStack : inventory) {
            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().hasLore() && Objects.requireNonNull(itemStack.getItemMeta().getLore()).contains("DO NOT USE INDOORS")) {
                return true;
            }
        }
        return false;
    }

    private void consumeAPHEMissile(Player player) {
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().hasLore() && Objects.requireNonNull(itemStack.getItemMeta().getLore()).contains("DO NOT USE INDOORS")) {
                int amount = itemStack.getAmount();
                if (amount > 1) {
                    itemStack.setAmount(amount - 1);
                } else {
                    player.getInventory().remove(itemStack);
                }
                return;
            }
        }
    }

    private Location getSpawnLocation(Player player) {
        return player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize());
    }
}
