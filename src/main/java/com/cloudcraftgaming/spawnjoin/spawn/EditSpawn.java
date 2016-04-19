package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Nova Fox on 2/7/2016.
 */
public class EditSpawn implements CommandExecutor {
    Main plugin;
    public EditSpawn(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("editspawn")) {
            if (plugin.getConfig().getString("Commands.Spawn.Enabled").equalsIgnoreCase("True")) {
                String prefix = MessageManager.getPrefix();
                String perm = MessageManager.getNoPermMessage();
                if (!(sender.hasPermission("SpawnJoin.use.editspawn"))) {
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
                        String spawnName = args[0];
                        String type = args[1];
                        if (type.equalsIgnoreCase("cost")) {
                            String number = args[2];
                            EditSpawn.editCost(spawnName, number, sender);
                        } else if (type.equalsIgnoreCase("item")) {
                            String item = args[2];
                            EditSpawn.editItem(spawnName, item, sender);
                        }
                    } else if (args.length > 3) {
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
    private static void editCost(String spawnName, String number, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        if (!(LocationChecker.spawnOnFile(spawnName))) {
            String msg = MessageManager.getMessageYml().getString("Spawn.NoSpawn");
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            Integer costInt = Integer.valueOf(number);
            Main.plugin.spawns.set("Spawns." + spawnName + ".cost", costInt);
            Main.plugin.saveCustomConfig(Main.plugin.spawns, Main.plugin.spawnFile);
            String original = MessageManager.getMessageYml().getString("Spawn.EditCost");
            String replaced = original.replaceAll("%spawn%", spawnName).replaceAll("%cost%", number);
            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
            MenuManager.updateSpawnInv();
        }
    }
    @SuppressWarnings("deprecation")
    private static void editItem(String spawnName, String item, CommandSender sender) {
        if (!(LocationChecker.spawnOnFile(spawnName))) {
            String msg = MessageManager.getMessageYml().getString("Spawn.NoSpawn");
            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            try {
                Integer itemId = Integer.parseInt(item);
                Main.plugin.spawns.set("Spawns." + spawnName + ".item", itemId);
                Main.plugin.saveCustomConfig(Main.plugin.spawns, Main.plugin.spawnFile);
                Material itemMat = Material.getMaterial(itemId);
                String msgOr = MessageManager.getMessageYml().getString("Spawn.EditItem");
                String msg = msgOr.replaceAll("%spawn%", spawnName).replaceAll("%item%", itemMat.name());
                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                MenuManager.updateSpawnInv();
            } catch (NumberFormatException e) {
                String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
            }
        }
    }
}