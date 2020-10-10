package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pile.DrawPile;
import pile.IDrawPile;

public class DrawPileFactory {

	public IDrawPile getDrawPile(String path) {
		if (path == null) {
			return (new DrawPile());
		} else {
			return getDrawPile(path);
		}
	}

	private IDrawPile getDrawPileFromFile(String path) {
		return new DrawPile();
	}

	private int[] getNumberFromFile(String path) throws IOException {
		// https://techblogstation.com/java/read-text-file-in-java/
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i;
		while ((i = fileReader.read()) != -1) {
			System.out.print((char) i);
		}
		return null;
	}

	private boolean isValid(String path) {
		return false;
	}

}
