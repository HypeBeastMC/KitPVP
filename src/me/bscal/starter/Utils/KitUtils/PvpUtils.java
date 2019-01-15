package me.bscal.starter.Utils.KitUtils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PvpUtils
{
  public static ItemStack Boots()
  {
    ItemStack itemStack = new ItemStack(Material.IRON_BOOTS);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("PvP's Boots");
    
    itemStack.setItemMeta(itemMeta);
    
    return itemStack;
  }
  
  public static ItemStack Legs()
  {
    ItemStack itemStack = new ItemStack(Material.IRON_LEGGINGS);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("PvP's Leggings");
    itemStack.setItemMeta(itemMeta);
    
    return itemStack;
  }
  
  public static ItemStack Chest()
  {
    ItemStack itemStack = new ItemStack(Material.IRON_CHESTPLATE);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("PvP's ChestPlate");
    itemStack.setItemMeta(itemMeta);
    
    return itemStack;
  }
  
  public static ItemStack Helmet()
  {
    ItemStack itemStack = new ItemStack(Material.IRON_HELMET);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("PvP's Helmet");
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }
  
  public static ItemStack Sword()
  {
    ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("PvP's Sword");
    itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
    
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }
  
  public static ItemStack Soup()
  {
    ItemStack itemStack = new ItemStack(Material.MUSHROOM_SOUP);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("Soup");
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }
}
