package me.ecomine.luchien.manager;

import me.ecomine.luchien.LucChien;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmocore.api.player.stats.StatType;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;

public class PowerManager {
    private HashMap<StatType, Double> powerType = new HashMap<>();

    public HashMap<StatType, Double> getPowerType() {
        return this.powerType;
    }

    public double getLucChienPlayer(OfflinePlayer p) {
        double lucchien = 0.0D;
        for (Map.Entry<StatType, Double> value : LucChien.getInstance().getPowerManager().getPowerType().entrySet())
            lucchien += PlayerData.get(p).getStats().getStat(String.valueOf(value.getKey())) * ((Double)value.getValue()).doubleValue();
        return lucchien;
    }
}
