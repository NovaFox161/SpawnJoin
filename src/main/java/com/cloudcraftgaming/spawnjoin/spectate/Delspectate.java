package com.cloudcraftgaming.spawnjoin.spectate;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Delspectate 
implements CommandExecutor {
	Main plugin;
	
	public Delspectate(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (cmd.getName().equalsIgnoreCase("delspectate")) {
			if (plugin.getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
				if (!(sender.hasPermission("SpawnJoin.use.delspectate"))) {
					sender.sendMessage(prefix + perm);
				}
				else if (args.length < 1) {
					if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
					}
				}
				else if (args.length == 1) {
					String loc = args[0];
					if (!(plugin.spec.contains("SPECTATE." + loc))) {
						sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.NoLoc")));
					}
					else {
						plugin.spec.set("SPECTATE." + loc, null);
						List<String> list = plugin.lists.getStringList("Spectate");
				        list.remove(loc);
				        plugin.lists.set("Spectate", list);
				        plugin.saveCustomConfig(plugin.lists, plugin.listFile);
				        plugin.saveCustomConfig(plugin.spec, plugin.specFile);
				        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.Deleted")));
						MenuManager.updateSpectateInv();
					}
				}
		    }
		}
		return false;
	}
}
