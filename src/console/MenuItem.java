package console;


/**
 * The MenuItem class represents a menu item with a label and an associated action.
 * It provides a method to execute the associated action when the menu item is selected.
 */
public record MenuItem(String label, Runnable action) {

	/**
	 * Executes the action associated with the menu item.
	 * It invokes the run() method of the Runnable object.
	 */
	public void execute() {
		this.action.run();
	}
}
