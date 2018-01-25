package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {
    public static String conVersion = "5.2";
    static String msgVersion = "2.0";

    public static void configCreator() {
        File file = new File(Main.plugin.getDataFolder() + File.separator + "config.yml");
        if (!(file.exists())) {
            Main.plugin.getLogger().info("Generating config.yml in folder /plugins/SpawnJoin/");

            Main.plugin.getConfig().addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            Main.plugin.getConfig().addDefault("Config Version", conVersion);
            Main.plugin.getConfig().addDefault("Check for Updates", true);
            Main.plugin.getConfig().addDefault("Download Updates", false);
            Main.plugin.getConfig().addDefault("Announce Dev Join", true);
            Main.plugin.getConfig().addDefault("Debug", false);
            Main.plugin.getConfig().addDefault("LogCommandsToFile", false);
            Main.plugin.getConfig().addDefault("Language", "En");
            Main.plugin.getConfig().addDefault("Use Economy", true);

            Main.plugin.getConfig().addDefault("Commands.Spawn.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Spawn.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Hub.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Hub.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Lobby.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Lobby.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Warp.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Warp.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Tpr.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Tpr.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Tpr.Cooldown", 3600);
            Main.plugin.getConfig().addDefault("Commands.Spectate.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Spectate.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Home.Enabled", true);
            Main.plugin.getConfig().addDefault("Commands.Home.Delay", 1);
            Main.plugin.getConfig().addDefault("Commands.Home.DefaultLimit", 1);
            Main.plugin.getConfig().addDefault("Commands.Home.UseRankLimits", false);
            Main.plugin.getConfig().addDefault("Commands.Home.GlobalLimit.Enabled", false);
            Main.plugin.getConfig().addDefault("Commands.Home.GlobalLimit.Limit", 3);
            Main.plugin.getConfig().addDefault("Commands.Sign.Enabled", true);

            Main.plugin.getConfig().addDefault("Inventory.Warp.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Warp.Name", "&6Warp Selector");
            Main.plugin.getConfig().addDefault("Inventory.Warp.ShowCost", true);
            Main.plugin.getConfig().addDefault("Inventory.Hub.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Hub.Name", "&6Hub Selector");
            Main.plugin.getConfig().addDefault("Inventory.Hub.ShowCost", true);
            Main.plugin.getConfig().addDefault("Inventory.Lobby.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Lobby.Name", "&6Lobby Selector");
            Main.plugin.getConfig().addDefault("Inventory.Lobby.ShowCost", true);
            Main.plugin.getConfig().addDefault("Inventory.Spectate.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Spectate.Name", "&6Spectate Location Selector");
            Main.plugin.getConfig().addDefault("Inventory.Spectate.ShowCost", true);
            Main.plugin.getConfig().addDefault("Inventory.Spawn.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Spawn.Name", "&6Spawn Selector");
            Main.plugin.getConfig().addDefault("Inventory.Spawn.ShowCost", true);
            Main.plugin.getConfig().addDefault("Inventory.Spawn.ShowPlayerCount", true);
            Main.plugin.getConfig().addDefault("Inventory.Home.Use", true);
            Main.plugin.getConfig().addDefault("Inventory.Home.Name", "&6Your Home Selector");
            Main.plugin.getConfig().addDefault("Inventory.Home.ShowWorld", true);


            Main.plugin.getConfig().addDefault("Inventory.ListCommandOverride", true);


            Main.plugin.getConfig().addDefault("Join.Enabled", true);
            Main.plugin.getConfig().addDefault("Join.AllowBypass", true);
            Main.plugin.getConfig().addDefault("Join.Worlds.All", true);
            List<String> joinWorldsList = Main.plugin.getConfig().getStringList("Join.Worlds.List");
            joinWorldsList.add("minigames");
            joinWorldsList.add("exampleWorld1");
            Main.plugin.getConfig().set("Join.Worlds.List", joinWorldsList);
            Main.plugin.getConfig().addDefault("Join.Command", "Hub");
            Main.plugin.getConfig().addDefault("Join.Location.Hub", "ExampleHub");
            Main.plugin.getConfig().addDefault("Join.Location.Lobby", "ExampleLobby");
            Main.plugin.getConfig().addDefault("Join.Location.Warp", "ExampleWarp");
            Main.plugin.getConfig().addDefault("Join.Location.Spawn", "ExampleWorldName");

            Main.plugin.getConfig().addDefault("TPR.Max", 1000);
            List<String> tprWorldsList = Main.plugin.getConfig().getStringList("TPR.Worlds");
            tprWorldsList.add("world");
            tprWorldsList.add("ExampleWorld1");
            Main.plugin.getConfig().set("TPR.Worlds", tprWorldsList);

            Main.plugin.getConfig().addDefault("Spawn.PerWorld", true);
            Main.plugin.getConfig().addDefault("Spawn.Save to File", true);
            Main.plugin.getConfig().addDefault("Spawn.Default.World", "world");

            Main.plugin.getConfig().addDefault("Respawn.Use", true);
            Main.plugin.getConfig().addDefault("Respawn.PerWorld", true);
            Main.plugin.getConfig().addDefault("Respawn.Default.World", "world");

            Main.plugin.getConfig().addDefault("Signs.Log", false);
            Main.plugin.getConfig().addDefault("Signs.UseBreakPermission", true);

            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Spawn", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Hub", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Lobby", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Warp", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Spectate", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Home", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Respawn", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Tpr", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.TprAllowed", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Bypass", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Perm", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Args", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.SpawnJoin", true);
            Main.plugin.getConfig().addDefault("NOTIFICATIONS.Update", true);
            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }

    public static void locationFileCreator() {
        if (!getHubFile().exists()) {
            Main.plugin.getLogger().info("Generating hubs.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration h = getHubYml();
            h.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String hubVersion = "1.0";
            h.addDefault("Hubs Version", hubVersion);
            h.options().copyDefaults(true);
            saveCustomConfig(h, getHubFile());

            h.options().copyDefaults(true);
            saveCustomConfig(h, getHubFile());
        }
        if (!getLobbyFile().exists()) {
            Main.plugin.getLogger().info("Generating lobbies.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration l = getLobbyYml();
            l.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String lobVersion = "1.0";
            l.addDefault("Lobbies Version", lobVersion);
            l.options().copyDefaults(true);
            saveCustomConfig(l, getLobbyFile());

            l.options().copyDefaults(true);
            saveCustomConfig(l, getLobbyFile());
        }
        if (!getWarpFile().exists()) {
            Main.plugin.getLogger().info("Generating warps.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration w = getWarpYml();
            w.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String warpVersion = "1.0 ";
            w.addDefault("Warps Version", warpVersion);
            w.options().copyDefaults(true);
            saveCustomConfig(w, getWarpFile());

            w.options().copyDefaults(true);
            saveCustomConfig(w, getWarpFile());
        }
        if (!getSpectateFile().exists()) {
            Main.plugin.getLogger().info("Generating spectate.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration s = getSpectateYml();
            s.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String specVersion = "1.0";
            s.addDefault("Spectate Version", specVersion);
            s.options().copyDefaults(true);
            saveCustomConfig(s, getSpectateFile());

            s.options().copyDefaults(true);
            saveCustomConfig(s, getSpectateFile());
        }
        if (!getHomeFile().exists()) {
            Main.plugin.getLogger().info("Generating homes.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration h = getHomeYml();
            h.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String homeVersion = "1.0";
            h.addDefault("Homes Version", homeVersion);
            h.options().copyDefaults(true);
            saveCustomConfig(h, getHomeFile());

            h.options().copyDefaults(true);
            saveCustomConfig(h, getHomeFile());
        }
        if (!getSpawnFile().exists()) {
            Main.plugin.getLogger().info("Generating spawns.yml in folder: /plugins/SpawnJoin/locations/");

            YamlConfiguration s = getSpawnYml();
            s.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String spawnVersion = "1.0";
            s.addDefault("Spawns Version", spawnVersion);
            s.options().copyDefaults(true);
            saveCustomConfig(s, getSpawnFile());

            s.options().copyDefaults(true);
            saveCustomConfig(s, getSpawnFile());
        }
    }

    public static void dataFileCreator() {
        if (!getListFile().exists()) {
            Main.plugin.getLogger().info("Generating lists.yml in folder: /plugins/SpawnJoin/Data/");

            YamlConfiguration li = getListYml();

            li.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String listVersion = "1.0";
            li.addDefault("Lists Version", listVersion);
            li.options().copyDefaults(true);
            saveCustomConfig(li, getListFile());

            li.options().copyDefaults(true);
            saveCustomConfig(li, getListFile());
        }
        if (!getHomeDataFile().exists()) {
            Main.plugin.getLogger().info("Generating HomeData.yml in folder /plugins/SpawnJoin/Data");

            YamlConfiguration hd = getHomeDataYml();
            hd.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String homeDataVersion = "1.0";
            hd.addDefault("HomeData Version", homeDataVersion);
            hd.options().copyDefaults(true);
            saveCustomConfig(hd, getHomeDataFile());

            hd.options().copyDefaults(true);
            saveCustomConfig(hd, getHomeDataFile());
        }
        if (!getSpawnDataFile().exists()) {
            Main.plugin.getLogger().info("Generating SpawnData.yml in folder /plugins/SpawnJoin/Data");

            YamlConfiguration sd = getSpawnDataYml();
            sd.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String spawnDataVersion = "1.0";
            sd.addDefault("SpawnData Version", spawnDataVersion);
            sd.addDefault("world_nether", "world");
            sd.addDefault("world_the_end", "world");
            sd.options().copyDefaults(true);
            saveCustomConfig(sd, getSpawnDataFile());

            sd.options().copyDefaults(true);
            saveCustomConfig(sd, getSpawnDataFile());
        }
        if (!getSignDataFile().exists()) {
            Main.plugin.getLogger().info("Generating SignData.yml in folder /plugins/SpawnJoin/Data");

            YamlConfiguration sd = getSignDataYml();
            sd.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String signDataVersion = "1.0";
            sd.addDefault("SignData Version", signDataVersion);
            sd.addDefault("NextId", 1);
            sd.options().copyDefaults(true);
            saveCustomConfig(sd, getSignDataFile());

            sd.options().copyDefaults(true);
            saveCustomConfig(sd, getSignDataFile());
        }
    }

    public static void settingsFileCreator() {
        if (!getHomeSettingsFile().exists()) {
            Main.plugin.getLogger().info("Generating HomeSettings.yml in /plugins/SpawnJoin/Settings");

            YamlConfiguration hs = getHomeSettingsYml();
            hs.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
            String homeSettingsVersion = "1.0";
            hs.addDefault("Home Settings Version", homeSettingsVersion);
            List<String> list = hs.getStringList("Groups");
            list.add("Default");
            list.add("VIP");
            list.add("Staff");
            hs.set("Groups", list);
            hs.addDefault("GroupSettings.Default.limit", 1);
            hs.addDefault("GroupSettings.VIP.limit", 5);
            hs.addDefault("Groups.Staff.limit", 10);
            hs.options().copyDefaults(true);
            saveCustomConfig(hs, getHomeSettingsFile());

            hs.options().copyDefaults(true);
            saveCustomConfig(hs, getHomeSettingsFile());
        }
    }

    public static void fileVersionCheck() {
        if (!(Main.plugin.getConfig().getString("Config Version").equalsIgnoreCase(conVersion))) {
            Main.plugin.getLogger().severe("Config Outdated!!!! Plugin will not work properly!! Please delete the config file and restart the server"
                    + "then edit the defaults to your preferred settings!!");
            Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
        if (!(MessageManager.getMessageYml().getString("Messages Version").equalsIgnoreCase(msgVersion))) {
            Main.plugin.getLogger().severe("Message files Outdated!!!! Plugin will not work properly!! Please delete the Messages folder and restart the server"
                    + "then edit the defaults to your preferred settings!!");
            Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }

    //Getters
    public static File getListFile() {
        return new File(Main.plugin.getDataFolder() + "/Data/lists.yml");
    }

    public static YamlConfiguration getListYml() {
        return YamlConfiguration.loadConfiguration(getListFile());
    }

    public static File getHubFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/hubs.yml");
    }

    public static YamlConfiguration getHubYml() {
        return YamlConfiguration.loadConfiguration(getHubFile());
    }

    public static File getLobbyFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/lobbies.yml");
    }

    public static YamlConfiguration getLobbyYml() {
        return YamlConfiguration.loadConfiguration(getLobbyFile());
    }

    public static File getWarpFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/warps.yml");
    }

    public static YamlConfiguration getWarpYml() {
        return YamlConfiguration.loadConfiguration(getWarpFile());
    }

    public static File getSpectateFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/spectate.yml");
    }

    public static YamlConfiguration getSpectateYml() {
        return YamlConfiguration.loadConfiguration(getSpectateFile());
    }

    public static File getHomeFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/homes.yml");
    }

    public static YamlConfiguration getHomeYml() {
        return YamlConfiguration.loadConfiguration(getHomeFile());
    }

    public static File getSpawnFile() {
        return new File(Main.plugin.getDataFolder() + "/locations/spawns.yml");
    }

    public static YamlConfiguration getSpawnYml() {
        return YamlConfiguration.loadConfiguration(getSpawnFile());
    }

    public static File getHomeDataFile() {
        return new File(Main.plugin.getDataFolder() + "/Data/HomeData.yml");
    }

    public static YamlConfiguration getHomeDataYml() {
        return YamlConfiguration.loadConfiguration(getHomeDataFile());
    }

    public static File getSpawnDataFile() {
        return new File(Main.plugin.getDataFolder() + "/Data/SpawnData.yml");
    }

    public static YamlConfiguration getSpawnDataYml() {
        return YamlConfiguration.loadConfiguration(getSpawnDataFile());
    }

    public static File getSignDataFile() {
        return new File(Main.plugin.getDataFolder() + "/Data/SignData.yml");
    }

    public static YamlConfiguration getSignDataYml() {
        return YamlConfiguration.loadConfiguration(getSignDataFile());
    }

    public static File getHomeSettingsFile() {
        return new File(Main.plugin.getDataFolder() + "/Settings/HomeSettings.yml");
    }

    public static YamlConfiguration getHomeSettingsYml() {
        return YamlConfiguration.loadConfiguration(getHomeSettingsFile());
    }

    //Functions
    public static void saveCustomConfig(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}