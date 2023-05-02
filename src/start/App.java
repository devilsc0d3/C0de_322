package start;

public class App {

	public void launch() {
		Menu menu = this.generateMainMenu();
		menu.displayAndWaitChoice();
	}
	
	public Menu generateMainMenu() {
		Menu menu = new Menu("=--Menu principal--=");
		menu.addItem(new MenuItem("Jouer", this::function1));
		menu.addItem(new MenuItem("Continuer", this::function2));
		menu.addItem(new MenuItem("Langue", this::language));
		menu.addItem(new MenuItem("Quitter\n", () -> System.out.println("function3")));

		return menu;
	}

	public void function1() {
		System.out.println("function1");
	}
	public void function2() {
		try {
			System.out.println("hi?");
			Thread.sleep(1000);

			System.out.println("yes");
			Thread.sleep(1000);
			System.out.println("oh! hello");

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // Rétablit le statut interrompu pour la thread
			System.err.println("La thread a été interrompue pendant la pause");
		}
	}


	public void language() {
		System.out.println("Bientot Disponible !\n");
		this.launch();
	}
}

