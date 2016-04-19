package com.cloudcraftgaming.spawnjoin.hub;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hub 
implements CommandExecutor {
	Main plugin;
	public Hub(Main instance) {
		plugin = instance;
	}
	@Override
	@SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hub")) {
			if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
				if (!(sender.hasPermission("SpawnJoin.use.hub"))) {
					sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
				} else {
					if (args.length < 1) {
						if (!(sender instanceof Player)) {
							String msg = MessageManager.getMessageYml().getString("Hub.Console");
							sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
						} else {
							Player player = (Player) sender;
							if (!(LocationChecker.hubExists("hub"))) {
								String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
								player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								DelayChecker.hubDelayCheck("hub", player);
							}
						}
					} else if (args.length == 1) {
						if (!(sender instanceof Player)) {
							String msg = MessageManager.getMessageYml().getString("Hub.Console");
							sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
						} else {
							Player player = (Player) sender;
							String hub = args[0];
							if (!(LocationChecker.hubExists(hub))) {
								String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
								player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								DelayChecker.hubDelayCheck(hub, player);
							}
						}
					} else if (args.length == 2) {
						if (!(sender.hasPermission("SpawnJoin.use.hubothers"))) {
							sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
						} else {
							String hub = args[0];
							String targetName = args[1];
							if (!(LocationChecker.hubExists(hub))) {
								String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								Player target = Bukkit.getPlayer(targetName);
								if (target == null) {
									String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOffline");
									sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
								} else {
									Teleporter.hub(hub, target, sender);
								}
							}
						}
					} else if (args.length > 2) {
						String msg = MessageManager.getMessageYml().getString("notifications.ManyArgs");
						sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					}
				}
			}
		}
	return false;
	}
}