package com.cloudcraftgaming.spawnjoin.hub;

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

public class Delhub implements CommandExecutor {
    private Main plugin;

    public Delhub(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("delhub")) {
            if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
                if (!(sender.hasPermission("SpawnJoin.use.delhub"))) {
                    sender.sendMessage(prefix + perm);
                } else if (args.length < 1) {
                    if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
                    }
                } else if (args.length == 1) {
                    String hub = args[0];
                    if (!LocationChecker.hubExists(hub)) {
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.NoHub")));
                    } else {
                        YamlConfiguration hubs = FileManager.getHubYml();
                        YamlConfiguration lists = FileManager.getListYml();

                        hubs.set("HUBS." + hub, null);
                        List<String> list = lists.getStringList("Hubs");
                        list.remove(hub);
                        lists.set("Hubs", list);
                        FileManager.saveCustomConfig(lists, FileManager.getListFile());
                        FileManager.saveCustomConfig(hubs, FileManager.getHubFile());
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.Deleted")));
                        MenuManager.updateHubInv();
                    }
                }
            }
        }
        return false;
    }
}
