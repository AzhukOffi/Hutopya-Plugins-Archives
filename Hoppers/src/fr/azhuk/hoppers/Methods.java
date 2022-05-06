package fr.azhuk.hoppers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Methods {

    MySQL SQL = new MySQL();
    Main plugin = Main.getPlugin(Main.class);

    public void autoBreak(Location loc, int lvl){
        int delay = -1;
        switch (lvl){
            case 4:
                delay = 16;
                break;
            case 5:
                delay = 14;
                break;
            case 6:
                delay = 12;
                break;
            case 7:
                delay = 11;
                break;
            default:
                return;

        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Block toBreak = loc.getBlock();
                if (loc.add(0,-1,0).getBlock().getType() == Material.HOPPER){
                    System.out.println(loc.add(0,-1,0).getBlock());
                    Hopper hopper = (Hopper) loc.add(0,-1,0).getBlock();
                    for (ItemStack it: toBreak.getDrops()){
                        hopper.getInventory().addItem(it);
                    }
                    toBreak.setType(Material.AIR);
                }
            }
        }.runTaskLater(plugin, delay);
    }

    public void checkSlot(int slot, Inventory inv, Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack current = inv.getItem(slot);
                if (current == null) return;
                if (current.getAmount() > 1) {
                    if (current.getAmount() == 64 && current.getType() == p.getItemOnCursor().getType()) {
                        p.setItemOnCursor(new ItemStack(current.getType(), current.getAmount()));
                        current.setAmount(1);
                    } else {
                        if (p.getItemOnCursor() != null) {
                            p.getInventory().addItem(p.getItemOnCursor());
                        }
                        p.setItemOnCursor(new ItemStack(current.getType(), current.getAmount() - 1));
                        current.setAmount(1);
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }

    public void Rejets(Inventory inv, Material it) {
        new BukkitRunnable() {
            @Override
            public void run() {
                inv.remove(it);
            }
        }.runTaskLater(plugin, 2);
    }

    public Inventory invHopper(int id, Player p) {
        Location loc = SQL.getLoc(id);
        int lvl = SQL.getLevel(loc);
        Inventory inv = Bukkit.createInventory(null, 27, plugin.messages.getString("messages.gui.hoppper_gui") + lvl);

        //ItemStack

        //Autocraft
        ItemStack craft = new ItemStack(Material.CRAFTING_TABLE, 1);
        ItemMeta craftmeta = craft.getItemMeta();
        craftmeta.setDisplayName(plugin.messages.getString("messages.gui.autocraft_item"));
        craft.setItemMeta(craftmeta);

        //synchro
        ItemStack synchro = new ItemStack(Material.TRIPWIRE_HOOK, 1);
        ItemMeta synchrometa = synchro.getItemMeta();
        synchrometa.setDisplayName(plugin.messages.getString("messages.gui.synchro_item"));
        synchro.setItemMeta(synchrometa);

        //autobreak
        ItemStack autobreak = new ItemStack(Material.IRON_ORE, 1);
        ItemMeta autobreakmeta = autobreak.getItemMeta();
        autobreakmeta.setDisplayName(plugin.messages.getString("messages.gui.autobreak_item"));
        autobreak.setItemMeta(autobreakmeta);

        //autokill
        ItemStack autokill = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta autokillmeta = autokill.getItemMeta();
        autokillmeta.setDisplayName(plugin.messages.getString("messages.gui.autokill_item"));
        autokill.setItemMeta(autokillmeta);

        //filtre
        ItemStack filtre = new ItemStack(Material.COMPARATOR, 1);
        ItemMeta filtremeta = filtre.getItemMeta();
        filtremeta.setDisplayName(plugin.messages.getString("messages.gui.filtre_item"));
        filtre.setItemMeta(filtremeta);

        //verre gris
        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta grayglassmeta = grayglass.getItemMeta();
        grayglassmeta.setDisplayName("§6");
        grayglass.setItemMeta(grayglassmeta);

        //verre bleu
        ItemStack blueglass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta blueglassmeta = blueglass.getItemMeta();
        blueglassmeta.setDisplayName("§6");
        blueglass.setItemMeta(blueglassmeta);

        //hopper
        ItemStack hopper = new ItemStack(Material.HOPPER, 1);
        ItemMeta hoppermeta = hopper.getItemMeta();
        hoppermeta.setDisplayName(plugin.messages.getString("messages.gui.hoppper_gui") + lvl);
        ArrayList<String> hopperlore = new ArrayList<String>();

        //lore par lvl
        switch (lvl) {
            case 1:
                hopperlore.add("§7Portée §610");
                hopperlore.add("§7Vitesse §61");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §82");
                hopperlore.add("§7Portée §620");
                hopperlore.add("§7Vitesse §62");
                break;
            case 2:
                hopperlore.add("§7Portée §620");
                hopperlore.add("§7Vitesse §62");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §83");
                hopperlore.add("§7Portée §630");
                hopperlore.add("§7Vitesse §63");
                hopperlore.add("§7Synchronisation §62");
                break;
            case 3:
                hopperlore.add("§7Portée §630");
                hopperlore.add("§7Vitesse §63");
                hopperlore.add("§7Synchronisation §62");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §84");
                hopperlore.add("§7Portée §640");
                hopperlore.add("§7Vitesse §64");
                hopperlore.add("§7Synchronisation §62");
                hopperlore.add("§7AutoBreak: §6tout les 16 ticks");
                break;
            case 4:
                hopperlore.add("§7Portée §640");
                hopperlore.add("§7Vitesse §64");
                hopperlore.add("§7Synchronisation §62");
                hopperlore.add("§7AutoBreak: §6tout les 16 ticks");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §85");
                hopperlore.add("§7Portée §650");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §63");
                hopperlore.add("§7AutoBreak: §6tout les 14 ticks");
                break;
            case 5:
                hopperlore.add("§7Portée §650");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §63");
                hopperlore.add("§7AutoBreak: §6tout les 14 ticks");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §86");
                hopperlore.add("§7Portée §660");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §63");
                hopperlore.add("§7AutoBreak: §6tout les 12 ticks");
                hopperlore.add("§7KillAuto: §6tout les x ticks");
                break;
            case 6:
                hopperlore.add("§7Portée §660");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §63");
                hopperlore.add("§7AutoBreak: §6tout les 12 ticks");
                hopperlore.add("§7");
                hopperlore.add("§6Hopper level §87");
                hopperlore.add("§7Portée §670");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §64");
                hopperlore.add("§7AutoBreak: §6tout les 11 ticks");
                hopperlore.add("§7KillAuto: §6tout les x ticks");
                hopperlore.add("§7CraftAuto: §6Activé");
                break;
            case 7:
                hopperlore.add("§7Portée §670");
                hopperlore.add("§7Vitesse §65");
                hopperlore.add("§7Synchronisation §64");
                hopperlore.add("§7AutoBreak: §6tout les 11 ticks");
                hopperlore.add("§7KillAuto: §6tout les x ticks");
                hopperlore.add("§7CraftAuto: §6Activé");
                break;
        }
        hoppermeta.setLore(hopperlore);
        hopper.setItemMeta(hoppermeta);

        //upgrade
        ItemStack upgrade = new ItemStack(Material.SUNFLOWER, 1);
        ItemMeta upgrademeta = upgrade.getItemMeta();
        upgrademeta.setDisplayName(plugin.messages.getString("messages.gui.upgrade_item"));
        ArrayList<String> upgradelore = new ArrayList<String>();

        //lore par lvl
        switch (lvl) {
            case 1:
                upgradelore.add("§7Coût: §a$150,000");
                break;
            case 2:
                upgradelore.add("§7Coût: §a$275,000");
                break;
            case 3:
                upgradelore.add("§7Coût: §a$500,000");
                break;
            case 4:
                upgradelore.add("§7Coût: §a$1,500,000");
                break;
            case 5:
                upgradelore.add("§7Coût: §a$2,250,000");
                break;
            case 6:
                upgradelore.add("§7Coût: §a$4,000,000");
                break;
            case 7:
                upgradelore.add("§7Ce hopper est déjà au maximum");
                break;
        }
        upgrademeta.setLore(upgradelore);
        upgrade.setItemMeta(upgrademeta);

        ItemStack blueinfoglass = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        String idString = id + "";
        String hidden = "";
        for (char c : idString.toCharArray()) hidden += ChatColor.COLOR_CHAR + "" + c;
        ItemMeta blueinfoglassmeta = blueinfoglass.getItemMeta();
        blueinfoglassmeta.setDisplayName(hidden);
        blueinfoglass.setItemMeta(blueinfoglassmeta);

        //set item in inv
        inv.setItem(0, blueglass);
        inv.setItem(1, blueglass);
        inv.setItem(2, grayglass);
        inv.setItem(3, craft);
        inv.setItem(4, grayglass);
        inv.setItem(5, synchro);
        inv.setItem(6, grayglass);
        inv.setItem(7, blueglass);
        inv.setItem(8, blueglass);
        inv.setItem(9, blueglass);
        inv.setItem(10, hopper);
        inv.setItem(11, grayglass);
        inv.setItem(12, grayglass);
        inv.setItem(13, filtre);
        inv.setItem(14, grayglass);
        inv.setItem(15, grayglass);
        inv.setItem(16, upgrade);
        inv.setItem(17, blueglass);
        inv.setItem(18, blueglass);
        inv.setItem(19, blueglass);
        inv.setItem(20, grayglass);
        inv.setItem(21, autobreak);
        inv.setItem(22, grayglass);
        inv.setItem(23, autokill);
        inv.setItem(24, grayglass);
        inv.setItem(25, blueglass);
        inv.setItem(26, blueinfoglass);

        return inv;
    }
}