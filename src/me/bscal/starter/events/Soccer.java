package me.bscal.starter.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.material.Wool;
import org.bukkit.util.Vector;

import de.tr7zw.entlib.CustomEntity;
import de.tr7zw.entlib.EntityLib;
import de.tr7zw.entlib.nms.inter.PathfinderGoal;
import me.bscal.starter.Main;
import me.bscal.starter.Utils.Utils;

public class Soccer implements Listener {
	
	private Team blueTeam, redTeam;
	private Goal blueGoal, redGoal;
	private Location ballSpawn;
	private TestMob ball;
	
	public Soccer(Location loc) {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPluginIntance());
		ballSpawn = loc;
		blueTeam = new Team();
		redTeam = new Team();
		SoccerManager.soccerInstances.add(this);
		startMatch();
	}
	
	public void startMatch() {
		ball = new TestMob(ballSpawn.getWorld());
		EntityLib.spawn(ball, ballSpawn);
		List<Entity> entities = ballSpawn.getWorld().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == ball.bukkit()) {
				Slime s = (Slime) entities.get(i);
				s.setSize(1);
			}
		}
	}
	
	class NoAI extends PathfinderGoal {
		@Override
		public boolean shouldExecute() {
			return true;
		}
		
	    @Override
	    public void updateTask() {
	    	return;
	    }
	}
	
	class TestMob extends CustomEntity {
		public TestMob(World world) {
			super(world, EntityType.SLIME);
			init();
		}
		
		public void init() {
			getHandler().addGoalSelector(1, new NoAI());
		}
	}
	
	public void update() {
		checkGoal(ball.bukkit().getLocation());
	}
	
	public void onGoal() {
		blueGoal.resetPlayerPosition();
		redGoal.resetPlayerPosition();
	}
	
	private void checkGoal(Location ballLocation) {
		for (int y = ballLocation.getBlockY(); y > 0; y--) {
			Location newLocation = ballLocation;
			newLocation.setY(y);
			if (newLocation.getBlock().getType() == Material.WOOL) {
				Wool wool = (Wool) newLocation.getBlock().getState().getData();
				if (wool.getColor() == DyeColor.RED) {
					score(redTeam, "Red");
				}
				else if (wool.getColor() == DyeColor.BLUE) {
					score(blueTeam, "Blue");
				}
			}

		}
		
		//blueGoal.isInGoal(ballLocation);
		//redGoal.isInGoal(ballLocation);
	}
	
	@EventHandler
	public void onPowerHit(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().equals(ball.bukkit())) {
			applyForce(e.getPlayer().getLocation(), 2f, 2);
		}
	}
	
//	@EventHandler
//	public void onBallHit(PlayerArmorStandManipulateEvent e) {
//		if (e.getRightClicked().equals(ball)) {
////			Vector newDir = new Vector(e.getPlayer().getLocation().getDirection().getX(),
////					e.getPlayer().getLocation().getDirection().getY(),
////					e.getPlayer().getLocation().getDirection().getZ());
//			applyForce(e.getPlayer().getLocation(), 2.0f, 1);
//			
//			e.setCancelled(true);
//		}
//	}
	
	@EventHandler
	public void onDribble(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		//if (isSoccerPlayer(player)) {
			for (Entity ent : player.getNearbyEntities(1, 2, 1)) {
				if (ent.equals(ball.bukkit())) {
					applyForce(player.getLocation(), 1.75f, 1);
				}
			}
		//}
	}
	
	private void applyForce(Location loc, double multiplier, int upForce) {
		ball.bukkit().setVelocity(loc.getDirection().multiply(multiplier).add(new Vector(0,upForce, 0)));
	}
	
	private void score(Team team, String teamColor) {
		team.score++;
		Bukkit.getServer().broadcastMessage(teamColor + "team has scored");
	}
	
	private boolean isSoccerPlayer(Player player) {
		if (blueTeam.players.contains(player))
			return true;
		else if (redTeam.players.contains(player))
			return true;
		return false;
	}
	
	public void printScore() {
		Bukkit.getServer().broadcastMessage(Utils.chat("&9Blue Team&7: " + blueTeam.score
				+ " | " + "&cRed Team&7: " + redTeam.score));
	}
}

class Team {
	List<Player> players;
	int score;
	
	Team() {
		score = 0;
	}
}

class Rectangle {
	public int x, y, z, w, h;
}

class Goal {
	Team team;
	Rectangle goal;
	
	Goal(Team team) {
		this.team = team;
	}
	
	public void setGoal(Location loc1, int w, int h) {
		goal.x = loc1.getBlockX();
		goal.z = loc1.getBlockZ();
		goal.w = w;
		goal.h = h;
	}
	
	public boolean isInGoal(Location loc) {
		if (loc.getBlockX() >= goal.x && loc.getBlockZ() >= goal.z) {
			if (loc.getBlockX() <= goal.w && loc.getBlockZ() <= goal.h) {
				team.score++;
				return true;
			}
		}
		return false;
	}
	
	void resetPlayerPosition() {
		for (Player p : team.players) {
			p.teleport(new Location(p.getWorld(), goal.x - goal.w / 2, goal.y, goal.z));
		}
	}
	
//	private void freezeEntity(Entity en){
//    net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
//    NBTTagCompound compound = new NBTTagCompound();
//    nmsEn.c(compound);
//    compound.setByte("NoAI", (byte) 1);
//    nmsEn.f(compound);
//}
}
