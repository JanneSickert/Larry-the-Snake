package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import functions.LarryGo;
import functions.MyFrame;
import lib.Formatierung;

/**
 * In dieser Klasse kann man erst hindernisse setzen dan Snake spielen.
 * 
 * @author Janne
 *
 */
public class Obstacles {

	/**
	 * Hier können die Hindernisse gesetzt werden.
	 * 
	 * @author Janne
	 *
	 */
	public static class MakeObstacles extends JPanel implements ActionListener {

		private static final long serialVersionUID = -2860804722280906559L;
		private JButton enter = new JButton();
		private byte nr = 1;

		public MakeObstacles() {
			this.setLayout(null);
			createObstacles();
		}

		private void createObstacles() {
			enter.setText(new Formatierung().gr_p4_fett("Mauern bauen"));
			for (int x = 0; x < LarryGo.field.length; x++) {
				for (int y = 0; y < LarryGo.field.length; y++) {
					JButton b = new JButton();
					b.setBackground(Color.YELLOW);
					b.addActionListener(this);
					b.setSize(LarryGo.DOT_SIZE, LarryGo.DOT_SIZE);
					b.setLocation(x * LarryGo.DOT_SIZE, y * LarryGo.DOT_SIZE + 75);
					LarryGo.field[x][y] = b;
					this.add(LarryGo.field[x][y]);
				}
			}
			enter.addActionListener(this);
			enter.setSize(MyFrame.SIZE_Y, 75);
			enter.setLocation(0, 0);
			this.add(enter);
		}

		@Override
		public void actionPerformed(ActionEvent input) {
			for (int x = 0; x < MyFrame.SIZE_X / LarryGo.DOT_SIZE; x++) {
				for (int y = 0; y < MyFrame.SIZE_X / LarryGo.DOT_SIZE; y++) {
					if (input.getSource() == LarryGo.field[x][y]) {
						if (!(LarryGo.field[x][y].getBackground() == Color.RED)) {
							if (nr <= 24) {
								LarryGo.field[x][y].setBackground(Color.RED);
								nr++;
							} else {
								JOptionPane.showMessageDialog(null, "Du kannst Maximal" + " 24 Hindernisse erstellen.",
										"ERROR", JOptionPane.OK_CANCEL_OPTION);
							}
						}
					}
				}
			}
			if (input.getSource() == enter) {
				LarryGo.mitHindernissenStart();
			}
			revalidate();
			repaint();
		}
	}

	/**
	 * Hier befindet sich die erweiterung für das Spiel mit Hindernissen.
	 * größtenteils sind es Methoden die Kollisionen überprüfen. Und eine draw
	 * Methode um die Mauern zu zeichnen. Noch eine Methode die einen Apfel in
	 * keiner Mauer spornen lässt. Und eine Methode die die Schlange in keiner Mauer
	 * spornen lässt.
	 * 
	 * @author Janne
	 *
	 */
	public static class Start extends Snake {

		private static final long serialVersionUID = 1510112182026993640L;
		private final int FIELDS = MyFrame.SIZE_X / LarryGo.DOT_SIZE;

		public Start() {
			locateStartPosition();
			this.initBoard();
		}

		@Override
		protected void checkCollision() {
			// Kollision mit einer Mauer.
			for (int ax = 0; ax < FIELDS; ax++) {
				for (int ay = 0; ay < FIELDS; ay++) {
					if ((x[0] == ax * LarryGo.DOT_SIZE) && (y[0] == ay * LarryGo.DOT_SIZE)
							&& (LarryGo.field[ax][ay].getBackground() == Color.RED)) {
						inGame = false;
					}
				}
			}
			// Kollision mit sich selbst.
			for (int z = dots; z > 0; z--) {
				if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
					inGame = false;
				}
			}
			// Kollision mit der Wand.
			if (y[0] >= B_HEIGHT || y[0] < 0 || x[0] >= B_WIDTH || x[0] < 0) {
				inGame = false;
			}
			if (!inGame) {
				timer.stop();
			}
		}

		@Override
		protected void drawWalls(Graphics g) {
			g.setColor(Color.RED);
			for (int ax = 0; ax < FIELDS; ax++) {
				for (int ay = 0; ay < FIELDS; ay++) {
					if (LarryGo.field[ax][ay].getBackground() == Color.RED) {
						g.fillRect(ax * LarryGo.DOT_SIZE, ay * LarryGo.DOT_SIZE + 75, LarryGo.DOT_SIZE,
								LarryGo.DOT_SIZE);
					}
				}
			}
		}

		@Override
		protected void locateApple() {
			int r, rb;
			do {
				rb = (int) (Math.random() * RAND_POS);
				r =  (int) (Math.random() * RAND_POS);
				apple_x = (r * DOT_SIZE);
				apple_y = (rb * DOT_SIZE);
			} while (LarryGo.field[r][rb].getBackground() == Color.RED);
		}
		
		private void locateStartPosition() {
			for (int ix = 5; ix < FIELDS; ix++) {
				for (int iy = 1; iy < FIELDS; iy++) {
					if (!(LarryGo.field[ix][iy].getBackground() == Color.RED)) {
						this.setStartPoint(ix, iy);
						ix = FIELDS;
						iy = FIELDS;
					}
				}
			}
		}
	}
}
