package lib.fils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtFileImporter {

	private ArrayList<String> list = new ArrayList<String>();

	public void setPath(String FileUrl) throws IOException {
		FileReader fr = new FileReader(FileUrl);
		BufferedReader br = new BufferedReader(fr);
		String zeile = "";
		while ((zeile = br.readLine()) != null) {
			list.add(zeile);
		}
		br.close();
	}

	public String[] getTEXT() {
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}
}