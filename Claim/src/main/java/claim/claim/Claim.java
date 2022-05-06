package main.java.claim.claim;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Claim {

    private String chunkId;
    private FileConfiguration configFile;
    private File file;
    private int x;
    private int z;


    public Claim(String chunkId, FileConfiguration configFile, File file){
        this.chunkId = chunkId;

        this.file = file;

        String[] coord = chunkId.split(",");
        x = Integer.parseInt(coord[0]);
        z = Integer.parseInt(coord[1]);

        this.configFile = configFile;
    }

    static boolean claimExist(String chunkId, FileConfiguration configFile){
        return configFile.isSet(chunkId);
    }

    public void setOwner(String ownerUUID){
        configFile.set(chunkId + ".owner", ownerUUID);
        try {
            configFile.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addMember(String memberUUID){

        int totalMembers = 0;
        //get all members
        Object members[];
        if(configFile.isSet(chunkId + ".members")) {
            members = configFile.getConfigurationSection(chunkId + ".members").getKeys(false).toArray();
            totalMembers = members.length;
        }
        //add member
        configFile.set(chunkId + ".members" + ".member" + totalMembers, memberUUID);
        try {
            configFile.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getX(){
        return x;
    }

    public int getZ(){
        return z;
    }

    static public int getTotalClaimOfPlayer(Player player, FileConfiguration configFile){
        Object claims[] = configFile.getConfigurationSection("").getKeys(false).toArray();

        int totalClaimsOfPlayer = 0;

        for(Object claim : claims){
            if(configFile.get(claim.toString() + ".owner").equals(player.getUniqueId().toString())){
                totalClaimsOfPlayer++;
            }
        }
        return totalClaimsOfPlayer;
    }

    public void removeMember(String memberUUID){
        //get all members
        if(configFile.isSet(chunkId + ".members")) {
            Object members[] = configFile.getConfigurationSection(chunkId + ".members").getKeys(false).toArray();

            int i = 0;
            for (Object member : members) {
                if (memberUUID.equalsIgnoreCase(configFile.getString(chunkId + ".members." + member.toString()))) {
                    //we found good member
                    int remainingMembers = members.length - i;

                    for (i = i; i < remainingMembers - 1; i++) {
                        configFile.set(chunkId + ".members.member" + String.valueOf(i),
                                configFile.get(chunkId + ".members.member" + String.valueOf(i + 1)));
                    }

                    configFile.set(chunkId + ".members.member" + String.valueOf(i), null);
                    break;
                }
                i++;
            }
            try {
                configFile.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOwner(){
        if(configFile.isSet(chunkId + ".owner")){
            return configFile.get(chunkId + ".owner").toString();
        }
        else{
            return "NULL";
        }
    }

    public void delete(){
        configFile.set(chunkId, null);
        try {
            configFile.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public boolean isInsideRegion(Location location){
        boolean retCode = false;
        if(location.getChunk().getX() == x){
            if(location.getChunk().getZ() == z){
                retCode = true;
            }
        }
        return retCode;
    }

    public boolean isOwner(String playerUUID){
        boolean retCode = false;
        if(configFile.isSet(chunkId + ".owner")){
            if(playerUUID.equalsIgnoreCase(configFile.getString(chunkId + ".owner").toString())){
                retCode = true;
            }
        }
        return retCode;
    }

    public boolean isMember(String playerUUID) {
        boolean retCode = false;

        //get all members
        if(configFile.isSet(chunkId + ".members")) {
            Object members[] = configFile.getConfigurationSection(chunkId + ".members").getKeys(false).toArray();

            for (Object member : members) {
                if (member.toString().equalsIgnoreCase(playerUUID)) {
                    retCode = true;
                    break;
                }
            }
        }
        return retCode;
    }

    static public String locationToString(Location location){
        return location.getChunk().getX() + "," + location.getChunk().getZ();
    }
}
