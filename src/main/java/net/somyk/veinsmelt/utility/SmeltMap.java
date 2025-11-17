package net.somyk.veinsmelt.utility;

import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SmeltMap {
    private static final Map<Material, Material> LOOKUP_BY_RAW_MATERIAL = new HashMap<>();

    static {
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_IRON, Material.IRON_INGOT);
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_IRON_BLOCK, Material.IRON_BLOCK);
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_COPPER, Material.COPPER_INGOT);
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_COPPER_BLOCK, Material.COPPER_BLOCK);
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_GOLD, Material.GOLD_INGOT);
        LOOKUP_BY_RAW_MATERIAL.put(Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK);
    }

    @Nullable
    public static Material getByRaw(Material raw) {
        return LOOKUP_BY_RAW_MATERIAL.get(raw);
    }
}
