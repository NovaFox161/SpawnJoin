package com.cloudcraftgaming.spawnjoin.menu;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Nova Fox on 2/6/2016.
 */
@SuppressWarnings("deprecation")
public class MenuManager {
    private static HashMap<UUID, Inventory> homeInventories = new HashMap<>();
    public static Inventory warpInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Warp Menu");
    public static Inventory hubInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Hub Menu");
    public static Inventory lobbyInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Lobby Menu");
    public static Inventory spectateInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Spectating Menu");
    public static Inventory spawnInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "Spawn Menu");

    public static void createWarpInv() {
        if (Main.plugin.lists.contains("Warps") && Main.plugin.warps.contains("WARPS")) {
            Integer slotNumber = 0;
            for (String warpName : Main.plugin.lists.getStringList("Warps")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.warps.contains("WARPS." + warpName + ".item")) {
                        Integer itemId = Main.plugin.warps.getInt("WARPS." + warpName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.warps.contains("WARPS." + warpName + ".cost")) {
                        cost = Main.plugin.warps.getInt("WARPS." + warpName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Warp Cost: " + cost);
                    createItemDisplay(itemDis, warpInv, slotNumber, warpName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void updateWarpInv() {
        warpInv.clear();
        if (Main.plugin.lists.contains("Warps") && Main.plugin.warps.contains("WARPS")) {
            Integer slotNumber = 0;
            for (String warpName : Main.plugin.lists.getStringList("Warps")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.warps.contains("WARPS." + warpName + ".item")) {
                        Integer itemId = Main.plugin.warps.getInt("WARPS." + warpName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.warps.contains("WARPS." + warpName + ".cost")) {
                        cost = Main.plugin.warps.getInt("WARPS." + warpName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Warp Cost: " + cost);
                    createItemDisplay(itemDis, warpInv, slotNumber, warpName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void createHubInv() {
        if (Main.plugin.lists.contains("Hubs") && Main.plugin.hubs.contains("HUBS")) {
            Integer slotNumber = 0;
            for (String hubName : Main.plugin.lists.getStringList("Hubs")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.hubs.contains("HUBS." + hubName + ".item")) {
                        Integer itemId = Main.plugin.hubs.getInt("HUBS." + hubName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.hubs.contains("HUBS." + hubName + ".cost")) {
                        cost = Main.plugin.hubs.getInt("HUBS." + hubName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Hub Cost: " + cost);
                    createItemDisplay(itemDis, hubInv, slotNumber, hubName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void updateHubInv() {
        hubInv.clear();
        if (Main.plugin.lists.contains("Hubs") && Main.plugin.hubs.contains("HUBS")) {
            Integer slotNumber = 0;
            for (String hubName : Main.plugin.lists.getStringList("Hubs")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.hubs.contains("HUBS." + hubName + ".item")) {
                        Integer itemId = Main.plugin.hubs.getInt("HUBS." + hubName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.hubs.contains("HUBS." + hubName + ".cost")) {
                        cost = Main.plugin.hubs.getInt("HUBS." + hubName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Hub Cost: " + cost);
                    createItemDisplay(itemDis, hubInv, slotNumber, hubName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void createLobbyInv() {
        if (Main.plugin.lists.contains("Lobbies") && Main.plugin.lobs.contains("LOBBIES")) {
            Integer slotNumber = 0;
            for (String lobbyName : Main.plugin.lists.getStringList("Lobbies")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.lobs.contains("LOBBIES." + lobbyName + ".item")) {
                        Integer itemId = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.lobs.contains("LOBBIES." + lobbyName + ".cost")) {
                        cost = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Lobby Cost: " + cost);
                    createItemDisplay(itemDis, lobbyInv, slotNumber, lobbyName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void updateLobbyInv() {
        lobbyInv.clear();
        if (Main.plugin.lists.contains("Lobbies") && Main.plugin.lobs.contains("LOBBIES")) {
            Integer slotNumber = 0;
            for (String lobbyName : Main.plugin.lists.getStringList("Lobbies")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.lobs.contains("LOBBIES." + lobbyName + ".item")) {
                        Integer itemId = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.lobs.contains("LOBBIES." + lobbyName + ".cost")) {
                        cost = Main.plugin.lobs.getInt("LOBBIES." + lobbyName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Lobby Cost: " + cost);
                    createItemDisplay(itemDis, lobbyInv, slotNumber, lobbyName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void createSpectateInv() {
        if (Main.plugin.lists.contains("Spectate") && Main.plugin.spec.contains("SPECTATE")) {
            Integer slotNumber = 0;
            for (String locName : Main.plugin.lists.getStringList("Spectate")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.spec.contains("SPECTATE." + locName + ".item")) {
                        Integer itemId = Main.plugin.spec.getInt("SPECTATE." + locName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.spec.contains("SPECTATE." + locName + ".cost")) {
                        cost = Main.plugin.spec.getInt("SPECTATE." + locName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Spectating Cost: " + cost);
                    createItemDisplay(itemDis, spectateInv, slotNumber, locName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void updateSpectateInv() {
        lobbyInv.clear();
        if (Main.plugin.lists.contains("Spectate") && Main.plugin.spec.contains("SPECTATE")) {
            Integer slotNumber = 0;
            for (String locName : Main.plugin.lists.getStringList("Spectate")) {
                if (slotNumber < 52) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    if (Main.plugin.spec.contains("SPECTATE." + locName + ".item")) {
                        Integer itemId = Main.plugin.spec.getInt("SPECTATE." + locName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.spec.contains("SPECTATE." + locName + ".cost")) {
                        cost = Main.plugin.spec.getInt("SPECTATE." + locName + ".cost");
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Spectating Cost: " + cost);
                    createItemDisplay(itemDis, spectateInv, slotNumber, locName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void createSpawnInv() {
        if (Main.plugin.lists.contains("Spawns") && Main.plugin.spawns.contains("Spawns")) {
            Integer slotNumber = 0;
            for (String spawnName : Main.plugin.lists.getStringList("Spawns")) {
                if (slotNumber < 52) {
                    World spawnWorld = Bukkit.getWorld(Main.plugin.spawns.getString("Spawns." + spawnName + ".world"));
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Integer playerCount = 0;
                    if (Main.plugin.spawns.contains("Spawns." + spawnName + ".item")) {
                        Integer itemId = Main.plugin.spawns.getInt("Spawns." + spawnName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (Main.plugin.spawns.contains("Spawns." + spawnName + ".cost")) {
                        cost = Main.plugin.spawns.getInt("Spawns." + spawnName + ".cost");
                    }
                    if (!(spawnWorld == null)) {
                        playerCount = spawnWorld.getPlayers().size();
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.RED + "Spawn Cost: " + cost);
                    lore.add(ChatColor.GREEN + "Players: " + playerCount);
                    createItemDisplay(itemDis, spawnInv, slotNumber, spawnName, lore);
                    slotNumber = slotNumber + 1;
                } else {
                    if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                        Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                    }
                }
            }
        }
    }
    public static void updateSpawnInv() {
        if (Main.plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
            spawnInv.clear();
            if (Main.plugin.lists.contains("Spawns") && Main.plugin.spawns.contains("Spawns")) {
                Integer slotNumber = 0;
                for (String spawnName : Main.plugin.lists.getStringList("Spawns")) {
                    if (slotNumber < 52) {
                        World spawnWorld = Bukkit.getWorld(Main.plugin.spawns.getString("Spawns." + spawnName + ".world"));
                        Integer playerCount = spawnWorld.getPlayers().size();
                        Material itemDis = Material.GRASS;
                        Integer cost = 0;
                        if (Main.plugin.spawns.contains("Spawns." + spawnName + ".item")) {
                            Integer itemId = Main.plugin.spawns.getInt("Spawns." + spawnName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (Main.plugin.spawns.contains("Spawns." + spawnName + ".cost")) {
                            cost = Main.plugin.spawns.getInt("Spawns." + spawnName + ".cost");
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(ChatColor.RED + "Spawn Cost: " + cost);
                        lore.add(ChatColor.GREEN + "Players: " + playerCount);
                        createItemDisplay(itemDis, spawnInv, slotNumber, spawnName, lore);
                        slotNumber = slotNumber + 1;
                    } else {
                        if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                            Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                        }
                    }
                }
            }
        }
    }
    public static Boolean hasHomeInventoryMenu(Player player) {
        return homeInventories.containsKey(player.getUniqueId());
    }
    private static void createPlayerHomeInv(Player player) {
        if (Main.plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
            UUID uuid = player.getUniqueId();
            Inventory homeInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + player.getName() + "'s homes");
            if (Main.plugin.lists.contains("Homes." + uuid) && Main.plugin.homes.contains("HOMES." + uuid)) {
                Integer slotNumber = 0;
                for (String homeName : Main.plugin.lists.getStringList("Homes." + uuid)) {
                    if (slotNumber < 52) {
                        Material itemDis = Material.BED;
                        if (Main.plugin.homes.contains("HOMES." + uuid + "." + homeName + ".item")) {
                            Integer itemId = Main.plugin.homes.getInt("HOMES." + uuid + "." + homeName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add(ChatColor.RED + "World: " + Main.plugin.homes.getString("HOMES." + uuid + "." + homeName + ".world"));
                        createItemDisplay(itemDis, homeInv, slotNumber, homeName, lore);
                        slotNumber = slotNumber + 1;
                    }
                }
                homeInventories.put(uuid, homeInv);
            }
        }
    }
    public static void removePlayerHomeInv(Player player) {
        if (hasHomeInventoryMenu(player)) {
            UUID uuid = player.getUniqueId();
            homeInventories.remove(uuid);
        }
    }
    public static void updatePlayerHomeInv(Player player) {
        if (Main.plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
            if (hasHomeInventoryMenu(player)) {
                UUID uuid = player.getUniqueId();
                Inventory homeInv = homeInventories.get(uuid);
                homeInv.clear();
                if (Main.plugin.lists.contains("Homes." + uuid) && Main.plugin.homes.contains("HOMES." + uuid)) {
                    Integer slotNumber = 0;
                    for (String homeName : Main.plugin.lists.getStringList("Homes." + uuid)) {
                        if (slotNumber < 52) {
                            Material itemDis = Material.BED;
                            if (Main.plugin.homes.contains("HOMES." + uuid + "." + homeName + ".item")) {
                                Integer itemId = Main.plugin.homes.getInt("HOMES." + uuid + "." + homeName + ".item");
                                itemDis = Material.getMaterial(itemId);
                            }
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(ChatColor.RED + "World: " + Main.plugin.homes.getString("HOMES." + uuid + "." + homeName + ".world"));
                            createItemDisplay(itemDis, homeInv, slotNumber, homeName, lore);
                            slotNumber = slotNumber + 1;
                        }
                    }
                    homeInventories.remove(uuid);
                    homeInventories.put(uuid, homeInv);
                }
            } else {
                createPlayerHomeInv(player);
            }
        }
    }
    public static Inventory getHomeInventory(Player player) {
        UUID uuid = player.getUniqueId();
        return homeInventories.get(uuid);
    }

    private static void createItemDisplay(Material material, Inventory inv, int slot, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(slot, item);
    }
}