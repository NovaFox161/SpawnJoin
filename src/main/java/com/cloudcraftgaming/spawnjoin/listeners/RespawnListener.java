package com.cloudcraftgaming.spawnjoin.listeners;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.Teleporter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener
        implements Listener {
    public RespawnListener(Main instance) {
        plugin = instance;
    }

    Main plugin;

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (Main.plugin.getConfig().getString("Respawn.Use").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            String currentWorld = player.getWorld().getName();
            String spawnWorld = LocationChecker.determineRespawnWorld(currentWorld);
            if (LocationChecker.spawnOnFile(spawnWorld)) {
                event.setRespawnLocation(Teleporter.getRespawnLocation(spawnWorld));

                if (Main.plugin.getConfig().getString("NOTIFICATIONS.Respawn").equalsIgnoreCase("True")) {
                    String msg = MessageManager.getMessageYml().getString("Spawn.Respawn");
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            } else {
                event.setRespawnLocation(Teleporter.getRespawnLocation(Bukkit.getWorld(spawnWorld)));

                if (Main.plugin.getConfig().getString("NOTIFICATIONS.Respawn").equalsIgnoreCase("True")) {
                    String msg = MessageManager.getMessageYml().getString("Spawn.Respawn");
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                }
            }
        }
    }
}
