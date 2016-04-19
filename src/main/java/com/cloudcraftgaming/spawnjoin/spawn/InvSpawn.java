package com.cloudcraftgaming.spawnjoin.spawn;

import com.cloudcraftgaming.spawnjoin.Main;
import com.cloudcraftgaming.spawnjoin.MessageManager;
import com.cloudcraftgaming.spawnjoin.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nova Fox on 2/7/2016.
 */
public class InvSpawn implements CommandExecutor {
    Main plugin;
    public InvSpawn(Main instance) {
        plugin = instance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = MessageManager.getPrefix();
        String perm = MessageManager.getNoPermMessage();
        if (cmd.getName().equalsIgnoreCase("InvSpawn")) {
            if (plugin.getConfig().getString("Commands.Spawn.Enabled").equalsIgnoreCase("True")
                    && plugin.getConfig().getString("Inventory.Use").equalsIgnoreCase("True")) {
                if (sender.hasPermission("SpawnJoin.use.invspawn")) {
                    if (sender instanceof Player) {
                        MenuManager.updateSpawnInv();
                        Player player = (Player) sender;
                        player.openInventory(MenuManager.spawnInv);
                        String msg = MessageManager.getMessageYml().getString("Inventory.OpenSpawn");
                        player.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', msg));
                    }
                } else {
                    sender.sendMessage(prefix + perm);
                }
            }
        }
        return false;
    }
}