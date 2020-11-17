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
import pile.AscendingPile;
import pile.DescendingPile;
import pile.ILayPile;

class TestServices {
	String path1;

	ICard c2 = new Number(2);
	ICard c10 = new Number(10);
	ICard c15 = new Number(15);
	ICard c20 = new Number(20);
	Direction dsc = Direction.DOWN;
	Direction asc = Direction.UP;
	Direction dscS = Direction.SUPER_DOWN;
	Direction ascS = Direction.SUPER_UP;
	ICard cT;

	ILayPile ascP = new AscendingPile();
	ILayPile dscP = new DescendingPile();

	int mv1; // moveweight
	int mv2;

	List<ILayPile> lays = new ArrayList<ILayPile>();
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

	@Test
	void test_SerivcesUser() {
		path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // not ok
		assertTrue(ServiceUser.isPathValid(path1));
	}

	@Test
	void test_ServiceResolution_getMinCardLay() {

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
		for (int i = 0; i < tabc.length; i++) {
			System.out.println(tabc[i]);
		}

		/*
		 * mv1 = ServiceResolution.evalCardAllLay(lays, c20); mv2 =
		 * ServiceResolution.evalCardAllLay(lays, c10); assertEquals(-10, mv1);
		 * assertEquals(-10, mv2);
		 */
	}

}
