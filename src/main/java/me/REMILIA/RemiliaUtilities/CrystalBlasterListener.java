package me.REMILIA.RemiliaUtilities;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class CrystalBlasterListener implements Listener {

    private final JavaPlugin plugin;

    public CrystalBlasterListener(RemiliaUtilities plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidCrystalBlaster(item)) {
                if (hasCrystalAmmo(player)) {
                    consumeCrystalAmmo(player);
                    Location spawnLoc = getSpawnLocation(player);

                    BukkitRunnable crystalProjectileTask = new BukkitRunnable() {
                        int ticks = 0;

                        @Override
                        public void run() {
                            if (ticks >= 240) { // 12 seconds at 20 ticks per second
                                cancel();
                                return;
                            }

                            if (spawnLoc.getWorld() != null) {
                                for (Entity nearbyEntity : spawnLoc.getWorld().getNearbyEntities(spawnLoc, 0.5, 0.5, 0.5)) {
                                    if (nearbyEntity instanceof LivingEntity livingEntity) {
                                        if (!livingEntity.equals(player)) {
                                            double damage = 20.0; // Apply 8 hearts of damage to all living entities within range
                                            livingEntity.damage(damage);
                                            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 0)); // Glowing for 5 seconds

                                            cancel(); // Stop the task when colliding with a living entity
                                            return;
                                        }
                                    }
                                }
                            }

                            if (spawnLoc.getBlock().getType() != Material.AIR && spawnLoc.getBlock().getType() != Material.GLOWSTONE) {
                                cancel();
                                return;
                            }

                            Objects.requireNonNull(spawnLoc.getWorld()).spawnParticle(Particle.BLOCK_CRACK, spawnLoc, 10, 0.1, 0.1, 0.1, 0, Material.GLOWSTONE.createBlockData());
                            spawnLoc.add(spawnLoc.getDirection().normalize().multiply(4)); // Multiply velocity by 1.4x

                            ticks++;
                        }
                    };

                    crystalProjectileTask.runTaskTimer(plugin, 0L, 1L); // Run the task every tick
                } else {
                    player.sendMessage("You need crystal blocks to use the Crystal Blaster!");
                }
            }
        }
    }

    private boolean isValidCrystalBlaster(ItemStack item) {
        return item != null && item.getItemMeta() != null && item.getItemMeta().hasLore() && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("This ain't Pixel Gun!");
    }

    private boolean hasCrystalAmmo(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (isValidCrystal(item)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCrystal(ItemStack item) {
        if (item == null || !item.hasItemMeta() || !Objects.requireNonNull(item.getItemMeta()).hasLore()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        String displayName = ChatColor.stripColor(meta.getDisplayName()); // Get the stripped display name

        // Check if the lore contains "It's shining" and if the display name contains specific crystal names
        return lore != null && lore.contains("It's shining") && (
                displayName.contains("Jade Crystal") ||
                        displayName.contains("Moonstone Crystal") ||
                        displayName.contains("Onyx Crystal") ||
                        displayName.contains("Rhodonite Crystal") ||
                        displayName.contains("Amethyst Crystal") ||
                        displayName.contains("Tanzanite Crystal") ||
                        displayName.contains("Sapphire Crystal") ||
                        displayName.contains("Kyanite Crystal") ||
                        displayName.contains("Turquoise Crystal") ||
                        displayName.contains("Emerald Crystal") ||
                        displayName.contains("Peridot Crystal") ||
                        displayName.contains("Topaz Crystal") ||
                        displayName.contains("Citrine Crystal") ||
                        displayName.contains("Garnet Crystal")
        );
    }

    private void consumeCrystalAmmo(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (isValidCrystal(item)) {
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1); // Decrease stack size by one
                } else {
                    player.getInventory().removeItem(item); // Remove the item if it's the last one in the stack
                }
                return;
            }
        }
    }

    private Location getSpawnLocation(Player player) {
        Location eyeLoc = player.getEyeLocation();
        return eyeLoc.add(eyeLoc.getDirection().normalize());
    }
}
