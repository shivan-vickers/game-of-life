package app;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Shivan Vickers
 *
 */
public abstract class Lifeform implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The cell this lifeform is currently in.
	 */
	protected World.Cell currentCell;

	/**
	 * Creates a new lifeform.
	 * 
	 * @param currentCell the cell the lifeform will spawn in
	 */
	protected Lifeform(World.Cell currentCell) {
		this.currentCell = currentCell;
	}

	/**
	 * Actions the lifeform object will take each turn.
	 */
	protected abstract void takeTurn();

	/**
	 * 
	 * @return true if the conditions are right for this lifeform to mate.
	 */
	protected abstract boolean canGiveBirth();

	/**
	 * Spawn a new lifeform at the target cell as a result of pollinating, giving
	 * birth, etc.
	 */
	protected abstract void giveBirth();

	/**
	 * @return a randomly selected empty neighbouring cell
	 */
	protected World.Cell getRandomEmptyCell() {
		ArrayList<World.Cell> emptyCells = new ArrayList<World.Cell>();

		for (World.Cell cell : currentCell.getNeighbours()) {
			if (cell.isEmpty()) {
				emptyCells.add(cell);
			}
		}
		if (emptyCells.size() > 0) {
			int randomTarget = RandomGenerator.nextNumber(emptyCells.size());
			return emptyCells.get(randomTarget);
		} else {
			return this.getCurrentCell();
		}
	}

	/**
	 * Called if this lifeform dies as a result of starvation.
	 */
	protected void die() {
		currentCell.removeLifeform();
	}

	/**
	 * @return the currentCell
	 */
	public World.Cell getCurrentCell() {
		return currentCell;
	}

	/**
	 * @param currentCell the currentCell to set
	 */
	public void setCurrentCell(World.Cell currentCell) {
		this.currentCell = currentCell;
	}

}
