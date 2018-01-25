package com.cloudcraftgaming.spawnjoin.home;

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
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class DelHome implements CommandExecutor {
    private Main plugin;

    public DelHome(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (command.getName().equalsIgnoreCase("DelHome")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
            } else {
                if (!(sender.hasPermission("SpawnJoin.use.DelHome"))) {
                    if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                        sender.sendMessage(prefix + perm);
                    }
                } else if (args.length < 1) {
                    Player player = (Player) sender;
                    UUID Id = player.getUniqueId();
                    if (!LocationChecker.homeExists("Home", player)) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.NoHome")));
                    } else {
                        YamlConfiguration homes = FileManager.getHomeYml();
                        YamlConfiguration homeData = FileManager.getHomeDataYml();
                        YamlConfiguration lists = FileManager.getListYml();

                        homes.set("HOMES." + Id + ".Home", null);
                        List<String> list = lists.getStringList("Homes." + Id);
                        list.remove("Home");
                        lists.set("Homes." + Id, list);
                        String oldS = homeData.getString("Players." + Id + ".HomeNumber");
                        Integer old = Integer.valueOf(oldS);
                        Integer newN = old - 1;
                        homeData.set("Players." + Id + ".HomeNumber", newN);
                        FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
                        FileManager.saveCustomConfig(homes, FileManager.getHomeFile());
                        FileManager.saveCustomConfig(lists, FileManager.getListFile());
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.Deleted")));
                        MenuManager.updatePlayerHomeInv(player);
                    }
                } else if (args.length == 1) {
                    String home = args[0];
                    Player player = (Player) sender;
                    UUID Id = player.getUniqueId();
                    if (!LocationChecker.homeExists(home, player)) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.NoHome")));
                    } else {
                        YamlConfiguration homes = FileManager.getHomeYml();
                        YamlConfiguration homeData = FileManager.getHomeDataYml();
                        YamlConfiguration lists = FileManager.getListYml();

                        homes.set("HOMES." + Id + "." + home, null);
                        List<String> list = lists.getStringList("Homes." + Id);
                        list.remove(home);
                        lists.set("Homes." + Id, list);
                        String oldS = homeData.getString("Players." + Id + ".HomeNumber");
                        Integer old = Integer.valueOf(oldS);
                        Integer newN = old - 1;
                        homeData.set("Players." + Id + ".HomeNumber", newN);
                        FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
                        FileManager.saveCustomConfig(homes, FileManager.getHomeFile());
                        FileManager.saveCustomConfig(lists, FileManager.getListFile());
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.Deleted")));
                        MenuManager.updatePlayerHomeInv(player);
                    }
                } else {
                    if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                    }
                }
            }
        }
        return false;
    }
}
