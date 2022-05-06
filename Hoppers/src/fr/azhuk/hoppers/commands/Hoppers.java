package fr.azhuk.hoppers.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hoppers implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;

            if (args.length == 2){
                Player argP = Bukkit.getPlayer(args[0]);

                //ItemStack des hoppers
                ItemStack hopper1 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper1meta = hopper1.getItemMeta();
                hopper1meta.setDisplayName("§6Hopper level §81");
                hopper1.setItemMeta(hopper1meta);

                ItemStack hopper2 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper2meta = hopper2.getItemMeta();
                hopper2meta.setDisplayName("§6Hopper level §82");
                hopper2.setItemMeta(hopper2meta);

                ItemStack hopper3 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper3meta = hopper3.getItemMeta();
                hopper3meta.setDisplayName("§6Hopper level §83");
                hopper3.setItemMeta(hopper3meta);

                ItemStack hopper4 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper4meta = hopper4.getItemMeta();
                hopper4meta.setDisplayName("§6Hopper level §84");
                hopper4.setItemMeta(hopper4meta);

                ItemStack hopper5 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper5meta = hopper5.getItemMeta();
                hopper5meta.setDisplayName("§6Hopper level §85");
                hopper5.setItemMeta(hopper5meta);

                ItemStack hopper6 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper6meta = hopper6.getItemMeta();
                hopper6meta.setDisplayName("§6Hopper level §86");
                hopper6.setItemMeta(hopper6meta);

                ItemStack hopper7 = new ItemStack(Material.HOPPER, 1);
                ItemMeta hopper7meta = hopper7.getItemMeta();
                hopper7meta.setDisplayName("§6Hopper level §87");
                hopper7.setItemMeta(hopper7meta);

                //give par rapport au 2ème argument
                switch (args[1]){
                    case "1":
                        p.getInventory().addItem(hopper1);
                        break;
                    case "2":
                        p.getInventory().addItem(hopper2);
                        break;
                    case "3":
                        p.getInventory().addItem(hopper3);
                        break;
                    case "4":
                        p.getInventory().addItem(hopper4);
                        break;
                    case "5":
                        p.getInventory().addItem(hopper5);
                        break;
                    case "6":
                        p.getInventory().addItem(hopper6);
                        break;
                    case "7":
                        p.getInventory().addItem(hopper7);
                        break;
                }
            }



        }
        return false;
    }
}
