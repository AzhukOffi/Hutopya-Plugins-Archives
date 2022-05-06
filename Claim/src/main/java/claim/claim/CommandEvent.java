package main.java.claim.claim;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface CommandEvent {
    void openMenu(Player player);
    void invalidCommand(Player player);
    void help(Player player);
    void check(Player player);
    void coop(Player player, Player coopPlayer);
    void uncoop(Player player, Player uncoopPlayer);
    void sell(Player player, Location location);
    void buy(Player player, Location location);
}
