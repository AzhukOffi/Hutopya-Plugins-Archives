package fr.azhuk.banksurvie.commands;

import fr.azhuk.banksurvie.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BankSurvie implements CommandExecutor {

    Main plugin;

    public BankSurvie(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (args.length >= 2){
            if (args[0].equalsIgnoreCase("give")){
                if (args[1].equalsIgnoreCase("machine")){
                    if (sender.hasPermission("banksurvie.givemachine")){
                        ItemStack it = new ItemStack(Material.IRON_BLOCK, 1);
                        ItemMeta itemMeta = it.getItemMeta();
                        itemMeta.setDisplayName(plugin.messages.getString("messages.machine_name"));
                        it.setItemMeta(itemMeta);
                        if (args.length == 2){
                            Player p = (Player) sender;
                            p.getInventory().addItem(it);
                            return true;
                        }
                        if (args.length == 3){
                            OfflinePlayer op = Bukkit.getOfflinePlayer(args[2]);
                            if (op.isOnline()){
                                Player p = Bukkit.getPlayer(op.getName());
                                p.getInventory().addItem(it);
                                return true;
                            } else {
                                sender.sendMessage(plugin.messages.getString("messages.give_not_online"));
                            }
                        }
                    } else {
                        sender.sendMessage(plugin.messages.getString("messages.no_perm"));
                    }
                }
                if (args[1].equalsIgnoreCase("emeraldplus")){
                    if (sender.hasPermission("banksurvie.givemachine")){
                        if (args.length >= 3){
                            int amount = Integer.parseInt(args[2]);
                            if (amount > 64 || amount < 1){
                                sender.sendMessage("§7[§c!§7] §cLe nombre doit être situé entre 1 et 64");
                            }
                            ItemStack it = new ItemStack(Material.EMERALD, amount);
                            ItemMeta im = it.getItemMeta();
                            im.setDisplayName(plugin.messages.getString("messages.item_name.emerald_plus"));
                            it.setItemMeta(im);
                            it.addUnsafeEnchantment(Main.Glow, 1);
                            if (args.length == 3){
                                Player p = (Player) sender;
                                p.getInventory().addItem(it);
                                return true;
                            }
                            if (args.length == 4){
                                OfflinePlayer op = Bukkit.getOfflinePlayer(args[2]);
                                if (op.isOnline()){
                                    Player p = Bukkit.getPlayer(op.getName());
                                    p.getInventory().addItem(it);
                                    return true;
                                } else {
                                    sender.sendMessage(plugin.messages.getString("messages.give_not_online"));
                                }
                            }
                        } else {
                            sender.sendMessage("§7[§c!§7] §cSyntaxe§7: /banksurvie give emeraldplus <nombre> [player]");
                        }
                    } else {
                        sender.sendMessage(plugin.messages.getString("messages.no_perm"));
                    }
                }
            }
        }
        return false;
    }
}
