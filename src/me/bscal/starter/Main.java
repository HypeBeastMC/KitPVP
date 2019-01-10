package me.bscal.starter;

import org.bukkit.Bukkit;
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
	}
	
	static public Plugin getPluginIntance() {
		return pluginInstance;
	}
	
	static public BukkitScheduler getScheduler() {
		return scheduler;
	}
	
	
}
