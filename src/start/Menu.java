package start;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	String name;
	List<MenuItem> items;
	Scanner scanner;

	public Menu(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

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
				this.scanner = new Scanner(System.in);
			}			
		}while(!end);
		this.scanner.close();
	}

	public void addItem(MenuItem item) {
		items.add(item);
	}
}
