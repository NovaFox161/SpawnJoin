package com.cloudcraftgaming.spawnjoin.hub;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetHub 
implements CommandExecutor {
	Main plugin;
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
				    }
				    else {
					    Player p = (Player) sender;
					    if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
					        if (p.hasPermission("SpawnJoin.use.sethub")) {
					    	    if (args.length < 1) {
	     					        plugin.hubs.set("HUBS.hub.world", p.getLocation().getWorld().getName());
	         			            plugin.hubs.set("HUBS.hub.x", p.getLocation().getX());
		        		            plugin.hubs.set("HUBS.hub.y", p.getLocation().getY());
			        	            plugin.hubs.set("HUBS.hub.z", p.getLocation().getZ());
				                    plugin.hubs.set("HUBS.hub.yaw", p.getLocation().getYaw());
				                    plugin.hubs.set("HUBS.hub.pitch", p.getLocation().getPitch());
				                    plugin.hubs.set("HUBS.hub.item", 2);
				                    plugin.hubs.set("HUBS.hub.cost", 0.0);
				                    plugin.saveCustomConfig(plugin.hubs, plugin.hubFile);
				                    if (!(plugin.lists.getStringList("Hubs").contains("hub"))) {
				                    	List<String> list = plugin.lists.getStringList("Hubs");
				                    	list.add("hub");
				                    	plugin.lists.set("Hubs", list);
				                    	plugin.saveCustomConfig(plugin.lists, plugin.listFile);
				                    }
									MenuManager.updateHubInv();
				                    String original = MessageManager.getMessageYml().getString("Hub.Set");
				                    String replaced = original.replaceAll("%hub%", "");
				                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
					    	    }
					    	    else {
					    		    if (args.length == 1) {
					    			    String hub = args[0];
						    			plugin.hubs.set("HUBS." + hub + ".world", p.getLocation().getWorld().getName());
	    	     			            plugin.hubs.set("HUBS." + hub + ".x", p.getLocation().getX());
		    	    		            plugin.hubs.set("HUBS." + hub + ".y", p.getLocation().getY());
			    	    	            plugin.hubs.set("HUBS." + hub + ".z", p.getLocation().getZ());
				    	                plugin.hubs.set("HUBS." + hub + ".yaw", p.getLocation().getYaw());
					                    plugin.hubs.set("HUBS." + hub + ".pitch", p.getLocation().getPitch());
					                    plugin.hubs.set("HUBS." + hub + ".item", 2);
					                    plugin.hubs.set("HUBS." + hub + ".cost", 0.0);
					                    plugin.saveCustomConfig(plugin.hubs, plugin.hubFile);
					                    if (!(plugin.lists.getStringList("Hubs").contains(hub))) {
					                    	List<String> list = plugin.lists.getStringList("Hubs");
					                    	list.add(hub);
						             	    plugin.lists.set("Hubs", list);
						             	    plugin.saveCustomConfig(plugin.lists, plugin.listFile);
										}
										MenuManager.updateHubInv();
					                    String original = MessageManager.getMessageYml().getString("Hub.Set");
					                    String replaced = original.replaceAll("%hub%", hub);
					                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
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
