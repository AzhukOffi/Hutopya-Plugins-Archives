package fr.azhuk.dailyjobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends JavaPlugin {

    private Connection connection;
    public String host, database, username, password, table;
    public int port;


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JobsListener(this), this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "DailyJobs" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "DailyJobs" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Désactivation");;
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
        table = this.getConfig().getString("table");


        try {
            synchronized (this){
                if(getConnection() != null && !getConnection().isClosed()){
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password));

                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "DailyJobs" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " MYSQL Connecté");
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
