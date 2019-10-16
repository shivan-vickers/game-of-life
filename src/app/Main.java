package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Shivan Vickers
 *
 */
public class Main extends Application {

	public Game game;
	public final int worldSize = 50;
	public GridPane grid;
	public Rectangle[][] cell;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// initialize the world
		game = new Game(worldSize);

		// create a visual representation of the cells using shapes from javafx
		cell = new Rectangle[worldSize][worldSize];
		for (int row = 0; row < worldSize; row++) {
			for (int col = 0; col < worldSize; col++) {
				cell[row][col] = new Rectangle(10, 10);
			}
		}

		// assigns colours to the rectangles based on their contents
		draw();

		// create top menu bar
		Menu menu = new Menu("File");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
        menu.getItems().addAll(open, save);
        MenuBar menuBar = new MenuBar(menu);
		
		FileChooser fileChooser = new FileChooser();
		
		// method to open a save file
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File file = fileChooser.showOpenDialog(primaryStage);
				try {
					FileInputStream fileIn = new FileInputStream(file);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					game.setWorld((World) in.readObject());
					in.close();
					fileIn.close();
					draw();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		
		// method to create a save file
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					FileOutputStream fileOut = new FileOutputStream("/GOL_SAVE.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(game.getWorld());
					out.close();
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
        
		
		// arrange the shapes in a grid
		grid = new GridPane();
		for (int row = 0; row < worldSize; row++) {
			for (int col = 0; col < worldSize; col++) {
				grid.add(cell[row][col], row, col);
			}
		}
		grid.setGridLinesVisible(true);

		// to advance one turn every time the mouse is clicked
		grid.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				game.nextTurn();
				draw();
			}

		});

		// display the application
		VBox root = new VBox(menuBar, grid);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Game of Life");
		primaryStage.show();
	}

	/**
	 * Call to update the world by assigning colours to the cells based on their
	 * contents.
	 */
	public void draw() {
		for (int row = 0; row < worldSize; row++) {
			for (int col = 0; col < worldSize; col++) {
				if (game.getWorld().getCellAt(row, col).getLifeform() instanceof Herbivore) {
					cell[row][col].setFill(Color.YELLOW);
				} else if (game.getWorld().getCellAt(row, col).getLifeform() instanceof Plant) {
					cell[row][col].setFill(Color.GREEN);
				} else if (game.getWorld().getCellAt(row, col).getLifeform() instanceof Carnivore) {
					cell[row][col].setFill(Color.RED);
				} else if (game.getWorld().getCellAt(row, col).getLifeform() instanceof Omnivore) {
					cell[row][col].setFill(Color.BLUE);
				} else {
					cell[row][col].setFill(Color.WHITE);
				}
			}
		}
	}
}
