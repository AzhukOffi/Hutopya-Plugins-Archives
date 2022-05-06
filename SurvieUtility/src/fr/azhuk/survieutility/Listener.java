package fr.azhuk.survieutility;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class Listener implements org.bukkit.event.Listener {

    Main plugin;

    public Listener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
        String args[] = e.getMessage().split(" ");
        if(args[0].equalsIgnoreCase("/plugins") || args[0].equalsIgnoreCase("/pl")){
            plugin.plPlayer(e.getPlayer());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (e.getItemDrop().getItemStack().getType() == Material.NETHER_STAR && e.getItemDrop().getItemStack().hasItemMeta() && e.getItemDrop().getItemStack().getItemMeta().hasDisplayName() && e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§6Menu")){
            e.getItemDrop().remove();
            e.getPlayer().sendMessage("§7[§c!§7] §cTu ne peux pas jeter cet item, mais tu peux le désactiver dans le menu");
            return;
        }
        e.setCancelled(false);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Menu")){
            e.setCancelled(true);
            p.closeInventory();
            p.chat("/menu");
        }
    }
    @EventHandler
    public void onStart(ServerLoadEvent e){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "papi reload");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (e.getBlock().getWorld().getName().equalsIgnoreCase("minage")){
            int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
            if (randomNum == 1){
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                int i = 0;
                while (i <= e.getExpToDrop() && e.getExpToDrop() != 0){
                    e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class);
                    i++;
                }
                for (ItemStack drop : e.getBlock().getDrops()){
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drop);
                }
            }
        }
    }
}