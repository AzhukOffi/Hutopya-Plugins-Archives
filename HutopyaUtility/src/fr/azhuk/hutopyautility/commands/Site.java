package fr.azhuk.hutopyautility.commands;

import fr.azhuk.hutopyautility.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Site implements CommandExecutor {

    Main plugin;

    public Site(Main instance) {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(plugin.getConfig().getString("message.info_site"));
            return true;
        }
        return false;
    }
}