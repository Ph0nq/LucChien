package me.ecomine.luchien.config;

import me.ecomine.luchien.LucChien;
import me.ecomine.luchien.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public interface ConfigAPI {
    public static final HashMap<String, File> configFile = new HashMap<>();

    public static final HashMap<String, FileConfiguration> configData = new HashMap<>();

    public static final ConfigEnum settings = ConfigEnum.settings;


    public static final ConfigEnum Message = ConfigEnum.Message;

    public enum ConfigEnum {
        Message("Message"),
        settings("settings");

        private final String name;

        ConfigEnum(String name) {
            this.name = name;
        }

        public void saveConfig() {
            try {
                ((FileConfiguration)ConfigAPI.configData.get(this.name)).save(ConfigAPI.configFile.get(this.name));
            } catch (IOException iOException) {}
        }

        public FileConfiguration getConfig() {
            return ConfigAPI.configData.get(this.name);
        }

        public String getName() {
            return this.name;
        }
    }

    static void loadConfig(String... configsName) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = configsName).length, b = 0; b < i; ) {
            String configName = arrayOfString[b];
            configFile.put(configName, new File(LucChien.getInstance().getDataFolder() + "/" + configName + ".yml"));
            if (!((File)configFile.get(configName)).exists()) {
                ((File)configFile.get(configName)).getParentFile().mkdir();
                try {
                    Exception exception2;
                    ((File)configFile.get(configName)).createNewFile();
                    Exception exception1 = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            configData.put(configName, YamlConfiguration.loadConfiguration(configFile.get(configName)));
            InputStream defaultConfigInputStream = LucChien.getInstance().getResource(String.valueOf(configName) + ".yml");
            if (defaultConfigInputStream == null)
                Utils.writeLog(new String[] { "Unable to load file " + configName + ".yml!" });
            b++;
        }
    }

    static void loadAllConfig() {
        byte b;
        int i;
        ConfigEnum[] arrayOfConfigEnum;
        for (i = (arrayOfConfigEnum = ConfigEnum.values()).length, b = 0; b < i; ) {
            ConfigEnum config = arrayOfConfigEnum[b];
            loadConfig(new String[] { config.getName() });
            b++;
        }
    }
}
