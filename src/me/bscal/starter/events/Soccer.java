package me.bscal.starter.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.tr7zw.entlib.CustomEntity;
import de.tr7zw.entlib.EntityLib;
import de.tr7zw.entlib.nms.inter.PathfinderGoal;
import me.bscal.starter.Main;
import me.bscal.starter.Utils.Utils;
import me.bscal.starter.config.DataManager;

public class Soccer implements Listener {
	
	public static final String BLUE_NAME = "Blue";
	public static final String RED_Name = "Red";
	private static final String TEXTURE_URL = "http://textures.minecraft.net/texture/955d611a878e821231749b2965708cad942650672db09e26847a88e2fac2946";
	private static final Vector OFFSET = new Vector(0.0, -1.5D, 0.0D);
	
	private Team blueTeam, redTeam;
	private Location ballSpawn;
	private Ball ball;
	private ArmorStand ballTexture;
	private DataManager arenaConfig = new DataManager(Main.getPluginIntance(), "arenas.yml");
	public EventStatus status;
	
	enum EventStatus {
		STARTUP ,READY, INGAME, SHUTDOWN
	}
	
	
	public Soccer(Location loc) {
		status = EventStatus.STARTUP;
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPluginIntance());
		World arenaWorld = Bukkit.getWorld(arenaConfig.getString("arenas.1.world"));
		ballSpawn = arenaConfig.getVector("arenas.1.ball.spawn").toLocation(arenaWorld);
		blueTeam = new Team(arenaConfig.getVector("arenas.1.blue.spawn").toLocation(arenaWorld));
		redTeam = new Team(arenaConfig.getVector("arenas.1.red.spawn").toLocation(arenaWorld));
		
		SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

		meta.setOwner("God");

		ItemStack stack = new ItemStack(Material.SKULL_ITEM,1 , (byte)3);

		stack.setItemMeta(meta);
		
		ballTexture = (ArmorStand) ballSpawn.getWorld().spawnEntity(ballSpawn, EntityType.ARMOR_STAND);
		ballTexture.setHelmet(stack);
		ballTexture.setGravity(false);
		ballTexture.setVisible(false);
	}
	
	public void startMatch() {
		status = EventStatus.INGAME;
		ball = new Ball(ballSpawn.getWorld());
		EntityLib.spawn(ball, ballSpawn);
		List<Entity> entities = ballSpawn.getWorld().getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) == ball.bukkit()) {
				Slime s = (Slime) entities.get(i);
				s.setMaxHealth(20.0D);
				s.setSize(1);
			}
		}
		this.reset(false);
		//SoccerManager.soccerInstances.add(this);
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
	
	class Ball extends CustomEntity {
		public Ball(World world) {
			super(world, EntityType.SLIME);
			init();
		}
		
		public void init() {
			getHandler().addGoalSelector(1, new NoAI());
		}
	}
	
	public void update() {
		if (status == EventStatus.INGAME) {
			checkGoal(ball.bukkit().getLocation());
			ball.bukkit().setHealth(20.0D);
			Location loc = ball.bukkit().getLocation();
			ballTexture.teleport(loc.add(OFFSET));
			ball.bukkit().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , 18000, 1));
		}
	}
	
	public void join(Player player, String teamName) {
		if (teamName.equalsIgnoreCase("Blue")) {
			blueTeam.players.add(player);
			player.sendMessage("You have joined the Blue team!");
		}
		else if (teamName.equalsIgnoreCase("Red")) {
			redTeam.players.add(player);
			player.sendMessage("You have joined the Red team!");
		}
		else {
			player.sendMessage("Not a valid team!");
		}
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
	}
	
	@EventHandler
	public void onPowerHit(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().equals(ball.bukkit())) {
			applyForce(e.getPlayer().getLocation(), 2f, 2);
		}
	}
	
	@EventHandler
	public void onBallHit(PlayerArmorStandManipulateEvent e) {
		if (e.getRightClicked().equals(ballTexture)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDribble(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		for (Entity ent : player.getNearbyEntities(1.0D, 2.25D, 1.0D)) {
			if (ent.equals(ball.bukkit())) {
				applyForce(player.getLocation(), 1.75f, 1);
			}
		}
	}
	
	private void applyForce(Location loc, double multiplier, int upForce) {
		ball.bukkit().setVelocity(loc.getDirection().multiply(multiplier).add(new Vector(0,upForce, 0)));
	}
	
	private void score(Team team, String teamColor) {
		team.score++;
		reset(true, ChatColor.GOLD + teamColor + " team has scored!");
	}
	
	public void printScore(Player sender) {
		sender.sendMessage(Utils.chat("&9Blue Team&7: " + blueTeam.score
				+ " | " + "&cRed Team&7: " + redTeam.score));
	}
	
	public void reset(boolean hasScored) {
		reset(hasScored, null);
	}
	
	public void reset(boolean hasScored, String msg) {
		ball.bukkit().teleport(ballSpawn);
		blueTeam.onGoal(hasScored, msg);
		redTeam.onGoal(hasScored, msg);
	}
}

class Team {
	List<Player> players;
	Location spawn;
	int score;
	
	public Team(Location spawn) {
		players = new ArrayList<Player>();
		this.spawn = spawn;
		this.score = 0;
	}
	
	public void onGoal(boolean hasScored, String msg) {
		for (Player p : players) {
			if (hasScored)
				p.sendMessage(msg);
			p.teleport(spawn);
		}
	}
}
	
//	private void freezeEntity(Entity en){
//    net.minecraft.server.v1_8_R3.Entity nmsEn = ((CraftEntity) en).getHandle();
//    NBTTagCompound compound = new NBTTagCompound();
//    nmsEn.c(compound);
//    compound.setByte("NoAI", (byte) 1);
//    nmsEn.f(compound);
//}
