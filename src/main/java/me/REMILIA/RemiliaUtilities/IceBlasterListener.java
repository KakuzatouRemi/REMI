package me.REMILIA.RemiliaUtilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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
import org.bukkit.entity.ExperienceOrb;

import java.util.Objects;

public class IceBlasterListener implements Listener {

    private final JavaPlugin plugin;

    public IceBlasterListener(RemiliaUtilities plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidIceBlaster(item)) {
                if (hasIceAmmo(player)) {
                    consumeIceAmmo(player);
                    Location spawnLoc = getSpawnLocation(player);

                    BukkitRunnable iceProjectileTask = new BukkitRunnable() {
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
                                            double damage = 8.0; // Apply 8 hearts of damage to all living entities within range
                                            livingEntity.damage(damage);
                                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0)); // Slowness I for 5 seconds

                                            // Drop XP if the entity is dead
                                            if (livingEntity.isDead()) {
                                                // Check if the entity is a player or not
                                                if (livingEntity instanceof Player) {
                                                    // If it's a player, we need to subtract XP
                                                    // You can implement this according to your XP management system
                                                } else {
                                                    // If it's not a player, drop XP as if it's a mob
                                                    int xpToDrop = calculateXpToDrop(livingEntity);
                                                    livingEntity.getWorld().spawn(livingEntity.getLocation(), ExperienceOrb.class).setExperience(xpToDrop);
                                                }
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

                            Objects.requireNonNull(spawnLoc.getWorld()).spawnParticle(Particle.BLOCK_CRACK, spawnLoc, 10, 0.1, 0.1, 0.1, 0, Material.FROSTED_ICE.createBlockData());
                            spawnLoc.add(spawnLoc.getDirection().normalize());
                            ticks++;
                        }
                    };

                    iceProjectileTask.runTaskTimer(plugin, 0L, 1L); // Run the task every tick
                } else {
                    player.sendMessage("You need ice blocks to use the Ice Blaster!");
                }
            }
        }
    }

    private boolean isValidIceBlaster(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Don't snipe me pls");
    }

    private boolean hasIceAmmo(Player player) {
        return player.getInventory().contains(Material.ICE) || player.getInventory().contains(Material.PACKED_ICE) || player.getInventory().contains(Material.BLUE_ICE);
    }

    private void consumeIceAmmo(Player player) {
        if (player.getInventory().contains(Material.ICE)) {
            player.getInventory().removeItem(new ItemStack(Material.ICE, 1));
        } else if (player.getInventory().contains(Material.PACKED_ICE)) {
            player.getInventory().removeItem(new ItemStack(Material.PACKED_ICE, 1));
        } else if (player.getInventory().contains(Material.BLUE_ICE)) {
            player.getInventory().removeItem(new ItemStack(Material.BLUE_ICE, 1));
        }
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
