package com.cloudcraftgaming.spawnjoin.listeners;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Nova Fox on 2/13/2016.
 */
public class QuitListener implements Listener {
    Main plugin;
    public QuitListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        if (MenuManager.hasHomeInventoryMenu(event.getPlayer())) {
            MenuManager.removePlayerHomeInv(event.getPlayer());
        }
    }
}
