package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Lobbies 
implements CommandExecutor 
{
	Main plugin;
	
	public Lobbies(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (cmd.getName().equalsIgnoreCase("lobbies")) {
			if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
				if (!(sender.hasPermission("SpawnJoin.use.lobbies"))) {
					sender.sendMessage(prefix + perm);
				} else {
					if (plugin.lists.contains("Lobbies")) {
						if (!(sender instanceof Player)) {
							String original = plugin.lists.getString("Lobbies");
							String replaced = original.replace("[", "").replace("]", "");
							for (String lobby : plugin.lists.getStringList("Lobbies")) {
								if (sender.hasPermission("SpawnJoin.use.lobby." + lobby)) {
									replaced = replaced.replaceAll(lobby, ChatColor.GREEN + lobby + ChatColor.RED);
								}
							}
							String heading = MessageManager.getMessageYml().getString("Lobby.LobbiesHeading");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
							sender.sendMessage(ChatColor.RED + replaced);
						} else if (plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")
								|| plugin.getConfig().getString("Inventory.ListCommandOverride").equalsIgnoreCase("True")) {
							Player player = (Player) sender;
							player.openInventory(MenuManager.lobbyInv);
							String msg = MessageManager.getMessageYml().getString("Inventory.OpenLobby");
							player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
						} else {
							String original = plugin.lists.getString("Lobbies");
							String replaced = original.replace("[", "").replace("]", "");
							for (String lobby : plugin.lists.getStringList("Lobbies")) {
								if (sender.hasPermission("SpawnJoin.use.lobby." + lobby)) {
									replaced = replaced.replaceAll(lobby, ChatColor.GREEN + lobby + ChatColor.RED);
								}
							}
							String heading = MessageManager.getMessageYml().getString("Lobby.LobbiesHeading");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
							sender.sendMessage(ChatColor.RED + replaced);
						}
					} else {
						String msg = MessageManager.getMessageYml().getString("Lobby.NoSet");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					}
				}
			}
		}
		return false;	
	}
}