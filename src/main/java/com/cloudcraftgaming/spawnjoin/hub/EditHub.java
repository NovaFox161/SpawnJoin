package com.cloudcraftgaming.spawnjoin.hub;

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

public class EditHub implements CommandExecutor {
    private Main plugin;

    public EditHub(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("edithub")) {
            if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
                String prefix = MessageManager.getPrefix();
                String perm = MessageManager.getNoPermMessage();
                if (!(sender.hasPermission("SpawnJoin.use.edithub"))) {
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
                        String hubName = args[0];
                        String type = args[1];
                        if (type.equalsIgnoreCase("cost")) {
                            String number = args[2];
                            EditHub.editCost(hubName, number, sender);
                        } else if (type.equalsIgnoreCase("name")) {
                            String newName = args[2];
                            EditHub.editName(hubName, newName, sender);
                        } else if (type.equalsIgnoreCase("item")) {
                            String item = args[2];
                            EditHub.editItem(hubName, item, sender);
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

    private static void editCost(String hubName, String number, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.hubExists(hubName)) {
            String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            YamlConfiguration hubs = FileManager.getHubYml();

            Integer costInt = Integer.valueOf(number);
            hubs.set("HUBS." + hubName + ".cost", costInt);
            FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
            String original = MessageManager.getMessageYml().getString("Hub.EditCost");
            String replaced = original.replaceAll("%hub%", hubName).replaceAll("%cost%", number);
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
            MenuManager.updateHubInv();
        }
    }

    private static void editName(String hubName, String newName, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.hubExists(hubName)) {
            String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (LocationChecker.hubExists(newName)) {
                String msg = MessageManager.getMessageYml().getString("Hub.AlreadyExists");
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
            } else {
                YamlConfiguration hubs = FileManager.getHubYml();
                YamlConfiguration lists = FileManager.getListYml();

                String w = hubs.getString("HUBS." + hubName + ".world");
                double x = hubs.getDouble("HUBS." + hubName + ".x");
                double y = hubs.getDouble("HUBS." + hubName + ".y");
                double z = hubs.getDouble("HUBS." + hubName + ".z");
                int ya = hubs.getInt("HUBS." + hubName + ".yaw");
                int pi = hubs.getInt("HUBS." + hubName + ".pitch");
                int item = hubs.getInt("HUBS." + hubName + ".item");
                int cost = hubs.getInt("HUBS." + hubName + ".cost");
                hubs.set("HUBS." + newName + ".world", w);
                hubs.set("HUBS." + newName + ".x", x);
                hubs.set("HUBS." + newName + ".y", y);
                hubs.set("HUBS." + newName + ".z", z);
                hubs.set("HUBS." + newName + ".yaw", ya);
                hubs.set("HUBS." + newName + ".pitch", pi);
                hubs.set("HUBS." + newName + ".item", item);
                hubs.set("HUBS." + newName + ".cost", cost);
                hubs.set("HUBS." + hubName, null);
                List<String> list = lists.getStringList("Hubs");
                list.remove(hubName);
                list.add(newName);
                lists.set("Hubs", list);
                FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                FileManager.saveCustomConfig(lists, FileManager.getListFile());
                String original = MessageManager.getMessageYml().getString("Hub.EditName");
                String replaced = original.replaceAll("%hub%", newName);
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                MenuManager.updateHubInv();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static void editItem(String hubName, String item, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!LocationChecker.hubExists(hubName)) {
            String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            if (!item.contains(":")) {
                try {
                    YamlConfiguration hubs = FileManager.getHubYml();

                    Integer itemId = Integer.parseInt(item);
                    hubs.set("HUBS." + hubName + ".item", itemId);
                    hubs.set("HUBS." + hubName + ".itemProp", null);
                    FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Hub.EditItem");
                    String msg = msgOr.replaceAll("%hub%", hubName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateHubInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            } else {
                try {
                    YamlConfiguration hubs = FileManager.getHubYml();

                    Integer itemId = Integer.valueOf(item.split(":")[0]);
                    Short damage = Short.valueOf(item.split(":")[1]);
                    hubs.set("HUBS." + hubName + ".item", itemId);
                    hubs.set("HUBS." + hubName + ".itemProp", damage);
                    FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                    Material itemMat = Material.getMaterial(itemId);
                    String msgOr = MessageManager.getMessageYml().getString("Hub.EditItem");
                    String msg = msgOr.replaceAll("%hub%", hubName).replaceAll("%item%", itemMat.name());
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    MenuManager.updateHubInv();
                } catch (NumberFormatException e) {
                    String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }
}