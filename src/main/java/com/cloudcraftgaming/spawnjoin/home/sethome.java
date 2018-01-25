package com.cloudcraftgaming.spawnjoin.home;

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
import java.util.UUID;

public class sethome implements CommandExecutor {
    private Main plugin;

    public sethome(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (command.getName().equalsIgnoreCase("sethome")) {
            if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getPlayerOnlyMessage()));
                } else {
                    Player player = (Player) sender;
                    UUID Id = player.getUniqueId();
                    if (!(player.hasPermission("SpawnJoin.use.sethome"))) {
                        if (plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
                            sender.sendMessage(prefix + perm);
                        }
                    } else {
                        if (args.length < 1) {
                            if (player.hasPermission("SpawnJoin.bypass.homelimit")
                                    || plugin.getConfig().getBoolean("Commands.Home.GlobalLimit.Enabled")) {
                                if (player.hasPermission("SpawnJoin.bypass.homelimit")) {
                                    sethome.setHome("Home", sender);
                                } else {
                                    String allowed = plugin.getConfig().getString("Commands.Home.GlobalLimit.Limit");
                                    Integer allowedNumber = Integer.valueOf(allowed);
                                    Integer currentNumber = FileManager.getHomeDataYml().getInt("Players." + Id + ".HomeNumber");
                                    Integer newNumber = currentNumber + 1;
                                    if (newNumber > allowedNumber) {
                                        String original = MessageManager.getMessageYml().getString("Home.TooMany");
                                        String replaced = original.replaceAll("%number%", allowed);
                                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                    } else if (currentNumber == 1) {
                                        sethome.setHomeOne("Home", sender);
                                    } else {
                                        sethome.setHome("Home", sender);
                                    }
                                }
                            } else {
                                Integer currentNumber = Integer.valueOf(FileManager.getHomeDataYml().getString("Players." + Id + ".HomeNumber"));
                                String Allowed = FileManager.getHomeDataYml().getString("Players." + Id + ".AllowedHomes");
                                Integer AllowedNumber = Integer.valueOf(Allowed);
                                Integer newNumber = currentNumber + 1;
                                if (newNumber > AllowedNumber) {
                                    String original = MessageManager.getMessageYml().getString("Home.TooMany");
                                    String replaced = original.replaceAll("%number%", Allowed);
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                } else if (currentNumber == 1) {
                                    sethome.setHomeOne("Home", sender);
                                } else {
                                    sethome.setHome("Home", sender);
                                }
                            }
                        } else if (args.length == 1) {
                            String home = args[0];
                            if (player.hasPermission("SpawnJoin.bypass.homelimit")
                                    || plugin.getConfig().getBoolean("Commands.Home.GlobalLimit.Enabled")) {
                                if (player.hasPermission("SpawnJoin.bypass.homelimit")) {
                                    sethome.setHome(home, sender);
                                } else {
                                    String allowed = plugin.getConfig().getString("Commands.Home.GlobalLimit.Limit");
                                    Integer allowedNumber = Integer.valueOf(allowed);
                                    Integer currentNumber = FileManager.getHomeDataYml().getInt("Players." + Id + ".HomeNumber");
                                    Integer newNumber = currentNumber + 1;
                                    if (newNumber > allowedNumber) {
                                        String original = MessageManager.getMessageYml().getString("Home.TooMany");
                                        String replaced = original.replaceAll("%number%", allowed);
                                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                    } else if (currentNumber == 1) {
                                        sethome.setHomeOne(home, sender);
                                    } else {
                                        sethome.setHome(home, sender);
                                    }
                                }
                            } else {
                                Integer currentNumber = Integer.valueOf(FileManager.getHomeDataYml().getString("Players." + Id + ".HomeNumber"));
                                String Allowed = FileManager.getHomeDataYml().getString("Players." + Id + ".AllowedHomes");
                                Integer AllowedNumber = Integer.valueOf(Allowed);
                                Integer newNumber = currentNumber + 1;
                                if (newNumber > AllowedNumber) {
                                    String original = MessageManager.getMessageYml().getString("Home.TooMany");
                                    String replaced = original.replaceAll("%number%", Allowed);
                                    player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
                                } else {
                                    sethome.setHome(home, sender);
                                }
                            }
                        } else {
                            if (plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
                                sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Notifications.ManyArgs")));
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static void setHome(String home, CommandSender sender) {
        YamlConfiguration homes = FileManager.getHomeYml();
        YamlConfiguration homeData = FileManager.getHomeDataYml();
        YamlConfiguration lists = FileManager.getListYml();

        String prefix = MessageManager.getPrefix();
        Player player = (Player) sender;
        UUID Id = player.getUniqueId();
        homes.set("HOMES." + Id + "." + home + ".world", player.getLocation().getWorld().getName());
        homes.set("HOMES." + Id + "." + home + ".x", player.getLocation().getX());
        homes.set("HOMES." + Id + "." + home + ".y", player.getLocation().getY());
        homes.set("HOMES." + Id + "." + home + ".z", player.getLocation().getZ());
        homes.set("HOMES." + Id + "." + home + ".yaw", player.getLocation().getYaw());
        homes.set("HOMES." + Id + "." + home + ".pitch", player.getLocation().getPitch());
        homes.set("HOMES." + Id + "." + home + ".item", 355);
        FileManager.saveCustomConfig(homes, FileManager.getHomeFile());
        String oldS = homeData.getString("Players." + Id + ".HomeNumber");
        Integer old = Integer.valueOf(oldS);
        Integer newN = old + 1;
        homeData.set("Players." + Id + ".HomeNumber", newN);
        FileManager.saveCustomConfig(homeData, FileManager.getHomeDataFile());
        if (!lists.getStringList("Homes." + Id).contains(home)) {
            List<String> list = lists.getStringList("Homes." + Id);
            list.add(home);
            lists.set("Homes." + Id, list);
            FileManager.saveCustomConfig(lists, FileManager.getListFile());
        }
        String original = MessageManager.getMessageYml().getString("Home.Set");
        String replaced = original.replaceAll("%home%", home);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
        MenuManager.updatePlayerHomeInv(player);
    }

    private static void setHomeOne(String home, CommandSender sender) {
        YamlConfiguration homes = FileManager.getHomeYml();
        YamlConfiguration lists = FileManager.getListYml();

        String prefix = MessageManager.getPrefix();
        Player player = (Player) sender;
        UUID Id = player.getUniqueId();
        homes.set("HOMES." + Id + "." + home + ".world", player.getLocation().getWorld().getName());
        homes.set("HOMES." + Id + "." + home + ".x", player.getLocation().getX());
        homes.set("HOMES." + Id + "." + home + ".y", player.getLocation().getY());
        homes.set("HOMES." + Id + "." + home + ".z", player.getLocation().getZ());
        homes.set("HOMES." + Id + "." + home + ".yaw", player.getLocation().getYaw());
        homes.set("HOMES." + Id + "." + home + ".pitch", player.getLocation().getPitch());
        homes.set("HOMES." + Id + "." + home + ".item", 355);
        FileManager.saveCustomConfig(homes, FileManager.getHomeFile());
        if (!lists.getStringList("Homes." + Id).contains(home)) {
            List<String> list = lists.getStringList("Homes." + Id);
            list.add(home);
            lists.set("Homes." + Id, list);
            FileManager.saveCustomConfig(lists, FileManager.getListFile());
        }
        String original = MessageManager.getMessageYml().getString("Home.Set");
        String replaced = original.replaceAll("%home%", home);
        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', replaced));
        MenuManager.updatePlayerHomeInv(player);
    }
}
