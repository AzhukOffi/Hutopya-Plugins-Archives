package fr.azhuk.hutopyautility.commands;

import fr.azhuk.hutopyautility.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickAFK implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length > 0){
            Bukkit.getPlayer(args[0]).kickPlayer("Vous étiez AFK");
        }
        return false;
    }
}
