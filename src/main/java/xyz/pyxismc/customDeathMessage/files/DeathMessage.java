package xyz.pyxismc.customDeathMessage.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Bukkit;


import java.io.File;
import java.io.IOException;

public class DeathMessage {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CustomDeathMessage").getDataFolder(), "deathMessages.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e) {
               //ow
            }

        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return customFile;
    }
    public static void save(){
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Couldn't save deathMessages.yml");
        }
    }
    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
