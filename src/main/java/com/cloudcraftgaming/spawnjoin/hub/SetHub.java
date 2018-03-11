package com.cloudcraftgaming.spawnjoin.hub;

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

public class SetHub implements CommandExecutor {
    private Main plugin;

    public SetHub(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sethub")) {
            String prefix = MessageManager.getPrefix();
            String perm = MessageManager.getNoPermMessage();
            if (cmd.getName().equalsIgnoreCase("sethub")) {
                if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
                    } else {
                        Player p = (Player) sender;
                        if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
                            if (p.hasPermission("SpawnJoin.use.sethub")) {
                                if (args.length < 1) {
                                    YamlConfiguration hubs = FileManager.getHubYml();

                                    hubs.set("HUBS.hub.world", p.getLocation().getWorld().getName());
                                    hubs.set("HUBS.hub.x", p.getLocation().getX());
                                    hubs.set("HUBS.hub.y", p.getLocation().getY());
                                    hubs.set("HUBS.hub.z", p.getLocation().getZ());
                                    hubs.set("HUBS.hub.yaw", p.getLocation().getYaw());
                                    hubs.set("HUBS.hub.pitch", p.getLocation().getPitch());
                                    hubs.set("HUBS.hub.item", 2);
                                    hubs.set("HUBS.hub.cost", 0.0);
                                    FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                                    if (!FileManager.getListYml().getStringList("Hubs").contains("hub")) {
                                        YamlConfiguration lists = FileManager.getListYml();
                                        List<String> list = lists.getStringList("Hubs");
                                        list.add("hub");
                                        lists.set("Hubs", list);
                                        FileManager.saveCustomConfig(lists, FileManager.getHubFile());
                                    }
                                    MenuManager.updateHubInv();
                                    String original = MessageManager.getMessageYml().getString("Hub.Set");
                                    String replaced = original.replaceAll("%hub%", "");
                                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                } else {
                                    if (args.length == 1) {
                                        YamlConfiguration hubs = FileManager.getHubYml();

                                        String hub = args[0];
                                        hubs.set("HUBS." + hub + ".world", p.getLocation().getWorld().getName());
                                        hubs.set("HUBS." + hub + ".x", p.getLocation().getX());
                                        hubs.set("HUBS." + hub + ".y", p.getLocation().getY());
                                        hubs.set("HUBS." + hub + ".z", p.getLocation().getZ());
                                        hubs.set("HUBS." + hub + ".yaw", p.getLocation().getYaw());
                                        hubs.set("HUBS." + hub + ".pitch", p.getLocation().getPitch());
                                        hubs.set("HUBS." + hub + ".item", 2);
                                        hubs.set("HUBS." + hub + ".cost", 0.0);
                                        FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                                        if (!FileManager.getListYml().getStringList("Hubs").contains(hub)) {
                                            YamlConfiguration lists = FileManager.getListYml();
                                            List<String> list = lists.getStringList("Hubs");
                                            list.add(hub);
                                            lists.set("Hubs", list);
                                            FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                        }
                                        MenuManager.updateHubInv();
                                        String original = MessageManager.getMessageYml().getString("Hub.Set");
                                        String replaced = original.replaceAll("%hub%", hub);
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
        }
        return false;
    }
}
