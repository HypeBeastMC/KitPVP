package me.bscal.starter;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.bscal.starter.config.DataManager;
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
	private DataManager arenaConfig = new DataManager(Main.getPluginIntance(), "arenas.yml");

	private void saveLocation(Player p, String path) {
		Vector v = new Vector(p.getLocation().getBlockX(), p.getLocation().getBlockY(),p.getLocation().getBlockZ());
		arenaConfig.set(path, v);
		arenaConfig.set("arenas.1.world", p.getWorld().getName());
		arenaConfig.save();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdString, String[] args) {
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("start")) {
			if (SoccerManager.soccerInstances.size() > 1) {
				SoccerManager.soccerInstances.set(0, new Soccer(((Player) sender).getLocation()));
			}
			else {
				SoccerManager.soccerInstances.add(new Soccer(((Player) sender).getLocation()));
			}
		}

		if (cmd.getName().equalsIgnoreCase("soccer")) {
			if (sender.hasPermission("soccer.admin")) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("score")) {
						if (SoccerManager.soccerInstances.get(0) == null) {
							player.sendMessage(ChatColor.RED + "No games being played!");
							return false;
						}
						SoccerManager.soccerInstances.get(0).printScore(player);
					} 
					else if (args[0].equalsIgnoreCase("reload")) {
						player.sendMessage("Config Reloaded");
						arenaConfig.reload();
					} 
					else if (args[0].equalsIgnoreCase("restart")) {
						SoccerManager.soccerInstances.get(0).reset(false);
					} 
					else if (args[0].equalsIgnoreCase("start")) {
						SoccerManager.soccerInstances.get(0).startMatch();
					} 
					else if (args[0].equalsIgnoreCase("set") && args[1] != null) {
						if (args[1].equalsIgnoreCase("blue")) {
							saveLocation(player, "arenas.1.blue.spawn");
							player.sendMessage("Successful set Blues Spawn Location!");
						} 
						else if (args[1].equalsIgnoreCase("red")) {
							saveLocation(player, "arenas.1.red.spawn");
							player.sendMessage("Successful set Reds Spawn Location!");
						} 
						else if (args[1].equalsIgnoreCase("ball")) {
							saveLocation(player, "arenas.1.ball.spawn");
							player.sendMessage("Successful set Balls Spawn Location!");
						}
					} 
					else if (args[0].equalsIgnoreCase("join") && args[1] != null) {
						SoccerManager.soccerInstances.get(0).join(player, args[1]);
					}
					return true;
				} 
				else {
					sender.sendMessage(ChatColor.RED + "Not enough args");
					return false;
				}
			}
			return true;
		}

		//
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
			} else {
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
