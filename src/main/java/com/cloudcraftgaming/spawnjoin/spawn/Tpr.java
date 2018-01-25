package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Tpr
implements CommandExecutor {
	Main plugin;
	public Tpr(Main instance) {
		plugin = instance;
	}
	
	public static ArrayList<String> cooldown = new ArrayList<>();
	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpr")) {
			if (!(sender instanceof Player)) {
				if (plugin.getConfig().getString("Commands.Tpr.Enabled").equalsIgnoreCase("True")) {
				    sender.sendMessage(MessageManager.getPrefix() + MessageManager.getPlayerOnlyMessage());
				}
			} else {
				Player player = (Player) sender;
				if (plugin.getConfig().getString("Commands.Tpr.Enabled").equalsIgnoreCase("True")) {
				    if (player.hasPermission("SpawnJoin.use.tpr")) {
				    	List<String> list = plugin.getConfig().getStringList("TPR.Worlds");
				    	String world = player.getLocation().getWorld().getName();
				    	if (list.contains(world)) {
							DelayChecker.tprDelayCheck(world, player);
				    	} else {
				    		if (plugin.getConfig().getString("NOTIFICATIONS.TprAllowed").equalsIgnoreCase("True")) {
								String msg = MessageManager.getMessageYml().getString("Tpr.Disallowed");
				    			String disallowed = (ChatColor.translateAlternateColorCodes('&', msg));
				    			player.sendMessage(MessageManager.getPrefix() + disallowed);
				    		}
				    	}
				    } else {
				    	if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
				    		sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
				    	}
				    }
				}
			}
		}
	  return false;
	}
}