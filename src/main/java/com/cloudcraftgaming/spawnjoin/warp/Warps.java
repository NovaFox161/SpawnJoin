package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warps 
implements CommandExecutor 
{
	Main plugin;
	
	public Warps(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)  {
        if (cmd.getName().equalsIgnoreCase("warps")) {
			if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
				if (sender.hasPermission("SpawnJoin.use.warps")) {
					if (plugin.lists.contains("Warps")) {
						if (!(sender instanceof Player)) {
							String original = plugin.lists.getString("Warps");
							String replaced = original.replace("[", "").replace("]", "");
							for (String warp : plugin.lists.getStringList("Warps")) {
								if (sender.hasPermission("SpawnJoin.use.warp." + warp)) {
									replaced = replaced.replaceAll(warp, ChatColor.GREEN + warp + ChatColor.RED);
								}
							}
							String heading = MessageManager.getMessageYml().getString("Warp.WarpsHeading");
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
							sender.sendMessage(ChatColor.RED + replaced);
						} else {
							if (plugin.getConfig().getString("Inventory.Warp.Use").equalsIgnoreCase("True")
									&& plugin.getConfig().getString("Inventory.ListCommandOverride").equalsIgnoreCase("True")) {
								Player player = (Player) sender;
								player.openInventory(MenuManager.warpInv);
								String msg = MessageManager.getMessageYml().getString("Inventory.OpenWarp");
								player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
							} else {
								String original = plugin.lists.getString("Warps");
								String replaced = original.replace("[", "").replace("]", "");
								for (String warp : plugin.lists.getStringList("Warps")) {
									if (sender.hasPermission("SpawnJoin.use.warp." + warp)) {
										replaced = replaced.replaceAll(warp, ChatColor.GREEN + warp + ChatColor.RED);
									}
								}
								String heading = MessageManager.getMessageYml().getString("Warp.WarpsHeading");
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
								sender.sendMessage(ChatColor.RED + replaced);
							}
						}
					  } else {
						String msg = MessageManager.getMessageYml().getString("Warp.NoSet");
						sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
					}
				} else {
					if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
						sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
					}
				}
			}
		}
    return false;		
	}
}