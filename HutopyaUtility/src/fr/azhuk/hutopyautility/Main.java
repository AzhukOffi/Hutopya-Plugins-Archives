package fr.azhuk.hutopyautility;

import fr.azhuk.hutopyautility.commands.*;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JavaPlugin implements CommandExecutor {



    public Plugin[] pluginsTemp;
    public ArrayList<Plugin> plugins;
    public ArrayList<Plugin> InternalPlugin;


    int i = 0;

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void plPlayer(Player p){
        String internplug;
        String internplugFinal = "";
        for(Plugin plugin: InternalPlugin){
            internplug = internplugFinal;
            if (internplug.equalsIgnoreCase("")){
                internplugFinal = plugin.getName();
            } else {
                internplugFinal = internplug  + ", " + plugin.getName();
            }
        }

        String plug;
        String plugFinal = "";
        for(Plugin plugin: plugins){
            plug = plugFinal;
            if (plug.equalsIgnoreCase("")){
                plugFinal = plugin.getName();
            } else {
                plugFinal = plug  + ", " + plugin.getName();
            }
        }

        p.sendMessage("§6Hutopya - Serveur " + Bukkit.getServer().getName());
        p.sendMessage("§aPlugins internes:");
        p.sendMessage("§6" + internplugFinal);
        p.sendMessage("");
        p.sendMessage("§cPlugins externes:");
        if (p.hasPermission("hutopyautility.viewplugin")){
            p.sendMessage("§6" + plugFinal);
        } else {
            p.sendMessage("§4Secret ;)");
        }
    }

    public void plConsole(){
        String internplug;
        String internplugFinal = "";
        for(Plugin plugin: InternalPlugin){
            internplug = internplugFinal;
            if (internplug.equalsIgnoreCase("")){
                internplugFinal = plugin.getName();
            } else {
                internplugFinal = internplug  + ", " + plugin.getName();
            }
        }

        String plug;
        String plugFinal = "";
        for(Plugin plugin: plugins){
            plug = plugFinal;
            if (plug.equalsIgnoreCase("")){
                plugFinal = plugin.getName();
            } else {
                plugFinal = plug  + ", " + plugin.getName();
            }
        }

        Bukkit.getConsoleSender().sendMessage("§6Hutopya - Serveur " + Bukkit.getServer().getName());
        Bukkit.getConsoleSender().sendMessage("§aPlugins internes:");
        Bukkit.getConsoleSender().sendMessage("§6" + internplugFinal);
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§cPlugins externes:");
        Bukkit.getConsoleSender().sendMessage("§6" + plugFinal);
    }

    @Override
    public void onEnable() {
        autoMSG();
        loadConfig();
        plugintri();
        Bukkit.getPluginManager().registerEvents(new Listener(this), this);
        getCommand("openshop").setExecutor(new CommandShop());
        getCommand("hutopyautility").setExecutor(this);
        getCommand("voteannounce").setExecutor(new CommandVoteAnnounce());
        getCommand("plugin").setExecutor(new Plugins(this));
        getCommand("discord").setExecutor(new Discord(this));
        getCommand("site").setExecutor(new Site(this));
        getCommand("help").setExecutor(new Help(this));
        getCommand("kickafk").setExecutor(new KickAFK());
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "HutopyaUtility" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0){
                p.sendMessage("§8[§6HutopyaUtility§8] Plugin par Azhuk");
                return true;
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    if (p.hasPermission("hutopyautility.reload")){
                        reloadConfig();
                        p.sendMessage("§8[§6HutopyaUtility§8] La config a bien été reload !");
                        return true;
                    } else {
                        p.sendMessage("§7[§c!§7] §cVous n'avez pas accès à cette commande !");
                    }
                    return true;
                }
                if (args[0].equalsIgnoreCase("toggleitem")){

                }
            }
            return false;
        }


        return false;
    }

    public void plugintri(){
        i = 0;
        pluginsTemp = Bukkit.getServer().getPluginManager().getPlugins();
        plugins = new ArrayList<Plugin>(Arrays.asList(pluginsTemp));
        InternalPlugin = new ArrayList<Plugin>();
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            List<String> authors = new ArrayList<String>(plugin.getDescription().getAuthors());
            for (String author: authors){
                if (author.equalsIgnoreCase("Hutopya")){
                    InternalPlugin.add(plugin);
                    plugins.remove(plugin);
                }
            }
            i++;
        }
    }

    public void autoMSG(){
        new BukkitRunnable(){
            public void run() {
                if (i == 3) {
                    String fm = new FancyMessage("§7[§9!§7] ")
                            .then("- Tu as un soucis ? Contacte le staff sur notre discord")
                            .color(ChatColor.GRAY)
                            .link("https://discordapp.com/invite/5prNyYx")
                            .tooltip("§7Clique pour te connecter au discord")
                            .toJSONString();
                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                    for (Player p : Bukkit.getOnlinePlayers())
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                    i++;
                }
                if (i == 2) {
                    String fm = new FancyMessage("§7[§9!§7] ")
                            .then("- Tu as un soucis ? Contacte le staff sur notre discord")
                            .color(ChatColor.GRAY)
                            .link("https://discordapp.com/invite/5prNyYx")
                            .tooltip("§7Clique pour te connecter au discord")
                            .toJSONString();
                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                    for (Player p : Bukkit.getOnlinePlayers())
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                    i++;
                }
                if (i == 1) {
                    String fm = new FancyMessage("§7[§9!§7] ")
                            .tooltip("§7Vous êtes surveillé, ne l'oubliez jamais ^^")
                            .then("- Tout cheat/comportement inacceptable/provocation/etc... pourra être ")
                            .color(ChatColor.GRAY)
                            .tooltip("§7Vous êtes surveillé, ne l'oubliez jamais ^^")
                            .then("sanctioné")
                            .color(ChatColor.RED)
                            .tooltip("§7Vous êtes surveillé, ne l'oubliez jamais ^^")
                            .toJSONString();
                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                    for (Player p : Bukkit.getOnlinePlayers())
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                    i++;
                }
                if (i == 0) {
                    String fm = new FancyMessage("§7[§9!§7] ")
                            .then("- N'hésitez pas à visiter notre site !")
                            .color(ChatColor.GRAY)
                            .link("https://www.hutopya.fr/")
                            .tooltip("Clique pour y accéder")
                            .toJSONString();
                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                    for (Player p : Bukkit.getOnlinePlayers())
                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                    i++;
                }
            }
        }.runTaskTimer(this, 1, 20*60*20);
    }
}
