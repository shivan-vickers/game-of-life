package app;

/**
 * @author Shivan Vickers
 *
 */
public class Plant extends Lifeform {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates the Plant object.
	 * 
	 * @param currentCell the cell this plant is in
	 */
	public Plant(World.Cell currentCell) {
		super(currentCell);
	}

	@Override
	protected void takeTurn() {
		if (canGiveBirth()) {
			giveBirth();
		}
	}

	@Override
	protected boolean canGiveBirth() {
		int emptyCells = 0;
		int plants = 0;

		for (World.Cell cell : currentCell.getNeighbours()) {
			if (cell.isEmpty()) {
				emptyCells++;
			} else if (cell.getLifeform() instanceof Plant) {
				plants++;
			}
		}

		if (plants >= 2 && emptyCells >= 3) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void giveBirth() {
		Plant spawn = new Plant(getRandomEmptyCell());
		spawn.getCurrentCell().addNewLifeform(spawn);
	}
}
