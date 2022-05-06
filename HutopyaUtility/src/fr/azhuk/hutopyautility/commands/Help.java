package fr.azhuk.hutopyautility.commands;

import fr.azhuk.hutopyautility.Main;
import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

    Main plugin;

    public Help(Main instance) {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0) {
                for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {

                    int msgint = Integer.parseInt(msgnum);

                    if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 1){
                        p.sendMessage("§7[§c!§7] §cPage introuvable !");
                        return true;
                    }

                    if (msgnum.equalsIgnoreCase("7")) {
                        return true;
                    }
                    if (msgnum.equalsIgnoreCase("1")) {
                        p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                        p.sendMessage("§8");
                    }
                    String msg = plugin.getConfig().getString("help." + msgnum);
                    p.sendMessage(msg);
                    if (plugin.getConfig().getConfigurationSection("help.").contains("7")) {
                        if (msgnum.equalsIgnoreCase("6")) {
                            String fm = new FancyMessage("|  ")
                                    .color(ChatColor.WHITE)
                                    .then("<<<")
                                    .color(ChatColor.GRAY)
                                    .style(ChatColor.BOLD)
                                    .then("  |  ")
                                    .color(ChatColor.WHITE)
                                    .then(">>>")
                                    .color(ChatColor.DARK_GRAY)
                                    .style(ChatColor.BOLD)
                                    .command("/help 2")
                                    .tooltip("§7Clique pour accéder")
                                    .then("  |")
                                    .color(ChatColor.WHITE)
                                    .toJSONString();
                            IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                            PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                        }
                    } else {
                        int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                        if (size == msgint) {
                            String fm = new FancyMessage("|  ")
                                    .color(ChatColor.WHITE)
                                    .then("<<<")
                                    .color(ChatColor.GRAY)
                                    .style(ChatColor.BOLD)
                                    .then("  |  ")
                                    .color(ChatColor.WHITE)
                                    .then(">>>")
                                    .color(ChatColor.GRAY)
                                    .style(ChatColor.BOLD)
                                    .then("  |")
                                    .color(ChatColor.WHITE)
                                    .toJSONString();
                            IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                            PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                        }
                    }
                }
            }
            if (args.length == 1){
                switch (args[0]){
                    case "1":
                        for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {

                            int msgint = Integer.parseInt(msgnum);

                            if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 1){
                                p.sendMessage("§7[§c!§7] §cPage introuvable !");
                                return true;
                            }

                            if (msgnum.equalsIgnoreCase("7")){
                                return true;
                            }
                            if (msgnum.equalsIgnoreCase("1")){
                                p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                                p.sendMessage("§8");
                            }
                            String msg = plugin.getConfig().getString("help." + msgnum);
                            p.sendMessage(msg);
                            if (plugin.getConfig().getConfigurationSection("help.").contains("7")){
                                if (msgnum.equalsIgnoreCase("6")){
                                    String fm = new FancyMessage("|  ")
                                            .color(ChatColor.WHITE)
                                            .then("<<<")
                                            .color(ChatColor.GRAY)
                                            .style(ChatColor.BOLD)
                                            .then("  |  ")
                                            .color(ChatColor.WHITE)
                                            .then(">>>")
                                            .color(ChatColor.DARK_GRAY)
                                            .style(ChatColor.BOLD)
                                            .command("/help 2")
                                            .tooltip("§7Clique pour accéder")
                                            .then("  |")
                                            .color(ChatColor.WHITE)
                                            .toJSONString();
                                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                }
                            } else {
                                int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                                if (size == msgint){
                                    String fm = new FancyMessage("|  ")
                                            .color(ChatColor.WHITE)
                                            .then("<<<")
                                            .color(ChatColor.GRAY)
                                            .style(ChatColor.BOLD)
                                            .then("  |  ")
                                            .color(ChatColor.WHITE)
                                            .then(">>>")
                                            .color(ChatColor.GRAY)
                                            .style(ChatColor.BOLD)
                                            .then("  |")
                                            .color(ChatColor.WHITE)
                                            .toJSONString();
                                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                }
                            }
                        }

                        break;
                    case "2":
                        for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {
                            if (msgnum.equalsIgnoreCase("13")){
                                return true;
                            }
                            int msgint = Integer.parseInt(msgnum);


                            if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 7){
                                p.sendMessage("§7[§c!§7] §cPage introuvable !");
                                return true;
                            }

                            if (msgint < 7){

                            } else {
                                if (msgnum.equalsIgnoreCase("7")){
                                    p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                                    p.sendMessage("§8");
                                }
                                String msg = plugin.getConfig().getString("help." + msgnum);
                                p.sendMessage(msg);
                                if (plugin.getConfig().getConfigurationSection("help.").contains("13")){
                                    if (msgnum.equalsIgnoreCase("12")){
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 1")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 3")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                } else {
                                    int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                                    if (size == msgint){
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 1")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.GRAY)
                                                .style(ChatColor.BOLD)
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                }
                            }
                        }
                        break;
                    case "3":
                        for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {
                            if (msgnum.equalsIgnoreCase("19")) {
                                return true;
                            }
                            int msgint = Integer.parseInt(msgnum);

                            if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 13) {
                                p.sendMessage("§7[§c!§7] §cPage introuvable !");
                                return true;
                            }

                            if (msgint < 13) {

                            } else {
                                if (msgnum.equalsIgnoreCase("13")) {
                                    p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                                    p.sendMessage("§8");
                                }
                                String msg = plugin.getConfig().getString("help." + msgnum);
                                p.sendMessage(msg);
                                if (plugin.getConfig().getConfigurationSection("help.").contains("19")) {
                                    if (msgnum.equalsIgnoreCase("18")) {
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 2")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 4")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                } else {
                                    int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                                    if (size == msgint) {
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 2")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.GRAY)
                                                .style(ChatColor.BOLD)
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                }
                            }
                        }
                        break;
                    case "4":
                        for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {
                            if (msgnum.equalsIgnoreCase("25")){
                                return true;
                            }
                            int msgint = Integer.parseInt(msgnum);

                            if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 19){
                                p.sendMessage("§7[§c!§7] §cPage introuvable !");
                                return true;
                            }

                            if (msgint < 19){

                            } else {
                                if (msgnum.equalsIgnoreCase("19")){
                                    p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                                    p.sendMessage("§8");
                                }
                                String msg = plugin.getConfig().getString("help." + msgnum);
                                p.sendMessage(msg);
                                if (plugin.getConfig().getConfigurationSection("help.").contains("25")) {
                                    if (msgnum.equalsIgnoreCase("24")){
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 3")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 5")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                } else {
                                    int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                                    if (size == msgint) {
                                        String fm = new FancyMessage("|  ")
                                                .color(ChatColor.WHITE)
                                                .then("<<<")
                                                .color(ChatColor.DARK_GRAY)
                                                .style(ChatColor.BOLD)
                                                .command("/help 3")
                                                .tooltip("§7Clique pour accéder")
                                                .then("  |  ")
                                                .color(ChatColor.WHITE)
                                                .then(">>>")
                                                .color(ChatColor.GRAY)
                                                .style(ChatColor.BOLD)
                                                .then("  |")
                                                .color(ChatColor.WHITE)
                                                .toJSONString();
                                        IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                        PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                    }
                                }
                            }
                        }
                        break;
                    case "5":
                        for (String msgnum : plugin.getConfig().getConfigurationSection("help.").getKeys(false)) {
                            if (msgnum.equalsIgnoreCase("31")){
                                return true;
                            }
                            int msgint = Integer.parseInt(msgnum);

                            if (plugin.getConfig().getConfigurationSection("help.").getKeys(false).size() < 25){
                                p.sendMessage("§7[§c!§7] §cPage introuvable !");
                                return true;
                            }

                            if (msgint < 25){

                            } else {
                                if (msgnum.equalsIgnoreCase("25")){
                                    p.sendMessage("  §8> §aAide §8- §6Hutopya §8<");
                                    p.sendMessage("§8");
                                }
                                String msg = plugin.getConfig().getString("help." + msgnum);
                                p.sendMessage(msg);

                                int size = plugin.getConfig().getConfigurationSection("help.").getKeys(false).size();
                                if (size == msgint) {
                                    String fm = new FancyMessage("|  ")
                                            .color(ChatColor.WHITE)
                                            .then("<<<")
                                            .color(ChatColor.DARK_GRAY)
                                            .style(ChatColor.BOLD)
                                            .command("/help 3")
                                            .tooltip("§7Clique pour accéder")
                                            .then("  |  ")
                                            .color(ChatColor.WHITE)
                                            .then(">>>")
                                            .color(ChatColor.GRAY)
                                            .style(ChatColor.BOLD)
                                            .then("  |")
                                            .color(ChatColor.WHITE)
                                            .toJSONString();
                                    IChatBaseComponent fm2 = IChatBaseComponent.ChatSerializer.a(fm);
                                    PacketPlayOutChat fm3 = new PacketPlayOutChat(fm2);
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(fm3);
                                }
                            }
                        }
                        break;
                }
            }
            if (args.length > 1){
                p.sendMessage("§7[§c!§7] §cMerci de renseigner au maximum 1 argument");
            }
            return true;
        } else {
            System.out.println("Console");
        }
        return false;
    }

}
