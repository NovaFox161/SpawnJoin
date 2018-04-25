package com.cloudcraftgaming.spawnjoin.listeners;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {
    public JoinListener(Main instance) {
        plugin = instance;
    }

    private Main plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void spawnJoinTeleport(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        if (plugin.getConfig().getString("Join.Enabled").equalsIgnoreCase("True")) {
            String cmd = Main.plugin.getConfig().getString("Join.Command");
            Teleporter.teleportOnJoin(cmd, joiner);
        }
    }

    //TODO: Use my new updater

    @EventHandler(priority = EventPriority.MONITOR)
    public void homeDataSet(PlayerJoinEvent event) {
        Player joiner = event.getPlayer();
        UUID uId = joiner.getUniqueId();
        YamlConfiguration homeData = FileManager.getHomeDataYml();

        if (!homeData.contains("Players." + uId)) {
            homeData.set("Players." + uId + ".HomeNumber", 0);
            Integer allowedHomes = plugin.getConfig().getInt("Commands.Home.DefaultLimit");
            homeData.set("Players." + uId + ".AllowedHomes", allowedHomes);
            FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
            if (plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                plugin.getLogger().info("<Debug> Generated player Home data for: " + joiner.getName());
            }
            if (plugin.getConfig().getString("Commands.Home.UseRankLimits").equalsIgnoreCase("True")) {
                Boolean updatedHomeLimit = false;
                for (String homeGroup : FileManager.getHomeSettingsYml().getStringList("Groups")) {
                    if (joiner.hasPermission("SpawnJoin.group." + homeGroup) && !(updatedHomeLimit)) {
                        Integer groupLimit = FileManager.getHomeSettingsYml().getInt("GroupSettings." + homeGroup + ".limit");
                        if (homeData.getInt("Players." + uId + ".AllowedHomes") != groupLimit) {
                            homeData.set("Players." + uId + ".AllowedHomes", groupLimit);
                            FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
                            updatedHomeLimit = true;
                            if (plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                                plugin.getLogger().info("<Debug> Updated Home limit for " + joiner.getName());
                            }
                        }
                    }
                }
            }
        } else {
            if (plugin.getConfig().getString("Commands.Home.UseRankLimits").equalsIgnoreCase("True")) {
                Boolean updatedHomeLimit = false;
                for (String homeGroup : FileManager.getHomeSettingsYml().getStringList("Groups")) {
                    if (joiner.hasPermission("SpawnJoin.group." + homeGroup) && !(updatedHomeLimit)) {
                        Integer groupLimit = FileManager.getHomeSettingsYml().getInt("GroupSettings." + homeGroup + ".limit");
                        if (homeData.getInt("Players." + uId + ".AllowedHomes") != groupLimit) {
                            homeData.set("Players." + uId + ".AllowedHomes", groupLimit);
                            FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
                            updatedHomeLimit = true;
                            if (plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                                plugin.getLogger().info("<Debug> Updated Home limit for " + joiner.getName());
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void devAnnounce(PlayerJoinEvent event) {
        if (plugin.getConfig().getString("Announce Dev Join").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            if (player.getUniqueId().toString().equals("2fff73e3-ddbb-48d5-90b9-e6d5342a6b3e")
                    || player.getUniqueId().toString().equals("7ee45311-338a-45ee-aeeb-7e7a4d4a083e")) {
                Bukkit.broadcastMessage(MessageManager.getPrefix() + ChatColor.AQUA + "The developer of SpawnJoin has joined the server!");
            }
        }
    }
}