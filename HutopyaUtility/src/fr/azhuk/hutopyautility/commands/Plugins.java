package fr.azhuk.hutopyautility.commands;

import fr.azhuk.hutopyautility.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Plugins implements CommandExecutor {

    Main plugin;

    public Plugins(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            plugin.plPlayer(p);
            return true;
        } else {
            plugin.plConsole();
            return true;
        }
    }
}
