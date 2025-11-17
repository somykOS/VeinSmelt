package net.somyk.veinsmelt.command;

import net.somyk.veinsmelt.Runes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GiveCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!sender.isOp() || !command.testPermission(sender)) return false;
        if(args.length > 2) return false;

        String targetName = args[0];
        String runeType = args[1];

        Player target = sender.getServer().getPlayer(targetName);
        ItemStack rune = Runes.getRune(runeType);

        if(target == null || rune == null) return false;

        target.give(rune);
        sender.sendRichMessage("<green>The rune is successfully given to the player</green>");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
}
