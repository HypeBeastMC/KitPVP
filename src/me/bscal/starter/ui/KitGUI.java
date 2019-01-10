package me.bscal.starter.ui;

import org.bukkit.inventory.ItemStack;

import me.bscal.starter.ui.ItemStacks.KitItemStacks;

public class KitGUI extends GUI {

	private static final KitItemStacks ITEM_STACKS = new KitItemStacks();
	
	private static final ItemStack[] CONTENTS = {
		// Row 1
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		ITEM_STACKS.noKit(),
		// Row 2
		ITEM_STACKS.pvpKit(),
		ITEM_STACKS.archerKit(),
		ITEM_STACKS.kangarooKit(),
		ITEM_STACKS.assassinKit(),
		ITEM_STACKS.ninjaKit(),
		ITEM_STACKS.flashKit(),
		ITEM_STACKS.thorKit(),
		ITEM_STACKS.scoutKit(),
		// Row 3
		ITEM_STACKS.firemanKit(),
		ITEM_STACKS.rogueKit(),
		ITEM_STACKS.cobraKit(),
		ITEM_STACKS.dogeKit()
	};

	public KitGUI() {
		super(27, GUIManager.KIT_PVP_TITLE, CONTENTS);
	}

}
