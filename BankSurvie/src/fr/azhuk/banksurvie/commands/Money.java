package fr.azhuk.banksurvie.commands;

import fr.azhuk.banksurvie.Main;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class Money implements CommandExecutor {

    Main plugin;

    public Money(Main instance) {
        plugin = instance;
    }

    private Economy economy = plugin.economy;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                if (economy.hasAccount(p)){
                    double balance = economy.getBalance(p);
                    double emeraldBlock = balance/9;
                    double enchantedEmerald = emeraldBlock/9;
                    double enchantedBlock = enchantedEmerald/9;

                    BigDecimal bd = new BigDecimal(emeraldBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    emeraldBlock = bd.doubleValue();

                    bd = new BigDecimal(enchantedEmerald);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedEmerald = bd.doubleValue();

                    bd = new BigDecimal(enchantedBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedBlock = bd.doubleValue();
                    TextComponent msg = new TextComponent("§7[§2!§7] §2Vous avez " + balance + "✧ emeraudes !");
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§2Solde: \n§2✧ §7Emeraudes : §2" + balance + "\n§2✧ §7Blocs : " + emeraldBlock + "\n§2✧ §7Emeraudes+ : §2" + enchantedEmerald + "\n§2✧ §7Blocs+ : §2" + enchantedBlock).create()));

                    p.spigot().sendMessage(msg);
                    return true;
                } else {
                    p.sendMessage("§7[§c!§7] §cTu n'as pas de compte !");
                }
            }
            if (args.length == 1){
                if (economy.hasAccount(Bukkit.getOfflinePlayer(args[0]))){
                    double balance = economy.getBalance(Bukkit.getOfflinePlayer(args[0]));
                    double emeraldBlock = balance/9;
                    double enchantedEmerald = emeraldBlock/9;
                    double enchantedBlock = enchantedEmerald/9;

                    BigDecimal bd = new BigDecimal(emeraldBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    emeraldBlock = bd.doubleValue();

                    bd = new BigDecimal(enchantedEmerald);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedEmerald = bd.doubleValue();

                    bd = new BigDecimal(enchantedBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedBlock = bd.doubleValue();
                    TextComponent msg = new TextComponent("§7[§2!§7] §2Ce joueur a" + balance + "✧ emeraudes !");
                    msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§2Solde: \n§2✧ §7Emeraudes : §2" + balance + "\n§2✧ §7Blocs : " + emeraldBlock + "\n§2✧ §7Emeraudes+ : §2" + enchantedEmerald + "\n§2✧ §7Blocs+ : §2" + enchantedBlock).create()));

                    p.spigot().sendMessage(msg);
                    return true;
                } else {
                    p.sendMessage("§7[§c!§7] §cCe joueur n'a pas de compte !");
                    return false;
                }
            }
            p.sendMessage("§7[§c!§7] §7Utilisation §8: §7/money [joueur]");
        } else {
            if (args.length == 1){
                if (economy.hasAccount(Bukkit.getOfflinePlayer(args[0]))){
                    double balance = economy.getBalance(Bukkit.getOfflinePlayer(args[0]));
                    double emeraldBlock = balance/9;
                    double enchantedEmerald = emeraldBlock/9;
                    double enchantedBlock = enchantedEmerald/9;

                    BigDecimal bd = new BigDecimal(emeraldBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    emeraldBlock = bd.doubleValue();

                    bd = new BigDecimal(enchantedEmerald);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedEmerald = bd.doubleValue();

                    bd = new BigDecimal(enchantedBlock);
                    bd= bd.setScale(2,BigDecimal.ROUND_DOWN);
                    enchantedBlock = bd.doubleValue();
                    Bukkit.getConsoleSender().sendMessage("§2Solde: \n§2✧ §7Emeraudes : §2" + balance + "\n§2✧ §7Blocs : " + emeraldBlock + "\n§2✧ §7Emeraudes+ : §2" + enchantedEmerald + "\n§2✧ §7Blocs+ : §2" + enchantedBlock);
                    return true;
                } else {
                    Bukkit.getConsoleSender().sendMessage("§7[§c!§7] §cCe joueur n'a pas de compte !");
                    return false;
                }
            } else {
                Bukkit.getConsoleSender().sendMessage("§7[§c!§7] §cMerci de renseigner uniquement le pseudo du joueur");
                return false;
            }

        }
        return false;
    }
}
