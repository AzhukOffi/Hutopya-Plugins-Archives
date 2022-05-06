package fr.azhuk.hoppers.listeners;

import fr.azhuk.hoppers.Methods;
import fr.azhuk.hoppers.MySQL;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockPlaceBreak implements Listener {

    MySQL SQL = new MySQL();
    Methods methods = new Methods();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getBlockPlaced().getType() == Material.HOPPER){
            switch (e.getItemInHand().getItemMeta().getDisplayName()){

                //création du hopper
                case "§6Hopper level §81":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 1);
                    return;
                case "§6Hopper level §82":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 2);
                    return;
                case "§6Hopper level §83":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 3);
                    return;
                case "§6Hopper level §84":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 4);
                    return;
                case "§6Hopper level §85":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 5);
                    return;
                case "§6Hopper level §86":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 6);
                    return;
                case "§6Hopper level §87":
                    SQL.createHopper(e.getBlockPlaced().getLocation(), 7);
                    return;
            }
            SQL.createHopper(e.getBlockPlaced().getLocation(), 1);
        }
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.HOPPER){

            //Supression du hopper
            int lvl = SQL.getLevel(e.getBlock().getLocation());
            SQL.deleteHopper(e.getBlock().getLocation(), lvl);

            //Création item & drop
            ItemStack hopper = new ItemStack(Material.HOPPER, 1);
            ItemMeta hoppermeta = hopper.getItemMeta();
            hoppermeta.setDisplayName("§6Hopper level §8" + lvl);
            hopper.setItemMeta(hoppermeta);
            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), hopper);
        }
    }



    @EventHandler
    public void onBlockForm(BlockFormEvent e){
        Location hopperLoc = e.getBlock().getLocation().add(0, -1,0 );
        Block hopper = hopperLoc.getBlock();
        if (hopper.getType() == Material.HOPPER){
            int id = SQL.getID(hopperLoc);
            int lvl = SQL.getLevel(hopperLoc);
            if (lvl >= 4){
                methods.autoBreak(e.getBlock().getLocation(), lvl);
            }
        }
    }


}
