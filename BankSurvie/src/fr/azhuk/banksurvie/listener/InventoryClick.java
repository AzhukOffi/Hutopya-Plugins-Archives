package fr.azhuk.banksurvie.listener;

import fr.azhuk.banksurvie.Main;
import fr.azhuk.banksurvie.Methods;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

public class InventoryClick implements Listener {

    Main plugin;

    public InventoryClick(Main instance) {
        plugin = instance;
    }

    private Economy economy = plugin.economy;

    Methods methods = new Methods();

    @EventHandler
    public void InvClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui_banquier.name"))){
            if (current == null) return;
            e.setCancelled(true);

            if (current.getType() == Material.RED_CONCRETE && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_banquier.retrait_name"))){
                plugin.data.set(p.getName() + ".emerald", 0);
                plugin.data.set(p.getName() + ".block", 0);
                plugin.data.set(p.getName() + ".switch", false);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Retrait("emerald", p);
                p.openInventory(Newinv);
            }
            if (current.getType() == Material.GREEN_CONCRETE && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_banquier.depot_name"))){
                plugin.data.set(p.getName() + ".emerald", 0);
                plugin.data.set(p.getName() + ".block", 0);
                plugin.data.set(p.getName() + ".switch", false);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Depot("emerald", p);
                p.openInventory(Newinv);
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.machine_name"))) {
            if (current == null) return;
            e.setCancelled(true);
            if (current.getType() == Material.EMERALD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.machine_gui.convert_to_emerald"))) {
                p.closeInventory();
                p.openInventory(methods.toEmerald());
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.machine_gui.convert_to_emerald"))) {
            if (current == null) return;
            e.setCancelled(true);
            if (current.getType() == Material.EMERALD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.machine_gui.convert_from_emerald_plus"))) {
                ItemStack[] invContents = p.getInventory().getContents();
                ItemStack it = new ItemStack(Material.EMERALD, 1);
                ItemMeta im = it.getItemMeta();
                im.setDisplayName(plugin.messages.getString("messages.item_name.emerald_plus"));
                it.setItemMeta(im);
                it.addUnsafeEnchantment(Main.Glow, 1);
                for(ItemStack item:invContents){
                    if (item != null){
                        if (item == it){
                            break;
                        }
                    }
                }
                p.getInventory().removeItem(it);
                p.getInventory().addItem(new ItemStack(Material.EMERALD, 9));
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.name"))){
            if (current == null) return;
            e.setCancelled(true);
            if (current.getType() == Material.EMERALD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.devise_emeraude"))) {
                plugin.data.set(p.getName() + ".switch", true);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Retrait("block", p);
                p.openInventory(Newinv);
            }

            if (current.getType() == Material.EMERALD_BLOCK && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.devise_block"))) {
                plugin.data.set(p.getName() + ".switch", true);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Retrait("emerald", p);
                p.openInventory(Newinv);
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.ajouter_1"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value++;
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value++;
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("emerald", p);
                    p.openInventory(Newinv);
                }

            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.ajouter_10"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value += 10;
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value += 10;
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.retirer_10"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value -= 10;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value -= 10;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.retirer_1"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value--;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value--;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Retrait("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.total_name"))) {
                if (plugin.data.getInt(p.getName() + ".block") == 0 && plugin.data.getInt(p.getName() + ".emerald") == 0){
                    p.closeInventory();
                    p.sendMessage(plugin.messages.getString("messages.error.all_0"));
                    return;
                }
                double block = plugin.data.getDouble(p.getName() + ".block");
                double emerald = plugin.data.getDouble(p.getName() + ".emerald");

                if (economy.hasAccount(p)){
                    if (economy.getBalance(p) >= block*9 + emerald){
                        economy.withdrawPlayer(p, block * 9 + emerald);
                        /*int count = 0;
                        for (int i = 0; i < inv.getContents().getSize(); i++) {
                            if (inv.getContents()[i] == null)
                                count++;
                        }*/
                        p.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK, plugin.data.getInt(p.getName() + ".block")));
                        p.getInventory().addItem(new ItemStack(Material.EMERALD, plugin.data.getInt(p.getName() + ".emerald")));
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.messages.getString("messages.error.not_enough"));
                    }
                 } else {
                    p.sendMessage(plugin.messages.getString("messages.error.no_account"));
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_retrait.back_name"))) {
                p.closeInventory();
            }
        }

        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.name"))){
            if (current == null) return;
            e.setCancelled(true);
            if (current.getType() == Material.EMERALD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.devise_emeraude"))) {
                plugin.data.set(p.getName() + ".switch", true);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Depot("block", p);
                p.openInventory(Newinv);
            }

            if (current.getType() == Material.EMERALD_BLOCK && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.devise_block"))) {
                plugin.data.set(p.getName() + ".switch", true);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Inventory Newinv = methods.Depot("emerald", p);
                p.openInventory(Newinv);
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.ajouter_1"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value++;
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value++;
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("emerald", p);
                    p.openInventory(Newinv);
                }

            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.ajouter_10"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value += 10;
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value += 10;
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.retirer_10"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value -= 10;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value -= 10;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.retirer_1"))) {
                if (inv.getItem(22).getType() == Material.EMERALD_BLOCK){
                    int value = plugin.data.getInt(p.getName() + ".block");
                    value--;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".block", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("block", p);
                    p.openInventory(Newinv);
                }
                if (inv.getItem(22).getType() == Material.EMERALD){
                    int value = plugin.data.getInt(p.getName() + ".emerald");
                    value--;
                    if (value < 0){
                        p.sendMessage(plugin.messages.getString("messages.error.under_0"));
                        return;
                    }
                    plugin.data.set(p.getName() + ".emerald", value);

                    plugin.data.set(p.getName() + ".switch", true);
                    try {
                        plugin.data.save(plugin.getFile("data"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Inventory Newinv = methods.Depot("emerald", p);
                    p.openInventory(Newinv);
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui_depot.total_name"))) {
                if (plugin.data.getInt(p.getName() + ".block") == 0 && plugin.data.getInt(p.getName() + ".emerald") == 0){
                    p.closeInventory();
                    p.sendMessage(plugin.messages.getString("messages.error.all_0"));
                    return;
                }
                double block = plugin.data.getDouble(p.getName() + ".block");
                double emerald = plugin.data.getDouble(p.getName() + ".emerald");

                if (economy.hasAccount(Bukkit.getOfflinePlayer(p.getName()))){
                    /*int count = 0;
                    for (int i = 0; i < inv.getContents().getSize(); i++) {
                        if (inv.getContents()[i] == null)
                            count++;
                    }*/
                    double TotalEmerald = 0;
                    double TotalEmeraldBlock = 0;
                    ItemStack[] invContents = p.getInventory().getContents();
                    for(ItemStack item:invContents){
                        if (item != null){
                            if (item.getType() == Material.EMERALD){
                                TotalEmerald += item.getAmount();
                            }
                            if (item.getType() == Material.EMERALD_BLOCK){
                                TotalEmeraldBlock += item.getAmount();
                            }
                        }
                        if (TotalEmerald >= emerald && TotalEmeraldBlock >= block){
                            break;
                        }
                    }
                    if (TotalEmerald >= emerald && TotalEmeraldBlock >= block){
                        economy.depositPlayer(Bukkit.getOfflinePlayer(p.getName()), block * 9 + emerald);
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD_BLOCK, plugin.data.getInt(p.getName() + ".block")));
                        p.getInventory().removeItem(new ItemStack(Material.EMERALD, plugin.data.getInt(p.getName() + ".emerald")));
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.messages.getString("messages.error.not_enough"));
                        p.closeInventory();
                    }
                } else {
                    p.sendMessage(plugin.messages.getString("messages.error.no_account"));
                }
            }
            if (current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§cFermer")) {
                p.closeInventory();
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if (e.getView().getTitle().equalsIgnoreCase("§6Banque §8> §cRetrait")){
            if (plugin.data.getBoolean(e.getPlayer().getName() + ".switch")){
                plugin.data.set(e.getPlayer().getName() + ".switch", false);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                plugin.data.set(e.getPlayer().getName(), null);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getView().getTitle().equalsIgnoreCase("§6Banque §8> §2Dépôt")){
            if (plugin.data.getBoolean(e.getPlayer().getName() + ".switch")){
                plugin.data.set(e.getPlayer().getName() + ".switch", false);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                plugin.data.set(e.getPlayer().getName(), null);
                try {
                    plugin.data.save(plugin.getFile("data"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        plugin.data.set(e.getPlayer().getName(), null);
        try {
            plugin.data.save(plugin.getFile("data"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
