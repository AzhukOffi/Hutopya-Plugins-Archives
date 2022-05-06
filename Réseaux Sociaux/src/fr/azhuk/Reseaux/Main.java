package fr.azhuk.Reseaux;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.azhuk.Reseaux.commands.CommandReseaux;
import fr.azhuk.Reseaux.events.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.UUID;

public class Main extends JavaPlugin {

    private Connection connection;
    public String host, database, username, password, table_player, table_transfer;
    public int port;


    @Override
    public void onEnable() {
        loadConfig();
        mysqlSetup();
        getCommand("reseaux").setExecutor(new CommandReseaux(this));
        Bukkit.getPluginManager().registerEvents(new CommandReseaux(this), this);
        Bukkit.getPluginManager().registerEvents(new Chat(this), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");;
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void mysqlSetup(){
        host = this.getConfig().getString("host");
        port = this.getConfig().getInt("port");
        database = this.getConfig().getString("database");
        username = this.getConfig().getString("username");
        password = this.getConfig().getString("password");
        table_player = this.getConfig().getString("table_player");
        table_transfer = this.getConfig().getString("table_transfer");



        try {
           synchronized (this){
               if(getConnection() != null && !getConnection().isClosed()){
                   return;
               }

               Class.forName("com.mysql.jdbc.Driver");
               setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password));

               Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " MYSQL Connecté");
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static ItemStack getHead(String name)
    {
        for (Heads head : Heads.values())
        {
            if (head.getName().equalsIgnoreCase(name))
            {
                return head.getItemStack();
            }
        }
        return null;
    }

    public static ItemStack createSkull(String url, String name)
    {
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

    public void refreshConnection() {

        try {
            if (getConnection().isClosed()){
                mysqlSetup();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
