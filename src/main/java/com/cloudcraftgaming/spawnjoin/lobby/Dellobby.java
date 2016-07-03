package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Dellobby 
implements CommandExecutor 
{
	Main plugin;
	
	public Dellobby(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (cmd.getName().equalsIgnoreCase("dellobby")) {
			if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
				if (!(sender.hasPermission("SpawnJoin.use.dellobby"))) {
					sender.sendMessage(prefix + perm);
				}
				else if (args.length < 1) {
					if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
					}
				}
				else if (args.length == 1) {
					String lobby = args[0];
					if (!(plugin.lobs.contains("LOBBIES." + lobby))) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.NoLobby")));
					}
					else {
						plugin.lobs.set("LOBBIES." + lobby, null);
						List<String> list = plugin.lists.getStringList("Lobbies");
				        list.remove(lobby);
				        plugin.lists.set("Lobbies", list);
				        plugin.saveCustomConfig(plugin.lists, plugin.listFile);
				        plugin.saveCustomConfig(plugin.lobs, plugin.lobFile);
				        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.Deleted")));
						MenuManager.updateLobbyInv();
					}
				}
		    }
		}
		return false;
	}
}