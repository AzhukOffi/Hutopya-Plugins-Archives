package fr.azhuk.dailyjobs;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.JobProgression;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JobsListener implements Listener {

    MySQL mySQL = new MySQL();

    Main plugin;

    public JobsListener(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        plugin.refreshConnection();
        List<JobProgression> jobs = Jobs.getPlayerManager().getJobsPlayer(p).getJobProgression();
        String J1 = "aucun";
        String J2 = "aucun";
        String J3 = "aucun";
        int i = 0;
        for (JobProgression OneJob : jobs) {
            if (i == 0) {
                J1 = OneJob.getJob().getName();
                i++;
            }
            if (i == 1) {
                J2 = OneJob.getJob().getName();
                i++;
            }
            if (i == 2) {
                J3 = OneJob.getJob().getName();
                i = 0;
            }
        }
        if (!mySQL.playerExists(p.getUniqueId())) {
            mySQL.createPlayer(p.getUniqueId(), p, J1, J2, J3, 0, 0, 0);
            if (J1.equalsIgnoreCase("aucun")){
                return;
            } else {

            }
        }


    }
}
