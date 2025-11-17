package net.somyk.veinsmelt.listeners;

import net.somyk.veinsmelt.Runes;
import net.somyk.veinsmelt.config.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class LootGenerateListener implements Listener {
    private static Random random = new Random();

    @EventHandler
    public void onLootGenerated(LootGenerateEvent event){
        String key = event.getLootTable().key().value();
        if(key.contains("simple_dungeon") || key.contains("nether_bridge")){
            if(random.nextFloat() <= Settings.VEINSMELT_SPAWN_CHANCE){
                List<ItemStack> loot = event.getLoot();
                loot.add(Runes.VEIN_SMELT.clone());
//                event.setLoot(loot);
            }
        }
    }
}
