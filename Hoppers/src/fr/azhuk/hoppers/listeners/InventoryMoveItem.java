package fr.azhuk.hoppers.listeners;

import fr.azhuk.hoppers.Methods;
import fr.azhuk.hoppers.MySQL;
import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryMoveItem implements Listener {

    MySQL SQL = new MySQL();
    Methods methods = new Methods();

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent e){
        if (e.getDestination().getType() == InventoryType.HOPPER) {
            Hopper hopper = (Hopper) e.getDestination().getHolder();
            int id = SQL.getID(hopper.getLocation());
            String[] whitelist = SQL.getWhitelist(id);

            boolean isEmptyWhitelist = true;
            for (String w: whitelist){
                if (!w.equalsIgnoreCase("none")){
                    isEmptyWhitelist = false;
                }
            }
            if (isEmptyWhitelist){
                //blacklist & rejets

                String[] blacklist = SQL.getBlacklist(id);
                boolean isEmptyBlacklist = true;

                for (String b: blacklist){
                    if (!b.equalsIgnoreCase("none")){
                        isEmptyBlacklist = false;
                    }
                }
                if (!isEmptyBlacklist){
                    for (String it: blacklist){
                        Material item = Material.getMaterial(it);
                        if (e.getItem().getType() == item){
                            e.setCancelled(true);
                            return;
                        }
                    }
                }

                String[] rejets = SQL.getRejets(id);
                boolean isEmptyRejets = true;

                for (String r: rejets){
                    if (!r.equalsIgnoreCase("none")){
                        isEmptyRejets = false;
                    }
                }
                if (!isEmptyRejets){
                    for (String it: rejets){
                        Material item = Material.getMaterial(it);
                        if (e.getItem().getType() == item){
                            methods.Rejets(e.getDestination(), item);
                            return;
                        }
                    }
                }
                e.setCancelled(false);
            } else {
                //System.out.println("1");
                //whitelist
                boolean isWhitelist = false;
                for (String it: whitelist){
                    Material item = Material.getMaterial(it);
                    if (e.getItem().getType() == item){
                        isWhitelist = true;
                    }
                }
                if (!isWhitelist){
                    e.setCancelled(true);
                }
            }
        }
    }
}
