package net.somyk.veinsmelt;

import net.somyk.veinsmelt.command.GiveCommand;
import net.somyk.veinsmelt.config.ConfigManager;
import net.somyk.veinsmelt.config.Settings;
import net.somyk.veinsmelt.listeners.AnvilListener;
import net.somyk.veinsmelt.listeners.BlockBreakEventListener;
import net.somyk.veinsmelt.listeners.LootGenerateListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VeinSmelt extends JavaPlugin {

    @Override
    public void onEnable() {
        Runes.register();

        setupConfig();

        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        getServer().getPluginManager().registerEvents(new LootGenerateListener(), this);

        Objects.requireNonNull(getCommand("rune")).setExecutor(new GiveCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupConfig() {
        ConfigManager manager = ConfigManager.getInstance();

        manager.getConfig("config.yml").saveDefaultConfig();
        manager.reloadConfig("config.yml");

        Settings.load();

        getLogger().info("Configuration loaded successfully!");
    }

    public static VeinSmelt getInstance(){
        return getPlugin(VeinSmelt.class);
    }
}
