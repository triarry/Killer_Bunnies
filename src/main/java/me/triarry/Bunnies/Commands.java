package me.triarry.Bunnies;

import me.triarry.Bunnies.files.Files;
import me.triarry.Bunnies.utils.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.plugin.PluginDescriptionFile;

public class Commands implements CommandExecutor {
	private Bunnies _bunnies;

	public Commands(Bunnies bunnies) {
		_bunnies = bunnies;
	}

	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("bunnies")) {
			if (sender instanceof Player){
				Player player = (Player) sender;
				if (args.length == 0){
					if (player.hasPermission("bunnies.reload") || player.hasPermission("bunnies.*") || player.hasPermission("bunnies.debug") || player.hasPermission("bunnies.spawn") || player.isOp()) {
						player.sendMessage(ChatColor.GREEN + "===== bunnies Commands ===== \n" +
							"/bunnies reload:  Reloads the config file.\n" + 
							"/bunnies spawn [entitytype] <x> <y> <z> : Spawns entity at the location given \n" +
							"/bunnies version:  Displays the version of the plugin running on the server");
					} else {
						player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					if ((player.hasPermission("bunnies.reload")) || (player.isOp()) || (player.hasPermission("bunnies.*"))) {
						if ((player.hasPermission("bunnies.reload")) || (player.isOp())) {
							API.getFileHandler().loadFiles();
							player.sendMessage(ChatColor.AQUA + "[bunnies] " + ChatColor.GREEN + "bunnies config file reloaded.");
							Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[bunnies]" + ChatColor.GREEN + player + "has reloaded the bunnies config");
							_bunnies.log.info("bunnies config file reloaded.");
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("spawn")){
					if((player.hasPermission("bunnies.spawn")) || (player.isOp()) || player.hasPermission("bunnies.*")){
						if (args.length >= 2){
							if(args[1].equalsIgnoreCase("bunny")){
								double health;
								String string = API.getFileHandler().getProperty(Files.BUNNY, "Bunny Configuration.Bunny Stats.Health");
								try {
									health = Double.parseDouble(string);
								} catch (Exception e) {
									health = 100;
								}
								
								if(args.length ==  2){
									Location loc = (Location) player.getEyeLocation();
									Location location = loc;
									Rabbit entity = (Rabbit) loc.getWorld().spawnEntity(location, EntityType.RABBIT);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									entity.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
									player.sendMessage(ChatColor.AQUA + "[bunnies] " + ChatColor.GREEN + "A Killer Bunny has been spawned!");
								}
								if(args.length == 5){
									Location location = player.getLocation();
									double locx = player.getLocation().getX();
									double locy = player.getLocation().getY();
									double locz = player.getLocation().getZ();
									
									try {
										locx = Integer.parseInt(args[2]);
										locy = Integer.parseInt(args[3]);
										locz = Integer.parseInt(args[4]);
										
									} catch (Exception e) {
									}
									location.setX(locx);
									location.setY(locy);
									location.setZ(locz);
									Location loc = location;
									
									Rabbit entity = (Rabbit) loc.getWorld().spawnEntity(location, EntityType.RABBIT);
									entity.setMaxHealth(health);
									entity.setHealth(health);
									entity.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
									player.sendMessage(ChatColor.AQUA + "[bunnies] " + ChatColor.GREEN + "A Killer Bunny has been spawned at x:" + locx + " y:" + locy + "z:" + locz);
								}
								return true;
							}
							else {
								player.sendMessage(ChatColor.RED + "Unknown Entity Type! I recognise bunny.");
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "Not enough arguments!");
						}
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("version")){
					if((player.hasPermission("bunnies.version")) || (player.isOp())){
						PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("bunnies").getDescription();
						player.sendMessage(ChatColor.AQUA + "[bunnies] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					}
					return true;
				}
				else {
					if (player.hasPermission("bunnies.reload") || player.hasPermission("bunnies.*") || player.hasPermission("bunnies.debug") || player.hasPermission("bunnies.spawn") || player.isOp()) {
						player.sendMessage(ChatColor.RED + "Unknown Command!");
					}
					return true;
				}
			}
			else {
				if (args.length == 0){
					Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "===== Bunnies Commands ===== \n" +
						"/bunnies reload:  Reloads the config file.\n" + 
						"/bunnies version:  Displays the version of the plugin running on the server");
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Bunnies] "+ ChatColor.GREEN + "Bunnies config files reloaded.");
					API.getFileHandler().loadFiles();
					_bunnies.log.info("Bunnies config file reloaded.");
					return true;
				}
				if (args[0].equalsIgnoreCase("version")){
					PluginDescriptionFile pdf = Bukkit.getPluginManager().getPlugin("bunnies").getDescription();
					Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[Bunnies] " + ChatColor.GREEN + pdf.getName() + " Version " + pdf.getVersion() + " is currently Enabled!");
					return true;
				}
				else{
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unknown Command!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "===== bunnies Commands ===== \n" +
						"/bunnies reload:  Reloads the config files.\n" + 
						"/bunnies version:  Displays the version of the plugin running on the server");
				}
			}
		}
		return true;
	}
}