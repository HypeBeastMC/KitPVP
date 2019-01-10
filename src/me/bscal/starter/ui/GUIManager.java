package me.bscal.starter.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUIManager implements Listener {
	
	private Inventory UI;
	private GUIItemStacks itemStacks = new GUIItemStacks();
	
	public GUIManager() {
		UI = Bukkit.getServer().createInventory(null, 27, "Kit Gui!");
		// Row 1
		UI.setItem(0, itemStacks.nullKit());
		UI.setItem(1, itemStacks.nullKit());
		UI.setItem(2, itemStacks.nullKit());
		UI.setItem(3, itemStacks.nullKit());
		UI.setItem(4, itemStacks.nullKit());
		UI.setItem(5, itemStacks.nullKit());
		UI.setItem(6, itemStacks.nullKit());
		UI.setItem(7, itemStacks.nullKit());
		UI.setItem(8, itemStacks.nullKit());
	    // Row 2
		UI.setItem(9, itemStacks.pvpKit());
		UI.setItem(10, itemStacks.archerKit());
		UI.setItem(11, itemStacks.kangarooKit());
		UI.setItem(12, itemStacks.assassinKit());
		UI.setItem(13, itemStacks.ninjaKit());
		UI.setItem(14, itemStacks.flashKit());
		UI.setItem(15, itemStacks.thorKit());
		UI.setItem(16, itemStacks.scoutKit());
	    // Row 3
		UI.setItem(17, itemStacks.firemanKit());
		UI.setItem(18, itemStacks.rogueKit());
		UI.setItem(20, itemStacks.cobraKit());
		UI.setItem(21, itemStacks.dogeKit());
	}
	
	public void show(Player p) {
		p.openInventory(UI);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player playerclick = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		
		// Runs command that will give the player the right kit
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("The PvP Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "pvp");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "archer");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kangaroo Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "kangaroo");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Assassin Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "assassin");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Flash Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "flash");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Doge Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "doge");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Cobra Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "cobra");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Rogue Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "rogue");
		
		else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Scout Kit"))
			e.getWhoClicked().getServer().dispatchCommand(playerclick, "scout");
		
		e.setCancelled(true);
		e.getWhoClicked().closeInventory();
	}
}
