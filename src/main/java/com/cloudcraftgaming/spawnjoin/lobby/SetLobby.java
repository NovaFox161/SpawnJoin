package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetLobby implements CommandExecutor {
    private Main plugin;

    public SetLobby(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("setlobby")) {
            if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
                } else {
                    Player p = (Player) sender;
                    if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
                        if (p.hasPermission("SpawnJoin.use.setlobby")) {
                            if (args.length < 1) {
                                YamlConfiguration lobs = FileManager.getLobbyYml();

                                lobs.set("LOBBIES.lobby.world", p.getLocation().getWorld().getName());
                                lobs.set("LOBBIES.lobby.x", p.getLocation().getX());
                                lobs.set("LOBBIES.lobby.y", p.getLocation().getY());
                                lobs.set("LOBBIES.lobby.z", p.getLocation().getZ());
                                lobs.set("LOBBIES.lobby.yaw", p.getLocation().getYaw());
                                lobs.set("LOBBIES.lobby.pitch", p.getLocation().getPitch());
                                lobs.set("LOBBIES.lobby.item", 2);
                                lobs.set("LOBBIES.lobby.cost", 0.0);
                                FileManager.saveCustomConfig(lobs, FileManager.getLobbyFile());
                                if (!(FileManager.getListYml().getStringList("Lobbies").contains("lobby"))) {
                                    YamlConfiguration lists = FileManager.getListYml();
                                    List<String> list = lists.getStringList("Lobbies");
                                    list.add("lobby");
                                    lists.set("Lobbies", list);
                                    FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                }
                                MenuManager.updateLobbyInv();
                                String original = MessageManager.getMessageYml().getString("Lobby.Set");
                                String replaced = original.replaceAll("%lobby%", "");
                                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                            } else {
                                if (args.length == 1) {
                                    String lobby = args[0];
                                    YamlConfiguration lobs = FileManager.getLobbyYml();

                                    lobs.set("LOBBIES." + lobby + ".world", p.getLocation().getWorld().getName());
                                    lobs.set("LOBBIES." + lobby + ".x", p.getLocation().getX());
                                    lobs.set("LOBBIES." + lobby + ".y", p.getLocation().getY());
                                    lobs.set("LOBBIES." + lobby + ".z", p.getLocation().getZ());
                                    lobs.set("LOBBIES." + lobby + ".yaw", p.getLocation().getYaw());
                                    lobs.set("LOBBIES." + lobby + ".pitch", p.getLocation().getPitch());
                                    lobs.set("LOBBIES." + lobby + ".item", 2);
                                    lobs.set("LOBBIES." + lobby + ".cost", 0.0);
                                    FileManager.saveCustomConfig(lobs, FileManager.getLobbyFile());
                                    if (!(FileManager.getListYml().getStringList("Lobbies").contains(lobby))) {
                                        YamlConfiguration lists = FileManager.getListYml();
                                        List<String> list = lists.getStringList("Lobbies");
                                        list.add(lobby);
                                        lists.set("Lobbies", list);
                                        FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                    }
                                    MenuManager.updateLobbyInv();
                                    String original = MessageManager.getMessageYml().getString("Lobby.Set");
                                    String replaced = original.replaceAll("%lobby%", lobby);
                                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                } else {
                                    if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                                    }
                                }
                            }
                        } else {
                            if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                                sender.sendMessage(prefix + perm);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}