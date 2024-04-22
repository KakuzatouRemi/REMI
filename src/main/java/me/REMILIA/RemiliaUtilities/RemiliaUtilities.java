package me.REMILIA.RemiliaUtilities;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.generators.SolarGenerator;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

//For fixing new cannot find symbol stuff
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

//TEMP, MIGHT NEED

import javax.annotation.Nonnull;
import java.util.*;

import org.bukkit.NamespacedKey;


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
        // Define a sub-category group, belonging to the main category RU
        final SubItemGroup RU_GEMS = new SubItemGroup(
                new NamespacedKey(this, "RU_GEMS"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.AMETHYST_SHARD, "&bGems") // Item representing the sub-group
        );
        // Define another sub-category group, belonging to the main category RU
        final SubItemGroup RU_TOOLS = new SubItemGroup(
                new NamespacedKey(this, "RU_TOOLS"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.DIAMOND_SWORD, "&6Tools") // Item representing the sub-group
        );
        // Define another sub-category group, belonging to the main category RU
        final SubItemGroup RU_PANELS = new SubItemGroup(
                new NamespacedKey(this, "RU_PANELS"), // Key for the sub-group
                RU, // Parent group (RU)
                new CustomItemStack(Material.DAYLIGHT_DETECTOR, "&bPanels") // Item representing the sub-group
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
                "&6Gems can be obtained by mining:",
                "&6Stone & Deepslate"// Multiline BSlore
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

        SlimefunItemStack ConcentratedExplosives = new SlimefunItemStack("CONCENTRATED_EXPLOSIVES", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ3YmRmMzJmNDI3YjhkZjBhNGNjMWVlYWMzNGQ5NTc1NjAzNWQ0YjM1MWU3NWZiMTE4NzYxZWYwMjA0YzQwZiJ9fX0=", "&6Concentrated Explosives", "&7No smoking!");
        ItemStack[] ConcentratedExplosivesRecipe = {
                new ItemStack(Material.TNT), new ItemStack(Material.END_CRYSTAL), new ItemStack(Material.TNT),
                new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.RESPAWN_ANCHOR), new ItemStack(Material.FIRE_CHARGE),
                new ItemStack(Material.TNT), new ItemStack(Material.END_CRYSTAL), new ItemStack(Material.TNT)
        };
        SlimefunItem ConcentratedExplosivesItem = new SlimefunItem(RU_MATERIALS, ConcentratedExplosives, RecipeType.ENHANCED_CRAFTING_TABLE, ConcentratedExplosivesRecipe);
        ConcentratedExplosivesItem.register(this);

        //HEHE//

        SlimefunItemStack APHE = new SlimefunItemStack("APHE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTRmOWRkMzkwNzAxODY0MWJkYjEwNWI5NjQ3YzA4MjI3OTYyZjEwNjUwMmE2ODI5YWRkNGI5MDhmNzQyYTgzNyJ9fX0=", "&6APHE", "&7Armor-Piercing High Explosive");
        ItemStack[] APHERecipe = {
                new ItemStack(SlimefunItems.STEEL_INGOT), new ItemStack(SlimefunItems.STEEL_INGOT), new ItemStack(SlimefunItems.STEEL_INGOT),
                new ItemStack(SlimefunItems.STEEL_INGOT), ConcentratedExplosives, new ItemStack(SlimefunItems.STEEL_INGOT),
                new ItemStack(SlimefunItems.STEEL_INGOT), new ItemStack(SlimefunItems.STEEL_INGOT), new ItemStack(SlimefunItems.STEEL_INGOT)
        };
        SlimefunItem APHEItem = new SlimefunItem(RU_MATERIALS, APHE, RecipeType.ENHANCED_CRAFTING_TABLE, APHERecipe);
        APHEItem.register(this);

        //HEHE//

        SlimefunItemStack DenseObsidian = new SlimefunItemStack("DENSE_OBSIDIAN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdjZjkyZTQxYWI5YmIzNmNkMjI4MDU5ZmM0ZWE2N2FlMmY1MTcxNzI2NTUyNDY2ODU3MDgxNTc3MTRlZWMxZSJ9fX0=", "&7Dense Obsidian", "&7Heavy AF");
        ItemStack[] DenseObsidianRecipe = {
                new SlimefunItemStack(SlimefunItems.WITHER_PROOF_OBSIDIAN,8), null, null,
                null,null,null,
                null,null,null
        };
        SlimefunItem DenseObsidianItem = new SlimefunItem(RU_MATERIALS, DenseObsidian, RecipeType.COMPRESSOR, DenseObsidianRecipe);
        DenseObsidianItem.register(this);

        //HEHE//

        SlimefunItemStack ActivatedObsidian = new SlimefunItemStack("ACTIVATED_OBSIDIAN", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FkZTc2ZjVkNTkxNjMyZDkzMjRhYjk5ZDc0NzI1MWNhMzc0N2UwNGNhZjQxZDAzNzBmODUwZjllYzg3YTEzZCJ9fX0=", "&4Activated Obsidian", "&7Much heavier than before");
        ItemStack[] ActivatedObsidianRecipe = {
                DenseObsidian, DenseObsidian, DenseObsidian,
                DenseObsidian, new ItemStack(Material.REDSTONE_BLOCK), DenseObsidian,
                DenseObsidian, DenseObsidian, DenseObsidian
        };
        SlimefunItem ActivatedObsidianItem = new SlimefunItem(RU_MATERIALS, ActivatedObsidian, RecipeType.ENHANCED_CRAFTING_TABLE, ActivatedObsidianRecipe);
        ActivatedObsidianItem.register(this);

        //HEHE//

        SlimefunItemStack LinearAccelerator = new SlimefunItemStack("LINEAR_ACCELERATOR", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE0Nzg0Njk0MGFmOGFhZDIwNzM5MTg1MmFhMDI3N2EyZWUxMTY3OWZhMmMyODZlZjA2Mjc3NjU1ZjU0YTE1OCJ9fX0=", "&bLinear Accelerator", "&7Capable of handling high amounts of energy");
        ItemStack[] LinearAcceleratorRecipe = {
                IceAccelerator, IceAccelerator, IceAccelerator,
                IceAccelerator, new ItemStack(Material.TINTED_GLASS), IceAccelerator,
                IceAccelerator, IceAccelerator, IceAccelerator
        };
        SlimefunItem LinearAcceleratorItem = new SlimefunItem(RU_MATERIALS, LinearAccelerator, RecipeType.ENHANCED_CRAFTING_TABLE, LinearAcceleratorRecipe);
        LinearAcceleratorItem.register(this);

        //HEHE//

        SlimefunItemStack LauncherTube = new SlimefunItemStack("LAUNCHER_TUBE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJjZmQyMmZiMWFhN2JkN2VmNGY4NmRhMGM4OTIxZTc2MGY4NDg4ODcwZGRkYmM0MGY2Mjc3NTBjMTE4ZTE3NiJ9fX0=", "&3Launcher Tube", "&7You put the missile here");
        ItemStack[] LauncherTubeRecipe = {
                new ItemStack(SlimefunItems.REINFORCED_PLATE), new ItemStack(SlimefunItems.REINFORCED_PLATE), new ItemStack(SlimefunItems.REINFORCED_PLATE),
                null, null, null,
                new ItemStack(SlimefunItems.REINFORCED_PLATE), new ItemStack(SlimefunItems.REINFORCED_PLATE), new ItemStack(SlimefunItems.REINFORCED_PLATE)
        };
        SlimefunItem LauncherTubeItem = new SlimefunItem(
                RU_MATERIALS, // Category
                LauncherTube, // Item
                RecipeType.ENHANCED_CRAFTING_TABLE, // Recipe type
                LauncherTubeRecipe // Recipe
        );
        LauncherTubeItem.register(this);

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

        //HEHE//

        SlimefunItemStack IceCore = new SlimefunItemStack("ICE_CORE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiNWY2OWUxOTAxMDE5NGExOWNhZjE4NDM3YzdjYTcxMjIxZTQxM2FhMDg5ZWFmYTQ3YjliZGQ5Zjk5ZGQ4NSJ9fX0=", "&bIce Core", "&bDon't mistake for a portable AC");
        ItemStack[] IceCoreRecipe = new ItemStack[]{
                ConsolidatedIce, ConsolidatedIce, ConsolidatedIce,
                ConsolidatedIce, PowerCore, ConsolidatedIce,
                ConsolidatedIce, ConsolidatedIce, ConsolidatedIce
        };
        SlimefunItem IceCoreItem = new SlimefunItem(RU_MATERIALS, IceCore, RecipeType.ENHANCED_CRAFTING_TABLE, IceCoreRecipe);
        IceCoreItem.register(this);

        //HEHE//

        SlimefunItemStack ExplosiveCore = new SlimefunItemStack("EXPLOSIVE_CORE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJiMjhiYjBiZjFhZDIxN2QyYTgxMTkxZWZmY2M2OWZlMTc0NzE0YTQzMmZkNzFmYTYwYWE1MGYzNzEyYjk3In19fQ==", "&cExplosive Core", "&cDon't stick a screwdriver!");
        ItemStack[] ExplosiveCoreRecipe = {
                APHE, APHE, APHE,
                APHE, PowerCore, APHE,
                APHE, APHE, APHE
        };
        SlimefunItem ExplosiveCoreItem = new SlimefunItem(RU_MATERIALS, ExplosiveCore, RecipeType.ENHANCED_CRAFTING_TABLE, ExplosiveCoreRecipe);
        ExplosiveCoreItem.register(this);

        //HEHE//

        SlimefunItemStack ProtectionCore = new SlimefunItemStack("PROTECTION_CORE", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFjMGU2OWYyY2RiNTQyNmFhZWQ3MTFjZTAzZWE2ZDcwOWM5MmU5YmViODRhZDFkMjJiNTg0YzQ2MWQ1MzM1ZCJ9fX0=", "&bProtection Core", "&cCan't lift bro?");
        ItemStack[] ProtectionCoreRecipe = {
                ActivatedObsidian, ActivatedObsidian, ActivatedObsidian,
                ActivatedObsidian, PowerCore, ActivatedObsidian,
                ActivatedObsidian, ActivatedObsidian, ActivatedObsidian
        };
        SlimefunItem ProtectionCoreItem = new SlimefunItem(RU_MATERIALS, ProtectionCore, RecipeType.ENHANCED_CRAFTING_TABLE, ProtectionCoreRecipe);
        ProtectionCoreItem.register(this);

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

        SlimefunItemStack MagicalContainer = new SlimefunItemStack("MAGICAL_CONTAINER", "b91b7b21725f146d29c192b745d79d22603267c7ad893badeb6546e746600060", "&6Magical Container", new String[]{"&7Magical Storage"});
        ItemStack[] MagicalContainerRecipe = new ItemStack[]{
                LapisCrystal, RedstoneCrystal, LapisCrystal,
                RedstoneCrystal, new ItemStack(Material.GLASS), RedstoneCrystal,
                LapisCrystal, RedstoneCrystal, LapisCrystal
        };
        SlimefunItem MagicalContainerItem = new SlimefunItem(RU_MATERIALS, MagicalContainer, RecipeType.ENHANCED_CRAFTING_TABLE, MagicalContainerRecipe);
        MagicalContainerItem.register(this);

        //OLD PLUGIN ITEMS, MERGE//

        //HEHE//

        //Waiting for a working Machine code before continuing
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

        //----------GEMS BELOW----------//
        //BASE//

        SlimefunItemStack Jade = new SlimefunItemStack(
                "JADE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTM0ZjAwZWNiOTY3NzAyODBjOGQ2NTNjMTg5MGU4N2FiNWIxMjVmODUyOGU3YWQ0ZmVjMWQzMWViZDEyMjg3YiJ9fX0=", // Material (example, change as needed)
                "&aJade", // Display name
                "&7A beautiful green gemstone", // Description
                "&7Found deep in the earth" // Additional lore
        );
        ItemStack[] jadeRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem jadeItem = new SlimefunItem(RU_GEMS, Jade, RecipeType.NULL, jadeRecipe);
        jadeItem.register(this);
        SlimefunItemStack Moonstone = new SlimefunItemStack(
                "MOONSTONE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ5M2VkOTJiMWE5YTI0YWQwNzJhY2Y2ZThmY2NmY2VkZTFiOGMyNzEzNmEyYjQyNTc2ZWU4NWRmN2RjMTE0YiJ9fX0=", // Material (example, change as needed)
                "&7Moonstone", // Display name
                "&fA mystical gemstone from the moon", // Description
                "&7Said to possess magical properties" // Additional lore
        );
        ItemStack[] moonstoneRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem moonstoneItem = new SlimefunItem(RU_GEMS, Moonstone, RecipeType.NULL, moonstoneRecipe);
        moonstoneItem.register(this);
        SlimefunItemStack Onyx = new SlimefunItemStack(
                "ONYX", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUwY2JjMThhYTgzODk2Zjk4NGI1MmIyMWU1MmYyN2ViOGE3MmUyNmM0NzE3OWZkNzkzMjk3ZjNiN2M2ZmJiZSJ9fX0=", // Material (example, change as needed)
                "&8Onyx", // Display name
                "&7A dark, glossy gemstone", // Description
                "&7Known for its protective properties" // Additional lore
        );
        ItemStack[] onyxRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem onyxItem = new SlimefunItem(RU_GEMS, Onyx, RecipeType.NULL, onyxRecipe);
        onyxItem.register(this);
        SlimefunItemStack Rhodonite = new SlimefunItemStack(
                "RHODONITE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA2N2ZjM2U4MWQ2NDU5MmY3NTEzYWVlYTc3M2U2MTYzNDMzYmY0MTg3YzRiZjcyYTc5MDM4Y2ZjNWVkYzE1ZCJ9fX0=", // Material (example, change as needed)
                "&dRhodonite", // Display name
                "&7A pink gemstone with black veins", // Description
                "&7Believed to promote love and emotional healing" // Additional lore
        );
        ItemStack[] rhodoniteRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem rhodoniteItem = new SlimefunItem(RU_GEMS, Rhodonite, RecipeType.NULL, rhodoniteRecipe);
        rhodoniteItem.register(this);
        SlimefunItemStack Amethyst = new SlimefunItemStack(
                "AMETHYST", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFkN2VlZjMzNzE1MzUxY2Y3MjJlYTM2M2FhOTkxNGExNmQ2NDExODFmNjc0ZGQ1OTJhODM2OGUyMWQ0M2I2YiJ9fX0=", // Material (example, change as needed)
                "&5Amethyst", // Display name
                "&7A purple gemstone with a soothing energy", // Description
                "&7Associated with clarity and intuition" // Additional lore
        );
        ItemStack[] amethystRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem amethystItem = new SlimefunItem(RU_GEMS, Amethyst, RecipeType.NULL, amethystRecipe);
        amethystItem.register(this);
        SlimefunItemStack Tanzanite = new SlimefunItemStack(
                "TANZANITE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY4ZDYzZTkwN2JkZmYyNTJhMDRlMGVhZWM5OGI1MmE4Nzk1OGQ3MzdkOGU0OGQ0MzE5ZmE1OTFjMzFkMGUzMSJ9fX0=", // Material (example, change as needed)
                "&bTanzanite", // Display name
                "&7A rare blue-violet gemstone", // Description
                "&7Found only in Tanzania" // Additional lore
        );
        ItemStack[] tanzaniteRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem tanzaniteItem = new SlimefunItem(RU_GEMS, Tanzanite, RecipeType.NULL, tanzaniteRecipe);
        tanzaniteItem.register(this);
        SlimefunItemStack Sapphire = new SlimefunItemStack(
                "SAPPHIRE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzllZjMzYTNlNzUzY2JhMjliNmMxMzRhMWJkNjc0MzhmNmQ2NjlmNmM0Yzc1NjI5MmNjMjBjMzEyMDkxMDUyOCJ9fX0=", // Material (example, change as needed)
                "&9Sapphire", // Display name
                "&7A precious blue gemstone", // Description
                "&7Associated with wisdom and royalty" // Additional lore
        );
        ItemStack[] sapphireRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem sapphireItem = new SlimefunItem(RU_GEMS, Sapphire, RecipeType.NULL, sapphireRecipe);
        sapphireItem.register(this);
        SlimefunItemStack Kyanite = new SlimefunItemStack(
                "KYANITE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTkxZWE0N2QwYTkxMmYwMjc2ODUyOGE4M2FlZTNmZTU3OTE3ODMyODgwOThiOTBlOWMwOGExZTBmN2VlNjY3OSJ9fX0=", // Material (example, change as needed)
                "&3Kyanite", // Display name
                "&7A blue mineral often used in jewelry", // Description
                "&7Known for its calming and balancing properties" // Additional lore
        );
        ItemStack[] kyaniteRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem kyaniteItem = new SlimefunItem(RU_GEMS, Kyanite, RecipeType.NULL, kyaniteRecipe);
        kyaniteItem.register(this);
        SlimefunItemStack Turquoise = new SlimefunItemStack(
                "TURQUOISE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhlYWNkYjA2OTRlZTEwYmEwYTk4NjZlZTQ5NTA5MDJmNGEzZGVkZTA3ODIwMWRlMDU3NjJkM2ZkZjI2M2NlMSJ9fX0=", // Material (example, change as needed)
                "&bTurquoise", // Display name
                "&7A blue-green gemstone prized for its color", // Description
                "&7Believed to bring protection and good fortune" // Additional lore
        );
        ItemStack[] turquoiseRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem turquoiseItem = new SlimefunItem(RU_GEMS, Turquoise, RecipeType.NULL, turquoiseRecipe);
        turquoiseItem.register(this);
        SlimefunItemStack Emerald = new SlimefunItemStack(
                "EMERALD", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjczNTRiODc4ODQzYzcxZWE1MzA2ZmViM2RjOGI3NjdmNzY4Y2U2MDM5ZmEwZTViYjZmMjNhZDk4ZGFmNmM2ZiJ9fX0=", // Material (example, change as needed)
                "&aEmerald", // Display name
                "&7A rare green gemstone", // Description
                "&7Valued for its beauty and rarity" // Additional lore
        );
        ItemStack[] emeraldRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem emeraldItem = new SlimefunItem(RU_GEMS, Emerald, RecipeType.NULL, emeraldRecipe);
        emeraldItem.register(this);
        SlimefunItemStack Peridot = new SlimefunItemStack(
                "PERIDOT", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUxZDRlOWNlMWYxNjg0Nzg2OTI3ZTEwZjU5Zjk2ZDNhNTZjNzkyZThhOWIzYzVjNmE3NDU2OGU1Mjk3YjU2NSJ9fX0=", // Material (example, change as needed)
                "&aPeridot", // Display name
                "&7A green gemstone often found in volcanic areas", // Description
                "&7Symbolizes strength and protection" // Additional lore
        );
        ItemStack[] peridotRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem peridotItem = new SlimefunItem(RU_GEMS, Peridot, RecipeType.NULL, peridotRecipe);
        peridotItem.register(this);
        SlimefunItemStack Topaz = new SlimefunItemStack(
                "TOPAZ", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTU2NTJmMzcxNmQzOGQ4OTkxZTI4N2E4Y2JmN2YwNmU5ODI3OWE1MWFmODAwNTAzMDE4YmVjNzI1NDJkNjBjMyJ9fX0=", // Material (example, change as needed)
                "&eTopaz", // Display name
                "&7A yellow gemstone associated with positivity", // Description
                "&7Thought to promote creativity and happiness" // Additional lore
        );
        ItemStack[] topazRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem topazItem = new SlimefunItem(RU_GEMS, Topaz, RecipeType.NULL, topazRecipe);
        topazItem.register(this);
        SlimefunItemStack Citrine = new SlimefunItemStack(
                "CITRINE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFjNzIxMjhmMDcxMmFiYzM1ODgyNjg1ZjVkMDgxZTQ2YTRhZjNhYWJjNTMyYjMzNTQyODMwODBkZWU1OTA1ZCJ9fX0=", // Material (example, change as needed)
                "&6Citrine", // Display name
                "&7A yellow-orange gemstone associated with abundance", // Description
                "&7Believed to attract wealth and success" // Additional lore
        );
        ItemStack[] citrineRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem citrineItem = new SlimefunItem(RU_GEMS, Citrine, RecipeType.NULL, citrineRecipe);
        citrineItem.register(this);
        SlimefunItemStack Garnet = new SlimefunItemStack(
                "GARNET", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzcxZDcwOTY5NWJlNWM3ZTQ0ZDAyZTg2NDE2M2I2MTg1YzE0YmM5YjdiOGNiNWM0ODMwOWQzNjE3NWIxYjc1OCJ9fX0=", // Material (example, change as needed)
                "&cGarnet", // Display name
                "&7A deep red gemstone symbolizing love and passion", // Description
                "&7Associated with strength and energy" // Additional lore
        );
        ItemStack[] garnetRecipe = {null, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem garnetItem = new SlimefunItem(RU_GEMS, Garnet, RecipeType.NULL, garnetRecipe);
        garnetItem.register(this);

        //HEHE//
        //CLUSTERS//

        SlimefunItemStack JadeCluster = new SlimefunItemStack(
                "JADE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEzMGU5YzRjZjY4NWE4MDU2NjNkZmY4YTNiNDMwNGFkMGE3MThmNzBhMWRjOGQwZGFlYzFlNTJhOGEzOGNiYSJ9fX0=", // Material (example, change as needed)
                "&aJade Cluster", // Display name
                "&7A cluster of beautiful green gemstones", // Description
                "&7Found deep in the earth" // Additional lore
        );
        ItemStack[] jadeClusterRecipe = {new CustomItemStack(Jade, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem jadeClusterItem = new SlimefunItem(RU_GEMS, JadeCluster, RecipeType.COMPRESSOR, jadeClusterRecipe);
        jadeClusterItem.register(this);
        SlimefunItemStack MoonstoneCluster = new SlimefunItemStack(
                "MOONSTONE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY4MGM2OTU0MDZlZWY0ZTA2ZWNmYzBkODg0ODhmNWZhMWY1ZDljYzRlNTg3NzJiZGQwMTg1NzZkZTU5MzBlMiJ9fX0=", // Material (example, change as needed)
                "&7Moonstone Cluster", // Display name
                "&fA cluster of mystical gemstones from the moon", // Description
                "&7Said to possess powerful magical properties" // Additional lore
        );
        ItemStack[] moonstoneClusterRecipe = {new CustomItemStack(Moonstone, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem moonstoneClusterItem = new SlimefunItem(RU_GEMS, MoonstoneCluster, RecipeType.COMPRESSOR, moonstoneClusterRecipe);
        moonstoneClusterItem.register(this);
        SlimefunItemStack OnyxCluster = new SlimefunItemStack(
                "ONYX_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWVlNDQ0NDIyNjM5NTI3NzQzMjQ5ZDg2YzdmMTk0ZGQ2MDFiMzI0YzUzOGIxYzQxNmQ3ZjIzMzFiMWEzNzBmYiJ9fX0=", // Material (example, change as needed)
                "&8Onyx Cluster", // Display name
                "&7A cluster of dark, glossy gemstones", // Description
                "&7Known for their combined protective properties" // Additional lore
        );
        ItemStack[] onyxClusterRecipe = {new CustomItemStack(Onyx, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem onyxClusterItem = new SlimefunItem(RU_GEMS, OnyxCluster, RecipeType.COMPRESSOR, onyxClusterRecipe);
        onyxClusterItem.register(this);
        SlimefunItemStack RhodoniteCluster = new SlimefunItemStack(
                "RHODONITE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUxNjhhMjA2ZDU3NzE3MmI5ZjZhZTk0OWM2YjFhZjM2ZWI5M2U1Mzk3ZmYyNDRkY2ZkZGJkNTljNDBmYzJlZCJ9fX0=", // Material (example, change as needed)
                "&dRhodonite Cluster", // Display name
                "&7A cluster of pink gemstones with black veins", // Description
                "&7Believed to amplify the properties of individual stones" // Additional lore
        );
        ItemStack[] rhodoniteClusterRecipe = {new CustomItemStack(Rhodonite, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem rhodoniteClusterItem = new SlimefunItem(RU_GEMS, RhodoniteCluster, RecipeType.COMPRESSOR, rhodoniteClusterRecipe);
        rhodoniteClusterItem.register(this);
        SlimefunItemStack AmethystCluster = new SlimefunItemStack(
                "AMETHYST_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZiYzU4ZGQ1ZDhhMDNkNTZhY2VkNGQwNDRhYTM5ZGM4MTU0OTZkNWViNDc0Y2JjZWYyNjk4NGFmNDkzOTFkIn19fQ==", // Material (example, change as needed)
                "&5Amethyst Cluster", // Display name
                "&7A cluster of purple gemstones emitting a soothing energy", // Description
                "&7Known for their collective calming effect" // Additional lore
        );
        ItemStack[] amethystClusterRecipe = {new CustomItemStack(Amethyst, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem amethystClusterItem = new SlimefunItem(RU_GEMS, AmethystCluster, RecipeType.COMPRESSOR, amethystClusterRecipe);
        amethystClusterItem.register(this);
        SlimefunItemStack TanzaniteCluster = new SlimefunItemStack(
                "TANZANITE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDdjZTk1N2Q4NDU3ZjhlMTAyYjhmOTJlOWE2ZDU2NDRhNWE5OWU3MDU3ZjQxOTY1ZjYxOTJiNTcyYzk3NGJhNyJ9fX0=", // Material (example, change as needed)
                "&bTanzanite Cluster", // Display name
                "&7A cluster of rare blue-violet gemstones", // Description
                "&7Said to enhance spiritual awareness when grouped" // Additional lore
        );
        ItemStack[] tanzaniteClusterRecipe = {new CustomItemStack(Tanzanite, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem tanzaniteClusterItem = new SlimefunItem(RU_GEMS, TanzaniteCluster, RecipeType.COMPRESSOR, tanzaniteClusterRecipe);
        tanzaniteClusterItem.register(this);
        SlimefunItemStack SapphireCluster = new SlimefunItemStack(
                "SAPPHIRE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTVjNTE1ZGY3Zjc4MzFjZjkwMmY4OTAwOTFlNjUyNjk2OWVlYzM2NDBlZGM2ZThhNDRiYTZjNDA1YTkxNmQxMCJ9fX0=", // Material (example, change as needed)
                "&9Sapphire Cluster", // Display name
                "&7A cluster of precious blue gemstones", // Description
                "&7Known for their collective wisdom and royalty" // Additional lore
        );
        ItemStack[] sapphireClusterRecipe = {new CustomItemStack(Sapphire, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem sapphireClusterItem = new SlimefunItem(RU_GEMS, SapphireCluster, RecipeType.COMPRESSOR, sapphireClusterRecipe);
        sapphireClusterItem.register(this);
        SlimefunItemStack KyaniteCluster = new SlimefunItemStack(
                "KYANITE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q0MTU3ZWNjY2ZiYjhhNGM4YzkzYjZkZTIwNTJiOTgxNDA2M2RhMWZlNzQ2YWZjMTlkMGUyNzUyYzgyMWJlOCJ9fX0=", // Material (example, change as needed)
                "&3Kyanite Cluster", // Display name
                "&7A cluster of blue minerals often used in jewelry", // Description
                "&7Known for their combined calming and balancing properties" // Additional lore
        );
        ItemStack[] kyaniteClusterRecipe = {new CustomItemStack(Kyanite, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem kyaniteClusterItem = new SlimefunItem(RU_GEMS, KyaniteCluster, RecipeType.COMPRESSOR, kyaniteClusterRecipe);
        kyaniteClusterItem.register(this);
        SlimefunItemStack TurquoiseCluster = new SlimefunItemStack(
                "TURQUOISE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTliMzY4MWI4ZTdiNjBmMmJlMWEyNzYxNGQ3M2NkODczMmQwMWI5MmE2YjRjYTA1ZjZjZmVhY2RlMDExZTZkNSJ9fX0=", // Material (example, change as needed)
                "&bTurquoise Cluster", // Display name
                "&7A cluster of blue-green gemstones prized for their color", // Description
                "&7Believed to amplify protection and good fortune when clustered" // Additional lore
        );
        ItemStack[] turquoiseClusterRecipe = {new CustomItemStack(Turquoise, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem turquoiseClusterItem = new SlimefunItem(RU_GEMS, TurquoiseCluster, RecipeType.COMPRESSOR, turquoiseClusterRecipe);
        turquoiseClusterItem.register(this);
        SlimefunItemStack EmeraldCluster = new SlimefunItemStack(
                "EMERALD_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE5NWI0NTQ2ODA1NWJiMDIwZWY3NzZiNzE3OGU3OTFhZGFiNTEwNWFmYzI5ODgzOGVmY2QzMGY0ODViZDJjMyJ9fX0=", // Material (example, change as needed)
                "&aEmerald Cluster", // Display name
                "&7A cluster of rare green gemstones", // Description
                "&7Valued for their collective beauty and rarity" // Additional lore
        );
        ItemStack[] emeraldClusterRecipe = {new CustomItemStack(Emerald, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem emeraldClusterItem = new SlimefunItem(RU_GEMS, EmeraldCluster, RecipeType.COMPRESSOR, emeraldClusterRecipe);
        emeraldClusterItem.register(this);
        SlimefunItemStack PeridotCluster = new SlimefunItemStack(
                "PERIDOT_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQwNWE0ZTQ1OTk5ODY1MThkNDhlYjZlMGIwZDI2N2IzNmQ0MTNhZjQ1NjVhM2YzZDA4ZTEyMmJjMjFlNGUxNCJ9fX0=", // Material (example, change as needed)
                "&aPeridot Cluster", // Display name
                "&7A cluster of green gemstones often found in volcanic areas", // Description
                "&7Symbolizes collective strength and protection" // Additional lore
        );
        ItemStack[] peridotClusterRecipe = {new CustomItemStack(Peridot, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem peridotClusterItem = new SlimefunItem(RU_GEMS, PeridotCluster, RecipeType.COMPRESSOR, peridotClusterRecipe);
        peridotClusterItem.register(this);
        SlimefunItemStack TopazCluster = new SlimefunItemStack(
                "TOPAZ_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhOWZiZWE3M2YzYzZkZTExYmU2ZWE0YjM1Y2NlYTY1OTZkZTBiNDY4MmI0ZmE1YjFmYjM4NTZjMjU0YjYyMiJ9fX0=", // Material (example, change as needed)
                "&eTopaz Cluster", // Display name
                "&7A cluster of yellow gemstones associated with positivity", // Description
                "&7Known for enhancing creativity and happiness when grouped" // Additional lore
        );
        ItemStack[] topazClusterRecipe = {new CustomItemStack(Topaz, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem topazClusterItem = new SlimefunItem(RU_GEMS, TopazCluster, RecipeType.COMPRESSOR, topazClusterRecipe);
        topazClusterItem.register(this);
        SlimefunItemStack CitrineCluster = new SlimefunItemStack(
                "CITRINE_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFhNmQwOTQxMThiODY2NTRmYjlkMTU5OWQyMjc2MjhhNzFjY2Q3NDE3ZWFmNTUwYTY3OGM1NWRhYzY0OTJkZiJ9fX0=", // Material (example, change as needed)
                "&6Citrine Cluster", // Display name
                "&7A cluster of yellow-orange gemstones associated with abundance", // Description
                "&7Believed to attract wealth and success when clustered" // Additional lore
        );
        ItemStack[] citrineClusterRecipe = {new CustomItemStack(Citrine, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem citrineClusterItem = new SlimefunItem(RU_GEMS, CitrineCluster, RecipeType.COMPRESSOR, citrineClusterRecipe);
        citrineClusterItem.register(this);
        SlimefunItemStack GarnetCluster = new SlimefunItemStack(
                "GARNET_CLUSTER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzIyODYzMjRiNmIwNmMyZDRjMWQ4ZjgzYTliMTQwZDdjYWJkODI4MWRiOTcyYjI0M2QyZTYyZDFhMjNkMDA1NCJ9fX0=", // Material (example, change as needed)
                "&cGarnet Cluster", // Display name
                "&7A cluster of deep red gemstones symbolizing love and passion", // Description
                "&7Known for their collective strength and energy" // Additional lore
        );
        ItemStack[] garnetClusterRecipe = {new CustomItemStack(Garnet, 8), null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem garnetClusterItem = new SlimefunItem(RU_GEMS, GarnetCluster, RecipeType.COMPRESSOR, garnetClusterRecipe);
        garnetClusterItem.register(this);

        //HEHE//
        //CRYSTALS//////ADD SP. LORE//

        SlimefunItemStack JadeCrystal = new SlimefunItemStack(
                "JADE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM1NTAwYTM0OGIwMDY1MWU3ZmY1MWRiOTJhYmI2NDdhNmNjZTM3MzM5N2M0MWFhZmNiZDQwNGNhZjhmY2JmNiJ9fX0=", // Material (example, change as needed)
                "&aJade Crystal", // Display name
                "&7A refined beautiful green gemstone", // Description
                "&7Found deep in the earth" // Additional lore
        );
        ItemMeta JCMeta = JadeCrystal.getItemMeta();
// Add Jade Crystal lore
        List<String> JClore = new ArrayList<>(JCMeta.getLore());
        JClore.add("");
        JClore.add("It's shining");
// Update the lore
        JCMeta.setLore(JClore);
        JadeCrystal.setItemMeta(JCMeta);
        ItemStack[] jadeCrystalRecipe = {JadeCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem jadeCrystalItem = new SlimefunItem(RU_GEMS, JadeCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, jadeCrystalRecipe);
        jadeCrystalItem.register(this);
        SlimefunItemStack MoonstoneCrystal = new SlimefunItemStack(
                "MOONSTONE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhkYzIyZjZhZjUyOTQ5MTBiMmRiZjFjNzRkMDUwYWY3ZTVhY2ZiZDZhYWMwNjE0YzY5ZDYxNTJjMzFiZGY3OSJ9fX0=", // Material (example, change as needed)
                "&7Moonstone Crystal", // Display name
                "&fA refined mystical gemstone from the moon", // Description
                "&7Said to possess powerful magical properties" // Additional lore
        );
        ItemMeta MCMeta = MoonstoneCrystal.getItemMeta();
// Add Moonstone Crystal lore
        List<String> MClore = new ArrayList<>(MCMeta.getLore());
        MClore.add("");
        MClore.add("It's shining");
// Update the lore
        MCMeta.setLore(MClore);
        MoonstoneCrystal.setItemMeta(MCMeta);
        ItemStack[] moonstoneCrystalRecipe = {MoonstoneCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem moonstoneCrystalItem = new SlimefunItem(RU_GEMS, MoonstoneCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, moonstoneCrystalRecipe);
        moonstoneCrystalItem.register(this);
        SlimefunItemStack OnyxCrystal = new SlimefunItemStack(
                "ONYX_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY3MGE5ODk5N2I1ZGE2MDYyZjM1MTliMWZiZDBiZWQ3NzBjZjA3YzlmNjA4M2IzYWYxZTM3OWY5NTQ5M2I1OSJ9fX0=", // Material (example, change as needed)
                "&8Onyx Crystal", // Display name
                "&7A refined dark, glossy gemstone", // Description
                "&7Known for its enhanced protective properties" // Additional lore
        );
        ItemMeta OCMeta = OnyxCrystal.getItemMeta();
// Add Onyx Crystal lore
        List<String> OClore = new ArrayList<>(OCMeta.getLore());
        OClore.add("");
        OClore.add("It's shining");
// Update the lore
        OCMeta.setLore(OClore);
        OnyxCrystal.setItemMeta(OCMeta);
        ItemStack[] onyxCrystalRecipe = {OnyxCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem onyxCrystalItem = new SlimefunItem(RU_GEMS, OnyxCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, onyxCrystalRecipe);
        onyxCrystalItem.register(this);
        SlimefunItemStack RhodoniteCrystal = new SlimefunItemStack(
                "RHODONITE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQxNTQxNDdhOWVlNWMwYTEwZmM0Y2I5OGNlODA2MzFiNGQyNjhlYjMwODgzMGM3NWEwODQzNDhjNmY0OTg0ZCJ9fX0=", // Material (example, change as needed)
                "&dRhodonite Crystal", // Display name
                "&7A refined pink gemstone with black veins", // Description
                "&7Believed to possess heightened properties for promoting love and emotional healing" // Additional lore
        );
        ItemMeta RCMeta = RhodoniteCrystal.getItemMeta();
// Add Rhodonite Crystal lore
        List<String> RClore = new ArrayList<>(RCMeta.getLore());
        RClore.add("");
        RClore.add("It's shining");
// Update the lore
        RCMeta.setLore(RClore);
        RhodoniteCrystal.setItemMeta(RCMeta);
        ItemStack[] rhodoniteCrystalRecipe = {RhodoniteCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem rhodoniteCrystalItem = new SlimefunItem(RU_GEMS, RhodoniteCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, rhodoniteCrystalRecipe);
        rhodoniteCrystalItem.register(this);
        SlimefunItemStack AmethystCrystal = new SlimefunItemStack(
                "AMETHYST_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjljODk5YzE0MzhjNGY4MTkyYTVjN2U2Yjc1OGZhYmE4ZmRkNTQ2MDRlZDg5YmY2MjI3YjZmY2MyYzc1YTQyMyJ9fX0=", // Material (example, change as needed)
                "&5Amethyst Crystal", // Display name
                "&7A refined purple gemstone with a soothing energy", // Description
                "&7Enhanced with clarity and intuition properties" // Additional lore
        );
        ItemMeta ACMeta = AmethystCrystal.getItemMeta();
// Add Amethyst Crystal lore
        List<String> AClore = new ArrayList<>(ACMeta.getLore());
        AClore.add("");
        AClore.add("It's shining");
// Update the lore
        ACMeta.setLore(AClore);
        AmethystCrystal.setItemMeta(ACMeta);
        ItemStack[] amethystCrystalRecipe = {AmethystCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem amethystCrystalItem = new SlimefunItem(RU_GEMS, AmethystCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, amethystCrystalRecipe);
        amethystCrystalItem.register(this);
        SlimefunItemStack TanzaniteCrystal = new SlimefunItemStack(
                "TANZANITE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Y0MzFlOWM3Y2IwNTFhMDFlNmFhYjNmM2QwZGFjODczNmNhOGVhZjE1ZjY1NTQwY2ZhNzk5NWExZTI0MWY1NSJ9fX0=", // Material (example, change as needed)
                "&bTanzanite Crystal", // Display name
                "&7A rare refined blue-violet gemstone", // Description
                "&7Enhanced with mystical properties" // Additional lore
        );
        ItemMeta TCMeta = TanzaniteCrystal.getItemMeta();
// Add Tanzanite Crystal lore
        List<String> TClore = new ArrayList<>(TCMeta.getLore());
        TClore.add("");
        TClore.add("It's shining");
// Update the lore
        TCMeta.setLore(TClore);
        TanzaniteCrystal.setItemMeta(TCMeta);
        ItemStack[] tanzaniteCrystalRecipe = {TanzaniteCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem tanzaniteCrystalItem = new SlimefunItem(RU_GEMS, TanzaniteCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, tanzaniteCrystalRecipe);
        tanzaniteCrystalItem.register(this);
        SlimefunItemStack SapphireCrystal = new SlimefunItemStack(
                "SAPPHIRE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhmOWYzY2VkNzE2Yzc5MGQ1MjQ1ZDRjZDllMmI3NjZhNTU3NjU0MmE4OGQ1YjE0NGFlMWQ3YjA0MjYwMzc4YSJ9fX0=", // Material (example, change as needed)
                "&9Sapphire Crystal", // Display name
                "&7A precious refined blue gemstone", // Description
                "&7Infused with ancient wisdom and power" // Additional lore
        );
        ItemMeta SCMeta = SapphireCrystal.getItemMeta();
// Add Sapphire Crystal lore
        List<String> SClore = new ArrayList<>(SCMeta.getLore());
        SClore.add("");
        SClore.add("It's shining");
// Update the lore
        SCMeta.setLore(SClore);
        SapphireCrystal.setItemMeta(SCMeta);
        ItemStack[] sapphireCrystalRecipe = {SapphireCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem sapphireCrystalItem = new SlimefunItem(RU_GEMS, SapphireCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, sapphireCrystalRecipe);
        sapphireCrystalItem.register(this);
        SlimefunItemStack KyaniteCrystal = new SlimefunItemStack(
                "KYANITE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDRmYzM4MDIyZDc1YjYwMTBiNDZkYTg1MTg2MGJlN2ViMmQ0YjAzNTQ5MDU3NDdkMTIyYTUyMTBkMDE3ZDI3OCJ9fX0=", // Material (example, change as needed)
                "&3Kyanite Crystal", // Display name
                "&7A refined blue mineral of exceptional clarity", // Description
                "&7Radiates tranquility and balance" // Additional lore
        );
        ItemMeta KCMeta = KyaniteCrystal.getItemMeta();
// Add Kyanite Crystal lore
        List<String> KClore = new ArrayList<>(KCMeta.getLore());
        KClore.add("");
        KClore.add("It's shining");
// Update the lore
        KCMeta.setLore(KClore);
        KyaniteCrystal.setItemMeta(KCMeta);
        ItemStack[] kyaniteCrystalRecipe = {KyaniteCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem kyaniteCrystalItem = new SlimefunItem(RU_GEMS, KyaniteCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, kyaniteCrystalRecipe);
        kyaniteCrystalItem.register(this);
        SlimefunItemStack TurquoiseCrystal = new SlimefunItemStack(
                "TURQUOISE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzZhOWE2Y2NiOTZiYWMwZDAyMGQyODQ2MTQ5YTFiZjg5OGM2MTgzMTQ0MjZhNTA4Y2M2YzY1YTRlNTc2OTVmNiJ9fX0=", // Material (example, change as needed)
                "&bTurquoise Crystal", // Display name
                "&7A refined blue-green gemstone prized for its color", // Description
                "&7Brings protection and good fortune to its bearer" // Additional lore
        );
        ItemMeta TqCMeta = TurquoiseCrystal.getItemMeta();
// Add Turquoise Crystal lore
        List<String> TqClore = new ArrayList<>(TqCMeta.getLore());
        TqClore.add("");
        TqClore.add("It's shining");
// Update the lore
        TqCMeta.setLore(TqClore);
        TurquoiseCrystal.setItemMeta(TqCMeta);
        ItemStack[] turquoiseCrystalRecipe = {TurquoiseCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem turquoiseCrystalItem = new SlimefunItem(RU_GEMS, TurquoiseCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, turquoiseCrystalRecipe);
        turquoiseCrystalItem.register(this);
        SlimefunItemStack EmeraldCrystal = new SlimefunItemStack(
                "EMERALD_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWI0ZTNmNjc4ZGI5OWVmOGVmODYwNWU5NjdkYmNhZDBlODNiYzJlM2M1NDIyMTQxNDliZDEzMTYzMmE3MjE1YSJ9fX0=", // Material (example, change as needed)
                "&aEmerald Crystal", // Display name
                "&7A refined rare green gemstone valued for its beauty and rarity", // Description
                "&7Brings prosperity and harmony to its possessor" // Additional lore
        );
        ItemMeta ECMeta = EmeraldCrystal.getItemMeta();
// Add Emerald Crystal lore
        List<String> ECllore = new ArrayList<>(ECMeta.getLore());
        ECllore.add("");
        ECllore.add("It's shining");
// Update the lore
        ECMeta.setLore(ECllore);
        EmeraldCrystal.setItemMeta(ECMeta);
        ItemStack[] emeraldCrystalRecipe = {EmeraldCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem emeraldCrystalItem = new SlimefunItem(RU_GEMS, EmeraldCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, emeraldCrystalRecipe);
        emeraldCrystalItem.register(this);
        SlimefunItemStack PeridotCrystal = new SlimefunItemStack(
                "PERIDOT_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTMzNzBjYzkzZTYzZGU3MzljMTcyNzZiNzY3ZDBhNDAyYjM5NDFmM2ExZDBjZGFmN2M5NzM1MzhhYWMzNjM4MiJ9fX0=", // Material (example, change as needed)
                "&aPeridot Crystal", // Display name
                "&7A refined green gemstone often found in volcanic areas", // Description
                "&7Symbolizes strength and protection, brings luck and prosperity" // Additional lore
        );
        ItemMeta PCMeta = PeridotCrystal.getItemMeta();
// Add Peridot Crystal lore
        List<String> PCllore = new ArrayList<>(PCMeta.getLore());
        PCllore.add("");
        PCllore.add("It's shining");
// Update the lore
        PCMeta.setLore(PCllore);
        PeridotCrystal.setItemMeta(PCMeta);
        ItemStack[] peridotCrystalRecipe = {PeridotCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem peridotCrystalItem = new SlimefunItem(RU_GEMS, PeridotCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, peridotCrystalRecipe);
        peridotCrystalItem.register(this);
        SlimefunItemStack TopazCrystal = new SlimefunItemStack(
                "TOPAZ_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA2ODYyZjE1NGVjODliYWZjMjE0ZDQ0MmJkOGNmMzYwZmFmZjNlNTg2MDMzMDdiMTE4YTE2Mjc2NzI2OGE2In19fQ==", // Material (example, change as needed)
                "&eTopaz Crystal", // Display name
                "&7A refined yellow gemstone associated with positivity", // Description
                "&7Thought to promote creativity and happiness" // Additional lore
        );
        ItemMeta TpCMeta = TopazCrystal.getItemMeta();
// Add Topaz Crystal lore
        List<String> TpClore = new ArrayList<>(TpCMeta.getLore());
        TpClore.add("");
        TpClore.add("It's shining");
// Update the lore
        TpCMeta.setLore(TpClore);
        TopazCrystal.setItemMeta(TpCMeta);
        ItemStack[] topazCrystalRecipe = {TopazCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem topazCrystalItem = new SlimefunItem(RU_GEMS, TopazCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, topazCrystalRecipe);
        topazCrystalItem.register(this);
        SlimefunItemStack CitrineCrystal = new SlimefunItemStack(
                "CITRINE_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZThhNjRjOGZhZjU1MzFjYjM2ZGEzMDMxMzhiYTc4MTM5ZDE3YzllMzUyM2UxNTZjNzFkMGY2OWRiM2Y1MjljYiJ9fX0=", // Material (example, change as needed)
                "&6Citrine Crystal", // Display name
                "&7A refined yellow-orange gemstone associated with abundance", // Description
                "&7Believed to attract wealth and success" // Additional lore
        );
        ItemMeta CCMeta = CitrineCrystal.getItemMeta();
// Add Citrine Crystal lore
        List<String> CClore = new ArrayList<>(CCMeta.getLore());
        CClore.add("");
        CClore.add("It's shining");
// Update the lore
        CCMeta.setLore(CClore);
        CitrineCrystal.setItemMeta(CCMeta);
        ItemStack[] citrineCrystalRecipe = {CitrineCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem citrineCrystalItem = new SlimefunItem(RU_GEMS, CitrineCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, citrineCrystalRecipe);
        citrineCrystalItem.register(this);
        SlimefunItemStack GarnetCrystal = new SlimefunItemStack(
                "GARNET_CRYSTAL", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4MTIxMTZiZmY0ZGNkYzdjZDg1ZDY3Nzk5NzRjZWU3YTM2ODA0NjFmYmZlZGQ1ZTk3MWE4ZmI2MTJlZmFkIn19fQ==", // Material (example, change as needed)
                "&cGarnet Crystal", // Display name
                "&7A refined deep red gemstone symbolizing love and passion", // Description
                "&7Associated with strength and energy" // Additional lore
        );
        ItemMeta GCMeta = GarnetCrystal.getItemMeta();
// Add Garnet Crystal lore
        List<String> GClore = new ArrayList<>(GCMeta.getLore());
        GClore.add("");
        GClore.add("It's shining");
// Update the lore
        GCMeta.setLore(GClore);
        GarnetCrystal.setItemMeta(GCMeta);
        ItemStack[] garnetCrystalRecipe = {GarnetCluster, null, null, null, null, null, null, null, null}; // Recipe ingredients, change as needed
        SlimefunItem garnetCrystalItem = new SlimefunItem(RU_GEMS, GarnetCrystal, RecipeType.HEATED_PRESSURE_CHAMBER, garnetCrystalRecipe);
        garnetCrystalItem.register(this);

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
            // Add the "End Sword Identifier" to the BSlore
            List<String> ESlore = new ArrayList<>(itemMeta.getLore());
            ESlore.add("");
            ESlore.add("It's screaming at you");
            itemMeta.setLore(ESlore);
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
            // Add the "Augmented End Sword Identifier" to the BSlore
            List<String> AESlore = new ArrayList<>(AESMeta.getLore());
            AESlore.add("");
            AESlore.add("It's staring at you");
            AESMeta.setLore(AESlore);
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
            // Add the "Ice Blaster Identifier" to the BSlore
            List<String> IBlore = new ArrayList<>(IBMeta.getLore());
            IBlore.add("");
            IBlore.add("Don't snipe me pls");
            IBMeta.setLore(IBlore);
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
            // Add the "Icicle Staff Identifier" to the BSlore
            List<String> ISlore = new ArrayList<>(ISMeta.getLore());
            ISlore.add("");
            ISlore.add("Don't freeze me pls");
            ISMeta.setLore(ISlore);
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

        SlimefunItemStack GlacialStaff = new SlimefunItemStack("GLACIAL_STAFF", Material.IRON_HOE, "&bGlacial Staff", "", "&e&lRight Click to Cast", "&eSynthesizes energy itself", "&cPierces through Shields", "&cSplash damage", "&bPortable fire extinguisher");
        ItemMeta GSmeta = GlacialStaff.getItemMeta();
        if (GSmeta != null) {
            // Enchants
            GSmeta.addEnchant(Enchantment.LOYALTY, 3, true);
            GlacialStaff.setItemMeta(GSmeta);
        }
        List<String> GSlore = new ArrayList<>(GSmeta.getLore());
        GSlore.add("");
        GSlore.add("Run! Take cover!");
        if (GSmeta != null) {
            GSmeta.setLore(GSlore);
            GlacialStaff.setItemMeta(GSmeta);
        }
        ItemStack[] glacialStaffRecipe = {
                ExplosiveCore, ExplosiveCore, ExplosiveCore,
                ExplosiveCore, IcicleStaff, ExplosiveCore,
                ExplosiveCore, ExplosiveCore, ExplosiveCore
        };
        SlimefunItem glacialStaffItem = new SlimefunItem(RU_TOOLS, GlacialStaff, RecipeType.ARMOR_FORGE, glacialStaffRecipe);
        glacialStaffItem.register(this);

        //HEHE//

        SlimefunItemStack BarrierSword = new SlimefunItemStack("BARRIER_SWORD", Material.IRON_SWORD, "&6Barrier Sword", "", "&e&lRight Click Ability", "&e&lInvulnerability", "&e&l15 Second Duration", "&e&lCooldown 75 Seconds");
        ItemMeta BSMeta = BarrierSword.getItemMeta();
        // Add BSlore
        List<String> BSlore = new ArrayList<>(BSMeta.getLore());
        BSlore.add("");
        BSlore.add("Basic Defensive Spell");
        if (BSMeta != null) {
            BSMeta.setLore(BSlore);
            BarrierSword.setItemMeta(BSMeta);
        }
        // Apply enchantments
        BSMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        BarrierSword.setItemMeta(BSMeta);
        ItemStack[] BarrierSwordRecipe = {
                null, ProtectionCore, null,
                null, ProtectionCore, null,
                null, ReinforcedHandle, null
        };
        SlimefunItem BarrierSwordItem = new SlimefunItem(RU_TOOLS, BarrierSword, RecipeType.ARMOR_FORGE, BarrierSwordRecipe);
        BarrierSwordItem.register(this);

        //HEHE//

        SlimefunItemStack APHEMissile = new SlimefunItemStack(
                "APHE_MISSILE", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDg5ZGNjNTViNDhlMjcwODcyZDI4NTc2YTc5MDA1NTQwYmVkNWYwYWU1MzAxNWNmZGM5NWI3NTFkMTZmYTllMiJ9fX0=", // Material
                "&6APHE Missile", // Display name
                "", "&e&lRight Click to Fire", "&eATA / ATG Missile", "&cUse responsibly", "&eDoesn't break blocks, Build-Safe!" // Description/lore
        );
        ItemMeta APHEMeta = APHEMissile.getItemMeta();
        // Add APHElore
        List<String> APHELore = new ArrayList<>(APHEMeta.getLore());
        APHELore.add("");
        APHELore.add("DO NOT USE INDOORS");
        if (APHEMeta != null) {
            APHEMeta.setLore(APHELore);
            APHEMissile.setItemMeta(APHEMeta);
        }
        ItemStack[] APHEMissileRecipe = {
                APHE, APHE, APHE,
                APHE, IceAccelerator, APHE,
                APHE, APHE, APHE
        }; // Recipe ingredients, change as needed
        SlimefunItem APHEMissileItem = new SlimefunItem(RU_TOOLS, APHEMissile, RecipeType.ENHANCED_CRAFTING_TABLE, APHEMissileRecipe);
        APHEMissileItem.register(this);

        //HEHE//

        SlimefunItemStack MissileLauncher = new SlimefunItemStack(
                "MISSILE_LAUNCHER", // Item ID
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFiNmVlNWJiZTVhZDQyOTY4MGMxYzE1Y2Y0MjBmOTgxMWUxMTRiNzY4NTRmODk5ZjBlZjA4ZmRlMzMyNzk4YyJ9fX0=", // Material
                "&eMissile Launcher", // Display name
                "", "&e&lRight Click to Fire", "&eUses APHE Missiles", "&cUse responsibly", "Faster ROF than manual" // Description/lore
        );
        ItemMeta MLMeta = MissileLauncher.getItemMeta();
        // Add MLlore
        List<String> MLLore = new ArrayList<>(MLMeta.getLore());
        MLLore.add("");
        MLLore.add("FREEEEBIRD!!!");
        if (MLMeta != null) {
            MLMeta.setLore(MLLore);
            MissileLauncher.setItemMeta(MLMeta);
        }
        ItemStack[] MissileLauncherRecipe = {
                LinearAccelerator, null, null,
                LauncherTube, LauncherTube, LauncherTube,
                null, null, ReinforcedHandle
        }; // Recipe ingredients, change as needed
        SlimefunItem MissileLauncherItem = new SlimefunItem(RU_TOOLS, MissileLauncher, RecipeType.NULL, MissileLauncherRecipe);
        MissileLauncherItem.register(this);

        //HEHE//

        SlimefunItemStack CrystalSniper = new SlimefunItemStack(
                "CRYSTAL_SNIPER", // Item ID
                Material.DIAMOND_HOE, // Material
                "&bCrystal Sniper", // Display name
                "", // Empty lore
                "&e&lLeft Click to Shoot", // Description
                "&e&lRight Click to Zoom", // Additional lore
                "&dHarness the power of crystals", // Additional lore
                "&eVery long range", // Additional lore
                "&e6s cooldown" // Additional lore
        );
        ItemMeta CSMeta = CrystalSniper.getItemMeta();
        if (CSMeta != null) {
            List<String> CSlore = new ArrayList<>(CSMeta.getLore());
            CSlore.add("");
            CSlore.add("This ain't Pixel Gun!");
            CSMeta.setLore(CSlore);
            CrystalSniper.setItemMeta(CSMeta);
        }
        ItemStack[] crystalSniperRecipe = {
                new ItemStack(Material.PISTON), LinearAccelerator, LinearAccelerator,
                MagicalContainer, ReinforcedHandle, new ItemStack(Material.SPYGLASS),
                ReinforcedHandle, ReinforcedHandle, new ItemStack(Material.ARMOR_STAND)
        };
        SlimefunItem crystalSniperItem = new SlimefunItem(RU_TOOLS, CrystalSniper, RecipeType.ARMOR_FORGE, crystalSniperRecipe);
        crystalSniperItem.register(this);

        //----------PANELS BELOW----------//

        // Template for future gens
        //GEN I
        int LPIdayEnergy = 4; // Energy generated during the day
        int LPInightEnergy = 4; // Energy generated during the night
        int LPIcapacity = 8; // Buffer capacity
        SlimefunItemStack LunisolarPanelI = new SlimefunItemStack("LUNISOLARPANELI", Material.DAYLIGHT_DETECTOR, "&bLuni&esolar &7Panel I",
                "", "&9Works all-day", "",
                "&7⇒ &e⚡ &7" + LPIcapacity + "J Buffer",
                "&7⇒ &e⚡ &7" + LPIdayEnergy + "J/s (Day)",
                "&7⇒ &e⚡ &7" + LPInightEnergy + "J/s (Night)"); // w/ multi-line lore
        RecipeType LPIrecipeType = RecipeType.ENHANCED_CRAFTING_TABLE;
        ItemStack[] recipe = {
                SlimefunItems.SOLAR_PANEL, LapisCrystal, SlimefunItems.SOLAR_PANEL,
                RedstoneCrystal, UnstablePearl, RedstoneCrystal,
                SlimefunItems.SOLAR_PANEL, LapisCrystal, SlimefunItems.SOLAR_PANEL
        };
        // Create and register the Lunisolar Panel I
        new SolarGenerator(RU_PANELS, LPIdayEnergy, LPInightEnergy, LunisolarPanelI, LPIrecipeType, recipe, LPIcapacity).register(this);

        // GEN II
        int LPIIdayEnergy = 16; // Energy generated during the day
        int LPIInightEnergy = 16; // Energy generated during the night
        int LPIIcapacity = 32; // Buffer capacity
        SlimefunItemStack LunisolarPanelII = new SlimefunItemStack("LUNISOLARPANELII", Material.DAYLIGHT_DETECTOR, "&bLuni&esolar &7Panel II",
                "", "&9Works all-day", "",
                "&7⇒ &e⚡ &7" + LPIIcapacity + "J Buffer",
                "&7⇒ &e⚡ &7" + LPIIdayEnergy + "J/s (Day)",
                "&7⇒ &e⚡ &7" + LPIInightEnergy + "J/s (Night)"); // w/ multi-line lore
        RecipeType LPIIrecipeType = RecipeType.ENHANCED_CRAFTING_TABLE;
        ItemStack[] LPIIRecipe = {
                LunisolarPanelI, LapisCrystal, LunisolarPanelI,
                RedstoneCrystal, MagicalContainer, RedstoneCrystal,
                LunisolarPanelI, LapisCrystal, LunisolarPanelI
        };
        // Create and register the Lunisolar Panel II
        new SolarGenerator(RU_PANELS, LPIIdayEnergy, LPIInightEnergy, LunisolarPanelII, LPIIrecipeType, LPIIRecipe, LPIIcapacity).register(this);

        //----------MACHINES BELOW----------//

        //HEHE//

        //----------ENCHANTS BELOW----------//

        //----------LISTENERS BELOW----------//

        //EndSwordSkillListener FALLBACK_REWRITE//
        getServer().getPluginManager().registerEvents(new EndSwordSkillListener(this, EndSword), this);

        //AugmentedEndSwordSkillListener FALLBACK_REWRITE//
        getServer().getPluginManager().registerEvents(new AugmentedEndSwordSkillListener(this, AugmentedEndSword), this);

        // Register the IceBlasterListener as a listener
        getServer().getPluginManager().registerEvents(new IceBlasterListener(this), this);

        // Register the IcicleStaffListener as a listener
        getServer().getPluginManager().registerEvents(new IcicleStaffListener(this), this);

        // Register the GlacialStaffListener as a listener
        getServer().getPluginManager().registerEvents(new GlacialStaffListener(this), this);

        // Register BarrierSwordSkillListener as a listener
        getServer().getPluginManager().registerEvents(new BarrierSwordSkillListener(this), this);

        // Register the BlockDropFeature as a listener
        getServer().getPluginManager().registerEvents(new BlockDropFeature(this), this);

        // Register the APHEMissileListener as a listener
        getServer().getPluginManager().registerEvents(new APHEMissileListener(this), this);

        // Register the MissileLauncherListener as a listener
        getServer().getPluginManager().registerEvents(new MissileLauncherListener(this), this);

        // Register the UnplaceableItemPlacementListener as a listener
        getServer().getPluginManager().registerEvents(new UnplaceableItemPlacementListener(), this);

        // Registering the CrystalSniperListener as a listener
        getServer().getPluginManager().registerEvents(new CrystalSniperListener(this), this);


    }



    // Helper method to generate a random coordinate within a certain range
    private double getRandomCoordinate() {
        // Replace min and max values with your desired range
        double min = -100;
        double max = 100;
        return min + Math.random() * (max - min);
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

/*
MC Server IPs
Java IPs
rules-story.gl.at.ply.gg:64745
147.185.221.16:64745

Bedrock IPs
etc-cry.gl.at.ply.gg:9285
147.185.221.16:9285

Nkahost sa pc ko nakakabit sa router
 */

//testing plugin...

/*

//MISSILE LAUNCHER
//MAKE NEW MATERIALS
SlimefunItemStack MissileLauncher = new SlimefunItemStack(
        "MISSILE_LAUNCHER", // Item ID
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDg5ZGNjNTViNDhlMjcwODcyZDI4NTc2YTc5MDA1NTQwYmVkNWYwYWU1MzAxNWNmZGM5NWI3NTFkMTZmYTllMiJ9fX0=", // Material
        "&6Missile Launcher", // Display name
        "", "&e&lRight Click to Fire", "&eUses APHE Missiles", "&cUse responsibly", "Faster ROF than manual" // Description/lore
);
ItemMeta MLMeta = MissileLauncher.getItemMeta();
// Add APHElore
List<String> MLLore = new ArrayList<>(MLMeta.getLore());
        MLLore.add("");
        MLLore.add("hehe");
        if (MLMeta != null) {
        MLMeta.setLore(MLLore);
            MissileLauncher.setItemMeta(MLMeta);
        }
ItemStack[] MissileLauncherRecipe = {
        LinearAccelerator, null, null,
        LauncherTube, LauncherTube, LauncherTube,
        null, null, ReinforcedHandle
}; // Recipe ingredients, change as needed
SlimefunItem MissileLauncherItem = new SlimefunItem(RU_TOOLS, MissileLauncher, RecipeType.NULL, MissileLauncherRecipe);
        MissileLauncherItem.register(this);

        */