package me.REMILIA.RemiliaUtilities;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.minecraft.world.item.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

//For fixing new cannot find symbol stuff
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.*;
import org.bukkit.event.Listener;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class RemiliaUtilities extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

        cfg.getBoolean("options.auto-update");// You could start an Auto-Updater for example

        //----------HELLO WORLD!----------//

        getLogger().info("♥-----------------------♥");
        getLogger().info("|  Remilia's Utilities  |");
        getLogger().info("| Made by KakuzatouRemi |");
        getLogger().info("|                       |");
        getLogger().info("|    I'm not a demon,   |");
        getLogger().info("|      I'm a fairy      |");
        getLogger().info("♥-----------------------♥");

        //----------CATEGORIES BELOW----------//

        // Define the main category group
        final NestedItemGroup RU = new NestedItemGroup(
                new NamespacedKey(this, "RU"), // Key for the group
                new CustomItemStack(Material.JIGSAW, "&5Remilia's Utilities") // Item representing the group
        );
        // Define a sub-category group, belonging to the main category RU
        final SubItemGroup RU_INFO = new SubItemGroup(
                new NamespacedKey(this, "RU_INFO"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.LEGACY_BOOK_AND_QUILL, "&bInfo") // Item representing the sub-group
        );
        // Define a sub-category group, belonging to the main category RU
        final SubItemGroup RU_MATERIALS = new SubItemGroup(
                new NamespacedKey(this, "RU_RES"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.END_CRYSTAL, "&bMaterials") // Item representing the sub-group
        );
        // Define another sub-category group, belonging to the main category RU
        final SubItemGroup RU_TOOLS = new SubItemGroup(
                new NamespacedKey(this, "RU_TOOLS"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.DIAMOND_SWORD, "&6Tools") // Item representing the sub-group
        );
        /* UNUSED YET
        // Define the Enchants subgroup
        final SubItemGroup RU_ENCHANTS = new SubItemGroup(
                new NamespacedKey(this, "RU_ENCHANTS"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.ENCHANTED_BOOK, "&6Enchants") // Item representing the sub-group
        );
        */
        /* UNUSED YET
        // Define another sub-category group, belonging to the main category RU
        final SubItemGroup RU_MACHINES = new SubItemGroup(
                new NamespacedKey(this, "RU_MACHINES "), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.BLAST_FURNACE, "&aMachines") // Item representing the sub-group
        );
        */

        //----------INFORMATION----------//

        SlimefunItemStack Note = new SlimefunItemStack(
                "NOTE", // Item ID
                Material.PAPER, // Material
                "&6Note", // Display name
                "&7Needs a lot of grinding", "",
                "&4I'm not a demon! -Remi" // Multiline lore
        );
        ItemStack[] NoteRecipe = {null, null, null, null, null, null, null, null, null};
        SlimefunItem NoteItem = new SlimefunItem(RU_INFO, Note, RecipeType.NULL, NoteRecipe);
        NoteItem.register(this);

        //----------ITEMS BELOW----------//

        SlimefunItemStack Binder = new SlimefunItemStack("BINDER", Material.SLIME_BALL, "&eBinder", "&bSticky");
        ItemStack[] binderRecipe = {new ItemStack(Material.SLIME_BLOCK, 8), null, null, null, null, null, null, null, null};
        SlimefunItem BinderItem = new SlimefunItem(RU_MATERIALS, Binder, RecipeType.COMPRESSOR, binderRecipe);
        BinderItem.register(this);

        //HEHE//

        SlimefunItemStack Crossguard = new SlimefunItemStack("CROSSGUARD", Material.IRON_INGOT, "&bCrossguard", "&cIt cares about you");
        ItemStack[] crossguardRecipe = {
                null, null, null,
                new ItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT), new ItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT), new ItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT),
                null, null, null
        };
        SlimefunItem crossguardItem = new SlimefunItem(RU_MATERIALS, Crossguard, RecipeType.ARMOR_FORGE, crossguardRecipe);
        crossguardItem.register(this);

        //HEHE//

        SlimefunItemStack ReinforcedHandle = new SlimefunItemStack("REINFORCED_HANDLE", Material.BLAZE_ROD, "&cReinforced Handle", "&cNot meant for doors");
        ItemStack[] reinforcedHandleRecipe = {
                new ItemStack(SlimefunItems.COMPRESSED_CARBON), null, new ItemStack(SlimefunItems.COMPRESSED_CARBON),
                Crossguard, Binder, Crossguard,
                new ItemStack(SlimefunItems.COMPRESSED_CARBON), new ItemStack(Material.BLAZE_ROD), new ItemStack(SlimefunItems.COMPRESSED_CARBON)
        };
        SlimefunItem reinforcedHandleItem = new SlimefunItem(RU_MATERIALS, ReinforcedHandle, RecipeType.ARMOR_FORGE, reinforcedHandleRecipe);
        reinforcedHandleItem.register(this);

        //HEHE//

        SlimefunItemStack EnderEye = new SlimefunItemStack("ENDER_EYE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGFhOGZjOGRlNjQxN2I0OGQ0OGM4MGI0NDNjZjUzMjZlM2Q5ZGE0ZGJlOWIyNWZjZDQ5NTQ5ZDk2MTY4ZmMwIn19fQ==", "&2Ender Eye", "&7Don't look at it");
        ItemStack[] EnderEyerecipe = {new ItemStack(Material.ENDER_EYE, 16), null, null, null, null, null, null, null, null};
        SlimefunItem EnderEyeitem = new SlimefunItem(RU_MATERIALS, EnderEye, RecipeType.COMPRESSOR, EnderEyerecipe);
        EnderEyeitem.register(this);

        //HEHE//

        SlimefunItemStack FlattenedPearl = new SlimefunItemStack(
                "FLATTENED_PEARL",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjdlNmFkNGI3OGJkMzUxODdhNjU3MDg5OTE4MTdlMjY2OTlmOTAyYzc3MzhjODFjMzc1ODU5ZDcyNzUzOSJ9fX0=",
                "&aFlattened Pearl",
                "&7So flat..."
        );
        ItemStack[] FlattenedPearlRecipe = {new ItemStack(Material.ENDER_PEARL, 16), null, null, null, null, null, null, null, null};
        SlimefunItem FlattenedPearlItem = new SlimefunItem(RU_MATERIALS, FlattenedPearl, RecipeType.COMPRESSOR, FlattenedPearlRecipe);
        FlattenedPearlItem.register(this);

        //HEHE//

        SlimefunItemStack UnstablePearl = new SlimefunItemStack("UNSTABLE_PEARL", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRkNDhhYzdlNjViOTE0Njc5MDRmMmY0ZDE1NDU1Njc3NmZmYTljZDJkNTI2YjEyYTA0OTliYjU5Y2M2NTZjNyJ9fX0=", "&aUnstable Pearl", "&7It won't hurt you... Probably");
        ItemStack[] UnstablePearlrecipe = {
                FlattenedPearl, FlattenedPearl, FlattenedPearl,
                FlattenedPearl, FlattenedPearl, FlattenedPearl,
                FlattenedPearl, FlattenedPearl, FlattenedPearl
        };
        SlimefunItem UnstablePearlitem = new SlimefunItem(RU_MATERIALS, UnstablePearl, RecipeType.ENHANCED_CRAFTING_TABLE, UnstablePearlrecipe);
        UnstablePearlitem.register(this);

        //HEHE//

        SlimefunItemStack StablePearl = new SlimefunItemStack("STABLE_PEARL", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWNiN2MyMWNjNDNkYzE3Njc4ZWU2ZjE2NTkxZmZhYWIxZjYzN2MzN2Y0ZjZiYmQ4Y2VhNDk3NDUxZDc2ZGI2ZCJ9fX0=", "&aStable Pearl", "&7It won't hurt you");
        ItemStack[] StablePearlrecipe = {
                UnstablePearl, UnstablePearl, UnstablePearl,
                UnstablePearl, EnderEye, UnstablePearl,
                UnstablePearl, UnstablePearl, UnstablePearl
        };
        SlimefunItem StablePearlitem = new SlimefunItem(RU_MATERIALS, StablePearl, RecipeType.ENHANCED_CRAFTING_TABLE, StablePearlrecipe);
        StablePearlitem.register(this);

        //HEHE//

        SlimefunItemStack HeavyPearl = new SlimefunItemStack("HEAVY_PEARL", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmRiZDRlNWQzZDljMDVhMDM2ZmI2MmU2ZTcwZmFmOWU2Zjk4ZDI5NGY5ZDAwNjc4MWMxNDRjOWYxNWI4NzcxNSJ9fX0=", "&bHeavy Pearl", "&7A pearl of substantial weight");
        ItemStack[] HeavyPearlrecipe = {
                null, StablePearl, null,
                StablePearl, EnderEye, StablePearl,
                null, StablePearl, null
        };
        SlimefunItem HeavyPearlitem = new SlimefunItem(RU_MATERIALS, HeavyPearl, RecipeType.ENHANCED_CRAFTING_TABLE, HeavyPearlrecipe);
        HeavyPearlitem.register(this);

        //HEHE//

        SlimefunItemStack CompressedIce = new SlimefunItemStack(
                "COMPRESSED_ICE",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmE4M2QxYWFjMDY2ZGZkN2Q2NjhhZjFmNmIyZTUyMDE1MmFlYmI2MjQ1MjRhMGY5OWQ1MDkzNWJhM2Q0ZTIyYSJ9fX0=",
                "&bCompressed Ice",
                "&bGot headache?"
        );
        ItemStack[] CompressedIceRecipe = new ItemStack[]{
                new ItemStack(Material.PACKED_ICE, 64), null, null, null, null, null, null, null, null};
        SlimefunItem CompressedIceItem = new SlimefunItem(RU_MATERIALS, CompressedIce, RecipeType.COMPRESSOR, CompressedIceRecipe);
        CompressedIceItem.register(this);

        //HEHE//

        SlimefunItemStack CondensedIce = new SlimefunItemStack("CONDENSED_ICE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZlNDI5YzYwOTMyZWJjMzY2ZTc5MWE0MmUxODZhZjg4OGRlMDhlNWQ4ZWI4YWM2ZjViNmY0ZDQ0MGRiNDg2YyJ9fX0=", "&bCondensed Ice", "&bMight cause frostbites");
        ItemStack[] CondensedIceRecipe = new ItemStack[]{
                CompressedIce, CompressedIce, CompressedIce, CompressedIce, CompressedIce, CompressedIce, CompressedIce, CompressedIce, CompressedIce};
        SlimefunItem CondensedIceItem = new SlimefunItem(RU_MATERIALS, CondensedIce, RecipeType.ENHANCED_CRAFTING_TABLE, CondensedIceRecipe);
        CondensedIceItem.register(this);

        //HEHE//

        SlimefunItemStack ConsolidatedIce = new SlimefunItemStack("CONSOLIDATED_ICE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTFmNzcyZjBlMGE5NGM4NGIyNjgyNmQxNWVjNGNlMzljMDEzZDVmM2ViMGZjOGMxODY4MDA4YzdiNzRjZDAwNSJ9fX0=", "&bConsolidated Ice", "&bCauses frostbites");
        ItemStack[] ConsolidatedIceRecipe = new ItemStack[]{
                CondensedIce, CondensedIce, null, CondensedIce, CondensedIce, null, null, null, null};
        SlimefunItem ConsolidatedIceItem = new SlimefunItem(RU_MATERIALS, ConsolidatedIce, RecipeType.ENHANCED_CRAFTING_TABLE, ConsolidatedIceRecipe);
        ConsolidatedIceItem.register(this);

        //HEHE//

        SlimefunItemStack IceAccelerator = new SlimefunItemStack("ICE_ACCELERATOR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk0MzY3NjNlZTcwNGRlZjFiYzZlODk0ZDFmODUzNTE0MWI0OWM5NTk5NWJhYzU3NjRmMTYzNWM2ZDBkMTEzNiJ9fX0=", "&bIce Accelerator", "&7Don't put anything in there");
        ItemStack[] IceAcceleratorRecipe = {
                CondensedIce, null, CondensedIce,
                CondensedIce, null, CondensedIce,
                CondensedIce, null, CondensedIce
        };
        SlimefunItem IceAcceleratorItem = new SlimefunItem(RU_MATERIALS, IceAccelerator, RecipeType.ENHANCED_CRAFTING_TABLE, IceAcceleratorRecipe);
        IceAcceleratorItem.register(this);

        //HEHE//

        SlimefunItemStack PowerCore = new SlimefunItemStack("POWER_CORE",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWIxMGVkOTQwODBhMDc1NDZhMjQ1ZWM4MzdjZmM1ODE0Yzc2NThjN2VmMzM3YmUwMzVhMTFiMmUxZTAzYmExNiJ9fX0=",
                "&8Power Core", "&cBetter than lithium-ion batteries");
        ItemStack[] powerCoreRecipe = {
                new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.BEACON), new ItemStack(Material.CRYING_OBSIDIAN),
                new ItemStack(Material.BEACON), new ItemStack(Material.RESPAWN_ANCHOR), new ItemStack(Material.BEACON),
                new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.BEACON), new ItemStack(Material.CRYING_OBSIDIAN)};
        SlimefunItem powerCoreItem = new SlimefunItem(RU_MATERIALS, PowerCore, RecipeType.ENHANCED_CRAFTING_TABLE, powerCoreRecipe);
        powerCoreItem.register(this);

        //HEHE//

        SlimefunItemStack TeleportationCore = new SlimefunItemStack("TELEPORTATION_CORE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVjMWMzZjdkMDljZTZjMGNiNDhlZDMwYjQ1OTZhNWMxNGZhZTc5ZGVmOGJmZDE0YTU5ZmMxOTM1NjAwYmM3YiJ9fX0=", "&bTeleportation Core", "&7It's pulsing");
        ItemStack[] teleportationCoreRecipe = {
                StablePearl, StablePearl, StablePearl,
                StablePearl, PowerCore, StablePearl,
                StablePearl, StablePearl, StablePearl
        };
        SlimefunItem teleportationCoreItem = new SlimefunItem(RU_MATERIALS, TeleportationCore, RecipeType.ENHANCED_CRAFTING_TABLE, teleportationCoreRecipe);
        teleportationCoreItem.register(this);

        //HEHE
        SlimefunItemStack IceCore = new SlimefunItemStack("ICE_CORE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiNWY2OWUxOTAxMDE5NGExOWNhZjE4NDM3YzdjYTcxMjIxZTQxM2FhMDg5ZWFmYTQ3YjliZGQ5Zjk5ZGQ4NSJ9fX0=", "&bIce Core", "&bDon't mistake for a portable AC");
        ItemStack[] IceCoreRecipe = new ItemStack[]{
                ConsolidatedIce, ConsolidatedIce, ConsolidatedIce,
                ConsolidatedIce, PowerCore, ConsolidatedIce,
                ConsolidatedIce, ConsolidatedIce, ConsolidatedIce
        };
        SlimefunItem IceCoreItem = new SlimefunItem(RU_MATERIALS, IceCore, RecipeType.ENHANCED_CRAFTING_TABLE, IceCoreRecipe);
        IceCoreItem.register(this);

        //OLD PLUGIN ITEMS, MERGE//
        //HEHE//

        SlimefunItemStack LapisCrystal = new SlimefunItemStack("LAPIS_CRYSTAL", "db9ffd85bf0283a588706e3d27afb9d4917e06f2ba717c1aea68dc79393b91f4", "&9Lapis Crystal", new String[]{"&9Crystallized Lapis Lazuli"});
        ItemStack[] LapisCrystalRecipe = new ItemStack[]{
                new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK),
                new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.GLASS_PANE), new ItemStack(Material.LAPIS_BLOCK),
                new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.LAPIS_BLOCK)
        };
        SlimefunItem LapisCrystalItem = new SlimefunItem(RU_MATERIALS, LapisCrystal, RecipeType.ENHANCED_CRAFTING_TABLE, LapisCrystalRecipe);
        LapisCrystalItem.register(this);

        //HEHE//

        SlimefunItemStack RedstoneCrystal = new SlimefunItemStack("REDSTONE_CRYSTAL", "f6257c06896cee4dd58f63dc77c0b514294ea0bc22bed78c061cf13d2d714536", "&cRedstone Crystal", new String[]{"&cCrystallized Redstone"});
        ItemStack[] RedstoneCrystalRecipe = new ItemStack[]{
                new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK),
                new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.GLASS_PANE), new ItemStack(Material.REDSTONE_BLOCK),
                new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.REDSTONE_BLOCK)
        };
        SlimefunItem RedstoneCrystalItem = new SlimefunItem(RU_MATERIALS, RedstoneCrystal, RecipeType.ENHANCED_CRAFTING_TABLE, RedstoneCrystalRecipe);
        RedstoneCrystalItem.register(this);

        //HEHE//

        SlimefunItemStack MagicalContainer = new SlimefunItemStack("MAGICAL_CONTAINER", "b91b7b21725f146d29c192b745d79d22603267c7ad893badeb6546e746600060", "&6Magical Container", new String[]{"&7Holds molten flowers"});
        ItemStack[] MagicalContainerRecipe = new ItemStack[]{
                LapisCrystal, RedstoneCrystal, LapisCrystal,
                RedstoneCrystal, new ItemStack(Material.GLASS), RedstoneCrystal,
                LapisCrystal, RedstoneCrystal, LapisCrystal
        };
        SlimefunItem MagicalContainerItem = new SlimefunItem(RU_MATERIALS, MagicalContainer, RecipeType.ENHANCED_CRAFTING_TABLE, MagicalContainerRecipe);
        MagicalContainerItem.register(this);

        //HEHE//

        //Waiting for a working Machine code before continuing
        //81711f139d3bca577ea7cfc1b8d84594db87dd066d6f00f73a9ae33bc9c2aed1
        SlimefunItemStack LilacBundle = new SlimefunItemStack(
                "LILAC_BUNDLE",
                Material.MAGENTA_DYE,
                "&5Lilac Bundle",
                new String[]{"&5Bundled Lilac"}
        );
        ItemStack[] LilacBundleRecipe = new ItemStack[]{
                new ItemStack(Material.LILAC, 64), null, null, null, null, null, null, null, null
        };
        SlimefunItem LilacBundleItem = new SlimefunItem(RU_MATERIALS, LilacBundle, RecipeType.COMPRESSOR, LilacBundleRecipe);
        LilacBundleItem.register(this);

        //HEHE//

        //ca71142706c084f70a95fa9955716baf2680ab7cff3771c427f5b116adf43bfa
        SlimefunItemStack SunflowerBundle = new SlimefunItemStack(
                "SUNFLOWER_BUNDLE",
                Material.YELLOW_DYE,
                "&eSunflower Bundle",
                new String[]{"&eBundled Sunflower"}
        );
        ItemStack[] SunflowerBundleRecipe = new ItemStack[]{
                new ItemStack(Material.SUNFLOWER, 64), null, null, null, null, null, null, null, null
        };
        SlimefunItem SunflowerBundleItem = new SlimefunItem(RU_MATERIALS, SunflowerBundle, RecipeType.COMPRESSOR, SunflowerBundleRecipe);
        SunflowerBundleItem.register(this);

        //HEHE//

        //17732caeb88cce8d18593df0ffa343305341741be647aac42e4ce4d250675765
        SlimefunItemStack PeonyBundle = new SlimefunItemStack(
                "PEONY_BUNDLE",
                Material.PINK_DYE,
                "&dPeony Bundle",
                new String[]{"&dBundled Peony"}
        );
        ItemStack[] PeonyBundleRecipe = new ItemStack[]{
                new ItemStack(Material.PEONY, 64), null, null, null, null, null, null, null, null
        };
        SlimefunItem PeonyBundleItem = new SlimefunItem(RU_MATERIALS, PeonyBundle, RecipeType.COMPRESSOR, PeonyBundleRecipe);
        PeonyBundleItem.register(this);

        //HEHE//

        //c469268e0643e69466738d0458ad9d9b8327ec14ae9bca79a990ea20776a2a28
        SlimefunItemStack RoseBundle = new SlimefunItemStack(
                "ROSE_BUNDLE",
                Material.RED_DYE,
                "&4Rose Bundle",
                new String[]{"&4Bundled Rose"}
        );
        ItemStack[] RoseBundleRecipe = new ItemStack[]{
                new ItemStack(Material.ROSE_BUSH, 64), null, null, null, null, null, null, null, null
        };
        SlimefunItem RoseBundleItem = new SlimefunItem(RU_MATERIALS, RoseBundle, RecipeType.COMPRESSOR, RoseBundleRecipe);
        RoseBundleItem.register(this);


        //END OF OLD ITEMS//

        //----------TOOLS BELOW----------//

        SlimefunItemStack EndSword = new SlimefunItemStack("END_SWORD", Material.NETHERITE_SWORD, "&5End Sword","",
                "&e&lRight Click Ability:","&e&lDirectional Teleportation","&e&lCooldown 20 seconds", "&e&lRange: 12 Blocks");
            //Enchants//
        EndSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
        EndSword.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
            //Attributes//
        ItemMeta itemMeta = EndSword.getItemMeta();
        if (itemMeta != null) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            // Add the "End Sword Identifier" to the lore
            List<String> lore = new ArrayList<>(itemMeta.getLore());
            lore.add("");
            lore.add("It's screaming at you");
            itemMeta.setLore(lore);
            EndSword.setItemMeta(itemMeta);
        }
        ItemStack[] endSwordRecipe = {null, HeavyPearl, null, null, HeavyPearl, null, null, ReinforcedHandle, null};
        SlimefunItem endSwordItem = new SlimefunItem(RU_TOOLS, EndSword, RecipeType.ARMOR_FORGE, endSwordRecipe);
        endSwordItem.register(this);

        //HEHE//

        SlimefunItemStack AugmentedEndSword = new SlimefunItemStack("AUGMENTED_END_SWORD", Material.NETHERITE_SWORD, "&5Augmented End Sword","",
                "&e&lRight Click Ability:", "&e&lDirectional Teleportation", "&e&lCooldown 5 seconds", "&e&lRange: 20 Blocks");
            // Enchants
        AugmentedEndSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        AugmentedEndSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
            // Attributes
        ItemMeta AESMeta = AugmentedEndSword.getItemMeta();
        if (AESMeta != null) {
            AESMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 14, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            AESMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", 3.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            // Add the "Augmented End Sword Identifier" to the lore
            List<String> lore = new ArrayList<>(AESMeta.getLore());
            lore.add("");
            lore.add("It's staring at you");
            AESMeta.setLore(lore);
            AugmentedEndSword.setItemMeta(AESMeta);
        }
        ItemStack[] augmentedEndSwordRecipe = {
                null, TeleportationCore, null,
                null, EndSword, null,
                TeleportationCore, null, TeleportationCore};
        SlimefunItem augmentedEndSwordItem = new SlimefunItem(RU_TOOLS, AugmentedEndSword, RecipeType.ARMOR_FORGE, augmentedEndSwordRecipe);
        augmentedEndSwordItem.register(this);

        //HEHE//

        SlimefunItemStack IceBlaster = new SlimefunItemStack("ICE_GUN", Material.IRON_HOE, "&bIce Blaster", "", "&e&lRight Click to Fire", "&eUses Ice as ammo","&cPierces through Shields");
        ItemMeta IBMeta = IceBlaster.getItemMeta();
        if (IBMeta != null) {
            // Enchants
            IBMeta.addEnchant(Enchantment.LOYALTY, 1, true);
            // Add the "Ice Blaster Identifier" to the lore
            List<String> lore = new ArrayList<>(IBMeta.getLore());
            lore.add("");
            lore.add("Don't snipe me pls");
            IBMeta.setLore(lore);
            IceBlaster.setItemMeta(IBMeta);
        }
        ItemStack[] iceBlasterRecipe = {
                null, IceAccelerator, IceAccelerator,
                null, null, ReinforcedHandle,
                null, null, ReinforcedHandle
        };
        SlimefunItem iceBlasterItem = new SlimefunItem(RU_TOOLS, IceBlaster, RecipeType.ARMOR_FORGE, iceBlasterRecipe);
        iceBlasterItem.register(this);

        //HEHE//

        SlimefunItemStack IcicleStaff = new SlimefunItemStack("ICICLE_STAFF", Material.IRON_HOE, "&bIcicle Staff", "", "&e&lRight Click to Cast", "&eUses Ice as source of energy", "&cPierces through Shields");
        ItemMeta ISMeta = IcicleStaff.getItemMeta();
        if (ISMeta != null) {
            // Enchants
            ISMeta.addEnchant(Enchantment.LOYALTY, 2, true);
            // Add the "Icicle Staff Identifier" to the lore
            List<String> lore = new ArrayList<>(ISMeta.getLore());
            lore.add("");
            lore.add("Don't freeze me pls");
            ISMeta.setLore(lore);
            IcicleStaff.setItemMeta(ISMeta);
        }
        ItemStack[] icicleStaffRecipe = {
                IceCore, IceCore, IceCore,
                IceCore, IceBlaster, IceCore,
                IceCore, IceCore, IceCore
        };
        SlimefunItem icicleStaffItem = new SlimefunItem(RU_TOOLS, IcicleStaff, RecipeType.ARMOR_FORGE, icicleStaffRecipe);
        icicleStaffItem.register(this);

        //HEHE//



        //----------MACHINES BELOW----------//

        /*
        SlimefunItemStack Rezinizer = new SlimefunItemStack("REZINIZER", Material.MAGENTA_STAINED_GLASS, "&5Rezinizer", "&7Hardens stuff");
        ItemStack[] RezinizerRecipe = {
                Binder, Binder, Binder,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                Binder, SlimefunItems.SMALL_CAPACITOR, Binder
        };
        SlimefunItem RezinizerMachine = new SlimefunItem(RU_MACHINES, Rezinizer, RecipeType.ENHANCED_CRAFTING_TABLE, RezinizerRecipe);
        RezinizerMachine.register(this);
        */

        //HEHE//

        //----------ENCHANTS BELOW----------//

        //----------SKILLS BELOW----------//

        //EndSwordSkillListener FALLBACK_REWRITE//
        //Relies on lore to activate//
        getServer().getPluginManager().registerEvents(new EndSwordSkillListener(this, EndSword), this);

        //AugmentedEndSwordSkillListener FALLBACK_REWRITE//
        //Relies on lore to activate//
        getServer().getPluginManager().registerEvents(new AugmentedEndSwordSkillListener(this, AugmentedEndSword), this);

        // Register the IceBlasterListener as a listener
        //Relies on lore to activate//
        getServer().getPluginManager().registerEvents(new IceBlasterListener(this), this);

        // Register the IcicleStaffListener as a listener
        //Relies on lore to activate//
        getServer().getPluginManager().registerEvents(new IcicleStaffListener(this), this);

    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

}
