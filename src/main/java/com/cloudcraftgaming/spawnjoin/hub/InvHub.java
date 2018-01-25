package com.cloudcraftgaming.spawnjoin.hub;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.utils.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 2/6/2016.
 */
public class InvHub implements CommandExecutor {
    Main plugin;
    public InvHub(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("InvHub")) {
            if (plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")
                    && plugin.getConfig().getString("Inventory.Hub.Use").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.invhub")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (MenuManager.hubInv != null) {
                            player.openInventory(MenuManager.hubInv);
                            String msg = MessageManager.getMessageYml().getString("Inventory.OpenHub");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            String msg = MessageManager.getMessageYml().getString("Hub.NoSet");
                            player.sendMessage(prefix + ChatColor.RED + ChatColor.translateAlternateColorCodes('&', msg));
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