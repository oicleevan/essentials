package me.evan.essentials;

import me.evan.essentials.Commands.Nick;
import me.evan.essentials.Events.BedSleep;
import me.evan.essentials.Events.JoinLeave;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Essentials extends JavaPlugin {

    @Override
    public void onEnable() {
        // Startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // event listeners
        getServer().getPluginManager().registerEvents(new JoinLeave(), this); // join and leave messages
        getServer().getPluginManager().registerEvents(new BedSleep(), this); // bed skip stuff.

        // commands
        getCommand("nick").setExecutor(new Nick());

        getLogger().info(ChatColor.YELLOW + "Essentials enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
