package pile;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import card.ICard;

public class TestPile {

	@Test
	void test_Hand() {

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

}