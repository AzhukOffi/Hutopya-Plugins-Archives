package fr.azhuk.Reseaux;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MysqlSetterGetter {

    Main plugin = Main.getPlugin(Main.class);


    public boolean mysqlWork(){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player);

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " MYSQL Déconnecté");
            return false;
        }
        return false;
    }

    public boolean playerExists(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE UUID=?");
            statement.setString(1, uuid.toString());

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void createPlayer(final UUID uuid, Player player){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (playerExists(uuid) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_player + " (UUID,player_name,twitch,discord,instagram,twitter,visible) VALUE (?,?,?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setString(3, "inconnu");
                insert.setString(4, "inconnu");
                insert.setString(5, "inconnu");
                insert.setString(6, "inconnu");
                insert.setBoolean(7, false);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTwitch(String username, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET twitch=? WHERE uuid=?");
            statement.setString(1, username);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateDiscord(String username, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET discord=? WHERE uuid=?");
            statement.setString(1, username);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateInstagram(String username, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET instagram=? WHERE uuid=?");
            statement.setString(1, username);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTwitter(String username, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET twitter=? WHERE uuid=?");
            statement.setString(1, username);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateVisible(Boolean visible, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET visible=? WHERE uuid=?");
            statement.setBoolean(1, visible);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getTwitch(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("twitch");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getDiscord(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("discord");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getInstagram(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("instagram");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getTwitter(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("twitter");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean getVisible(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getBoolean("visible");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean transferExists(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
            statement.setString(1, uuid.toString());

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void transferCreate(final UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (transferExists(uuid) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_transfer + " (uuid,recup,service) VALUE (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setBoolean(2, false);
                insert.setString(3, "aucun");
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void transferUpdate(Boolean recup, UUID uuid, String service){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_transfer + " SET recup=?, service=? WHERE uuid=?");
            statement.setBoolean(1, recup);
            statement.setString(3, uuid.toString());
            statement.setString(2, service);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean transferGet(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            if(rs.isBeforeFirst()) {
                rs.next();
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " ERREUR");
                return false;
            }
            return rs.getBoolean("recup");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String transferString(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet rs = statement.executeQuery();
            if(rs.isBeforeFirst()) {
                rs.next();
            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Réseaux Sociaux" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " ERREUR");
                return null;
            }
            return rs.getString("service");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
