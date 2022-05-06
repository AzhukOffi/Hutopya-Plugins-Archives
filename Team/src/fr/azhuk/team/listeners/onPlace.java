package fr.azhuk.team.listeners;

import fr.azhuk.team.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onPlace implements Listener {

    MySQL SQL = new MySQL();

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if (!SQL.claimExists(e.getBlockPlaced().getLocation().getChunk(), p.getWorld())){
            return;
        }
        if (SQL.getTeams(p).contains(SQL.getClaimOwner(e.getBlockPlaced().getLocation().getChunk(), p.getWorld()))){
            e.setCancelled(false);
            return;
        } else {
            e.setCancelled(true);
            p.sendMessage("Erreur: pas ton chunk");
            return;
        }
    }

}
