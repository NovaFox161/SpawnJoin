package com.cloudcraftgaming.spawnjoin.home;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 2/8/2016.
 */
public class InvHome implements CommandExecutor {
    Main plugin;
    public InvHome(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("InvHome")) {
            if (plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True") && plugin.getConfig().getBoolean("Inventory.Use")) {
                if (sender.hasPermission("SpawnJoin.use.invhome")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        MenuManager.updatePlayerHomeInv(player);
                        if (MenuManager.hasHomeInventoryMenu(player)) {
                            player.openInventory(MenuManager.getHomeInventory(player));
                            String msg = MessageManager.getMessageYml().getString("Inventory.OpenHome");
                            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            String msg = MessageManager.getMessageYml().getString("Home.NoSet");
                            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                        }
                    }
                } else {
                    sender.sendMessage(MessageManager.getPrefix() + MessageManager.getNoPermMessage());
                }
            }
        }
        return false;
    }
}
