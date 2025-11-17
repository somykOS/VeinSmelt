package net.somyk.veinsmelt.utility;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.*;
import java.util.function.Predicate;

public class BlockUtils {
    public static final Set<Material> vein_filter = new HashSet<>(Arrays.asList(
            Material.COAL_ORE,
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE,
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE,
            Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE,
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.ANCIENT_DEBRIS
    ));

    public static List<Block> getBlockVein(Block origin, Set<Material> filter, int limit, Predicate<Block> predicate) {
        if (!filter.contains(origin.getType()) || !predicate.test(origin)) {
            return Collections.emptyList();
        }

        List<Block> foundVein = new ArrayList<>();
        Queue<Block> queue = new ArrayDeque<>();
        Set<Block> visited = new HashSet<>();

        queue.add(origin);
        visited.add(origin);

        while (!queue.isEmpty() && foundVein.size() < limit) {
            Block currentBlock = queue.poll();
            foundVein.add(currentBlock);

            for (BlockFace face : SCAN_FACES) {
                Block neighbor = currentBlock.getRelative(face);

                if (visited.contains(neighbor)) {
                    continue;
                }

                if (filter.contains(neighbor.getType()) && predicate.test(neighbor) && origin.getType() == neighbor.getType()) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return foundVein;
    }

    public static List<Block> getBlockVein(Block origin, Set<Material> filter, int limit) {
        return getBlockVein(origin, filter, limit, block -> true);
    }

    public static List<Block> findVeinInRadius(Block origin, Set<Material> filter, int limit, int radius) {
        int originX = origin.getX();
        int originY = origin.getY();
        int originZ = origin.getZ();

        int minX = originX - radius;
        int maxX = originX + radius;
        int minY = originY - radius;
        int maxY = originY + radius;
        int minZ = originZ - radius;
        int maxZ = originZ + radius;

        Predicate<Block> areaPredicate = block -> {
            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            return x >= minX && x <= maxX &&
                    y >= minY && y <= maxY &&
                    z >= minZ && z <= maxZ;
        };

        return getBlockVein(origin, filter, limit, areaPredicate);
    }

    private static final BlockFace[] SCAN_FACES = {
            BlockFace.UP, BlockFace.DOWN,
            BlockFace.NORTH, BlockFace.SOUTH,
            BlockFace.EAST, BlockFace.WEST
    };
}
