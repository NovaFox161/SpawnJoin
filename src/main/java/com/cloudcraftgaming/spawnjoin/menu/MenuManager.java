package com.cloudcraftgaming.spawnjoin.menu;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
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
        if (FileManager.getListYml().contains("Warps") && FileManager.getWarpYml().contains("WARPS")) {
            int warpCount = FileManager.getListYml().getStringList("Warps").size();
            String warpNameOr = Main.plugin.getConfig().getString("Inventory.Warp.Name");
            warpInv = Bukkit.createInventory(null, getInvSize(warpCount), ChatColor.translateAlternateColorCodes('&', warpNameOr));
            Integer slotNumber = 0;
            for (String warpName : FileManager.getListYml().getStringList("Warps")) {
                if (slotNumber < warpInv.getSize()) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Short damage = 0;
                    if (FileManager.getWarpYml().contains("WARPS." + warpName + ".item")) {
                        Integer itemId = FileManager.getWarpYml().getInt("WARPS." + warpName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (FileManager.getWarpYml().contains("WARPS." + warpName + ".cost")) {
                        cost = FileManager.getWarpYml().getInt("WARPS." + warpName + ".cost");
                    }
                    if (FileManager.getWarpYml().contains("WARPS." + warpName + ".itemProp")) {
                        damage = Short.valueOf(FileManager.getWarpYml().getString("WARPS." + warpName + ".itemProp"));
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    if (Main.plugin.getConfig().getString("Inventory.Warp.ShowCost").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.RED + "Warp Cost: " + cost);
                    }
                    createItemDisplay(itemDis, damage, warpInv, slotNumber, warpName, lore);
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
            int warpCount = FileManager.getListYml().getStringList("Warps").size();
            if (sizeNeedsToBeChanged(warpCount, warpInv.getSize())) {
                createWarpInv();
            }
            if (FileManager.getListYml().contains("Warps") && FileManager.getWarpYml().contains("WARPS")) {
                Integer slotNumber = 0;
                for (String warpName : FileManager.getListYml().getStringList("Warps")) {
                    if (slotNumber < warpInv.getSize()) {
                        Material itemDis = Material.GRASS;
                        Integer cost = 0;
                        Short damage = 0;
                        if (FileManager.getWarpYml().contains("WARPS." + warpName + ".item")) {
                            Integer itemId = FileManager.getWarpYml().getInt("WARPS." + warpName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (FileManager.getWarpYml().contains("WARPS." + warpName + ".cost")) {
                            cost = FileManager.getWarpYml().getInt("WARPS." + warpName + ".cost");
                        }
                        if (FileManager.getWarpYml().contains("WARPS." + warpName + ".itemProp")) {
                            damage = Short.valueOf(FileManager.getWarpYml().getString("WARPS." + warpName + ".itemProp"));
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        if (Main.plugin.getConfig().getString("Inventory.Warp.ShowCost").equalsIgnoreCase("True")) {
                            lore.add(ChatColor.RED + "Warp Cost: " + cost);
                        }
                        createItemDisplay(itemDis, damage, warpInv, slotNumber, warpName, lore);
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
        if (FileManager.getListYml().contains("Hubs") && FileManager.getHubYml().contains("HUBS")) {
            int hubCount = FileManager.getListYml().getStringList("Hubs").size();
            String nameOr = Main.plugin.getConfig().getString("Inventory.Hub.Name");
            hubInv = Bukkit.createInventory(null, getInvSize(hubCount), ChatColor.translateAlternateColorCodes('&', nameOr));
            Integer slotNumber = 0;
            for (String hubName : FileManager.getListYml().getStringList("Hubs")) {
                if (slotNumber < hubInv.getSize()) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Short damage = 0;
                    if (FileManager.getHubYml().contains("HUBS." + hubName + ".item")) {
                        Integer itemId =  FileManager.getHubYml().getInt("HUBS." + hubName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (FileManager.getHubYml().contains("HUBS." + hubName + ".cost")) {
                        cost = FileManager.getHubYml().getInt("HUBS." + hubName + ".cost");
                    }
                    if (FileManager.getHubYml().contains("HUBS." + hubName + ".itemProp")) {
                        damage = Short.valueOf(FileManager.getHubYml().getString("HUBS." + hubName + ".itemProp"));
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    if (Main.plugin.getConfig().getString("Inventory.Hub.ShowCost").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.RED + "Hub Cost: " + cost);
                    }
                    createItemDisplay(itemDis, damage, hubInv, slotNumber, hubName, lore);
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
            int hubCount = FileManager.getListYml().getStringList("Hubs").size();
            if (sizeNeedsToBeChanged(hubCount, hubInv.getSize())) {
                createHubInv();
            }
            if (FileManager.getListYml().contains("Hubs") && FileManager.getHubYml().contains("HUBS")) {
                Integer slotNumber = 0;
                for (String hubName : FileManager.getListYml().getStringList("Hubs")) {
                    if (slotNumber < hubInv.getSize()) {
                        Material itemDis = Material.GRASS;
                        Integer cost = 0;
                        Short damage = 0;
                        if (FileManager.getHubYml().contains("HUBS." + hubName + ".item")) {
                            Integer itemId = FileManager.getHubYml().getInt("HUBS." + hubName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (FileManager.getHubYml().contains("HUBS." + hubName + ".cost")) {
                            cost = FileManager.getHubYml().getInt("HUBS." + hubName + ".cost");
                        }
                        if (FileManager.getHubYml().contains("HUBS." + hubName + ".itemProp")) {
                            damage = Short.valueOf(FileManager.getHubYml().getString("HUBS." + hubName + ".itemProp"));
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        if (Main.plugin.getConfig().getString("Inventory.Hub.ShowCost").equalsIgnoreCase("True")) {
                            lore.add(ChatColor.RED + "Hub Cost: " + cost);
                        }
                        createItemDisplay(itemDis, damage, hubInv, slotNumber, hubName, lore);
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
        if (FileManager.getListYml().contains("Lobbies") && FileManager.getLobbyYml().contains("LOBBIES")) {
            int lobbyCount = FileManager.getListYml().getStringList("Lobbies").size();
            String nameOr = Main.plugin.getConfig().getString("Inventory.Lobby.Name");
            lobbyInv = Bukkit.createInventory(null, getInvSize(lobbyCount), ChatColor.translateAlternateColorCodes('&', nameOr));
            Integer slotNumber = 0;
            for (String lobbyName : FileManager.getListYml().getStringList("Lobbies")) {
                if (slotNumber < lobbyInv.getSize()) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Short damage = 0;
                    if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".item")) {
                        Integer itemId = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".cost")) {
                        cost = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".cost");
                    }
                    if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".itemProp")) {
                        damage = Short.valueOf(FileManager.getLobbyYml().getString("LOBBIES." + lobbyName + ".itemProp"));
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    if (Main.plugin.getConfig().getString("Inventory.Lobby.ShowCost").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.RED + "Lobby Cost: " + cost);
                    }
                    createItemDisplay(itemDis, damage, lobbyInv, slotNumber, lobbyName, lore);
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
            int lobbyCount = FileManager.getListYml().getStringList("Lobbies").size();
            if (sizeNeedsToBeChanged(lobbyCount, lobbyInv.getSize())) {
                createLobbyInv();
            }
            if (FileManager.getListYml().contains("Lobbies") && FileManager.getLobbyYml().contains("LOBBIES")) {
                Integer slotNumber = 0;
                for (String lobbyName : FileManager.getListYml().getStringList("Lobbies")) {
                    if (slotNumber < lobbyInv.getSize()) {
                        Material itemDis = Material.GRASS;
                        Integer cost = 0;
                        Short damage = 0;
                        if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".item")) {
                            Integer itemId = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".cost")) {
                            cost = FileManager.getLobbyYml().getInt("LOBBIES." + lobbyName + ".cost");
                        }
                        if (FileManager.getLobbyYml().contains("LOBBIES." + lobbyName + ".itemProp")) {
                            damage = Short.valueOf(FileManager.getLobbyYml().getString("LOBBIES." + lobbyName + ".itemProp"));
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        if (Main.plugin.getConfig().getString("Inventory.Lobby.ShowCost").equalsIgnoreCase("True")) {
                            lore.add(ChatColor.RED + "Lobby Cost: " + cost);
                        }
                        createItemDisplay(itemDis, damage, lobbyInv, slotNumber, lobbyName, lore);
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
        if (FileManager.getListYml().contains("Spectate") && FileManager.getSpectateYml().contains("SPECTATE")) {
            int locCount = FileManager.getListYml().getStringList("Spectate").size();
            String nameOr = Main.plugin.getConfig().getString("Inventory.Spectate.Name");
            spectateInv = Bukkit.createInventory(null, getInvSize(locCount), ChatColor.translateAlternateColorCodes('&', nameOr));
            Integer slotNumber = 0;
            for (String locName : FileManager.getListYml().getStringList("Spectate")) {
                if (slotNumber < spectateInv.getSize()) {
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Short damage = 0;
                    if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".item")) {
                        Integer itemId = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".cost")) {
                        cost = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".cost");
                    }
                    if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".itemProp")) {
                        damage = Short.valueOf(FileManager.getSpectateYml().getString("SPECTATE." + locName + ".itemProp"));
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    if (Main.plugin.getConfig().getString("Inventory.Spectate.ShowCost").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.RED + "Spectating Cost: " + cost);
                    }
                    createItemDisplay(itemDis, damage, spectateInv, slotNumber, locName, lore);
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
            int locCount = FileManager.getListYml().getStringList("Spectate").size();
            if (sizeNeedsToBeChanged(locCount, spectateInv.getSize())) {
                createSpectateInv();
            }
            if (FileManager.getListYml().contains("Spectate") && FileManager.getSpectateYml().contains("SPECTATE")) {
                Integer slotNumber = 0;
                for (String locName : FileManager.getListYml().getStringList("Spectate")) {
                    if (slotNumber < spectateInv.getSize()) {
                        Material itemDis = Material.GRASS;
                        Integer cost = 0;
                        Short damage = 0;
                        if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".item")) {
                            Integer itemId = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".cost")) {
                            cost = FileManager.getSpectateYml().getInt("SPECTATE." + locName + ".cost");
                        }
                        if (FileManager.getSpectateYml().contains("SPECTATE." + locName + ".itemProp")) {
                            damage = Short.valueOf(FileManager.getSpectateYml().getString("SPECTATE." + locName + ".itemProp"));
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        if (Main.plugin.getConfig().getString("Inventory.Spectate.ShowCost").equalsIgnoreCase("True")) {
                            lore.add(ChatColor.RED + "Spectating Cost: " + cost);
                        }
                        createItemDisplay(itemDis, damage, spectateInv, slotNumber, locName, lore);
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
        if (FileManager.getListYml().contains("Spawns") && FileManager.getSpawnYml().contains("Spawns")) {
            int spawnCount = FileManager.getListYml().getStringList("Spawns").size();
            String nameOr = Main.plugin.getConfig().getString("Inventory.Spawn.Name");
            spawnInv = Bukkit.createInventory(null, getInvSize(spawnCount), ChatColor.translateAlternateColorCodes('&', nameOr));
            Integer slotNumber = 0;
            for (String spawnName : FileManager.getListYml().getStringList("Spawns")) {
                if (slotNumber < spawnInv.getSize()) {
                    World spawnWorld = Bukkit.getWorld(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".world"));
                    Material itemDis = Material.GRASS;
                    Integer cost = 0;
                    Short damage = 0;
                    Integer playerCount = 0;
                    if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".item")) {
                        Integer itemId = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".item");
                        itemDis = Material.getMaterial(itemId);
                    }
                    if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".cost")) {
                        cost = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".cost");
                    }
                    if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".itemProp")) {
                        damage = Short.valueOf(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".itemProp"));
                    }
                    if (!(spawnWorld == null)) {
                        playerCount = spawnWorld.getPlayers().size();
                    }
                    ArrayList<String> lore = new ArrayList<>();
                    if (Main.plugin.getConfig().getString("Inventory.Spawn.ShowCost").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.RED + "Spawn Cost: " + cost);
                    }
                    if (Main.plugin.getConfig().getString("Inventory.Spawn.ShowPlayerCount").equalsIgnoreCase("True")) {
                        lore.add(ChatColor.GREEN + "Players: " + playerCount);
                    }
                    createItemDisplay(itemDis, damage, spawnInv, slotNumber, spawnName, lore);
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
        if (Main.plugin.getConfig().getString("Inventory.Spawn.Use").equalsIgnoreCase("True")) {
            if (spawnInv != null) {
                spawnInv.clear();
                int spawnCount = FileManager.getListYml().getStringList("Spawns").size();
                if (sizeNeedsToBeChanged(spawnCount, spawnInv.getSize())) {
                    createSpawnInv();
                }
                if (FileManager.getListYml().contains("Spawns") && FileManager.getSpawnYml().contains("Spawns")) {
                    Integer slotNumber = 0;
                    for (String spawnName : FileManager.getListYml().getStringList("Spawns")) {
                        if (slotNumber < spawnInv.getSize()) {
                            World spawnWorld = Bukkit.getWorld(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".world"));
                            Integer playerCount = spawnWorld.getPlayers().size();
                            Material itemDis = Material.GRASS;
                            Integer cost = 0;
                            Short damage = 0;
                            if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".item")) {
                                Integer itemId = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".item");
                                itemDis = Material.getMaterial(itemId);
                            }
                            if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".cost")) {
                                cost = FileManager.getSpawnYml().getInt("Spawns." + spawnName + ".cost");
                            }
                            if (FileManager.getSpawnYml().contains("Spawns." + spawnName + ".itemProp")) {
                                damage = Short.valueOf(FileManager.getSpawnYml().getString("Spawns." + spawnName + ".itemProp"));
                            }
                            ArrayList<String> lore = new ArrayList<>();
                            if (Main.plugin.getConfig().getString("Inventory.Spawn.ShowCost").equalsIgnoreCase("True")) {
                                lore.add(ChatColor.RED + "Spawn Cost: " + cost);
                            }
                            if (Main.plugin.getConfig().getString("Inventory.Spawn.ShowPlayerCount").equalsIgnoreCase("True")) {
                                lore.add(ChatColor.GREEN + "Players: " + playerCount);
                            }
                            createItemDisplay(itemDis, damage, spawnInv, slotNumber, spawnName, lore);
                            slotNumber = slotNumber + 1;
                        } else {
                            if (Main.plugin.getConfig().getString("Debug").equalsIgnoreCase("True")) {
                                Main.plugin.getLogger().info("<Debug> Out of room for GUI Menu! Pagination not currently supported!");
                            }
                        }
                    }
                }
            } else {
                createSpawnInv();
            }
        }
    }

    public static Boolean hasHomeInventoryMenu(Player player) {
        return homeInventories.containsKey(player.getUniqueId());
    }

    private static void createPlayerHomeInv(Player player) {
        if (Main.plugin.getConfig().getString("Inventory.Home.Use").equalsIgnoreCase("True")) {
            UUID uuid = player.getUniqueId();
            if (FileManager.getListYml().contains("Homes." + uuid) && FileManager.getHomeYml().contains("HOMES." + uuid)) {
                int homeCount = FileManager.getListYml().getStringList("Homes." + uuid).size();
                String nameOr = Main.plugin.getConfig().getString("Inventory.Home.Name");
                Inventory homeInv = Bukkit.createInventory(null, getInvSize(homeCount), ChatColor.translateAlternateColorCodes('&', nameOr));

                Integer slotNumber = 0;
                for (String homeName : FileManager.getListYml().getStringList("Homes." + uuid)) {
                    if (slotNumber < 52) {
                        Material itemDis = Material.BED;
                        Short damage = 0;
                        if (FileManager.getHomeYml().contains("HOMES." + uuid + "." + homeName + ".item")) {
                            Integer itemId = FileManager.getHomeYml().getInt("HOMES." + uuid + "." + homeName + ".item");
                            itemDis = Material.getMaterial(itemId);
                        }
                        if (FileManager.getHomeYml().contains("HOMES." + uuid + "." + homeName + ".itemProp")) {
                            damage = Short.valueOf(FileManager.getHomeYml().getString("HOMES." + uuid + "." + homeName + ".itemProp"));
                        }
                        ArrayList<String> lore = new ArrayList<>();
                        if (Main.plugin.getConfig().getString("Inventory.Home.ShowWorld").equalsIgnoreCase("True")) {
                            lore.add(ChatColor.RED + "World: " + FileManager.getHomeYml().getString("HOMES." + uuid + "." + homeName + ".world"));
                        }
                        createItemDisplay(itemDis, damage, homeInv, slotNumber, homeName, lore);
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
        if (Main.plugin.getConfig().getString("Inventory.Home.Use").equalsIgnoreCase("True")) {
            if (hasHomeInventoryMenu(player)) {
                UUID uuid = player.getUniqueId();
                Inventory homeInv = homeInventories.get(uuid);
                homeInv.clear();
                int homeCount = FileManager.getListYml().getStringList("Homes." + uuid).size();
                if (sizeNeedsToBeChanged(homeCount, homeInv.getSize())) {
                    createPlayerHomeInv(player);
                }
                if (FileManager.getListYml().contains("Homes." + uuid) && FileManager.getHomeYml().contains("HOMES." + uuid)) {
                    Integer slotNumber = 0;
                    for (String homeName : FileManager.getListYml().getStringList("Homes." + uuid)) {
                        if (slotNumber < 52) {
                            Material itemDis = Material.BED;
                            Short damage = 0;
                            if (FileManager.getHomeYml().contains("HOMES." + uuid + "." + homeName + ".item")) {
                                Integer itemId = FileManager.getHomeYml().getInt("HOMES." + uuid + "." + homeName + ".item");
                                itemDis = Material.getMaterial(itemId);
                            }
                            if (FileManager.getHomeYml().contains("HOMES." + uuid + "." + homeName + ".itemProp")) {
                                damage = Short.valueOf(FileManager.getHomeYml().getString("HOMES." + uuid + "." + homeName + ".itemProp"));
                            }
                            ArrayList<String> lore = new ArrayList<>();
                            if (Main.plugin.getConfig().getString("Inventory.Home.ShowWorld").equalsIgnoreCase("True")) {
                                lore.add(ChatColor.RED + "World: " + FileManager.getHomeYml().getString("HOMES." + uuid + "." + homeName + ".world"));
                            }
                            createItemDisplay(itemDis, damage, homeInv, slotNumber, homeName, lore);
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

    private static void createItemDisplay(Material material, Short damage, Inventory inv, int slot, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material, 1, damage);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(slot, item);
    }

    private static int getInvSize(int count) {
        if (count > 0 && count < 10) {
            return 9;
        } else if (count > 9 && count < 19) {
            return 18;
        } else if (count > 18 && count < 28) {
            return 27;
        } else if (count > 27 && count < 37) {
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