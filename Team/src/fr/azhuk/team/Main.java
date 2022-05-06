package fr.azhuk.team;

import fr.azhuk.team.commands.Claim;
import fr.azhuk.team.commands.Team;
import fr.azhuk.team.commands.TeamCompleter;
import fr.azhuk.team.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public String host, database, username, password, table_team, table_player, table_transfer, table_invite, table_claim;
    public FileConfiguration messages = YamlConfiguration.loadConfiguration(getFile("messages"));
    public FileConfiguration interactable_block = YamlConfiguration.loadConfiguration(getFile("interactable_block"));
    public int port;
    private Connection connection;
    public ArrayList<Material> interactable = new ArrayList<Material>();



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
        createFile("mysql");
        createFile("messages");
        createFile("interactable_block");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new onJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onInvClick(), this);
        Bukkit.getPluginManager().registerEvents(new onChat(), this);
        Bukkit.getPluginManager().registerEvents(new onLeave(), this);
        Bukkit.getPluginManager().registerEvents(new onPlace(), this);
        Bukkit.getPluginManager().registerEvents(new onBreak(), this);
        Bukkit.getPluginManager().registerEvents(new Interact(), this);
    }

    @Override
    public void onEnable() {
        loadConfig();
        if (!mysqlSetup()){
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Team" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Echec MySQL - Arrêt du plugin");
            Bukkit.getPluginManager().disablePlugins();
            return;
        }
        registerListeners();
        getCommand("team").setExecutor(new Team());
        getCommand("team").setTabCompleter(new TeamCompleter());

        getCommand("claim").setExecutor(new Claim());

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Team" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }



    public boolean mysqlSetup(){
        FileConfiguration mysql = YamlConfiguration.loadConfiguration(getFile("mysql"));
        host = mysql.getString("host");
        port = mysql.getInt("port");
        database = mysql.getString("database");
        username = mysql.getString("username");
        password = mysql.getString("password");
        table_team = mysql.getString("table_team");
        table_player = mysql.getString("table_player");
        table_transfer = mysql.getString("table_transfer");
        table_invite = mysql.getString("table_invite");
        table_claim = mysql.getString("table_claim");


        try {
            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return false;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Team" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " MYSQL Connecté");
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
