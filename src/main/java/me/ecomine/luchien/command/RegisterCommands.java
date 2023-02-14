package me.ecomine.luchien.command;

import io.lumine.mythic.lib.api.item.ItemTag;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.enumapi.ItemTagAPI;
import me.ecomine.luchien.utils.UtilsMessages;
import org.bukkit.*;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class RegisterCommands extends UtilsMessages {
    public void registerCommands() {
        registerCommand("lucchien", new Command());
    }

    private static void registerCommand(String fallback, BukkitCommand command) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap)bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(fallback, (org.bukkit.command.Command)command);
        } catch (IllegalAccessException|IllegalArgumentException|NoSuchFieldException|SecurityException e) {
            e.printStackTrace();
        }
    }

    public class Command extends BukkitCommand {
        public Command() {
            super("lucchien");
            setDescription("lucchien Command");
            setPermission("lucchien.reload");
            setPermissionMessage("You don't have permission to do this command");
            setUsage("/lucchien");
        }

        public boolean execute(CommandSender sender, String lb, String[] args) {
            if (sender instanceof Player) {
                Player player1 = (Player)sender;
                if (player1.hasPermission(getPermission()) &&
                        !player1.isOp()) {
                    sender.sendMessage(getPermissionMessage());
                    return true;
                }
            }
            Player player = (Player)sender;
            if (args.length < 1) {
                player.sendMessage("&e/lucchien [item, reload]");
                return true;
            }
            if (args[0].equalsIgnoreCase("item")) {
                if (args.length < 2) {
                    player.sendMessage("&e/lucchien item [lucchien]");
                    return true;
                }
                if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                    player.sendMessage("&eHãy cầm một vật phẩm");
                    return true;
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                NBTItem nbtItem = NBTItem.get(item);
                ItemTag itemTag = new ItemTag(ItemTagAPI.LUCCHIEN_POWER.name(), args[1]);
                nbtItem.addTag(new ItemTag[] { itemTag });
                player.getInventory().setItemInHand(nbtItem.toItem());
                player.sendMessage("Tạo mới thành công");
            } else if (args[0].equalsIgnoreCase("reload")) {
                LucChien.getInstance().reload();
                player.sendMessage("Reload");
            }
            return true;
        }
    }
}
