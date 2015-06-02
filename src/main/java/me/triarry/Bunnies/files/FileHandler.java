package me.triarry.Bunnies.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import me.triarry.Bunnies.Bunnies;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileHandler {
	private final Bunnies _bunnies;
	private final HashMap<Files, YamlConfiguration> _configurations;
	
	public FileHandler(Bunnies bunnies) {
		_bunnies = bunnies;
		_configurations = new HashMap<Files, YamlConfiguration>();
		loadWorlds();
		loadFiles();
		loadDefaultDrop("Bunny");
	}

	private List<String> loadWorlds() {
		List<String> worldList = new ArrayList<String>();
		for (World w : _bunnies.getServer().getWorlds()) {
			worldList.add(w.getName());
		}
		return worldList;
	}

	private String[] loadDefaultDrop(String arg) {
		String[] drops = null;
		switch(arg){
			case "Bunny":
				drops = new String[]{ "1-0-0;1;100/100" };
				break;
			default:
				break;
		}
		return drops;
	}
	
	public void loadFiles() {
		for (Files file : Files.values()) {
			File confFile = new File(file.getPath());

			if (confFile.exists()) {
				if (_configurations.containsKey(file)) {
					_configurations.remove(file);
				}
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(confFile);
				_configurations.put(file, conf);
			} else {
				File parentFile = confFile.getParentFile();

				if (!parentFile.exists()) {
					parentFile.mkdirs();
				}
				this.createFiles(file, confFile);
			}
		}
	}

	private void createFiles(Files files, File file) {
		switch (files) {
			case CONFIG:
				YamlConfiguration Config = YamlConfiguration.loadConfiguration(file);
				Config.set("Bunnies Configuration.Entities.Bunny", true);
				Config.set("Bunnies Configuration.Debug Mode.Enabled", false);
				Config.set("Bunnies Configuration.Debug Mode.Debug Message", "&2A {entity} has spawned at X:&F%X &2Y:&F%Y &2Z:&F%Z");
				try {
					Config.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Config);
				break;
			case BUNNY:
				YamlConfiguration Bunny = YamlConfiguration.loadConfiguration(file);
				Bunny.set("Bunny Configuration.Spawn Settings.Chance", new Integer(10));
				Bunny.set("Bunny Configuration.Spawn Settings.Worlds", loadWorlds());
				Bunny.set("Bunny Configuration.Bunny Stats.Health", new Integer(100));
				Bunny.set("Bunny Configuration.Bunny Stats.Experience", new Integer(5));
				Bunny.set("Bunny Configuration.Bunny Stats.Drops", Arrays.asList(loadDefaultDrop("Bunny")));
				Bunny.set("Bunny Configuration.Damage Settings.Arrows.Damage done by arrow", new Integer(10));
				Bunny.set("Bunny Configuration.Damage Settings.Fire.Allow Fire Damage", true);
				Bunny.set("Bunny Configuration.Damage Settings.Block Damage.Allow Suffocation", false);
				Bunny.set("Bunny Configuration.Damage Settings.Block Damage.Allow Cacti Damage", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Lightning Attack", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Stomp Attack.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Stomp Attack.Explosion Power", new Integer(1));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Stomp Attack.Light Fire", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Kick Attack.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Kick Attack.Kick Height", new Integer(1));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Fire Attack.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Fire Attack.Ticks for Target", new Integer(100));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Fire Attack.Ticks for Bunny", new Integer(100));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Throw Boulder Attack.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Throw Boulder Attack.Block Damage", new Integer(1));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Shrapnel Attack.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Shrapnel Attack.Baby Zombies", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Shrapnel Attack.Zombies to Spawn", new Integer(3));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Shrapnel Attack.Health", new Integer(20));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Spawn Zombies On Death.Enabled", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Spawn Zombies On Death.Baby Zombies", false);
				Bunny.set("Bunny Configuration.Attack Mechanisms.Spawn Zombies On Death.Zombies to Spawn", new Integer(5));
				Bunny.set("Bunny Configuration.Attack Mechanisms.Spawn Zombies On Death.Health", new Integer(20));
				Bunny.set("Bunny Configuration.Sounds.Stomp Attack", true);
				Bunny.set("Bunny Configuration.Sounds.Fire Attack", true);
				Bunny.set("Bunny Configuration.Sounds.Throw Boulder Attack", true);
				Bunny.set("Bunny Configuration.Sounds.Kick Attack", true);
				Bunny.set("Bunny Configuration.Sounds.Shrapnel Attack", true);
				Bunny.set("Bunny Configuration.Sounds.Death", true);
				try {
					Bunny.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, Bunny);
				break;
			case BUNNYBIOMES:
				YamlConfiguration BunnyBiomes = YamlConfiguration.loadConfiguration(file);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Swampland.Swampland", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Swampland.Swampland Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Forest.Forest", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Forest.Forest Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Taiga", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Taiga Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Taiga Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Cold Taiga", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Cold Taiga Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Cold Taiga Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Mega Taiga", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Mega Taiga Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Mega Spruce Taiga", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Taiga.Mega Spruce Taiga Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Plains.Plains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Plains.Ice Plains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Plains.Ice Plains Spikes", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Plains.Sunflower Plains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Ocean.Ocean", false);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Ocean.Deep Ocean", false);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Ocean.Frozen Ocean", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.River.River", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.River.Frozen River", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Beach.Beach", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Beach.Stone Beach", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Beach.Cold Beach", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Extreme Hills.Extreme Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Extreme Hills.Extreme Hills Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Extreme Hills.Extreme Hills Plus Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mushroom Island.Mushroom Island", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mushroom Island.Mushroom Shore", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Desert.Desert", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Desert.Desert Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Desert.Desert Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Jungle.Jungle", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Jungle.Jungle Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Jungle.Jungle Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Birch Forest.Birch Forest", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Birch Forest.Birch Forest Hills", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Birch Forest.Birch Forest Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Birch Forest.Birch Forest Hills Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Savanna.Savanna", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Savanna.Savanna Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Savanna.Savanna Plateau", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Savanna.Savanna Plateau Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Roofed Forest.Roofed Forest", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Roofed Forest.Roofed Forest Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa Bryce", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa Plateau", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa Plateau Forest", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa Plateau Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Mesa.Mesa Plateau Forest Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Other.Small Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Other.Ice Mountains", true);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Other.Hell", false);
				BunnyBiomes.set("Bunny Configuration.Biome Settings.Other.Sky", false);
				try {
					BunnyBiomes.save(file);
				} catch (IOException e) {
				}
				_configurations.put(files, BunnyBiomes);
				break;
		default:
			break;
		}
	}

	public String getProperty(Files file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			String prop = conf.getString(path, "NULL");

			if (!prop.equalsIgnoreCase("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}

	public List<String> getPropertyList(Files file, String path) {
		FileConfiguration conf = _configurations.get(file);

		if (conf != null) {
			List<String> prop = conf.getStringList(path);
			if (!prop.contains("NULL"))
				return prop;
			conf.set(path, null);
		}
		return null;
	}
}