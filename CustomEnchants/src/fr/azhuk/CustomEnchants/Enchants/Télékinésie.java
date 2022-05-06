package fr.azhuk.CustomEnchants.Enchants;

import fr.azhuk.CustomEnchants.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;

public class Télékinésie extends Enchantment implements Listener {

    public Télékinésie(){
        super(new NamespacedKey(Main.plugin, "Telekinesie"));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.Telekinesie.getKey()))) {

            if (p.getGameMode() == GameMode.CREATIVE){
                if (p.getEquipment().getItemInMainHand().getType() == Material.DIAMOND_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.GOLDEN_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.IRON_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.STONE_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
                    e.setCancelled(true);
                }
                return;
            }

            int count = 0;
            for (int i = 0; i < p.getInventory().getSize(); i++) {
                if (p.getInventory().getItem(i) == null)
                    count++;
            }
            if (count >= p.getInventory().getSize() - e.getBlock().getDrops().size()) {
                for (ItemStack drop : e.getBlock().getDrops()) {
                    p.getWorld().dropItem(e.getBlock().getLocation(), drop);
                }
                ((ExperienceOrb)p.getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class)).setExperience(e.getExpToDrop());
                e.setCancelled(false);
                return;
            } else {
                for (ItemStack drop : e.getBlock().getDrops()) {
                    p.getInventory().addItem(drop);
                }
                p.giveExp(e.getExpToDrop());
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if (e.getEntity().getKiller() != null){
            Player p = e.getEntity().getKiller();
            if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.Telekinesie.getKey()))) {
                int count = 0;
                for (int i = 0; i < p.getInventory().getSize(); i++) {
                    if (p.getInventory().getItem(i) == null)
                        count++;
                }
                if (count >= p.getInventory().getSize() - e.getDrops().size()) {
                    for (ItemStack drop : e.getDrops()) {
                        e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), drop);
                    }
                    ((ExperienceOrb)e.getEntity().getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getDroppedExp());
                    return;
                } else {
                    for (ItemStack drop : e.getDrops()) {
                        p.getInventory().addItem(drop);
                    }
                    p.giveExp(e.getDroppedExp());
                    e.setDroppedExp(0);
                    e.getDrops().clear();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Telekinesie";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }

}
