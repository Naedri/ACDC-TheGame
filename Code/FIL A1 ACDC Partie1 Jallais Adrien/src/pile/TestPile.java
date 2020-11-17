package pile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import card.ICard;

public class TestPile {

	@Test
	void test_Hand() {
		System.out.println("test_Hand");

		IHand hand = new Hand(new ArrayList<ICard>());

		ICard c1 = new card.Number(1);
		ICard c2 = new card.Number(2);
		ICard c3 = new card.Number(3);
		ICard c4 = new card.Number(4);
		ICard c5 = new card.Number(5);

		hand.add(c1);
		hand.add(c2);
		hand.add(c4);
		hand.add(c3);
		hand.add(c5);

		List<ICard> cardList = hand.read();
		cardList.forEach(card -> {
			System.out.println(card.getValue());
		});

		System.out.println(c5.compareTo(c1));
	}

	@Test
	void test_LayPile() {
		System.out.println("test_LayPile");
		ICard c1 = new card.Number(1);
		ICard c2 = new card.Number(2);
		ICard c3 = new card.Number(3);
		ICard c4 = new card.Number(4);
		ICard c5 = new card.Number(5);
		ICard c98 = new card.Number(98);
		ICard c99 = new card.Number(99);

		ILayPile dscP = new DescendingPile();
		dscP.lay(c5);
		assertFalse(dscP.isFull());
		dscP.lay(c2);
		assertTrue(dscP.isFull());

		dscP = new DescendingPile();
		dscP.lay(c5);
		assertEquals(2, dscP.readThresholdMax().getValue());
		assertEquals(4, dscP.readThresholdMin().getValue());
		assertEquals(c4, dscP.readThresholdMin());
		assertEquals(c2, dscP.readThresholdMax());

		assertEquals(3, dscP.getRemainCards());
		dscP.lay(c4);
		assertEquals(2, dscP.getRemainCards());

		dscP.lay(c2);
		assertTrue(dscP.isFull());
		assertEquals(0, dscP.getRemainCards());

		ILayPile ascP = new AscendingPile();
		ascP.lay(c98);
		assertEquals(1, ascP.getRemainCards());
		ascP.lay(c99);
		assertTrue(ascP.isFull());
		assertEquals(0, ascP.getRemainCards());

	}
}