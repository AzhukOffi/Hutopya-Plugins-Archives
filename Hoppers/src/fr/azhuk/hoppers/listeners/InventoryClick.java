package fr.azhuk.hoppers.listeners;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import fr.azhuk.hoppers.Main;
import fr.azhuk.hoppers.Methods;
import fr.azhuk.hoppers.MySQL;
import net.ess3.api.Economy;
import net.milkbowl.vault.VaultEco;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;

public class InventoryClick implements Listener {

    Main plugin;

    public InventoryClick(Main instance) {
        plugin = instance;
    }

    MySQL SQL = new MySQL();
    Methods methods = new Methods();


    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        //définir le lvl du hopper
        int lvl = 0;
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "1")){
            lvl = 1;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "2")){
            lvl = 2;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "3")){
            lvl = 3;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "4")){
            lvl = 4;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "5")){
            lvl = 5;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "6")){
            lvl = 6;
        }
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + "7")){
            lvl = 7;
        }



        //si inv = hopper
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("messages.gui.hoppper_gui") + lvl)){
            if (e.getInventory().getType() == InventoryType.HOPPER){
                return;
            }
            if (current == null) return;
            e.setCancelled(true);
            if (current.getType() == Material.SUNFLOWER && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui.synchro_item"))){

            }


            if (current.getType() == Material.SUNFLOWER && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui.upgrade_item"))){
            String idTemp = "";
            for (char c : e.getInventory().getItem(26).getItemMeta().getDisplayName().toCharArray()){
                if (Character.isDigit(c)){
                    idTemp = idTemp + c;
                }
            }
            int id = Integer.valueOf(idTemp);
            switch (lvl){
                //upgrade to lvl 2
                case 1:
                    if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                        int priceInt = plugin.getConfig().getInt("price.upgrade_to_2"); //Récuperer le prix
                        try {
                            boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                            if (hasEnough){
                                BigDecimal price = new BigDecimal(priceInt);
                                try {
                                    Economy.substract(p.getName(), price); //Enlever le prix
                                    SQL.updateLevel(id, 2); //Upgrade bdd
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                    //update inv
                                    p.closeInventory();
                                    p.openInventory(methods.invHopper(id, p));
                                } catch (NoLoanPermittedException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                            }
                        } catch (UserDoesNotExistException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                    }
                    break;
                    //upgrade to lvl 3
                    case 2:
                        if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                            int priceInt = plugin.getConfig().getInt("price.upgrade_to_3"); //Récuperer le prix
                            try {
                                boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                                if (hasEnough){
                                    BigDecimal price = new BigDecimal(priceInt);
                                    try {
                                        Economy.substract(p.getName(), price); //Enlever le prix
                                        SQL.updateLevel(id, 3); //Upgrade bdd
                                        p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                        //update inv
                                        p.closeInventory();
                                        p.openInventory(methods.invHopper(id, p));
                                    } catch (NoLoanPermittedException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                                }
                            } catch (UserDoesNotExistException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                        }
                        break;
                    //upgrade to lvl 4
                    case 3:
                        if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                            int priceInt = plugin.getConfig().getInt("price.upgrade_to_4"); //Récuperer le prix
                            try {
                                boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                                if (hasEnough){
                                    BigDecimal price = new BigDecimal(priceInt);
                                    try {
                                        Economy.substract(p.getName(), price); //Enlever le prix
                                        SQL.updateLevel(id, 4); //Upgrade bdd
                                        p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                        //update inv
                                        p.closeInventory();
                                        p.openInventory(methods.invHopper(id, p));
                                    } catch (NoLoanPermittedException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                                }
                            } catch (UserDoesNotExistException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                        }
                        break;
                    //upgrade to lvl 5
                    case 4:
                        if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                            int priceInt = plugin.getConfig().getInt("price.upgrade_to_5"); //Récuperer le prix
                            try {
                                boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                                if (hasEnough){
                                    BigDecimal price = new BigDecimal(priceInt);
                                    try {
                                        Economy.substract(p.getName(), price); //Enlever le prix
                                        SQL.updateLevel(id, 5); //Upgrade bdd
                                        p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                        //update inv
                                        p.closeInventory();
                                        p.openInventory(methods.invHopper(id, p));
                                    } catch (NoLoanPermittedException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                                }
                            } catch (UserDoesNotExistException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                        }
                        break;
                    //upgrade to lvl 6
                    case 5:
                        if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                            int priceInt = plugin.getConfig().getInt("price.upgrade_to_6"); //Récuperer le prix
                            try {
                                boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                                if (hasEnough){
                                    BigDecimal price = new BigDecimal(priceInt);
                                    try {
                                        Economy.substract(p.getName(), price); //Enlever le prix
                                        SQL.updateLevel(id, 6); //Upgrade bdd
                                        p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                        //update inv
                                        p.closeInventory();
                                        p.openInventory(methods.invHopper(id, p));
                                    } catch (NoLoanPermittedException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                                }
                            } catch (UserDoesNotExistException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                        }
                        break;
                    //upgrade to lvl 7
                    case 6:
                        if (Economy.playerExists(p.getName())){ //Vérifier l'éxistance du compte
                            int priceInt = plugin.getConfig().getInt("price.upgrade_to_7"); //Récuperer le prix
                            try {
                                boolean hasEnough = Economy.hasEnough(p.getName(), priceInt); //Il a assez ?
                                if (hasEnough){
                                    BigDecimal price = new BigDecimal(priceInt);
                                    try {
                                        Economy.substract(p.getName(), price); //Enlever le prix
                                        SQL.updateLevel(id, 7); //Upgrade bdd
                                        p.sendMessage(plugin.messages.getString("messages.upgrade.hopper_upgrade")); //Upgrade réussi
                                        //update inv
                                        p.closeInventory();
                                        p.openInventory(methods.invHopper(id, p));
                                    } catch (NoLoanPermittedException ex) {
                                        ex.printStackTrace();
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("messages.upgrade.err_not_enough")); //Erreur : Vous n'avez pas assez d'argent
                                }
                            } catch (UserDoesNotExistException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("messages.upgrade.err_no_account")); //Erreur: pas de compte
                        }
                        break;
                }
            }
            //si item = filtre
            if (current.getType() == Material.COMPARATOR && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("messages.gui.filtre_item"))){
                Inventory invFilter = Bukkit.createInventory((InventoryHolder)null, 36, "§6Configuration du filtre");

                //Itemstack : BLUE GLASS
                ItemStack blueglass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
                ItemMeta blueglassmeta = blueglass.getItemMeta();
                blueglassmeta.setDisplayName("§6");
                blueglass.setItemMeta(blueglassmeta);

                //Itemstack : BLUE GLASS | avec info caché
                ItemStack blueinfoglass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
                String hidden = e.getInventory().getItem(26).getItemMeta().getDisplayName();
                ItemMeta blueinfoglassmeta = blueinfoglass.getItemMeta();
                blueinfoglassmeta.setDisplayName(hidden);
                blueinfoglass.setItemMeta(blueinfoglassmeta);

                //Itemstack : GRAY GLASS
                ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
                ItemMeta grayglassmeta = grayglass.getItemMeta();
                grayglassmeta.setDisplayName("§6");
                grayglass.setItemMeta(grayglassmeta);

                String idTemp = "";
                for (char c : e.getInventory().getItem(26).getItemMeta().getDisplayName().toCharArray()){
                    if (Character.isDigit(c)){
                        idTemp = idTemp + c;
                    }
                }
                int id = Integer.valueOf(idTemp);

                String[] whitelist = SQL.getWhitelist(id);

                if (!whitelist[0].equalsIgnoreCase("none")){
                    ItemStack whitelist1 = new ItemStack(Material.getMaterial(whitelist[0]), 1);
                    invFilter.setItem(0, whitelist1);
                }
                if (!whitelist[1].equalsIgnoreCase("none")){
                    ItemStack whitelist2 = new ItemStack(Material.getMaterial(whitelist[1]), 1);
                    invFilter.setItem(1, whitelist2);
                }
                if (!whitelist[2].equalsIgnoreCase("none")){
                    ItemStack whitelist3 = new ItemStack(Material.getMaterial(whitelist[2]), 1);
                    invFilter.setItem(9, whitelist3);
                }
                if (!whitelist[3].equalsIgnoreCase("none")){
                    ItemStack whitelist4 = new ItemStack(Material.getMaterial(whitelist[3]), 1);
                    invFilter.setItem(10, whitelist4);
                }
                if (!whitelist[4].equalsIgnoreCase("none")){
                    ItemStack whitelist5 = new ItemStack(Material.getMaterial(whitelist[4]), 1);
                    invFilter.setItem(18, whitelist5);
                }
                if (!whitelist[5].equalsIgnoreCase("none")){
                    ItemStack whitelist6 = new ItemStack(Material.getMaterial(whitelist[5]), 1);
                    invFilter.setItem(19, whitelist6);
                }

                String[] rejets = SQL.getRejets(id);

                if (!rejets[0].equalsIgnoreCase("none")){
                    ItemStack rejets1 = new ItemStack(Material.getMaterial(rejets[0]), 1);
                    invFilter.setItem(3, rejets1);
                }
                if (!rejets[1].equalsIgnoreCase("none")){
                    ItemStack rejets2 = new ItemStack(Material.getMaterial(rejets[1]), 1);
                    invFilter.setItem(4, rejets2);
                }
                if (!rejets[2].equalsIgnoreCase("none")){
                    ItemStack rejets3 = new ItemStack(Material.getMaterial(rejets[2]), 1);
                    invFilter.setItem(5, rejets3);
                }
                if (!rejets[3].equalsIgnoreCase("none")){
                    ItemStack rejets4 = new ItemStack(Material.getMaterial(rejets[3]), 1);
                    invFilter.setItem(12, rejets4);
                }
                if (!rejets[4].equalsIgnoreCase("none")){
                    ItemStack rejets5 = new ItemStack(Material.getMaterial(rejets[4]), 1);
                    invFilter.setItem(13, rejets5);
                }
                if (!rejets[5].equalsIgnoreCase("none")){
                    ItemStack rejets6 = new ItemStack(Material.getMaterial(rejets[5]), 1);
                    invFilter.setItem(14, rejets6);
                }

                String[] blacklist = SQL.getBlacklist(id);

                if (!blacklist[0].equalsIgnoreCase("none")){
                    ItemStack blacklist1 = new ItemStack(Material.getMaterial(blacklist[0]), 1);
                    invFilter.setItem(7, blacklist1);
                }
                if (!blacklist[1].equalsIgnoreCase("none")){
                    ItemStack blacklist2 = new ItemStack(Material.getMaterial(blacklist[1]), 1);
                    invFilter.setItem(8, blacklist2);
                }
                if (!blacklist[2].equalsIgnoreCase("none")){
                    ItemStack blacklist3 = new ItemStack(Material.getMaterial(blacklist[2]), 1);
                    invFilter.setItem(16, blacklist3);
                }
                if (!blacklist[3].equalsIgnoreCase("none")){
                    ItemStack blacklist4 = new ItemStack(Material.getMaterial(blacklist[3]), 1);
                    invFilter.setItem(17, blacklist4);
                }
                if (!blacklist[4].equalsIgnoreCase("none")){
                    ItemStack blacklist5 = new ItemStack(Material.getMaterial(blacklist[4]), 1);
                    invFilter.setItem(25, blacklist5);
                }
                if (!blacklist[5].equalsIgnoreCase("none")){
                    ItemStack blacklist6 = new ItemStack(Material.getMaterial(blacklist[5]), 1);
                    invFilter.setItem(26, blacklist6);
                }


                //set item
                invFilter.setItem(2, blueglass);
                invFilter.setItem(6, blueglass);
                invFilter.setItem(11, blueglass);
                invFilter.setItem(15, blueglass);
                invFilter.setItem(20, blueglass);
                invFilter.setItem(21, blueglass);
                invFilter.setItem(22, blueglass);
                invFilter.setItem(23, blueglass);
                invFilter.setItem(24, blueglass);
                invFilter.setItem(27, blueglass);
                invFilter.setItem(28, blueglass);
                invFilter.setItem(29, blueglass);
                invFilter.setItem(30, blueglass);
                invFilter.setItem(31, blueglass);
                invFilter.setItem(32, blueglass);
                invFilter.setItem(33, blueglass);
                invFilter.setItem(34, blueglass);
                invFilter.setItem(35, blueinfoglass);

                p.openInventory(invFilter);
            }
        }

        //si inv = filtre
        if (e.getView().getTitle().equalsIgnoreCase("§6Configuration du filtre")){
            if (current == null && e.getCursor() == null) return;
            String idTemp = "";
            for (char c : e.getInventory().getItem(35).getItemMeta().getDisplayName().toCharArray()){
                if (Character.isDigit(c)){
                    idTemp = idTemp + c;
                }
            }
            int id = Integer.valueOf(idTemp);
            for (ItemStack item : e.getView().getTopInventory().getContents()) {

            }
            if (e.getClickedInventory() == null){
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory().getHolder() == null) {
                //si item = BLUE GLASS (renomée)
                if (current != null && current.getType() == Material.BLUE_STAINED_GLASS_PANE && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().contains("§6")) {
                    e.setCancelled(true);
                    return;
                }

                int slot = -1;
                switch (e.getRawSlot()) {

                    case 0:
                        slot = 1;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        String item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 1:
                        slot = 2;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 3:
                        slot = 1;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 4:
                        slot = 2;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 5:
                        slot = 3;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 7:
                        slot = 1;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                    case 8:
                        slot = 2;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                    case 9:
                        slot = 3;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 10:
                        slot = 4;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 12:
                        slot = 4;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 13:
                        slot = 5;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 14:
                        slot = 6;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateRejets(item, id, slot);
                        break;
                    case 16:
                        slot = 3;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                    case 17:
                        slot = 4;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                    case 18:
                        slot = 5;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 19:
                        slot = 6;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateWhitelist(item, id, slot);
                        break;
                    case 25:
                        slot = 5;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                    case 26:
                        slot = 6;
                        methods.checkSlot(e.getRawSlot(), e.getClickedInventory(), p);
                        item = e.getCursor().getType().toString();
                        if (item.equalsIgnoreCase("AIR")) {
                            item = "none";
                        }
                        SQL.updateBlacklist(item, id, slot);
                        break;
                }
            }
            if (e.getClickedInventory().getHolder() == p){
                if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY){
                    int slot = -1;
                    int rawSlot = -1;
                    switch (e.getView().getTopInventory().firstEmpty()){

                        //
                        //Whitelist
                        //

                        case 0:
                            slot = 1;
                            rawSlot = 0;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                            //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                            //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 1:
                            rawSlot = 1;
                            slot = 2;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 9:
                            rawSlot = 9;
                            slot = 3;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 10:
                            rawSlot = 10;
                            slot = 4;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 18:
                            rawSlot = 18;
                            slot = 5;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 19:
                            rawSlot = 19;
                            slot = 6;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }

                        //
                        //Fin Whitelist
                        //
                        //Rejets
                        //

                        case 3:
                            rawSlot = 3;
                            slot = 1;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }
                        case 4:
                            rawSlot = 4;
                            slot = 2;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateRejets(item, id, slot);
                                return;
                            }
                        case 5:
                            rawSlot = 5;
                            slot = 3;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateRejets(item, id, slot);
                                return;
                            }
                        case 12:
                            rawSlot = 12;
                            slot = 4;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateRejets(item, id, slot);
                                return;
                            }
                        case 13:
                            rawSlot = 13;
                            slot = 5;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateRejets(item, id, slot);
                                return;
                            }
                        case 14:
                            rawSlot = 14;
                            slot = 6;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateRejets(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateWhitelist(item, id, slot);
                                return;
                            }

                        //
                        //Fin Rejets
                        //
                        //Blacklist
                        //

                        case 7:
                            rawSlot = 7;
                            slot = 1;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateBlacklist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }
                        case 8:
                            rawSlot = 8;
                            slot = 2;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateBlacklist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }
                        case 16:
                            rawSlot = 16;
                            slot = 3;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateBlacklist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }
                        case 17:
                            rawSlot = 17;
                            slot = 4;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateBlacklist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }

                        case 25:
                            rawSlot = 25;
                            slot = 6;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateWhitelist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }
                        case 26:
                            rawSlot = 26;
                            slot = 5;
                            //si il est null, l'item ne peux pas être le même donc pas de verif
                            if (e.getInventory().getItem(rawSlot) == null) {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")){
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() -1));
                                SQL.updateBlacklist(item, id, slot);
                                return;

                                //si il est pas null, vérifier si c'est le même
                            } else if (e.getInventory().getItem(rawSlot).getType() == current.getType()) {
                                return;
                                //si ce n'est pas le même
                            } else {
                                String item = current.getType().toString();
                                if (item.equalsIgnoreCase("AIR")) {
                                    item = "none";
                                }
                                e.setCancelled(true);
                                e.getView().getTopInventory().setItem(rawSlot, new ItemStack(current.getType(), 1));
                                e.getView().setItem(e.getRawSlot(), new ItemStack(current.getType(), current.getAmount() - 1));
                                SQL.updateBlacklist(item, id, slot);
                                return;
                            }
                    }
                }
            }
        }
    }
}
