package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class EditLobby implements CommandExecutor {
    private Main plugin;

    public EditLobby(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("editlobby")) {
            if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
                String prefix = MessageManager.getPrefix();
                String perm = MessageManager.getNoPermMessage();
                if (!(sender.hasPermission("SpawnJoin.use.editlobby"))) {
                    sender.sendMessage(prefix + perm);
                } else {
                    if (args.length < 1) {
                        String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    } else if (args.length == 1) {
                        String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    } else if (args.length == 2) {
                        String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    } else if (args.length == 3) {
                        String lobbyName = args[0];
                        String type = args[1];
                        if (type.equalsIgnoreCase("cost")) {
                            String number = args[2];
                            EditLobby.editCost(lobbyName, number, sender);
                        } else if (type.equalsIgnoreCase("name")) {
                            String newName = args[2];
                            EditLobby.editName(lobbyName, newName, sender);
                        } else if (type.equalsIgnoreCase("item")) {
                            String item = args[2];
                            EditLobby.editItem(lobbyName, item, sender);
                        }
                    } else {
                        String msg = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private static void editCost(String lobbyName, String number, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!(FileManager.getLobbyYml().contains("LOBBIES." + lobbyName))) {
            String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            YamlConfiguration lobs = FileManager.getLobbyYml();

            Integer costInt = Integer.valueOf(number);
            lobs.set("LOBBIES." + lobbyName + ".cost", costInt);
            FileManager.saveCustomConfig(lobs, FileManager.getLobbyFile());
            String original = MessageManager.getMessageYml().getString("Lobby.EditCost");
            String replaced = original.replaceAll("%lobby%", lobbyName).replaceAll("%cost%", number);
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    private static void editName(String lobbyName, String newName, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!FileManager.getLobbyYml().contains("LOBBIES." + lobbyName)) {
            String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (FileManager.getLobbyYml().contains("LOBBIES." + newName)) {
                String msg = MessageManager.getMessageYml().getString("Lobby.AlreadyExists");
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
            } else {
                YamlConfiguration l = FileManager.getLobbyYml();
                YamlConfiguration lists = FileManager.getListYml();

                String w = l.getString("LOBBIES." + lobbyName + ".world");
                double x = l.getDouble("LOBBIES." + lobbyName + ".x");
                double y = l.getDouble("LOBBIES." + lobbyName + ".y");
                double z = l.getDouble("LOBBIES." + lobbyName + ".z");
                int ya = l.getInt("LOBBIES." + lobbyName + ".yaw");
                int pi = l.getInt("LOBBIES." + lobbyName + ".pitch");
                int item = l.getInt("LOBBIES." + lobbyName + ".item");
                int cost = l.getInt("LOBBIES." + lobbyName + ".cost");
                l.set("LOBBIES." + newName + ".world", w);
                l.set("LOBBIES." + newName + ".x", x);
                l.set("LOBBIES." + newName + ".y", y);
                l.set("LOBBIES." + newName + ".z", z);
                l.set("LOBBIES." + newName + ".yaw", ya);
                l.set("LOBBIES." + newName + ".pitch", pi);
                l.set("LOBBIES." + newName + ".item", item);
                l.set("LOBBIES." + newName + ".cost", cost);
                l.set("LOBBIES." + lobbyName, null);
                List<String> list = lists.getStringList("Lobbies");
                list.remove(lobbyName);
                list.add(newName);
                lists.set("Lobbies", list);
                FileManager.saveCustomConfig(l, FileManager.getLobbyFile());
                FileManager.saveCustomConfig(lists, FileManager.getListFile());
                String original = MessageManager.getMessageYml().getString("Lobby.EditName");
                String replaced = original.replaceAll("%lobby%", newName);
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static void editItem(String lobbyName, String item, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!FileManager.getLobbyYml().contains("LOBBIES." + lobbyName)) {
            String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (!item.contains(":")) {
                try {
                    YamlConfiguration l = FileManager.getLobbyYml();

                    Integer itemId = Integer.parseInt(item);
                    l.set("LOBBIES." + lobbyName + ".item", itemId);
                    l.set("LOBBIES." + lobbyName + ".itemProp", null);
                    FileManager.saveCustomConfig(l, FileManager.getLobbyFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Lobby.EditItem");
                    String msg = msgOr.replaceAll("%lobby%", lobbyName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateLobbyInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            } else {
                try {
                    YamlConfiguration l = FileManager.getLobbyYml();

                    Integer itemId = Integer.valueOf(item.split(":")[0]);
                    Short damage = Short.valueOf(item.split(":")[1]);
                    l.set("LOBBIES." + lobbyName + ".item", itemId);
                    l.set("LOBBIES." + lobbyName + ".itemProp", damage);
                    FileManager.saveCustomConfig(l, FileManager.getLobbyFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Lobby.EditItem");
                    String msg = msgOr.replaceAll("%lobby%", lobbyName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateLobbyInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }
}