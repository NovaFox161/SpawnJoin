package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LocationChecker {
    public static boolean hubExists(String hub) {
        return FileManager.getHubYml().contains("HUBS." + hub);
    }

    public static boolean lobbyExists(String lobby) {
        return FileManager.getLobbyYml().contains("LOBBIES." + lobby);
    }

    public static boolean warpExists(String warp) {
        return FileManager.getWarpYml().contains("WARPS." + warp);
    }

    public static boolean spectateExists(String loc) {
        return FileManager.getSpectateYml().contains("SPECTATE." + loc);
    }

    public static boolean spawnExists(String worldName) {
        if (FileManager.getSpawnYml().contains("Spawns." + worldName)) {
            return true;
        } else if (FileManager.getSpawnDataYml().contains(worldName)) {
            return true;
        } else return !(Bukkit.getWorld(worldName) == null);
    }

    public static boolean spawnOnFile(String spawnName) {
        return (FileManager.getSpawnYml().contains("Spawns." + spawnName));
    }

    public static boolean homeExists(String homeName, Player player) {
        return FileManager.getHomeYml().contains("HOMES." + player.getUniqueId() + "." + homeName);
    }

    public static String determineSpawnWorld(String currentWorld) {
        if (Main.plugin.getConfig().getString("Spawn.PerWorld").equalsIgnoreCase("True")) {
            if (FileManager.getSpawnDataYml().contains(currentWorld)) {
                return FileManager.getSpawnDataYml().getString(currentWorld);
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
            if (FileManager.getSpawnDataYml().contains(currentWorld)) {
                return FileManager.getSpawnDataYml().getString(currentWorld);
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