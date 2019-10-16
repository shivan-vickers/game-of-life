package app;

import java.util.ArrayList;

/**
 * @author Shivan Vickers
 *
 */
public abstract class Animal extends Lifeform {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Maximum and starting health points for an animal.
	 */
	protected final int maxHP = 5;

	/**
	 * Current health points.
	 */
	protected int HP;

	/**
	 * Creates the Animal object.
	 * 
	 * @param currentCell the cell this animal is in
	 */
	public Animal(World.Cell currentCell) {
		super(currentCell);
		HP = maxHP;
	}

	@Override
	protected void takeTurn() {
			// look for food
			ArrayList<World.Cell> food = new ArrayList<World.Cell>();
			for (World.Cell cell : currentCell.getNeighbours()) {
				if (edible(cell.getLifeform())) {
					food.add(cell);
				}
			}

			// if food is found, eat it, else move into a random empty space
			if (food.size() > 0) {
				World.Cell randomTarget = food.get(RandomGenerator.nextNumber(food.size()));
				eat(randomTarget);
			} else {
				move();
			}

			// try to give birth
			if (canGiveBirth()) {
				giveBirth();
			}
			
			if (HP == 0) {
				die();
			}
	}

	/**
	 * Moves the lifeform to a random empty cell.
	 */
	protected void move() {
		currentCell.setLifeform(null);
		currentCell = getRandomEmptyCell();
		currentCell.setLifeform(this);
		
		HP--;
	}

	/**
	 * @param target lifeform to eat
	 */
	protected void eat(World.Cell target) {
		target.removeLifeform();

		currentCell.setLifeform(null);
		currentCell = target;
		currentCell.setLifeform(this);

		HP = maxHP;
	}

	/**
	 * @param target lifeform
	 * @return true if this animal can eat the target
	 */
	protected abstract boolean edible(Lifeform target);
}
