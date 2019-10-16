package app;

/**
 * @author Shivan Vickers
 *
 */
public class Herbivore extends Animal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Herbivore(World.Cell currentCell) {
		super(currentCell);
	}

	@Override
	protected boolean edible(Lifeform target) {
		if (target instanceof Plant) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean canGiveBirth() {
		int emptyCells = 0;
		int food = 0;
		int mates = 0;

		for (World.Cell cell : currentCell.getNeighbours()) {
			if (cell.isEmpty()) {
				emptyCells++;
			} else if (edible(cell.getLifeform())) {
				food++;
			} else if (cell.getLifeform() instanceof Herbivore) {
				mates++;
			}
		}

		if (mates >= 1 && emptyCells >= 2 && food >= 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void giveBirth() {
		Herbivore spawn = new Herbivore(getRandomEmptyCell());
		spawn.getCurrentCell().addNewLifeform(spawn);
	}

}
