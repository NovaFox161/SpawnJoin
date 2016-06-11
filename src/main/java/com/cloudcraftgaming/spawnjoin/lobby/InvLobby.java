package com.cloudcraftgaming.spawnjoin.lobby;

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
public class InvLobby implements CommandExecutor {
    Main plugin;
    public InvLobby(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("InvLobby")) {
            if (plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")
                    && plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.invlobby")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (MenuManager.lobbyInv != null) {
                            player.openInventory(MenuManager.lobbyInv);
                            String msg = MessageManager.getMessageYml().getString("Inventory.OpenLobby");
                            player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                        } else {
                            String msg = MessageManager.getMessageYml().getString("Lobby.NoSet");
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