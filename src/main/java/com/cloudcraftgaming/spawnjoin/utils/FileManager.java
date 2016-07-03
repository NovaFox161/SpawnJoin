package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;

import java.io.File;
import java.util.List;

public class FileManager {
	public static void configCreator() {
		File file = new File(Main.plugin.getDataFolder() + File.separator + "config.yml");
		if (!(file.exists())) {
			Main.plugin.getLogger().info("Generating config.yml in folder /plugins/SpawnJoin/");
			
			Main.plugin.getConfig().addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.getConfig().addDefault("Config Version", Main.plugin.conVersion);
			Main.plugin.getConfig().addDefault("Check for Updates", true);
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

			Main.plugin.getConfig().addDefault("Inventory.Use", true);
			Main.plugin.getConfig().addDefault("Inventory.ListCommandOverride", true);

			
			Main.plugin.getConfig().addDefault("Join.Enabled", true);
			Main.plugin.getConfig().addDefault("Join.AllowBypass", true);
			Main.plugin.getConfig().addDefault("Join.Command", "Hub");
			Main.plugin.getConfig().addDefault("Join.Location.Hub", "ExampleHub");
			Main.plugin.getConfig().addDefault("Join.Location.Lobby", "ExampleLobby");
			Main.plugin.getConfig().addDefault("Join.Location.Warp", "ExampleWarp");
			Main.plugin.getConfig().addDefault("Join.Location.Spawn", "ExampleWorldName");
			
			Main.plugin.getConfig().addDefault("TPR.Max", 1000);
			List<String> list = Main.plugin.getConfig().getStringList("TPR.Worlds");
			list.add("world");
			list.add("ExampleWorld1");
			Main.plugin.getConfig().set("TPR.Worlds", list);
			
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
		if (!(Main.plugin.hubFile.exists())) {
			Main.plugin.getLogger().info("Generating hubs.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.hubs.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.hubs.addDefault("Hubs Version", Main.plugin.hubVersion);
			Main.plugin.hubs.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.hubs, Main.plugin.hubFile);
			
			Main.plugin.hubs.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.hubs, Main.plugin.hubFile);	
		}
		if (!(Main.plugin.lobFile.exists())) {
			Main.plugin.getLogger().info("Generating lobbies.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.lobs.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.lobs.addDefault("Lobbies Version", Main.plugin.lobVersion);
			Main.plugin.lobs.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.lobs, Main.plugin.lobFile);
			
			Main.plugin.lobs.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.lobs, Main.plugin.lobFile);	
		}
		if (!(Main.plugin.warpFile.exists())) {
			Main.plugin.getLogger().info("Generating warps.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.warps.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.warps.addDefault("Warps Version", Main.plugin.warpVersion);
			Main.plugin.warps.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);
			
			Main.plugin.warps.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.warps, Main.plugin.warpFile);	
		}
		if (!(Main.plugin.specFile.exists())) {
			Main.plugin.getLogger().info("Generating spectate.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.spec.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.spec.addDefault("Spectate Version", Main.plugin.specVersion);
			Main.plugin.spec.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spec, Main.plugin.specFile);
			
			Main.plugin.spec.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spec, Main.plugin.specFile);	
		}
		if (!(Main.plugin.homeFile.exists())) {
			Main.plugin.getLogger().info("Generating homes.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.homes.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.homes.addDefault("Homes Version", Main.plugin.homeVersion);
			Main.plugin.homes.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homes, Main.plugin.homeFile);
			
			Main.plugin.homes.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homes, Main.plugin.homeFile);
		}
		if (!(Main.plugin.spawnFile.exists())) {
			Main.plugin.getLogger().info("Generating spawns.yml in folder: /plugins/SpawnJoin/locations/");
			Main.plugin.spawns.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.spawns.addDefault("Spawns Version", Main.plugin.spawnVersion);
			Main.plugin.spawns.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spawns, Main.plugin.spawnFile);
			
			Main.plugin.spawns.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spawns, Main.plugin.spawnFile);
		}
	}
	public static void dataFileCreator() {
		if (!(Main.plugin.listFile.exists())) {
			Main.plugin.getLogger().info("Generating lists.yml in folder: /plugins/SpawnJoin/Data/");
			Main.plugin.lists.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.lists.addDefault("Lists Version", Main.plugin.listVersion);
			Main.plugin.lists.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);
			
			Main.plugin.lists.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.lists, Main.plugin.listFile);	
		}
		if (!(Main.plugin.homeDataFile.exists())) {
			Main.plugin.getLogger().info("Generating HomeData.yml in folder /plugins/SpawnJoin/Data");
			Main.plugin.homeData.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.homeData.addDefault("HomeData Version", Main.plugin.homeDataVersion);
			Main.plugin.homeData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homeData, Main.plugin.homeDataFile);
			
			Main.plugin.homeData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homeData, Main.plugin.homeDataFile);
		}
		if (!(Main.plugin.spawnDataFile.exists())) {
			Main.plugin.getLogger().info("Generating SpawnData.yml in folder /plugins/SpawnJoin/Data");
			Main.plugin.spawnData.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.spawnData.addDefault("SpawnData Version", Main.plugin.spawnDataVersion);
			Main.plugin.spawnData.addDefault("world_nether", "world");
			Main.plugin.spawnData.addDefault("world_the_end", "world");
			Main.plugin.spawnData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spawnData, Main.plugin.spawnDataFile);
			
			Main.plugin.spawnData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.spawnData, Main.plugin.spawnDataFile);
		}
		if (!(Main.plugin.signDataFile.exists())) {
			Main.plugin.getLogger().info("Generating SignData.yml in folder /plugins/SpawnJoin/Data");
			Main.plugin.signData.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.signData.addDefault("SignData Version", Main.plugin.signDataVersion);
			Main.plugin.signData.addDefault("NextId", 1);
			Main.plugin.signData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.signData, Main.plugin.signDataFile);
			
			Main.plugin.signData.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.signData, Main.plugin.signDataFile);
		}
	}
	public static void settingsFileCreator() {
		if (!(Main.plugin.homeSettingsFile.exists())) {
			Main.plugin.getLogger().info("Generating HomeSettings.yml in /plugins/SpawnJoin/Settings");
			Main.plugin.homeSettings.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			Main.plugin.homeSettings.addDefault("Home Settings Version", Main.plugin.homeSettingsVersion);
			List<String> list = Main.plugin.homeSettings.getStringList("Groups");
			list.add("Default");
			list.add("VIP");
			list.add("Staff");
			Main.plugin.homeSettings.set("Groups", list);
			Main.plugin.homeSettings.addDefault("GroupSettings.Default.limit", 1);
			Main.plugin.homeSettings.addDefault("GroupSettings.VIP.limit", 5);
			Main.plugin.homeSettings.addDefault("Groups.Staff.limit", 10);
			Main.plugin.homeSettings.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homeSettings, Main.plugin.homeSettingsFile);

			Main.plugin.homeSettings.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(Main.plugin.homeSettings, Main.plugin.homeSettingsFile);
		}
	}
	public static void fileVersionCheck() {
		if (!(Main.plugin.getConfig().getString("Config Version").equalsIgnoreCase(Main.plugin.conVersion))) {
			Main.plugin.getLogger().severe("Config Outdated!!!! Plugin will not work properly!! Please delete the config file and restart the server"
					+ "then edit the defaults to your preferred settings!!");
			Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
			Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
		}
		if (!(MessageManager.getMessageYml().getString("Messages Version").equalsIgnoreCase(Main.plugin.msgVersion))) {
			Main.plugin.getLogger().severe("Message files Outdated!!!! Plugin will not work properly!! Please delete the Messages folder and restart the server"
					+ "then edit the defaults to your preferred settings!!");
			Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
			Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
		}
	}
}