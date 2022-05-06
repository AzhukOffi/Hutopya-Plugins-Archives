package fr.azhuk.BankNotes;

import fr.azhuk.BankNotes.Listener.OnClick;
import fr.azhuk.BankNotes.commands.CommandWithdraw;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new OnClick(), this);
        getCommand("withdraw").setExecutor(new CommandWithdraw());
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Bank Note" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Bank Note" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");;
    }

}
