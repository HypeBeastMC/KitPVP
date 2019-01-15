package me.bscal.starter.ui.ItemStacks;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.bscal.starter.Utils.Utils;

public class KitItemStacks {

	public ItemStack noKit() {
		ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Nothing here");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Just something to make it look fancy");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack pvpKit() {
		ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("The PvP Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Spawnw with full iron and a sharp 1 Diamond sword");
		itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack archerKit() {
		ItemStack itemStack = new ItemStack(Material.BOW);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("aArcher Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Spawn with a power 3 bow, speed II,");
		lore.add("jump III, and full leather");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack assassinKit() {
		ItemStack itemStack = new ItemStack(Material.EGG);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Assassin Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Shoot your egg and teleport and");
		lore.add("gain strength");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack kangarooKit() {
		ItemStack itemStack = new ItemStack(Material.FIREWORK);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Kangaroo Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Right click your rocket to launch!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack ninjaKit() {
		ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Ninja Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Throw ninja stars at people!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack flashKit() {
		ItemStack itemStack = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Flash Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Teleport 35 blocks away from someone!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack thorKit() {
		ItemStack itemStack = new ItemStack(Material.IRON_AXE);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Thor Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Right click your axe and produce lightening!!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack scoutKit() {
		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 34);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Scout Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Speed II, and strafe!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack rogueKit() {
		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 78);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Rogue Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Right click redstone to become invisible!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack firemanKit() {
		ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Fireman Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Take no fire damage and spawn with");
		lore.add("fire sword!");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack cobraKit() {
		ItemStack itemStack = new ItemStack(Material.POTION, 1, (short) 36);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Cobra Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Every 1/3 hits give the enemy poison");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public ItemStack dogeKit() {
		ItemStack itemStack = new ItemStack(Material.BONE);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName("Doge Kit");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Wow, many doge, so kit, must pick, wow");
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	public ItemStack Selector() {
		ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(Utils.chat("&c&lKit Selector"));
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Utils.chat("&6&lRight Click for a kit."));
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

}
