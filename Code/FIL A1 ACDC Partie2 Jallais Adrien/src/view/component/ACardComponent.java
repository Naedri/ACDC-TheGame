/**
 * 
 */
package view.component;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

/**
 * @author Adrien Jallais
 *
 */
public abstract class ACardComponent extends Button implements ICardView {

	private Background backgroundInit;
	private Background backgroundHover;

	private SimpleBooleanProperty selectedObs;

	public ACardComponent() {
		super();
		initBackground();
		initSelectedObs();
		setMouseHoverAction();
	}

	public ACardComponent(String text, Node graphic) {
		super(text, graphic);
		initBackground();
		initSelectedObs();
		setMouseHoverAction();

	}

	public ACardComponent(String text) {
		super(text);
		initBackground();
		initSelectedObs();
		setMouseHoverAction();
	}

	protected abstract void initBackground();

	private void setMouseHoverAction() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				setBackground(getBackgroundHover());
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (!selectedObs.getValue()) {
					setBackground(getBackgroundInit());
				}
			}
		});
	}

	@Override
	public void switchActive() {
		selectedObs.set(!selectedObs.getValue());
	}

	@Override
	public Boolean isActive() {
		return selectedObs.getValue();
	}

	@Override
	public void setActive(Boolean selected) {
		selectedObs.set(selected);
	}

	/**
	 * To init a Boolean Observable value (SimpleBooleanProperty) and To add a
	 * listener on this value (selectedListener) and To define the actions
	 * associated to the observable change made by the listener (setBackground)
	 * 
	 * @source https://edencoding.com/javafx-properties-and-binding-a-complete-guide/#bindings
	 */
	private void initSelectedObs() {
		selectedObs = new SimpleBooleanProperty(false);
		ChangeListener<Boolean> selectedListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!selectedObs.getValue()) {
					setBackground(getBackgroundInit());
				} else {
					setBackground(getBackgroundHover());
				}
			}
		};
		getActiveObs().addListener(selectedListener);
	}

	public SimpleBooleanProperty getActiveObs() {
		return selectedObs;
	}

	@Override
	public Background getBackgroundInit() {
		return backgroundInit;
	}

	@Override
	public Background getBackgroundHover() {
		return backgroundHover;
	}

	@Override
	/**
	 * @param backgroundInit the backgroundInit to set
	 */
	public void setBackgroundInit(Background backgroundInit) {
		this.backgroundInit = backgroundInit;
	}

	@Override
	/**
	 * @param backgroundHover the backgroundHover to set
	 */
	public void setBackgroundHover(Background backgroundHover) {
		this.backgroundHover = backgroundHover;
	}

}
