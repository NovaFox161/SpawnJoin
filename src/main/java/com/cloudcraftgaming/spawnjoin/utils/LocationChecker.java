package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LocationChecker {
	public static boolean hubExists(String hub) {
		return Main.plugin.hubs.contains("HUBS." + hub);
	}
	public static boolean lobbyExists(String lobby) {
		return Main.plugin.lobs.contains("LOBBIES." + lobby);
	}
	public static boolean warpExists(String warp) {
		return Main.plugin.warps.contains("WARPS." + warp);
	}
	public static boolean spectateExists(String loc) {
		return Main.plugin.spec.contains("SPECTATE." + loc);
	}
	public static boolean spawnExists(String worldName) {
		if (Main.plugin.spawns.contains("Spawns." + worldName)) {
			return true;
		} else if (Main.plugin.spawnData.contains(worldName)) {
			return true;
		} else if (!(Bukkit.getWorld(worldName) == null)) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean spawnOnFile(String spawnName) {
		return (Main.plugin.spawns.contains("Spawns." + spawnName));
	}
	public static boolean homeExists(String homeName, Player player) {
		return Main.plugin.homes.contains("HOMES." + player.getUniqueId() + "." + homeName);
	}
	public static String determineSpawnWorld(String currentWorld) {
		if (Main.plugin.getConfig().getString("Spawn.PerWorld").equalsIgnoreCase("True")) {
			if (Main.plugin.spawnData.contains(currentWorld)) {
				return Main.plugin.spawnData.getString(currentWorld);
			} else {
				return currentWorld;
			}
		} else {
			if (!(Bukkit.getWorld(Main.plugin.getConfig().getString("Spawn.Default.World")) == null)) {
				return Main.plugin.getConfig().getString("Spawn.Default.World");
			} else {
				return currentWorld;
			}
		}
	}
	public static String determineRespawnWorld(String currentWorld) {
		if (Main.plugin.getConfig().getString("Respawn.PerWorld").equalsIgnoreCase("True")) {
			if (Main.plugin.spawnData.contains(currentWorld)) {
				return Main.plugin.spawnData.getString(currentWorld);
			} else {
				return currentWorld;
			}
		} else {
			if (!(Bukkit.getWorld(Main.plugin.getConfig().getString("Respawn.Default.World")) == null)) {
				return Main.plugin.getConfig().getString("Respawn.Default.World");
			} else {
				return currentWorld;
			}
		}
	}
}