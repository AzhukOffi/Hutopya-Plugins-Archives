package fr.azhuk.CustomEnchants.Enchants;

import fr.azhuk.CustomEnchants.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class OreAmplifier extends Enchantment implements Listener {

    public OreAmplifier(){
        super(new NamespacedKey(Main.plugin, "OreAmplifier"));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (p.getEquipment().getItemInMainHand().getEnchantments().containsKey(Enchantment.getByKey(Main.OreAmplifier.getKey()))) {
            if (p.getGameMode() == GameMode.CREATIVE){
                e.setCancelled(false);
                return;
            }
            if (p.getEquipment().getItemInMainHand().getType() == Material.DIAMOND_PICKAXE || p.getEquipment().getItemInMainHand().getType() == Material.GOLDEN_PICKAXE || p.getEquipment().getItemInMainHand().getType() == Material.IRON_PICKAXE || p.getEquipment().getItemInMainHand().getType() == Material.STONE_PICKAXE || p.getEquipment().getItemInMainHand().getType() == Material.WOODEN_PICKAXE){
                int random = (int)(Math.random() * 100);
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.OreAmplifier.getKey())) == 1){
                    if (random < 5){
                        e.setCancelled(true);
                        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND_BLOCK, e.getBlock().getDrops().size()));
                        p.giveExp(e.getExpToDrop()*2);
                        e.getBlock().setType(Material.AIR);
                    } else {
                        e.setCancelled(false);
                    }
                }
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.OreAmplifier.getKey())) == 2){
                    if (random < 25){
                        e.setCancelled(true);
                        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND_BLOCK, e.getBlock().getDrops().size()));
                        p.giveExp(e.getExpToDrop()*2);
                        e.getBlock().setType(Material.AIR);
                    } else {
                        e.setCancelled(false);
                    }
                }
                if (p.getEquipment().getItemInMainHand().getEnchantments().get(Enchantment.getByKey(Main.OreAmplifier.getKey())) == 3){
                    if (random < 35){
                        e.setCancelled(true);
                        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND_BLOCK, e.getBlock().getDrops().size()));
                        p.giveExp(e.getExpToDrop()*2);
                        e.getBlock().setType(Material.AIR);
                    } else {
                        e.setCancelled(false);
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Ore Amplifier";
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
