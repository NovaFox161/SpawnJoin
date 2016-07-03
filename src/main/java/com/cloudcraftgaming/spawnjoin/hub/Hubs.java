package com.cloudcraftgaming.spawnjoin.hub;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hubs implements CommandExecutor {
	Main plugin;
	public Hubs(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		  if (cmd.getName().equalsIgnoreCase("hubs")) {
			if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
				if (!(sender.hasPermission("SpawnJoin.use.hubs"))) {
					sender.sendMessage(prefix + perm);
				} else {
					if (plugin.lists.contains("Hubs")) {
						if (!(sender instanceof Player)) {
							String original = plugin.lists.getString("Hubs");
							String replaced = original.replace("[", "").replace("]", "");
							for (String hub : plugin.lists.getStringList("Hubs")) {
								if (sender.hasPermission("SpawnJoin.use.hub." + hub)) {
									replaced = replaced.replaceAll(hub, ChatColor.GREEN + hub + ChatColor.RED);
								}
							}
							String hubHeading = MessageManager.getMessageYml().getString("Hub.HubsHeading");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hubHeading));
							sender.sendMessage(ChatColor.RED + replaced);
						} else if (plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")
								&& plugin.getConfig().getString("Inventory.ListCommandOverride").equalsIgnoreCase("True")) {
							Player player = (Player) sender;
							player.openInventory(MenuManager.hubInv);
							String msg = MessageManager.getMessageYml().getString("Inventory.OpenHub");
							player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
						} else {
							String original = plugin.lists.getString("Hubs");
							String replaced = original.replace("[", "").replace("]", "");
							for (String hub : plugin.lists.getStringList("Hubs")) {
								if (sender.hasPermission("SpawnJoin.use.hub." + hub)) {
									replaced = replaced.replaceAll(hub, ChatColor.GREEN + hub + ChatColor.RED);
								}
							}
							String hubHeading = MessageManager.getMessageYml().getString("Hub.HubsHeading");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hubHeading));
							sender.sendMessage(ChatColor.RED + replaced);
						}
					} else {
						String msg = MessageManager.getMessageYml().getString("Hub.NoSet");
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
					}
				}
			}
		}
		return false;	
	}
}