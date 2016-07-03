package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class sethome 
implements CommandExecutor {
Main plugin;
	
	public sethome(Main instance)
	{
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (command.getName().equalsIgnoreCase("sethome")) {
			if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getPlayerOnlyMessage()));
				}
				else {
					Player player = (Player) sender;
					UUID Id = player.getUniqueId();
					if (!(player.hasPermission("SpawnJoin.use.sethome"))) {
						if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
							sender.sendMessage(prefix + perm);
						}
					}
					else {
						if (args.length < 1) {
							if (player.hasPermission("SpawnJoin.bypass.homelimit") 
									|| plugin.getConfig().getBoolean("Commands.Home.GlobalLimit.Enabled")) {
								if (player.hasPermission("SpawnJoin.bypass.homelimit")) {
									sethome.setHome("Home", sender);
								}
								else {
									String allowed = plugin.getConfig().getString("Commands.Home.GlobalLimit.Limit");
									Integer allowedNumber = Integer.valueOf(allowed);
									Integer currentNumber = plugin.homeData.getInt("Players." + Id + ".HomeNumber");
									Integer newNumber = currentNumber + 1;
									if (newNumber > allowedNumber) {
										String original = MessageManager.getMessageYml().getString("Home.TooMany");
										String replaced = original.replaceAll("%number%", allowed);
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
									}
									else if (currentNumber == 1) {
										sethome.setHomeOne("Home", sender);
									}
									else {
										sethome.setHome("Home", sender);
									}
								}
							}
							else {
								Integer currentNumber = Integer.valueOf(plugin.homeData.getString("Players." + Id + ".HomeNumber"));
								String Allowed = plugin.homeData.getString("Players." + Id + ".AllowedHomes");
								Integer AllowedNumber = Integer.valueOf(Allowed);
								Integer newNumber = currentNumber + 1;
								if (newNumber > AllowedNumber) {
									String original = MessageManager.getMessageYml().getString("Home.TooMany");
									String replaced = original.replaceAll("%number%", Allowed);
									player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
								}
								else if (currentNumber == 1) {
									sethome.setHomeOne("Home", sender);
								}
								else {
									sethome.setHome("Home", sender);
								}
							}
					    }
						else if (args.length == 1) {
							String home = args[0];
							if (player.hasPermission("SpawnJoin.bypass.homelimit")
									|| plugin.getConfig().getBoolean("Commands.Home.GlobalLimit.Enabled")) {
								if (player.hasPermission("SpawnJoin.bypass.homelimit")) {
									sethome.setHome(home, sender);
								}
								else {
									String allowed = plugin.getConfig().getString("Commands.Home.GlobalLimit.Limit");
									Integer allowedNumber = Integer.valueOf(allowed);
									Integer currentNumber = plugin.homeData.getInt("Players." + Id + ".HomeNumber");
									Integer newNumber = currentNumber + 1;
									if (newNumber > allowedNumber) {
										String original = MessageManager.getMessageYml().getString("Home.TooMany");
										String replaced = original.replaceAll("%number%", allowed);
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
									}
									else if (currentNumber == 1) {
										sethome.setHomeOne(home, sender);
									}
									else {
										sethome.setHome(home, sender);
									}
								}
							}
							else {
								Integer currentNumber = Integer.valueOf(plugin.homeData.getString("Players." + Id + ".HomeNumber"));
								String Allowed = plugin.homeData.getString("Players." + Id + ".AllowedHomes");
								Integer AllowedNumber = Integer.valueOf(Allowed);
								Integer newNumber = currentNumber + 1;
								if (newNumber > AllowedNumber) {
									String original = MessageManager.getMessageYml().getString("Home.TooMany");
									String replaced = original.replaceAll("%number%", Allowed);
									player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
								}
								else {
									sethome.setHome(home, sender);
								}
							}
						}
						else if (args.length > 1) {
							if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
								sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
							}
						}
					}
				}
			}
		}
		return false;
	}
	private static void setHome(String home, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		Player player = (Player) sender;
		UUID Id = player.getUniqueId();
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".world", player.getLocation().getWorld().getName());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".x", player.getLocation().getX());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".y", player.getLocation().getY());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".z", player.getLocation().getZ());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".yaw", player.getLocation().getYaw());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".pitch", player.getLocation().getPitch());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".item", 355);
		Main.plugin.saveCustomConfig(Main.plugin.homes, Main.plugin.homeFile);
        String oldS = Main.plugin.homeData.getString("Players." + Id + ".HomeNumber");
	    Integer old = Integer.valueOf(oldS);
	    Integer newN = old + 1;
	    Main.plugin.homeData.set("Players." + Id + ".HomeNumber", newN);
	    Main.plugin.saveCustomConfig(Main.plugin.homeData, Main.plugin.homeDataFile);
        if (!(Main.plugin.lists.getStringList("Homes." + Id).contains(home))) {
        	List<String> list = Main.plugin.lists.getStringList("Homes." + Id);
        	list.add(home);
        	Main.plugin.lists.set("Homes." + Id, list);
        	Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
        }
        String original = MessageManager.getMessageYml().getString("Home.Set");
        String replaced = original.replaceAll("%home%", home);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		MenuManager.updatePlayerHomeInv(player);
	}
	private static void setHomeOne(String home, CommandSender sender) {
		String prefix = MessageManager.getPrefix();
		Player player = (Player) sender;
		UUID Id = player.getUniqueId();
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".world", player.getLocation().getWorld().getName());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".x", player.getLocation().getX());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".y", player.getLocation().getY());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".z", player.getLocation().getZ());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".yaw", player.getLocation().getYaw());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".pitch", player.getLocation().getPitch());
		Main.plugin.homes.set("HOMES." + Id + "." + home + ".item", 355);
		Main.plugin.saveCustomConfig(Main.plugin.homes, Main.plugin.homeFile);
        if (!(Main.plugin.lists.getStringList("Homes." + Id).contains(home))) {
        	List<String> list = Main.plugin.lists.getStringList("Homes." + Id);
        	list.add(home);
        	Main.plugin.lists.set("Homes." + Id, list);
        	Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
        }
        String original = MessageManager.getMessageYml().getString("Home.Set");
        String replaced = original.replaceAll("%home%", home);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		MenuManager.updatePlayerHomeInv(player);
	}
}
