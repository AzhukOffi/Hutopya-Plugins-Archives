package fr.azhuk.team.listeners;

import fr.azhuk.team.Main;
import fr.azhuk.team.MySQL;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class onChat implements Listener {


    MySQL SQL = new MySQL();

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onChat(PlayerChatEvent e){
        Player p = e.getPlayer();
        if (e.isCancelled()){
            return;
        }

        e.setCancelled(true);
        if (SQL.getTransfer(p.getUniqueId()).equalsIgnoreCase("name")){
            if (e.getMessage().length() > 10){
                p.sendMessage(plugin.messages.getString("chat.teamEdit.name_too_long"));
                return;
            }
            SQL.updateTeamName(SQL.getTeams(p).get(0), e.getMessage());
            SQL.updateTransfer(p.getUniqueId(), "none");
            p.sendMessage(plugin.messages.getString("chat.teamEdit.update_success.name"));
            return;

        } else if (SQL.getTransfer(p.getUniqueId()).equalsIgnoreCase("desc")){
            if (e.getMessage().length() > 40){
                p.sendMessage(plugin.messages.getString("chat.teamEdit.desc_too_long"));
                return;
            }
            SQL.updateTeamDesc(SQL.getTeams(p).get(0), e.getMessage());
            SQL.updateTransfer(p.getUniqueId(), "none");
            p.sendMessage(plugin.messages.getString("chat.teamEdit.update_success.desc"));
            return;

        } else if (SQL.getTransfer(p.getUniqueId()).equalsIgnoreCase("icon")){
            if (Material.getMaterial(e.getMessage()) == null){
                p.sendMessage(plugin.messages.getString("chat.teamEdit.unknow_item"));
                return;
            }
            SQL.updateTeamIcon(SQL.getTeams(p).get(0), e.getMessage());
            SQL.updateTransfer(p.getUniqueId(), "none");
            p.sendMessage(plugin.messages.getString("chat.teamEdit.update_success.icon"));
            return;

        } else if (SQL.getTransfer(p.getUniqueId()).equalsIgnoreCase("prefix")){
            if (e.getMessage().length() > 4){
                p.sendMessage(plugin.messages.getString("chat.teamEdit.prefix_too_long"));
                return;
            }
            SQL.updateTeamPrefix(SQL.getTeams(p).get(0), e.getMessage());
            SQL.updateTransfer(p.getUniqueId(), "none");
            p.sendMessage(plugin.messages.getString("chat.teamEdit.update_success.prefix"));
            return;
        }

        if (SQL.getTeams(p).get(0) == 0){
            String txt = "%vault_rankprefix%" + e.getPlayer().getName() + "%vault_ranksuffix% " + ChatColor.GRAY +  ": ";
            String txtHover = "§8> §aInformations §8: \n§6Temps de jeu §8: §7%statistic_time_played%\n§6Money §8: §7%vault_eco_balance%\n§6Team §8: §7Aucune";
            TextComponent msg = new TextComponent(PlaceholderAPI.setPlaceholders(e.getPlayer(), txt) + e.getMessage());
            msg.setColor(net.md_5.bungee.api.ChatColor.GRAY);
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(PlaceholderAPI.setPlaceholders(e.getPlayer(), txtHover)).create()));
            Bukkit.spigot().broadcast(msg);
        } else {
            String txt = "§7[§f"  + SQL.getInfos(SQL.getTeams(p).get(0)).get(2) + "§7] " + "%vault_rankprefix%" + e.getPlayer().getName() + "%vault_ranksuffix% " + ChatColor.GRAY +  ": ";
            String txtHover = "§8> §aInformations §8: \n§6Temps de jeu §8: §7%statistic_time_played%\n§6Money §8: §7%vault_eco_balance%\n§6Team §8: §7" + SQL.getInfos(SQL.getTeams(p).get(0)).get(0);
            TextComponent msg = new TextComponent(PlaceholderAPI.setPlaceholders(e.getPlayer(), txt) + e.getMessage());
            msg.setColor(net.md_5.bungee.api.ChatColor.GRAY);
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(PlaceholderAPI.setPlaceholders(e.getPlayer(), txtHover)).create()));
            Bukkit.spigot().broadcast(msg);
        }
    }

}
