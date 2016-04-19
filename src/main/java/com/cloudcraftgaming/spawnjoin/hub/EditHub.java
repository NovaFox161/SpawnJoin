package com.cloudcraftgaming.spawnjoin.hub;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class EditHub 
implements CommandExecutor {
	Main plugin;	
	public EditHub(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("edithub")) {
			if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
				String prefix = MessageManager.getPrefix();
				String perm = MessageManager.getNoPermMessage();
				if (!(sender.hasPermission("SpawnJoin.use.edithub"))) {
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
						String hubName = args[0];
						String type = args[1];
						if (type.equalsIgnoreCase("cost")) {
							String number = args[2];
							EditHub.editCost(hubName, number, sender);
						} else if (type.equalsIgnoreCase("name")) {
							String newName = args[2];
							EditHub.editName(hubName, newName, sender);
						} else if (type.equalsIgnoreCase("item")) {
							String item = args[2];
							EditHub.editItem(hubName, item, sender);
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
	private static void editCost(String hubName, String number, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.hubs.contains("HUBS." + hubName))) {
			String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			Integer costInt = Integer.valueOf(number);
			Main.plugin.hubs.set("HUBS." + hubName + ".cost", costInt);
			Main.plugin.saveCustomConfig(Main.plugin.hubs, Main.plugin.hubFile);
			String original = MessageManager.getMessageYml().getString("Hub.EditCost");
			String replaced = original.replaceAll("%hub%", hubName).replaceAll("%cost%", number);
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
			MenuManager.updateHubInv();
		}
	}
	private static void editName(String hubName, String newName, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.hubs.contains("HUBS." + hubName))) {
			String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			if (Main.plugin.hubs.contains("HUBS." + newName)) {
				String msg = MessageManager.getMessageYml().getString("Hub.AlreadyExists");
				sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
			} else {
				String w = Main.plugin.hubs.getString("HUBS." + hubName + ".world");
				double x = Main.plugin.hubs.getDouble("HUBS." + hubName + ".x");
				double y = Main.plugin.hubs.getDouble("HUBS." + hubName + ".y");
				double z = Main.plugin.hubs.getDouble("HUBS." + hubName + ".z");
				int ya = Main.plugin.hubs.getInt("HUBS." + hubName + ".yaw");
				int pi = Main.plugin.hubs.getInt("HUBS." + hubName + ".pitch");
				int item = Main.plugin.hubs.getInt("HUBS." + hubName + ".item");
				int cost = Main.plugin.hubs.getInt("HUBS." + hubName + ".cost");
				Main.plugin.hubs.set("HUBS." + newName + ".world", w);
				Main.plugin.hubs.set("HUBS." + newName + ".x", x);
	            Main.plugin.hubs.set("HUBS." + newName + ".y", y);
	            Main.plugin.hubs.set("HUBS." + newName + ".z", z);
	            Main.plugin.hubs.set("HUBS." + newName + ".yaw", ya);
	            Main.plugin.hubs.set("HUBS." + newName + ".pitch", pi);
	            Main.plugin.hubs.set("HUBS." + newName + ".item", item);
	            Main.plugin.hubs.set("HUBS." + newName + ".cost", cost);
	            Main.plugin.hubs.set("HUBS." + hubName, null);
	            List<String> list = Main.plugin.lists.getStringList("Hubs");
	            list.remove(hubName);
	            list.add(newName);
	            Main.plugin.lists.set("Hubs", list);
	            Main.plugin.saveCustomConfig(Main.plugin.hubs, Main.plugin.hubFile);
	            Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
	            String original = MessageManager.getMessageYml().getString("Hub.EditName");
	            String replaced = original.replaceAll("%hub%", newName);
	            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
				MenuManager.updateHubInv();
			}
		}
	}
	@SuppressWarnings("deprecation")
	private static void editItem(String hubName, String item, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.hubs.contains("HUBS." + hubName))) {
			String msg = MessageManager.getMessageYml().getString("Hub.NoHub");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			try {
				Integer itemId = Integer.parseInt(item);
				Main.plugin.hubs.set("HUBS." + hubName + ".item", itemId);
				Main.plugin.saveCustomConfig(Main.plugin.hubs, Main.plugin.hubFile);
				Material itemMat = Material.getMaterial(itemId);
				String msgOr = MessageManager.getMessageYml().getString("Hub.EditItem");
				String msg = msgOr.replaceAll("%hub%", hubName).replaceAll("%item%", itemMat.name());
				sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
				MenuManager.updateHubInv();
			} catch (NumberFormatException e) {
				String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
				sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
			}
		}
	}
}