package fr.azhuk.hutopyautility;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Listener implements org.bukkit.event.Listener {

    Main plugin;

    public Listener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
        String args[] = e.getMessage().split(" ");
        if(args[0].equalsIgnoreCase("/plugins") || args[0].equalsIgnoreCase("/pl")){
            plugin.plPlayer(e.getPlayer());
            e.setCancelled(true);
        }
    }



}
