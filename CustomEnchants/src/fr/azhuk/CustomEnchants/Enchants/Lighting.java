package fr.azhuk.CustomEnchants.Enchants;

import fr.azhuk.CustomEnchants.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Lighting extends Enchantment implements Listener {

    public Lighting(){
        super(new NamespacedKey(Main.plugin, "Lighting"));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.Lighting.getKey()))) {
                if (p.getEquipment().getItemInMainHand().getType() == Material.DIAMOND_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.GOLDEN_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.IRON_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.STONE_SWORD || p.getEquipment().getItemInMainHand().getType() == Material.WOODEN_SWORD) {
                    if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.Lighting.getKey())) == 1) {
                        Random generator = new Random();
                        double x = generator.nextInt(5);
                        double z = generator.nextInt(5);
                        System.out.println(x + z);

                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation().add(x, 0, z));
                    }
                    if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.Lighting.getKey())) == 2) {
                        Random generator = new Random();
                        double x = generator.nextInt(3);
                        double z = generator.nextInt(3);
                        System.out.println(x + z);

                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation().add(x, 0, z));
                    }
                    if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.Lighting.getKey())) == 3) {
                        p.getWorld().strikeLightning(p.getLocation());
                        e.getEntity().getWorld().strikeLightning(e.getEntity().getLocation());
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Lighting";
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
