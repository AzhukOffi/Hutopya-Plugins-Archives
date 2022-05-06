package fr.azhuk.team;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class MySQL {

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


    public boolean teamExists(String teamName){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE name=?");
            statement.setString(1, teamName);

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean teamIdExists(int teamId){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE id=?");
            statement.setInt(1, teamId);

            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean inviteExists(Player p, Player from){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_invite + " WHERE from_player=? and invited=?");
            statement.setString(1, from.getUniqueId().toString());
            statement.setString(2, p.getUniqueId().toString());
            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createInvite(int id, Player p, Player from){
        try {
            if (inviteExists(p, from) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_invite + " (team_id,invited,from_player) VALUE (?,?,?)");
                insert.setInt(1, id);
                insert.setString(2, p.getUniqueId().toString());
                insert.setString(3, from.getUniqueId().toString());
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void createTeam(String name, UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE name=?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            results.next();
            if (teamExists(name) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_team + " (name,description,prefix,item,owner) VALUE (?,?,?,?,?)");
                insert.setString(1, name);
                insert.setString(2, "Aucune description");
                insert.setString(3, "✘");
                insert.setString(4, "GRASS_BLOCK");
                insert.setString(5, uuid.toString());
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTransfer(UUID uuid){
        try {
            if (transferExists(uuid) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_transfer + " (uuid,type) VALUE (?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, "none");
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean transferExists(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
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

    public boolean playerExists(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
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

    public void createPlayer(Player p){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, p.getUniqueId().toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (playerExists(p.getUniqueId()) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_player + " (uuid,name,team,team_rank,coop_1,coop_2,coop_3,coop_4) VALUE (?,?,?,?,?,?,?,?)");
                insert.setString(1, p.getUniqueId().toString());
                insert.setString(2, p.getName());
                insert.setInt(3, 0);
                insert.setInt(4, 0);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.setInt(8, 0);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTransfer(UUID uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_transfer + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("type");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "none";
    }

    public Integer getIdFromOwner(String uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE owner=?");
            statement.setString(1, uuid);
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getOwner(int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE id=?");
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getString("owner");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getID(String name){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE name=?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTeamType(Player p, int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, p.getUniqueId().toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (results.getInt("team") == id){
                return "team";
            }
            if (results.getInt("coop_1") == id){
                return "coop_1";
            }
            if (results.getInt("coop_2") == id){
                return "coop_2";
            }
            if (results.getInt("coop_3") == id){
                return "coop_3";
            }
            if (results.getInt("coop_4") == id){
                return "coop_4";
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getRank(String uuid){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, uuid);
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("team_rank");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer getInvite(Player p, Player from){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_invite + " WHERE from_player=? and invited=?");
            statement.setString(1, from.getUniqueId().toString());
            statement.setString(2, p.getUniqueId().toString());

            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("team_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getMembersWithType(int id, int type){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE team=? AND team_rank=?");
            statement.setInt(1, id);
            statement.setInt(2, type);
            ResultSet results = statement.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (results.next()){
                list.add(results.getString("name"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getMembers(int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE team=? OR coop_1=? OR coop_2=? OR coop_3=? OR coop_4=?");
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.setInt(3, id);
            statement.setInt(4, id);
            statement.setInt(5, id);
            ResultSet results = statement.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (results.next()){
                list.add(results.getString("uuid"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public ArrayList<Integer> getTeams(OfflinePlayer p){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_player + " WHERE uuid=?");
            statement.setString(1, p.getUniqueId().toString());
            ResultSet results = statement.executeQuery();
            ArrayList<Integer> list = new ArrayList<>();
            results.next();
            list.add(results.getInt("team"));
            list.add(results.getInt("coop_1"));
            list.add(results.getInt("coop_2"));
            list.add(results.getInt("coop_3"));
            list.add(results.getInt("coop_4"));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getInfos(int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE id=?");
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            results.next();
            list.add(results.getString("name"));
            list.add(results.getString("description"));
            list.add(results.getString("prefix"));
            list.add(results.getString("item"));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePlayerTeam(Player p, int id, String type){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET " + type + "=? WHERE uuid=?");
            statement.setInt(1, id);
            statement.setString(2, p.getUniqueId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayerRank(Player p, int rank){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET team_rank=? WHERE uuid=?");
            statement.setInt(1, rank);
            statement.setString(2, p.getUniqueId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updateTeamOwner(Player p, int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_team + " SET owner=? WHERE id=?");
            statement.setString(1, p.getUniqueId().toString());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeamName(int id, String value){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_team + " SET name=? WHERE id=?");
            statement.setString(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeamIcon(int id, String value){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_team + " SET item=? WHERE id=?");
            statement.setString(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeamDesc(int id, String value){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_team + " SET description=? WHERE id=?");
            statement.setString(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTeamPrefix(int id, String value){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_team + " SET prefix=? WHERE id=?");
            statement.setString(1, value);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTransfer(UUID uuid, String type){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_transfer + " SET type=? WHERE uuid=?");
            statement.setString(1, type);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isOwner(UUID uuid, int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_team + " WHERE id=?");
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            results.next();
            if (results.getString("owner").equalsIgnoreCase(uuid.toString())){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteTeam(int id){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_team + " WHERE id=?");
            statement.setInt(1, id);
            statement.execute();

            statement  = plugin.getConnection().prepareStatement("UPDATE " + plugin.table_player + " SET team=0 WHERE team=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInvite(Player p, Player from){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_invite + " WHERE from_player=? and invited=?");
            statement.setString(1, p.getUniqueId().toString());
            statement.setString(2, from.getUniqueId().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteInviteToPlayer(Player p){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.table_invite + " WHERE invited=?");
            statement.setString(1, p.getUniqueId().toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createClaim(Chunk chunk, World world, int team){
        try {
            if (claimExists(chunk, world) != true){
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table_claim + " (chunk,world,owner) VALUE (?,?,?)");
                insert.setString(1, chunk.toString());
                insert.setString(2, world.getName());
                insert.setInt(3, team);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean claimExists(Chunk chunk, World world){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_claim + " WHERE chunk=? and world=?");
            statement.setString(1, chunk.toString());
            statement.setString(2, world.getName());
            ResultSet results = statement.executeQuery();
            if (results.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer getClaimOwner(Chunk chunk, World world){
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table_claim + " WHERE chunk=? and world=?");
            statement.setString(1, chunk.toString());
            statement.setString(2, world.getName());
            ResultSet results = statement.executeQuery();
            results.next();
            return results.getInt("owner");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
