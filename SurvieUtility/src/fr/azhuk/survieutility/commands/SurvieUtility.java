package fr.azhuk.survieutility.commands;

import fr.azhuk.survieutility.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SurvieUtility implements CommandExecutor {


    Main plugin;

    public SurvieUtility(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                p.sendMessage("§8[§6HutopyaUtility§8] Plugin par Azhuk");
                return true;
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    if (p.hasPermission("survieutility.reload")){
                        plugin.reloadConfig();
                        p.sendMessage("§8[§6SurvieUtility§8] La config a bien été reload !");
                        return true;
                    } else {
                        p.sendMessage("§7[§c!§7] §cVous n'avez pas accès à cette commande !");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("toggleitem")){
                    ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName("§6Menu");
                    ArrayList<String> itemLore = new ArrayList<String>();
                    itemLore.add("§7Clique pour accéder au menu");
                    itemMeta.setLore(itemLore);
                    item.setItemMeta(itemMeta);
                    if (p.getInventory().getItem(17) == null){
                        p.getInventory().setItem(17, item);
                        p.sendMessage("§7[§a!§7] §aTu as réactivé l'item de menu !");
                        return true;
                    }
                    if (p.getInventory().getItem(17).getType() == Material.NETHER_STAR && p.getInventory().getItem(17).hasItemMeta() && p.getInventory().getItem(17).getItemMeta().hasDisplayName() && p.getInventory().getItem(17).getItemMeta().getDisplayName().equalsIgnoreCase("§6Menu")) {
                        p.getInventory().getItem(17).setAmount(0);
                        p.sendMessage("§7[§a!§7] §6Tu as désactivé l'item de menu, mais tu as toujours accès au /menu");
                        return true;
                    } else {
                        if (p.getInventory().getItem(17) == null){
                            p.getInventory().setItem(17, item);
                            p.sendMessage("§7[§a!§7] §aTu as réactivé l'item de menu !");
                            return true;
                        } else {
                            boolean isFull = true;
                            int i = 0;
                            while (i < 36){
                                if (p.getInventory().getItem(i) == null){
                                    isFull = false;
                                }
                                i++;
                            }
                            if(!isFull) {
                                p.getInventory().setItem(p.getInventory().firstEmpty(), p.getInventory().getItem(17));
                                p.getInventory().setItem(17, item);
                                p.sendMessage("§7[§a!§7] §aTu as réactivé l'item de menu !");
                                return true;
                            } else {
                                p.sendMessage("§7[§c!§7] §cTu doit libérer un slot dans ton inventaire");
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }


        return false;
    }

}
