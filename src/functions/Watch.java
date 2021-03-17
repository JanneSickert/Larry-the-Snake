package functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Diese Klasse beinhaltet den Timer für die Spielzeit.
 * 
 * @author Janne
 *
 */
public class Watch {

	public static String currentTime;
	public static Timer timer;

	public static void reset() {
		currentTime = "00:00";
		initTimer();
	}

	private static void initTimer() {
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = currentTime.split(":");
				int min, sek;
				String ssek, smin;
				min = Integer.parseInt(arr[0]);
				sek = Integer.parseInt(arr[1]);
				sek++;
				if (sek > 59) {
					sek = 0;
					min++;
				}
				if (sek < 10) {
					ssek = "0" + sek;
				} else {
					ssek = "" + sek;
				}
				if (min < 10) {
					smin = "0" + min;
				} else {
					smin = "" + min;
				}
				currentTime = smin + ":" + ssek;
			}
		});
	}
}