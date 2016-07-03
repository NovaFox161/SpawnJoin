package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetLobby implements CommandExecutor 
{
	Main plugin;
	
	public SetLobby(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (cmd.getName().equalsIgnoreCase("setlobby")) {
			if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
			    if (!(sender instanceof Player)) {
				    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
			    }
			    else {
				    Player p = (Player) sender;
				    if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
				        if (p.hasPermission("SpawnJoin.use.setlobby")) {
				    	    if (args.length < 1) {
     					        plugin.lobs.set("LOBBIES.lobby.world", p.getLocation().getWorld().getName());
         			            plugin.lobs.set("LOBBIES.lobby.x", p.getLocation().getX());
	        		            plugin.lobs.set("LOBBIES.lobby.y", p.getLocation().getY());
		        	            plugin.lobs.set("LOBBIES.lobby.z", p.getLocation().getZ());
			                    plugin.lobs.set("LOBBIES.lobby.yaw", p.getLocation().getYaw());
			                    plugin.lobs.set("LOBBIES.lobby.pitch", p.getLocation().getPitch());
			                    plugin.lobs.set("LOBBIES.lobby.item", 2);
			                    plugin.lobs.set("LOBBIES.lobby.cost", 0.0);
			                    plugin.saveCustomConfig(plugin.lobs, plugin.lobFile);
			                    if (!(plugin.lists.getStringList("Lobbies").contains("lobby"))) {
			                    	List<String> list = plugin.lists.getStringList("Lobbies");
			                    	list.add("lobby");
			                    	plugin.lists.set("Lobbies", list);
			                    	plugin.saveCustomConfig(plugin.lists, plugin.listFile);
			                    }
								MenuManager.updateLobbyInv();
			                    String original = MessageManager.getMessageYml().getString("Lobby.Set");
			                    String replaced = original.replaceAll("%lobby%", "");
			                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
				    	    }
				    	    else {
				    		    if (args.length == 1) {
				    			    String lobby = args[0];
					    			plugin.lobs.set("LOBBIES." + lobby + ".world", p.getLocation().getWorld().getName());
    	     			            plugin.lobs.set("LOBBIES." + lobby + ".x", p.getLocation().getX());
	    	    		            plugin.lobs.set("LOBBIES." + lobby + ".y", p.getLocation().getY());
		    	    	            plugin.lobs.set("LOBBIES." + lobby + ".z", p.getLocation().getZ());
			    	                plugin.lobs.set("LOBBIES." + lobby + ".yaw", p.getLocation().getYaw());
				                    plugin.lobs.set("LOBBIES." + lobby + ".pitch", p.getLocation().getPitch());
				                    plugin.lobs.set("LOBBIES." + lobby + ".item", 2);
				                    plugin.lobs.set("LOBBIES." + lobby + ".cost", 0.0);
				                    plugin.saveCustomConfig(plugin.lobs, plugin.lobFile);
				                    if (!(plugin.lists.getStringList("Lobbies").contains(lobby))) {
				                    	List<String> list = plugin.lists.getStringList("Lobbies");
				                    	list.add(lobby);
					             	    plugin.lists.set("Lobbies", list);
					             	    plugin.saveCustomConfig(plugin.lists, plugin.listFile);
				                    	}
									MenuManager.updateLobbyInv();
				                    String original = MessageManager.getMessageYml().getString("Lobby.Set");
				                    String replaced = original.replaceAll("%lobby%", lobby);
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
		return false;
	}
}