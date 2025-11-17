package net.somyk.veinsmelt.listeners;

import net.somyk.veinsmelt.utility.BlockUtils;
import net.somyk.veinsmelt.utility.Keys;
import net.somyk.veinsmelt.utility.SmeltMap;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;

public class BlockBreakEventListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;

        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
        if (!(tool.getType().getKey().value().endsWith("_pickaxe") && tool.getPersistentDataContainer().has(Keys.VEIN_SMELT_KEY))) {
            return;
        }

        Block startBlock = event.getBlock();
        World world = startBlock.getWorld();

        List<Block> vein = BlockUtils.findVeinInRadius(startBlock, BlockUtils.vein_filter, 27, 3);
        vein.remove(startBlock);
        if (!vein.isEmpty()) event.setDropItems(false);

        for (Block blockInVein : vein) {
            Collection<ItemStack> drops = blockInVein.getDrops(tool);
            for (ItemStack drop : drops) {
                Material smelted = SmeltMap.getByRaw(drop.getType());
                if (smelted != null) {
                    ItemStack smeltedDrop = new ItemStack(smelted, drop.getAmount());
                    world.dropItemNaturally(blockInVein.getLocation(), smeltedDrop);
                } else world.dropItemNaturally(blockInVein.getLocation(), drop);
            }

            blockInVein.setType(Material.AIR);
        }
    }

//    @EventHandler
//    public void onBlockBreak(BlockBreakEvent event){
//        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
//        if(event.isCancelled() || !(itemStack.getType().getKey().value().contains("pickaxe") && itemStack.getPersistentDataContainer().has(Keys.VEIN_SMELT_KEY))) return;
//
//        Block block = event.getBlock();
//        List<Block> vein = BlockUtils.findVeinInRadius(block, BlockUtils.vein_filter, 27, 3);
//        vein.remove(block);
//        for (Block blockInVein : vein) {
//            blockInVein.breakNaturally(itemStack);
//        }
//    }

//    @EventHandler
//    public void onBlockDrop(BlockDropItemEvent event){
//        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();
//        if(event.isCancelled() || !(itemStack.getType().getKey().value().contains("pickaxe") && itemStack.getPersistentDataContainer().has(Keys.VEIN_SMELT_KEY))) return;
//
//        List<Item> dropItems = event.getItems();
//        for (Item drop : dropItems) {
//            System.out.println(drop.getItemStack().getType());
//            if(getByRaw(drop.getItemStack().getType()) instanceof Material smelted){
//                drop.setItemStack(new ItemStack(smelted));
//                System.out.println(smelted);
//            }
//        }
//    }
}
