package fr.azhuk.team.listeners;

import fr.azhuk.team.Main;
import fr.azhuk.team.MySQL;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class Interact implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    MySQL SQL = new MySQL();

    @EventHandler
    public void interact(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (plugin.interactable.isEmpty()){
            for (String interactableList: plugin.interactable_block.getStringList("block")){
                String type_id = plugin.interactable_block.getString("block." + interactableList);
                plugin.interactable.add(Material.getMaterial(type_id));
            }
        }


        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR){
            return;
        }
        if (!SQL.claimExists(e.getClickedBlock().getLocation().getChunk(), p.getWorld())){
            return;
        }
        Material clickedBlockType = e.getClickedBlock().getType();
        if (plugin.interactable.contains(clickedBlockType)){
            if (SQL.getTeams(p).contains(SQL.getClaimOwner(e.getClickedBlock().getLocation().getChunk(), p.getWorld()))){
                e.setCancelled(false);
                return;
            } else {
                e.setCancelled(true);
                p.sendMessage("Erreur: pas ton chunk");
                return;
            }
        }
    }
}
