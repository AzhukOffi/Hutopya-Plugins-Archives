package fr.azhuk.banksurvie.listener;

import fr.azhuk.banksurvie.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BlockBreak implements Listener {

    Main plugin;

    public BlockBreak(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.IRON_BLOCK){
            Location loc = e.getBlock().getLocation();
            loc.add(0.5, 0, 0.5);
            List<Entity> nearbyEntities = (List<Entity>) e.getBlock().getLocation().getWorld().getNearbyEntities(loc, 0.5, 5, 0.5);


            ItemStack it = new ItemStack(Material.IRON_BLOCK, 1);
            ItemMeta itemMeta = it.getItemMeta();
            itemMeta.setDisplayName(plugin.messages.getString("messages.machine_name"));
            it.setItemMeta(itemMeta);
            for (Entity entity: nearbyEntities) {
                if (entity.getType() == EntityType.ARMOR_STAND && entity.getCustomName().equalsIgnoreCase(plugin.messages.getString("messages.machine_name"))) {
                    entity.remove();
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), it);
                    return;
                }
            }
        }
    }


}
