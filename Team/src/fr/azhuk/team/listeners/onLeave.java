package fr.azhuk.team.listeners;

import fr.azhuk.team.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class onLeave implements Listener {

    MySQL SQL = new MySQL();

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        SQL.deleteInviteToPlayer(e.getPlayer());
    }

}
