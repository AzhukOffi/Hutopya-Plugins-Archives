package fr.azhuk.hoppers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {



    Main plugin = Main.getPlugin(Main.class);


    public boolean hoppperExists(Location loc){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_hopper + " WHERE X=? AND Y=? AND Z=? AND World=?");
            statement.setDouble(1, loc.getX());
            statement.setDouble(2, loc.getY());
            statement.setDouble(3, loc.getZ());
            statement.setString(4, loc.getWorld().getName());

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hoppperIdExists(int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_hopper + " WHERE id=?");
            statement.setInt(1, id);

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createHopper(Location loc, int lvl){
        try {
            if (hoppperExists(loc) != true){

                //hopper db
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_hopper + " (X,Y,Z,world,level) VALUE (?,?,?,?,?)");
                insert.setDouble(1, loc.getX());
                insert.setDouble(2, loc.getY());
                insert.setDouble(3, loc.getZ());
                insert.setString(4, loc.getWorld().getName());
                insert.setInt(5, lvl);
                insert.executeUpdate();

                //whitelist db
                PreparedStatement whitelist = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_whitelist + " (id_hopper,id_1,id_2,id_3,id_4,id_5,id_6) VALUE (?,?,?,?,?,?,?)");
                whitelist.setInt(1, getID(loc));
                whitelist.setString(2, "none");
                whitelist.setString(3, "none");
                whitelist.setString(4, "none");
                whitelist.setString(5, "none");
                whitelist.setString(6, "none");
                whitelist.setString(7, "none");
                whitelist.executeUpdate();

                //blacklist db
                PreparedStatement blacklist = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_blacklist + " (id_hopper,id_1,id_2,id_3,id_4,id_5,id_6) VALUE (?,?,?,?,?,?,?)");
                blacklist.setInt(1, getID(loc));
                blacklist.setString(2, "none");
                blacklist.setString(3, "none");
                blacklist.setString(4, "none");
                blacklist.setString(5, "none");
                blacklist.setString(6, "none");
                blacklist.setString(7, "none");
                blacklist.executeUpdate();

                //rejets db
                PreparedStatement rejets = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_rejets + " (id_hopper,id_1,id_2,id_3,id_4,id_5,id_6) VALUE (?,?,?,?,?,?,?)");
                rejets.setInt(1, getID(loc));
                rejets.setString(2, "none");
                rejets.setString(3, "none");
                rejets.setString(4, "none");
                rejets.setString(5, "none");
                rejets.setString(6, "none");
                rejets.setString(7, "none");
                rejets.executeUpdate();
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Hopper créé à la localisation X:" + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ() + " W: " + loc.getWorld().getName() + " lvl: " +  getLevel(loc));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHopper(Location loc, int lvl){
        try {
            if (hoppperExists(loc) == true){
                int id = getID(loc);

                //hopper db
                PreparedStatement insert = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_hopper + " WHERE X=? AND Y=? AND Z=? AND World=?");
                insert.setDouble(1, loc.getX());
                insert.setDouble(2, loc.getY());
                insert.setDouble(3, loc.getZ());
                insert.setString(4, loc.getWorld().getName());
                insert.executeUpdate();

                //blacklist db
                PreparedStatement blacklist = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_blacklist + " WHERE id_hopper=?");
                blacklist.setInt(1, id);
                blacklist.executeUpdate();

                //whitelist db
                PreparedStatement whitelist = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_whitelist + " WHERE id_hopper=?");
                whitelist.setInt(1, id);
                whitelist.executeUpdate();

                //rejets db
                PreparedStatement rejets = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_rejets + " WHERE id_hopper=?");
                rejets.setInt(1, id);
                rejets.executeUpdate();
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Hoppers" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " Hopper supprimé à la localisation X:" + loc.getX() + " Y: " + loc.getY() + " Z: " + loc.getZ() + " W: " + loc.getWorld().getName() + " lvl: " +  lvl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevel(Location loc){
        try {
            if (hoppperExists(loc) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_hopper + " WHERE X=? AND Y=? AND Z=? AND World=?");
                statement.setDouble(1, loc.getX());
                statement.setDouble(2, loc.getY());
                statement.setDouble(3, loc.getZ());
                statement.setString(4, loc.getWorld().getName());
                ResultSet results = statement.executeQuery();
                results.next();
                return results.getInt("level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateLevel(int id, int level){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_hopper + " SET level=? WHERE id=?");
            statement.setInt(1, level);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Location getLoc(int id){
        try {
            if (hoppperIdExists(id) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_hopper + " WHERE id=?");
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                results.next();
                return new Location(Bukkit.getWorld(results.getString("world")), results.getInt("x"), results.getInt("y"), results.getInt("z"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getID(Location loc){
        try {
            if (hoppperExists(loc) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_hopper + " WHERE X=? AND Y=? AND Z=? AND World=?");
                statement.setDouble(1, loc.getX());
                statement.setDouble(2, loc.getY());
                statement.setDouble(3, loc.getZ());
                statement.setString(4, loc.getWorld().getName());
                ResultSet results = statement.executeQuery();
                results.next();
                return results.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String[] getWhitelist(int id){
        try {
            if (hoppperIdExists(id) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_whitelist + " WHERE id_hopper=?");
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                results.next();
                String[] whitelist = {"none","none","none","none","none","none"};
                int i = 1;
                while (i <= 6){
                    whitelist[i-1] = results.getString("id_" + i);
                    i++;
                }
                return whitelist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String[] getRejets(int id){
        try {
            if (hoppperIdExists(id) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_rejets + " WHERE id_hopper=?");
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                results.next();
                String[] rejets = {"none","none","none","none","none","none"};
                int i = 1;
                while (i <= 6){
                    rejets[i-1] = results.getString("id_" + i);
                    i++;
                }
                return rejets;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getBlacklist(int id){
        try {
            if (hoppperIdExists(id) == true) {

                PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_blacklist + " WHERE id_hopper=?");
                statement.setInt(1, id);
                ResultSet results = statement.executeQuery();
                results.next();
                String[] blacklist = {"none","none","none","none","none","none"};
                int i = 1;
                while (i <= 6){
                    blacklist[i-1] = results.getString("id_" + i);
                    i++;
                }
                return blacklist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateWhitelist(String item_id, int id, int slot){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_whitelist + " SET id_?=? WHERE id_hopper=?");
            statement.setInt(1, slot);
            statement.setString(2, item_id);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBlacklist(String item_id, int id, int slot){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_blacklist + " SET id_?=? WHERE id_hopper=?");
            statement.setInt(1, slot);
            statement.setString(2, item_id);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRejets(String item_id, int id, int slot){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_rejets + " SET id_?=? WHERE id_hopper=?");
            statement.setInt(1, slot);
            statement.setString(2, item_id);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
