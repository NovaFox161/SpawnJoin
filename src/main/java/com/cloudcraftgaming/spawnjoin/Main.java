package com.cloudcraftgaming.spawnjoin;

import com.cloudcraftgaming.spawnjoin.home.*;
import com.cloudcraftgaming.spawnjoin.hub.*;
import com.cloudcraftgaming.spawnjoin.listeners.*;
import com.cloudcraftgaming.spawnjoin.lobby.*;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.spawn.*;
import com.cloudcraftgaming.spawnjoin.spectate.*;
import com.cloudcraftgaming.spawnjoin.utils.*;
import com.cloudcraftgaming.spawnjoin.warp.*;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
	public static Main plugin;
	
	public File listFile = new File(this.getDataFolder() + "/Data/lists.yml");
	public FileConfiguration lists = YamlConfiguration.loadConfiguration(listFile);
	public File hubFile = new File(this.getDataFolder() + "/locations/hubs.yml");
	public FileConfiguration hubs = YamlConfiguration.loadConfiguration(hubFile);
	public File lobFile = new File(this.getDataFolder() + "/locations/lobbies.yml");
	public FileConfiguration lobs = YamlConfiguration.loadConfiguration(lobFile);
	public File warpFile = new File(this.getDataFolder() + "/locations/warps.yml");
	public FileConfiguration warps = YamlConfiguration.loadConfiguration(warpFile);
	public File specFile = new File(this.getDataFolder() + "/locations/spectate.yml");
	public FileConfiguration spec = YamlConfiguration.loadConfiguration(specFile);
	public File homeFile = new File(this.getDataFolder() + "/locations/homes.yml");
	public FileConfiguration homes = (YamlConfiguration.loadConfiguration(homeFile));
	public File spawnFile = new File(this.getDataFolder() + "/locations/spawns.yml");
	public FileConfiguration spawns = (YamlConfiguration.loadConfiguration(spawnFile));
	public File homeDataFile = new File(this.getDataFolder() + "/Data/HomeData.yml");
	public FileConfiguration homeData = (YamlConfiguration.loadConfiguration(homeDataFile));
	public File spawnDataFile = new File(this.getDataFolder() + "/Data/SpawnData.yml");
	public FileConfiguration spawnData = (YamlConfiguration.loadConfiguration(spawnDataFile));
	public File signDataFile = new File(this.getDataFolder() + "/Data/SignData.yml");
	public FileConfiguration signData = (YamlConfiguration.loadConfiguration(signDataFile));
	public File homeSettingsFile = new File(this.getDataFolder() + "/Settings/HomeSettings.yml");
	public FileConfiguration homeSettings = (YamlConfiguration.loadConfiguration(homeSettingsFile));
	
	public UpdateChecker updateChecker;
	public String conVersion = "5.2";
	public String listVersion = "1.0";
	public String hubVersion = "1.0";
	public String lobVersion = "1.0";
	public String warpVersion = "1.0 ";
	public String specVersion = "1.0";
	public String homeVersion = "1.0";
	public String spawnVersion = "1.0";
	public String msgVersion = "2.0";
	public String homeDataVersion = "1.0";
	public String spawnDataVersion = "1.0";
	public String signDataVersion = "1.0";
	public String homeSettingsVersion = "1.0";

	public void onDisable() {
		if (!(Tpr.cooldown.isEmpty())) {
			this.getLogger().info(MessageManager.getMessageYml().getString("Notifications.ClearTprCooldowns"));
			Tpr.cooldown.clear();
		}
	}
	
	public void onEnable() {
		plugin = this;
		FileManager.configCreator();
		FileManager.locationFileCreator();
		FileManager.dataFileCreator();
		FileManager.settingsFileCreator();
		MessageManager.createEnglishFile();

		FileManager.fileVersionCheck();

		//Inventory menu stuffs
		if (getConfig().getString("Inventory.Warp.Use").equalsIgnoreCase("True")) {
			MenuManager.createWarpInv();
		}
		if (getConfig().getString("Inventory.Hub.Use").equalsIgnoreCase("True")) {
			MenuManager.createHubInv();
		}
		if (getConfig().getString("Inventory.Lobby.Use").equalsIgnoreCase("True")) {
			MenuManager.createLobbyInv();
		}
		if (getConfig().getString("Inventory.Spectate.Use").equalsIgnoreCase("True")) {
			MenuManager.createSpectateInv();
		}
		if (getConfig().getString("Inventory.Spawn.Use").equalsIgnoreCase("True")) {
			MenuManager.createSpawnInv();
		}

		//Commands and event registering
		getLogger().info("Registering SpawnJoin event listeners...");
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(new QuitListener(this), this);
		getServer().getPluginManager().registerEvents(new SignListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);

		if (getConfig().getString("Respawn.Use").equalsIgnoreCase("True")) {
			getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
		}
		getLogger().info("Event listeners registered! Registering enabled commands...");
		getCommand("spawnjoin").setExecutor(new HelpCommands(this));
		getCommand("editsign").setExecutor(new EditSign(this));
		if (getConfig().getString("Commands.Spawn.Enabled").equalsIgnoreCase("True")) {
			getCommand("setspawn").setExecutor(new SetSpawn(this));
			getCommand("spawns").setExecutor(new Spawns(this));
			getCommand("spawn").setExecutor(new Spawn(this));
			getCommand("editspawn").setExecutor(new EditSpawn(this));
			getCommand("invspawn").setExecutor(new InvSpawn(this));
		}
		if (getConfig().getString("Commands.Tpr.Enabled").equalsIgnoreCase("True")) {
			getCommand("tpr").setExecutor(new Tpr(this));
		}
		if (getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
			getCommand("sethub").setExecutor(new SetHub(this));
			getCommand("hub").setExecutor(new Hub(this));
			getCommand("delhub").setExecutor(new Delhub(this));
			getCommand("hubs").setExecutor(new Hubs(this));
			getCommand("edithub").setExecutor(new EditHub(this));
			getCommand("invhub").setExecutor(new InvHub(this));
		}
		if (getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
			getCommand("setlobby").setExecutor(new SetLobby(this));
			getCommand("lobby").setExecutor(new Lobby(this));
			getCommand("dellobby").setExecutor(new Dellobby(this));
			getCommand("lobbies").setExecutor(new Lobbies(this));
			getCommand("editlobby").setExecutor(new EditLobby(this));
			getCommand("invlobby").setExecutor(new InvLobby(this));
		}
		if (getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
			getCommand("setwarp").setExecutor(new Setwarp(this));
			getCommand("warp").setExecutor(new Warp(this));
			getCommand("delwarp").setExecutor(new Delwarp(this));
			getCommand("warps").setExecutor(new Warps(this));
			getCommand("editwarp").setExecutor(new EditWarp(this));
			getCommand("invwarp").setExecutor(new InvWarp(this));
		}
		if (getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
			getCommand("sethome").setExecutor(new sethome(this));
			getCommand("DelHome").setExecutor(new DelHome(this));
			getCommand("Home").setExecutor(new Home(this));
			getCommand("homes").setExecutor(new Homes(this));
			getCommand("edithome").setExecutor(new EditHome(this));
			getCommand("invhome").setExecutor(new InvHome(this));
		}
		if (getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
			getCommand("setspectate").setExecutor(new SetSpectate(this));
			getCommand("delspectate").setExecutor(new Delspectate(this));
			getCommand("spectate").setExecutor(new Spectate(this));
			getCommand("invspectate").setExecutor(new InvSpectate(this));
		}
		getLogger().info("All enabled commands now registered!");
		
		if (getConfig().getString("Config Version").equalsIgnoreCase(conVersion)) {
			 if (getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
				 this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/teleport-spawn-join/files.rss");
				 if (this.updateChecker.UpdateNeeded()) {
					 String versionMsgOriginal = MessageManager.getMessageYml().getString("Notifications.Update.Console.Version");
					 String versionMsg = versionMsgOriginal.replaceAll("%version%", this.updateChecker.getVersion());
					 String linkMsgOriginal = MessageManager.getMessageYml().getString("Notifications.Update.Console.Link");
					 String linkMsg = linkMsgOriginal.replaceAll("%link%", this.updateChecker.getLink());
				     getLogger().info(ChatColor.translateAlternateColorCodes('&', versionMsg));
				     getLogger().info(ChatColor.translateAlternateColorCodes('&', linkMsg));
			    }
			}
		}
		if (getConfig().getString("Config Version").equalsIgnoreCase(conVersion)) {
			if (getConfig().getString("Debug").equalsIgnoreCase("True")) {
				Debug.startup();
			}
		}
	}
	 public void saveCustomConfig(FileConfiguration ymlConfig, File ymlFile) {
		 try {
			 ymlConfig.save(ymlFile);
		 } catch (IOException e) {
			e.printStackTrace();
		 }
	}
}