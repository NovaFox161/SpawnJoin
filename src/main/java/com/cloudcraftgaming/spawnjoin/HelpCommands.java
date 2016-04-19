package com.cloudcraftgaming.spawnjoin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommands 
implements CommandExecutor  {
	Main plugin;
	public HelpCommands(Main instance) {
		plugin = instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		if (cmd.getName().equalsIgnoreCase("spawnjoin")) {
			if (args.length < 1) {
				sender.sendMessage(ChatColor.AQUA + "Please use " + ChatColor.GREEN + "/spawnjoin help" + ChatColor.AQUA + " for a list of commands.");
			} else {
			    if (args.length == 1) {
				    if (args[0].equalsIgnoreCase("help")) {
				    	if (sender.hasPermission("SpawnJoin.help")) {
				            Help.helpList(1, sender);
				    } else {
				    		if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
				    		    sender.sendMessage(prefix + perm);
				    		}
				    	}
		            } else if (args[0].equalsIgnoreCase("info")) {
				    	if (sender.hasPermission("SpawnJoin.admin")) {
				    		sender.sendMessage(ChatColor.GOLD + "-~-~-~-~" + ChatColor.AQUA + " SpawnJoin Info " + ChatColor.GOLD + "~-~-~-~-");
				    		sender.sendMessage(ChatColor.GREEN + "SpawnJoin is developed and managed by " + ChatColor.DARK_AQUA + "Shades161");
				    		sender.sendMessage(ChatColor.GREEN + "SpawnJoin version: " + ChatColor.BLUE + plugin.getDescription().getVersion());
				    		if (!(plugin.getConfig().getString("Config Version").equalsIgnoreCase(plugin.conVersion))) {
				    			sender.sendMessage(ChatColor.RED + "Config Outdated!!" + ChatColor.BLUE + " Version: " +ChatColor.RED + (plugin.getConfig().getString("Config Version")));
				    			sender.sendMessage(ChatColor.RED + "Plugin will not work properly!!");
				    			sender.sendMessage(ChatColor.RED + "Please copy your settings and delete the config file!!");
				    			sender.sendMessage(ChatColor.RED + "Then restart the server and change the defaults back to your desired settings!!");
				    		} else {
				    			sender.sendMessage(ChatColor.GREEN + "Config Up-To-Date!");
				    		}
				    		if (plugin.getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
				    			plugin.updateChecker = new UpdateChecker(plugin, "http://dev.bukkit.org/bukkit-plugins/teleport-spawn-join/files.rss");
								if (plugin.updateChecker.UpdateNeeded()) {
									sender.sendMessage(ChatColor.RED + "New update available for SpawnJoin version: " + ChatColor.BLUE + plugin.updateChecker.getVersion());
									sender.sendMessage(ChatColor.GREEN + "Download it at: " + ChatColor.BLUE + plugin.updateChecker.getLink());
								} else {
									sender.sendMessage(ChatColor.GREEN + "SpawnJoin is up to date!");
								}
				    		}
				    	} else {
				    		if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
				    			sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
				    		}
				    	}
				    } else if (args[0].equalsIgnoreCase("version")) {
				    	if (sender.hasPermission("SpawnJoin.admin")) {
				    		sender.sendMessage(ChatColor.GREEN + "SpawnJoin Version: " + ChatColor.BLUE + plugin.getDescription().getVersion());
				    		if (plugin.getConfig().getString("Config Version").equalsIgnoreCase(plugin.conVersion)) {
				    			if (plugin.getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
									plugin.updateChecker = new UpdateChecker(plugin, "http://dev.bukkit.org/bukkit-plugins/teleport-spawn-join/files.rss");
									if (plugin.updateChecker.UpdateNeeded()) {
										sender.sendMessage(ChatColor.RED + "New update available for SpawnJoin version: " + ChatColor.BLUE + plugin.updateChecker.getVersion());
										sender.sendMessage(ChatColor.GREEN + "Download it at: " + ChatColor.BLUE + plugin.updateChecker.getLink());
									} else {
										sender.sendMessage(ChatColor.GREEN + "SpawnJoin is up to date!");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "SpawnJoin cannot check for updates because it is disabled in the config.");
								}
							}
						} else {
							sender.sendMessage(prefix + perm);
						}
					} else {
						sender.sendMessage(prefix + ChatColor.RED + "Please use: " + ChatColor.GREEN + "/spawnjoin help" + ChatColor.RED + " for a list of commands.");
				    	}
				    } else if (args.length == 2) {
			    	String arg = args[0];
			    	if (arg.equalsIgnoreCase("help")) {
			    		String pageString = args[1];
						try {
							Integer page = Integer.valueOf(pageString);
							Help.helpList(page, sender);
						} catch (NumberFormatException e) {
							String msg = MessageManager.getMessageYml().getString("Notifications.NotInt");
							sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
						}
			    	} else {
			    		sender.sendMessage(prefix + ChatColor.RED + "Please use: " + ChatColor.GREEN + "/spawnjoin help" + ChatColor.RED + " for a list of commands.");
			    	}
			    } else if (args.length > 2) {
			    	sender.sendMessage(prefix + ChatColor.RED + "Please use: " + ChatColor.GREEN + "/spawnjoin help" + ChatColor.RED + " for a list of commands.");
			    }
		    }
		}
		return false;
	}
}