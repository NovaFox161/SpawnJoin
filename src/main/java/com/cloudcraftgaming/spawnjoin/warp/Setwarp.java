package com.cloudcraftgaming.spawnjoin.warp;

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

public class Setwarp implements CommandExecutor {
    private Main plugin;

    public Setwarp(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (!(sender instanceof Player)) {
                if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
                }
            } else {
                Player p = (Player) sender;
                if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
                    if (p.hasPermission("SpawnJoin.use.setwarp")) {
                        if (args.length < 1) {
                            if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
                            }
                        } else {
                            if (args.length == 1) {
                                String warp = (args[0]);

                                YamlConfiguration warps = FileManager.getWarpYml();
                                warps.set("WARPS." + warp + ".world", p.getLocation().getWorld().getName());
                                warps.set("WARPS." + warp + ".x", p.getLocation().getX());
                                warps.set("WARPS." + warp + ".y", p.getLocation().getY());
                                warps.set("WARPS." + warp + ".z", p.getLocation().getZ());
                                warps.set("WARPS." + warp + ".yaw", p.getLocation().getYaw());
                                warps.set("WARPS." + warp + ".pitch", p.getLocation().getPitch());
                                warps.set("WARPS." + warp + ".item", 2);
                                warps.set("WARPS." + warp + ".cost", 0.0);
                                FileManager.saveCustomConfig(warps, FileManager.getWarpFile());
                                if (!FileManager.getListYml().getStringList("Warps").contains(warp)) {
                                    YamlConfiguration lists = FileManager.getListYml();
                                    List<String> list = lists.getStringList("Warps");
                                    list.add(warp);
                                    lists.set("Warps", list);
                                    FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                }
                                String original = MessageManager.getMessageYml().getString("Warp.Set");
                                String replaced = original.replaceAll("%warp%", warp);
                                p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                MenuManager.updateWarpInv();
                            } else {
                                if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                                }
                            }
                        }
                    } else {
                        if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                            sender.sendMessage(prefix + ChatColor.RED + perm);
                        }
                    }
                }
            }
        }
        return false;
    }
}