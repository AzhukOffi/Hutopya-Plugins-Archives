package fr.azhuk.CustomEnchants;

import fr.azhuk.CustomEnchants.Enchants.Lighting;
import fr.azhuk.CustomEnchants.Enchants.OreAmplifier;
import fr.azhuk.CustomEnchants.Enchants.Télékinésie;
import fr.azhuk.CustomEnchants.Enchants.XPBoost;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    public static Main plugin;

    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();
    public static Télékinésie Telekinesie;
    public static OreAmplifier OreAmplifier;
    public static Lighting Lighting;
    public static XPBoost XPBoost;


    @Override
    public void onEnable() {
        plugin = this;

        Telekinesie = new Télékinésie();
        OreAmplifier = new OreAmplifier();
        Lighting = new Lighting();
        XPBoost = new XPBoost();

        //Add each enchantment to list so we can use
        custom_enchants.add(Telekinesie);
        custom_enchants.add(OreAmplifier);
        custom_enchants.add(Lighting);
        custom_enchants.add(XPBoost);

        //Register custom enchantments
        registerEnchantment(Telekinesie);
        registerEnchantment(OreAmplifier);
        registerEnchantment(Lighting);
        registerEnchantment(XPBoost);

        getServer().getPluginManager().registerEvents(new Listener(), this);
        getServer().getPluginManager().registerEvents(new Télékinésie(), this);
        getServer().getPluginManager().registerEvents(new OreAmplifier(), this);
        getServer().getPluginManager().registerEvents(new Lighting(), this);
        getServer().getPluginManager().registerEvents(new XPBoost(), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "CustomEnchants" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(Telekinesie.getKey())) {
                byKey.remove(Telekinesie.getKey());
            }
            if(byKey.containsKey(OreAmplifier.getKey())) {
                byKey.remove(OreAmplifier.getKey());
            }
            if(byKey.containsKey(Lighting.getKey())) {
                byKey.remove(Lighting.getKey());
            }
            if(byKey.containsKey(XPBoost.getKey())) {
                byKey.remove(XPBoost.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(Telekinesie.getName())) {
                byName.remove(Telekinesie.getName());
            }
            if(byName.containsKey(OreAmplifier.getName())) {
                byName.remove(OreAmplifier.getName());
            }
            if(byName.containsKey(Lighting.getName())) {
                byName.remove(Lighting.getName());
            }
            if(byName.containsKey(XPBoost.getName())) {
                byName.remove(XPBoost.getName());
            }
        } catch (Exception ignored) { }
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "CustomEnchants" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }


}
