package me.bscal.starter.ui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bscal.starter.Utils.Utils;
import me.bscal.starter.ui.ItemStacks.KitItemStacks;

public class GUIManager implements Listener {
	
	// Constant names of titles of gui's
	static public final String KIT_PVP_TITLE = "Kit PVP GUI!";
	static public final String BAN_TITLE = "Ban Player";
	private void cleanup(InventoryClickEvent e) {
		/**
		 * Cleanup
		 */
		e.setCancelled(true);
		e.getWhoClicked().closeInventory();
	}


	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player playerClick = (Player) e.getWhoClicked();
		if (playerClick.getItemInHand().getType() == Material.AIR) {
			cleanup(e);
			return;	// Nothing happens
		}
		
		// Kit PVP
		if (e.getInventory().getName() == KIT_PVP_TITLE) {
			onKitPVPInteract(e, playerClick);
			cleanup(e);
	}
		// Ban menu
		else if (e.getInventory().getName() == BAN_TITLE) {
			onBanInteract(e, playerClick);
			cleanup(e);
		}
		

	}


	/**
	 * Kit PVP GUI functionality
	 */
	private void onKitPVPInteract(InventoryClickEvent e, Player playerclick) {
		String name = e.getCurrentItem().getItemMeta().getDisplayName();
		if (name.contains("The PvP Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "pvp");
		
		else if (name.contains("Archer Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "archer");
		
		else if (name.contains("Kangaroo Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "kangaroo");
		
		else if (name.contains("Assassin Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "assassin");
		
		else if (name.contains("Flash Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "flash");
		
		else if (name.contains("Doge Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "doge");
		
		else if (name.contains("Cobra Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "cobra");
		
		else if (name.contains("Rogue Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "rogue");
		
		else if (name.contains("Scout Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "scout");
	}
	
	/**
	 * Ban GUI functionality
	 */

	

 	
	@EventHandler
	public void KitOpen(PlayerInteractEvent e) {
	 KitGUI ui = new KitGUI();  
	 Player p = e.getPlayer();	 
	 Action action = e.getAction();
	   ItemStack item = e.getItem();


	 
		   Player player = e.getPlayer();
	        if(item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(Utils.chat("&c&lKit Selector"))){
	            ui.show(p);
	            //do something...
		     }else
		     return;

		}
	
	
	private void onBanInteract(InventoryClickEvent e, Player playerClick) {
		// TODO Auto-generated method stub
	}
}
