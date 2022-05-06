package fr.azhuk.team.listeners;

import fr.azhuk.team.Main;
import fr.azhuk.team.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {


    MySQL SQL = new MySQL();

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (SQL.mysqlWork()){
            if (!SQL.transferExists(e.getPlayer().getUniqueId())){
                SQL.createTransfer(e.getPlayer().getUniqueId());
            }
            if (!SQL.playerExists(e.getPlayer().getUniqueId())){
                SQL.createPlayer(e.getPlayer());
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Team" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Creation du joueur " + e.getPlayer().getName());
            }
        } else {
            plugin.mysqlSetup();
            try {
                Thread.sleep(500);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
            if (!SQL.transferExists(e.getPlayer().getUniqueId())){
                SQL.createTransfer(e.getPlayer().getUniqueId());
            }
            if (!SQL.playerExists(e.getPlayer().getUniqueId())){
                SQL.createPlayer(e.getPlayer());
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Team" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Creation du joueur " + e.getPlayer().getName());
            }
        }
    }

}
