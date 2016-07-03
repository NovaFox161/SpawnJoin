package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class EditWarp 
implements CommandExecutor {
	Main plugin;	
	public EditWarp(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("editwarp")) {
			if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
				String prefix = MessageManager.getPrefix();
				String perm = MessageManager.getNoPermMessage();
				if (!(sender.hasPermission("SpawnJoin.use.editwarp"))) {
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
						String warpName = args[0];
						String type = args[1];
						if (type.equalsIgnoreCase("cost")) {
							String number = args[2];
							EditWarp.editCost(warpName, number, sender);
						} else if (type.equalsIgnoreCase("name")) {
							String newName = args[2];
							EditWarp.editName(warpName, newName, sender);
						} else if (type.equalsIgnoreCase("item")) {
							String item = args[2];
							EditWarp.editItem(warpName, item, sender);
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
	private static void editCost(String warpName, String number, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.warps.contains("WARPS." + warpName))) {
			String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			Integer costInt = Integer.valueOf(number);
			Main.plugin.warps.set("WARPS." + warpName + ".cost", costInt);
			Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);
			String original = MessageManager.getMessageYml().getString("Warp.EditCost");
			String replaced = original.replaceAll("%warp%", warpName).replaceAll("%cost%", number);
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
			MenuManager.updateWarpInv();
		}
	}
	private static void editName(String warpName, String newName, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.warps.contains("WARPS." + warpName))) {
			String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			if (Main.plugin.warps.contains("WARPS." + newName)) {
				String msg = MessageManager.getMessageYml().getString("Warp.AlreadyExists");
				sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
			} else {
				String w = Main.plugin.warps.getString("WARPS." + warpName + ".world");
				double x = Main.plugin.warps.getDouble("WARPS." + warpName + ".x");
				double y = Main.plugin.warps.getDouble("WARPS." + warpName + ".y");
				double z = Main.plugin.warps.getDouble("WARPS." + warpName + ".z");
				int ya = Main.plugin.warps.getInt("WARPS." + warpName + ".yaw");
				int pi = Main.plugin.warps.getInt("WARPS." + warpName + ".pitch");
				int item = Main.plugin.warps.getInt("WARPS." + warpName + ".item");
				int cost = Main.plugin.warps.getInt("WARPS." + warpName + ".cost");
				Main.plugin.warps.set("WARPS." + newName + ".world", w);
				Main.plugin.warps.set("WARPS." + newName + ".x", x);
	            Main.plugin.warps.set("WARPS." + newName + ".y", y);
	            Main.plugin.warps.set("WARPS." + newName + ".z", z);
	            Main.plugin.warps.set("WARPS." + newName + ".yaw", ya);
	            Main.plugin.warps.set("WARPS." + newName + ".pitch", pi);
	            Main.plugin.warps.set("WARPS." + newName + ".item", item);
	            Main.plugin.warps.set("WARPS." + newName + ".cost", cost);
	            Main.plugin.warps.set("WARPS." + warpName, null);
	            List<String> list = Main.plugin.lists.getStringList("Warps");
	            list.remove(warpName);
	            list.add(newName);
	            Main.plugin.lists.set("Warps", list);
	            Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);
	            Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
	            String original = MessageManager.getMessageYml().getString("Warp.EditName");
	            String replaced = original.replaceAll("%warp%", newName);
	            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
				MenuManager.updateWarpInv();
			}
		}
	}
	@SuppressWarnings("deprecation")
	private static void editItem(String warpName, String item, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		if (!(Main.plugin.warps.contains("WARPS." + warpName))) {
			String msg = MessageManager.getMessageYml().getString("Warp.NoWarp");
			sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
		} else {
			if (!item.contains(":")) {
				try {
					Integer itemId = Integer.parseInt(item);
					Main.plugin.warps.set("WARPS." + warpName + ".item", itemId);
					Main.plugin.warps.set("WARPS." + warpName + ".itemProp", null);
					Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);
					Material itemMat = Material.getMaterial(itemId);
					String msgOr = MessageManager.getMessageYml().getString("Warp.EditItem");
					String msg = msgOr.replaceAll("%warp%", warpName).replaceAll("%item%", itemMat.name());
					sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					MenuManager.updateWarpInv();
				} catch (NumberFormatException e) {
					String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
					sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
				}
			} else {
				try {
					Integer itemId = Integer.valueOf(item.split(":")[0]);
					Short damage = Short.valueOf(item.split(":")[1]);
					Main.plugin.warps.set("WARPS." + warpName + ".item", itemId);
					Main.plugin.warps.set("WARPS." + warpName + ".itemProp", damage);
					Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);
					Material itemMat = Material.getMaterial(itemId);
					String msgOr = MessageManager.getMessageYml().getString("Warp.EditItem");
					String msg = msgOr.replaceAll("%warp%", warpName).replaceAll("%item%", itemMat.name());
					sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					MenuManager.updateWarpInv();
				} catch (NumberFormatException e) {
					String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
					sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
				}
			}
		}
	}
}