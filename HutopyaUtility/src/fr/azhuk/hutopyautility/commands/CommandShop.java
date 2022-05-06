package fr.azhuk.hutopyautility.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage("§7[§c!§7] §cSeul la console peut utiliser cette commande");
            return false;
        }
        if (args.length == 2){
            Player p = Bukkit.getPlayer(args[0]);
            switch (args[1]){
                case "main":
                    p.chat("/shop");
                    break;
                case "blocks":
                    p.chat("/shop blocks");
                    break;
                case "concrete":
                    p.chat("/shop concrete");
                    break;
                case "terracotta":
                    p.chat("/shop terracotta");
                    break;
                case "quartz":
                    p.chat("/shop quartz");
                    break;
                case "slab":
                    p.chat("/shop slab");
                    break;
                case "glass":
                    p.chat("/shop glass");
                    break;
                case "stairs":
                    p.chat("/shop stairs");
                    break;
                case "fence":
                    p.chat("/shop fence");
                    break;
                case "stone":
                    p.chat("/shop stone");
                    break;
                case "dirt":
                    p.chat("/shop dirt");
                    break;
                case "wool":
                    p.chat("/shop wool");
                    break;
                case "sand":
                    p.chat("/shop sand");
                    break;
                case "ore":
                    p.chat("/shop ore");
                    break;
                case "wood":
                    p.chat("/shop wood");
                    break;
                case "naturel":
                    p.chat("/shop naturel");
                    break;
                case "agriculture":
                    p.chat("/shop agriculture");
                    break;
                case "drops":
                    p.chat("/shop drops");
                    break;
                case "sapling":
                    p.chat("/shop sapling");
                    break;
                case "flower":
                    p.chat("/shop flower");
                    break;
                case "leaves":
                    p.chat("/shop leaves");
                    break;
                case "outilsarmures":
                    p.chat("/shop outilsarmures");
                    break;
                case "outils":
                    p.chat("/shop outils");
                    break;
                case "armures":
                    p.chat("/shop armures");
                    break;
                case "autres":
                    p.chat("/shop autres");
                    break;
                case "dye":
                    p.chat("/shop dye");
                    break;
                case "bed":
                    p.chat("/shop bed");
                    break;
                case "minecart":
                    p.chat("/shop minecart");
                    break;
                case "door":
                    p.chat("/shop door");
                    break;
                case "carpet":
                    p.chat("/shop carpet");
                    break;
            }
        }
        return false;
    }
}
