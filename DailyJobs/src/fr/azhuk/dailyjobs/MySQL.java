package fr.azhuk.dailyjobs;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQL {

    Main plugin = Main.getPlugin(Main.class);

    public boolean playerExists(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
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
    public void createPlayer(final UUID uuid, Player player, String J1, String J2, String J3, int A1, int A2, int A3){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (playerExists(uuid) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table + " (UUID,player_name,jobs1,jobs2,jobs3,avancement1,avancement2,avancement3) VALUE (?,?,?,?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setString(3, J1);
                insert.setString(4, J2);
                insert.setString(5, J3);
                insert.setInt(6, A1);
                insert.setInt(7, A2);
                insert.setInt(8, A3);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
