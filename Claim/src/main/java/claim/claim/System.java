package main.java.claim.claim;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public final class System extends JavaPlugin implements Listener{

    //config
    private File claimFile = new File(this.getDataFolder(), "Claims/claims.yml");
    private File langName = new File(this.getDataFolder(), "Claims/lang.yml");
    private FileConfiguration claimFileConfig = YamlConfiguration.loadConfiguration(claimFile);
    private FileConfiguration langFileConfig = YamlConfiguration.loadConfiguration(langName);

    private CommandHandler commandHandler;

    //vault
    private Economy economy = null;

    public System(){
        initEvent();
    }

    @Override
    public void onEnable() {

        // Plugin startup logic
        setupEconomy();
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        try {
            claimFileConfig.save(claimFile);
        } catch(IOException e){
            e.printStackTrace();
        }
        try {
            langFileConfig.save(langName);
            if(!langFileConfig.isSet("MENU_NAME")){
                //setDefaultLangFile();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        return commandHandler.processCommand(getServer(), sender, command, label, args);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    CommandEvent commandEvent = new CommandEvent() {
        @Override
        public void openMenu(Player player) {

            int totalClaimOfPlayer = Claim.getTotalClaimOfPlayer(player, claimFileConfig);
            int freePlayerClaims = Integer.parseInt(langFileConfig.getString("FREE_CLAIMS"));
            int remainingClaims = freePlayerClaims - totalClaimOfPlayer;
            if (remainingClaims < 0) {
                remainingClaims = 0;
            }

            Gui gui = new Gui(player, 9, langFileConfig.getString("MENU_NAME"));
            gui.addItem(Material.EMERALD, "Voir la map des claims", null, null);
            gui.addItem(Material.IRON_INGOT, "Acheter ce claim", null, null);
            gui.addItem(Material.REDSTONE, "Vendre ce claim", null, null);
            gui.addItem(Material.GOLD_INGOT, "Nombre de claim possédé: " + totalClaimOfPlayer,
                    "Claims gratuit restant: " + remainingClaims,
                    "Coût du prochain claim: " + getClaimNextPrice(freePlayerClaims, totalClaimOfPlayer));
            gui.open();
        }

        @Override
        public void invalidCommand(Player player) {
            //Send help message
            Object helpArray[] = langFileConfig.getConfigurationSection("help_menu").getKeys(false).toArray();
            for (Object key : helpArray) {
                player.sendMessage(langFileConfig.getString("help_menu." + key));
            }
        }

        @Override
        public void help(Player player) {
            //Send help message
            Object helpArray[] = langFileConfig.getConfigurationSection("help_menu").getKeys(false).toArray();
            for (Object key : helpArray) {
                player.sendMessage(langFileConfig.getString("help_menu." + key));
            }
        }

        @Override
        public void check(Player player) {
            //Check if player is in a claim
            if(Claim.claimExist(Claim.locationToString(player.getLocation()), claimFileConfig)){
                player.sendMessage(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ());
            }else
            {
                player.sendMessage(langFileConfig.getString("PLAYER_IS_NOT_IN_A_CLAIM"));
            }
        }

        @Override
        public void coop(Player player, Player coopPlayer) {
            //Add player to all claims
            Claim claim = new Claim(Claim.locationToString(player.getLocation()), claimFileConfig, claimFile);

            if(claim.isOwner(player.getUniqueId().toString())){
                claim.addMember(coopPlayer.getUniqueId().toString());
                player.sendMessage(langFileConfig.getString("PLAYER_HAS_BEEN_ADDED_TO_CLAIM"));
            }
        }

        @Override
        public void uncoop(Player player, Player uncoopPlayer) {
            Claim claim = new Claim(Claim.locationToString(player.getLocation()), claimFileConfig, claimFile);

            if(claim.isOwner(player.getUniqueId().toString())){
                player.sendMessage("uncoop");
                claim.removeMember(uncoopPlayer.getUniqueId().toString());
            }
        }

        @Override
        public void sell(Player player, Location location) {
            if(Claim.claimExist(Claim.locationToString(location), claimFileConfig)) {
                Claim claim = new Claim(Claim.locationToString(location), claimFileConfig, claimFile);
                if(claim.isOwner(player.getUniqueId().toString())){
                    claim.delete();
                    player.sendMessage(langFileConfig.getString("CLAIM_SOLD"));
                }else{
                    player.sendMessage(langFileConfig.getString("PLAYER_IS_NOT_OWNER_OF_REGION"));
                }
            }
        }

        @Override
        public void buy(Player player, Location location) {

            if(!Claim.claimExist(Claim.locationToString(location), claimFileConfig)){
                int playerClaimsNum = Claim.getTotalClaimOfPlayer(player, claimFileConfig);
                int freePlayerClaims = Integer.parseInt(langFileConfig.getString("FREE_CLAIMS"));
                int remainingClaims = freePlayerClaims - playerClaimsNum;
                if (remainingClaims < 0) {
                    remainingClaims = 0;
                }

                Claim claim = new Claim(Claim.locationToString(location), claimFileConfig, claimFile);

                OfflinePlayer offlinePlayer = (OfflinePlayer) player;
                if (economy.getBalance(offlinePlayer) >= getClaimNextPrice(freePlayerClaims, playerClaimsNum)) {
                    economy.withdrawPlayer(offlinePlayer, getClaimNextPrice(freePlayerClaims, playerClaimsNum));
                    claim.setOwner(player.getUniqueId().toString());
                    player.sendMessage(langFileConfig.getString("PLAYER_PURCHASED_CLAIM"));
                } else {
                    player.sendMessage(langFileConfig.getString("PLAYER_DOESNT_HAVE_ENOUGH_MONEY"));
                }

            }
        }
    };

    private void initEvent(){
        commandHandler = new CommandHandler(commandEvent);
    }

    //Deny player actions if he is in someone else claim
    @EventHandler
    public void playerInteract(PlayerInteractEvent event){
        if(!event.getPlayer().hasPermission("claim.override")) {
            if(event.getClickedBlock() != null) {
                if (Claim.claimExist(Claim.locationToString(event.getClickedBlock().getLocation()), claimFileConfig)) {
                    Claim claim = new Claim(Claim.locationToString(event.getClickedBlock().getLocation()), claimFileConfig, claimFile);
                    if (!claim.isOwner(event.getPlayer().getUniqueId().toString())) {
                        if (!claim.isMember(event.getPlayer().getUniqueId().toString())) {

                            try {
                                String url = "jdbc:mysql://5.196.39.210/team";
                                Connection conn = DriverManager.getConnection(url, "team", "rzU2jGlHZK0NQo5w");
                                Statement stmt = conn.createStatement();

                                ResultSet rs;
                                String playerTeam = "-1";

                                rs = stmt.executeQuery("SELECT * FROM `player`");
                                while(rs.next()){
                                    if(rs.getString("name").equalsIgnoreCase(event.getPlayer().getName())){
                                        playerTeam = rs.getString("team");
                                        break;
                                    }
                                }

                                rs = stmt.executeQuery("SELECT * FROM `team`");

                                boolean claimIsInTeam = false;

                                while(rs.next()){
                                    if(playerTeam.equalsIgnoreCase(rs.getString("team"))){
                                        if(claim.isOwner(rs.getString("uuid"))){
                                            claimIsInTeam = true;
                                            break;
                                        }
                                    }
                                }
                                conn.close();
                                if(!claimIsInTeam){
                                    event.setCancelled(true);
                                    event.getPlayer().sendMessage(langFileConfig.getString("PLAYER_UNAUTHORIZED"));
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
            else {
                if (Claim.claimExist(Claim.locationToString(event.getPlayer().getLocation()), claimFileConfig)) {
                    Claim claim = new Claim(Claim.locationToString(event.getPlayer().getLocation()), claimFileConfig, claimFile);
                    if (!claim.isOwner(event.getPlayer().getUniqueId().toString())) {
                        if (!claim.isMember(event.getPlayer().getUniqueId().toString())) {
                            try {
                                String url = "jdbc:mysql://5.196.39.210/team";
                                Connection conn = DriverManager.getConnection(url, "team", "rzU2jGlHZK0NQo5w");
                                Statement stmt = conn.createStatement();

                                ResultSet rs;
                                String playerTeam = "-1";

                                rs = stmt.executeQuery("SELECT * FROM `player`");
                                while(rs.next()){
                                    if(rs.getString("name").equalsIgnoreCase(event.getPlayer().getName())){
                                        playerTeam = rs.getString("team");
                                        break;
                                    }
                                }

                                rs = stmt.executeQuery("SELECT * FROM `team`");

                                boolean claimIsInTeam = false;

                                while(rs.next()){
                                    if(playerTeam.equalsIgnoreCase(rs.getString("team"))){
                                        if(claim.isOwner(rs.getString("uuid"))){
                                            claimIsInTeam = true;
                                            break;
                                        }
                                    }
                                }
                                conn.close();
                                if(!claimIsInTeam){
                                    event.setCancelled(true);
                                    event.getPlayer().sendMessage(langFileConfig.getString("PLAYER_UNAUTHORIZED"));
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        if(event.getView().getTitle().equalsIgnoreCase(langFileConfig.getString("MENU_NAME"))){
            event.setCancelled(true);

            Player player = (Player)event.getWhoClicked();
            if(event.getCurrentItem().getType().equals(Material.IRON_INGOT)){
                commandEvent.buy(player, player.getLocation());
            }
            if(event.getCurrentItem().getType().equals(Material.REDSTONE)){
                commandEvent.sell(player, player.getLocation());
            }
            if(event.getCurrentItem().getType().equals(Material.EMERALD)){
                generateClaimMap(player);
            }
        }
        else if(event.getView().getTitle().equalsIgnoreCase("Claim map")){
            event.setCancelled(true);

            Player player = (Player)event.getWhoClicked();

            if(event.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)){

                if(!Claim.claimExist(event.getCurrentItem().getItemMeta().getDisplayName(), claimFileConfig)){
                    int playerClaimsNum = Claim.getTotalClaimOfPlayer(player, claimFileConfig);
                    int freePlayerClaims = Integer.parseInt(langFileConfig.getString("FREE_CLAIMS"));
                    int remainingClaims = freePlayerClaims - playerClaimsNum;
                    if (remainingClaims < 0) {
                        remainingClaims = 0;
                    }

                    Claim claim = new Claim(event.getCurrentItem().getItemMeta().getDisplayName(), claimFileConfig, claimFile);

                    OfflinePlayer offlinePlayer = (OfflinePlayer) player;
                    if (economy.getBalance(offlinePlayer) >= getClaimNextPrice(freePlayerClaims, playerClaimsNum)) {
                        economy.withdrawPlayer(offlinePlayer, getClaimNextPrice(freePlayerClaims, playerClaimsNum));
                        claim.setOwner(player.getUniqueId().toString());
                        player.sendMessage(langFileConfig.getString("PLAYER_PURCHASED_CLAIM"));
                    } else {
                        player.sendMessage(langFileConfig.getString("PLAYER_DOESNT_HAVE_ENOUGH_MONEY"));
                    }

                }

            }
            generateClaimMap(player);

        }
    }

    private void generateClaimMap(Player player){
        Gui gui = new Gui(player, 54, "Claim Map");

        int initialXPos = player.getLocation().getChunk().getX() - 3;
        int initialZPos = player.getLocation().getChunk().getZ() - 4;

        for(int x = initialXPos; x < initialXPos + 6; x++){
            for(int z = initialZPos; z < initialZPos + 9; z++){
                if(Claim.claimExist(x + "," + z, claimFileConfig)){
                    Claim claim = new Claim(x + "," + z, claimFileConfig, claimFile);
                    gui.addItem(Material.RED_STAINED_GLASS_PANE, "Claim " + x + "," + z + " appartient à:", Bukkit.getPlayer(UUID.fromString(claim.getOwner())).getName().toString());
                } else{
                    gui.addItem(Material.GREEN_STAINED_GLASS_PANE, x + "," + z, "Claim disponible");
                }
            }
        }
        gui.open();
    }

    private int getClaimNextPrice(int freeClaims, int AlreadyOwnedClaims){
        int retCode;
        if(AlreadyOwnedClaims < freeClaims){
            retCode = 0;
        }
        else{
            retCode = (50 * (AlreadyOwnedClaims - freeClaims)) + 100;
        }
        return retCode;
    }

}
