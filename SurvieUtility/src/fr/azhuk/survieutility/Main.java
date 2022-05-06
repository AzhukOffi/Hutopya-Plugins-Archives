package fr.azhuk.survieutility;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.azhuk.survieutility.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin implements CommandExecutor {

    public Plugin[] pluginsTemp;
    public ArrayList<Plugin> plugins;
    public ArrayList<Plugin> InternalPlugin;


    int i = 0;

    public boolean fileExists(String filename){
        if (!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), filename + ".yml");

        if (!file.exists()){
            return false;
        }
        return true;
    }

    public void createFile(String filename){
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


    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void plPlayer(Player p){
        String internplug;
        String internplugFinal = "";
        for(Plugin plugin: InternalPlugin){
            internplug = internplugFinal;
            if (internplug.equalsIgnoreCase("")){
                internplugFinal = plugin.getName();
            } else {
                internplugFinal = internplug  + ", " + plugin.getName();
            }
        }

        String plug;
        String plugFinal = "";
        for(Plugin plugin: plugins){
            plug = plugFinal;
            if (plug.equalsIgnoreCase("")){
                plugFinal = plugin.getName();
            } else {
                plugFinal = plug  + ", " + plugin.getName();
            }
        }

        p.sendMessage("§6Hutopya - Serveur Survie");
        p.sendMessage("§aPlugins internes:");
        p.sendMessage("§6" + internplugFinal);
        p.sendMessage("");
        p.sendMessage("§cPlugins externes:");
        if (p.hasPermission("SurvieUtility.viewplugin")){
            p.sendMessage("§6" + plugFinal);
        } else {
            p.sendMessage("§4Vous n'avez pas accès aux plugins externes");
        }
    }

    public void plConsole(){
        String internplug;
        String internplugFinal = "";
        for(Plugin plugin: InternalPlugin){
            internplug = internplugFinal;
            if (internplug.equalsIgnoreCase("")){
                internplugFinal = plugin.getName();
            } else {
                internplugFinal = internplug  + ", " + plugin.getName();
            }
        }

        String plug;
        String plugFinal = "";
        for(Plugin plugin: plugins){
            plug = plugFinal;
            if (plug.equalsIgnoreCase("")){
                plugFinal = plugin.getName();
            } else {
                plugFinal = plug  + ", " + plugin.getName();
            }
        }

        Bukkit.getConsoleSender().sendMessage("§6Hutopya - Serveur Survie");
        Bukkit.getConsoleSender().sendMessage("§aPlugins internes:");
        Bukkit.getConsoleSender().sendMessage("§6" + internplugFinal);
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§cPlugins externes:");
        Bukkit.getConsoleSender().sendMessage("§6" + plugFinal);
    }

    @Override
    public void onEnable() {
        loadConfig();
        plugintri();
        getCommand("help").setExecutor(new Help(this));
        getCommand("rtp").setExecutor(new RandomTP(this));
        getCommand("discord").setExecutor(new Discord(this));
        getCommand("voteannounce").setExecutor(new VoteAnnounce());
        getCommand("minage").setExecutor(new Minage(this));
        getCommand("site").setExecutor(new Site(this));
        getCommand("survieutility").setExecutor(new SurvieUtility(this));
        Bukkit.getPluginManager().registerEvents(new Listener(this), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "SurvieUtility" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }


    public void plugintri(){
        i = 0;
        pluginsTemp = Bukkit.getServer().getPluginManager().getPlugins();
        plugins = new ArrayList<Plugin>(Arrays.asList(pluginsTemp));
        InternalPlugin = new ArrayList<Plugin>();
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            List<String> authors = new ArrayList<String>(plugin.getDescription().getAuthors());
            for (String author: authors){
                if (author.equalsIgnoreCase("Hutopya")){
                    InternalPlugin.add(plugin);
                    plugins.remove(plugin);
                }
            }
            i++;
        }
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
