package com.cloudcraftgaming.spawnjoin.warp;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 2/6/2016.
 */
public class InvWarp implements CommandExecutor {
    Main plugin;
    public InvWarp(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("InvWarp")) {
            if (plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")
                    && plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.invwarp")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (MenuManager.warpInv != null) {
                            player.openInventory(MenuManager.warpInv);
                            String msg = MessageManager.getMessageYml().getString("Inventory.OpenWarp");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            String msg = MessageManager.getMessageYml().getString("Warp.NoSet");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        }
                    }
                } else {
                    sender.sendMessage(prefix + perm);
                }
            }
        }
        return false;
    }
}