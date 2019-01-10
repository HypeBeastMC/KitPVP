package me.bscal.starter.ui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIManager implements Listener {
	
	// Constant names of titles of gui's
	static public final String KIT_PVP_TITLE = "Kit PVP GUI!";
	static public final String BAN_TITLE = "Ban Player";
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player playerClick = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getItemMeta() == null) {
			return;	// Nothing happens
		}
		
		// Kit PVP
		if (e.getInventory().getName() == KIT_PVP_TITLE)
			onKitPVPInteract(e, playerClick);
		// Ban menu
		else if (e.getInventory().getName() == BAN_TITLE)
			onBanInteract(e, playerClick);
		
		/**
		 * Cleanup
		 */
		e.setCancelled(true);
		e.getWhoClicked().closeInventory();
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
	private void onBanInteract(InventoryClickEvent e, Player playerClick) {
		// TODO Auto-generated method stub
	}
}
