package lib.fils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class NC {

	private void help(String path, String text, boolean bbbb) {
		File ff = new File(path);
		if (ff.exists()) {
			try {
				FileWriter fwf = new FileWriter(ff, bbbb);
				fwf.write(text);
				fwf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("File do not exist: " + path);
		}
	}

	public void overwrite(String path, String text) {
		help(path, text, false);
	}
}