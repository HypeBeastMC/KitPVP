package me.bscal.starter.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.bscal.starter.Main;
import me.bscal.starter.config.DataManager;
import me.bscal.starter.events.SoccerManager;

public class Hypebest implements CommandExecutor{

	private final String START_COMMAND_NAME = "pre";
	
	private DataManager pvpConfig = new DataManager(Main.getPluginIntance(), "pvp.yml");
	
	private void saveLocation(Player p, String path) {
		Vector v = new Vector(p.getLocation().getBlockX(), p.getLocation().getBlockY(),p.getLocation().getBlockZ());
		pvpConfig.set(path, v);
		pvpConfig.set("arenas.1.world", p.getWorld().getName());
		pvpConfig.save();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("hypebeast")) {
			if (sender.hasPermission("hypebeast.set")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("score")) {
							player.sendMessage(ChatColor.RED + "&8[&cHypeBeast&8] &7No games being played!");
							return false;
						}
					 
					else if (args[0].equalsIgnoreCase("set") && args[1] != null) {
						if (args[1].equalsIgnoreCase("1")) {
							saveLocation(player, "pvp.1.1.spawn");
							player.sendMessage("Successful set First Spawn Location!");
						} 
						else if (args[1].equalsIgnoreCase("2")) {
							saveLocation(player, "pvp.1.2.spawn");
							player.sendMessage("Successful set 2nd Spawn Location!");
						} 
						else if (args[1].equalsIgnoreCase("3")) {
							saveLocation(player, "pvp.1.3.spawn");
							player.sendMessage("Successful set 3rd Spawn Location!");
						}
						else if (args[1].equalsIgnoreCase("4")) {
							saveLocation(player, "pvp.1.4.spawn");
							player.sendMessage("Successful set 4th Spawn Location!");
						}	else if (args[1].equalsIgnoreCase("5")) {
							saveLocation(player, "pvp.1.5.spawn");
							player.sendMessage("Successful set 5th Spawn Location!");
						}	else if (args[1].equalsIgnoreCase("6")) {
							saveLocation(player, "pvp.1.6.spawn");
							player.sendMessage("Successful set 6th Spawn Location!");
						}	else if (args[1].equalsIgnoreCase("7")) {
							saveLocation(player, "pvp.1.7.spawn");
							player.sendMessage("Successful set 7th Spawn Location!");
						}	else if (args[1].equalsIgnoreCase("8")) {
							saveLocation(player, "pvp.1.8.spawn");
							player.sendMessage("Successful set 8th Spawn Location!");
						}	else if (args[1].equalsIgnoreCase("9")) {
							saveLocation(player, "pvp.1.9.spawn");
							player.sendMessage("Successful set 9th Spawn Location!");
						}
					} 
			
		
				} 
				else {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	

}
