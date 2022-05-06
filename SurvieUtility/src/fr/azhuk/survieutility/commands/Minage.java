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

public class Minage implements CommandExecutor {

    Main plugin;

    public Minage(Main instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Random r = new Random();

            int randomNum = r.nextInt(plugin.getConfig().getInt("randomtp.max"));

            int x = randomNum;
            int y = 200;
            int z = randomNum;

            Location rp = new Location (Bukkit.getServer().getWorld("minage"), x, y, z);
            Player p = (Player) sender;
            p.teleport(rp);
            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 9999));
        }

        return false;
    }
}
