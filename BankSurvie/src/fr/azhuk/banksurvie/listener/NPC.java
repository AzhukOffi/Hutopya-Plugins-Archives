package fr.azhuk.banksurvie.listener;

import fr.azhuk.banksurvie.Main;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NPC implements Listener {


    Main plugin;

    public NPC(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onRightClickNPC(NPCRightClickEvent e) {
        if (e.getNPC().getName().equalsIgnoreCase(plugin.messages.getString("messages.npc_name"))) {
            Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("messages.gui_banquier.name"));

            //verre gris
            ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta grayglassmeta = grayglass.getItemMeta();
            grayglassmeta.setDisplayName(plugin.messages.getString("messages.gui_banquier.glass_name"));
            grayglass.setItemMeta(grayglassmeta);

            //déposer
            ItemStack deposer = new ItemStack(Material.GREEN_CONCRETE, 1);
            ItemMeta deposerMeta = deposer.getItemMeta();
            deposerMeta.setDisplayName(plugin.messages.getString("messages.gui_banquier.depot_name"));
            deposer.setItemMeta(deposerMeta);

            //retirer
            ItemStack retirer = new ItemStack(Material.RED_CONCRETE, 1);
            ItemMeta retirermeta = retirer.getItemMeta();
            retirermeta.setDisplayName(plugin.messages.getString("messages.gui_banquier.retrait_name"));
            retirer.setItemMeta(retirermeta);

            //emeraude
            ItemStack emeraude = new ItemStack(Material.EMERALD, 1);
            ItemMeta emeraudemeta = emeraude.getItemMeta();
            emeraudemeta.setDisplayName(plugin.messages.getString("messages.gui_banquier.infos_name"));
            ArrayList<String> lore = new ArrayList<String>();
            System.out.println(plugin.messages.getConfigurationSection("messages.gui_banquier.lore").getKeys(false).size());
            for (String loreList : plugin.messages.getConfigurationSection("messages.gui_banquier.lore.").getKeys(false)) {
                String msg = plugin.messages.getString("messages.gui_banquier.lore." + loreList);
                lore.add(msg);
            }
            emeraudemeta.setLore(lore);
            emeraude.setItemMeta(emeraudemeta);

            inv.setItem(0, grayglass);
            inv.setItem(1, grayglass);
            inv.setItem(2, grayglass);
            inv.setItem(3, grayglass);
            inv.setItem(4, grayglass);
            inv.setItem(5, grayglass);
            inv.setItem(6, grayglass);
            inv.setItem(7, grayglass);
            inv.setItem(8, grayglass);
            inv.setItem(9, grayglass);
            inv.setItem(10, grayglass);
            inv.setItem(11, grayglass);
            inv.setItem(12, grayglass);
            inv.setItem(13, grayglass);
            inv.setItem(14, grayglass);
            inv.setItem(15, grayglass);
            inv.setItem(16, grayglass);
            inv.setItem(17, grayglass);
            inv.setItem(18, grayglass);
            inv.setItem(19, retirer);
            inv.setItem(20, grayglass);
            inv.setItem(21, grayglass);
            inv.setItem(22, emeraude);
            inv.setItem(23, grayglass);
            inv.setItem(24, grayglass);
            inv.setItem(25, deposer);
            inv.setItem(26, grayglass);
            inv.setItem(27, grayglass);
            inv.setItem(28, grayglass);
            inv.setItem(29, grayglass);
            inv.setItem(30, grayglass);
            inv.setItem(31, grayglass);
            inv.setItem(32, grayglass);
            inv.setItem(33, grayglass);
            inv.setItem(34, grayglass);
            inv.setItem(35, grayglass);
            inv.setItem(36, grayglass);
            inv.setItem(37, grayglass);
            inv.setItem(38, grayglass);
            inv.setItem(39, grayglass);
            inv.setItem(40, grayglass);
            inv.setItem(41, grayglass);
            inv.setItem(42, grayglass);
            inv.setItem(43, grayglass);
            inv.setItem(44, grayglass);

            e.getClicker().openInventory(inv);
        }


    }
}
