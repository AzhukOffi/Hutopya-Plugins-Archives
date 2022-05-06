package fr.azhuk.team;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Methods {

    //Team Methods

    MySQL SQL = new MySQL();

    Main plugin = Main.getPlugin(Main.class);

    public Inventory teamsInv(int team1ID, int team2ID, int team3ID, int team4ID, int team5ID){

        //creation de l'inv
        Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.teamsInv.name"));

        //barrier
        ItemStack barrier = new ItemStack(Material.BARRIER, 1);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName("§6");
        barrier.setItemMeta(barrierMeta);

        /*
        Team 1
         */
        if (team1ID != 0 && SQL.teamIdExists(team1ID)){ //Si elle existe
            ArrayList<String> team1Infos = new ArrayList<>(SQL.getInfos(team1ID));      //Récupération infos
            if (Material.getMaterial(team1Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team1Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team1Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(13, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(13, barrier);
        }
        /*
        Team 2
         */
        if (team2ID != 0 && SQL.teamIdExists(team2ID)){ //Si elle existe
            ArrayList<String> team2Infos = new ArrayList<>(SQL.getInfos(team2ID));      //Récupération infos
            if (Material.getMaterial(team2Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team2Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team2Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(28, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(28, barrier);
        }

        /*
        Team 3
         */
        if (team3ID != 0 && SQL.teamIdExists(team3ID)){ //Si elle existe
            ArrayList<String> team3Infos = new ArrayList<>(SQL.getInfos(team3ID));      //Récupération infos
            if (Material.getMaterial(team3Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team3Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team3Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(30, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(30, barrier);
        }

        /*
        Team 4
         */
        if (team4ID != 0 && SQL.teamIdExists(team4ID)){ //Si elle existe
            ArrayList<String> team4Infos = new ArrayList<>(SQL.getInfos(team4ID));      //Récupération infos
            if (Material.getMaterial(team4Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team4Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team4Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(32, team);
            }
        } else {
            inv.setItem(32, barrier);
        }

        /*
        Team 5
         */
        if (team5ID != 0 && SQL.teamIdExists(team5ID)){ //Si elle existe
            ArrayList<String> team5Infos = new ArrayList<>(SQL.getInfos(team5ID));      //Récupération infos
            if (Material.getMaterial(team5Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team5Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team5Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(34, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(34, barrier);
        }

        //verre gris
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§6");
        glass.setItemMeta(glassMeta);

        //set verre gris
        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, glass);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        inv.setItem(16, glass);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        inv.setItem(27, glass);
        inv.setItem(29, glass);
        inv.setItem(31, glass);
        inv.setItem(33, glass);
        inv.setItem(35, glass);
        inv.setItem(36, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(39, glass);
        inv.setItem(40, glass);
        inv.setItem(41, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);
        inv.setItem(44, glass);


        return inv;
    }


    public Inventory teamEditInv(int teamId){

        if (teamId != 0){ //Si elle existe
            ArrayList<String> teamInfos = new ArrayList<>(SQL.getInfos(teamId));      //Récupération infos

            //creation de l'inv
            Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.teamEditInv.name") + teamInfos.get(0));

            //barrier
            ItemStack barrier = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrierMeta = barrier.getItemMeta();
            barrierMeta.setDisplayName("§6");
            barrier.setItemMeta(barrierMeta);


            if (Material.getMaterial(teamInfos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack icon = new ItemStack(Material.getMaterial(teamInfos.get(3)), 1);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(plugin.messages.getString("gui.teamEditInv.icon"));
                ArrayList<String> iconLore = new ArrayList<String>();
                for (String loreList: plugin.messages.getConfigurationSection("gui.teamEditInv.icon_lore").getKeys(false)){
                    String msg = plugin.messages.getString("gui.teamEditInv.icon_lore." + loreList);
                    if (loreList.equalsIgnoreCase("2")){
                        msg = msg + teamInfos.get(3);
                    }
                    iconLore.add(msg);
                }
                iconMeta.setLore(iconLore);
                icon.setItemMeta(iconMeta);

                //set dans l'inv
                inv.setItem(19, icon);
            } else {
                //ItemStack
                ItemStack icon = new ItemStack(Material.GRASS_BLOCK, 1);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(plugin.messages.getString("gui.teamEditInv.name"));
                ArrayList<String> iconLore = new ArrayList<String>();
                for (String loreList: plugin.messages.getConfigurationSection("gui.teamEditInv.icon_lore").getKeys(false)){
                    String msg = plugin.messages.getString("gui.teamEditInv.icon_lore." + loreList);
                    if (loreList.equalsIgnoreCase("2")){
                        msg = msg + teamInfos.get(3);
                    }
                    iconLore.add(msg);
                }
                iconMeta.setLore(iconLore);
                icon.setItemMeta(iconMeta);


                inv.setItem(19, icon);
            }

            //ItemStack
            ItemStack prefix = new ItemStack(Material.BLUE_BANNER, 1);
            ItemMeta prefixMeta = prefix.getItemMeta();
            prefixMeta.setDisplayName(plugin.messages.getString("gui.teamEditInv.prefix"));
            ArrayList<String> prefixLore = new ArrayList<String>();
            for (String loreList: plugin.messages.getConfigurationSection("gui.teamEditInv.prefix_lore").getKeys(false)){
                String msg = plugin.messages.getString("gui.teamEditInv.prefix_lore." + loreList);
                if (loreList.equalsIgnoreCase("2")){
                    msg = msg + teamInfos.get(2);
                }
                prefixLore.add(msg);
            }
            prefixMeta.setLore(prefixLore);
            prefix.setItemMeta(prefixMeta);

            inv.setItem(23, prefix);

            //ItemStack
            ItemStack desc = new ItemStack(Material.OAK_SIGN, 1);
            ItemMeta descMeta = desc.getItemMeta();
            descMeta.setDisplayName(plugin.messages.getString("gui.teamEditInv.desc"));
            ArrayList<String> descLore = new ArrayList<String>();
            for (String loreList: plugin.messages.getConfigurationSection("gui.teamEditInv.desc_lore").getKeys(false)){
                String msg = plugin.messages.getString("gui.teamEditInv.desc_lore." + loreList);
                if (loreList.equalsIgnoreCase("2")){
                    msg = msg + teamInfos.get(1);
                }
                descLore.add(msg);
            }
            descMeta.setLore(descLore);
            desc.setItemMeta(descMeta);

            inv.setItem(21, desc);

            //ItemStack
            ItemStack name = new ItemStack(Material.ANVIL, 1);
            ItemMeta nameMeta = name.getItemMeta();
            nameMeta.setDisplayName(plugin.messages.getString("gui.teamEditInv.teamname"));
            ArrayList<String> nameLore = new ArrayList<String>();
            for (String loreList: plugin.messages.getConfigurationSection("gui.teamEditInv.teamname_lore").getKeys(false)){
                String msg = plugin.messages.getString("gui.teamEditInv.teamname_lore." + loreList);
                if (loreList.equalsIgnoreCase("2")){
                    msg = msg + teamInfos.get(0);
                }

                nameLore.add(msg);
            }
            nameMeta.setLore(nameLore);
            name.setItemMeta(nameMeta);

            inv.setItem(25, name);



            //verre gris
            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName("§6");
            glass.setItemMeta(glassMeta);

            //set verre gris
            inv.setItem(0, glass);
            inv.setItem(1, glass);
            inv.setItem(2, glass);
            inv.setItem(3, glass);
            inv.setItem(4, glass);
            inv.setItem(5, glass);
            inv.setItem(6, glass);
            inv.setItem(7, glass);
            inv.setItem(8, glass);
            inv.setItem(9, glass);
            inv.setItem(10, glass);
            inv.setItem(11, glass);
            inv.setItem(12, glass);
            inv.setItem(13, glass);
            inv.setItem(14, glass);
            inv.setItem(15, glass);
            inv.setItem(16, glass);
            inv.setItem(17, glass);
            inv.setItem(18, glass);
            inv.setItem(20, glass);
            inv.setItem(22, glass);
            inv.setItem(24, glass);
            inv.setItem(26, glass);
            inv.setItem(27, glass);
            inv.setItem(28, glass);
            inv.setItem(29, glass);
            inv.setItem(30, glass);
            inv.setItem(31, glass);
            inv.setItem(32, glass);
            inv.setItem(33, glass);
            inv.setItem(34, glass);
            inv.setItem(35, glass);
            inv.setItem(36, glass);
            inv.setItem(37, glass);
            inv.setItem(38, glass);
            inv.setItem(39, glass);
            inv.setItem(40, glass);
            inv.setItem(41, glass);
            inv.setItem(42, glass);
            inv.setItem(43, glass);
            inv.setItem(44, glass);


            return inv;
        }
        return null;
    }

    public Inventory teamViewInv(int teamId){

        if (teamId != 0){ //Si elle existe
            ArrayList<String> teamInfos = new ArrayList<>(SQL.getInfos(teamId));      //Récupération infos

            //creation de l'inv
            Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.teamViewInv.name") + teamInfos.get(0));

            //barrier
            ItemStack barrier = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrierMeta = barrier.getItemMeta();
            barrierMeta.setDisplayName("§6");
            barrier.setItemMeta(barrierMeta);


            if (Material.getMaterial(teamInfos.get(3)) != null){                       //Si l'item n'est pas null
                //ItemStack
                ItemStack icon = new ItemStack(Material.getMaterial(teamInfos.get(3)), 1);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(teamInfos.get(0));
                icon.setItemMeta(iconMeta);

                //set dans l'inv
                inv.setItem(22, icon);
            } else {
                //ItemStack
                ItemStack icon = new ItemStack(Material.GRASS_BLOCK, 1);
                ItemMeta iconMeta = icon.getItemMeta();
                iconMeta.setDisplayName(teamInfos.get(0));
                icon.setItemMeta(iconMeta);

                inv.setItem(22, icon);
            }

            //ItemStack
            ItemStack prefix = new ItemStack(Material.BLUE_BANNER, 1);
            ItemMeta prefixMeta = prefix.getItemMeta();
            if (teamInfos.get(2).equalsIgnoreCase("NA")){
                prefixMeta.setDisplayName("✘");
            } else {
                prefixMeta.setDisplayName(teamInfos.get(2));
            }
            prefix.setItemMeta(prefixMeta);

            inv.setItem(20, prefix);

            //ItemStack
            ItemStack desc = new ItemStack(Material.OAK_SIGN, 1);
            ItemMeta descMeta = desc.getItemMeta();
            descMeta.setDisplayName(teamInfos.get(1));
            desc.setItemMeta(descMeta);

            inv.setItem(24, desc);


            //verre gris
            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName("§6");
            glass.setItemMeta(glassMeta);

            //set verre gris
            inv.setItem(0, glass);
            inv.setItem(1, glass);
            inv.setItem(2, glass);
            inv.setItem(3, glass);
            inv.setItem(4, glass);
            inv.setItem(5, glass);
            inv.setItem(6, glass);
            inv.setItem(7, glass);
            inv.setItem(8, glass);
            inv.setItem(9, glass);
            inv.setItem(10, glass);
            inv.setItem(11, glass);
            inv.setItem(12, glass);
            inv.setItem(13, glass);
            inv.setItem(14, glass);
            inv.setItem(15, glass);
            inv.setItem(16, glass);
            inv.setItem(17, glass);
            inv.setItem(18, glass);
            inv.setItem(19, glass);
            inv.setItem(21, glass);
            inv.setItem(23, glass);
            inv.setItem(25, glass);
            inv.setItem(26, glass);
            inv.setItem(27, glass);
            inv.setItem(28, glass);
            inv.setItem(29, glass);
            inv.setItem(30, glass);
            inv.setItem(31, glass);
            inv.setItem(32, glass);
            inv.setItem(33, glass);
            inv.setItem(34, glass);
            inv.setItem(35, glass);
            inv.setItem(36, glass);
            inv.setItem(37, glass);
            inv.setItem(38, glass);
            inv.setItem(39, glass);
            inv.setItem(40, glass);
            inv.setItem(41, glass);
            inv.setItem(42, glass);
            inv.setItem(43, glass);
            inv.setItem(44, glass);


            return inv;
        }
        return null;
    }

    public String getName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader nameJson = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size()-1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "error";
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader nameJson = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(readAll(nameJson));
            return nameObject.get("id").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }



    public Inventory TeamMembers(int id, int page){
        if (id != 0 && SQL.teamIdExists(id)){//Si elle existe

            //creation de l'inv
            Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.TeamMembers.name"));


            String ownerUUID = SQL.getOwner(id);
            String name = getName(ownerUUID);
            //ItemStack
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setDisplayName(plugin.messages.getString("gui.TeamMembers.chef") + name);
            skullMeta.setOwner(name);
            skull.setItemMeta(skullMeta);


            //set dans l'inv
            inv.setItem(13, skull);


            //verre gris
            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName("§6");
            glass.setItemMeta(glassMeta);

            //after
            ItemStack after = new ItemStack(Material.ARROW, 1);
            ItemMeta afterMeta = after.getItemMeta();
            afterMeta.setDisplayName(plugin.messages.getString("gui.TeamMembers.next"));
            after.setItemMeta(afterMeta);

            inv.setItem(35, after);

            //page
            int actualPage = page + 1;
            ItemStack pageStack = new ItemStack(Material.PAPER, 1);
            ItemMeta pageMeta = pageStack.getItemMeta();
            pageMeta.setDisplayName(plugin.messages.getString("gui.TeamMembers.actuel") + actualPage);
            pageStack.setItemMeta(pageMeta);

            inv.setItem(40, pageStack);

            //previous
            ItemStack previous = new ItemStack(Material.ARROW, 1);
            ItemMeta previousMeta = previous.getItemMeta();
            previousMeta.setDisplayName(plugin.messages.getString("gui.TeamMembers.previous"));
            previous.setItemMeta(previousMeta);

            inv.setItem(27, previous);




            //set verre gris
            inv.setItem(0, glass);
            inv.setItem(1, glass);
            inv.setItem(2, glass);
            inv.setItem(3, glass);
            inv.setItem(4, glass);
            inv.setItem(5, glass);
            inv.setItem(6, glass);
            inv.setItem(7, glass);
            inv.setItem(8, glass);
            inv.setItem(9, glass);
            inv.setItem(10, glass);
            inv.setItem(11, glass);
            inv.setItem(12, glass);
            inv.setItem(14, glass);
            inv.setItem(15, glass);
            inv.setItem(16, glass);
            inv.setItem(17, glass);
            inv.setItem(18, glass);
            inv.setItem(19, glass);
            inv.setItem(20, glass);
            inv.setItem(21, glass);
            inv.setItem(22, glass);
            inv.setItem(23, glass);
            inv.setItem(24, glass);
            inv.setItem(25, glass);
            inv.setItem(26, glass);
            inv.setItem(36, glass);
            inv.setItem(37, glass);
            inv.setItem(38, glass);
            inv.setItem(39, glass);
            inv.setItem(41, glass);
            inv.setItem(42, glass);
            inv.setItem(43, glass);
            inv.setItem(44, glass);

            ArrayList<String> list1 = new ArrayList<>(SQL.getMembersWithType(id, 2));
            ArrayList<String> list2 = new ArrayList<>(SQL.getMembersWithType(id, 1));
            ArrayList<String> list3 = new ArrayList<>(SQL.getMembersWithType(id, 0));
            int listTotal = list1.size() + list2.size() + list3.size();
            int slot = 28;
            int i = page*7;
            int l1 = list1.size();
            int l2 = list2.size();
            int l3 = list3.size();
            int rankOfOwner = SQL.getRank(ownerUUID);
            switch (rankOfOwner){
                case 2:
                    l1--;
                    break;
                case 1:
                    l2--;
                    break;
                case 0:
                    l3--;
                    break;
            }
            int toSet = i;
            if (listTotal <= i){
                return inv;
            }
            if (l1 != 0){
                ItemStack playerStack = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta playerMeta = (SkullMeta) skull.getItemMeta();

                while (true){
                    if (i >= l1 && i != 0){
                        break;
                    }
                    String player = list1.get(i);
                    if (!Bukkit.getOfflinePlayer(player).getUniqueId().toString().equalsIgnoreCase(ownerUUID)){
                        playerMeta.setDisplayName("§6Adjoint: §8" + player);
                        playerMeta.setOwner(player);
                        playerStack.setItemMeta(playerMeta);

                        inv.setItem(slot, playerStack);
                        if (slot == 34){
                            return inv;
                        }
                        slot++;
                    } else {
                        l1--;
                    }
                    i++;

                }
            }
            if (l2 != 0){
                ItemStack playerStack = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta playerMeta = (SkullMeta) skull.getItemMeta();


                while (true){
                    toSet = i - l1;
                    if (toSet >= l2 && i != 0){
                        break;
                    }

                    String player = list2.get(toSet);
                    if (!Bukkit.getOfflinePlayer(player).getUniqueId().toString().equalsIgnoreCase(ownerUUID)){
                        playerMeta.setDisplayName("§6Officier: §8" + player);
                        playerMeta.setOwner(player);
                        playerStack.setItemMeta(playerMeta);

                        inv.setItem(slot, playerStack);
                        if (slot == 34){
                            return inv;
                        }
                        slot++;
                    } else {
                        l2--;
                    }
                    i++;

                }
            }
            if (l3 != 0){
                ItemStack playerStack = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta playerMeta = (SkullMeta) skull.getItemMeta();

                while (true){
                    toSet = i - l1 - l2;
                    if (toSet >= l3 && i != 0){
                        if (slot == 28 && page != 0){
                            return null;
                        }
                        return inv;
                    }

                    String player = list3.get(toSet);
                    if (!Bukkit.getOfflinePlayer(player).getUniqueId().toString().equalsIgnoreCase(ownerUUID)){
                        playerMeta.setDisplayName("§6Membres: §8" + player);
                        playerMeta.setOwner(player);
                        playerStack.setItemMeta(playerMeta);

                        inv.setItem(slot, playerStack);
                        if (slot == 34){
                            return inv;
                        }
                        slot++;
                    } else {
                        l3--;
                    }
                    i++;
                }
            }
            return inv;
        }
        return null;
    }

    public Inventory RankInv(Player p){
        //creation de l'inv
        Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.RankInv.name"));


        OfflinePlayer op = Bukkit.getOfflinePlayer(p.getName());
        //ItemStack
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName("§6" + op.getName());
        skullMeta.setOwner(op.getName());
        skull.setItemMeta(skullMeta);

        //set dans l'inv
        inv.setItem(13, skull);


        //diamond
        ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK, 1);
        ItemMeta diamondMeta = diamond.getItemMeta();
        diamondMeta.setDisplayName(plugin.messages.getString("gui.RankInv.diamond"));
        diamond.setItemMeta(diamondMeta);

        inv.setItem(33, diamond);

        //gold
        ItemStack gold = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName(plugin.messages.getString("gui.RankInv.gold"));
        gold.setItemMeta(goldMeta);

        inv.setItem(31, gold);

        //iron
        ItemStack iron = new ItemStack(Material.IRON_BLOCK, 1);
        ItemMeta ironMeta = iron.getItemMeta();
        ironMeta.setDisplayName(plugin.messages.getString("gui.RankInv.iron"));
        iron.setItemMeta(ironMeta);

        inv.setItem(29, iron);

        //verre gris
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§6");
        glass.setItemMeta(glassMeta);

        //set verre gris
        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, glass);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        inv.setItem(16, glass);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);
        inv.setItem(30, glass);
        inv.setItem(32, glass);
        inv.setItem(34, glass);
        inv.setItem(35, glass);
        inv.setItem(36, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(39, glass);
        inv.setItem(40, glass);
        inv.setItem(41, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);
        inv.setItem(44, glass);


        return inv;
    }

    public Inventory LeaveTeam(int team1ID, int team2ID, int team3ID, int team4ID, int team5ID){

        //creation de l'inv
        Inventory inv = Bukkit.createInventory(null, 45, plugin.messages.getString("gui.LeaveTeam.name"));

        //barrier
        ItemStack barrier = new ItemStack(Material.BARRIER, 1);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName("§6");
        barrier.setItemMeta(barrierMeta);

        /*
        Team 1
         */
        if (team1ID != 0 && SQL.teamIdExists(team1ID)){ //Si elle existe
            ArrayList<String> team1Infos = new ArrayList<>(SQL.getInfos(team1ID));      //Récupération infos
            if (Material.getMaterial(team1Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team1Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team1Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(13, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(13, barrier);
        }
        /*
        Team 2
         */
        if (team2ID != 0 && SQL.teamIdExists(team2ID)){ //Si elle existe
            ArrayList<String> team2Infos = new ArrayList<>(SQL.getInfos(team2ID));      //Récupération infos
            if (Material.getMaterial(team2Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team2Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team2Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(28, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(28, barrier);
        }

        /*
        Team 3
         */
        if (team3ID != 0 && SQL.teamIdExists(team3ID)){ //Si elle existe
            ArrayList<String> team3Infos = new ArrayList<>(SQL.getInfos(team3ID));      //Récupération infos
            if (Material.getMaterial(team3Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team3Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team3Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(30, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(30, barrier);
        }

        /*
        Team 4
         */
        if (team4ID != 0 && SQL.teamIdExists(team4ID)){ //Si elle existe
            ArrayList<String> team4Infos = new ArrayList<>(SQL.getInfos(team4ID));      //Récupération infos
            if (Material.getMaterial(team4Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team4Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team4Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(32, team);
            }
        } else {
            inv.setItem(32, barrier);
        }

        /*
        Team 5
         */
        if (team5ID != 0 && SQL.teamIdExists(team5ID)){ //Si elle existe
            ArrayList<String> team5Infos = new ArrayList<>(SQL.getInfos(team5ID));      //Récupération infos
            if (Material.getMaterial(team5Infos.get(3)) != null){                       //Si l'item n'est pas null

                //ItemStack
                ItemStack team = new ItemStack(Material.getMaterial(team5Infos.get(3)), 1);
                ItemMeta teamMeta = team.getItemMeta();
                teamMeta.setDisplayName(plugin.messages.getString("gui.teamsInv.team_color") + team5Infos.get(0));
                team.setItemMeta(teamMeta);

                //set dans l'inv
                inv.setItem(34, team);
            }
        } else { //sinon remplacer par barrier
            inv.setItem(34, barrier);
        }

        //verre gris
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§6");
        glass.setItemMeta(glassMeta);

        //set verre gris
        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(2, glass);
        inv.setItem(3, glass);
        inv.setItem(4, glass);
        inv.setItem(5, glass);
        inv.setItem(6, glass);
        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(9, glass);
        inv.setItem(10, glass);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        inv.setItem(16, glass);
        inv.setItem(17, glass);
        inv.setItem(18, glass);
        inv.setItem(19, glass);
        inv.setItem(20, glass);
        inv.setItem(21, glass);
        inv.setItem(22, glass);
        inv.setItem(23, glass);
        inv.setItem(24, glass);
        inv.setItem(25, glass);
        inv.setItem(26, glass);
        inv.setItem(27, glass);
        inv.setItem(29, glass);
        inv.setItem(31, glass);
        inv.setItem(33, glass);
        inv.setItem(35, glass);
        inv.setItem(36, glass);
        inv.setItem(37, glass);
        inv.setItem(38, glass);
        inv.setItem(39, glass);
        inv.setItem(40, glass);
        inv.setItem(41, glass);
        inv.setItem(42, glass);
        inv.setItem(43, glass);
        inv.setItem(44, glass);


        return inv;
    }

    //Claim methods

    



}
