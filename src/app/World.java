package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Shivan Vickers
 *
 */
public class World implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** array holding the cells in the world. */
	private Cell[][] cell;

	/** a list of lifeforms currently on the map. */
	private ArrayList<Lifeform> currentLifeforms;

	/** a list of lifeforms that spawned during the current turn. */
	private ArrayList<Lifeform> newLifeforms;

	/** a list of lifeforms that died during the current turn. */
	private ArrayList<Lifeform> deadLifeforms;

	/**
	 * Creates the world and fills it with cells in a two dimensional array.
	 * 
	 * @param size the size of the world
	 */
	public World(int size) {
		cell = new Cell[size][size];
		currentLifeforms = new ArrayList<Lifeform>();
		deadLifeforms = new ArrayList<Lifeform>();
		newLifeforms = new ArrayList<Lifeform>();

		RandomGenerator.reset();

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				cell[row][col] = new Cell(row, col);
			}
		}
	}

	/**
	 * This method returns the size of one dimension of the world. The world is a
	 * two dimensional array of cells.
	 * 
	 * @return the size of the world
	 */
	public int getSize() {
		return cell.length;
	}

	/**
	 * Returns the cell at the given coordinates.
	 * 
	 * @param row to use
	 * @param col to use
	 * @return the cell at the given row and column
	 */
	public World.Cell getCellAt(int row, int col) {
		return cell[row][col];
	}

	/**
	 * @return the list of current lifeforms
	 */
	public ArrayList<Lifeform> getCurrentLifeforms() {
		return currentLifeforms;
	}

	/**
	 * @return the list of dead lifeforms
	 */
	public ArrayList<Lifeform> getDeadLifeforms() {
		return deadLifeforms;
	}

	/**
	 * Updates the lists of lifeforms. New lifeforms are added to current. Dead
	 * lifeforms are removed from current.
	 */
	public void update() {
		currentLifeforms.addAll(newLifeforms);
		newLifeforms.clear();
		currentLifeforms.removeAll(deadLifeforms);
		deadLifeforms.clear();
	}

	/**
	 * @author Shivan Vickers
	 *
	 */
	public class Cell implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int row;
		private int col;
		private Lifeform lifeform;

		/**
		 * Object representing each cell in the world.
		 * 
		 * @param row of the cell in array
		 * @param col of the cell in array
		 */
		public Cell(int row, int col) {
			this.row = row;
			this.col = col;

			int randomNumber = RandomGenerator.nextNumber(99);

			if (randomNumber >= 80) {
				lifeform = new Herbivore(this);
			} else if (randomNumber >= 60) {
				lifeform = new Plant(this);
			} else if (randomNumber >= 50) {
				lifeform = new Carnivore(this);
			} else if (randomNumber >= 45) {
				lifeform = new Omnivore(this);
			} else {
				lifeform = null;
			}

			if (lifeform != null) {
				currentLifeforms.add(lifeform);
			}
		}

		/**
		 * @return the lifeform currently in this cell
		 */
		public Lifeform getLifeform() {
			return lifeform;
		}

		/**
		 * @param lifeform the lifeform to add to this cell
		 */
		public void setLifeform(Lifeform lifeform) {
			this.lifeform = lifeform;
		}

		/**
		 * Removes the lifeform from this cell as a result of death.
		 */
		public void removeLifeform() {
			deadLifeforms.add(lifeform);
			lifeform.setCurrentCell(null);
			lifeform = null;
		}

		/**
		 * Adds a new lifeform as a result of breeding.
		 * 
		 * @param lifeform to add
		 */
		public void addNewLifeform(Lifeform lifeform) {
			this.lifeform = lifeform;
			newLifeforms.add(lifeform);
		}

		/**
		 * @return true if cell has no lifeforms
		 */
		public boolean isEmpty() {
			return (lifeform == null);
		}

		/**
		 * Used to find the cells that are neighbouring the cell that calls this method.
		 * 
		 * @return a list of neighbouring cells
		 */
		public ArrayList<World.Cell> getNeighbours() {
			ArrayList<World.Cell> neighbours = new ArrayList<World.Cell>();

			for (int row = this.row - 1; row <= this.row + 1; row++) {
				for (int col = this.col - 1; col <= this.col + 1; col++) {
					if (row >= 0 && row < getSize() && col >= 0 && col < getSize() && cell[row][col] != this) {
						neighbours.add(cell[row][col]);
					}
				}
			}

			return neighbours;
		}
	}
}
