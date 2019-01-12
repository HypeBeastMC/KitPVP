package me.bscal.starter.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.bscal.starter.Main;

public class Soccer implements Listener {
	
	private Team blueTeam, redTeam;
	private Goal blueGoal, redGoal;
	private Location ballSpawn;
	private Slime ball;
	
	public Soccer(Location loc) {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPluginIntance());
		ballSpawn = loc;
		SoccerManager.soccerInstances.add(this);
		startMatch();
	}
	
	public void startMatch() {
		ball = (Slime) ballSpawn.getWorld().spawnEntity(ballSpawn, EntityType.SLIME);
		ball.setSize(1);
		ball.setMaxHealth(20);
		ball.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, -3, false), false);
		//freezeEntity(ball);
		//ball.setCustomName("Ball");
		//ball.setCustomNameVisible(true);
		//ball.setBasePlate(false);
		//ball.setVisible(true);
		//ball.setGravity(true);
		//ball.setHelmet(new ItemStack(Material.SKULL_ITEM));
		//ball.setBodyPose(new EulerAngle(0, 0, 180));
		//ball.setLeftLegPose(new EulerAngle(0, 0, 180));
		//ball.setRightArmPose(new EulerAngle(0, 0, 180));

	}
	
	public void update() {
		//checkGoal(ball.getLocation());
		ball.setHealth(20);
		ball.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, -3, true), true);
		
	}
	
	public void onGoal() {
		blueGoal.resetPlayerPosition();
		redGoal.resetPlayerPosition();
	}
	
	private void checkGoal(Location loc) {
		blueGoal.isInGoal(loc);
		redGoal.isInGoal(loc);
	}
	
//	private void freezeEntity(Entity en){
//	    net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
//	    NBTTagCompound compound = new NBTTagCompound();
//	    nmsEn.c(compound);
//	    compound.setByte("NoAI", (byte) 1);
//	    nmsEn.f(compound);
//	}
	
	@EventHandler
	public void onPowerHit(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().equals(ball)) {
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
			for (Entity ent : player.getNearbyEntities(2, 3, 2)) {
				if (ent.equals(ball)) {
					applyForce(player.getLocation(), 1.75f, 1);
				}
			}
		//}
	}
	
	private void applyForce(Location loc, double multiplier, int upForce) {
		ball.setVelocity(loc.getDirection().multiply(multiplier).add(new Vector(0,upForce, 0)));
	}
	
	private boolean isSoccerPlayer(Player player) {
		if (blueTeam.players.contains(player))
			return true;
		else if (redTeam.players.contains(player))
			return true;
		return false;
	}
}

class Team {
	List<Player> players;
	int score;
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
}
