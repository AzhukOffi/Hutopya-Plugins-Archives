package fr.azhuk.backuprestorer;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.MultiverseCoreConfiguration;
import com.onarandombox.MultiverseCore.commands.MultiverseCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BackupRestorer" + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Activation");
        getCommand("restorebackup").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length < 1 || args.length > 2) {
                p.sendMessage("Arg incorrect");
                return false;
            }
            if (args.length == 1){
                World world = Bukkit.getWorld(args[0]);
                if (world != null){
                    String base = Bukkit.getWorldContainer().getAbsolutePath().replaceAll(".", "");
                    File backupFoler = new File(base + "backups");
                    int i = 1;
                    for (File backup: backupFoler.listFiles()) {
                        p.sendMessage(i + ": Backup avec le nom : " + backup.getName());
                        if (i >= 5){
                            break;
                        }
                        i++;
                    }
                } else {
                    p.sendMessage("Monde incorrect");
                    return false;
                }
            }
            if (args.length == 2){
                World world = Bukkit.getWorld(args[0]);
                if (world != null){
                    File backupFoler = new File("/backups");
                    backupFoler.listFiles();
                    int i = 1;
                    String zip = null;
                    int arg2 = Integer.valueOf(args[1]);
                    if (arg2 > 5){
                        p.sendMessage("arg2 trop grand");
                        return false;
                    }
                    for (File backup: backupFoler.listFiles()) {
                        if (i == arg2){
                            zip = "/backups/" + backup.getName();
                            break;
                        }
                        i++;
                    }
                    if (zip == null){
                        System.out.println("erreur");
                        return false;
                    }
                    getServer().dispatchCommand(getServer().getConsoleSender(), "mv delete " + world.getName());
                    getServer().dispatchCommand(getServer().getConsoleSender(), "mv confirm");
                    unZipIt(zip, world);
                } else {
                    p.sendMessage("Monde incorrect");
                    return false;
                }
            }

        }

        return true;
    }
    public void unZipIt(String zipFile, World world) {

        byte[] buffer = new byte[1024];



        try {

            //create output directory is not exists
            File folder = new File("/" + world.getName());
            if (!folder.exists()) {
                folder.mkdir();
            }

            //get the zip file content
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null && ze.getName().contains(world.getName()) && !ze.getName().contains("plugins")) {

                String fileName = ze.getName();
                File newFile = new File("/" + world.getName() + File.separator + fileName);

                System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();
            }

            zis.closeEntry();
            zis.close();
            FileOwnerAttributeView foaw1 = Files.getFileAttributeView(Paths.get(Bukkit.getWorldContainer().getAbsolutePath().replaceAll(".", "") + "/plugins"), FileOwnerAttributeView.class);
            FileOwnerAttributeView foaw2 = Files.getFileAttributeView(Paths.get(Bukkit.getWorldContainer().getAbsolutePath().replaceAll(".", "")), FileOwnerAttributeView.class);
            foaw2.setOwner(foaw1.getOwner());
            getServer().dispatchCommand(getServer().getConsoleSender(), "mv import" + world.getName() + "normal CleanRoomGenerator");
            System.out.println("Fini");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
