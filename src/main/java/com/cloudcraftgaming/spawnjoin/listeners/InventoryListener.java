package com.cloudcraftgaming.spawnjoin.listeners;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.LocationChecker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Nova Fox on 2/6/2016.
 */
public class InventoryListener implements Listener {
    Main plugin;
    public InventoryListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack clicked = event.getCurrentItem();
        if (!(clicked == null)) {
            if (MenuManager.warpInv != null && inv.getName().equals(MenuManager.warpInv.getName())) {
                event.setCancelled(true);
                if (!(clicked.getType().equals(Material.AIR))) {
                    ItemMeta meta = clicked.getItemMeta();
                    String warpName = meta.getDisplayName();
                    if (LocationChecker.warpExists(warpName)) {
                        player.closeInventory();
                        if (player.hasPermission("SpawnJoin.use.warp." + warpName) || player.hasPermission("SpawnJoin.use.warp.*")) {
                            DelayChecker.warpDelayCheck(warpName, player);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                        }
                    }
                }
            } else if (MenuManager.hubInv != null && inv.getName().equals(MenuManager.hubInv.getName())) {
                event.setCancelled(true);
                if (!(clicked.getType().equals(Material.AIR))) {
                    ItemMeta meta = clicked.getItemMeta();
                    String hubName = meta.getDisplayName();
                    if (LocationChecker.hubExists(hubName)) {
                        player.closeInventory();
                        if (player.hasPermission("SpawnJoin.use.hub." + hubName) || player.hasPermission("SpawnJoin.use.hub.*")) {
                            DelayChecker.hubDelayCheck(hubName, player);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                        }
                    }
                }
            } else if (MenuManager.lobbyInv != null && inv.getName().equals(MenuManager.lobbyInv.getName())) {
                event.setCancelled(true);
                if (!(clicked.getType().equals(Material.AIR))) {
                    ItemMeta meta = clicked.getItemMeta();
                    String lobbyName = meta.getDisplayName();
                    if (LocationChecker.lobbyExists(lobbyName)) {
                        player.closeInventory();
                        if (player.hasPermission("SpawnJoin.use.lobby." + lobbyName) || player.hasPermission("SpawnJoin.use.lobby.*")) {
                            DelayChecker.lobbyDelayCheck(lobbyName, player);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                        }
                    }
                }
            } else if (MenuManager.spectateInv != null && inv.getName().equalsIgnoreCase(MenuManager.spectateInv.getName())) {
                event.setCancelled(true);
                if (!(clicked.getType().equals(Material.AIR))) {
                    ItemMeta meta = clicked.getItemMeta();
                    String locName = meta.getDisplayName();
                    if (LocationChecker.spectateExists(locName)) {
                        player.closeInventory();
                        if (player.hasPermission("SpawnJoin.use.spectate." + locName) || player.hasPermission("SpawnJoin.use.spectate.*")) {
                            DelayChecker.spectateDelayCheck(locName, player);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                        }
                    }
                }
            } else if (MenuManager.spawnInv != null && inv.getName().equalsIgnoreCase(MenuManager.spawnInv.getName())) {
                event.setCancelled(true);
                if (!(clicked.getType().equals(Material.AIR))) {
                    ItemMeta meta = clicked.getItemMeta();
                    String spawnName = meta.getDisplayName();
                    if (LocationChecker.spawnExists(spawnName)) {
                        player.closeInventory();
                        if (player.hasPermission("SpawnJoin.use.spawn")) {
                            DelayChecker.spawnDelayCheck(spawnName, player);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                        }
                    }
                }
            } else if (MenuManager.hasHomeInventoryMenu(player)) {
                if (inv.getName().equalsIgnoreCase(MenuManager.getHomeInventory(player).getName())) {
                    event.setCancelled(true);
                    if (!(clicked.getType().equals(Material.AIR))) {
                        ItemMeta meta = clicked.getItemMeta();
                        String homeName = meta.getDisplayName();
                        if (LocationChecker.homeExists(homeName, player)) {
                            player.closeInventory();
                            if (player.hasPermission("SpawnJoin.use.home")) {
                                DelayChecker.homeDelayCheck(homeName, player);
                            } else {
                                player.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                            }
                        }
                    }
                }
            }
        }
    }
}