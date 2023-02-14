package me.ecomine.luchien.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.ecomine.luchien.LucChien;
import org.bukkit.OfflinePlayer;

public class LucChienPAPIExpansion extends PlaceholderExpansion {
    private final LucChien mmopower;

    public LucChienPAPIExpansion(LucChien sk) {
        this.mmopower = sk;
    }

    public String getIdentifier() {
        return "lucchien";
    }

    public boolean persist() {
        return true;
    }

    public boolean canRegister() {
        return true;
    }

    public String getAuthor() {
        return "EcoMineVN";
    }

    public String getVersion() {
        return this.mmopower.getDescription().getVersion();
    }

    public String onRequest(OfflinePlayer p, String params) {
        if (p == null)
            return null;
        double luchien = LucChien.getInstance().getPowerManager().getLucChienPlayer(p);
        if (params.equalsIgnoreCase("value"))
            return String.valueOf((int)luchien);
        return null;
    }
}
