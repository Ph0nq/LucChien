package me.ecomine.luchien.listener;

import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.listener.playerItem.PlayerUseItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ListenerManager {
    public ListenerManager() {
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerUseItemEvent(), (Plugin)LucChien.getInstance());
    }
}
