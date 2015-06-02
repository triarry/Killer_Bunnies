package me.triarry.Bunnies.utils;

import java.util.List;

import me.triarry.Bunnies.Bunnies;
import me.triarry.Bunnies.Commands;
import me.triarry.Bunnies.events.BunnyListeners;
import me.triarry.Bunnies.files.FileHandler;
import me.triarry.Bunnies.files.Files;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;

public class API {
	private static Bunnies _bunnies;
	private Commands commands;
	private static FileHandler fileHandler;

	public API(Bunnies bunnies) {
		_bunnies = bunnies;
		new BunnyListeners(_bunnies);
		commands = new Commands(_bunnies);
		_bunnies.getCommand("bunnies").setExecutor(commands);
		fileHandler = new FileHandler(_bunnies);
	}

	public static boolean isBunny(Entity entity) {
		String config = API.getFileHandler().getProperty(Files.CONFIG, "Bunnies Configuration.Entities.Bunny");
		if (config.equalsIgnoreCase("false")){
			return false;
		}
		return (((Rabbit) entity).getRabbitType())== Rabbit.Type.THE_KILLER_BUNNY;
	}

	public static List<String> getBunnySpawnWorlds() {
		return getFileHandler().getPropertyList(Files.BUNNY, "Bunny Configuration.Spawn Settings.Worlds");
	}

	public static FileHandler getFileHandler() {
		return fileHandler;
	}
	
}