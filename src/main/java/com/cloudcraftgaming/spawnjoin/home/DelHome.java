package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class DelHome
implements CommandExecutor {
	Main plugin;
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
			}
			else {
				if (!(sender.hasPermission("SpawnJoin.use.DelHome"))) {
					if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
						sender.sendMessage(prefix + perm);
					}
				}
				else if (args.length < 1) {
					Player player = (Player) sender;
					UUID Id = player.getUniqueId();
					if (!(plugin.homes.contains("HOMES." + Id + ".Home"))) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.NoHome")));
					}
					else {
						plugin.homes.set("HOMES." + Id + ".Home", null);
                        List<String> list = plugin.lists.getStringList("Homes." + Id);
					    list.remove("Home");
					    plugin.lists.set("Homes." + Id, list);
					    String oldS = plugin.homeData.getString("Players." + Id + ".HomeNumber");
					    Integer old = Integer.valueOf(oldS);
					    Integer newN = old - 1;
					    plugin.homeData.set("Players." + Id + ".HomeNumber", newN);
					    plugin.saveCustomConfig(plugin.homeData, plugin.homeDataFile);
                        plugin.saveCustomConfig(plugin.homes, plugin.homeFile);
                        plugin.saveCustomConfig(plugin.lists, plugin.listFile);
                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.Deleted")));
						MenuManager.updatePlayerHomeInv(player);
					}
				}
				else if (args.length == 1) {
						String home = args[0];
						Player player = (Player) sender;
						UUID Id = player.getUniqueId();
						if (!(plugin.homes.contains("HOMES." + Id + "." + home))) {
							sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.NoHome")));
						}
						else {
							plugin.homes.set("HOMES." + Id + "." + home, null);
							List<String> list = plugin.lists.getStringList("Homes." + Id);
						    list.remove(home);
						    plugin.lists.set("Homes." + Id, list);
						    String oldS = plugin.homeData.getString("Players." + Id + ".HomeNumber");
						    Integer old = Integer.valueOf(oldS);
						    Integer newN = old - 1;
						    plugin.homeData.set("Players." + Id + ".HomeNumber", newN);
						    plugin.saveCustomConfig(plugin.homeData, plugin.homeDataFile);
	                        plugin.saveCustomConfig(plugin.homes, plugin.homeFile);
	                        plugin.saveCustomConfig(plugin.lists, plugin.listFile);
	                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.Deleted")));
							MenuManager.updatePlayerHomeInv(player);
						}
					}
				else if (args.length > 1) {
					if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
					}
				}
			}
		}
		return false;
	}
}
