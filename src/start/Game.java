package start;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int day = 0;
    private boolean game = true;

    List<Character> pj = new ArrayList<>();;

    public int getDay() {
        return this.day;
    }

    public void setDay(int nbr) {
        this.day += nbr;
    }

    public void hebdo() {
        while (game) {
            Menu menu = new Menu( "\n-- day : "+ getDay() + " --");
            menu.addItem(new MenuItem("next", this::next));
            menu.addItem(new MenuItem("quite", this::quite));
            menu.displayAndWaitChoice();
        }
    }

    public void create(int nbr) {
        for (int i = 0 ; i < nbr; i++) {
            Scanner name = new Scanner(System.in);
            System.out.println("choisit un prenom au personnage " + (i+1));
            Character character = new Character(name.nextLine());
            pj.add(character);
        }
        hebdo();
    }

    public void next() {
        setDay(1);
        hebdo();
    }

    public void quite() {
        System.out.println("partie perdue...");
        game = false;
    }

    public void check() {
        System.out.println("good");
    }
}
