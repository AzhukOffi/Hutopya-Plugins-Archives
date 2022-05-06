package fr.azhuk.Reseaux.events;

import fr.azhuk.Reseaux.Main;
import fr.azhuk.Reseaux.MysqlSetterGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements Listener {

    Main plugin;

    public Chat(Main instance) {
        plugin = instance;
    }

    MysqlSetterGetter MySQL = new MysqlSetterGetter();

    @EventHandler
    public void onChat(PlayerChatEvent event){
        String msg = event.getMessage();
        Player p = event.getPlayer();
        Boolean get = MySQL.transferGet(p.getUniqueId());
        String service = MySQL.transferString(p.getUniqueId());
        if (get){
            if (msg.equalsIgnoreCase("stop")){
                p.sendMessage(plugin.getConfig().getString("messages.request_cancel"));
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
                switch (service){
                    case "twitch":
                        MySQL.updateTwitch(msg, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.success_add_compte"));
                        break;
                    case "discord":
                        MySQL.updateDiscord(msg, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.success_add_compte"));
                        break;
                    case "instagram":
                        MySQL.updateInstagram(msg, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.success_add_compte"));
                        break;
                    case "twitter":
                        MySQL.updateTwitter(msg, p.getUniqueId());
                        p.sendMessage(plugin.getConfig().getString("messages.success_add_compte"));
                        break;
                    case "player":
                        p.chat("/reseaux " + msg);
                        break;
                }
                MySQL.transferUpdate(false, p.getUniqueId(), "aucun");
                return;
            }
            MySQL.transferUpdate(false, p.getUniqueId(), "aucun");
            return;
        }

    }
}
