package me.bscal.starter.cmds.Kits;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.bscal.starter.Main;
import me.bscal.starter.Utils.KitUtils.Lists;
import me.bscal.starter.Utils.KitUtils.PvpUtils;
import me.bscal.starter.config.DataManager;

public class Pvp  implements CommandExecutor{
	
private DataManager pvpConfig = new DataManager(Main.getPluginIntance(), "pvp.yml");
	World pvpWorld = Bukkit.getWorld(pvpConfig.getString("arenas.1.world"));


public final Main plugin;

public Pvp(Main instance)

{
  this.plugin = instance;
  
}

	
		
	


public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args)
{
	  
  Player p = (Player)sender;
  if (cmd.getName().equalsIgnoreCase("pvp")) {
    if (!p.hasPermission("hype.pvp"))
    {
      p.sendMessage(ChatColor.RED + "You do not have this kit do /kshop to purchase a kit!");
    }
    else if (Lists.hasKit.contains(p.getName()))
    {
      p.sendMessage(ChatColor.RED + "" +  ChatColor.ITALIC + "You may only have 1 kit per life!");
    }
    else
    {
  	   p.sendMessage(ChatColor.GOLD + "Teleporting to KitMap!");
  	  Lists.hasKit.add(p.getName());
  	      plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable()
  	      {
  	        public void run()
  	        {


  		      
  		        p.getInventory().clear();
  		        p.sendMessage(ChatColor.RED + "You have received the PVP kit");
  		        p.getInventory().setHelmet(PvpUtils.Helmet());
  		        p.getInventory().setChestplate(PvpUtils.Chest());
  		        p.getInventory().setLeggings(PvpUtils.Legs());
  		        p.getInventory().setBoots(PvpUtils.Boots());
  		        
  		        p.getInventory().addItem(new ItemStack[] { PvpUtils.Sword() });
  		        for (int i = 0; i < 35; i++) {
  		          p.getInventory().addItem(new ItemStack[] { PvpUtils.Soup() });
  	        
  	          Random ran = new Random();
  	          int r = ran.nextInt(8);
  	          if (r == 1) {

  	            p.teleport(pvpConfig.getVector("pvp.1.1.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 2) {
  	            p.teleport(pvpConfig.getVector("pvp.1.2.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 3) {
  	            p.teleport(pvpConfig.getVector("pvp.1.3.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 5) {
  	            p.teleport(pvpConfig.getVector("pvp.1.4.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 6) {
  	            p.teleport(pvpConfig.getVector("pvp.1.5.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 7) {
  	            p.teleport(pvpConfig.getVector("pvp.1.6.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 8) {
  	            p.teleport(pvpConfig.getVector("pvp.1.7.spawn").toLocation(pvpWorld));
  	          }
  	          if (r == 9) {
  	            p.teleport(pvpConfig.getVector("pvp.1.8.spawn").toLocation(pvpWorld));
  	          }
  	        }
  	        }
  	      }, 5L);

      }
    }
  

  return false;
}
}






