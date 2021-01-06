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
	private ChangeListener<Boolean> selectedListener;
	private boolean clikable = true;

	public ACardComponent() {
		super();
		init();
	}

	public ACardComponent(String text, Node graphic) {
		super(text, graphic);
		init();
	}

	public ACardComponent(String text) {
		super(text);
		init();
	}

	/**
	 * To gather steps done at the instanciation of the class in a same function
	 * 
	 */
	private void init() {
		initBackground();
		initSelectedObs();
		setMouseHoverAction();
	}

	protected abstract void initBackground();

	/**
	 * To init a Boolean Observable value (SimpleBooleanProperty) and To add a
	 * listener on this value (selectedListener) and To define the actions
	 * associated to the observable change made by the listener (setBackground)
	 * 
	 * @source https://edencoding.com/javafx-properties-and-binding-a-complete-guide/#bindings
	 */
	private void initSelectedObs() {
		selectedObs = new SimpleBooleanProperty(false);
		selectedListener = new ChangeListener<Boolean>() {
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

	/**
	 * To define the behaviour of the component when it is hovered by the user's
	 * mouse
	 */
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
	public void setActive(Boolean state) {
		selectedObs.set(state);
	}

	@Override
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
	public void setBackgroundInit(Background backgroundInit) {
		this.backgroundInit = backgroundInit;
	}

	@Override
	public void setBackgroundHover(Background backgroundHover) {
		this.backgroundHover = backgroundHover;
	}

	@Override
	public boolean isClickable() {
		return clikable;
	}

	@Override
	public void setClickable(Boolean clickable) {
		this.clikable = clickable;
	}

}
