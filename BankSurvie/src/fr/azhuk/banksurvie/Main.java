package fr.azhuk.banksurvie;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.azhuk.banksurvie.commands.BankSurvie;
import fr.azhuk.banksurvie.commands.Money;
import fr.azhuk.banksurvie.listener.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static Glow Glow;
    public static ArrayList<Enchantment> custom_enchants = new ArrayList<>();


    public FileConfiguration messages = YamlConfiguration.loadConfiguration(getFile("messages"));
    public FileConfiguration data = YamlConfiguration.loadConfiguration(getFile("data"));

    public static Economy economy;


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

    @Override
    public void onEnable() {
        plugin = this;

        Glow = new Glow();

        //Add each enchantment to list so we can use
        custom_enchants.add(Glow);

        //Register custom enchantments
        registerEnchantment(Glow);

        setupEconomy();
        createFile("messages");
        createFile("data");
        getCommand("money").setExecutor(new Money(this));
        getCommand("banksurvie").setExecutor(new BankSurvie(this));
        registerEvents();
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BankSurvie" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new NPC(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(this), this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandManip(this), this);
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            if(byKey.containsKey(Glow.getKey())) {
                byKey.remove(Glow.getKey());
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            if(byName.containsKey(Glow.getName())) {
                byName.remove(Glow.getName());
            }
        } catch (Exception ignored) { }

        resetFile("data");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BankSurvie" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    private void resetFile(String filename){
        File file = new File(getDataFolder(), filename + ".yml");
        if (file.exists()){
            FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(getFile(filename));
            try {
                fileConfig.load(new InputStreamReader(getResource(filename + ".yml")));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
            try {
                fileConfig.save(getFile(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createFile(String filename){
        if (!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), filename + ".yml");

        if (!file.exists()){
            try {
                file.createNewFile();
                FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(getFile(filename));
                try {
                    fileConfig.load(new InputStreamReader(getResource(filename + ".yml")));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                try {
                    fileConfig.save(getFile(filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile(String filename){
        return new File(getDataFolder(), filename + ".yml");
    }


    public static ItemStack createSkull(String url, String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short)3);
        if (url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", url));

        try
        {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);

        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public static ItemStack getHead(String name) {
        for (Heads head : Heads.values())
        {
            if (head.getName().equalsIgnoreCase(name))
            {
                return head.getItemStack();
            }
        }
        return null;
    }
}
