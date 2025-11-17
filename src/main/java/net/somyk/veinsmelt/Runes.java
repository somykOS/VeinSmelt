package net.somyk.veinsmelt;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

import static net.somyk.veinsmelt.utility.Keys.*;

public class Runes {
    public static HashSet<ItemStack> REGISTRY = new HashSet<>();

    public static final ItemStack base = new ItemStack(Material.ENCHANTED_BOOK);
    public static ItemStack VEIN_SMELT = base.clone();

    public static void register(){}

    static {
        VEIN_SMELT.editPersistentDataContainer(persistentDataContainer -> {
            persistentDataContainer.set(VEIN_SMELT_KEY, PersistentDataType.BOOLEAN, true);
        });
        ItemMeta temp = VEIN_SMELT.getItemMeta();
        temp.setLore(List.of("Ancient Rune infused with smelting power..."));
        VEIN_SMELT.setItemMeta(temp);
        REGISTRY.add(VEIN_SMELT);
    }

    @Nullable
    public static ItemStack getRune(String type) {
        for(ItemStack i : REGISTRY){
            if(i.getPersistentDataContainer().has(new NamespacedKey(VeinSmelt.getInstance(), type))) return i;
        }
        return null;
    }
}
