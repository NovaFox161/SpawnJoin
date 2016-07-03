package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class EditSign 
implements CommandExecutor {
	Main plugin;
	public EditSign(Main instance) {
		plugin = instance;
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String prefix = MessageManager.getPrefix();
		if (command.getName().equalsIgnoreCase("editsign")) {
			if (plugin.getConfig().getString("Commands.Sign.Enabled").equalsIgnoreCase("True")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
					return false;
				}
				else {
					Player player = (Player) sender;
					if (!(player.hasPermission("SpawnJoin.use.editsign"))) {
						player.sendMessage(prefix + MessageManager.getMessageYml());
					}
					else {
						Block b = player.getTargetBlock((HashSet<Byte>) null, 10);
						if (b.getState() instanceof Sign) {
							Sign sign = (Sign) b.getState();
							if (sign.getLine(0).contains("[SpawnJoin]")) {
								if (args.length < 1) {
									if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("true")) {
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
									}
								}
								else if (args.length == 1) {
									String type = args[0];
									if (type.equalsIgnoreCase("spawn")) {
										sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
										sign.setLine(1, ChatColor.GREEN + "Spawn");
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.SpawnSignSet")));
									}
									else if (type.equalsIgnoreCase("tpr")) {
										Integer radiusInt = plugin.getConfig().getInt("TPR.Max");
										String radius = String.valueOf(radiusInt);
										sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
										sign.setLine(1, ChatColor.GREEN + "Tpr");
										sign.setLine(2, ChatColor.DARK_RED + "Teleport Random");
										sign.setLine(3, ChatColor.DARK_GREEN + "Radius: " + ChatColor.BLUE + radius + ChatColor.DARK_GREEN + "Blks");
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.TprSignSet")));
									}
									else {
										sender.sendMessage(prefix + MessageManager.getMessageYml().getString("Signs.InvalidType"));
									}
								}
								else if (args.length == 2) {
									String type = args[0];
									String loc = args[1];
									if (type.equalsIgnoreCase("warp")) {
										if (plugin.warps.contains("WARPS." + loc)) {
											sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
											sign.setLine(1, ChatColor.GREEN + "Warp");
											sign.setLine(2, loc);
										}
										else {
											player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Warp.NoWarp")));
										}
									}
									else if (type.equalsIgnoreCase("hub")) {
										if (plugin.hubs.contains("HUBS." + loc)) {
											sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
											sign.setLine(1, ChatColor.GREEN + "Hub");
											sign.setLine(2, loc);
										}
										else {
											player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Hub.NoHub")));
										}
									}
									else if (type.equalsIgnoreCase("lobby")) {
										if (plugin.lobs.contains("LOBBIES." + loc)) {
											sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
											sign.setLine(1, ChatColor.GREEN + "Lobby");
											sign.setLine(2, loc);
										}
										else { 
											player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Lobby.NoLobby")));
										}
									}
									else if (type.equalsIgnoreCase("spectate")) {
										if (!(plugin.spec.contains("SPECTATE." + loc))) {
											player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spectate.NoLoc")));
										}
										else {
											sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
											sign.setLine(1, ChatColor.GREEN + "Spectate");
											sign.setLine(2, loc);
											player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.SpectateSignSet")));
										}
									}
									else if (type.equalsIgnoreCase("Home")) {
										sign.setLine(0, ChatColor.DARK_PURPLE + "[SpawnJoin]");
										sign.setLine(1, ChatColor.GREEN + "Home");
										sign.setLine(2, "Home");
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.HomeSignSet")));
									}
									else {
										player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Signs.InvalidType")));
									}
								}
							}
						}
					}
				}
			}
			else {
				return false;
			}
		}
 	return false;
	}

}
