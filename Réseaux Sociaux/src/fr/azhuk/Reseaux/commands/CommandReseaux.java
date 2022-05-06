package fr.azhuk.Reseaux.commands;

import fr.azhuk.Reseaux.Main;
import fr.azhuk.Reseaux.MysqlSetterGetter;
import fr.azhuk.Reseaux.events.Chat;
import java.sql.SQLException;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandReseaux implements CommandExecutor, Listener {
    MysqlSetterGetter MySQL = new MysqlSetterGetter();
    Main plugin;

    Chat chat = new Chat(plugin);
    public boolean get = false;
    public String PlayerOfGet;

    public CommandReseaux(Main instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player && cmd.getName().equalsIgnoreCase("reseaux")) {
            Player p = (Player)sender;
            if (args.length > 1) {
                p.sendMessage("§cMerci de renseigner au maximum 1 argument");
            }

            String TwitchUsername;
            String DiscordUsername;
            String InstagramUsername;
            String TwitterUsername;
            if (args.length == 1) {
                OfflinePlayer argP = Bukkit.getOfflinePlayer(args[0]);
                if (MySQL.playerExists(argP.getUniqueId())) {
                    TwitchUsername = MySQL.getTwitch(argP.getUniqueId());
                    DiscordUsername = MySQL.getDiscord(argP.getUniqueId());
                    InstagramUsername = MySQL.getInstagram(argP.getUniqueId());
                    TwitterUsername= MySQL.getTwitter(argP.getUniqueId());
                    Boolean Visible = MySQL.getVisible(argP.getUniqueId());
                    if (Visible) {
                        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, plugin.getConfig().getString("messages.gui_view_other_player_before") + argP.getName() + plugin.getConfig().getString("messages.gui_view_other_player_after"));
                        ItemStack Twitch = Main.getHead("twitch");
                        ItemMeta TwitchMeta = Twitch.getItemMeta();
                        TwitchMeta.setDisplayName(plugin.getConfig().getString("messages.Twitch_Displayname"));
                        if (!TwitchUsername.equalsIgnoreCase("inconnu")) {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte"));
                            lore.add(plugin.getConfig().getString("messages.color_username") + TwitchUsername);
                            TwitchMeta.setLore(lore);
                        } else {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte_non_renseigne"));
                            TwitchMeta.setLore(lore);
                        }

                        Twitch.setItemMeta(TwitchMeta);
                        ItemStack Discord = Main.getHead("discord");
                        ItemMeta DiscordMeta = Discord.getItemMeta();
                        DiscordMeta.setDisplayName(plugin.getConfig().getString("messages.Discord_Displayname"));
                        if (!DiscordUsername.equalsIgnoreCase("inconnu")) {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte"));
                            lore.add(plugin.getConfig().getString("messages.color_username") + DiscordUsername);
                            DiscordMeta.setLore(lore);
                        } else {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte_non_renseigne"));
                            DiscordMeta.setLore(lore);
                        }

                        Discord.setItemMeta(DiscordMeta);
                        ItemStack Instagram = Main.getHead("instagram");
                        ItemMeta InstagramMeta = Instagram.getItemMeta();
                        InstagramMeta.setDisplayName(plugin.getConfig().getString("messages.Insta_Displayname"));
                        if (!InstagramUsername.equalsIgnoreCase("inconnu")) {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte"));
                            lore.add(plugin.getConfig().getString("messages.color_username") + InstagramUsername);
                            InstagramMeta.setLore(lore);
                        } else {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte_non_renseigne"));
                            InstagramMeta.setLore(lore);
                        }

                        Instagram.setItemMeta(InstagramMeta);
                        ItemStack Twitter = Main.getHead("twitter");
                        ItemMeta TwitterMeta = Twitter.getItemMeta();
                        TwitterMeta.setDisplayName(plugin.getConfig().getString("messages.Twitter_Displayname"));
                        if (!TwitterUsername.equalsIgnoreCase("inconnu")) {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte"));
                            lore.add(plugin.getConfig().getString("messages.color_username") + TwitterUsername);
                            TwitterMeta.setLore(lore);
                        } else {
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(plugin.getConfig().getString("messages.compte_non_renseigne"));
                            TwitterMeta.setLore(lore);
                        }

                        Twitter.setItemMeta(TwitterMeta);
                        inv.setItem(10, Twitch);
                        inv.setItem(12, Discord);
                        inv.setItem(14, Instagram);
                        inv.setItem(16, Twitter);
                        p.openInventory(inv);
                    } else {
                        p.sendMessage(plugin.getConfig().getString("messages.reseaux_non_visibles"));
                    }
                } else {
                    p.sendMessage(plugin.getConfig().getString("messages.joueur_introuvable"));
                }
            } else {
                TwitchUsername = MySQL.getTwitch(p.getUniqueId());
                DiscordUsername = MySQL.getDiscord(p.getUniqueId());
                InstagramUsername = MySQL.getInstagram(p.getUniqueId());
                TwitterUsername = MySQL.getTwitter(p.getUniqueId());
                Boolean Visible = MySQL.getVisible(p.getUniqueId());
                Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, plugin.getConfig().getString("messages.gui_edit"));
                ItemStack Twitch = Main.getHead("twitch");
                ItemMeta TwitchMeta = Twitch.getItemMeta();
                TwitchMeta.setDisplayName(plugin.getConfig().getString("messages.Twitch_Displayname"));
                if (!TwitchUsername.equalsIgnoreCase("inconnu")) {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.compte"));
                    lore.add(plugin.getConfig().getString("messages.color_username") + TwitchUsername);
                    TwitchMeta.setLore(lore);
                } else {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.add_compte"));
                    TwitchMeta.setLore(lore);
                }

                Twitch.setItemMeta(TwitchMeta);
                ItemStack Discord = Main.getHead("discord");
                ItemMeta DiscordMeta = Discord.getItemMeta();
                DiscordMeta.setDisplayName(plugin.getConfig().getString("messages.Discord_Displayname"));
                if (!DiscordUsername.equalsIgnoreCase("inconnu")) {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.compte"));
                    lore.add(plugin.getConfig().getString("messages.color_username") + DiscordUsername);
                    DiscordMeta.setLore(lore);
                } else {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.add_compte"));
                    DiscordMeta.setLore(lore);
                }

                Discord.setItemMeta(DiscordMeta);
                ItemStack Instagram = Main.getHead("instagram");
                ItemMeta InstagramMeta = Instagram.getItemMeta();
                InstagramMeta.setDisplayName(plugin.getConfig().getString("messages.Insta_Displayname"));
                if (!InstagramUsername.equalsIgnoreCase("inconnu")) {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.compte"));
                    lore.add(plugin.getConfig().getString("messages.color_username") + InstagramUsername);
                    InstagramMeta.setLore(lore);
                } else {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.add_compte"));
                    InstagramMeta.setLore(lore);
                }

                Instagram.setItemMeta(InstagramMeta);
                ItemStack Twitter = Main.getHead("twitter");
                ItemMeta TwitterMeta = Twitter.getItemMeta();
                TwitterMeta.setDisplayName(plugin.getConfig().getString("messages.Twitter_Displayname"));
                if (!TwitterUsername.equalsIgnoreCase("inconnu")) {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.compte"));
                    lore.add(plugin.getConfig().getString("messages.color_username") + TwitterUsername);
                    TwitterMeta.setLore(lore);
                } else {
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.add_compte"));
                    TwitterMeta.setLore(lore);
                }

                Twitter.setItemMeta(TwitterMeta);
                ItemStack VisibleStack = new ItemStack(Material.ENDER_EYE, 1);
                ItemMeta VisibleMeta = VisibleStack.getItemMeta();
                if (Visible) {
                    VisibleMeta.setDisplayName(plugin.getConfig().getString("messages.visible_Displayname"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.toggle_private"));
                    VisibleMeta.setLore(lore);
                } else {
                    VisibleMeta.setDisplayName(plugin.getConfig().getString("messages.nonvisible_Displayname"));
                    ArrayList<String> lore = new ArrayList<String>();
                    lore.add(plugin.getConfig().getString("messages.toggle_public"));
                    VisibleMeta.setLore(lore);
                }

                VisibleStack.setItemMeta(VisibleMeta);
                ItemStack BackStack = new ItemStack(Material.ARROW, 1);
                ItemMeta BackMeta = BackStack.getItemMeta();
                BackMeta.setDisplayName(plugin.getConfig().getString("messages.Retour_Displayname"));
                BackStack.setItemMeta(BackMeta);
                ItemStack PlayerStack = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta PlayerMeta = PlayerStack.getItemMeta();
                PlayerMeta.setDisplayName(plugin.getConfig().getString("messages.view_other_player_Displayname"));
                PlayerStack.setItemMeta(PlayerMeta);
                inv.setItem(10, Twitch);
                inv.setItem(12, Discord);
                inv.setItem(14, Instagram);
                inv.setItem(16, Twitter);
                inv.setItem(22, VisibleStack);
                inv.setItem(18, BackStack);
                inv.setItem(26, PlayerStack);
                p.openInventory(inv);
            }
        }

        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        InventoryView invV = event.getView();
        Player p = (Player)event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        String TwitchUsername = MySQL.getTwitch(p.getUniqueId());
        String DiscordUsername = MySQL.getDiscord(p.getUniqueId());
        String InstagramUsername = MySQL.getInstagram(p.getUniqueId());
        String TwitterUsername = MySQL.getTwitter(p.getUniqueId());
        if (current != null) {
                if (current.getType() == Material.PLAYER_HEAD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.Twitch_Displayname"))) {
                    event.setCancelled(true);
                    if (TwitchUsername.equalsIgnoreCase("inconnu")) {
                        p.sendMessage(plugin.getConfig().getString("messages.renseigner_pseudo"));
                        p.sendTitle("§cTaper \"stop\"", "§cpour annuler");
                        MySQL.transferUpdate(true, p.getUniqueId(), "twitch");
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.getConfig().getString("messages.suppr"));
                        MySQL.updateTwitch("inconnu", p.getUniqueId());
                        p.closeInventory();
                        p.chat("/reseaux");
                    }
                }

                if (current.getType() == Material.PLAYER_HEAD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.Discord_Displayname"))) {
                    event.setCancelled(true);
                    if (DiscordUsername.equalsIgnoreCase("inconnu")) {
                        p.sendMessage(plugin.getConfig().getString("messages.renseigner_pseudo"));
                        p.sendTitle("§cTaper \"stop\"", "§cpour annuler");
                        MySQL.transferUpdate(true, p.getUniqueId(), "discord");
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.getConfig().getString("messages.suppr"));
                        MySQL.updateDiscord("inconnu", p.getUniqueId());
                        p.closeInventory();
                        p.chat("/reseaux");
                    }
                }

                if (current.getType() == Material.PLAYER_HEAD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.Insta_Displayname"))) {
                    event.setCancelled(true);
                    if (InstagramUsername.equalsIgnoreCase("inconnu")) {
                        p.sendMessage(plugin.getConfig().getString("messages.renseigner_pseudo"));
                        p.sendTitle("§cTaper \"stop\"", "§cpour annuler");
                        MySQL.transferUpdate(true, p.getUniqueId(), "instagram");
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.getConfig().getString("messages.suppr"));
                        MySQL.updateInstagram("inconnu", p.getUniqueId());
                        p.closeInventory();
                        p.chat("/reseaux");
                    }
                }

                if (current.getType() == Material.PLAYER_HEAD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.Twitter_Displayname"))) {
                    event.setCancelled(true);
                    if (TwitterUsername.equalsIgnoreCase("inconnu")) {
                        p.sendMessage(plugin.getConfig().getString("messages.renseigner_pseudo"));
                        p.sendTitle("§cTaper \"stop\"", "§cpour annuler");
                        MySQL.transferUpdate(true, p.getUniqueId(), "twitter");
                        p.closeInventory();
                    } else {
                        p.sendMessage(plugin.getConfig().getString("messages.suppr"));
                        MySQL.updateTwitter("inconnu", p.getUniqueId());
                        p.closeInventory();
                        p.chat("/reseaux");
                    }
                }

                if (current.getType() == Material.PLAYER_HEAD && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.view_other_player_Displayname"))) {
                    event.setCancelled(true);
                    p.sendMessage(plugin.getConfig().getString("messages.renseign_pseudo"));
                    p.sendTitle("§cTaper \"stop\"", "§cpour annuler");
                    MySQL.transferUpdate(true, p.getUniqueId(), "player");
                    p.closeInventory();
                }

                if (current.getType() == Material.ARROW && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.Retour_Displayname"))) {
                    event.setCancelled(true);
                    p.closeInventory();
                    p.chat("/compte");
                }

                if (current.getType() == Material.ENDER_EYE && current.getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if (current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.visible_Displayname"))) {
                        MySQL.updateVisible(false, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.reseaux_invisible"));
                        p.closeInventory();
                    } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.getConfig().getString("messages.nonvisible_Displayname"))) {
                        MySQL.updateVisible(true, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.reseaux_visible"));
                        p.closeInventory();
                    }
                }
            }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (MySQL.mysqlWork()){
            MySQL.createPlayer(p.getUniqueId(), p);
            MySQL.transferCreate(p.getUniqueId());
        } else {
            plugin.mysqlSetup();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MySQL.createPlayer(p.getUniqueId(), p);
            MySQL.transferCreate(p.getUniqueId());
        }

    }
}
