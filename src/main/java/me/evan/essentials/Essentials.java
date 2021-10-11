package me.evan.essentials;

import me.evan.essentials.Commands.General.*;
import me.evan.essentials.Events.BedSleep;
import me.evan.essentials.Events.JoinLeave;
import me.evan.essentials.Events.MessageConfig;
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
        getServer().getPluginManager().registerEvents(new MessageConfig(), this);

        // commands
        getCommand("nick").setExecutor(new Nick());
        getCommand("god").setExecutor(new God());
        getCommand("fly").setExecutor(new Fly());
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());

        getLogger().info(ChatColor.YELLOW + "Essentials enabled.");
    }
}
