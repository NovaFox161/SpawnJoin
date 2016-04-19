package com.cloudcraftgaming.spawnjoin.listeners;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.utils.DelayChecker;
import com.cloudcraftgaming.spawnjoin.utils.SignLogger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.UUID;

public class SignListener 
implements Listener {
	Main plugin;
	public SignListener(Main instance) {
		plugin = instance;
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChange(SignChangeEvent event) {
		String prefix = MessageManager.getPrefix();
		String perm = MessageManager.getNoPermMessage();
		Player player = event.getPlayer();
		if (event.getLine(0).equalsIgnoreCase("[SpawnJoin]")) {
			if (event.getLine(1).equalsIgnoreCase("warp") && (!(event.getLine(2).isEmpty()))) {
				if (player.hasPermission("SpawnJoin.sign.place.warp")) {
					String warpName = event.getLine(2);
					if (!(plugin.warps.contains("WARPS." + warpName))) {
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.NoWarp")));
					}
					else {
						event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
						event.setLine(1, ChatColor.GREEN + "Warp");
						event.setLine(2, warpName);
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.WarpSignSet")));
						if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
							Location loc = event.getBlock().getLocation();
							SignLogger.logSignPlace(loc, "Warp", player);
						}
					}
				}
				else {
					player.sendMessage(prefix + perm);
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("hub") && (!(event.getLine(2).isEmpty()))) {
				if (player.hasPermission("SpawnJoin.sign.place.hub")) {
					String hubName = event.getLine(2);
					if (!(plugin.hubs.contains("HUBS." + hubName))) {
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.NoHub")));
					}
					else {
						event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
						event.setLine(1, ChatColor.GREEN + "Hub");
						event.setLine(2, hubName);
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.HubSignSet")));
						if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
							Location loc = event.getBlock().getLocation();
							SignLogger.logSignPlace(loc, "Hub", player);
						}
					}
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("lobby") && (!(event.getLine(2).isEmpty()))) {
				if (player.hasPermission("SpawnJoin.sign.place.lobby")) {
					String lobbyName = event.getLine(2);
					if (!(plugin.lobs.contains("LOBBIES." + lobbyName))) {
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.noLobby")));
					}
					else {
						event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
						event.setLine(1, ChatColor.GREEN + "Lobby");
						event.setLine(2, lobbyName);
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.LobbySignSet")));
						if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
							Location loc = event.getBlock().getLocation();
							SignLogger.logSignPlace(loc, "Lobby", player);
						}
					}
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("spectate") && (!(event.getLine(2).isEmpty()))) {
				if (player.hasPermission("SpawnJoin.sign.place.spectate")) {
					String locName = event.getLine(2);
					if (!(plugin.spec.contains("SPECTATE." + locName))) {
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.noLoc")));
					}
					else {
						event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
						event.setLine(1, ChatColor.GREEN + "Spectate");
						event.setLine(2, locName);
						player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.SpectateSignSet")));
						if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
							Location loc = event.getBlock().getLocation();
							SignLogger.logSignPlace(loc, "Spectate", player);
						}
					}
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("Tpr")) {
				if (player.hasPermission("SpawnJoin.sign.place.tpr")) {
					Integer radiusInt = plugin.getConfig().getInt("TPR.Max");
					String radius = String.valueOf(radiusInt);
					event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
					event.setLine(1, ChatColor.GREEN + "Tpr");
					event.setLine(2, ChatColor.DARK_RED + "Teleport Random");
					event.setLine(3, ChatColor.DARK_GREEN + "Radius: " + ChatColor.BLUE + radius + ChatColor.DARK_GREEN + "Blks");
					player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.TprSignSet")));
					if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
						Location loc = event.getBlock().getLocation();
						SignLogger.logSignPlace(loc, "Tpr", player);
					}
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("Spawn")) {
				if (player.hasPermission("SpawnJoin.sign.place.spawn")) {
					event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
					event.setLine(1, ChatColor.GREEN + "Spawn");
					player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.SpawnSignSet")));
					if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
						Location loc = event.getBlock().getLocation();
						SignLogger.logSignPlace(loc, "Spawn", player);
					}
				}
			}
			else if (event.getLine(1).equalsIgnoreCase("Home")) {
				if (player.hasPermission("SpawnJoin.sign.place.Home")) {
					event.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
					event.setLine(1, ChatColor.GREEN + "Home");
					event.setLine(2, "Home");
					player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.HomeSignSet")));
					if (plugin.getConfig().getString("Signs.Log").equalsIgnoreCase("True")) {
						Location loc = event.getBlock().getLocation();
						SignLogger.logSignPlace(loc, "Home", player);
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		String prefix = MessageManager.getPrefix();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player player = event.getPlayer();
			if (event.getClickedBlock().getState() instanceof Sign) {
				Sign sign = (Sign) event.getClickedBlock().getState();
				if (sign.getLine(0).contains("[SpawnJoin]")) {
					if (sign.getLine(1).contains("Warp")) {
						if (player.hasPermission("SpawnJoin.sign.use.warp")) {
							String warpName = sign.getLine(2);
							if (plugin.warps.contains("WARPS." + warpName)) {
								DelayChecker.warpDelayCheck(warpName, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.NoWarp")));
							}
						}
					}
					else if (sign.getLine(1).contains("Hub")) {
						if (player.hasPermission("SpawnJoin.sign.use.hub")) {
							String hubName = sign.getLine(2);
							if (plugin.hubs.contains("HUBS." + hubName)) {
								DelayChecker.hubDelayCheck(hubName, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.NoHub")));
							}
						}
					}
					else if (sign.getLine(1).contains("Lobby")) {
						if (player.hasPermission("SpawnJoin.sign.use.lobby")) {
							String lobbyName = sign.getLine(2);
							if (plugin.lobs.contains("LOBBIES." + lobbyName)) {
								DelayChecker.lobbyDelayCheck(lobbyName, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.NoLobby")));
							}
						}
					}
					else if (sign.getLine(1).contains("Spectate")) {
						if (player.hasPermission("SpawnJoin.sign.use.spectate")) {
							String locName = sign.getLine(2);
							if (plugin.spec.contains("SPECTATE." + locName)) {
								DelayChecker.spectateDelayCheck(locName, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.NoLoc")));
							}
						}
					}
					else if (sign.getLine(1).contains("Tpr")) {
						if (player.hasPermission("SpawnJoin.sign.use.tpr")) {
							List<String> list = plugin.getConfig().getStringList("TPR.Worlds");
					    	final String world = player.getLocation().getWorld().getName();
					    	if (list.contains(world)) {
								DelayChecker.tprDelayCheck(world, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Tpr.Disallowed")));
							}
						}
					}
					else if (sign.getLine(1).contains("Spawn")) {
						if (player.hasPermission("SpawnJoin.sign.use.spawn")) {
							DelayChecker.spawnDelayCheck(player.getWorld().getName(), player);
						}
					}
					else if (sign.getLine(1).contains("Home")) {
						if (player.hasPermission("SpawnJoin.sign.use.Home")) {
							UUID Id = player.getUniqueId();
							String home = sign.getLine(2);
							if (plugin.homes.contains("HOMES." + Id + "." + home)) {
								DelayChecker.homeDelayCheck(home, player);
							}
							else {
								player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Home.NoHome")));
							}
						}
					}
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignBreak(BlockBreakEvent event) {
		if (event.getBlock().getState() instanceof Sign) {
			Sign sign = (Sign) event.getBlock().getState();
			if (sign.getLine(0).contains("[SpawnJoin]")) {
				if (sign.getLine(1).contains("Warp") || sign.getLine(1).contains("Hub") || sign.getLine(1).contains("Lobby")
						|| sign.getLine(1).contains("Tpr") || sign.getLine(1).contains("Spectate") || sign.getLine(1).contains("Spawn")
						|| sign.getLine(1).contains("Home")) {
					if (!(event.isCancelled())) {
						Player player = event.getPlayer();
						if (plugin.getConfig().getString("Signs.UseBreakPermission").equalsIgnoreCase("True")) {
							if (!(player.hasPermission("SpawnJoin.sign.break"))) {
								event.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}
}