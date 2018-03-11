package com.cloudcraftgaming.spawnjoin.lobby;

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

public class Lobby
        implements CommandExecutor {
    Main plugin;

    public Lobby(Main instance) {
        plugin = instance;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("lobby")) {
            if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
                if (!(sender.hasPermission("SpawnJoin.use.lobby"))) {
                    sender.sendMessage(prefix + perm);
                } else {
                    if (args.length < 1) {
                        if (!(sender instanceof Player)) {
                            String msg = MessageManager.getMessageYml().getString("Lobby.Console");
                            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            Player player = (Player) sender;
                            if (!(LocationChecker.lobbyExists("lobby"))) {
                                String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
                                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                            } else {
                                DelayChecker.lobbyDelayCheck("lobby", player);
                            }
                        }
                    } else if (args.length == 1) {
                        if (!(sender instanceof Player)) {
                            String msg = MessageManager.getMessageYml().getString("Lobby.Console");
                            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            Player player = (Player) sender;
                            String lobby = args[0];
                            if (!(LocationChecker.lobbyExists(lobby))) {
                                String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
                                player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                            } else {
                                DelayChecker.lobbyDelayCheck(lobby, player);
                            }
                        }
                    } else if (args.length == 2) {
                        if (!(sender.hasPermission("SpawnJoin.use.lobbyothers"))) {
                            sender.sendMessage(prefix + perm);
                        } else {
                            String lobby = args[0];
                            String targetName = args[1];
                            if (!(LocationChecker.lobbyExists(lobby))) {
                                String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                            } else {
                                Player target = Bukkit.getPlayer(targetName);
                                if (target == null) {
                                    String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOffline");
                                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                } else {
                                    Teleporter.lobby(lobby, target, sender);
                                }
                            }
                        }
                    } else if (args.length > 2) {
                        String msg = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            }
        }
        return false;
    }
}