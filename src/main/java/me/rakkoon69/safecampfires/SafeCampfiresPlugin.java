package me.rakkoon69.safecampfires;

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public final class SafeCampfiresPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().addPermission(new Permission("safecampfires.use"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
