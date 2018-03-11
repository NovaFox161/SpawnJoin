package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn
        implements CommandExecutor {
    Main plugin;

    public Spawn(Main instance) {
        plugin = instance;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            final String prefix = MessageManager.getPrefix();
            String perm = MessageManager.getNoPermMessage();
            if (args.length < 1) {
                if (sender.hasPermission("SpawnJoin.use.spawn")) {
                    if (!(sender instanceof Player)) {
                        String consoleMessage = MessageManager.getMessageYml().getString("Spawn.Console");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', consoleMessage));
                    } else {
                        Player player = (Player) sender;
                        String currentWorld = player.getWorld().getName();
                        String worldName = LocationChecker.determineSpawnWorld(currentWorld);
                        DelayChecker.spawnDelayCheck(worldName, player);
                    }
                } else {
                    sender.sendMessage(prefix + perm);
                }
            } else if (args.length == 1) {
                String playerName = args[0];
                if (sender.hasPermission("SpawnJoin.use.spawnothers")) {
                    Player target = Bukkit.getPlayer(playerName);
                    if (!(target == null)) {
                        String targetCurrentWorld = target.getWorld().getName();
                        String targetWorldName = LocationChecker.determineSpawnWorld(targetCurrentWorld);
                        if (LocationChecker.spawnOnFile(targetWorldName)) {
                            Teleporter.spawn(targetWorldName, target);
                        } else {
                            Teleporter.spawn(Bukkit.getWorld(targetWorldName), target);
                        }
                        String targetSpawnOr = MessageManager.getMessageYml().getString("Spawn.Target");
                        String targetSpawn = targetSpawnOr.replaceAll("%target%", target.getName());
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', targetSpawn));
                    } else {
                        String offline = MessageManager.getMessageYml().getString("Notifications.PlayerOffline");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', offline));
                    }
                } else {
                    sender.sendMessage(prefix + perm);
                }
            } else if (args.length > 1) {
                String tooManyArgs = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', tooManyArgs));
            }
        }
        return false;
    }
}