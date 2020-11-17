package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import card.ICard;
import card.Number;
import direction.Direction;
import factory.GameFactory;
import game.IGame;
import game.Move;
import pile.AscendingPile;
import pile.DescendingPile;
import pile.Hand;
import pile.IHand;
import pile.ILayPile;

class TestServices {
	String path1;

	// for first test
	ICard c2 = new Number(2);
	ICard c10 = new Number(10);
	ICard c15 = new Number(15);
	ICard c20 = new Number(20);

	// for backward trick test
	// for hand
	ICard c12 = new Number(12);
	ICard c18 = new Number(18);
	ICard c22 = new Number(22);
	ICard c44 = new Number(44);
	ICard c43 = new Number(43);
	ICard c91 = new Number(91);
	ICard c14 = new Number(14);
	ICard c23 = new Number(33);

	// for lay
	ICard c4 = new Number(4);
	ICard c33 = new Number(33);
	ICard c94 = new Number(94);
	ICard c13 = new Number(13);

	Direction dsc = Direction.DOWN;
	Direction asc = Direction.UP;
	Direction dscS = Direction.SUPER_DOWN;
	Direction ascS = Direction.SUPER_UP;
	ICard cT;

	ILayPile ascP = new AscendingPile();
	ILayPile dscP = new DescendingPile();
	ILayPile ascP2 = new AscendingPile();
	ILayPile dscP2 = new DescendingPile();

	int mv1; // moveweight
	int mv2;

	List<ILayPile> lays = new ArrayList<ILayPile>();
	List<ICard> listC = new ArrayList<ICard>();
	IHand hand;

	int[] tabc;
	int[] tablc;

	private void init() {
		dscP.lay(c20);
		ascP.lay(c10);
		lays.add(dscP);
		lays.add(dscP);
		lays.add(ascP);
		lays.add(ascP);
	}

	private void initBW() {
		dscP.lay(c10);
		ascP.lay(c20);
	}

	private void initBW2() {
		// init lay piles
		dscP.lay(c4);
		dscP2.lay(c33);
		ascP.lay(c94);
		ascP2.lay(c13);
		lays.add(dscP);
		lays.add(dscP2);
		lays.add(ascP);
		lays.add(ascP2);

		// init hand
		listC.add(c12);
		listC.add(c14);
		listC.add(c18);
		listC.add(c22);
	}

	private void print() {
		hand = new Hand(listC);
		lays.forEach(lay -> {
			lay.print();
		});
		hand.print();
	}

	@Test
	void test_SerivcesUser() {
		path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // not ok
		assertTrue(ServiceUser.isPathValid(path1));
	}

	@Test
	void test_ServiceResolution_getMinCard() {

		// usual dir
		cT = ServiceResolution.getMinCard(asc, c10, c15);
		assertTrue(cT.equals(c10));

		cT = ServiceResolution.getMinCard(dsc, c10, c15);
		assertTrue(cT.equals(c15));
		cT = ServiceResolution.getMinCard(dsc, c15, c10);
		assertTrue(cT.equals(c15));

		cT = ServiceResolution.getMinCard(asc, c20, c20);
		assertTrue(cT.equals(c20));

		// super dir
		cT = ServiceResolution.getMinCard(ascS, c10, c15);
		assertTrue(cT.equals(c10));

		cT = ServiceResolution.getMinCard(dscS, c10, c15);
		assertTrue(cT.equals(c15));
		cT = ServiceResolution.getMinCard(dscS, c15, c10);
		assertTrue(cT.equals(c15));

		cT = ServiceResolution.getMinCard(ascS, c20, c20);
		assertTrue(cT.equals(c20));

	}

	@Test
	void test_ServiceResolution_evalCardLay() throws IOException {
		initBW();

		mv1 = ServiceResolution.evalCardLay(dscP, c20);
		mv2 = ServiceResolution.evalCardLay(ascP, c10);
		assertEquals(-10, mv1);
		assertEquals(-10, mv2);

		init();

		mv1 = ServiceResolution.evalCardLay(dscP, c15);
		mv2 = ServiceResolution.evalCardLay(ascP, c15);
		assertEquals(5, mv1);
		assertEquals(5, mv2);
	}

	@Test
	void test_ServiceResolution_evalCardAllLay() throws IOException {
		init();
		tabc = ServiceResolution.evalCardAllLay(lays, c20);
		/*
		 * mv1 = ServiceResolution.evalCardAllLay(lays, c20); mv2 =
		 * ServiceResolution.evalCardAllLay(lays, c10); assertEquals(-10, mv1);
		 * assertEquals(-10, mv2);
		 */
	}

	@Test
	void test_ServiceResolution_chooseOneLayOneCard() {
		initBW2();
		listC.add(c91);

		int[] move = ServiceResolution.chooseOneLayOneCard(lays, listC);
		print();
		ILayPile ltemp = lays.get(move[0]);
		ICard ctemp = listC.get(move[1]);

		assertEquals(lays.get(0), ltemp);
		assertEquals(listC.get(1), ctemp);
	}

	void test_ServiceResolution_chooseOneLayOneCard2() {
		initBW2();
		listC.add(c43);

		int[] move = ServiceResolution.chooseOneLayOneCard(lays, listC);
		print();
		ILayPile ltemp = lays.get(move[0]);
		ICard ctemp = listC.get(move[1]);

		assertEquals(lays.get(1), ltemp);
		assertEquals(listC.get(4), ctemp);
	}

	void test_ServiceResolution_chooseOneLayOneCard3() {
		initBW2();
		listC.add(c23);

		int[] move = ServiceResolution.chooseOneLayOneCard(lays, listC);
		print();
		ILayPile ltemp = lays.get(move[0]);
		ICard ctemp = listC.get(move[1]);

		assertEquals(lays.get(1), ltemp);
		assertEquals(listC.get(4), ctemp);
	}

	@Test
	void test_ServiceResolution_resolve() throws IOException {
		GameFactory gameF = new GameFactory();
		String path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt";
		IGame game = gameF.getGame(path1);
		List<Move> tableResolved = ServiceResolution.resolve(game);
		System.out.println("| LayPile_index | Card_index |");
		tableResolved.forEach(row -> {
			row.print();
		});
	}
}
