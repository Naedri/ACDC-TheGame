package view.scene;

import application.Main;

public class HumanScene extends APlayScene {
	private static String name = Main.d.get("PLAY_mode_human");

	public HumanScene() {
		super(name);
		setActionCard();
		setActionDrawPile();
		setActionLayPile();
	}

	private void setActionCard() {
		super.cardL.forEach(card -> {
			card.setOnAction(e -> {
				if (card.isActive()) {

				} else if (!card.isActive()) {

				}
			});
		});

	}

	private void setActionDrawPile() {
		// TODO Auto-generated method stub

	}

	private void setActionLayPile() {
		// TODO Auto-generated method stub

	}
}
