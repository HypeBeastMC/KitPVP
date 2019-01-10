import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.bscal.starter.Main;

public class ConfigRegisterListener
  implements Listener
{
  Main pl;
  
  public ConfigRegisterListener(Main pl)
  {
    this.pl = pl;
  }
  
  @EventHandler
  public void onLoginFirstTime(PlayerJoinEvent evt)
  {
    Player p = evt.getPlayer();
    if (!this.pl.getConfig().contains(evt.getPlayer().getName()))
    {
      this.pl.getConfig().createSection(p.getName());
      this.pl.getConfig().set(evt.getPlayer().getName() + ".Kills", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".Deaths", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".Money", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".KDR", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".StaffKilled", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".EventsWon", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".Logins", Integer.valueOf(0));
      this.pl.getConfig().set(evt.getPlayer().getName() + ".Time Played", Integer.valueOf(0));
    }
    int i = this.pl.getConfig().getInt(evt.getPlayer().getName() + ".Logins");
    this.pl.getConfig().set(evt.getPlayer().getName() + ".Logins", Integer.valueOf(i + 1));
    this.pl.saveConfig();
  }
  
  @EventHandler
  public void onPlayerStaffDeath(PlayerDeathEvent evt)
  {
    Player p = evt.getEntity();
    Player d = evt.getEntity().getKiller();
    if (p.hasPermission("unknown.staff"))
    {
      int i = this.pl.getConfig().getInt(d.getName() + ".StaffKilled");
      this.pl.getConfig().set(d.getName() + ".StaffKilled", Integer.valueOf(i + 1));
      d.sendMessage(ChatColor.RED + "You killed a staff member!");
    }
  }
  
  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent evt)
  {
    Player p = evt.getEntity();
    evt.setDeathMessage(null);
    if (!this.pl.getConfig().contains(p.getName()))
    {
      this.pl.getConfig().createSection(p.getName());
      this.pl.getConfig().set(p.getName() + ".Kills", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".Deaths", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".Money", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".KDR", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".StaffKilled", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".EventsWon", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".Logins", Integer.valueOf(0));
      this.pl.getConfig().set(p.getName() + ".Time Played", Integer.valueOf(0));
      this.pl.saveConfig();
    }
    else
    {
      int i = this.pl.getConfig().getInt(p.getName() + ".Deaths");
      this.pl.getConfig().set(p.getName() + ".Deaths", Integer.valueOf(i + 1));
      int kills = this.pl.getConfig().getInt(p.getName() + ".Kills");
      double kdr = kills / (i + 1);
      this.pl.getConfig().set(p.getName() + ".KDR", Double.valueOf(kdr));
      this.pl.saveConfig();
    }
    if (p.getKiller() != null)
    {
      if ((p.getKiller() instanceof Player))
      {
        Player k = p.getKiller();
        if (k.getName() != p.getName())
        {
          if (!this.pl.getConfig().contains(k.getName()))
          {
            this.pl.getConfig().createSection(k.getName());
            this.pl.getConfig().set(k.getName() + ".Kills", Integer.valueOf(0));
            this.pl.getConfig().set(k.getName() + ".Deaths", Integer.valueOf(0));
            this.pl.getConfig().set(k.getName() + ".Money", Integer.valueOf(0));
            this.pl.getConfig().set(k.getName() + ".KDR", Integer.valueOf(0));
            this.pl.getConfig().set(p.getName() + ".StaffKilled", Integer.valueOf(0));
            this.pl.getConfig().set(p.getName() + ".EventsWon", Integer.valueOf(0));
            this.pl.getConfig().set(k.getName() + ".Logins", Integer.valueOf(0));
            this.pl.getConfig().set(k.getName() + ".Time Played", Integer.valueOf(0));
            this.pl.saveConfig();
          }
          int i = this.pl.getConfig().getInt(k.getName() + ".Kills");
          this.pl.getConfig().set(k.getName() + ".Kills", Integer.valueOf(i + 1));
          int deaths = this.pl.getConfig().getInt(k.getName() + ".Deaths");
          int m = this.pl.getConfig().getInt(k.getName() + ".Money");
          Random r = new Random();
          int Low = 60;
          int High = 120;
          int R = r.nextInt(High - Low) + Low;
          this.pl.getConfig().set(k.getName() + ".Money", Integer.valueOf(m + R));
          double kdr = (i + 1) / deaths;
          this.pl.getConfig().set(k.getName() + ".KDR", Double.valueOf(kdr));
          this.pl.saveConfig();
          k.sendMessage(ChatColor.GREEN + "You have recieved " + R + " Credits for killing " + p.getName());
        }
        else
        {
          p.sendMessage(ChatColor.RED + "You will not get rewarded for killing yourself.");
        }
      }
      else
      {
        p.sendMessage(ChatColor.RED + "You have died!");
      }
    }
    else {
      p.sendMessage(ChatColor.RED + "You have died!");
    }
  }
}
