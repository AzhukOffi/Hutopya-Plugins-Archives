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

public class XPBoost extends Enchantment implements Listener {

    public XPBoost(){
        super(new NamespacedKey(Main.plugin, "XPBoost"));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.XPBoost.getKey()))) {

            if (p.getGameMode() == GameMode.CREATIVE){
                if (p.getEquipment().getItemInMainHand().getType() == Material.DIAMOND_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.GOLDEN_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.IRON_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.STONE_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
                    e.setCancelled(true);
                }
                return;
            }
            if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 1){
                ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getExpToDrop() / 4);
            }
            if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 2){
                ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getExpToDrop() / 2);
            }
            if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 3){
                ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getExpToDrop() / 4 * 3);
            }
            if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 4){
                ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getExpToDrop());
            }
        }
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        if (e.getEntity().getKiller() != null){
            Player p = e.getEntity().getKiller();
            if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.XPBoost.getKey()))) {
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 1){
                    ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getDroppedExp() / 4);
                }
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 2){
                    ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getDroppedExp() / 2);
                }
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 3){
                    ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getDroppedExp() / 4 * 3);
                }
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.XPBoost.getKey())) == 4){
                    ((ExperienceOrb)p.getWorld().spawn(p.getLocation(), ExperienceOrb.class)).setExperience(e.getDroppedExp());
                }
            }
        }
    }

    @Override
    public String getName() {
        return "XPBoost";
    }

    @Override
    public int getMaxLevel() {
        return 4;
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
