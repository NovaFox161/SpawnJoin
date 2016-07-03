package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.spawn.Tpr;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DelayChecker {
	public static void warpDelayCheck(String warpName, Player player) {
		if (player.hasPermission("SpawnJoin.bypass.warp")) {
			Teleporter.warp(warpName, player);
		}
		else {
			DelayChecker.warpTime(warpName, player);
		}
	}
	public static void hubDelayCheck(String hubName, Player player) {
		if (player.hasPermission("SpawnJoin.bypass.hub")) {
			Teleporter.hub(hubName, player);
		}
		else {
			DelayChecker.hubTime(hubName, player);
		}
	}
	public static void lobbyDelayCheck(String lobbyName, Player player) {
		if (player.hasPermission("SpawnJoin.bypass.lobby")) {
			Teleporter.lobby(lobbyName, player);
		}
		else {
			DelayChecker.lobbyTime(lobbyName, player);
		}
	}
	public static void spectateDelayCheck(String locName, Player player) {
		if (player.hasPermission("SpawnJoin.bypass.spectate")) {
			Teleporter.spectate(locName, player);
		}
		else {
			DelayChecker.spectateTime(locName, player);
		}
	}
	public static void tprDelayCheck(String world, final Player player) {
		if (player.hasPermission("SpawnJoin.cooldown.tpr")) {
			if (player.hasPermission("SpawnJoin.bypass.tpr")) {
				Teleporter.tpr(world, player);
			}
			else {
				DelayChecker.tprTime(world, player);
			}
		}
		else {
			Tpr.cooldown.add(player.getName());
			Integer coolInt = Main.plugin.getConfig().getInt("Commands.Tpr.Cooldown");
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
			Tpr.cooldown.remove(player.getName());
			}
		}, coolInt * 20L);
		if (player.hasPermission("SpawnJoin.bypass.tpr")) {
			Teleporter.tpr(world, player);
		}
		else {
			DelayChecker.tprTime(world, player);
			}
		}
	}
	public static void spawnDelayCheck(String spawnName,final Player player) {
		if (player.hasPermission("SpawnJoin.bypass.spawn")) {
			if (Main.plugin.getConfig().getString("Spawn.PerWorld").equalsIgnoreCase("True")) {
				if (LocationChecker.spawnExists(spawnName)) {
					if (LocationChecker.spawnOnFile(spawnName)) {
						Teleporter.spawn(spawnName, player);
					} else {
						Teleporter.spawn(Bukkit.getWorld(spawnName), player);
					}
				} else {
					Teleporter.spawn(Bukkit.getWorld(spawnName), player);
				}
			}
		} else {
			DelayChecker.spawnDelay(spawnName, player);
		}
	}
	public static void homeDelayCheck(String home, final Player player) {
		if (player.hasPermission("SpawnJoin.bypass.Home")) {
			Teleporter.home(home, player);
		}
		else {
			DelayChecker.homeDelay(home, player);
		}
	}
	private static void warpTime(final String warpName, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Warp.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Warp.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.warp(warpName, player);
			}
		}, delayInt * 20L);
	}
	private static void hubTime(final String hubName, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Hub.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Hub.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.hub(hubName, player);
			}
		}, delayInt * 20L);
	}
	private static void lobbyTime(final String lobbyName, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Lobby.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Lobby.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.lobby(lobbyName, player);
			}
		}, delayInt * 20L);
	}
	private static void spectateTime(final String locName, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Spectate.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Spectate.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.spectate(locName, player);
			}
		}, delayInt * 20L);
	}
	private static void tprTime(final String world, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Tpr.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Tpr.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.tpr(world, player);
			}
		}, delayInt * 20L);
	}
	private static void spawnDelay(final String worldName, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Spawn.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Spawn.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				if (LocationChecker.spawnOnFile(worldName)) {
					Teleporter.spawn(worldName, player);
				} else {
					Teleporter.spawn(Bukkit.getWorld(worldName), player);
				}
			 }
		 }, delayInt * 20L);
	}
	private static void homeDelay(final String home, final Player player) {
		String prefix = MessageManager.getPrefix();
		String delayString = Main.plugin.getConfig().getString("Commands.Home.Delay");
		Integer delayInt = Integer.valueOf(delayString);
		String original = (MessageManager.getMessageYml().getString("Home.Delay"));
		String replaced = original.replaceAll("%delay%", delayString);
		player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			public void run() {
				Teleporter.home(home, player);
			 }
		 }, delayInt * 20L);
	}
}