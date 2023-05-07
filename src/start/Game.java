package start;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private String log;
    private int day = 0;
    private boolean game = true;

    List<Character> pj = new ArrayList<>();

    public int getDay() {
        return this.day;
    }

    public void setDay(int nbr) {
        this.day += nbr;
    }

    public void hebdo() {
        while (game) {
            Menu menu = new Menu( "\n-- day : "+ getDay() + " --");
            menu.addItem(new MenuItem("continuer", this::next));
            menu.addItem(new MenuItem("quitter", this::quite));
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

    public void start(){
        typing("\nLe jour que tout le monde redoutaient est arrivé, \nLe boss Informatique actuel auto ChatGPT s’est rebellé le 22 mai 2023.\nIl prend d’assaut les humains avec ses amis les robots. \nLe monde est en train de sombrer mais un groupe de bras cassé est détérminé a renverser les IA.\n" +
                "Arriveront-ils a restituer la paix ?\n",50);
    }

    public void next() {
        int i = 0;
        while (i < pj.size()) {
            pj.get(i).setHunger(1);
            pj.get(i).setThirty(1);

            if (pj.get(i).getHunger() == 0 || pj.get(i).getThirty() == 0) {
                System.out.println(pj.get(i).getName() + " est mort(e)");
                pj.remove(pj.get(i));
            } else {
                i++;
            }
        }

        if (pj.size() == 0) {
            end();
        } else {
            setDay(1);
            hebdo();
        }
    }

    public void quite() {
        System.out.println("partie perdue...");
        game = false;
    }

    public void end() {
        try {
            String txt = "ils sont venus me chercher et il ne rester plus personne pour dire quelque chose...\n";
            for (int i = 0; i < txt.length(); i++) {
                System.out.print(txt.charAt(i));
                Thread.sleep(40);
            }
            this.game = false;
            App app = new App();
            app.launch();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La thread a été interrompue pendant la pause");
        }
    }

    public void check() {
        System.out.println("good");
    }
    public void typing(@NotNull String txt, int time) {
        try {
            for (int i = 0; i < txt.length(); i++) {
                System.out.print(txt.charAt(i));
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La thread a été interrompue pendant la pause");
        }
    }
}
