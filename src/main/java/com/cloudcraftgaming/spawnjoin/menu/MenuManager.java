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
    public static Inventory warpInv = null;
    public static Inventory hubInv = null;
    public static Inventory lobbyInv = null;
    public static Inventory spectateInv = null;
    public static Inventory spawnInv = null;

    public static void createWarpInv() {
        if (Main.plugin.lists.contains("Warps") && Main.plugin.warps.contains("WARPS")) {
            int warpCount = Main.plugin.lists.getStringList("Warps").size();
            warpInv = Bukkit.createInventory(null, getInvSize(warpCount), ChatColor.LIGHT_PURPLE + "Warp Menu");
            Integer slotNumber = 0;
            for (String warpName : Main.plugin.lists.getStringList("Warps")) {
                if (slotNumber < warpInv.getSize()) {
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
        if (warpInv != null) {
            warpInv.clear();
            int warpCount = Main.plugin.lists.getStringList("Warps").size();
            if (sizeNeedsToBeChanged(warpCount, warpInv.getSize())) {
                createWarpInv();
            }
            if (Main.plugin.lists.contains("Warps") && Main.plugin.warps.contains("WARPS")) {
                Integer slotNumber = 0;
                for (String warpName : Main.plugin.lists.getStringList("Warps")) {
                    if (slotNumber < warpInv.getSize()) {
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
        } else {
            createWarpInv();
        }
    }

    public static void createHubInv() {
        if (Main.plugin.lists.contains("Hubs") && Main.plugin.hubs.contains("HUBS")) {
            int hubCount = Main.plugin.lists.getStringList("Hubs").size();
            hubInv = Bukkit.createInventory(null, getInvSize(hubCount), ChatColor.LIGHT_PURPLE + "Hub Menu");
            Integer slotNumber = 0;
            for (String hubName : Main.plugin.lists.getStringList("Hubs")) {
                if (slotNumber < hubInv.getSize()) {
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
        if (hubInv != null) {
            hubInv.clear();
            int hubCount = Main.plugin.lists.getStringList("Hubs").size();
            if (sizeNeedsToBeChanged(hubCount, hubInv.getSize())) {
                createHubInv();
            }
            if (Main.plugin.lists.contains("Hubs") && Main.plugin.hubs.contains("HUBS")) {
                Integer slotNumber = 0;
                for (String hubName : Main.plugin.lists.getStringList("Hubs")) {
                    if (slotNumber < hubInv.getSize()) {
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
        } else {
            createHubInv();
        }
    }

    public static void createLobbyInv() {
        if (Main.plugin.lists.contains("Lobbies") && Main.plugin.lobs.contains("LOBBIES")) {
            int lobbyCount = Main.plugin.lists.getStringList("Lobbies").size();
            lobbyInv = Bukkit.createInventory(null, getInvSize(lobbyCount), ChatColor.LIGHT_PURPLE + "Lobby Menu");
            Integer slotNumber = 0;
            for (String lobbyName : Main.plugin.lists.getStringList("Lobbies")) {
                if (slotNumber < lobbyInv.getSize()) {
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
        if (lobbyInv != null) {
            lobbyInv.clear();
            int lobbyCount = Main.plugin.lists.getStringList("Lobbies").size();
            if (sizeNeedsToBeChanged(lobbyCount, lobbyInv.getSize())) {
                createLobbyInv();
            }
            if (Main.plugin.lists.contains("Lobbies") && Main.plugin.lobs.contains("LOBBIES")) {
                Integer slotNumber = 0;
                for (String lobbyName : Main.plugin.lists.getStringList("Lobbies")) {
                    if (slotNumber < lobbyInv.getSize()) {
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
        } else {
            createLobbyInv();
        }
    }

    public static void createSpectateInv() {
        if (Main.plugin.lists.contains("Spectate") && Main.plugin.spec.contains("SPECTATE")) {
            int locCount = Main.plugin.lists.getStringList("Spectate").size();
            spectateInv = Bukkit.createInventory(null, getInvSize(locCount), ChatColor.LIGHT_PURPLE + "Spectating Menu");
            Integer slotNumber = 0;
            for (String locName : Main.plugin.lists.getStringList("Spectate")) {
                if (slotNumber < spectateInv.getSize()) {
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
        if (spectateInv != null) {
            spectateInv.clear();
            int locCount = Main.plugin.lists.getStringList("Spectate").size();
            if (sizeNeedsToBeChanged(locCount, spectateInv.getSize())) {
                createSpectateInv();
            }
            if (Main.plugin.lists.contains("Spectate") && Main.plugin.spec.contains("SPECTATE")) {
                Integer slotNumber = 0;
                for (String locName : Main.plugin.lists.getStringList("Spectate")) {
                    if (slotNumber < spectateInv.getSize()) {
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
        } else {
            createSpectateInv();
        }
    }

    public static void createSpawnInv() {
        if (Main.plugin.lists.contains("Spawns") && Main.plugin.spawns.contains("Spawns")) {
            int spawnCount = Main.plugin.lists.getStringList("Spawns").size();
            spawnInv = Bukkit.createInventory(null, getInvSize(spawnCount), ChatColor.LIGHT_PURPLE + "Spawn Menu");
            Integer slotNumber = 0;
            for (String spawnName : Main.plugin.lists.getStringList("Spawns")) {
                if (slotNumber < spawnInv.getSize()) {
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
            int spawnCount = Main.plugin.lists.getStringList("Spawns").size();
            if (sizeNeedsToBeChanged(spawnCount, spawnInv.getSize())) {
                createSpawnInv();
            }
            if (Main.plugin.lists.contains("Spawns") && Main.plugin.spawns.contains("Spawns")) {
                Integer slotNumber = 0;
                for (String spawnName : Main.plugin.lists.getStringList("Spawns")) {
                    if (slotNumber < spawnInv.getSize()) {
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
            if (Main.plugin.lists.contains("Homes." + uuid) && Main.plugin.homes.contains("HOMES." + uuid)) {
                int homeCount = Main.plugin.lists.getStringList("Homes." + uuid).size();
                Inventory homeInv = Bukkit.createInventory(null, getInvSize(homeCount), ChatColor.LIGHT_PURPLE + "Warp Menu");

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
                int homeCount = Main.plugin.lists.getStringList("Homes." + uuid).size();
                if (sizeNeedsToBeChanged(homeCount, homeInv.getSize())) {
                    createPlayerHomeInv(player);
                }
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

    private static int getInvSize(int count) {
        if (count > 0 &&count < 10) {
            return 9;
        } else if (count > 9 && count < 19) {
            return 18;
        } else if (count > 18 && count < 28) {
            return 27;
        } else  if (count > 27 && count < 37) {
            return 36;
        } else if (count > 36 && count < 46) {
            return 45;
        } else if (count > 45 && count < 55) {
            return 54;
        } else {
            return 54;
        }
    }
    private static Boolean sizeNeedsToBeChanged(int count, int currentSize) {
        return getInvSize(count) != currentSize;
    }
}