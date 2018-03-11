package com.cloudcraftgaming.spawnjoin.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignLogger {
    private static Date now = new Date();
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static void logSignPlace(Location loc, String type, Player player) {
        YamlConfiguration signData = FileManager.getSignDataYml();

        String time = format.format(now);
        String name = player.getName();
        Integer Id = signData.getInt("NextId");
        signData.set("Signs." + Id + ".Type", type);
        signData.set("Signs." + Id + ".time", time);
        signData.set("Signs." + Id + ".Placed by", name);
        signData.set("Signs." + Id + ".Location.world", loc.getWorld().getName());
        signData.set("Signs." + Id + ".Location.x", loc.getX());
        signData.set("Signs." + Id + ".Location.y", loc.getY());
        signData.set("Signs." + Id + ".Location.z", loc.getZ());
        signData.set("Signs." + Id + ".Location.yaw", loc.getYaw());
        signData.set("Signs." + Id + ".Location.pitch", loc.getPitch());
        Integer newId = Id + 1;
        signData.set("NextId", newId);
        FileManager.saveCustomConfig(signData, FileManager.getSignDataFile());
    }
}
