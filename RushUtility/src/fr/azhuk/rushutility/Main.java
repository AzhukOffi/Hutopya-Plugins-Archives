package fr.azhuk.rushutility;

import me.TechsCode.UltraPermissions.UltraPermissions;
import me.TechsCode.UltraPermissions.UltraPermissionsAPI;
import me.TechsCode.UltraPermissions.storage.objects.Group;
import me.TechsCode.UltraPermissions.storage.objects.User;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.material.Bed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Main extends JavaPlugin implements CommandExecutor, Listener {

    boolean started = false;

    @Override
    public void onEnable() {
        Bukkit.getWorld("Rush").setDifficulty(Difficulty.NORMAL);
        getCommand("join").setExecutor(this);
        getCommand("leave").setExecutor(this);
        getCommand("help").setExecutor(this);
        getCommand("aide").setExecutor(this);
        getCommand("?").setExecutor(this);
        getCommand("start").setExecutor(this);
        getCommand("end").setExecutor(this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "RushUtility" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        Group bleu = UltraPermissions.getAPI().getGroups().name("bleu");
        Group orange = UltraPermissions.getAPI().getGroups().name("orange");
        if (cmd.getName().equalsIgnoreCase("end")){
            if (sender.hasPermission("admin")){
                for (Player p: Bukkit.getOnlinePlayers()){
                    User user = UltraPermissions.getAPI().getUsers().name(p.getName());

                    if (user.getGroups().id(orange.getId()) == orange){
                        user.removeGroup(orange);
                        p.teleport(new Location(p.getWorld(), -1.5, 55, -62.5));
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                    if (user.getGroups().id(bleu.getId()) == bleu){
                        user.removeGroup(bleu);
                        p.teleport(new Location(p.getWorld(), -1.5, 55, 61.5));
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                }
            } else {
                sender.sendMessage("§8[§c!§8]§c Tu n'as pas la permission !");
            }
        }
        if (cmd.getName().equalsIgnoreCase("start")){
            if (sender.hasPermission("admin")){
                ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
                Team teamBleu = scoreboard.registerNewTeam("bleu");
                Team teamOrange = scoreboard.registerNewTeam("orange");
                teamBleu.setAllowFriendlyFire(false);
                started = true;
                teamOrange.setAllowFriendlyFire(false);
                for (Player p: Bukkit.getOnlinePlayers()){
                    User user = UltraPermissions.getAPI().getUsers().name(p.getName());

                    if (user.getGroups().id(orange.getId()) == orange){
                        teamBleu.addPlayer(p);
                        user.newPermission("blockjoinleave");
                        p.teleport(new Location(p.getWorld(), -1.5, 55, -62.5));
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                    if (user.getGroups().id(bleu.getId()) == bleu){
                        teamOrange.addPlayer(p);
                        user.newPermission("blockjoinleave");
                        p.teleport(new Location(p.getWorld(), -1.5, 55, 61.5));
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }

            } else {
                sender.sendMessage("§8[§c!§8]§c Tu n'as pas la permission !");
            }
        }
        if (cmd.getName().equalsIgnoreCase("help") || cmd.getName().equalsIgnoreCase("?") || cmd.getName().equalsIgnoreCase("aide")){
            sender.sendMessage("§8");
            sender.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
            sender.sendMessage("§8");
            sender.sendMessage("§a/join <bleu|rouge> §f- §8Rejoindre une team");
            sender.sendMessage("§a/leave §f- §8Quitter sa team");
        }
        if (cmd.getName().equalsIgnoreCase("leave")){
            if (args.length == 1){
                if (sender.hasPermission("forceleave")){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                    if (offlinePlayer.isOnline()){
                        Player p = offlinePlayer.getPlayer();
                        User user = UltraPermissions.getAPI().getUsers().name(p.getName());
                        user.removeGroup(bleu);
                        user.removeGroup(orange);
                        p.teleport(new Location(p.getWorld(), -210.5, 50.5, -8.5));
                        p.sendMessage("§8[§c!§8]§c Vous avez été enlevé de votre team !");
                        return true;
                    } else {
                        sender.sendMessage("§8[§c!§8]§c Ce joueur n'est pas en ligne !");
                        return false;
                    }
                } else {
                    sender.sendMessage("§8[§c!§8]§c Tu n'as pas la permission !");
                    return false;
                }
            }
            if (args.length == 0){
                if (sender instanceof Player){
                    if (sender.hasPermission("blockjoinleave") && !sender.hasPermission("bypass.blockjoinleave")){
                        sender.sendMessage("§8[§c!§8]§c Tu n'as pas accès à cette commande !");
                        return false;
                    }
                    Player p = (Player) sender;
                    User user = UltraPermissions.getAPI().getUsers().name(p.getName());
                    user.removeGroup(bleu);
                    user.removeGroup(orange);
                    p.teleport(new Location(p.getWorld(), -210.5, 50.5, -8.5));
                    p.sendMessage("§8[§c!§8]§c Vous avz quitté votre team !");
                    return true;
                }
            }
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("join")){
            if (args.length == 0){
                sender.sendMessage("§8[§c!§8]§c /join [joueur] [team]");
                sender.sendMessage("§8[§c!§8]§c Argument [joueur] réservé au staff");
            }
            if (args.length == 2){
                if (sender.hasPermission("forcejoin")){
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                    if (offlinePlayer.isOnline()){
                        Player p = offlinePlayer.getPlayer();
                        if (args[1].equalsIgnoreCase("Bleu") || args[1].equalsIgnoreCase("Orange")){
                            if (args[1].equalsIgnoreCase("Bleu")){
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + sender.getName() + " -210.5 50 -26.5");
                            } else {
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + sender.getName() + " -210.5 50 9.5");
                            }
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc addgroup " + p.getName() + " "  + args[1]);
                            p.sendMessage("§8[§a!§8]§a Vous avez été ajouté à la team  " + args[1] + "!");
                            return true;
                        } else {
                            sender.sendMessage("§8[§c!§8]§c Les teams sont : Bleu et Orange !");
                            return false;
                        }
                    } else {
                        sender.sendMessage("§8[§c!§8]§c Ce joueur n'est pas en ligne !");
                        return false;
                    }
                } else {
                    sender.sendMessage("§8[§c!§8]§c Tu n'as pas la permission !");
                    return false;
                }
            }
            if (args.length == 1){
                if (sender instanceof Player){
                    if (sender.hasPermission("blockjoinleave") && !sender.hasPermission("bypass.blockjoinleave")){
                        sender.sendMessage("§8[§c!§8]§c Tu n'as pas accès à cette commande !");
                        return false;
                    }
                    if (args[0].equalsIgnoreCase("Bleu") || args[0].equalsIgnoreCase("Orange")){
                        if (args[0].equalsIgnoreCase("Bleu")){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + sender.getName() + " -210.5 50 -26.5");
                        } else {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + sender.getName() + " -210.5 50 9.5");
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "upc addgroup " + sender.getName() + " " + args[0]);
                        return true;
                    } else {
                        sender.sendMessage("§8[§c!§8]§c Les teams sont : Bleu et Orange !");
                        return false;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if (e.getPlayer().hasPermission("bypass.antibreak")){
            e.setCancelled(false);
            return;
        }
        if (e.getBlock().getType() == Material.QUARTZ_BLOCK || e.getBlock().getType() == Material.SEA_LANTERN || e.getBlock().getType() == Material.OBSIDIAN || e.getBlock().getType() == Material.CHEST || e.getBlock().getType() == Material.QUARTZ_STAIRS || e.getBlock().getType() == Material.STONE_SLAB2 || e.getBlock().getType() == Material.STAINED_GLASS){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplosion (ExplosionPrimeEvent e) {
        int radius = Math.round(e.getRadius());
        Location loc = e.getEntity().getLocation();
        World world = loc.getWorld();
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = world.getBlockAt(loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z);
                    if (block.getType() == Material.BED_BLOCK || block.getType() == Material.SANDSTONE ) {
                        block.setType(Material.AIR);
                    }
                }
            }
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onBedEnter (PlayerBedEnterEvent e) {
        Block b = e.getBed();
        Location loc = b.getLocation();
        this.getConfig().set(e.getPlayer().getName() + ".X", loc.getBlockX());
        this.getConfig().set(e.getPlayer().getName() + ".Y", loc.getBlockY());
        this.getConfig().set(e.getPlayer().getName() + ".Z", loc.getBlockZ());
        this.getConfig().set(e.getPlayer().getName() + ".World", loc.getWorld().getName());
        this.saveConfig();
        e.getPlayer().sendMessage("§8[§a!§8] §aLe point de respawn a bien été set !");
    }

    @EventHandler
    public void onBedEnter (PlayerRespawnEvent e) {
        int x = this.getConfig().set(e.getPlayer().getName() + ".X", loc.getBlockX());
        int y =  this.getConfig().set(e.getPlayer().getName() + ".Y", loc.getBlockY());
        int z = this.getConfig().set(e.getPlayer().getName() + ".Z", loc.getBlockZ());
        String w = this.getConfig().set(e.getPlayer().getName() + ".World", loc.getWorld().getName());
        Location loc = new Location(Bukkit.getWorld(w), x, y, z);
        if (loc.getBlock().getType() == Material.BED_BLOCK){
            Bed bed = (Bed) loc.getBlock();
            if (bed.isHeadOfBed()){
                if (bed.getFacing() == BlockFace.NORTH){

                }
                if (loc.add(1,0,0).getBlock() == null) e.setRespawnLocation(loc.add(1,0,0));
                if (loc.add(0,0,1).getBlock() == null) e.setRespawnLocation(loc.add(0,0,1));
                if (loc.add(1,0,1).getBlock() == null) e.setRespawnLocation(loc.add(1,0,1));
                if (loc.add(-1,0,0).getBlock() == null) e.setRespawnLocation(loc.add(-1,0,0));
                if (loc.add(0,0,-1).getBlock() == null) e.setRespawnLocation(loc.add(0,0,-1));
                if (loc.add(-1,0,-1).getBlock() == null) e.setRespawnLocation(loc.add(-1,0,-1));
            }
        }
    }


    @EventHandler
    public void onItemSpawn (ItemSpawnEvent e) {
        if (!started){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMobSpawn (CreatureSpawnEvent e) {
        e.setCancelled(true);
    }
}
