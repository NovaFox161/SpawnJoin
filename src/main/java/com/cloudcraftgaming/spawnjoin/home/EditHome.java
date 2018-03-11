package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EditHome implements CommandExecutor {
    private Main plugin;

    public EditHome(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("edithome")) {
            String prefix = MessageManager.getPrefix();
            String perm = MessageManager.getNoPermMessage();
            if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("true")) {
                if (!(sender.hasPermission("SpawnJoin.use.edithome"))) {
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
                        String type = args[1];
                        if (type.equalsIgnoreCase("limit")) {
                            if (sender.hasPermission("SpawnJoin.use.edithome.limit")) {
                                String name = args[0];
                                String number = args[2];
                                EditHome.editLimit(name, number, sender);
                            } else {
                                sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                            }
                        } else if (type.equalsIgnoreCase("item")) {
                            if (sender instanceof Player) {
                                String homeName = args[0];
                                String itemIdS = args[2];
                                if (!itemIdS.contains(":")) {
                                    try {
                                        Integer itemId = Integer.valueOf(itemIdS);
                                        Player player = (Player) sender;
                                        editItem(homeName, itemId, null, player);
                                    } catch (NumberFormatException e) {
                                        String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                                        sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                    }
                                } else {
                                    try {
                                        Integer itemId = Integer.valueOf(itemIdS.split(":")[0]);
                                        Short damage = Short.valueOf(itemIdS.split(":")[1]);
                                        Player player = (Player) sender;
                                        editItem(homeName, itemId, damage, player);
                                    } catch (NumberFormatException e) {
                                        String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                                        sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                    }
                                }
                            } else {
                                String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOnly");
                                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                            }
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

    @SuppressWarnings("deprecation")
    private static void editLimit(String playerName, String number, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        OfflinePlayer target = Bukkit.getOfflinePlayer(playerName);
        UUID Id = target.getUniqueId();

        YamlConfiguration homeData = FileManager.getHomeDataYml();
        if (homeData.contains("Players." + Id)) {
            Integer limit = Integer.valueOf(number);
            homeData.set("Players." + Id + ".AllowedHomes", limit);
            FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
            String original = MessageManager.getMessageYml().getString("Home.EditLimit");
            String replaced = original.replaceAll("%player%", playerName).replaceAll("%number%", number);
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
        } else {
            String msg = MessageManager.getMessageYml().getString("Notifications.PlayerNotExist");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    @SuppressWarnings("deprecation")
    private static void editItem(String homeName, Integer itemId, Short damage, Player sender) {
        if (LocationChecker.homeExists(homeName, sender)) {
            YamlConfiguration homes = FileManager.getHomeYml();

            homes.set("HOMES." + sender.getUniqueId() + "." + homeName + ".item", itemId);
            homes.set("HOMES." + sender.getUniqueId() + "." + homeName + ".itemProp", damage);
            FileManager.saveCustomConfig(homes, FileManager.getHomeFile());
            String msgOr = MessageManager.getMessageYml().getString("Home.EditItem");
            String msg = msgOr.replaceAll("%home%", homeName).replaceAll("%item%", Material.getMaterial(itemId).name());
            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            String msg = MessageManager.getMessageYml().getString("Home.NoHome");
            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}
