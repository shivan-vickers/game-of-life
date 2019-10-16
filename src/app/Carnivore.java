package app;

/**
 * @author Shivan Vickers
 *
 */
public class Carnivore extends Animal {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Carnivore(World.Cell currentCell) {
		super(currentCell);
	}

	@Override
	protected boolean edible(Lifeform target) {
		if (target instanceof Herbivore || target instanceof Omnivore) {
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
			} else if (cell.getLifeform() instanceof Carnivore) {
				mates++;
			}
		}

		if (mates >= 1 && emptyCells >= 3 && food >= 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void giveBirth() {
		Carnivore spawn = new Carnivore(getRandomEmptyCell());
		spawn.getCurrentCell().addNewLifeform(spawn);
	}

}
