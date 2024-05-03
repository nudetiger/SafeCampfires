package me.rakkoon69.safecampfires.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Settings {

    private static Plugin p;
    private Settings() {}

    private static File cfile, mfile;
    private static FileConfiguration config, messages;

    public static void setUp(Plugin plugin) {
        p = plugin;

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        cfile = new File(p.getDataFolder(), "config.yml");
        if (!cfile.exists()) {
            p.saveResource("config.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(cfile);


        mfile = new File(p.getDataFolder(), "messages.yml");
        if (!mfile.exists()) {
            p.saveResource("messages.yml", true);
        }
        messages = YamlConfiguration.loadConfiguration(mfile);
    }

    public static void reloadAll() {
        reloadConfig();
        reloadMessages();
    }

    //Config
    public static FileConfiguration getConfig() {
        return config;
    }

    public static void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
            Message.log("&cCould not save config.yml!");
        }
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    //Messages
    public static FileConfiguration getMessages() {
        return messages;
    }

    public static void saveMessages() {
        try {
            messages.save(mfile);
        } catch (IOException e) {
            Message.log("&cCould not save messages.yml!");
        }
    }

    public static void reloadMessages() {
        messages = YamlConfiguration.loadConfiguration(mfile);
    }

}
