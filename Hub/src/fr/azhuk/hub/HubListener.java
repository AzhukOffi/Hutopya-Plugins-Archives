package fr.azhuk.hub;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import javafx.scene.paint.Color;
import me.clip.placeholderapi.PlaceholderAPI;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

public class HubListener implements Listener {

    Main plugin;

    public HubListener(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Location loc = new Location(p.getWorld(),13.5, 127.5, 19.5);
        p.teleport(loc);

        p.getInventory().clear();

        ItemStack boussole = new ItemStack(Material.COMPASS, 1);
        ItemMeta boussoleMeta = boussole.getItemMeta();
        boussoleMeta.setDisplayName("§6Menu");
        boussole.setItemMeta(boussoleMeta);

        p.getInventory().setItem(4, boussole);

        ItemStack settings = new ItemStack(Material.COMPARATOR, 1);
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName("§6Paramètres");
        settings.setItemMeta(settingsMeta);

        p.getInventory().setItem(8, settings);

        ItemStack tpbow = new ItemStack(Material.BOW, 1);
        ItemMeta tpbowMeta = tpbow.getItemMeta();
        tpbowMeta.setDisplayName("§6Arc de téléportation");
        tpbowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        tpbow.setItemMeta(tpbowMeta);

        p.getInventory().setItem(0, tpbow);
        p.getInventory().setItem(17, new ItemStack(Material.ARROW, 1));



        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();

        Objective o = b.registerNewObjective("Gold", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.RED + "Hutopya");

        String Strois = "  §e» Grade: §f%vault_rankprefix%";
        Strois = PlaceholderAPI.setPlaceholders(e.getPlayer(), Strois);

        Score cinq = o.getScore(" ");
        Score quatre = o.getScore("§6§oJoueur:");
        Score trois = o.getScore(Strois);
        Score deux = o.getScore("  ");
        Score un = o.getScore("§3§lplay.hutopya.fr");
        un.setScore(1);
        deux.setScore(2);
        trois.setScore(3);
        quatre.setScore(4);
        cinq.setScore(5);

        p.setScoreboard(b);

        BossBar bar = Bukkit.createBossBar("Bienvenue sur Hutopya", BarColor.RED, BarStyle.SOLID);
        bar.addPlayer(p);
    }

    @EventHandler
    public void onRightClickNPC(NPCRightClickEvent e){
        if (e.getNPC().getName().equalsIgnoreCase("Skyblock")){
            Player p = e.getClicker();
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("Skyblock");
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }
        if (e.getNPC().getName().equalsIgnoreCase("Survie")){
            Player p = e.getClicker();
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("Survie");
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }
        if (e.getNPC().getName().equalsIgnoreCase("Créatif")){
            Player p = e.getClicker();
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("Creatif");
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        if (e.getEntity().getShooter() instanceof Player){
            Player p = (Player) e.getEntity().getShooter();
            float yaw = p.getLocation().getYaw();
            float pitch = p.getLocation().getPitch();
            if (e.getHitBlock() == null){
                p.teleport(e.getHitEntity());
            } else if (e.getHitEntity() == null){
                Location loc = e.getHitBlock().getLocation();
                loc.setYaw(yaw);
                loc.setPitch(pitch);
                loc.add(0,1,0);
                p.teleport(loc);
            }
        }
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent e){
        e.setCancelled(true);
        InventoryView invV = e.getView();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (current != null) {
            if (invV.getTitle().equalsIgnoreCase("§6Paramètres")){
                if (current.getType() == Material.SUGAR && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§bSpeed III")) {
                    if (p.hasPermission("hub.settings.speed")){
                        if (p.hasPotionEffect(PotionEffectType.SPEED)) {
                            p.removePotionEffect(PotionEffectType.SPEED);
                        } else {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2));
                        }
                    } else {
                        p.sendMessage("§7[§c!§7] §cVous ne pouvez pas utiliser cette commande");
                    }
                }
                if (current.getType() == Material.GOLDEN_BOOTS && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aJump III")){
                    if (p.hasPermission("hub.settings.speed")) {
                        if (p.hasPotionEffect(PotionEffectType.JUMP)) {
                            p.removePotionEffect(PotionEffectType.JUMP);
                        } else {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 2));
                        }
                    } else {
                        p.sendMessage("§7[§c!§7] §cVous ne pouvez pas utiliser cette commande");
                    }
                }
                if (current.getType() == Material.FEATHER && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase("§6Fly")){
                    if (p.hasPermission("hub.settings.speed")) {
                        if (p.getAllowFlight() == true) {
                            p.setAllowFlight(false);
                        } else {
                            p.setAllowFlight(true);
                        }
                    } else {
                        p.sendMessage("§7[§c!§7] §cVous ne pouvez pas utiliser cette commande");
                    }
                }
            }
        }
    }

    @EventHandler
    public void InteractEvent(PlayerInteractEvent e){
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK){
            if (e.getItem().getType() == Material.COMPARATOR && e.getItem().getItemMeta().hasDisplayName()){
                Player p = e.getPlayer();
                p.chat("/settings");
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            e.setCancelled(true);
            if (e.getEntity().getLocation().getBlockY() < 50){
                Location loc = new Location(e.getEntity().getWorld(),13.5, 128.5, 19.5);
                e.getEntity().teleport(loc);
            }
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e){
        e.setCancelled(true);
    }
}
