package net.somyk.veinsmelt.config;

import org.bukkit.configuration.file.YamlConfiguration;

public class Settings {

    public static float VEINSMELT_SPAWN_CHANCE;

    public static void load() {
        YamlConfiguration config = ConfigManager.getInstance().getConfig("config.yml").get();

        VEINSMELT_SPAWN_CHANCE = (float) config.getDouble("veinSmeltSpawnChance", 0.05F);
    }
}