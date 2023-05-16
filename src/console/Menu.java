package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Menu class represents a menu with a name and a list of menu items.
 * It provides methods to display the menu, wait for user input, and execute the selected menu item.
 */
public class Menu {
	String name;
	List<MenuItem> items;
	Scanner scanner;

	/**
	 * Creates a new Menu object with the specified name.
	 *
	 * @param name the name of the menu
	 */
	public Menu(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

	/**
	 * Displays the menu and waits for the user to make a choice.
	 * It executes the selected menu item's action when a valid choice is made.
	 */
	public void displayAndWaitChoice() {
		this.scanner = new Scanner(System.in);
		boolean end = false;
		do {
			System.out.println(this.name);
			for(int i=0; i< items.size(); i++) {
				String line = String.format("%d - %s", i+1, this.items.get(i).label());
				System.out.println(line);
			}
			System.out.print("Votre choix : ");
			try {
				int menuChoice = this.scanner.nextInt();
				if (menuChoice == 666) {
					System.out.println("...Votre fin est proche...\n\n");
				}
				if(menuChoice <= this.items.size() && menuChoice > 0) {
					this.items.get(menuChoice - 1).execute();
					end = true;
				} else {
					System.out.println("Saisie invalide2");
				}
			}catch(Exception e) {
				System.out.println("Saisie invalide");
				System.err.println("Error saving game: " + e.getMessage());

				this.scanner = new Scanner(System.in);
			}			
		}while(!end);
		this.scanner.close();
	}

	/**
	 * Adds a menu item to the menu.
	 *
	 * @param item the menu item to add
	 */
	public void addItem(MenuItem item) {
		items.add(item);
	}
}
