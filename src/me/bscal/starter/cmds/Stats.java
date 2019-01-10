package me.bscal.starter.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.bscal.starter.Main;

public class Stats
  implements CommandExecutor
{
  Main pl;
  
  public Stats(Main commands)
  {
    this.pl = commands;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player p = (Player)sender;
      if (commandLabel.equalsIgnoreCase("stats")) {
        if (args.length == 0)
        {
          if (this.pl.getConfig().contains(p.getName()))
          {
            String name = p.getName();
            int kills = this.pl.getConfig().getInt(name + ".Kills");
            int deaths = this.pl.getConfig().getInt(name + ".Deaths");
            int money = this.pl.getConfig().getInt(name + ".Money");
            double kdr = this.pl.getConfig().getDouble(name + ".KDR");
            int timeplayed = this.pl.getConfig().getInt(name + ".Time Played");
            int staffkilled = this.pl.getConfig().getInt(name + ".StaffKilled");
            
            double k = timeplayed / 60;
            float g = (float)Math.ceil(k);
            StringBuilder online = new StringBuilder();
            if (Bukkit.getServer().getPlayer(p.getName()) != null) {
              online.append(ChatColor.GREEN + "" + ChatColor.BOLD + "Online");
            } else {
              online.append(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Offline");
            }
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "?????????????????????????????????????");
            
            p.sendMessage(ChatColor.GRAY + " " + ChatColor.RED + name + ChatColor.GRAY + " / " + online + ChatColor.GRAY + " ");
            p.sendMessage(ChatColor.RED + "Kills : " + ChatColor.GRAY + kills);
            p.sendMessage(ChatColor.RED + "Deaths : " + ChatColor.GRAY + deaths);
            p.sendMessage(ChatColor.RED + "KDR : " + ChatColor.GRAY + kdr);
            p.sendMessage(ChatColor.RED + "Staff Killed : " + ChatColor.GRAY + staffkilled);
            
            p.sendMessage(ChatColor.RED + "Credits : " + ChatColor.GRAY + money + " Credits");
            if (g >= 1.0F)
            {
              p.sendMessage(ChatColor.RED + "Time Played : " + ChatColor.GRAY + g + " minutes");
              p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "?????????????????????????????????????");
            }
            else
            {
              p.sendMessage(ChatColor.RED + "Time Played : " + ChatColor.GRAY + timeplayed + " seconds");
              p.sendMessage(ChatColor.RED + "" +  ChatColor.BOLD + "?????????????????????????????????????");
            }
          }
          else
          {
            p.sendMessage(ChatColor.RED + "No data has been recorded for you, yet.");
          }
        }
        else if (args.length == 1)
        {
          if (this.pl.getConfig().contains(args[0]))
          {
            String name = args[0];
            int kills = this.pl.getConfig().getInt(name + ".Kills");
            int deaths = this.pl.getConfig().getInt(name + ".Deaths");
            int money = this.pl.getConfig().getInt(name + ".Money");
            double kdr = this.pl.getConfig().getDouble(name + ".KDR");
            int staffkilled = this.pl.getConfig().getInt(name + ".StaffKilled");
            
            int timeplayed = this.pl.getConfig().getInt(name + ".Time Played");
            double k = timeplayed / 60;
            float g = (float)Math.ceil(k);
            StringBuilder online = new StringBuilder();
            if (Bukkit.getServer().getPlayer(args[0]) != null) {
              online.append(ChatColor.GREEN + "" + ChatColor.BOLD + "Online");
            } else {
              online.append(ChatColor.RED + "" + ChatColor.BOLD + "Offline");
            }
            p.sendMessage(ChatColor.RED + "" +  ChatColor.BOLD + "?????????????????????????????????????");
            p.sendMessage(ChatColor.GRAY + " " + ChatColor.RED + args[0] + ChatColor.GRAY + " / " + online + ChatColor.GRAY + " ");
            p.sendMessage(ChatColor.RED + "Kills : " + ChatColor.GRAY + kills);
            p.sendMessage(ChatColor.RED + "Deaths : " + ChatColor.GRAY + deaths);
            p.sendMessage(ChatColor.RED + "KDR : " + ChatColor.GRAY + kdr);
            p.sendMessage(ChatColor.RED + "Staff Killed : " + ChatColor.GRAY + staffkilled);
            
            p.sendMessage(ChatColor.RED + "Credits : " + ChatColor.GRAY + money + " Credits");
            if (g >= 1.0F)
            {
              p.sendMessage(ChatColor.RED + "Time Played : " + ChatColor.GRAY + g + " minutes");
              p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "?????????????????????????????????????");
            }
            else
            {
              p.sendMessage(ChatColor.RED + "Time Played : " + ChatColor.GRAY + timeplayed + " seconds");
              p.sendMessage(ChatColor.RED + "" +  ChatColor.BOLD + "?????????????????????????????????????");
            }
          }
          else
          {
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "?????????????????????????????????????");
            p.sendMessage(ChatColor.GRAY + " " + ChatColor.RED + args[0] + ChatColor.GRAY + " / " + ChatColor.RED + ChatColor.BOLD + "Offline");
            p.sendMessage(ChatColor.RED + "Kills : " + ChatColor.GRAY + "0");
            p.sendMessage(ChatColor.RED + "Deaths : " + ChatColor.GRAY + "0");
            p.sendMessage(ChatColor.RED + "KDR : " + ChatColor.GRAY + "0.0");
            p.sendMessage(ChatColor.RED + "Staff Killed : " + ChatColor.GRAY + "0");
            p.sendMessage(ChatColor.RED + "Credits : " + ChatColor.GRAY + "0 Credits");
            p.sendMessage(ChatColor.RED + "Time Played : " + ChatColor.GRAY + "0 seconds");
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "?????????????????????????????????????");
            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "(this person never joined the server before be sure you spelled their name right and use CaSe SeNsItIvE)");
          }
        }
        else {
          p.sendMessage(ChatColor.RED + "Incorrect usage! Try " + ChatColor.GREEN + "/stats <Player Name>");
        }
      }
    }
    return false;
  }
}
