package GUI;

import Game.Game;
import processing.core.*;

public class Main extends PApplet {
	final int FIELD_SIZE = 12;
	final int MAX_SHIP_SIZE = 4;
	final int TILE_SIZE = 54;

	Game game = new Game(FIELD_SIZE, MAX_SHIP_SIZE);

	public static void main(String[] args) {
		PApplet.main("GUI.Main");
	}

	public void settings() {
		// setze die größe des fensters
		size(game.getSIZE() * TILE_SIZE, game.getSIZE() * TILE_SIZE + 4 * TILE_SIZE);
	}

	public void setup() {
		surface.setTitle("Battleship");
	}

	public void stop() {
	}

	public void draw() {
		background(245, 222, 179);
		// gebe das gamefeld aus
		int[][] tmp = game.getHit();
		for (int x = 0; x < tmp.length; x++) {
			for (int y = 0; y < tmp[x].length; y++) {
				if (tmp[x][y] < 0) {
					fill(0, 0, 139);
				} else if (tmp[x][y] > 0) {
					fill(255, 165, 0);
				} else {

					fill(161, 161, 161);
				}
				rect(TILE_SIZE * y, TILE_SIZE * x, TILE_SIZE, TILE_SIZE, 7);
			}
		}
		if (!game.finish()) {
			// eingabe der züge
			if (mousePressed) {
				int[] eingabe = Tools.eingaben(mouseX, mouseY, TILE_SIZE);
				game.draw(eingabe[0], eingabe[1]);
			}
		} else {
			// gebe gewonnen aus
			textAlign(CENTER, CENTER);
			textSize(TILE_SIZE / 2);
			fill(100);
			text("Gewonnen!", game.getSIZE() * TILE_SIZE / 2, game.getSIZE() * TILE_SIZE + 2 * TILE_SIZE);
		}
		// gebe Versuche, Treffer, anzahl der schiffe aus
		textAlign(CENTER, CENTER);
		textSize(TILE_SIZE / 2);
		fill(100);
		text("Versuche: " + game.getTries(), game.getSIZE() * TILE_SIZE / 6, game.getSIZE() * TILE_SIZE + TILE_SIZE);
		text("Treffer: " + game.getShipHits(), game.getSIZE() * TILE_SIZE / 6 * 3,
				game.getSIZE() * TILE_SIZE + TILE_SIZE);
		text("Schiffe: " + game.getSHIP_FIELDS(), game.getSIZE() * TILE_SIZE / 6 * 5,
				game.getSIZE() * TILE_SIZE + TILE_SIZE);

		// setze den "neues spiel" button
		fill(255, 140, 0);
		rect(2 * (TILE_SIZE), game.getSIZE() * TILE_SIZE + 2 * TILE_SIZE + (TILE_SIZE / 2),
				TILE_SIZE * (game.getSIZE() - 4), TILE_SIZE, 10);
		textAlign(CENTER, CENTER);
		textSize(TILE_SIZE / 2);
		fill(100);
		text("Neues Spiel", game.getSIZE() * TILE_SIZE / 2, game.getSIZE() * TILE_SIZE + 3 * TILE_SIZE);

		// eingabe button
		buttonPressed();

	}

	private void buttonPressed() {
		if (mousePressed) {
			if (mouseX >= 2 * (TILE_SIZE) && mouseX < TILE_SIZE * (game.getSIZE() - 2)
					&& mouseY >= game.getSIZE() * TILE_SIZE + (TILE_SIZE / 2) + 2 * TILE_SIZE
					&& mouseY < game.getSIZE() * TILE_SIZE + (TILE_SIZE / 2) + 3 * TILE_SIZE) {
				// starte neues game
				game = new Game(FIELD_SIZE, MAX_SHIP_SIZE);
			}
		}
	}

}
