package fr.azhuk.hub.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Settings implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 1) {
                p.sendMessage("§cMerci de renseigner au maximum 1 argument");
            }
            Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6Paramètres");

            ItemStack speed = new ItemStack(Material.SUGAR, 1);
            ItemMeta speedMeta = speed.getItemMeta();
            speedMeta.setDisplayName("§bSpeed III");
            speed.setItemMeta(speedMeta);

            ItemStack fly = new ItemStack(Material.FEATHER, 1);
            ItemMeta flyMeta = fly.getItemMeta();
            flyMeta.setDisplayName("§6Fly");
            fly.setItemMeta(flyMeta);

            ItemStack jump = new ItemStack(Material.GOLDEN_BOOTS, 1);
            ItemMeta jumpMeta = jump.getItemMeta();
            jumpMeta.setDisplayName("§aJump III");
            jump.setItemMeta(jumpMeta);



            inv.setItem(11, speed);
            inv.setItem(13, fly);
            inv.setItem(15, jump);

            p.openInventory(inv);
            return true;
        }
        return false;
    }
}
