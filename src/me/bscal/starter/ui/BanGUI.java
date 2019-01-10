package me.bscal.starter.ui;

import org.bukkit.inventory.ItemStack;

public class BanGUI extends GUI {

	public ItemStack[] itemstack = {
			
	};
	
	public BanGUI(int size) {
		super(27, GUIManager.KIT_PVP_TITLE, null);
		gui.setContents(itemstack);
	}

}
