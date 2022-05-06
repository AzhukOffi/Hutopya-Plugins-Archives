package fr.azhuk.team.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("team")) {
            if (args.length == 1){
                List<String> List = new ArrayList<>();
                List<String> LS = Arrays.asList("leave", "accept", "invite", "owner", "create", "members", "rank", "disband");

                for (String firstArg: LS){
                    if (firstArg.toLowerCase().startsWith(args[0])){
                        List.add(firstArg);
                    }
                }

                return List;
            }
            if (args.length > 2){
                if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("owner") || args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("members") || args[0].equalsIgnoreCase("rank")){
                    List<String> List = new ArrayList<>();

                    for (Player firstArg: Bukkit.getOnlinePlayers()){
                        if (args.length == 3){
                            if (firstArg.getName().toLowerCase().startsWith(args[1])){
                                List.add(firstArg.getName());
                            }
                        } else {
                            List.add(firstArg.getName());
                        }

                    }

                    return List;
                }

            }
        }

        return null;


    }

}
