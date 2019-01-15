package me.bscal.starter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.bscal.starter.config.DataManager;
import me.bscal.starter.events.SoccerManager;
import me.bscal.starter.listeners.KitPvpListener;
import me.bscal.starter.listeners.SoupListener;

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
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new KitPvpListener(), this);
		pm.registerEvents(new SoupListener(), this);
		//pm.registerEvents(new GUIManager(), this);
		
		DataManager arenaConfig = new DataManager(this, "arenas.yml", "arenas.yml");
		
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
