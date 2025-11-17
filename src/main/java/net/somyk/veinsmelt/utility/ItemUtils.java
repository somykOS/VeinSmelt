package net.somyk.veinsmelt.utility;

import org.bukkit.inventory.ItemStack;

public class ItemUtils {

    public static boolean isAirOrNull(ItemStack i){
        return i == null || i.getType().toString().contains("AIR");
    }
}
