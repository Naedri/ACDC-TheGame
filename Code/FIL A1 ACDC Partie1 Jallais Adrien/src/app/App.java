package app;

import java.io.IOException;

import card.ICard;
import factory.DrawPileFactory;
import pile.DrawPile;

public class App {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DrawPileFactory drawF = new DrawPileFactory();
		String path = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";
		DrawPile draw = (DrawPile) drawF.getDrawPile(path);
		while (!draw.isEmpty()) {
			ICard card = draw.draw();
			System.out.println(card.getValue());
		}

	}

}
