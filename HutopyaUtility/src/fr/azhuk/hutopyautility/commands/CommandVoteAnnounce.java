package fr.azhuk.hutopyautility.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVoteAnnounce implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if (args.length == 1){
            Bukkit.broadcastMessage("§8[§6Vote§8] §a" + args[0]);
            return true;
        }
        return false;

    }
}
