package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp 
implements CommandExecutor 
{
	Main plugin;
	
	public Warp(Main instance)
	{
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
				if (args.length  < 1) {
					if (!(sender instanceof Player)) {
						String msg = MessageManager.getMessageYml().getString("Warp.Console");
						sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					} else {
						Player player = (Player) sender;
						if (!(player.hasPermission("SpawnJoin.use.warp"))) {
							if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
								player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
							}
						} else {
							String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
							player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
						}
					}
				} else if (args.length == 1) {
					if (!(sender instanceof Player)) {
						String msg = MessageManager.getMessageYml().getString("Warp.Console");
						sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					} else {
						Player player = (Player) sender;
						if (player.hasPermission("SpawnJoin.use.warp")) {
							String warp = args[0];
							if (!(LocationChecker.warpExists(warp))) {
								String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
								player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								if (!(player.hasPermission("SpawnJoin.use.warp." + warp) || player.hasPermission("SpawnJoin.use.warp.*"))) {
									String msg = MessageManager.getMessageYml().getString("Warp.WarpPerm");
									sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
								} else {
									DelayChecker.warpDelayCheck(warp, player);
								}
							}
						}
					}
				} else if (args.length == 2) {
					if (sender.hasPermission("SpawnJoin.use.warpothers")) {
						final String warp = args[0];
						final Player target = Bukkit.getPlayer(args[1]);
						if (!(LocationChecker.warpExists(warp))) {
							String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
							sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
						} else {
							if (target == null) {
								String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOffline");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								Teleporter.warp(warp, target, sender);
							}
						}
					}
				}
			}
		}
	return false;
	}
}