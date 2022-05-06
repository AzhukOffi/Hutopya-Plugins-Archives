package fr.azhuk.banksurvie.listener;

import fr.azhuk.banksurvie.Main;
import fr.azhuk.banksurvie.Methods;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorStandManip implements Listener {

    Methods methods = new Methods();

    Main plugin;

    public ArmorStandManip(Main instance) {
        plugin = instance;
    }


    @EventHandler
    public void ArmorStandManip(PlayerArmorStandManipulateEvent e){
        if (e.getRightClicked().getCustomName().equalsIgnoreCase(plugin.messages.getString("messages.machine_name"))) {
            Player p = e.getPlayer();
            e.setCancelled(true);
            p.openInventory(methods.Echanges());
        }
    }

}
