package com.cloudcraftgaming.spawnjoin.spectate;

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
public class InvSpectate implements CommandExecutor {
    Main plugin;
    public InvSpectate(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("InvSpectate")) {
            if (plugin.getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.invspectate")
                        && plugin.getConfig().getString("Inventory.Spectate.Use").equalsIgnoreCase("True")) {
                    if (sender instanceof Player) {
                        if (MenuManager.spectateInv != null) {
                            Player player = (Player) sender;
                            player.openInventory(MenuManager.spectateInv);
                            String msg = MessageManager.getMessageYml().getString("Inventory.OpenSpectate");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            String msg = MessageManager.getMessageYml().getString("Spectate.NoSet");
                            sender.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
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