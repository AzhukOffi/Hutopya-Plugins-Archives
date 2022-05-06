package fr.azhuk.banksurvie;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Methods {

    Main plugin = Main.getPlugin(Main.class);

    public Inventory Retrait(String currency, Player p){
        Inventory Newinv = Bukkit.createInventory(null, 45, plugin.messages.getString("messages.gui_retrait.name"));
        //verre gris
        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta grayglassmeta = grayglass.getItemMeta();
        grayglassmeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.glass_name"));
        grayglass.setItemMeta(grayglassmeta);

        //info
        ItemStack info = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName(plugin.messages.getString("messages.gui_retrait.infos_name"));
        ArrayList<String> loreInfo = new ArrayList<String>();
        for (String loreList: plugin.messages.getConfigurationSection("messages.gui_retrait.lore.").getKeys(false)){
            String msg = plugin.messages.getString("messages.gui_retrait.lore." + loreList);
            loreInfo.add(msg);
        }
        infometa.setLore(loreInfo);
        info.setItemMeta(infometa);

        //total
        ItemStack total = new ItemStack(Material.PAPER, 1);
        ItemMeta totalmeta = total.getItemMeta();
        totalmeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.total_name"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(plugin.messages.getString("messages.gui_retrait.total_lore.1") + plugin.data.getInt(p.getName() + ".block"));
        lore.add(plugin.messages.getString("messages.gui_retrait.total_lore.2") + plugin.data.getInt(p.getName() + ".emerald"));
        lore.add(plugin.messages.getString("messages.gui_retrait.total_lore.3"));
        lore.add(plugin.messages.getString("messages.gui_retrait.total_lore.4"));
        totalmeta.setLore(lore);
        total.setItemMeta(totalmeta);

        //back
        ItemStack back = new ItemStack(Material.BARRIER, 1);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.back_name"));
        back.setItemMeta(backmeta);

        //rouge
        ItemStack RedUn = Main.getHead("redun");
        ItemMeta RedUnMeta = RedUn.getItemMeta();
        RedUnMeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.retirer_1"));
        RedUn.setItemMeta(RedUnMeta);

        ItemStack RedDix = Main.getHead("reddix");
        ItemMeta RedDixMeta = RedDix.getItemMeta();
        RedDixMeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.retirer_10"));
        RedDix.setItemMeta(RedDixMeta);

        //vert
        ItemStack GreenUn = Main.getHead("greenun");
        ItemMeta GreenUnMeta = GreenUn.getItemMeta();
        GreenUnMeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.ajouter_1"));
        GreenUn.setItemMeta(GreenUnMeta);

        ItemStack GreenDix = Main.getHead("greendix");
        ItemMeta GreenDixMeta = GreenDix.getItemMeta();
        GreenDixMeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.ajouter_10"));
        GreenDix.setItemMeta(GreenDixMeta);

        switch (currency){
            case "emerald":
                //emeraude
                ItemStack emeraude = new ItemStack(Material.EMERALD, 1);
                ItemMeta emeraudemeta = emeraude.getItemMeta();
                emeraudemeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.devise_emeraude"));
                emeraude.setItemMeta(emeraudemeta);
                Newinv.setItem(22, emeraude);
                break;
            case "block":
                //bloc
                ItemStack block = new ItemStack(Material.EMERALD_BLOCK, 1);
                ItemMeta blockmeta = block.getItemMeta();
                blockmeta.setDisplayName(plugin.messages.getString("messages.gui_retrait.devise_block"));
                block.setItemMeta(blockmeta);
                Newinv.setItem(22, block);
                break;

        }

        Newinv.setItem(0, grayglass);
        Newinv.setItem(1, grayglass);
        Newinv.setItem(2, grayglass);
        Newinv.setItem(3, grayglass);
        Newinv.setItem(4, info);
        Newinv.setItem(5, grayglass);
        Newinv.setItem(6, grayglass);
        Newinv.setItem(7, grayglass);
        Newinv.setItem(8, grayglass);
        Newinv.setItem(9, grayglass);
        Newinv.setItem(10, RedUn);
        Newinv.setItem(11, grayglass);
        Newinv.setItem(12, grayglass);
        Newinv.setItem(13, grayglass);
        Newinv.setItem(14, grayglass);
        Newinv.setItem(15, grayglass);
        Newinv.setItem(16, GreenUn);
        Newinv.setItem(17, grayglass);
        Newinv.setItem(18, grayglass);
        Newinv.setItem(19, grayglass);
        Newinv.setItem(20, grayglass);
        Newinv.setItem(21, grayglass);
        Newinv.setItem(23, grayglass);
        Newinv.setItem(24, grayglass);
        Newinv.setItem(25, grayglass);
        Newinv.setItem(26, grayglass);
        Newinv.setItem(27, grayglass);
        Newinv.setItem(28, RedDix);
        Newinv.setItem(29, grayglass);
        Newinv.setItem(30, grayglass);
        Newinv.setItem(31, grayglass);
        Newinv.setItem(32, grayglass);
        Newinv.setItem(33, grayglass);
        Newinv.setItem(34, GreenDix);
        Newinv.setItem(35, grayglass);
        Newinv.setItem(36, grayglass);
        Newinv.setItem(37, grayglass);
        Newinv.setItem(38, grayglass);
        Newinv.setItem(39, back);
        Newinv.setItem(40, grayglass);
        Newinv.setItem(41, total);
        Newinv.setItem(42, grayglass);
        Newinv.setItem(43, grayglass);
        Newinv.setItem(44, grayglass);

        return Newinv;
    }

    public Inventory Depot(String currency, Player p){
        Inventory Newinv = Bukkit.createInventory(null, 45, plugin.messages.getString("messages.gui_depot.name"));
        //verre gris
        ItemStack grayglass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta grayglassmeta = grayglass.getItemMeta();
        grayglassmeta.setDisplayName(plugin.messages.getString("messages.gui_depot.glass_name"));
        grayglass.setItemMeta(grayglassmeta);

        //info
        ItemStack info = new ItemStack(Material.RED_CONCRETE, 1);
        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName(plugin.messages.getString("messages.gui_depot.infos_name"));
        ArrayList<String> loreInfo = new ArrayList<String>();
        for (String loreList: plugin.messages.getConfigurationSection("messages.gui_depot.lore.").getKeys(false)){
            String msg = plugin.messages.getString("messages.gui_depot.lore." + loreList);
            loreInfo.add(msg);
        }
        infometa.setLore(loreInfo);
        info.setItemMeta(infometa);

        //total
        ItemStack total = new ItemStack(Material.PAPER, 1);
        ItemMeta totalmeta = total.getItemMeta();
        totalmeta.setDisplayName(plugin.messages.getString("messages.gui_depot.total_name"));
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(plugin.messages.getString("messages.gui_depot.total_lore.1") + plugin.data.getInt(p.getName() + ".block"));
        lore.add(plugin.messages.getString("messages.gui_depot.total_lore.2") + plugin.data.getInt(p.getName() + ".emerald"));
        lore.add(plugin.messages.getString("messages.gui_depot.total_lore.3"));
        lore.add(plugin.messages.getString("messages.gui_depot.total_lore.4"));
        totalmeta.setLore(lore);
        total.setItemMeta(totalmeta);

        //back
        ItemStack back = new ItemStack(Material.BARRIER, 1);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(plugin.messages.getString("messages.gui_depot.back_name"));
        back.setItemMeta(backmeta);

        //rouge
        ItemStack RedUn = Main.getHead("redun");
        ItemMeta RedUnMeta = RedUn.getItemMeta();
        RedUnMeta.setDisplayName(plugin.messages.getString("messages.gui_depot.retirer_1"));
        RedUn.setItemMeta(RedUnMeta);

        ItemStack RedDix = Main.getHead("reddix");
        ItemMeta RedDixMeta = RedDix.getItemMeta();
        RedDixMeta.setDisplayName(plugin.messages.getString("messages.gui_depot.retirer_10"));
        RedDix.setItemMeta(RedDixMeta);

        //vert
        ItemStack GreenUn = Main.getHead("greenun");
        ItemMeta GreenUnMeta = GreenUn.getItemMeta();
        GreenUnMeta.setDisplayName(plugin.messages.getString("messages.gui_depot.ajouter_1"));
        GreenUn.setItemMeta(GreenUnMeta);

        ItemStack GreenDix = Main.getHead("greendix");
        ItemMeta GreenDixMeta = GreenDix.getItemMeta();
        GreenDixMeta.setDisplayName(plugin.messages.getString("messages.gui_depot.ajouter_10"));
        GreenDix.setItemMeta(GreenDixMeta);

        switch (currency){
            case "emerald":
                //emeraude
                ItemStack emeraude = new ItemStack(Material.EMERALD, 1);
                ItemMeta emeraudemeta = emeraude.getItemMeta();
                emeraudemeta.setDisplayName(plugin.messages.getString("messages.gui_depot.devise_emeraude"));
                emeraude.setItemMeta(emeraudemeta);
                Newinv.setItem(22, emeraude);
                break;
            case "block":
                //bloc
                ItemStack block = new ItemStack(Material.EMERALD_BLOCK, 1);
                ItemMeta blockmeta = block.getItemMeta();
                blockmeta.setDisplayName(plugin.messages.getString("messages.gui_depot.devise_block"));
                block.setItemMeta(blockmeta);
                Newinv.setItem(22, block);
                break;

        }

        Newinv.setItem(0, grayglass);
        Newinv.setItem(1, grayglass);
        Newinv.setItem(2, grayglass);
        Newinv.setItem(3, grayglass);
        Newinv.setItem(4, info);
        Newinv.setItem(5, grayglass);
        Newinv.setItem(6, grayglass);
        Newinv.setItem(7, grayglass);
        Newinv.setItem(8, grayglass);
        Newinv.setItem(9, grayglass);
        Newinv.setItem(10, RedUn);
        Newinv.setItem(11, grayglass);
        Newinv.setItem(12, grayglass);
        Newinv.setItem(13, grayglass);
        Newinv.setItem(14, grayglass);
        Newinv.setItem(15, grayglass);
        Newinv.setItem(16, GreenUn);
        Newinv.setItem(17, grayglass);
        Newinv.setItem(18, grayglass);
        Newinv.setItem(19, grayglass);
        Newinv.setItem(20, grayglass);
        Newinv.setItem(21, grayglass);
        Newinv.setItem(23, grayglass);
        Newinv.setItem(24, grayglass);
        Newinv.setItem(25, grayglass);
        Newinv.setItem(26, grayglass);
        Newinv.setItem(27, grayglass);
        Newinv.setItem(28, RedDix);
        Newinv.setItem(29, grayglass);
        Newinv.setItem(30, grayglass);
        Newinv.setItem(31, grayglass);
        Newinv.setItem(32, grayglass);
        Newinv.setItem(33, grayglass);
        Newinv.setItem(34, GreenDix);
        Newinv.setItem(35, grayglass);
        Newinv.setItem(36, grayglass);
        Newinv.setItem(37, grayglass);
        Newinv.setItem(38, grayglass);
        Newinv.setItem(39, back);
        Newinv.setItem(40, grayglass);
        Newinv.setItem(41, total);
        Newinv.setItem(42, grayglass);
        Newinv.setItem(43, grayglass);
        Newinv.setItem(44, grayglass);

        return Newinv;
    }

    public Inventory Echanges(){
        Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("messages.machine_name"));

        ItemStack emeraldBlock = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta emeraldBlockMeta = emeraldBlock.getItemMeta();
        emeraldBlockMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_block_emerald"));
        emeraldBlock.setItemMeta(emeraldBlockMeta);

        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_emerald"));
        emerald.setItemMeta(emeraldMeta);

        ItemStack emeraldBlockPlus = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta emeraldBlockMetaPlus = emeraldBlockPlus.getItemMeta();
        emeraldBlockMetaPlus.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_block_emerald_plus"));
        emeraldBlockPlus.setItemMeta(emeraldBlockMetaPlus);
        emeraldBlockPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack emeraldPlus = new ItemStack(Material.EMERALD, 1);
        ItemMeta emeraldMetaPlus = emeraldPlus.getItemMeta();
        emeraldMetaPlus.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_emerald_plus"));
        emeraldPlus.setItemMeta(emeraldMetaPlus);
        emeraldPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§6");
        glass.setItemMeta(glassMeta);

        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, emerald);
        inv.setItem(11, glass);
        inv.setItem(12, emeraldBlock);
        inv.setItem(13, glass);
        inv.setItem(14, emeraldPlus);
        inv.setItem(15, glass);
        inv.setItem(16, emeraldBlockPlus);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);
        inv.setItem(29, glass);
        inv.setItem(30, glass);
        inv.setItem(31, glass);
        inv.setItem(32, glass);
        inv.setItem(33, glass);
        inv.setItem(34, glass);
        inv.setItem(35, glass);
        inv.setItem(36, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(39, glass);
        inv.setItem(40, glass);
        inv.setItem(41, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);
        inv.setItem(44, glass);

        return inv;
    }

    public Inventory toEmerald(){
        Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("messages.machine_gui.convert_to_emerald"));

        ItemStack fromEmeraldBlock = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta fromEmeraldBlockMeta = fromEmeraldBlock.getItemMeta();
        fromEmeraldBlockMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_from_block_emerald"));
        fromEmeraldBlock.setItemMeta(fromEmeraldBlockMeta);

        /*ItemStack fromEmerald = new ItemStack(Material.EMERALD, 1);
        ItemMeta fromEmeraldMeta = fromEmerald.getItemMeta();
        fromEmeraldMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_from_emerald"));
        fromEmerald.setItemMeta(fromEmeraldMeta);*/

        ItemStack fromEmeraldBlockPlus = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta fromEmeraldBlockPlusMeta = fromEmeraldBlockPlus.getItemMeta();
        fromEmeraldBlockPlusMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_from_block_emerald_plus"));
        fromEmeraldBlockPlus.setItemMeta(fromEmeraldBlockPlusMeta);
        fromEmeraldBlockPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack fromEmeraldPlus = new ItemStack(Material.EMERALD, 1);
        ItemMeta fromEmeraldPlusMeta = fromEmeraldPlus.getItemMeta();
        fromEmeraldPlusMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_from_emerald_plus"));
        fromEmeraldPlus.setItemMeta(fromEmeraldPlusMeta);
        fromEmeraldPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack emeraldBlock = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta emeraldBlockMeta = emeraldBlock.getItemMeta();
        emeraldBlockMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_block_emerald"));
        emeraldBlock.setItemMeta(emeraldBlockMeta);

        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_emerald"));
        emerald.setItemMeta(emeraldMeta);

        ItemStack emeraldBlockPlus = new ItemStack(Material.EMERALD_BLOCK, 1);
        ItemMeta emeraldBlockMetaPlus = emeraldBlockPlus.getItemMeta();
        emeraldBlockMetaPlus.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_block_emerald_plus"));
        emeraldBlockPlus.setItemMeta(emeraldBlockMetaPlus);
        emeraldBlockPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack emeraldPlus = new ItemStack(Material.EMERALD, 1);
        ItemMeta emeraldMetaPlus = emeraldPlus.getItemMeta();
        emeraldMetaPlus.setDisplayName(plugin.messages.getString("messages.machine_gui.convert_to_emerald_plus"));
        emeraldPlus.setItemMeta(emeraldMetaPlus);
        emeraldPlus.addUnsafeEnchantment(Main.Glow, 1);

        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§6");
        glass.setItemMeta(glassMeta);



        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, emerald);
        inv.setItem(11, glass);
        inv.setItem(12, emeraldBlock);
        inv.setItem(13, glass);
        inv.setItem(14, emeraldPlus);
        inv.setItem(15, glass);
        inv.setItem(16, emeraldBlockPlus);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);
        inv.setItem(29, fromEmeraldBlock);
        inv.setItem(30, glass);
        inv.setItem(31, fromEmeraldPlus);
        inv.setItem(32, glass);
        inv.setItem(33, fromEmeraldBlockPlus);
        inv.setItem(34, glass);
        inv.setItem(35, glass);
        inv.setItem(36, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(39, glass);
        inv.setItem(40, glass);
        inv.setItem(41, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);
        inv.setItem(44, glass);

        return inv;
    }

}
