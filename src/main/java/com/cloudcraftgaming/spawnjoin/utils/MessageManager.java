package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageManager {
	public static File getMessageFile() {
		String langFile = Main.plugin.getConfig().getString("Language");
		return new File(Main.plugin.getDataFolder() + "/Messages/" + langFile + ".yml");
	}
	public static YamlConfiguration getMessageYml() {
		File messageFile = getMessageFile();
		return YamlConfiguration.loadConfiguration(messageFile);
	}
	public static String getPrefix() {
		String prefixOr = ChatColor.translateAlternateColorCodes('&', getMessageYml().getString("Prefix"));
		return prefixOr + " " + ChatColor.RESET;
	}
	public static String getNoPermMessage() {
		return ChatColor.translateAlternateColorCodes('&', getMessageYml().getString("Notifications.NoPerm"));
	}
	public static String getPlayerOnlyMessage() {
		return  ChatColor.translateAlternateColorCodes('&', getMessageYml().getString("Notifications.PlayerOnly"));
	}
	public static void createEnglishFile() {
		File enFile = new File(Main.plugin.getDataFolder() + "/Messages/En.yml");
		if (!(enFile.exists())) {
			YamlConfiguration en = YamlConfiguration.loadConfiguration(enFile);
			Main.plugin.getLogger().info("Generating En.yml in folder: /plugins/SpawnJoin/Messages/");
			en.addDefault("DO NOT DELETE", "SpawnJoin is developed and managed by Shades161");
			en.addDefault("Messages Version", FileManager.msgVersion);
			en.addDefault("Prefix", "&5[SpawnJoin]");

			en.addDefault("Join.Bypass", "&6You have bypassed SpawnJoin auto teleport!");
			en.addDefault("Join.Spawn", "&6You are now at the spawn!");
			en.addDefault("Join.Hub.hub", "&6You have been warped to the hub!");
			en.addDefault("Join.Hub.other", "&6You have been warped to the &5%hub% &6hub!");
			en.addDefault("Join.Lobby.lobby", "&6You have been warped to the lobby!");
			en.addDefault("Join.Lobby.other", "&6You have been warped to the &5%lobby% &6lobby!");
			en.addDefault("Join.Warp", "&6You have been warped to the &5%warp% &6warp!");

			en.addDefault("Spawn.Spawn", "&6You are now at the world spawn!");
			en.addDefault("Spawn.Respawn", "&6You are now at the world spawn!");
			en.addDefault("Spawn.set", "&6The spawn has been set!");
			en.addDefault("Spawn.NoSet", "&4No spawns have been set!");
			en.addDefault("Spawn.NoSpawn", "&4That spawn does not exist or is not on file!");
			en.addDefault("Spawn.SpawnsHeading", "&6-~-~- &5Spawn List &6-~-~-");
			en.addDefault("Spawn.Target", "&6You have teleported &3%target% &6to the spawn!");
			en.addDefault("Spawn.Console", "&4Please use: &3/spawn <playerName>");
			en.addDefault("Spawn.Delay", "&6Teleporting in &4%delay% &6seconds...");
			en.addDefault("Spawn.EditCost", "&6Spawn &2%spawn% &6now costs &2%cost%&6!");
			en.addDefault("Spawn.EditItem", "&5Item display for spawn &6%spawn% &5is now: &6%item%");

			en.addDefault("Tpr.Disallowed", "&4Tpr is not allowed in this world!");
			en.addDefault("Tpr.Distance", "&6You have been teleported &5%distance% &6blocks away from your original location!");
			en.addDefault("Tpr.Delay", "&6Teleporting in &4%delay% &6seconds...");
			en.addDefault("Tpr.Cooldown", "&4You cannot tpr again yet!");
			en.addDefault("Tpr.TprSignSet", "&6Tpr sign set! Right click to Teleport Random!");
			en.addDefault("Tpr.Teleporting", "&6Teleporting to a random location... please wait...");

			en.addDefault("Warp.NoWarp", "&4That warp does not exist!");
			en.addDefault("Warp.Deleted", "&4Warp deleted!");
			en.addDefault("Warp.Set", "&6Warp saved! Use: /warp %warp% &6to go there!");
			en.addDefault("Warp.WarpsHeading", "&6-~-~- &5Warps List &6-~-~-");
			en.addDefault("Warp.NoSet", "&4No warps have been set!");
			en.addDefault("Warp.Console", "&6Please use: &4/warp <warpName> <playerName>");
			en.addDefault("Warp.WarpPerm", "&4You do not have permission to warp there!");
			en.addDefault("Warp.Delay", "&6Warping in &4%delay% &6seconds...");
			en.addDefault("Warp.Warp", "&6You have been warped to &5%warp%&6!");
			en.addDefault("Warp.Target", "&6You have warped &3%target% &6to &3%warp%&6!");
			en.addDefault("Warp.EditCost", "&6Warp &2%warp% &6now costs &2%cost%&6!");
			en.addDefault("Warp.AlreadyExists", "&4A warp with that name already exists!");
			en.addDefault("Warp.EditName", "&6Warp name edited! Use: &3/warp %warp% &6to go there!");
			en.addDefault("Warp.EditItem", "&5Item display for warp &6%warp% &5is now: &6%item%");

			en.addDefault("Lobby.NoLobby", "&4That lobby does not exist!");
			en.addDefault("Lobby.Deleted", "&4Lobby deleted!");
			en.addDefault("Lobby.Set", "&6Lobby saved! Use: &5/lobby %lobby% &6to go there!");
			en.addDefault("Lobby.LobbiesHeading", "&6-~-~- &5Lobbies List &6-~-~-");
			en.addDefault("Lobby.NoSet", "&4No lobbies have been set!");
			en.addDefault("Lobby.Console", "&6Please use: &4/lobby <LobbyName> <playerName>");
			en.addDefault("Lobby.LobbyPerm", "&4You do not have permission to warp to that lobby!");
			en.addDefault("Lobby.Delay", "&6Warping in &4%delay% &6seconds...");
			en.addDefault("Lobby.Lobby", "&6You have been warped to &5%lobby% lobby&6!");
			en.addDefault("Lobby.Target", "&6You have warped &3%target% &6to &3Lobby %lobby%&6!");
			en.addDefault("Lobby.EditCost", "&6Lobby &2%lobby% &6now costs &2%cost%&6!");
			en.addDefault("Lobby.AlreadyExists", "&4A lobby with that name already exists!");
			en.addDefault("Lobby.EditName", "&6Lobby name edited! Use: &3/lobby %lobby% &6to go there!");
			en.addDefault("Lobby.EditItem", "&5Item display for lobby &6%lobby% &5is now: &6%item%");

			en.addDefault("Hub.NoHub", "&4That hub does not exist!");
			en.addDefault("Hub.Deleted", "&4Hub deleted!");
			en.addDefault("Hub.Set", "&6Hub saved! Use: /hub %hub% &6to go there!");
			en.addDefault("Hub.HubsHeading", "&6-~-~- &5Hubs List &6-~-~-");
			en.addDefault("Hub.NoSet", "&4No hubs have been set!");
			en.addDefault("Hub.Console", "&6Please use: &4/hub <hubName> <playerName>");
			en.addDefault("Hub.HubPerm", "&4You do not have permission to warp there!");
			en.addDefault("Hub.Delay", "&6Warping in &4%delay% &6seconds...");
			en.addDefault("Hub.Hub", "&6You have been warped to &5%hub%&6!");
			en.addDefault("Hub.Target", "&6You have warped &3%target% &6to &3%hub%&6!");
			en.addDefault("Hub.EditCost", "&6Hub &2%hub% &6now costs &2%cost%&6!");
			en.addDefault("Hub.AlreadyExists", "&4A hub with that name already exists!");
			en.addDefault("Hub.EditName", "&6Hub name edited! Use: &3/hub %hub% &6to go there!");
			en.addDefault("Hub.EditItem", "&5Item display for hub &6%hub% &5is now: &6%item%");

			en.addDefault("Spectate.NoLoc", "&4That spectating location does not exist!");
			en.addDefault("Spectate.Deleted", "&4Spectating location deleted!");
			en.addDefault("Spectate.Set", "&6Spectating location saved! Use: /spectate %loc% &6to go there!");
			en.addDefault("Spectate.SpectateHeading", "&6-~-~- &5Spectating Location List &6-~-~-");
			en.addDefault("Spectate.NoSet", "&4No spectating locations have been set!");
			en.addDefault("Spectate.Console", "&6Please use: &4/spectate <LocationName> <playerName>");
			en.addDefault("Spectate.SpectatePerm", "&4You do not have permission to warp there!");
			en.addDefault("Spectate.Delay", "&6Warping in &4%delay% &6seconds...");
			en.addDefault("Spectate.Spectate", "&6You have been warped to Spectating location: &5%loc%&6!");
			en.addDefault("Spectate.Target", "&6You have warped &3%target% &6to spectating location: &3%loc%&6!");
			en.addDefault("Spectate.Choose", "&6Please choose a location from the list, then use: &5/spectate (LocationName) &6to go there.");

			en.addDefault("Home.NoHome", "&4That home does not exist!");
			en.addDefault("Home.Deleted", "&4Home deleted!");
			en.addDefault("Home.Set", "&6Home saved! Use: &5/Home %home% &6to go there!");
			en.addDefault("Home.HomesHeading", "&6-~-~- &5Home List &6-~-~-");
			en.addDefault("Home.NoSet", "&4You do not have any homes set!");
			en.addDefault("Home.Console", "&6Please use: &4/Home <HomeName> <playerName>");
			en.addDefault("Home.HomePerm", "&4You do not have permission to teleport there!");
			en.addDefault("Home.Delay", "&6Teleporting in &4%delay% &6seconds...");
			en.addDefault("Home.Home", "&6You have been teleported to &5home %home%&6!");
			en.addDefault("Home.Target", "&6You have teleported &3%target% &6to &3%Home%&6!");
			en.addDefault("Home.TooMany", "&4You only have permission to set &2%number% &4home(s)!");
			en.addDefault("Home.EditLimit", "&4%player% &6can now set a max of &2%number% &6homes!");
			en.addDefault("Home.EditItem", "&5Item display for home &6%home% &5is now: &6%item%");

			en.addDefault("Signs.HomeSignSet", "&6Home sign set! Right click to teleport to your Home!");
			en.addDefault("Signs.SpectateSignSet", "&6Spectate sign set! Right click to teleport there!");
			en.addDefault("Signs.HubSignSet", "&6Hub sign set! Right click to teleport there!");
			en.addDefault("Signs.LobbySignSet", "&6Lobby sign set! Right click to teleport there!");
			en.addDefault("Signs.WarpSignSet", "&6Warp sign set! Right click to teleport there!");
			en.addDefault("Signs.SpawnSignSet", "&5Spawn sign set! Right click to go to the spawn!");

			en.addDefault("Inventory.OpenWarp", "&5Opened Warp menu!");
			en.addDefault("Inventory.OpenHub", "&5Opened Hub Menu!");
			en.addDefault("Inventory.OpenLobby", "&5Opened Lobby Menu!");
			en.addDefault("Inventory.OpenSpectate", "&5Opened Spectating Menu!");
			en.addDefault("Inventory.OpenSpawn", "&5Opened Spawn Menu!");
			en.addDefault("Inventory.OpenHome", "&5Opened Home menu!");

			en.addDefault("Notifications.NoPerm", "&4You do not have permission to do that!");
			en.addDefault("Notifications.FewArgs", "&4Too few arguments! Please use: &3/spawnjoin help &4for a list of commands.");
			en.addDefault("Notifications.ManyArgs", "&4Too many arguments! Please use: &3/spawnjoin help &4for a list of commands.");
			en.addDefault("Notifications.NoCommand", "&4Please use: &a/spawnjoin help &4for a list of commands.");
			en.addDefault("Notifications.PlayerOffline", "&4That player is not online!");
			en.addDefault("Notifications.PlayerOnly", "&4Only players can do this!");
			en.addDefault("Notifications.PlayerNotExist", "&4That player does not exist or has never been on the server before!");
			en.addDefault("Notifications.NotInt", "&4Number must be a valid integer(Ex: 1, 2, 3)!");
			en.addDefault("Notifications.Update.Version", "&6A new version of SpawnJoin is available: &9%version%");
			en.addDefault("Notifications.Update.Link", "&aDownload it at: &9%link%");
			en.addDefault("Notifications.Update.Console.Version", "A new version of SpawnJoin is available: %version%");
			en.addDefault("Notifications.Update.Console.Link", "Download it at: %link%");
			en.addDefault("Notifications.ClearTprCooldown", "Clearing Tpr cooldowns...");

			en.addDefault("Help.Heading", "&6-~- &5SpawnJoin Help Page %current%/%max% &6-~-");
			en.addDefault("Help.Next", "&aUse /SpawnJoin help %next% for more commands.");
			en.addDefault("Help.End", "&aEnd of SpawnJoin help.");
			en.addDefault("Help.SpawnJoin-Info", "&2/spawnjoin info &3- Displays info about SpawnJoin.");
			en.addDefault("Help.Setspawn", "&2/setspawn &3- Sets the world spawn.");
			en.addDefault("Help.Editspawn", "&2/editspawn <SpawnName> <Type> <Value> &3- Edits a spawn's info.");
			en.addDefault("Help.Spawns", "&2/spawns &3- Displays a list of spawns or opens the spawn menu.");
			en.addDefault("Help.InvSpawn", "&2/invspawn &3- Opens an inventory menu for spawns.");
			en.addDefault("Help.Spawn", "&2/spawn (player) &3- Teleports you or another player to the world spawn.");
			en.addDefault("Help.Tpr", "&2/tpr &3- Teleports you to a random location.");
			en.addDefault("Help.Hub", "&2/hub (hubName) (player) &3- Teleports you or another player to the hub.");
			en.addDefault("Help.Hubs", "&2/hubs &3- Displays a list of hubs or opens the hub menu.");
			en.addDefault("Help.InvHub", "&2/invhub &3- Opens an inventory menu for hubs.");
			en.addDefault("Help.Sethub", "&2/sethub (hubName) &3- Sets a hub.");
			en.addDefault("Help.Delhub", "&2/delhub (hubName) &3- Deletes a hub.");
			en.addDefault("Help.Edithub", "&2/edithub <hubName> <Type> <value> &3- Edits a hub's info.");
			en.addDefault("Help.Lobby", "&2/lobby (lobbyName) (player) &3- Teleports you or another player to the lobby.");
			en.addDefault("Help.Lobbies", "&2/lobbies &3- Displays a list of lobbies or opens the lobby menu.");
			en.addDefault("Help.InvLobby", "&2/invlobby &3- Opens an inventory menu for lobbies.");
			en.addDefault("Help.Setlobby", "&2/setlobby (lobbyName) &3- Sets a lobby.");
			en.addDefault("Help.Dellobby", "&2/dellobby (lobbyName &3- Deletes a lobby.");
			en.addDefault("Help.Editlobby", "&2/editlobby <lobbyName> <Type> <Value> &3- Edits a lobby's info.");
			en.addDefault("Help.Warp", "&2/warp <warpName> (player) &3- Teleports you or another player to the warp.");
			en.addDefault("Help.Warps", "&2/warps &3- Displays a list of warps or opens the warp menu.");
			en.addDefault("Help.InvWarp", "&2/invwarp &3- Opens an inventory menu for warps.");
			en.addDefault("Help.Setwarp", "&2/setwarp <warpName> &3- Sets a warp.");
			en.addDefault("Help.Delwarp", "&2/delwarp <warpName> &3- Deletes a warp.");
			en.addDefault("Help.Editwarp", "&2/editwarp <WarpName> <type> <value> &3- Edits a warp's info.");
			en.addDefault("Help.Home", "&2/home (homeName) &3- Teleports you to your home.");
			en.addDefault("Help.Homes", "&2/homes &3- Displays a list of your homes or opens your home menu.");
			en.addDefault("Help.InvHome", "&2/invhome &3- Opens an inventory menu for your homes.");
			en.addDefault("Help.Sethome", "&2/sethome (homeName) &3- Sets your home.");
			en.addDefault("Help.Delhome", "&2/delhome (homeName) &3- Deletes your home.");
			en.addDefault("Help.Edithome", "&2/edithome <homeName/player> <type> <value> &3- Edits a home's info or a player's home info.");
			en.addDefault("Help.Spectate", "&2/spectate (LocationName) &3- Teleports you to a spectating location or displays a list.");
			en.addDefault("Help.InvSpectate", "&2/invspectate &3- Opens an inventory menu for spectating locations.");
			en.addDefault("Help.Setspectate", "&2/setspectate <LocationName> &3- Sets a spectating location.");
			en.addDefault("Help.Delspectate", "&2/delspectate <LocationName> &3- Deletes a spectating location.");

			en.addDefault("Color.For color/formatting help visit:", "http://minecraft.gamepedia.com/Formatting_codes");

			en.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(en, enFile);

			en.options().copyDefaults(true);
			Main.plugin.saveCustomConfig(en, enFile);
		}
	}
}