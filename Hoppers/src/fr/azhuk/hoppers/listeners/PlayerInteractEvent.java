package fr.azhuk.hoppers.listeners;

import com.songoda.skyblock.api.SkyBlockAPI;
import com.songoda.skyblock.api.island.Island;
import fr.azhuk.hoppers.Methods;
import fr.azhuk.hoppers.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerInteractEvent implements Listener {

    MySQL SQL = new MySQL();
    Methods methods = new Methods();

    @EventHandler
    public void onClick(org.bukkit.event.player.PlayerInteractEvent e) {
        Player p = e.getPlayer();

        //clique gauche
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            //if hopper
            if (e.getClickedBlock().getType() == Material.HOPPER) {
                //Vérifier si il essai de le casser (via le sneak)
                if (!p.isSneaking()) {
                    e.setCancelled(true);
                } else {
                    e.setCancelled(false);
                    return;
                }

                //si le hopper existe
                if (!SQL.hoppperExists(e.getClickedBlock().getLocation())) {
                    p.sendMessage("§8[§6Hoppers§8] §cErreur: Hopper non enregistré dans nos données");
                    return;
                }

                //Recup l'inv

                Island is = SkyBlockAPI.getIslandManager().getIslandPlayerAt(p);
                if (is != null) {
                    if (SkyBlockAPI.getIslandManager().isPlayerAtIsland(is, p) || p.hasPermission("Hoppers.admin")) {
                        Inventory inv = methods.invHopper(SQL.getID(e.getClickedBlock().getLocation()), p);
                        //ouvrir l'inv
                        p.openInventory(inv);
                    }
                }

            }
        }
    }
}