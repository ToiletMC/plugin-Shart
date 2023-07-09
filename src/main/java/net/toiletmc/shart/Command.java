package net.toiletmc.shart;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.toiletmc.shart.tasks.ScatterRainbowShartTask;
import net.toiletmc.shart.tasks.ScatterShartTask;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player && sender.hasPermission("shart.command.shart")) {
                new ScatterShartTask(((Player) sender).getPlayer());
            }
        } else if (sender.hasPermission("shart.command.shart.others")) {
            String playerName = args[0];
            if (!isPlayerOnline(playerName)) {
                sender.sendMessage(Component.text(playerName + " 当前并不在线！").color(NamedTextColor.RED));
                return true;
            }

            if (args.length == 1) {
                new ScatterShartTask((Bukkit.getPlayer(playerName)));
            } else if (args.length == 2) {
                String type = args[1];

                if (type.equals("rainbow")) {
                    new ScatterRainbowShartTask((Bukkit.getPlayer(playerName)));
                } else {
                    sender.sendMessage(Component.text("错误的类型").color(NamedTextColor.RED));
                }
            }
        } else {
            sender.sendMessage(Component.text("未知的指令").color(NamedTextColor.RED));
        }

        return true;
    }

    private boolean isPlayerOnline(String playerName) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(playerName)) return true;
        }
        return false;
    }
}
