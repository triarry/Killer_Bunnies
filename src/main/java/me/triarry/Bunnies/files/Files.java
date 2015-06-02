package me.triarry.Bunnies.files;

public enum Files {
	CONFIG("plugins/Bunnies/config.yml"),
	BUNNY("plugins/Bunnies/Bunny/bunny.yml"), BUNNYBIOMES("plugins/Bunnies/Bunny/biomes.yml");
	
	private final String _path;

	private Files(final String path) {
		_path = path;
	}

	public String getPath() {
		return _path;
	}
	
}