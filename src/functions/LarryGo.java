package functions;

import panels.Standart;
import javax.swing.JButton;
import panels.Obstacles;

/**
 * <b>Main Klasse von Snake</b><br>
 * Hier kann zwischen verschiedenden Oberflächen ausgewählt werden.
 * @author Janne
 *
 */
public class LarryGo {
	
	public static final int DOT_SIZE = 30;
	public static MyFrame ins;
	public static JButton[][] field = new JButton[MyFrame.SIZE_X / DOT_SIZE][MyFrame.SIZE_X / DOT_SIZE];

	public static void main(String[] args) {
		menu();
	}
	
	public static void menu() {
		Menu d = new Menu();
		d.openWin();
		d.setVisible(true);
	}
	
	public static void ohneHindernisse() {
		ins = new MyFrame();
		ins.add(new Standart());
		ins.setVisible(true);
		Watch.reset();
		Watch.timer.start();
	}
	
	public static void mitHindernissen() {
		ins = new MyFrame();
		ins.add(new Obstacles.MakeObstacles());
		ins.setVisible(true);
	}
	
	public static void mitHindernissenStart() {
		ins.setVisible(false);
		ins = new MyFrame();
		ins.add(new Obstacles.Start());
		ins.setVisible(true);
		Watch.reset();
		Watch.timer.start();
	}
}