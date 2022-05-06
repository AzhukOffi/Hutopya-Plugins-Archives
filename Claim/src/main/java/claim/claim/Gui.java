package main.java.claim.claim;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gui {

    Player player;
    Inventory menu;

    public Gui(Player player, int size, String name){
        menu = Bukkit.createInventory(player, size, name);
        this.player = player;
    }


    public void open(){
        player.openInventory(menu);
    }


    //for adding items in temp inventory with custom names
    public void addItem(Material material, String name, String...lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        for (String lorecomments : lore) {

            metalore.add(lorecomments);
        }
        meta.setLore(metalore);
        item.setItemMeta(meta);
        menu.addItem(item);
    }

}
