package fr.azhuk.CustomEnchants;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        ItemStack un = new ItemStack(Material.DIAMOND_SWORD, 1);
        un.addUnsafeEnchantment(Main.XPBoost, 1);
        ItemMeta unmeta = un.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("XPBoost I");
        unmeta.setLore(lore);
        un.setItemMeta(unmeta);
        p.getInventory().addItem(un);

        ItemStack deux = new ItemStack(Material.DIAMOND_SWORD, 1);
        deux.addUnsafeEnchantment(Main.XPBoost, 2);
        ItemMeta deuxmeta = deux.getItemMeta();
        lore = new ArrayList<>();
        lore.add("XPBoost II");
        deuxmeta.setLore(lore);
        deux.setItemMeta(deuxmeta);
        p.getInventory().addItem(deux);

        ItemStack trois = new ItemStack(Material.DIAMOND_SWORD, 1);
        trois.addUnsafeEnchantment(Main.XPBoost, 3);
        ItemMeta troismeta = trois.getItemMeta();
        lore = new ArrayList<>();
        lore.add("XPBoost III");
        troismeta.setLore(lore);
        trois.setItemMeta(troismeta);
        p.getInventory().addItem(trois);


        ItemStack quatre = new ItemStack(Material.DIAMOND_SWORD, 1);
        quatre.addUnsafeEnchantment(Main.XPBoost, 4);
        ItemMeta quatremeta = quatre.getItemMeta();
        lore = new ArrayList<>();
        lore.add("XPBoost IIII");
        quatremeta.setLore(lore);
        quatre.setItemMeta(quatremeta);
        p.getInventory().addItem(quatre);
    }


    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            Inventory inv = e.getInventory();

            if (inv instanceof AnvilInventory){
                AnvilInventory anvil = (AnvilInventory)inv;
                InventoryView view = e.getView();
                int rawSlot = e.getRawSlot();
                if (rawSlot == view.convertSlot(rawSlot)){
                    if (rawSlot == 2){
                        ItemStack[] items = anvil.getContents();
                        ItemStack item1 = items[0];
                        ItemStack item2 = items[1];
                        if(item1 != null && item2 != null) {
                            ItemStack item3 = e.getCurrentItem();
                            if(item3 != null) {
                                ItemMeta meta = item3.getItemMeta();
                                if(meta != null){
                                    if (item1.getEnchantments().containsKey(Enchantment.getByKey(Main.Telekinesie.getKey()))){
                                        item3.addUnsafeEnchantment(Main.Telekinesie, 1);
                                    }
                                    if (item1.getEnchantments().containsKey(Enchantment.getByKey(Main.OreAmplifier.getKey()))){
                                        item3.addUnsafeEnchantment(Main.OreAmplifier, 1);
                                    }
                                    if (item1.getEnchantments().containsKey(Enchantment.getByKey(Main.Lighting.getKey()))){
                                        item3.addUnsafeEnchantment(Main.Lighting, 1);
                                    }
                                    if (item1.getEnchantments().containsKey(Enchantment.getByKey(Main.XPBoost.getKey()))){
                                        item3.addUnsafeEnchantment(Main.XPBoost, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
