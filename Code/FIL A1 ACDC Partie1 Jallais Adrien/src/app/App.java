package app;

import java.io.IOException;

import card.ICard;
import factory.DrawPileFactory;
import pile.IDrawPile;

public class App {

	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		DrawPileFactory drawF = new DrawPileFactory();

		String path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // ok
		String path2 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game2.txt"; //
		String path3 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game3.txt"; //
		String path4 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game4.txt"; //

		IDrawPile draw;
		draw = drawF.getDrawPile(path4);

		while (!draw.isEmpty()) {
			ICard card = draw.draw();
			System.out.println(card.getValue());
		}

	}

}
