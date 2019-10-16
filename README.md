# Game of Life
 A simple simulation made at BCIT to learn Java and the concepts of Object Oriented Design.

## Description
The world is a square grid and each lifeform takes up one cell of the grid. The animals graze, eat, and breed. Plants will create more plants as long as there are enough adjacent plants to help pollinate. Clicking the window advances the simulation by one turn. Supports save files to save the state of the simulation to reload later. Uses JavaFX for graphics.

## Testing

Easiest way to get this running is with VS Code and it's Java development toolkit. I've tested this with AdoptOpenJDK 11.

Since JavaFX was removed from JDK 11, you'll have to download the JavaFX 11 module separately and reference the libraries in the `.classpath` file.

1. Clone the repository
2. Edit the classpath to point to the JavaFX libraries
3. Start the debugger by pressing F5
4. Click anywhere in the window to advance the simulation by one turn

## Class Hierarchy

#### Main
Creates the window and starts the simulation. Also handles the graphics and save functionality.

#### Game
Initializes the world and controls turns.

#### World
Contains all the cells of grid in an array.

#### Cell
An inner class of World. Represents each cell of the grid. Has a lifeform reference which points to the lifeform object that's currently in it. 

#### Lifeform
An abstract class with some methods shared by all lifeforms. Has a cell reference which points to the cell the lifeform is currently in.

#### Plant
A child of the Lifeform class. Can pollinate to create more plants.

#### Animal
A child of the Lifeform class. Has methods for moving and eating that are shared by all animals. Has abstract methods for breeding to be defined in more specific classes.

#### Herbivore
A child of Animal. Can only eat plants and reproduces if there are at least 1 herbivore, at least 2 empty cells, and at least 2 cells with food adjacent to it.

#### Carnivore
A child of Animal. Can eat herbivores and omnivores and can reproduce if there are at least 1 carnivore, at least 3 empty cells, and at least 2 cells with food adjacent to it.

#### Omnivore
A child of Animal. Can eat plants, herbivores, and carnivores and can reproduce if there is at least 1 omnivore, at least 2 empty cells, and at least 2 cells with food adjacent to it.