package fr.azhuk.hoppers;

import fr.azhuk.hoppers.commands.Hoppers;
import fr.azhuk.hoppers.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends JavaPlugin {

    public FileConfiguration messages = YamlConfiguration.loadConfiguration(getFile("messages"));
    private Connection connection;
    public String host, database, username, password, table_hopper, table_whitelist, table_rejets, table_blacklist;
    public int port;

    @Override
    public void onEnable() {
        if (mysqlSetup()){
            loadConfig();
            registerListeners();
            registerCommands();

            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Erreur MySQL, plugin désactivé");
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

    public void loadConfig(){
        createFile("messages");
        createFile("mysql");

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceBreak(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryDrag(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryMoveItem(), this);
    }

    private void registerCommands(){
        getCommand("hoppers").setExecutor(new Hoppers());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");;
    }

    public boolean mysqlSetup(){
        FileConfiguration mysql = YamlConfiguration.loadConfiguration(getFile("mysql"));
        host = mysql.getString("host");
        port = mysql.getInt("port");
        database = mysql.getString("database");
        username = mysql.getString("username");
        password = mysql.getString("password");
        table_hopper = mysql.getString("table_hopper");
        table_whitelist = mysql.getString("table_whitelist");
        table_rejets = mysql.getString("table_rejets");
        table_blacklist = mysql.getString("table_blacklist");


        try {
            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return false;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " MYSQL Connecté");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }



}
