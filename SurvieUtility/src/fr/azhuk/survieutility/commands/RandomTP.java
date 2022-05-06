package fr.azhuk.survieutility.commands;

import fr.azhuk.survieutility.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomTP implements CommandExecutor {

    Main plugin;

    public RandomTP(Main instance) {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Random r = new Random();

            int randomNum = r.nextInt(plugin.getConfig().getInt("randomtp.max"));//Will return a number between 1 and 501
            int min = plugin.getConfig().getInt("randomtp.min");

            int x = randomNum;
            int y = 200;
            int z = randomNum;

            while (x < min && y < min) {
                x = randomNum;
                z = randomNum;
            }

            Location rp = new Location (Bukkit.getServer().getWorld("world"), x, y, z);
            Player p = (Player) sender;
            p.teleport(rp);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 9999));
            return true;
        }
        return false;
    }
}
