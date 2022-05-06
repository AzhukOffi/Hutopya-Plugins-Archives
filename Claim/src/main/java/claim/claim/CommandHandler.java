package main.java.claim.claim;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler {

    CommandEvent commandEvent;




    public CommandHandler(CommandEvent commandEvent){
        this.commandEvent = commandEvent;
    }



    public boolean processCommand(Server server, CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equals("claim")) {

            try{

                if (!args[0].isEmpty()) {
                    if (args[0].equals("menu")) {
                        commandEvent.openMenu((Player) sender);
                    } else if (args[0].equals("help")) {
                        commandEvent.help((Player) sender);
                    } else if (args[0].equals("check")) {
                        commandEvent.check((Player) sender);
                    } else if (args[0].equals("coop")) {
                        if (!args[1].isEmpty()) {
                            Player coopPlayer = server.getPlayer(args[1]);
                            commandEvent.coop((Player) sender, coopPlayer);
                        } else {
                            commandEvent.invalidCommand((Player) sender);
                        }
                    } else if (args[0].equals("uncoop")) {
                        if (!args[1].isEmpty()) {
                            Player uncoopPlayer = server.getPlayer(args[1]);
                            commandEvent.uncoop((Player) sender, uncoopPlayer);
                        } else {
                            commandEvent.invalidCommand((Player) sender);
                        }
                    } else if (args[0].equals("sell")) {
                        commandEvent.sell((Player) sender, ((Player) sender).getLocation());
                    } else if (args[0].equals("buy")) {
                        commandEvent.buy((Player) sender, ((Player) sender).getLocation());
                    }
                } else {
                    commandEvent.openMenu((Player) sender);
                }
            }catch(Exception e){
                commandEvent.openMenu((Player) sender);
            }
        }
        else{
            commandEvent.invalidCommand((Player)sender);
        }


        return false;
    }



}

