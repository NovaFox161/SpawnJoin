package com.cloudcraftgaming.spawnjoin.spectate;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpectate implements CommandExecutor {
    private Main plugin;

    public SetSpectate(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (command.getName().equalsIgnoreCase("setspectate")) {
            if (plugin.getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.PlayerOnly")));
                } else {
                    Player p = (Player) sender;
                    if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
                        if (p.hasPermission("SpawnJoin.use.sethub")) {
                            if (args.length < 1) {
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.FewArgs")));
                            } else {
                                if (args.length == 1) {
                                    YamlConfiguration spec = FileManager.getSpectateYml();

                                    String loc = args[0];
                                    spec.set("SPECTATE." + loc + ".world", p.getLocation().getWorld().getName());
                                    spec.set("SPECTATE." + loc + ".x", p.getLocation().getX());
                                    spec.set("SPECTATE." + loc + ".y", p.getLocation().getY());
                                    spec.set("SPECTATE." + loc + ".z", p.getLocation().getZ());
                                    spec.set("SPECTATE." + loc + ".yaw", p.getLocation().getYaw());
                                    spec.set("SPECTATE." + loc + ".pitch", p.getLocation().getPitch());
                                    spec.set("SPECTATE." + loc + ".item", 2);
                                    spec.set("SPECTATE." + loc + ".cost", 0.0);
                                    FileManager.saveCustomConfig(spec, FileManager.getSpectateFile());
                                    if (!FileManager.getListYml().getStringList("Spectate").contains(loc)) {
                                        YamlConfiguration lists = FileManager.getListYml();

                                        List<String> list = lists.getStringList("Spectate");
                                        list.add(loc);
                                        lists.set("Spectate", list);
                                        FileManager.saveCustomConfig(lists, FileManager.getListFile());
                                    }
                                    MenuManager.updateSpectateInv();
                                    String original = MessageManager.getMessageYml().getString("Spectate.Set");
                                    String replaced = original.replaceAll("%loc%", loc);
                                    p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                } else {
                                    if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                        sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                                    }
                                }
                            }
                        } else {
                            if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                                sender.sendMessage(prefix + perm);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}