package panels;

import java.awt.Graphics;

/**
 * In dieser Klasse befindet sich die Version ohne Hindernisse.
 * 
 * @author Janne
 *
 */
public class Standart extends Snake {

	private static final long serialVersionUID = -20629296539662058L;

	@Override
	protected void checkCollision() {
		for (int z = dots; z > 0; z--) {
			if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
				inGame = false;
			}
		}
		if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0) {
			inGame = false;
		}
		if (!inGame) {
			timer.stop();
		}
	}

	public Standart() {
		this.setStartPoint(5, 2);
		this.initBoard();
	}

	@Override
	protected void drawWalls(Graphics g) {
		// TODO Auto-generated method stub
		// Methode wird hir nicht verwendet.
	}

	@Override
	protected void locateApple() {
		int r = (int) (Math.random() * RAND_POS);
		apple_x = ((r * DOT_SIZE));
		r = (int) (Math.random() * RAND_POS);
		apple_y = ((r * DOT_SIZE));
	}
}
