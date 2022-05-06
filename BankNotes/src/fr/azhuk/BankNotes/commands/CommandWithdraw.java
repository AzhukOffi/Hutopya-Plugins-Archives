package fr.azhuk.BankNotes.commands;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class CommandWithdraw implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            if(cmd.getName().equalsIgnoreCase("withdraw")){
                Player p = (Player) sender;
                if (args.length == 0){
                    p.sendMessage("§8[§aBank Note§8] §cMerci de renseigner 1 argument");
                    return false;
                }
                if (args.length > 1){
                    p.sendMessage("§8[§aBank Note§8] §cMerci de renseigner 1 argument");
                    return false;
                }
                if (!Economy.playerExists(p.getName())){
                    p.sendMessage("§8[§aBank Note§8] §cVous n'avez pas de compte");
                    return false;
                }
                String arg0 = args[0].replaceAll("$", "");
                try {
                    Integer.parseInt(arg0);
                }
                catch (NumberFormatException e)
                {
                    p.sendMessage("§8[§aBank Note§8] §cMerci de renseigner un nombre entier");
                    return false;
                }
                BigDecimal argM = new BigDecimal(0);
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator(',');
                String pattern = "###,###,###,###";
                DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
                decimalFormat.setParseBigDecimal(true);
                try {
                    argM = (BigDecimal) decimalFormat.parse(arg0);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                if (argM.compareTo(BigDecimal.ZERO) <= 0){
                    p.sendMessage("§8[§aBank Note§8] §cMerci de renseigner un nombre positif");
                    return false;
                }
                System.out.println(argM);
                try {
                    if (!Economy.hasEnough(p.getName(), argM)){
                        p.sendMessage("§8[§aBank Note§8] §cVous n'avez pas assez !");
                        return false;
                    }
                } catch (UserDoesNotExistException e) {
                    e.printStackTrace();
                }

                try {
                    Economy.substract(p.getName(), argM);
                } catch (UserDoesNotExistException | NoLoanPermittedException e) {
                    e.printStackTrace();
                    return false;
                }

                String ID = generateString(new Random(), "123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz", 7);
                DecimalFormat format = new DecimalFormat("" );
                DecimalFormatSymbols s = format.getDecimalFormatSymbols();
                s.setGroupingSeparator(',');
                format.setDecimalFormatSymbols(s);
                long longM = Long.parseLong(arg0);
                ItemStack paper = new ItemStack(Material.PAPER);
                ItemMeta paperMeta = paper.getItemMeta();
                paperMeta.setDisplayName("§aBankNotes §8(Clique Droit)");
                paperMeta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
                paperMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                ArrayList<String> lore = new ArrayList<String>();
                lore.add("§8▪ §7Moneys: §a" + format.format(longM));
                lore.add("§8▪ §7Déposé par: §f" + p.getName());
                lore.add("§8▪ §7ID de transaction: §f" + ID);
                paperMeta.setLore(lore);
                paper.setItemMeta(paperMeta);

                boolean isFull = false;

                for (ItemStack item : p.getInventory().getContents()) {
                // But what's this? A null-check! So every Item that is NOT null wont trigger it. So lets say there is 1 cobblestone in my inventory and i pickup 1 cobblestone, it thinks my Inventory is full since 1 cobblestone is not null.
                    if (item == null) {
                        isFull = false;
                    }
                }
                if (isFull){
                    p.getLocation().getWorld().dropItem(p.getLocation(), paper);
                } else {
                    p.getInventory().addItem(paper);
                }
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Bank Note" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Withdraw:");
                Bukkit.getConsoleSender().sendMessage("§7Moneys: §a" + format.format(longM));
                Bukkit.getConsoleSender().sendMessage("§7Déposé par: §f" + p.getName() );
                Bukkit.getConsoleSender().sendMessage("§7ID de transaction: §f" + ID);
            }
            }
        return false;
        }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
