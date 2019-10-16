# game-of-life
 A simple simulation made at BCIT to learn Java and the concepts of Object Oriented Design.

## Description
The world is a square grid and each lifeform takes up one cell of the grid. The animals graze, eat, and breed. Plants will create more plants as long as there are enough adjacent plants to help pollinate. Clicking the window advances the game by one turn. Supports save files to save the state of the game to reload later. Uses JavaFX for graphics.

## Class Hierarchy

Main class starts the game and generates graphics.

Game class initializes the world and controls turns.

World class contains all the cells of grid in an array.

Cell class is an inner class of World. Represents each cell of the grid. Has a lifeform reference which points to the lifeform object that's currently in it. 

Lifeform class is an abstract class with some methods shared by all lifeforms. Has a cell reference which points to the cell the lifeform is currently in.

Plant class is a child of the Lifeform class. Can pollinate to create more plants.

Animal class is a child of the Lifeform class. Has methods for moving and eating that are shared by all animals. Has abstract methods for breeding to be defined in more specific classes.

Herbivore class is a child of Animal. Can only eat plants and reproduces if there are at least 1 herbivore, at least 2 empty cells, and at least 2 cells with food adjacent to it.

Carnivore class is a child of Animal. Can eat herbivores and omnivores and can reproduce if there are at least 1 carnivore, at least 3 empty cells, and at least 2 cells with food adjacent to it.

Omnivore is a child of Animal. Can eat plants, herbivores, and carnivores and can reproduce if there is at least 1 omnivore, at least 2 empty cells, and at least 2 cells with food adjacent to it.

## Testing

1. Compile
2. Run from command line as `java -jar gameoflife.jar`
3. Click anywhere in the window to advance the simulation by one turn.