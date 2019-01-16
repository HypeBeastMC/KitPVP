package me.bscal.starter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.bscal.starter.cmds.Hypebest;
import me.bscal.starter.cmds.Kits.Pvp;
import me.bscal.starter.config.DataManager;
import me.bscal.starter.events.SoccerManager;
import me.bscal.starter.listeners.DeathEvent;
import me.bscal.starter.listeners.JoinListener;
import me.bscal.starter.listeners.KitPvpListener;
import me.bscal.starter.listeners.SoupListener;
import me.bscal.starter.listeners.SpamCheckListener;
import me.bscal.starter.ui.GUIManager;

public class Main extends JavaPlugin {

	static private BukkitScheduler scheduler;
	static private JavaPlugin pluginInstance;
	
	private SoccerManager sm;
	
	public void onEnable() {
		pluginInstance = this;
		scheduler = getServer().getScheduler();
		EventStarter es = new EventStarter();
		getCommand("events").setExecutor(es);
		getCommand("join").setExecutor(es);
		getCommand("start").setExecutor(es);
		getCommand("soccer").setExecutor(es);
		
		
		 getCommand("pvp").setExecutor(new Pvp(this));
		 getCommand("hypebeast").setExecutor(new Hypebest());
		
		
		//Listeners
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new KitPvpListener(), this);
		pm.registerEvents(new SoupListener(), this);
		pm.registerEvents(new GUIManager(), this);
		pm.registerEvents(new DeathEvent(), this);
		pm.registerEvents(new SpamCheckListener(), this);
		DataManager arenaConfig = new DataManager(this, "arenas.yml", "arenas.yml");
		DataManager pvpConfig = new DataManager(this, "pvp.yml", "pvp.yml");
		
		sm = new SoccerManager();
		sm.initSoccer();
	}
	
	static public JavaPlugin getPluginIntance() {
		return pluginInstance;
	}
	
	static public BukkitScheduler getScheduler() {
		return scheduler;
	}
	
	
}
