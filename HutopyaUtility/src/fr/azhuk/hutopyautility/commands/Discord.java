package fr.azhuk.hutopyautility.commands;

import fr.azhuk.hutopyautility.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Discord implements CommandExecutor {

    Main plugin;

    public Discord(Main instance) {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(plugin.getConfig().getString("message.info_discord"));
            return true;
        }
        return false;
    }
}
