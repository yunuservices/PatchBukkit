package org.basicplugins.basicPlugin;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class BasicPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("BasicPlugin has been enabled!");

        Server server = this.getServer();
        server
            .getPluginManager()
            .registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("BasicPlugin has been disabled!");
    }
}
