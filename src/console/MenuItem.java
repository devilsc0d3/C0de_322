package console;

public record MenuItem(String label, Runnable action) {

	public void execute() {
		this.action.run();
	}
}
