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

public class GlacialStaffListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<Player, Boolean> cooldownMap;

    public GlacialStaffListener(RemiliaUtilities plugin) {
        this.plugin = plugin;
        this.cooldownMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidGlacialStaff(item)) {
                if (cooldownMap.getOrDefault(player, false)) {
                    return;
                }

                Location spawnLoc = getSpawnLocation(player);

                BukkitRunnable glacialProjectileTask = new BukkitRunnable() {
                    int ticks = 0;
                    boolean hitEntity = false;

                    @Override
                    public void run() {
                        if (ticks >= 100 || hitEntity) { // 5 seconds at 20 ticks per second
                            cancel();
                            return;
                        }

                        if (spawnLoc.getWorld() != null) {
                            for (org.bukkit.entity.Entity nearbyEntity : spawnLoc.getWorld().getNearbyEntities(spawnLoc, 0.5, 0.5, 0.5)) {
                                if (nearbyEntity instanceof LivingEntity livingEntity) {
                                    if (!livingEntity.equals(player)) {
                                        double damage = 15.0; // Apply 15 hearts of damage to the hit entity
                                        livingEntity.damage(damage);

                                        // Damage entities within 3 blocks of the hit entity
                                        for (org.bukkit.entity.Entity nearbyEntity1 : livingEntity.getNearbyEntities(3, 3, 3)) {
                                            if (nearbyEntity1 instanceof LivingEntity nearbyLivingEntity) {
                                                nearbyLivingEntity.damage(damage);
                                            }
                                        }

                                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0)); // Slowness I for 5 seconds

                                        // Drop XP if the entity is dead
                                        if (livingEntity.isDead()) {
                                            // Drop XP as if it's a mob
                                            int xpToDrop = calculateXpToDrop(livingEntity);
                                            livingEntity.getWorld().spawn(livingEntity.getLocation(), ExperienceOrb.class).setExperience(xpToDrop);
                                        }

                                        hitEntity = true;
                                        cancel(); // Stop the task when colliding with a living entity
                                        return;
                                    }
                                }
                            }

                            if (spawnLoc.getBlock().getType() != Material.AIR && spawnLoc.getBlock().getType() != Material.WATER) {
                                // Damage entities within 3 blocks if hit a block
                                for (LivingEntity nearbyLivingEntity : spawnLoc.getWorld().getNearbyEntities(spawnLoc, 3, 3, 3)
                                        .stream()
                                        .filter(entity -> entity instanceof LivingEntity)
                                        .map(entity -> (LivingEntity) entity)
                                        .toArray(LivingEntity[]::new)) {
                                    if (!nearbyLivingEntity.equals(player)) {
                                        double damage = 15.0; // Apply 15 hearts of damage to entities within the radius
                                        nearbyLivingEntity.damage(damage);
                                    }
                                }


                                cancel();
                                return;
                            }

                            Objects.requireNonNull(spawnLoc.getWorld()).spawnParticle(Particle.BLOCK_CRACK, spawnLoc, 10, 0.1, 0.1, 0.1, 0, Material.PACKED_ICE.createBlockData());
                            spawnLoc.add(spawnLoc.getDirection().normalize().multiply(1.4)); // Multiply velocity by 1.4x
                            ticks++;
                        }
                    }
                };

                glacialProjectileTask.runTaskTimer(plugin, 0L, 1L); // Run the task every tick

                // Set cooldown
                cooldownMap.put(player, true);
                Bukkit.getScheduler().runTaskLater(plugin, () -> cooldownMap.put(player, false), 12); // 12 ticks = 0.6 seconds
            }
        }
    }

    private boolean isValidGlacialStaff(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Run! Take cover!");
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
