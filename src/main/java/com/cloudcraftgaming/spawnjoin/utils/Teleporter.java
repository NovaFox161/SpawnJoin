package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Teleporter {
    /**
     * Teleports the player to the specified warp.
     *
     * @param warpName The name of the warp location the player is teleported to.
     * @param player   The player who will be teleported.
     */
    public static void warp(String warpName, Player player) {
        String prefix = MessageManager.getPrefix();
        World w = Bukkit.getServer().getWorld(FileManager.getWarpYml().getString("WARPS." + warpName + ".world"));
        double x = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".x");
        double y = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".y");
        double z = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".z");
        int ya = FileManager.getWarpYml().getInt("WARPS." + warpName + ".yaw");
        int pi = FileManager.getWarpYml().getInt("WARPS." + warpName + ".pitch");
        player.teleport(new Location(w, x, y, z, ya, pi));
        String original = (MessageManager.getMessageYml().getString("Warp.Warp"));
        String replaced = original.replaceAll("%warp%", warpName);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
    }

    /**
     * Teleports the specified player to the specified warp, when caused by another sender.
     *
     * @param warpName The name of the warp the player will be teleported to.
     * @param target   The player who will be teleported it.
     * @param sender   The sender who caused the target to be teleported.
     */
    public static void warp(String warpName, Player target, CommandSender sender) {
        World w = Bukkit.getServer().getWorld(FileManager.getWarpYml().getString("WARPS." + warpName + ".world"));
        double x = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".x");
        double y = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".y");
        double z = FileManager.getWarpYml().getDouble("WARPS." + warpName + ".z");
        int ya = FileManager.getWarpYml().getInt("WARPS." + warpName + ".yaw");
        int pi = FileManager.getWarpYml().getInt("WARPS." + warpName + ".pitch");
        target.teleport(new Location(w, x, y, z, ya, pi));
        String name = target.getDisplayName();
        String or = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.Target")));
        String replace = or.replaceAll("%target%", name).replaceAll("%warp%", warpName);
        sender.sendMessage(MessageManager.getPrefix() + replace);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Warp").equalsIgnoreCase("True")) {
            String original = (MessageManager.getMessageYml().getString("Warp.Warp"));
            String replaced = original.replaceAll("%warp%", warpName);
            target.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    /**
     * Teleports the player to the specified hub.
     *
     * @param hubName The name of the hub the player will be teleported to.
     * @param player  The player that will be teleported.
     */
    public static void hub(String hubName, Player player) {
        World w = Bukkit.getServer().getWorld(FileManager.getHubYml().getString("HUBS." + hubName + ".world"));
        double x = FileManager.getHubYml().getDouble("HUBS." + hubName + ".x");
        double y = FileManager.getHubYml().getDouble("HUBS." + hubName + ".y");
        double z = FileManager.getHubYml().getDouble("HUBS." + hubName + ".z");
        int ya = FileManager.getHubYml().getInt("HUBS." + hubName + ".yaw");
        int pi = FileManager.getHubYml().getInt("HUBS." + hubName + ".pitch");
        player.teleport(new Location(w, x, y, z, ya, pi));
        String original = (MessageManager.getMessageYml().getString("Hub.Hub"));
        String replaced = original.replaceAll("%hub%", hubName);
        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', replaced));
    }

    /**
     * Teleports the specified player the the specified hub, when caused by another sender.
     *
     * @param hubName The name of the hub the player will be teleported to.
     * @param target  The player that will be teleported to the hub.
     * @param sender  The sender who caused the target to be teleported.
     */
    public static void hub(String hubName, Player target, CommandSender sender) {
        World w = Bukkit.getServer().getWorld(FileManager.getHubYml().getString("HUBS." + hubName + ".world"));
        double x = FileManager.getHubYml().getDouble("HUBS." + hubName + ".x");
        double y = FileManager.getHubYml().getDouble("HUBS." + hubName + ".y");
        double z = FileManager.getHubYml().getDouble("HUBS." + hubName + ".z");
        int ya = FileManager.getHubYml().getInt("HUBS." + hubName + ".yaw");
        int pi = FileManager.getHubYml().getInt("HUBS." + hubName + ".pitch");
        target.teleport(new Location(w, x, y, z, ya, pi));
        String name = target.getDisplayName();
        String or = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.Target")));
        String replace = or.replaceAll("%target%", name).replaceAll("%hub%", hubName);
        sender.sendMessage(target + replace);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Hub").equalsIgnoreCase("True")) {
            String original = (MessageManager.getMessageYml().getString("Hub.Hub"));
            String replaced = original.replaceAll("%hub%", hubName);
            target.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    /**
     * Teleports the player to the specified lobby.
     *
     * @param lobbyName Name of the lobby player will be teleported to.
     * @param player    The player that will be teleported.
     */
    public static void lobby(String lobbyName, Player player) {
        String prefix = MessageManager.getPrefix();
        World w = Bukkit.getServer().getWorld(FileManager.getLobbyYml().getString("LOBBIES." + lobbyName + ".world"));
        double x = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".x");
        double y = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".y");
        double z = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".z");
        int ya = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".yaw");
        int pi = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".pitch");
        player.teleport(new Location(w, x, y, z, ya, pi));
        String original = (MessageManager.getMessageYml().getString("Lobby.Lobby"));
        String replaced = original.replaceAll("%lobby%", lobbyName);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
    }

    /**
     * Teleports the specified player to the specified lobby when caused by another sender.
     *
     * @param lobbyName The name of the lobby the player will be teleported to.
     * @param target    The player that will be teleported.
     * @param sender    The sender who caused the target to be teleported to the lobby.
     */
    public static void lobby(String lobbyName, Player target, CommandSender sender) {
        World w = Bukkit.getServer().getWorld(FileManager.getLobbyYml().getString("LOBBIES." + lobbyName + ".world"));
        double x = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".x");
        double y = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".y");
        double z = FileManager.getLobbyYml().getDouble("LOBBIES." + lobbyName + ".z");
        int ya = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".yaw");
        int pi = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".pitch");
        target.teleport(new Location(w, x, y, z, ya, pi));
        String name = target.getDisplayName();
        String or = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.Target")));
        String replace = or.replaceAll("%target%", name).replaceAll("%lobby%", lobbyName);
        sender.sendMessage(MessageManager.getPrefix() + replace);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Lobby").equalsIgnoreCase("True")) {
            String original = (MessageManager.getMessageYml().getString("Lobby.Lobby"));
            String replaced = original.replaceAll("%lobby%", lobbyName);
            target.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    /**
     * Teleports the player to the specified spectating location.
     *
     * @param locName The name of the spectating location.
     * @param player  The player being teleported.
     */
    public static void spectate(String locName, Player player) {
        String prefix = MessageManager.getPrefix();
        World w = Bukkit.getServer().getWorld(FileManager.getSpectateYml().getString("SPECTATE." + locName + ".world"));
        double x = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".x");
        double y = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".y");
        double z = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".z");
        int ya = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".yaw");
        int pi = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".pitch");
        player.teleport(new Location(w, x, y, z, ya, pi));
        String original = (MessageManager.getMessageYml().getString("Spectate.Spectate"));
        String replaced = original.replaceAll("%loc%", locName);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
    }

    /**
     * Teleports the specified player to the specified spectating location when caused by another sender.
     *
     * @param locName The name of the spectating location.
     * @param target  The player being teleported.
     * @param sender  The sender that caused the target to be teleported.
     */
    public static void spectate(String locName, Player target, CommandSender sender) {
        String prefix = MessageManager.getPrefix();
        World w = Bukkit.getServer().getWorld(FileManager.getSpectateYml().getString("SPECTATE." + locName + ".world"));
        double x = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".x");
        double y = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".y");
        double z = FileManager.getSpectateYml().getDouble("SPECTATE." + locName + ".z");
        int ya = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".yaw");
        int pi = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".pitch");
        target.teleport(new Location(w, x, y, z, ya, pi));
        String name = target.getDisplayName();
        String or = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.Target")));
        String replace = or.replaceAll("%target%", name).replaceAll("%loc%", locName);
        sender.sendMessage(prefix + replace);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Spectate").equalsIgnoreCase("True")) {
            String original = (MessageManager.getMessageYml().getString("Spectate.Spectate"));
            String replaced = original.replaceAll("%loc%", locName);
            target.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    /**
     * Teleports the player to their specified home.
     *
     * @param homeName The name of the player's home.
     * @param player   The player being teleported.
     */
    public static void home(String homeName, Player player) {
        UUID Id = player.getUniqueId();
        World w = Bukkit.getServer().getWorld(FileManager.getHomeYml().getString("HOMES." + Id + "." + homeName + ".world"));
        double x = FileManager.getHomeYml().getDouble("HOMES." + Id + "." + homeName + ".x");
        double y = FileManager.getHomeYml().getDouble("HOMES." + Id + "." + homeName + ".y");
        double z = FileManager.getHomeYml().getDouble("HOMES." + Id + "." + homeName + ".z");
        int ya = FileManager.getHomeYml().getInt("HOMES." + Id + "." + homeName + ".yaw");
        int pi = FileManager.getHomeYml().getInt("HOMES." + Id + "." + homeName + ".pitch");
        player.teleport(new Location(w, x, y, z, ya, pi));
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Home").equalsIgnoreCase("True")) {
            String original = MessageManager.getMessageYml().getString("Home.Home");
            String replaced = original.replaceAll("%home%", homeName);
            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', replaced));
        }
    }

    /**
     * Teleports the player to a random location within the configured range.
     *
     * @param world  The world the player is currently in.
     * @param player The player who will be randomly teleported.
     */
    public static void tpr(String world, Player player) {
        String msg = MessageManager.getMessageYml().getString("Tpr.Teleporting");
        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        Location originalLocation = player.getLocation();
        Random random = new Random();
        Location teleportLocation;
        int maxDistance = Main.plugin.getConfig().getInt("TPR.Max");
        World w = Bukkit.getServer().getWorld(world);
        int x = random.nextInt(maxDistance) + 1;
        int y = 150;
        int z = random.nextInt(maxDistance) + 1;
        boolean isOnLand = false;
        teleportLocation = new Location(w, x, y, z);
        while (!isOnLand) {
            teleportLocation = new Location(w, x, y, z);
            if (teleportLocation.getBlock().getType() != Material.AIR) {
                isOnLand = true;
            } else {
                y--;
            }
        }
        player.teleport(new Location(w, teleportLocation.getX(), teleportLocation.getY() + 1.0D, teleportLocation.getZ()));
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Tpr").equalsIgnoreCase("True")) {
            int dis = (int) teleportLocation.distance(originalLocation);
            String dist = String.valueOf(dis);
            String original = (MessageManager.getMessageYml().getString("Tpr.Distance").replaceAll("%distance%", dist));
            String distance = (ChatColor.translateAlternateColorCodes('&', original));
            player.sendMessage(MessageManager.getPrefix() + distance);
        }
    }

    /**
     * Used to send players to the spawn location. Uses spawn on file.
     *
     * @param spawnName Name of spawn on file or spawn to be used.
     * @param player    Player that is being teleported to the spawn.
     */
    public static void spawn(String spawnName, Player player) {
        World w = Bukkit.getWorld(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".world"));
        Double x = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".x");
        Double y = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".y");
        Double z = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".z");
        int ya = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".yaw");
        int pi = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".pitch");
        Location spawnLoc = new Location(w, x, y, z, ya, pi);
        player.teleport(spawnLoc);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Spawn").equalsIgnoreCase("True")) {
            String msg = MessageManager.getMessageYml().getString("Spawn.Spawn");
            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    /**
     * Used to send players to the spawn location. Does not use spawn on file.
     *
     * @param world  World player is teleported to.
     * @param player Player that is being teleported to spawn.
     */
    public static void spawn(World world, Player player) {
        Location spawnLoc = world.getSpawnLocation();
        player.teleport(spawnLoc);
        if (Main.plugin.getConfig().getString("NOTIFICATIONS.Spawn").equalsIgnoreCase("True")) {
            String msg = MessageManager.getMessageYml().getString("Spawn.Spawn");
            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public static Location getRespawnLocation(String spawnName) {
        World w = Bukkit.getWorld(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".world"));
        Double x = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".x");
        Double y = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".y");
        Double z = FileManager.getSpawnYml().getDouble("Spawns." + spawnName + ".z");
        int ya = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".yaw");
        int pi = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".pitch");
        return new Location(w, x, y, z, ya, pi);
    }

    public static Location getRespawnLocation(World world) {
        return world.getSpawnLocation();
    }


    public static void teleportOnJoin(String cmd, Player player) {
        if (Main.plugin.getConfig().getString("Join.AllowBypass").equalsIgnoreCase("True")) {
            if (player.hasPermission("SpawnJoin.bypass.join")) {
                return;
            }
        }
        if (Main.plugin.getConfig().getString("Join.Worlds.All").equalsIgnoreCase("False")) {
            List<String> worlds = Main.plugin.getConfig().getStringList("Join.Worlds.List");
            if (!worlds.contains(player.getWorld().getName())) {
                return;
            }
        }
        if (cmd.equalsIgnoreCase("Hub")) {
            String hub = Main.plugin.getConfig().getString("Join.Location.Hub");
            if (LocationChecker.hubExists(hub)) {
                Teleporter.hub(hub, player);
            }
        } else if (cmd.equalsIgnoreCase("Lobby")) {
            String lobby = Main.plugin.getConfig().getString("Join.Location.Lobby");
            if (LocationChecker.lobbyExists(lobby)) {
                Teleporter.lobby(lobby, player);
            }
        } else if (cmd.equalsIgnoreCase("Warp")) {
            String warp = Main.plugin.getConfig().getString("Join.Location.Warp");
            if (LocationChecker.warpExists(warp)) {
                Teleporter.warp(warp, player);
            }
        } else if (cmd.equalsIgnoreCase("Spawn")) {
            String worldName = Main.plugin.getConfig().getString("Join.Location.Spawn");
            if (LocationChecker.spawnExists(worldName)) {
                if (LocationChecker.spawnOnFile(worldName)) {
                    Teleporter.spawn(worldName, player);
                }
            }
        }
    }
}