package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import card.ICard;
import card.Number;
import pile.DrawPile;
import pile.IDrawPile;

public class DrawPileFactory {
	Queue<ICard> queue;

	public IDrawPile getDrawPile(String path) {
		if (path == null) {
			return (new DrawPile());
		} else {
			return DrawPile(path);
		}
	}

	private IDrawPile getDrawPileFromFile(String path) {
		return new DrawPile();
	}

	private Queue<ICard> getNumbersFromFile(String path) throws IOException {
		this.queue = new LinkedList<>();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i;
		String number = "";
		while ((i = fileReader.read()) != -1) {
			char digit = (char) i;
			if (digit != '\n') {
				number += String.valueOf(digit);
			} else {
				Number card = new Number(Integer.parseInt(number));
				this.queue.add(card);
				number = "";
			}
		}
		return queue;
	}

	/**
	 * Check for redundancy in the table of ICard
	 * 
	 * @return true if DrawPile is valid
	 */
	private boolean isNumbersValid() {
		return true;
	}
}
