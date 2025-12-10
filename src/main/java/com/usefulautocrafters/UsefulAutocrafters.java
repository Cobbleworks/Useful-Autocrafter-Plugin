package com.usefulautocrafters;

import org.bukkit.plugin.java.JavaPlugin;

public class UsefulAutocrafters extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new AutocrafterListener(), this);
        getLogger().info("Useful-Autocrafters plugin enabled!");
        getLogger().info("Autocrafters will now preserve at least 1 item in each enabled slot");
    }

    @Override
    public void onDisable() {
        getLogger().info("Useful-Autocrafters plugin disabled!");
    }
}
