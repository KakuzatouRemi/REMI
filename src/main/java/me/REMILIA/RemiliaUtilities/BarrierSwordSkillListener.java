package me.REMILIA.RemiliaUtilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BarrierSwordSkillListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<Player, Long> cooldownMap = new HashMap<>();

    public BarrierSwordSkillListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT")) {
            if (isValidBarrierSword(item)) {
                Long lastUsedTime = cooldownMap.get(player);
                long currentTime = System.currentTimeMillis();

                // 75 seconds in milliseconds
                long cooldownMillis = 75000;
                if (lastUsedTime != null && currentTime - lastUsedTime < cooldownMillis) {
                    // Still on cooldown
                    long remainingCooldown = cooldownMillis - (currentTime - lastUsedTime);
                    player.sendMessage("Barrier Sword is Recharging. Remaining Cooldown: " + (remainingCooldown / 1000) + " seconds");
                    return;
                }

                // Apply invulnerability for 15 seconds
                player.setInvulnerable(true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                player.sendMessage("Skill Active!");

                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    player.setInvulnerable(false);
                    player.removePotionEffect(PotionEffectType.GLOWING);
                    player.sendMessage("Skill Inactive!");
                }, 300); // 300 ticks = 15 seconds

                // Set cooldown
                cooldownMap.put(player, currentTime);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.isInvulnerable()) {
                event.setCancelled(true);
            }
        }
    }

    private boolean isValidBarrierSword(ItemStack item) {
        return item != null && item.getType() == Material.IRON_SWORD &&
                item.hasItemMeta() && Objects.requireNonNull(item.getItemMeta()).hasLore() &&
                item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains("Basic Defensive Spell");
    }
}
