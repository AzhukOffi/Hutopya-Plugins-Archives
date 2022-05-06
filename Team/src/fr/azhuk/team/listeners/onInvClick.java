package fr.azhuk.team.listeners;

import fr.azhuk.team.Main;
import fr.azhuk.team.Methods;
import fr.azhuk.team.MySQL;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class onInvClick implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    MySQL SQL = new MySQL();

    Methods methods = new Methods();

    @EventHandler
    public void onInv(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (current == null) return;
        if (e.getView().getTitle().equalsIgnoreCase(plugin.messages.getString("gui.teamsInv.name"))) {
            e.setCancelled(true);
            if (current == null) return;
            if (current.getType() == Material.GRAY_STAINED_GLASS_PANE) return;

            int id = SQL.getID(ChatColor.stripColor(current.getItemMeta().getDisplayName()));
            if (id == 0){
                p.sendMessage("error");
                return;
            }
            Material item = Material.GRASS_BLOCK;
            ArrayList<String> teamInfos = new ArrayList<>(SQL.getInfos(id));      //Récupération infos
            item = Material.getMaterial(teamInfos.get(3));

            if (current.getType() == item && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(current.getItemMeta().getDisplayName())){
                p.closeInventory();
                if (SQL.isOwner(p.getUniqueId(), id)){
                    p.openInventory(methods.teamEditInv(id));
                } else {
                    p.openInventory(methods.teamViewInv(id));
                }
            }

        }
        if (e.getView().getTitle().contains(plugin.messages.getString("gui.teamViewInv.name"))) {
            e.setCancelled(true);
        }
        if (e.getView().getTitle().contains(plugin.messages.getString("gui.TeamMembers.name"))) {
            e.setCancelled(true);
            if (current == null) return;
            if (current.getType() == Material.GRAY_STAINED_GLASS_PANE || current.getType() == Material.PLAYER_HEAD) return;

            if (current.getType() == Material.ARROW && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.TeamMembers.next"))){
                int page = Integer.parseInt(ChatColor.stripColor(inv.getItem(40).getItemMeta().getDisplayName().replace(plugin.messages.getString("gui.TeamMembers.actuel"), "")));
                String uuid = ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName().replace(plugin.messages.getString("gui.TeamMembers.chef"), ""));
                uuid = Bukkit.getOfflinePlayer(uuid).getUniqueId().toString();
                p.closeInventory();
                Inventory newInv = methods.TeamMembers(SQL.getIdFromOwner(uuid), page);
                if (newInv == null){
                    p.sendMessage(plugin.messages.getString("chat.members.no_page"));
                }
                p.openInventory(newInv);
            }
            if (current.getType() == Material.ARROW && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.TeamMembers.previous"))){
                int page = Integer.parseInt(ChatColor.stripColor(inv.getItem(40).getItemMeta().getDisplayName().replace(plugin.messages.getString("gui.TeamMembers.actuel"), ""))) - 2;
                String uuid = ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName().replace(plugin.messages.getString("gui.TeamMembers.chef"), ""));
                uuid = Bukkit.getOfflinePlayer(uuid).getUniqueId().toString();
                p.closeInventory();
                if (page < 0){
                    p.sendMessage(plugin.messages.getString("chat.members.no_page"));
                    return;
                }
                Inventory newInv = methods.TeamMembers(SQL.getIdFromOwner(uuid), page);
                if (newInv == null){
                    p.sendMessage(plugin.messages.getString("chat.members.no_page"));
                    return;
                }
                p.openInventory(newInv);
            }
        }
        if (e.getView().getTitle().contains(plugin.messages.getString("gui.RankInv.name"))) {
            e.setCancelled(true);
            if (current == null) return;
            if (current.getType() == Material.GRAY_STAINED_GLASS_PANE) return;

            Player cible = Bukkit.getOfflinePlayer(ChatColor.stripColor(inv.getItem(13).getItemMeta().getDisplayName())).getPlayer();
            if (current.getType() == Material.IRON_BLOCK && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.RankInv.iron"))){
                p.closeInventory();
                SQL.updatePlayerRank(cible, 0);
            }
            if (current.getType() == Material.GOLD_BLOCK && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.RankInv.gold"))){
                p.closeInventory();
                SQL.updatePlayerRank(cible, 1);
            }
            if (current.getType() == Material.DIAMOND_BLOCK && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.RankInv.diamond"))){
                p.closeInventory();
                SQL.updatePlayerRank(cible, 2);
            }
        }
        if (e.getView().getTitle().contains(plugin.messages.getString("gui.LeaveTeam.name"))) {
            e.setCancelled(true);
            if (current == null) return;
            if (current.getType() == Material.GRAY_STAINED_GLASS_PANE) return;

            int id = SQL.getID(ChatColor.stripColor(current.getItemMeta().getDisplayName()));
            if (id == 0){
                p.sendMessage("error");
                return;
            }
            Material item = Material.GRASS_BLOCK;
            ArrayList<String> teamInfos = new ArrayList<>(SQL.getInfos(id));      //Récupération infos
            item = Material.getMaterial(teamInfos.get(3));

            if (current.getType() == item && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(current.getItemMeta().getDisplayName())){
                p.closeInventory();
                TextComponent msg = new TextComponent(plugin.messages.getString("chat.leave.ClickForLeave"));
                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cCliquez pour valider").create()));
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team leave " + id));
                p.spigot().sendMessage(msg);
            }

        }
        if (e.getView().getTitle().contains(plugin.messages.getString("gui.teamEditInv.name"))) {
            e.setCancelled(true);
            if (current == null) return;
            if (current.getType() == Material.GRAY_STAINED_GLASS_PANE) return;

            int id = SQL.getID(ChatColor.stripColor(e.getView().getTitle().replace(plugin.messages.getString("gui.teamEditInv.name"), "")));
            if (id == 0){
                p.sendMessage("error");
                return;
            }

            Material item = Material.GRASS_BLOCK;
            ArrayList<String> teamInfos = new ArrayList<>(SQL.getInfos(id));      //Récupération infos
            item = Material.getMaterial(teamInfos.get(3));

            if (current.getType() == item && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.teamEditInv.icon"))){
                if (SQL.transferExists(p.getUniqueId())){
                    SQL.updateTransfer(p.getUniqueId(), "icon");
                } else {
                    p.sendMessage("une erreur, essai de deco reco");
                    return;
                }
                p.sendMessage(plugin.messages.getString("chat.teamEdit.enter_value.icon"));
                p.closeInventory();
            }
            if (current.getType() == Material.ANVIL && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.teamEditInv.teamname"))){
                if (SQL.transferExists(p.getUniqueId())){
                    SQL.updateTransfer(p.getUniqueId(), "name");
                } else {
                    p.sendMessage("une erreur, essai de deco reco");
                    return;
                }
                p.sendMessage(plugin.messages.getString("chat.teamEdit.enter_value.name"));
                p.closeInventory();
            }
            if (current.getType() == Material.OAK_SIGN && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.teamEditInv.desc"))){
                if (SQL.transferExists(p.getUniqueId())){
                    SQL.updateTransfer(p.getUniqueId(), "desc");
                } else {
                    p.sendMessage("une erreur, essai de deco reco");
                    return;
                }
                p.sendMessage(plugin.messages.getString("chat.teamEdit.enter_value.desc"));
                p.closeInventory();
            }
            if (current.getType() == Material.BLUE_BANNER && current.getItemMeta().hasDisplayName() && current.getItemMeta().getDisplayName().equalsIgnoreCase(plugin.messages.getString("gui.teamEditInv.prefix"))){
                if (SQL.transferExists(p.getUniqueId())){
                    SQL.updateTransfer(p.getUniqueId(), "prefix");
                } else {
                    p.sendMessage("une erreur, essai de deco reco");
                    return;
                }
                p.sendMessage(plugin.messages.getString("chat.teamEdit.enter_value.prefix"));
                p.closeInventory();
            }
        }
    }

}
