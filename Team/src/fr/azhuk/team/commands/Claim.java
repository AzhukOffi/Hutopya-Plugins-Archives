package fr.azhuk.team.commands;

import fr.azhuk.team.Methods;
import fr.azhuk.team.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Claim implements CommandExecutor {

    MySQL SQL = new MySQL();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){

            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("buy")){
                    if (!SQL.claimExists(p.getLocation().getChunk(), p.getWorld())){
                        int team = SQL.getTeams(p).get(0);
                        if (team == 0){
                            p.sendMessage("aucune team");
                            return false;
                        }
                        if (SQL.getRank(p.getUniqueId().toString()) > 1 || SQL.isOwner(p.getUniqueId(), team)){
                            SQL.createClaim(p.getLocation().getChunk(), p.getWorld(), team);
                            p.sendMessage("Claim acheté");
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
