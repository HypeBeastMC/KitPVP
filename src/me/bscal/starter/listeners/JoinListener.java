package me.bscal.starter.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.bscal.starter.Utils.KitUtils.Lists;
import me.bscal.starter.ui.ItemStacks.KitItemStacks;

public class JoinListener implements Listener {

	private static final KitItemStacks ITEM_STACKS = new KitItemStacks();
	private static final ItemStack[] CONTENTS = {
			// Row 1
			ITEM_STACKS.Selector(), };

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Lists.hasKit.contains(p.getName())) {
			e.setJoinMessage(null);

		} else {
			PlayerInventory pi = e.getPlayer().getInventory();
			e.setJoinMessage(null);
			pi.clear();
			p.sendMessage("testing null");
			pi.addItem(CONTENTS);
		}
	}
}