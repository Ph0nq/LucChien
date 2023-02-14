package me.ecomine.luchien.hook;

import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.utils.Utils;
import org.bukkit.Bukkit;

public class PlaceholderAPIHooker {
    private boolean hook;

    public PlaceholderAPIHooker(LucChien plugin) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.writeLog(new String[] { "PlaceholderAPI found" });
            this.hook = true;
            if ((new LucChienPAPIExpansion(plugin)).register())
                Utils.writeLog(new String[] { "Success when registering LucChien" });
        } else {
            this.hook = false;
            Utils.writeLog(new String[] { "PlaceholderAPI not found" });
        }
    }

    public boolean isHook() {
        return this.hook;
    }
}
