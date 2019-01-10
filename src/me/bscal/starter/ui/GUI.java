package me.bscal.starter.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/***
 * Base class for GUI's. Can be used be used stand alone or as a parent class.
 */
public class GUI {

	protected final String title = "Kit PVP";
	
	public Inventory gui;
	
	/***
	 * Creates a new GUI setting contents to null.
	 * @param size Size of inventory. Must be multiple of 9
	 * @param title Title of the inventory
	 */
	public GUI(int size, String title) {
		this(size, title, null);
	}

	/***
	 * Creates a new GUI
	 * @param size Size of inventory. Must be multiple of 9
	 * @param title Title of the inventory
	 * @param contents The contents for the GUI. Can be null. Use material air for blank spaces.
	 */
	public GUI(int size, String title, ItemStack[] contents) {
		gui = Bukkit.getServer().createInventory(null, size, title);
		if (contents != null)
			gui.setContents(contents);
	}
	
	/***
	 * Shows the current inventory to the specified player
	 */
	public void show(Player p) {
		p.openInventory(gui);
	}
	
	public String getTitle() {
		return gui.getTitle();
	}
	
}
