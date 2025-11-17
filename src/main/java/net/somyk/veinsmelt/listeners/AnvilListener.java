package net.somyk.veinsmelt.listeners;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.somyk.veinsmelt.VeinSmelt;
import net.somyk.veinsmelt.utility.ItemUtils;
import net.somyk.veinsmelt.utility.Keys;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import static net.somyk.veinsmelt.utility.Keys.VEIN_SMELT_KEY;

public class AnvilListener implements Listener {

    @EventHandler
    public void onAnvilTake(InventoryClickEvent e){
        if (!(e.getClickedInventory() instanceof AnvilInventory a)) return;
        ItemStack item1 = a.getItem(0);
        ItemStack item2 = a.getItem(1);
        ItemStack result = a.getItem(2);
        if (ItemUtils.isAirOrNull(item1) || ItemUtils.isAirOrNull(item2) || ItemUtils.isAirOrNull(result) || e.getRawSlot() != 2) return;
        VeinSmelt.getInstance().getServer().getScheduler().runTaskLater(VeinSmelt.getInstance(), () -> {
            a.setItem(1, null);
        }, 1L);
    }

    @EventHandler
    public void onAnvilUse(PrepareAnvilEvent e) {
        ItemStack item1 = e.getInventory().getFirstItem();
        ItemStack item2 = e.getInventory().getSecondItem();
        if (ItemUtils.isAirOrNull(item1)) return;
        if (ItemUtils.isAirOrNull(item2)) return;
        HumanEntity entity = e.getViewers().isEmpty() ? null : e.getViewers().getFirst();
        if (entity == null) return;

        if(item2.getType().equals(Material.ENCHANTED_BOOK) && item2.getPersistentDataContainer().has(Keys.VEIN_SMELT_KEY)){
            if(item1.getType().getKey().value().contains("pickaxe")){
                ItemStack result = item1.clone();

                result.editPersistentDataContainer(persistentDataContainer -> {
                    persistentDataContainer.set(VEIN_SMELT_KEY, PersistentDataType.BOOLEAN, true);
                });
                result.setData(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true);

//                e.getInventory().setResult(result);
                entity.dropItem(result);
                item2.subtract();
                item1.subtract();
            }
        }
    }
}
