package me.bscal.starter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.bscal.starter.listeners.KitPvpListener;
import me.bscal.starter.listeners.SoupListener;
import me.bscal.starter.ui.GUIManager;

public class Main extends JavaPlugin {

	static private BukkitScheduler scheduler;
	static private Plugin pluginInstance;
	
	public void onEnable() {
		pluginInstance = this;
		scheduler = getServer().getScheduler();
		EventStarter es = new EventStarter();
		getCommand("events").setExecutor(es);
		getCommand("join").setExecutor(es);
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new KitPvpListener(), this);
		pm.registerEvents(new SoupListener(), this);
		pm.registerEvents(new GUIManager(), this);
		
		 getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
		    {
		      public void run()
		      {
		        Player[] arrayOfPlayer;
		        int j = (arrayOfPlayer = Bukkit.getServer().getOnlinePlayers()).length;
		        for (int i = 0; i < j; i++)
		        {
		          Player p = arrayOfPlayer[i];
		          if (Main.this.getConfig().contains(p.getName()))
		          {
		            int i = Main.this.getConfig().getInt(p.getName() + ".Time Played");
		            Main.this.getConfig().set(p.getName() + ".Time Played", Integer.valueOf(i + 1));
		          }
		          else
		          {
		            Main.this.getConfig().createSection(p.getName());
		            Main.this.getConfig().set(p.getName() + ".Kills", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".Deaths", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".Money", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".KDR", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".StaffKilled", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".EventsWon", Integer.valueOf(0));
		            
		            Main.this.getConfig().set(p.getName() + ".Logins", Integer.valueOf(0));
		            Main.this.getConfig().set(p.getName() + ".Time Played", Integer.valueOf(1));
		          }
		        }
		        Main.this.saveConfig();
		      }
		    }, 0L, 20L);
		  }
	}
	
	static public Plugin getPluginIntance() {
		return pluginInstance;
	}
	
	static public BukkitScheduler getScheduler() {
		return scheduler;
	}
	
	
}
