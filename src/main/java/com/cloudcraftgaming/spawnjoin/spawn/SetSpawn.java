package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpawn
implements CommandExecutor {
	Main plugin;
	
	public SetSpawn(Main instance)
	{
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		String setSpawn = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spawn.set")));
		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(prefix + "Only players can do this!");
			}
			else {
				Player player = (Player) sender;
				if (!(player.hasPermission("SpawnJoin.use.setspawn"))) {
					player.sendMessage(prefix + perm);
				}
				else {		
					if (plugin.getConfig().getString("Spawn.Save to File").equalsIgnoreCase("False")) {
						Location loc = player.getLocation();
						String world = player.getWorld().getName();
						Bukkit.getWorld(world).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
						player.sendMessage(prefix + setSpawn);
					}
					else {
						String world = player.getWorld().getName();
				        plugin.spawns.set("Spawns." + world + ".world", player.getLocation().getWorld().getName());
			            plugin.spawns.set("Spawns." + world + ".x", player.getLocation().getX());
			            plugin.spawns.set("Spawns." + world + ".y", player.getLocation().getY());
			            plugin.spawns.set("Spawns." + world + ".z", player.getLocation().getZ());
			            plugin.spawns.set("Spawns." + world + ".yaw", player.getLocation().getYaw());
			            plugin.spawns.set("Spawns." + world + ".pitch", player.getLocation().getPitch());
			            plugin.spawns.set("Spawns." + world + ".item", 2);
			            plugin.spawns.set("Spawns." + world + ".cost", 0.0);
						Main.plugin.saveCustomConfig(Main.plugin.spawns, Main.plugin.spawnFile);
						Bukkit.getWorld(world).setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
						if (plugin.lists.contains("Spawns")) {
							if (!(plugin.lists.getString("Spawns").contains(world))) {
								List<String> list = plugin.lists.getStringList("Spawns");
								list.add(world);
								plugin.lists.set("Spawns", list);
								plugin.saveCustomConfig(plugin.lists, plugin.listFile);
							}
						}
						else {
							List<String> list = plugin.lists.getStringList("Spawns");
							list.add(world);
							plugin.lists.set("Spawns", list);
							plugin.saveCustomConfig(plugin.lists, plugin.listFile);
						}
						MenuManager.updateSpawnInv();
						player.sendMessage(prefix + setSpawn);
					}
				}
			}	
		}
	return false;
	}
}