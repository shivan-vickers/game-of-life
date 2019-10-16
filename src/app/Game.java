package app;

/**
 * @author Shivan Vickers
 *
 */
public class Game {
	
	/**
	 * the world
	 */
	private World world;

	/**
	 * Creates the game object and initializes the world.
	 * 
	 * @param size the size of the world to create
	 */
	public Game(int size) {
		world = new World(size);
	}

	/**
	 * Makes each lifeform currently in the world take a turn.
	 */
	public void nextTurn() {
		for (Lifeform lifeform : world.getCurrentLifeforms()) {
			if (!(world.getDeadLifeforms().contains(lifeform))) {
				lifeform.takeTurn();
			}
		}

		world.update();
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}
	
	public void save() {
		// TODO save method
	}
	
	public void open() {
		// TODO open method
	}

	public void setWorld(World readObject) {
		this.world = readObject;
	}
}
