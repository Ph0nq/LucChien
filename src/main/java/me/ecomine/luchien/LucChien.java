package me.ecomine.luchien;

import me.ecomine.luchien.command.RegisterCommands;
import me.ecomine.luchien.config.ConfigAPI;
import me.ecomine.luchien.hook.PlaceholderAPIHooker;
import me.ecomine.luchien.listener.ListenerManager;
import me.ecomine.luchien.manager.PowerManager;
import me.ecomine.luchien.utils.Utils;
import me.ecomine.luchien.utils.UtilsMessages;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LucChien extends JavaPlugin {
    private static LucChien instance;

    private PowerManager powerManager;

    private RegisterCommands registerCommands;

    private PlaceholderAPIHooker placeholderAPIHooker;

    private ListenerManager listenerManager;

    public static LucChien getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        this.powerManager = new PowerManager();
        this.registerCommands = new RegisterCommands();
        Utils.writeLog(new String[] { "&e========[&4LucChien&e]========" });
        Utils.writeLog(new String[] { "&lAuthor: &rPhongTN" });
        Utils.writeLog(new String[] { "LucChien starting..." });
        Utils.writeLog(new String[] { "Loading configs..." });
        ConfigAPI.loadAllConfig();
        UtilsMessages.loadUtilsMessages();
        UtilsMessages.loadStatType();
        this.listenerManager = new ListenerManager();
        this.registerCommands.registerCommands();
        this.placeholderAPIHooker = new PlaceholderAPIHooker(instance);
        Utils.writeLog(new String[] { "The config files have been loaded successfully!" });
        Utils.writeLog(new String[] { "Loaded!" });
        Utils.writeLog(new String[] { "&e=========================" });
    }

    public void onDisable() {
        Utils.writeLog(

                new String[] { "&e========[&fLucChien&e]========" });
        Utils.writeLog(new String[] { "Stopping plugin..." });
        Utils.writeLog(new String[] { "Plugin has been stopped!" });
        Utils.writeLog(new String[] { "&e=========================" });
    }

    public PowerManager getPowerManager() {
        return this.powerManager;
    }

    public PlaceholderAPIHooker getPlaceholderAPIHooker() {
        return this.placeholderAPIHooker;
    }

    public ListenerManager getListenerManager() {
        return this.listenerManager;
    }

    public void reload() {
        Bukkit.getScheduler().cancelTasks((Plugin)instance);
        HandlerList.unregisterAll((Plugin)instance);
        onEnable();
    }
}
