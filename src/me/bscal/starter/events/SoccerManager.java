package me.bscal.starter.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import me.bscal.starter.Main;

/***
 * SoccerManager manages multiple instances and has them update at 20 tps;
 */
public class SoccerManager {
	static public List<Soccer> soccerInstances = new ArrayList<Soccer>();
	
	public SoccerManager() {
		//soccerInstances.add(new Soccer());
	}
	
	public void initSoccer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPluginIntance(), new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < soccerInstances.size(); i++) {
					soccerInstances.get(i).update();
					Bukkit.getLogger().info("test");
				}
			}
		}, 0L, 20L);
	}
}