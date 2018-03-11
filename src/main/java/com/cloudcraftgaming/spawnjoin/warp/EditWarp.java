package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class EditWarp implements CommandExecutor {
    private Main plugin;

    public EditWarp(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("editwarp")) {
            if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
                String prefix = MessageManager.getPrefix();
                String perm = MessageManager.getNoPermMessage();
                if (!(sender.hasPermission("SpawnJoin.use.editwarp"))) {
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
                        String warpName = args[0];
                        String type = args[1];
                        if (type.equalsIgnoreCase("cost")) {
                            String number = args[2];
                            EditWarp.editCost(warpName, number, sender);
                        } else if (type.equalsIgnoreCase("name")) {
                            String newName = args[2];
                            EditWarp.editName(warpName, newName, sender);
                        } else if (type.equalsIgnoreCase("item")) {
                            String item = args[2];
                            EditWarp.editItem(warpName, item, sender);
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

    private static void editCost(String warpName, String number, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.warpExists(warpName)) {
            String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            YamlConfiguration warps = FileManager.getWarpYml();

            Integer costInt = Integer.valueOf(number);
            warps.set("WARPS." + warpName + ".cost", costInt);
            FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
            String original = MessageManager.getMessageYml().getString("Warp.EditCost");
            String replaced = original.replaceAll("%warp%", warpName).replaceAll("%cost%", number);
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
            MenuManager.updateWarpInv();
        }
    }

    private static void editName(String warpName, String newName, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.warpExists(warpName)) {
            String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (LocationChecker.warpExists(newName)) {
                String msg = MessageManager.getMessageYml().getString("Warp.AlreadyExists");
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
            } else {
                YamlConfiguration warps = FileManager.getWarpYml();
                YamlConfiguration lists = FileManager.getListYml();

                String w = warps.getString("WARPS." + warpName + ".world");
                double x = warps.getDouble("WARPS." + warpName + ".x");
                double y = warps.getDouble("WARPS." + warpName + ".y");
                double z = warps.getDouble("WARPS." + warpName + ".z");
                int ya = warps.getInt("WARPS." + warpName + ".yaw");
                int pi = warps.getInt("WARPS." + warpName + ".pitch");
                int item = warps.getInt("WARPS." + warpName + ".item");
                int cost = warps.getInt("WARPS." + warpName + ".cost");
                warps.set("WARPS." + newName + ".world", w);
                warps.set("WARPS." + newName + ".x", x);
                warps.set("WARPS." + newName + ".y", y);
                warps.set("WARPS." + newName + ".z", z);
                warps.set("WARPS." + newName + ".yaw", ya);
                warps.set("WARPS." + newName + ".pitch", pi);
                warps.set("WARPS." + newName + ".item", item);
                warps.set("WARPS." + newName + ".cost", cost);
                warps.set("WARPS." + warpName, null);
                List<String> list = lists.getStringList("Warps");
                list.remove(warpName);
                list.add(newName);
                lists.set("Warps", list);
                FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
                FileManager.saveCustomConfig(lists, FileManager.getListFile());
                String original = MessageManager.getMessageYml().getString("Warp.EditName");
                String replaced = original.replaceAll("%warp%", newName);
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                MenuManager.updateWarpInv();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static void editItem(String warpName, String item, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.warpExists(warpName)) {
            String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (!item.contains(":")) {
                try {
                    YamlConfiguration warps = FileManager.getWarpYml();

                    Integer itemId = Integer.parseInt(item);
                    warps.set("WARPS." + warpName + ".item", itemId);
                    warps.set("WARPS." + warpName + ".itemProp", null);
                    FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Warp.EditItem");
                    String msg = msgOr.replaceAll("%warp%", warpName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateWarpInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            } else {
                try {
                    YamlConfiguration warps = FileManager.getWarpYml();

                    Integer itemId = Integer.valueOf(item.split(":")[0]);
                    Short damage = Short.valueOf(item.split(":")[1]);
                    warps.set("WARPS." + warpName + ".item", itemId);
                    warps.set("WARPS." + warpName + ".itemProp", damage);
                    FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Warp.EditItem");
                    String msg = msgOr.replaceAll("%warp%", warpName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateWarpInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }
}