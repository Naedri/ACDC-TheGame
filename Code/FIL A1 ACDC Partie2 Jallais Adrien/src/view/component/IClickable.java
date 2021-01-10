package view.component;

/**
 * Interface to be used when we add an event click, in order to easier the
 * desactivation of all added click events
 * 
 * @author Adrien Jallais
 *
 */
public interface IClickable {

	public boolean isClickable();

	public void setClickable(Boolean clickable);
}
