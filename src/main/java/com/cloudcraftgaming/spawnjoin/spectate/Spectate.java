package com.cloudcraftgaming.spawnjoin.spectate;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectate implements CommandExecutor {
    private Main plugin;

    public Spectate(Main instance) {
        plugin = instance;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(final CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spectate")) {
            if (plugin.getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.spectate")) {
                    if (args.length < 1) {
                        if (!(sender instanceof Player)) {
                            String consoleMsg = MessageManager.getMessageYml().getString("Spectate.Console");
                            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', consoleMsg));
                            if (FileManager.getListYml().contains("Spectate")) {
                                String original = FileManager.getListYml().getString("Spectate");
                                String replaced = original.replace("[", "").replace("]", "");
                                String heading = MessageManager.getMessageYml().getString("Spectate.SpectateHeading");
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
                                sender.sendMessage(ChatColor.AQUA + replaced);
                            } else {
                                String msg = MessageManager.getMessageYml().getString("Spectate.NoSet");
                                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                            }
                        } else {
                            String chooseMsg = MessageManager.getMessageYml().getString("Spectate.Choose");
                            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', chooseMsg));
                            if (FileManager.getListYml().contains("Spectate")) {
                                String original = FileManager.getListYml().getString("Spectate");
                                String replaced = original.replace("[", "").replace("]", "");
                                String heading = MessageManager.getMessageYml().getString("Spectate.SpectateHeading");
                                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
                                sender.sendMessage(ChatColor.AQUA + replaced);
                            } else {
                                String msg = MessageManager.getMessageYml().getString("Spectate.NoSet");
                                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                            }
                        }
                    } else {
                        if (args.length == 1) {
                            if (!(sender instanceof Player)) {
                                String consoleMsg = MessageManager.getMessageYml().getString("Spectate.Console");
                                sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', consoleMsg));
                                if (FileManager.getListYml().contains("Spectate")) {
                                    String original = FileManager.getListYml().getString("Spectate");
                                    String replaced = original.replace("[", "").replace("]", "");
                                    String heading = MessageManager.getMessageYml().getString("Spectate.SpectateHeading");
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
                                    sender.sendMessage(replaced);
                                } else {
                                    String msg = MessageManager.getMessageYml().getString("Spectate.NoSet");
                                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                }
                            } else {
                                Player player = (Player) sender;
                                String loc = args[0];
                                if (player.hasPermission("SpawnJoin.use.spectate." + loc)) {
                                    if (!(LocationChecker.spectateExists(loc))) {
                                        String msg = MessageManager.getMessageYml().getString("Spectate.NoLoc");
                                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                    } else {
                                        DelayChecker.spectateDelayCheck(loc, player);
                                    }
                                } else {
                                    if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                                        String msg = MessageManager.getMessageYml().getString("Spectate.SpectatePerm");
                                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                    }
                                }
                            }
                        } else {
                            if (args.length == 2) {
                                if (sender.hasPermission("SpawnJoin.use.spectateothers")) {
                                    String loc = args[0];
                                    Player target = Bukkit.getPlayer(args[1]);
                                    if (target == null) {
                                        String msg = MessageManager.getMessageYml().getString("Notifications.PlayerOffline");
                                        sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                    } else {
                                        if (!(LocationChecker.spectateExists(loc))) {
                                            String msg = MessageManager.getMessageYml().getString("Spectate.NoLoc");
                                            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                        } else {
                                            Teleporter.spectate(loc, target, sender);
                                        }
                                    }
                                } else {
                                    if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                                        sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                                    }
                                }
                            } else {
                                if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                    String msg = MessageManager.getMessageYml().getString("Notifications.ManyArgs");
                                    sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
