package net.playwolf.minescript;

import org.bukkit.plugin.java.JavaPlugin;

public final class MineScript extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("MineScript enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MineScript disabled!");
    }
}
