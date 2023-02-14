package me.ecomine.luchien.utils;

import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.config.ConfigAPI;
import net.Indyuce.mmocore.api.player.stats.StatType;
import net.md_5.bungee.api.ChatColor;

import java.util.HashMap;

public class UtilsMessages implements ConfigAPI {
    public static HashMap<String, String> message = new HashMap<>();

    public static String get(String string) {
        return message.containsKey(string) ? message.get(string) : ("MESSAGE: " + string);
    }

    public static void loadUtilsMessages() {
        Message.getConfig().getConfigurationSection("Message.").getKeys(false).forEach(value -> message.put(value, Replace(Message.getConfig().getString("Message." + value))));
    }

    public static void loadStatType() {
        settings.getConfig().getConfigurationSection("LucChien.StatType.").getKeys(false).stream().forEach(value -> {
            double stt = settings.getConfig().getDouble("LucChien.StatType." + value);
            LucChien.getInstance().getPowerManager().getPowerType().put(StatType.valueOf(value.toUpperCase()), Double.valueOf(stt));
        });
    }

    public static String Replace(String s) {
        if (s.contains("{prefix}"))
            return ChatColor.translateAlternateColorCodes('&', s.replace("{prefix}", Message.getConfig().getString("Prefix")));
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}