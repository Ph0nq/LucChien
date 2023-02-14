package me.ecomine.luchien.listener.playerItem;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.enumapi.ItemTagAPI;
import me.ecomine.luchien.utils.UtilsMessages;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerUseItemEvent implements Listener {
    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player)e.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType().equals(Material.AIR))
                return;
            NBTItem nbtItem = NBTItem.get(item);
            if (!nbtItem.hasTag(ItemTagAPI.LUCCHIEN_POWER.name()))
                return;
            double lc = Double.valueOf(nbtItem.getString(ItemTagAPI.LUCCHIEN_POWER.name())).doubleValue();
            if (LucChien.getInstance().getPowerManager().getLucChienPlayer((OfflinePlayer)player) < lc) {
                player.sendMessage(UtilsMessages.get("Item").replace("{lucchien}", String.valueOf(lc)));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.AIR))
            return;
        NBTItem nbtItem = NBTItem.get(item);
        if (!nbtItem.hasTag(ItemTagAPI.LUCCHIEN_POWER.name()))
            return;
        double lc = Double.valueOf(nbtItem.getString(ItemTagAPI.LUCCHIEN_POWER.name())).doubleValue();
        if (LucChien.getInstance().getPowerManager().getLucChienPlayer((OfflinePlayer)player) < lc) {
            player.sendMessage(UtilsMessages.get("Item").replace("{lucchien}", String.valueOf(lc)));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player)e.getWhoClicked();
        if (e.getInventory().getType() == InventoryType.CRAFTING &&
                e.getSlotType() == InventoryType.SlotType.ARMOR) {
            if (e.getCursor().getType().equals(Material.AIR))
                return;
            ItemStack item = e.getCursor();
            NBTItem nbtItem = NBTItem.get(item);
            if (!nbtItem.hasTag(ItemTagAPI.LUCCHIEN_POWER.name()))
                return;
            double lc = Double.valueOf(nbtItem.getString(ItemTagAPI.LUCCHIEN_POWER.name())).doubleValue();
            if (LucChien.getInstance().getPowerManager().getLucChienPlayer((OfflinePlayer)player) < lc) {
                player.sendMessage(UtilsMessages.get("Item").replace("{lucchien}", String.valueOf(lc)));
                e.setCancelled(true);
            }
        }
    }
}
