package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Homes 
implements CommandExecutor {
	Main plugin;
		
		public Homes(Main instance)
		{
			plugin = instance;
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			String prefix = MessageManager.getPrefix();
			String perm = MessageManager.getNoPermMessage();
			if (command.getName().equalsIgnoreCase("homes")) {
				if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
					if (!(sender instanceof Player)) {
						sender.sendMessage(prefix + MessageManager.getPlayerOnlyMessage());
					} else {
						if (!(sender.hasPermission("SpawnJoin.use.homes"))) {
							if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
								sender.sendMessage(prefix + perm);
							}
						} else {
							Player player = (Player) sender;
							UUID Id = player.getUniqueId();
							if (!(plugin.lists.contains("Homes."+ Id))) {
								String msg = MessageManager.getMessageYml().getString("Home.NoSet");
								sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								if (plugin.getConfig().getString("Inventory.Home.Use").equalsIgnoreCase("True")
										&& plugin.getConfig().getString("Inventory.ListCommandOverride").equalsIgnoreCase("True")) {
									MenuManager.updatePlayerHomeInv(player);
									player.openInventory(MenuManager.getHomeInventory(player));
									String msg = MessageManager.getMessageYml().getString("Inventory.OpenHome");
									player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
								} else {
									String original = plugin.lists.getString("Homes." + Id);
									String replaced = original.replace("[", "").replace("]", "");
									String homesHeading = MessageManager.getMessageYml().getString("Home.HomesHeading");
									sender.sendMessage(ChatColor.translateAlternateColorCodes('&', homesHeading));
									sender.sendMessage(ChatColor.GREEN + replaced);
								}
							}
						}
					}
				}
			}
			return false;
		}
}
