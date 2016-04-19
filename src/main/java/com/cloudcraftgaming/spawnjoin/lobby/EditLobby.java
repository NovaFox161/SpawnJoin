package com.cloudcraftgaming.spawnjoin.lobby;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class EditLobby 
implements CommandExecutor {
	Main plugin;	
	public EditLobby(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("editlobby")) {
			if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
				String prefix = MessageManager.getPrefix();
				String perm = MessageManager.getNoPermMessage();
				if (!(sender.hasPermission("SpawnJoin.use.editlobby"))) {
					sender.sendMessage(prefix + perm);
				} else {
					if (args.length < 1) {
						String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					} else if (args.length == 1) {
						String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					} else if (args.length == 2) {
						String msg = MessageManager.getMessageYml().getString("Notifications.FewArgs");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					} else if (args.length == 3) {
						String lobbyName = args[0];
						String type = args[1];
						if (type.equalsIgnoreCase("cost")) {
							String number = args[2];
							EditLobby.editCost(lobbyName, number, sender);
						} else if (type.equalsIgnoreCase("name")) {
							String newName = args[2];
							EditLobby.editName(lobbyName, newName, sender);
						} else if (type.equalsIgnoreCase("item")) {
							String item = args[2];
							EditLobby.editItem(lobbyName, item, sender);
						}
					} else if (args.length > 3) {
						String msg = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					}
				}
			} else {
				return false;
			}
		}
		return false;
	}
	private static void editCost(String lobbyName, String number, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.lobs.contains("LOBBIES." + lobbyName))) {
			String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			Integer costInt = Integer.valueOf(number);
			Main.plugin.lobs.set("LOBBIES." + lobbyName + ".cost", costInt);
			Main.plugin.saveCustomConfig(Main.plugin.lobs, Main.plugin.lobFile);
			String original = MessageManager.getMessageYml().getString("Lobby.EditCost");
			String replaced = original.replaceAll("%lobby%", lobbyName).replaceAll("%cost%", number);
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		}
	}
	private static void editName(String lobbyName, String newName, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.lobs.contains("LOBBIES." + lobbyName))) {
			String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			if (Main.plugin.lobs.contains("LOBBIES." + newName)) {
				String msg = MessageManager.getMessageYml().getString("Lobby.AlreadyExists");
				sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
			} else {
				String w = Main.plugin.lobs.getString("LOBBIES." + lobbyName + ".world");
				double x = Main.plugin.lobs.getDouble("LOBBIES." + lobbyName + ".x");
				double y = Main.plugin.lobs.getDouble("LOBBIES." + lobbyName + ".y");
				double z = Main.plugin.lobs.getDouble("LOBBIES." + lobbyName + ".z");
				int ya = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".yaw");
				int pi = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".pitch");
				int item = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".item");
				int cost = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".cost");
				Main.plugin.lobs.set("LOBBIES." + newName + ".world", w);
				Main.plugin.lobs.set("LOBBIES." + newName + ".x", x);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".y", y);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".z", z);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".yaw", ya);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".pitch", pi);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".item", item);
	            Main.plugin.lobs.set("LOBBIES." + newName + ".cost", cost);
	            Main.plugin.lobs.set("LOBBIES." + lobbyName, null);
	            List<String> list = Main.plugin.lists.getStringList("Lobbies");
	            list.remove(lobbyName);
	            list.add(newName);
	            Main.plugin.lists.set("Lobbies", list);
	            Main.plugin.saveCustomConfig(Main.plugin.lobs, Main.plugin.lobFile);
	            Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
	            String original = MessageManager.getMessageYml().getString("Lobby.EditName");
	            String replaced = original.replaceAll("%lobby%", newName);
	            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
			}
		}
	}
	@SuppressWarnings("deprecation")
	private static void editItem(String lobbyName, String item, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.lobs.contains("LOBBIES." + lobbyName))) {
			String msg = MessageManager.getMessageYml().getString("Lobby.NoLobby");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			try {
				Integer itemId = Integer.parseInt(item);
				Main.plugin.lobs.set("LOBBIES." + lobbyName + ".item", itemId);
				Main.plugin.saveCustomConfig(Main.plugin.lobs, Main.plugin.lobFile);
				Material itemMat = Material.getMaterial(itemId);
				String msgOr = MessageManager.getMessageYml().getString("Lobby.EditItem");
				String msg = msgOr.replaceAll("%lobby%", lobbyName).replaceAll("%item%", itemMat.name());
				sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
				MenuManager.updateLobbyInv();
			} catch (NumberFormatException e) {
				String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
				sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
			}
		}
	}
}