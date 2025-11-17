package net.somyk.veinsmelt.config;

import net.somyk.veinsmelt.VeinSmelt;

import java.util.HashMap;

public class ConfigManager {

    private final VeinSmelt plugin;
    private final HashMap<String, Config> configs = new HashMap<>();
    private static ConfigManager manager = null;

    public ConfigManager() {
        plugin = VeinSmelt.getInstance();
    }

    public static ConfigManager getInstance() {
        if (manager == null) {
            manager = new ConfigManager();
        }
        return manager;
    }

    public HashMap<String, Config> getConfigs() {
        return configs;
    }

    public Config getConfig(String name) {
        if (!configs.containsKey(name))
            configs.put(name, new Config(name));

        return configs.get(name);
    }

    public Config saveConfig(String name) {
        return getConfig(name).save();
    }

    public Config reloadConfig(String name) {
        return getConfig(name).reload();
    }

}
