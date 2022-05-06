package fr.azhuk.BankNotes.Listener;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import net.ess3.api.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class OnClick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() != null && e.getItem().getType() == Material.PAPER && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBankNotes §8(Clique Droit)")){
            if (p.getGameMode() == GameMode.CREATIVE){
                p.sendMessage("§8[§aBank Note§8] §cEn attente d'une mise à jour du plugin, vous ne pouvez pas encaissé en mode créatif");
                return;
            }
            String M = e.getItem().getItemMeta().getLore().get(0);
            M = M.replaceAll("§8▪ §7Moneys: §a", "");
            String S = e.getItem().getItemMeta().getLore().get(1);
            S = S.replaceAll("§8▪ §7Déposé par: §f", "");
            String ID = e.getItem().getItemMeta().getLore().get(2);
            ID = ID.replaceAll("§8▪ §7ID de transaction: §f", "");
            M = M.replace(".", ",");

            System.out.println(M);
            BigDecimal Money = new BigDecimal(0);
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setGroupingSeparator(',');
            String pattern = "###,###,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
            decimalFormat.setParseBigDecimal(true);
            try {
                Money = (BigDecimal) decimalFormat.parse(M);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }



            if (Economy.playerExists(p.getName())){
                try {
                    Economy.add(p.getName(), Money);
                } catch (UserDoesNotExistException | NoLoanPermittedException e1) {
                    e1.printStackTrace();
                }

                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Bank Note" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Withdraw:");
                Bukkit.getConsoleSender().sendMessage("§7Moneys: §a" + M);
                Bukkit.getConsoleSender().sendMessage("§7Déposé par: §f" + S);
                Bukkit.getConsoleSender().sendMessage("§7Encaissé par: §f" + p.getName());
                Bukkit.getConsoleSender().sendMessage("§7ID de transaction: §f" + ID);

                p.getInventory().remove(e.getItem());
                p.sendMessage("§8[§aBank Note§8] §7Vous avez encaissé §a§l" + M + "$§7 venant de §a" + S);
            } else {
                p.sendMessage("§8[§aBank Note§8] §cVous n'avez pas de compte");
            }
        }
    }
}
