package me.REMILIA.RemiliaUtilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IcicleStaffListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<Player, Boolean> cooldownMap;

    public IcicleStaffListener(RemiliaUtilities plugin) {
        this.plugin = plugin;
        this.cooldownMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidIcicleStaff(item)) {
                if (hasIceAmmo(player)) {
                    if (cooldownMap.getOrDefault(player, false)) {
                        return;
                    }

                    Location spawnLoc = getSpawnLocation(player);

                    BukkitRunnable icicleProjectileTask = new BukkitRunnable() {
                        int ticks = 0;

                        @Override
                        public void run() {
                            if (ticks >= 100) { // 5 seconds at 20 ticks per second
                                cancel();
                                return;
                            }

                            if (spawnLoc.getWorld() != null) {
                                for (org.bukkit.entity.Entity nearbyEntity : spawnLoc.getWorld().getNearbyEntities(spawnLoc, 0.5, 0.5, 0.5)) {
                                    if (nearbyEntity instanceof LivingEntity livingEntity) {
                                        if (!livingEntity.equals(player)) {
                                            double damage = 10.0; // Apply 10 hearts of damage to all living entities within range
                                            livingEntity.damage(damage);
                                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0)); // Slowness I for 5 seconds

                                            // Drop XP if the entity is dead
                                            if (livingEntity.isDead()) {
                                                // Drop XP as if it's a mob
                                                int xpToDrop = calculateXpToDrop(livingEntity);
                                                livingEntity.getWorld().spawn(livingEntity.getLocation(), ExperienceOrb.class).setExperience(xpToDrop);
                                            }

                                            cancel(); // Stop the task when colliding with a living entity
                                            return;
                                        }
                                    }
                                }
                            }

                            if (spawnLoc.getBlock().getType() != Material.AIR && spawnLoc.getBlock().getType() != Material.WATER) {
                                cancel();
                                return;
                            }

                            Objects.requireNonNull(spawnLoc.getWorld()).spawnParticle(Particle.BLOCK_CRACK, spawnLoc, 10, 0.1, 0.1, 0.1, 0, Material.PACKED_ICE.createBlockData());
                            spawnLoc.add(spawnLoc.getDirection().normalize().multiply(1.2)); // Multiply velocity by 1.2x
                            ticks++;
                        }
                    };

                    icicleProjectileTask.runTaskTimer(plugin, 0L, 1L); // Run the task every tick

                    // Set cooldown
                    cooldownMap.put(player, true);
                    Bukkit.getScheduler().runTaskLater(plugin, () -> cooldownMap.put(player, false), 6); // 6 ticks = 0.3 seconds
                } else {
                    player.sendMessage("You need ice blocks to use the Icicle Staff!");
                }
            }
        }
    }

    private boolean isValidIcicleStaff(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Don't freeze me pls");
    }

    private boolean hasIceAmmo(Player player) {
        return player.getInventory().contains(Material.ICE) || player.getInventory().contains(Material.PACKED_ICE) || player.getInventory().contains(Material.BLUE_ICE);
    }

    private Location getSpawnLocation(Player player) {
        Location eyeLoc = player.getEyeLocation();
        return eyeLoc.add(eyeLoc.getDirection().normalize());
    }

    // Method to calculate XP to drop based on mob's XP yield
    private int calculateXpToDrop(LivingEntity entity) {
        // You can implement this method according to your XP management system
        // For example, you can use entity's type to determine how much XP to drop
        // and take into account any plugins that modify XP yields
        return 0; // Replace with your implementation
    }
}
