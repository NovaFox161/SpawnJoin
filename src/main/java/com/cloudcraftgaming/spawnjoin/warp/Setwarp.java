package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Setwarp 
implements CommandExecutor 
{
	Main plugin;
	
	public Setwarp(Main instance)
	{
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
			}
			else {
				Player p = (Player) sender;
				if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
				    if (p.hasPermission("SpawnJoin.use.setwarp")) {
					    if (args.length < 1) {
					    	if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
						        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
					    	}
					    }
					    else {
						    if (args.length == 1) {
							    String warp = (args[0]);
						        plugin.warps.set("WARPS." + warp + ".world", p.getLocation().getWorld().getName());
					            plugin.warps.set("WARPS." + warp + ".x", p.getLocation().getX());
					            plugin.warps.set("WARPS." + warp + ".y", p.getLocation().getY());
					            plugin.warps.set("WARPS." + warp + ".z", p.getLocation().getZ());
					            plugin.warps.set("WARPS." + warp + ".yaw", p.getLocation().getYaw());
					            plugin.warps.set("WARPS." + warp + ".pitch", p.getLocation().getPitch());
					            plugin.warps.set("WARPS." + warp + ".item", 2);
					            plugin.warps.set("WARPS." + warp + ".cost", 0.0);
					            plugin.saveCustomConfig(plugin.warps, plugin.warpFile);
					            if (!(plugin.lists.getStringList("Warps").contains(warp))) {
					            	List<String> list = plugin.lists.getStringList("Warps");
					            	list.add(warp);
					            	plugin.lists.set("Warps", list);
					            	plugin.saveCustomConfig(plugin.lists, plugin.listFile);
					            }
					            String original = MessageManager.getMessageYml().getString("Warp.Set");
					            String replaced = original.replaceAll("%warp%", warp);
					            p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
								MenuManager.updateWarpInv();
						    }
					        else {
						        if (args.length > 1) {
						        	if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
							            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
						        	}
						        }
					        }
				        }
				    }
				    else {
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