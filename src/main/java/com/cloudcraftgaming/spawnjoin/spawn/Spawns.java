package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawns implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawns")) {
            if (!(sender.hasPermission("SpawnJoin.use.spawns"))) {
                sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
            } else {
                if (!FileManager.getListYml().contains("Spawns")) {
                    String msg = MessageManager.getMessageYml().getString("Spawn.NoSet");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else {
                    if (!(sender instanceof Player)) {
                        String spawnHeading = MessageManager.getMessageYml().getString("Spawn.SpawnsHeading");
                        String original = FileManager.getListYml().getString("Spawns");
                        String replaced = original.replace("[", "").replace("]", "");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', spawnHeading));
                        sender.sendMessage(ChatColor.AQUA + replaced);
                    } else {
                        String spawnHeading = MessageManager.getMessageYml().getString("Spawn.SpawnsHeading");
                        String original = FileManager.getListYml().getString("Spawns");
                        String replaced = original.replace("[", "").replace("]", "");
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', spawnHeading));
                        sender.sendMessage(ChatColor.AQUA + replaced);
                    }
                }
            }
        }
        return false;
    }
}