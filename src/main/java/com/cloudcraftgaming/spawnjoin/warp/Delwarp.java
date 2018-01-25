package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public class Delwarp implements CommandExecutor {
    private Main plugin;

    public Delwarp(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("delwarp")) {
            if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
                if (!(sender.hasPermission("SpawnJoin.use.delwarp"))) {
                    sender.sendMessage(prefix + perm);
                } else {
                    if (args.length < 1) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
                    } else {
                        if (args.length == 1) {
                            String warp = args[0];
                            if (!LocationChecker.warpExists(warp)) {
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.NoWarp")));
                            } else {
                                YamlConfiguration warps = FileManager.getWarpYml();
                                YamlConfiguration lists = FileManager.getListYml();

                                warps.set("WARPS." + warp, null);
                                List<String> list = lists.getStringList("Warps");
                                list.remove(warp);
                                lists.set("Warps", list);
                                FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.Deleted")));
                                MenuManager.updateWarpInv();
                            }
                        } else {
                            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                        }
                    }
                }
            }
        }
        return false;
    }
}