package factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import card.ICard;
import card.Number;
import pile.DrawPile;
import pile.IDrawPile;
import services.Rules;

public class DrawPileFactory {
	Deque<ICard> cardDrawPile;

	public DrawPileFactory() {
		this.cardDrawPile = new LinkedList<>();
	}

	public IDrawPile getDrawPile(String path) throws IOException {
		if (path == null) {
			this.fillcardDrawPile();
		} else {
			this.fillcardDrawPile(path);
		}
		return new DrawPile(cardDrawPile);
	}

	public IDrawPile getDrawPile() {
		this.fillcardDrawPile();
		return new DrawPile(cardDrawPile);
	}

	/**
	 * produce 1 to 99 number and shuffle it and fill cardDrawPile with
	 */
	private void fillcardDrawPile() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = Rules.getCardRange()[0]; i <= Rules.getCardRange()[1]; i++) {
			list.add((Integer) i);
		}
		Collections.shuffle(list);
		list.forEach(i -> {
			ICard card = new Number(i);
			this.cardDrawPile.add(card);
		});
	}

	/**
	 * read a given file and fill cardDrawPile with its integer from its row
	 * 
	 * @param path of the file
	 * @throws IOException about redundancy
	 */
	private void fillcardDrawPile(String path) throws IOException {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int i;
		String number = "";
		while ((i = fileReader.read()) != -1) {
			String digit = String.valueOf((char) i);
			if (!digit.matches("(\\r\\n|\\r|\\n)")) {
				number += digit;
			} else {
				if (!number.isBlank()) {
					ICard card = new Number(Integer.parseInt(number));
					if (isCardValid(card)) {
						this.cardDrawPile.add(card);
					} else {
						throw new IllegalArgumentException("Card value number" + number + " is not unique");
					}
					number = "";
				}
			}
		}
	}

	/**
	 * Check for redundancy in the table of ICard
	 * 
	 * @return true if DrawPile is valid
	 */
	private boolean isCardValid(ICard card) {
		return !cardDrawPile.contains(card);
	}
}
