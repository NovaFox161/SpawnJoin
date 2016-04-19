package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EditHome 
implements CommandExecutor {
	Main plugin;	
	public EditHome(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("edithome")) {
			String prefix = MessageManager.getPrefix();
			String perm = MessageManager.getNoPermMessage();
			if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("true")) {
				if (!(sender.hasPermission("SpawnJoin.use.edithome"))) {
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
						String type = args[1];
						if (type.equalsIgnoreCase("limit")) {
							if (sender.hasPermission("SpawnJoin.use.edithome.limit")) {
								String name = args[0];
								String number = args[2];
								EditHome.editLimit(name, number, sender);
							} else {
								sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
							}
						} else if (type.equalsIgnoreCase("item")) {
							if (sender instanceof Player) {
								String homeName = args[0];
								String itemIdS = args[2];
								try {
									Integer itemId = Integer.valueOf(itemIdS);
									Player player = (Player) sender;
									editItem(homeName, itemId, player);
								} catch (NumberFormatException e) {
									String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
									sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
								}
							} else {
								String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOnly");
								sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							}
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
	@SuppressWarnings("deprecation")
	private static void editLimit(String playerName, String number, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		OfflinePlayer target = Bukkit.getOfflinePlayer(playerName);
		UUID Id = target.getUniqueId();
		if (Main.plugin.homeData.contains("Players." + Id)) {
			Integer limit = Integer.valueOf(number);
			Main.plugin.homeData.set("Players." + Id + ".AllowedHomes", limit);
			Main.plugin.saveCustomConfig(Main.plugin.homeData, Main.plugin.homeDataFile);
			String original = MessageManager.getMessageYml().getString("Home.EditLimit");
			String replaced = original.replaceAll("%player%", playerName).replaceAll("%number%", number);
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		} else {
			String msg = MessageManager.getMessageYml().getString("Notifications.PlayerNotExist");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&',msg));
		}
	}
	@SuppressWarnings("deprecation")
	private static void editItem(String homeName, Integer itemId, Player sender) {
		if (LocationChecker.homeExists(homeName, sender)) {
			Main.plugin.homes.set("HOMES." + sender.getUniqueId() + "." + homeName + ".item", itemId);
			Main.plugin.saveCustomConfig(Main.plugin.homes, Main.plugin.homeFile);
			String msgOr = MessageManager.getMessageYml().getString("Home.EditItem");
			String msg = msgOr.replaceAll("%home%", homeName).replaceAll("%item%", Material.getMaterial(itemId).name());
			sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			String msg = MessageManager.getMessageYml().getString("Home.NoHome");
			sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
}
