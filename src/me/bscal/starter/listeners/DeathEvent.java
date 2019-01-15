package me.bscal.starter.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.bscal.starter.Utils.KitUtils.Lists;

public class DeathEvent implements Listener{

	
	
	@EventHandler
	public void onPlayerRemove(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player d = e.getEntity().getKiller();
	  Lists.hasKit.remove(p.getName());

	  
	}
	
	/*
	

	
	 @EventHandler
	  public void onEntityDamage(EntityDamageEvent e)
	  {
	    if ((e.getEntity() instanceof Player))
	    {
	      Player p = (Player)e.getEntity();
	      if (p.getHealth() - e.getFinalDamage() <= 0.0D)
	      {
	        e.setDamage(0.0D);
	        p.setHealth(20.0D);
	        p.setFoodLevel(20);
	        p.setSaturation(20.0F);
	        Lists.hasKit.remove(p.getName());
	        p.teleport(p.getWorld().getSpawnLocation());
	        
	
	        Bukkit.broadcastMessage(p.getDisplayName() + " died!");
	      }
	    }*/
	  
}
