package fr.azhuk.team.commands;

import fr.azhuk.team.Main;
import fr.azhuk.team.Methods;
import fr.azhuk.team.MySQL;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team implements CommandExecutor {

    MySQL SQL = new MySQL();
    Methods methods = new Methods();
    Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                ArrayList<Integer> teams = new ArrayList<>(SQL.getTeams(p));
                p.openInventory(methods.teamsInv(teams.get(0), teams.get(1), teams.get(2), teams.get(3), teams.get(4)));
            }
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args.length > 1) {
                        int i = 1;
                        String name = "";
                        while (args.length > i) {
                            name = name + args[i] + " ";
                            i++;
                        }
                        if (SQL.getTeams(p).get(0) != 0) {
                            p.sendMessage(plugin.messages.getString("chat.create.Already_in_team"));
                            return false;
                        } else if (SQL.teamExists(name)) {
                            p.sendMessage(plugin.messages.getString("chat.create.Team_Already_Exist"));
                            return false;
                        } else {
                            SQL.createTeam(name, p.getUniqueId());
                            SQL.updatePlayerTeam(p, SQL.getID(name), "team");
                            p.sendMessage(plugin.messages.getString("chat.create.Success"));
                            return true;
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("chat.create.Help"));
                    }
                }
                if (args[0].equalsIgnoreCase("owner")) {
                    if (args.length == 2) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);

                        if (offlinePlayer.isOnline()) {
                            if (SQL.getTeams(p).get(0) == SQL.getTeams(offlinePlayer.getPlayer()).get(0)) {
                                SQL.updateTeamOwner(offlinePlayer.getPlayer(), SQL.getTeams(offlinePlayer.getPlayer()).get(0));
                                p.sendMessage(plugin.messages.getString("chat.owner.setOwner"));
                                offlinePlayer.getPlayer().sendMessage(plugin.messages.getString("chat.owner.toOwner"));
                                return true;
                            } else {
                                p.sendMessage(plugin.messages.getString("chat.owner.Not_in_team"));
                                return false;
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.owner.Not_online"));
                            return false;
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("chat.owner.Help"));
                        return false;
                    }
                }
                if (args[0].equalsIgnoreCase("invite")) {
                    if (args.length == 2) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                        int id = SQL.getTeams(p).get(0);
                        if (offlinePlayer.isOnline()) {
                            if (id != 0 && SQL.teamIdExists(id)) {
                                SQL.createInvite(id, offlinePlayer.getPlayer(), p);
                                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                    @Override
                                    public void run() {
                                        SQL.deleteInvite(offlinePlayer.getPlayer(), p);
                                        if (offlinePlayer.isOnline()) {
                                            offlinePlayer.getPlayer().sendMessage(plugin.messages.getString("chat.invite.expired_for_invited"));
                                        }
                                        if (Bukkit.getOfflinePlayer(p.getName()).isOnline()) {
                                            p.sendMessage(plugin.messages.getString("chat.invite.expired_for_sender"));
                                        }
                                    }
                                }, 36000);
                                TextComponent msg = new TextComponent(plugin.messages.getString("chat.invite.clickForAccept").replace("%player%", p.getDisplayName()));
                                msg.setColor(ChatColor.BLUE);
                                msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Cliquez pour accepter").create()));
                                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team accept " + p.getName()));
                                offlinePlayer.getPlayer().spigot().sendMessage(msg);
                                return true;
                            } else {
                                p.sendMessage(plugin.messages.getString("chat.invite.Not_in_team"));
                                return false;
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.invite.Not_online"));
                            return false;
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("chat.invite.Help"));
                        return false;
                    }
                }
                if (args[0].equalsIgnoreCase("accept")) {
                    if (args.length == 2) {
                        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                        if (!SQL.inviteExists(p, offlinePlayer.getPlayer())){
                            p.sendMessage(plugin.messages.getString("chat.accept.Not_in_team"));
                            return false;
                        }

                        if (SQL.getTeams(p).get(0) == 0) {
                            int id = SQL.getInvite(p, offlinePlayer.getPlayer());
                            SQL.deleteInviteToPlayer(p);
                            SQL.updatePlayerTeam(p, id, "team");
                            p.sendMessage(plugin.messages.getString("chat.invite.Accepted").replace("%player%", offlinePlayer.getPlayer().getDisplayName()));
                            return true;
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.invite.Already_In_Team"));
                            return false;
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("chat.accept.Help"));
                        return false;
                    }
                }
                if (args[0].equalsIgnoreCase("leave")) {
                    if (args.length == 1) {
                        ArrayList<Integer> teams = new ArrayList<>(SQL.getTeams(p));
                        p.openInventory(methods.LeaveTeam(teams.get(0), teams.get(1), teams.get(2), teams.get(3), teams.get(4)));
                        return true;
                    }
                    if (args.length == 2) {
                        int id = Integer.parseInt(args[1]);
                        if (id != 0 && SQL.teamIdExists(id)) {
                            if (SQL.getMembers(id).contains(p.getUniqueId().toString())) {
                                if (!SQL.isOwner(p.getUniqueId(), id)) {
                                    String type = SQL.getTeamType(p, id);
                                    if (type != null) {
                                        SQL.updatePlayerTeam(p, 0, type);
                                        p.sendMessage(plugin.messages.getString("chat.leave.ValidLeave"));
                                        return true;
                                    }
                                } else {
                                    p.sendMessage(plugin.messages.getString("chat.leave.isOwner"));
                                    return false;
                                }
                            } else {
                                p.sendMessage(plugin.messages.getString("chat.leave.Not_in_the_team"));
                                return false;
                            }
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.leave.Team_not_exist"));
                            return false;
                        }
                    }
                    p.sendMessage(plugin.messages.getString("chat.leave.Help"));
                    return false;
                }
                if (args[0].equalsIgnoreCase("rank")) {
                    if (args.length == 2) {
                        if (SQL.getTeams(p).get(0) == 0){
                            p.sendMessage(plugin.messages.getString("chat.rank.HaveNoTeam"));
                            return false;
                        }
                        if (SQL.getMembers(SQL.getTeams(p).get(0)).contains(Bukkit.getOfflinePlayer(args[1]).getUniqueId().toString())){
                            p.openInventory(methods.RankInv(Bukkit.getOfflinePlayer(args[1]).getPlayer()));
                            return true;
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.rank.NotInTeam"));
                            return false;
                        }
                    } else {
                        p.sendMessage(plugin.messages.getString("chat.rank.Help"));
                        return false;
                    }
                }
                if (args[0].equalsIgnoreCase("members")) {
                    if (args.length == 1){
                        if (SQL.getTeams(p).get(0) != 0){
                            p.openInventory(methods.TeamMembers(SQL.getTeams(p).get(0), 0));
                            return true;
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.members.team_not_exists"));
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        if (SQL.getTeams(Bukkit.getOfflinePlayer(args[1])).get(0) == 0){
                            p.openInventory(methods.TeamMembers(SQL.getTeams(Bukkit.getOfflinePlayer(args[1])).get(0), 0));
                            return true;
                        } else {
                            p.sendMessage(plugin.messages.getString("chat.members.team_not_exists"));
                            return false;
                        }
                    }
                    p.sendMessage(plugin.messages.getString("chat.members.Help"));
                    return false;
                }
                if (args[0].equalsIgnoreCase("disband")) {
                    if (args.length == 1){
                        int id = SQL.getTeams(p).get(0);
                        if (SQL.isOwner(p.getUniqueId(), id)){
                            SQL.deleteTeam(id);
                            p.sendMessage(plugin.messages.getString("chat.disband.player"));
                        }
                        return true;
                    }
                    if (args.length == 2){
                        if (p.hasPermission("team.disband.other")){
                            int id = SQL.getTeams(Bukkit.getOfflinePlayer(args[1])).get(0);
                            SQL.deleteTeam(id);
                            p.sendMessage(plugin.messages.getString("chat.disband.admin"));
                        }
                        return true;
                    }
                    p.sendMessage(plugin.messages.getString("chat.members.Help"));
                    return false;
                }
                p.sendMessage(plugin.messages.getString("chat.Global_Help"));
                return false;
            }
        }
        return false;
    }


}
