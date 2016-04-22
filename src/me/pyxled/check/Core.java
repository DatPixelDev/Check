package me.pyxled.check;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Pyxled Development 2016 (c)
 * File created: 02/24/2016
 */
public class Core extends JavaPlugin {
    private static Core instance;
    @Override
    public void onEnable() {
        instance = this;
        on();
    }
    public void on(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
        getCommand("check").setExecutor(new CheckCommand());
    }

    public static Core getInstance() {
        return instance;
    }
}
