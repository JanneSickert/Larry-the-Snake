package functions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import lib.Formatierung;
import lib.fils.TxtFileImporter;

/**
 * Hier wird der Startbildschirm beschrieben.
 * 
 * @author Janne
 *
 */
public class Menu extends MyFrame{

	/*
	 * Die serial version UID ist dafür zuständig aus der Klasse erzeugte Objekte beim Kombilern
	 * der Richtigen Klasse zuzuordnen.
	 */
	private static final long serialVersionUID = 1953708387244294077L;
	private JLabel picSn = new JLabel();
	private JPanel jp = new JPanel();
	private Timer timer;
	private JButton withObstacles, empty;
	private JLabel best;
	private Dimension SIZE = new Dimension(300,75);
	int pic = 0;

	public void openWin() {
		dif();
		add(jp);
	}

	private class Paths {
		public static final String snakePath1 = "Pictures/Start/Sp1.jpg";
		public static final String snakePath2 = "Pictures/Start/Sp2.jpg";
		public static final String snakePath3 = "Pictures/Start/Sp3.jpg";
		public static final String hs = "save/hs.txt";
	}
	
	public Menu() {
		super();
	}

	public void dif() {
		writeHighScoor();
		withObstacles = new JButton(new Formatierung().gr_p2_fett("Mit Hindernissen"));
		empty = new JButton(new Formatierung().gr_p2_fett("ohne Hindernissen"));
		empty.setBackground(Color.GREEN);
		withObstacles.setBackground(Color.RED);
		empty.setLocation(0, 0);
		empty.setSize(SIZE);
		withObstacles.setLocation(300, 0);
		withObstacles.setSize(SIZE);
		empty.addActionListener(new Handler());
		withObstacles.addActionListener(new Handler());
		best.setLocation(42, 75);
		best.setSize(600, 75);
		jp.setLayout(null);
		jp.add(withObstacles);
		jp.add(empty);
		jp.add(best);
		jp.setBackground(Color.WHITE);
		picSn.setLocation(42, 150);
		picSn.setSize(400, 400);
		larry();
	}

	private void writeHighScoor() {
		TxtFileImporter f = new TxtFileImporter();
		try {
			f.setPath("save/hs.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Datei wurde nicht unter diesem Pfad gefunden: " + Paths.hs);
		}
		best = new JLabel(new Formatierung().gr_p2("Längste Schlange: " + f.getTEXT()[0]));
	}

	private class Handler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent nrOfBu) {
			if (nrOfBu.getSource() == empty) {
				// Version ohne hindernisse wird gestartet
				timer.stop();
				setVisible(false);
				LarryGo.ohneHindernisse();
			} else {
				// Version mit hindernissen wird gestartet.
				timer.stop();
				setVisible(false);
				LarryGo.mitHindernissen();
			}
		}
	}
	
	private void larry() {
		timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (pic) {
				case 0:
					picSn.setIcon(new ImageIcon(Paths.snakePath1));
					refresh();
					pic++;
					break;
				case 1:
					picSn.setIcon(new ImageIcon(Paths.snakePath2));
					refresh();
					pic++;
					break;
				case 2:
					picSn.setIcon(new ImageIcon(Paths.snakePath3));
					refresh();
					pic++;
					break;
				}
				if (pic>2) {
					pic = 0;
				}
				Toolkit.getDefaultToolkit().sync();
			}
		});
		timer.start();
	}

	private void refresh() {
		jp.add(picSn);
		add(jp);
		revalidate();
		repaint();
	}
}