package functions;

import javax.swing.JFrame;

/**
 * Hier wird ein JFrame für weitere Klassen vordifiniert.
 * @author Janne
 *
 */
public class MyFrame extends JFrame{
	
	private static final long serialVersionUID = -1555086730172535018L;
	public final static int SIZE_X = 600;
	public final static int SIZE_Y = 705;
	
	public MyFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(400, 50);
		setTitle("Larry The Snake");
		setSize(SIZE_X, SIZE_Y);
		}
}