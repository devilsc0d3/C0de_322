package start;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Game implements Serializable {
    //TODO need save method with gsobnà
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
            for (int i = 0 ; i < pj.size() ; i++) {
                int finalI = i;
                menu.addItem(new MenuItem("nourir " + pj.get(i).getName(), () -> eat(finalI)));

            }
            for (int i = 0 ; i < pj.size() ; i++) {
                int finalI = i;
                menu.addItem(new MenuItem("abreuver " + pj.get(i).getName(), () -> drink(finalI)));

            }
            menu.addItem(new MenuItem("continuer", this::next));
            menu.addItem(new MenuItem("quitter", this::quite));
            menu.displayAndWaitChoice();
        }
    }

    public void eat(int nbr) {
        pj.get(nbr).setHunger(3);
       this.hebdo();
    }
    public void drink(int nbr) {
        pj.get(nbr).setThirty(2);
        this.hebdo();
    }

    public void create(int nbr) {
        for (int i = 0 ; i < nbr; i++) {
            Scanner name = new Scanner(System.in);
            System.out.println("choisit un prenom au personnage " + (i+1));
            Character character = new Character(name.nextLine());
            pj.add(character);
        }
        story();
        hebdo();
    }

    public void story(){
//        typing("""
//				Le jour que tout le monde redoutaient est arrivé,\s
//				Le boss Informatique actuel auto ChatGPT s’est rebellé le 22 mai 2023.
//				Il prend d’assaut les humains avec ses amis les robots.\s
//				Le monde est en train de sombrer mais un groupe de bras cassé est détérminé a renverser les IA.
//				Arriveront-ils a restituer la paix ?
//				""", 40);
    }

    public void next() {
        int i = 0;
        while (i < pj.size()) {
            pj.get(i).setHunger(-1);
            pj.get(i).setThirty(-1);

            if (pj.get(i).getHunger() == 0 || pj.get(i).getThirty() == 0) {
                System.out.println(pj.get(i).getName() + " est mort(e)");
                pj.remove(pj.get(i));
            } else {
                i++;
            }
        }

        if (pj.size() == 0) {
            badEnd();
        } else if (day == 10) {
            goodEnd();
        } else {
            setDay(1);
            hebdo();
        }

    }

    public void quite() {
        System.out.println("partie perdue...");
        save();
        game = false;
    }

    public void badEnd() {
        typing("ils sont venus me chercher et il ne rester plus personne pour dire quelque chose...\n", 190);
        this.game = false;
        restart();
    }

    public void goodEnd() {
        typing("ils sont venus me chercher mais il ne mon pas trouvé...\n", 40);
        this.game = false;
        restart();
    }
    public void restart() {
        App app = new App();
        app.launch();
    }

    public void ok() {
        System.out.println("ok");
    }

    public void save() {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (FileWriter writer = new FileWriter("game_data.json")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void typing(String txt, int time) {
        for (int i = 0; i < txt.length(); i++) {
            System.out.print(txt.charAt(i));
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("La thread a été interrompue pendant la pause");
                break;
            }
        }
    }
}
