package com.cloudcraftgaming.spawnjoin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.cloudcraftgaming.spawnjoin.Main;

public class SignLogger {
	static Date now = new Date();
	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	public static void logSignPlace(Location loc, String type, Player player) {
		String time = format.format(now);
		String name = player.getName();
		Integer Id = Main.plugin.signData.getInt("NextId");
		Main.plugin.signData.set("Signs." + Id + ".Type", type);
		Main.plugin.signData.set("Signs." + Id + ".time", time);
		Main.plugin.signData.set("Signs." + Id + ".Placed by", name);
		Main.plugin.signData.set("Signs." + Id + ".Location.world", loc.getWorld().getName());
		Main.plugin.signData.set("Signs." + Id + ".Location.x", loc.getX());
		Main.plugin.signData.set("Signs." + Id + ".Location.y", loc.getY());
		Main.plugin.signData.set("Signs." + Id + ".Location.z", loc.getZ());
		Main.plugin.signData.set("Signs." + Id + ".Location.yaw", loc.getYaw());
		Main.plugin.signData.set("Signs." + Id + ".Location.pitch", loc.getPitch());
		Integer newId = Id + 1;
		Main.plugin.signData.set("NextId", newId);
		Main.plugin.saveCustomConfig(Main.plugin.signData, Main.plugin.signDataFile);
	}
}
