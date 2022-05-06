package fr.azhuk.banksurvie.listener;

import fr.azhuk.banksurvie.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.EulerAngle;

import java.util.List;

public class BlockPlace implements Listener {

    Main plugin;

    public BlockPlace(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if (e.getBlockPlaced().getType() == Material.IRON_BLOCK && e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.machine_name"))){
            if (e.isCancelled()){
                return;
            }
            Location loc = e.getBlock().getLocation();
            BlockFace face = e.getPlayer().getFacing().getOppositeFace();

            Location as1Loc = e.getBlock().getLocation();
            loc.add(0.5, 0, 0.5);
            List<Entity> nearbyEntities = (List<Entity>) loc.getWorld().getNearbyEntities(loc, 0.5, 500, 0.5);
            for (Entity entity: nearbyEntities){
                if (entity.getType() == EntityType.ARMOR_STAND && entity.getCustomName().equalsIgnoreCase(plugin.messages.getString("messages.machine_name"))){
                    e.getPlayer().sendMessage(plugin.messages.getString("messages.machine_one_XY"));
                    e.setCancelled(true);
                    return;
                }
            }

            loc.setZ(Math.floor(loc.getZ()) + 0.5);
            loc.setX(Math.floor(loc.getX()) + 0.5);

            loc.setYaw(0);
            loc.setPitch(0);

            loc.getBlock().setType(Material.IRON_BLOCK);


            as1Loc.setZ(Math.floor(loc.getZ()) + 0.5);
            as1Loc.setX(Math.floor(loc.getX()) + 0.5);
            as1Loc.setY(loc.getY() - 0.5);
            as1Loc.setDirection(face.getDirection());
            ArmorStand as1 = (ArmorStand) as1Loc.getWorld().spawnEntity(as1Loc, EntityType.ARMOR_STAND);
            as1.setHelmet(Main.getHead("monitor"));
            as1.setHeadPose(new EulerAngle(-0.9,0,0));
            as1.setInvulnerable(true);
            as1.setCanPickupItems(false);
            as1.setGravity(false);
            as1.setArms(false);
            as1.setCustomName(plugin.messages.getString("messages.machine_name"));
            as1.setCustomNameVisible(true);
            as1.setVisible(false);
        }
    }


}
