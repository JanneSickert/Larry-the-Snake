package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import functions.LarryGo;
import functions.MyFrame;
import functions.Watch;
import lib.fils.NC;
import lib.fils.TxtFileImporter;

/**
 * Hier ist die Panel Klasse von Snake in ihr ist das abstracte Spiel,
 * was an Obstacles und Standart weitervererbt wird.
 * 
 * @author Janne
 *
 */
public abstract class Snake extends JPanel implements ActionListener {

	private static final long serialVersionUID = 7032890431187500659L;
	protected final int DOT_SIZE = LarryGo.DOT_SIZE;
	// Größe eines Schlangenteiles oder eines Geldbündels.
	protected final int B_WIDTH = MyFrame.SIZE_X;
	// Breite des Feldes in Pixeln
	protected final int B_HEIGHT = MyFrame.SIZE_Y - 75 - DOT_SIZE;
	// Höhe des Feldes
	private final int ALL_DOTS = 400;
	// Die Maximale Anzahl der Felder im Spiel.
	protected final int RAND_POS = 19;
	// Wird benötigt um eine zufällige posision für das Geld zu ermitteln.
	private final int DELAY = 140;
	// Länge bis zur Bewegung in Milisekunden.

	protected int x[] = new int[ALL_DOTS];
	protected int y[] = new int[ALL_DOTS];
	// Hier wird die Positsion der Schlange gespeichert.

	protected int dots;
	// Anzahl der Schlangenteile.
	protected int apple_x;
	protected int apple_y;
	// Positsion des Geldes.
	private int startPointX;
	private int startPointY;
	// Position von der aus die Schlange startet.

	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	protected boolean inGame = true;

	protected Timer timer;

	private Image ball;
	private Image apple;
	private Image head;
	private Image trump;

	public void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.black);
		setFocusable(true);
		// Vereint den X und Y wert in einen Objekt
		Dimension dim = new Dimension(B_WIDTH, B_HEIGHT);
		setPreferredSize(dim);
		loadImages();
		initGame();
	}

	private class Paths {
		public static final String teil = "Pictures/Game/dot.png";
		public static final String head = "Pictures/Game/head.png";
		public static final String food = "Pictures/Game/apple.png";
		public static final String trup = "Pictures/GameOver/trump.jpg";
		public static final String hs = "save/hs.txt";
	}

	// Hier werden die Bilder für das Spiel geladen.
	private void loadImages() {
		ImageIcon iid = new ImageIcon(Paths.teil);
		ball = iid.getImage();
		ImageIcon iia = new ImageIcon(Paths.food);
		apple = iia.getImage();
		ImageIcon iih = new ImageIcon(Paths.head);
		head = iih.getImage();
		ImageIcon iit = new ImageIcon(Paths.trup);
		trump = iit.getImage();
	}

	/*
	 * Hier wird die Schlange erstellt, ein zufälliges Geldstück generiert und der
	 * Timer gestartet.
	 */
	private void initGame() {
		dots = 3;
		for (int z = 0; z < dots; z++) {
			x[z] = (DOT_SIZE * startPointX) - (z * DOT_SIZE);
			y[z] = startPointY * DOT_SIZE;
		}
		locateApple();
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	protected void setStartPoint(int x, int y) {
		startPointX = x;
		startPointY = y;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		drawTime(g);
		drawLenght(g);
	}
	
	protected abstract void drawWalls(Graphics g);
	
	private void drawLenght(Graphics g) {
		String str = "Acktuelle länge: " + dots;
		Font small = new Font("Helvetica", Font.BOLD, 30);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(str, 300, 50);
	} 
	
	private void drawTime(Graphics g) {
		String str = "Spielzeit: " + Watch.currentTime;
        Font small = new Font("Helvetica", Font.BOLD, 30);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(str, 30, 50);
        g.setColor(Color.RED);
        g.drawLine(0, 75, 600, 75);
	}

	// Hier wird die Oberfläche gespeichert
	private void doDrawing(Graphics g) {
		if (inGame) {
			drawWalls(g);
			g.drawImage(apple, apple_x, apple_y + 75, this);
			for (int z = 0; z < dots; z++) {
				if (z == 0) {
					g.drawImage(head, x[z], y[z] + 75, this);
				} else {
					g.drawImage(ball, x[z], y[z] + 75, this);
				}
			}
			// Diese Methode sollte bei Animatsionen verwendet werden damit das Ganze
			// Fenster syncron aktualisiert wird. Sie kan aber auch weckgelassen werden.
			Toolkit.getDefaultToolkit().sync();
		} else {
			gameOver(g);
		}
	}

	// Bildschirm wenn das Spiel verloren ist.
	private void gameOver(Graphics g) {
		g.drawImage(trump, 0, 75, this);
		NC writer = new NC();
		TxtFileImporter f = new TxtFileImporter();
		String str = "";
		try {
			f.setPath("save/hs.txt");
			int lastTry = Integer.parseInt(f.getTEXT()[0]);
			if (lastTry < dots) {
				str = "" + dots;
				writer.overwrite(Paths.hs, str);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Datei wurde nicht unter diesem Pfad gefunden: " + Paths.hs);
		}
	}

	/*
	 * Wenn der Kopf der Schlange an der selben positsion ist wie das Geld, wird sie
	 * um ein Teil länger und es wird ein neues Geldstück generiert.
	 */
	private void checkApple() {
		if ((x[0] == apple_x) && (y[0] == apple_y)) {
			dots++;
			locateApple();
		}
	}

	/*
	 * Hier wird die Schlange bewegt. Dabei wird zuerst der Kopf auf die neue
	 * Positsion gesetzt und dann wird der erste Teil auf die Positsion des
	 * Schlangenkopfes gezogen. So wird das ganze Array abgearbeitet bis die ganze
	 * Schlange bewegt wurde.
	 */
	private void move() {
		for (int z = dots; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}
		if (leftDirection) {
			x[0] -= DOT_SIZE;
		}
		if (rightDirection) {
			x[0] += DOT_SIZE;
		}
		if (upDirection) {
			y[0] -= DOT_SIZE;
		}
		if (downDirection) {
			y[0] += DOT_SIZE;
		}
	}

	/*
	 * Hier wird kontrolliert ob die Schlange mit sich selbst oder der Wand
	 * kolliediert ist. Weil Larry in der 2ten Version auch mit Mauern
	 * kolliedieren kann ist diese Methode abstrat.
	 */
	protected abstract void checkCollision();

	/*
	 * Hier wird die zufällige Positsion des Apfels erstellt.
	 */
	protected abstract void locateApple();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			checkApple();
			checkCollision();
			move();
		}
		repaint();
	}

	/**
	 * Hier werden die Tastertureingaben der Cursertasten abgefangen.
	 * 
	 * @author Janne
	 *
	 */
	private class TAdapter extends KeyAdapter {

		/*
		 * Hier wird abgesichert das die Schlange nur in eine Richtung gesteuert werden
		 * kann.
		 */
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}
			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}
			if ((key == KeyEvent.VK_UP) && (!downDirection)) {
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			if ((key == KeyEvent.VK_ENTER) && (!inGame)) {
				Watch.timer.stop();
				LarryGo.ins.setVisible(false);
				LarryGo.menu();
			}
		}
	}
}