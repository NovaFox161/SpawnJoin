package com.cloudcraftgaming.spawnjoin.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Help {
    public static void helpList(Integer page, CommandSender sender) {
        String headingOr = MessageManager.getMessageYml().getString("Help.Heading");
        String nextOr = MessageManager.getMessageYml().getString("Help.Next");
        String next = nextOr.replaceAll("%next%", String.valueOf(page + 1));
        String heading = headingOr.replaceAll("%current%", page.toString()).replaceAll("%max%", "6");
        if (page == 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.SpawnJoin-Info")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Spawn")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Spawns")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvSpawn")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Setspawn")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Editspawn")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Tpr")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', next));
        } else if (page == 2) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Hub")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Hubs")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvHub")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Sethub")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Delhub")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Edithub")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', next));
        } else if (page == 3) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Lobby")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Lobbies")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvLobby")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Setlobby")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Dellobby")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Editlobby")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', next));
        } else if (page == 4) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Warp")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Warps")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvWarp")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Setwarp")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Delwarp")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Editwarp")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', next));
        } else if (page == 5) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Home")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Homes")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvHome")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Sethome")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Delhome")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Edithome")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', next));
        } else if (page == 6) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Spectate")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.InvSpectate")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Setspectate")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.Delspectate")));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageManager.getMessageYml().getString("Help.End")));
        }
    }
}
