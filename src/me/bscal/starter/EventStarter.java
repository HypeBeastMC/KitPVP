package me.bscal.starter;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.bscal.starter.events.Soccer;
import me.bscal.starter.events.SoccerManager;

public class EventStarter implements CommandExecutor {

	private final String START_COMMAND_NAME = "events";
	private final String JOIN_COMMAND_NAME = "join";
	private final int QUEUE_TIME = 30 * 20;
	
	private boolean isOpen = false;
	private ArrayList<Player> queue = new ArrayList<Player>();
	private Map<String, ItemStack[]> playerInventories;
	
	private String eventName;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdString, String[] args) {
		if (cmd.getName().equalsIgnoreCase("start")) {
			SoccerManager.soccerInstances.add(new Soccer(((Player) sender).getLocation()));
		}
		
		if (cmd.getName().equalsIgnoreCase("soccer")) {
			if (sender.hasPermission("soccer.admin")) {
				if (args.length > 1) {
					if (args[0].equalsIgnoreCase("score")) {
						SoccerManager.soccerInstances.get(0).printScore();
					}
					return true;
				}
				else {
					sender.sendMessage(ChatColor.RED + "Not proper commands");
				}
			}
			return false;
		}
		
		/////////////////
		if (cmd.getName().equalsIgnoreCase(START_COMMAND_NAME)) {
			// Set this to whatever permission you want
			if (sender.hasPermission("kitpvp.admin")) {
				eventName = args[1];
				startQueue();
			}
		}
		
		else if (cmd.getName().equalsIgnoreCase(JOIN_COMMAND_NAME)) {
			if (isOpen) {
				queue.add((Player) sender);
				sender.sendMessage("You were added to the queue!");
			}
			else {
				return true;
			}
		}
		return false;
	}

	public void startQueue() {
		Main.getPluginIntance().getServer().broadcastMessage("Event " + eventName + " has started");
		isOpen = true;
		
		Main.getScheduler().scheduleSyncDelayedTask(Main.getPluginIntance(), new Runnable() {
			@Override
			public void run() {
				// This will start the event
				stopQueue();
			}
		}, QUEUE_TIME);
	}
	
	public void stopQueue() {
		isOpen = false;
		startEvent();
	}
	
	public void startEvent() {
		// Saves player inventories
		for (int i = 0; i < queue.size(); i++) {
			Player currentPlayer = queue.get(i);
			playerInventories.put(currentPlayer.getDisplayName(), currentPlayer.getInventory().getContents());
			
			currentPlayer.sendMessage("Event starting...");
			// Do other player stuff
		}
		queue.clear();
		// TODO DELETE THIS only for testing.
		endEvent();
	}
	
	public void endEvent() {
		for (int i = 0; i < queue.size(); i++) {
			Player currentPlayer = queue.get(i);
			currentPlayer.closeInventory();
			currentPlayer.getInventory().clear();
			currentPlayer.getInventory().setContents(playerInventories.get(currentPlayer.getDisplayName()));
			playerInventories.remove(currentPlayer.getName());
			
			currentPlayer.sendMessage("Event ending...");
			// Do other player cleanup
		}
	}

}
