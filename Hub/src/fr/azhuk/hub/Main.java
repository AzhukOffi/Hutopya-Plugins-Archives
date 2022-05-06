package fr.azhuk.hub;

import fr.azhuk.hub.commands.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(new HubListener(this), this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getCommand("settings").setExecutor(new Settings());
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hub" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hub" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");;
    }
}