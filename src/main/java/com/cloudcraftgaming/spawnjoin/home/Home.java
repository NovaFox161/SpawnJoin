package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home
implements CommandExecutor {
	Main plugin;	
		public Home(Main instance) {
			plugin = instance;
		}
	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("Home")) {
			if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(MessageManager.getPrefix() + "Only players can do that!");
				}
				else {
					if (!(sender.hasPermission("SpawnJoin.use.home"))) {
						if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
							sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
						}
					}
					else {
						if (args.length < 1) {
							Player player = (Player) sender;
							if (!(LocationChecker.homeExists("home", player))) {
								String msg = MessageManager.getMessageYml().getString("Home.NoSet");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
	    				     }
							else {
								DelayChecker.homeDelayCheck("home", player);
							}
						}
						else if (args.length == 1) {
							Player player = (Player) sender;
							final String loc = args[0];
							if (!(LocationChecker.homeExists(loc, player))) {
								String msg = MessageManager.getMessageYml().getString("Home.NoSet");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
	    				     }
							else {
								DelayChecker.homeDelayCheck(loc, player);
							}
						}
						else if (args.length > 1) {
							if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
								String msg = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							}
						}
					}
				}
			}
		}
		return false;
	}
}
