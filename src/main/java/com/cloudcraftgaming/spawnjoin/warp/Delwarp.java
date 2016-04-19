package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Delwarp 
implements CommandExecutor {
	Main plugin;
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
        		}
        			else {
        				if (args.length < 1) {
        					sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
        				}
        				else {
        					if (args.length == 1) {
        						String warp = args[0];
        						if (!(plugin.warps.contains("WARPS." + warp))) {
        							sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.NoWarp")));
        						}
        						else {
        							plugin.warps.set("WARPS." + warp, null);
        							List<String> list = plugin.lists.getStringList("Warps");
							        list.remove(warp);
							        plugin.lists.set("Warps", list);
							        plugin.saveCustomConfig(plugin.lists, plugin.listFile);
							        plugin.saveCustomConfig(plugin.warps, plugin.warpFile);
							        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.Deleted")));
									MenuManager.updateWarpInv();
        						}
        					}
        					else {
        						if (args.length > 1) {
        							sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
        						}
        					}
        				}
        			}
        		}
        	}
    return false;
	}
}