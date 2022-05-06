package fr.azhuk.hoppers.listeners;

import fr.azhuk.hoppers.MySQL;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryDrag implements Listener {

    MySQL SQL = new MySQL();

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase("§6Configuration du filtre")) {
            if (e.getInventory().getHolder() == null) {
                e.setCancelled(true);


                    /*String idTemp = "";
                    for (char c : e.getInventory().getItem(35).getItemMeta().getDisplayName().toCharArray()){
                        if (Character.isDigit(c)){
                            idTemp = idTemp + c;
                        }
                    }
                    int id_hopper = Integer.valueOf(idTemp);

                    for(Map.Entry<Integer, ItemStack> entry : e.getNewItems().entrySet()) {
                        int slot = entry.getKey();
                        ItemStack it = entry.getValue();

                        if (slot < 35){
                            switch (slot){
                                case 0:
                                    slot = 1;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 1:
                                    slot = 2;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 3:
                                    slot = 1;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 4:
                                    slot = 2;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 5:
                                    slot = 3;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 7:
                                    slot = 1;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 8:
                                    slot = 2;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 9:
                                    slot = 3;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 10:
                                    slot = 4;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 12:
                                    slot = 4;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 13:
                                    slot = 5;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 14:
                                    slot = 6;
                                    SQL.updateRejets(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 16:
                                    slot = 3;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 17:
                                    slot = 4;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 18:
                                    slot = 5;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 19:
                                    slot = 6;
                                    SQL.updateWhitelist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 25:
                                    slot = 5;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                                case 26:
                                    slot = 6;
                                    SQL.updateBlacklist(it.getType().toString(), id_hopper, slot);
                                    break;
                            }
                        }
                    }*/
            }
        }
    }
}
