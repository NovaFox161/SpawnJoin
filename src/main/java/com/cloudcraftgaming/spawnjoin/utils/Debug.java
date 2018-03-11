package com.cloudcraftgaming.spawnjoin.utils;

import com.cloudcraftgaming.spawnjoin.Main;

public class Debug {
    public static void startup() {
        Main.plugin.getLogger().info("-~-~-~-~ SpawnJoin Debug Info ~-~-~-~-");
        Main.plugin.getLogger().info("<Debug> SpawnJoin is developed and managed by Shades161");
        Main.plugin.getLogger().info("<Debug> SpawnJoin version: " + Main.plugin.getDescription().getVersion());

        Main.plugin.getLogger().info("<Debug> Getting enabled commands...");
        if (Main.plugin.getConfig().getString("Commands.Spawn.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Spawn commands are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Spawn commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Tpr.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Tpr command are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Tpr commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Hub.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Hub commands are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Hub commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Lobby.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Lobby commands are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Lobby commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Warp.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Warp commands are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Warp commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Home.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Home commands are enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Home commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Commands.Spectate.Enabled").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("<Debug> Spectate are commands enabled!");
        } else {
            Main.plugin.getLogger().info("<Debug> Spectate commands are disabled!");
        }
        if (Main.plugin.getConfig().getString("Join.Enabled").equalsIgnoreCase("True")) {
            String cmd = Main.plugin.getConfig().getString("Join.Command");
            Main.plugin.getLogger().info("<Debug> SpawnJoin auto teleport enabled! Command in use: " + cmd);
        } else {
            Main.plugin.getLogger().info("<Debug> SpawnJoin auto teleport is disabled.");
        }
    }
}
