package net.toiletmc.shart;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shart extends JavaPlugin {
    public static Shart instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("shart").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
