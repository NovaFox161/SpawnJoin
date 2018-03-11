package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import com.cloudcraftgaming.spawnjoin.utils.FileManager;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SetSpawn implements CommandExecutor {
    private Main plugin;

    public SetSpawn(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        String setSpawn = (ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Spawn.set")));
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(prefix + "Only players can do this!");
            } else {
                Player player = (Player) sender;
                if (!(player.hasPermission("SpawnJoin.use.setspawn"))) {
                    player.sendMessage(prefix + perm);
                } else {
                    if (plugin.getConfig().getString("Spawn.Save to File").equalsIgnoreCase("False")) {
                        Location loc = player.getLocation();
                        String world = player.getWorld().getName();
                        Bukkit.getWorld(world).setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                        player.sendMessage(prefix + setSpawn);
                    } else {
                        YamlConfiguration spawns = FileManager.getSpawnYml();

                        String world = player.getWorld().getName();
                        spawns.set("Spawns." + world + ".world", player.getLocation().getWorld().getName());
                        spawns.set("Spawns." + world + ".x", player.getLocation().getX());
                        spawns.set("Spawns." + world + ".y", player.getLocation().getY());
                        spawns.set("Spawns." + world + ".z", player.getLocation().getZ());
                        spawns.set("Spawns." + world + ".yaw", player.getLocation().getYaw());
                        spawns.set("Spawns." + world + ".pitch", player.getLocation().getPitch());
                        spawns.set("Spawns." + world + ".item", 2);
                        spawns.set("Spawns." + world + ".cost", 0.0);
                        FileManager.saveCustomConfig(spawns, FileManager.getSpawnFile());
                        Bukkit.getWorld(world).setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());

                        YamlConfiguration lists = FileManager.getListYml();
                        if (lists.contains("Spawns")) {
                            if (!lists.getString("Spawns").contains(world)) {
                                List<String> list = lists.getStringList("Spawns");
                                list.add(world);
                                lists.set("Spawns", list);
                                FileManager.saveCustomConfig(lists, FileManager.getListFile());
                            }
                        } else {
                            List<String> list = lists.getStringList("Spawns");
                            list.add(world);
                            lists.set("Spawns", list);
                            FileManager.saveCustomConfig(lists, FileManager.getListFile());
                        }
                        MenuManager.updateSpawnInv();
                        player.sendMessage(prefix + setSpawn);
                    }
                }
            }
        }
        return false;
    }
}