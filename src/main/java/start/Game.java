package start;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int day = 0;
    private boolean game = true;

    private List<String> log = List.of(new String[]{"rien"});

    List<Character> pj = new ArrayList<>();
    List<Character> expedition = new ArrayList<>();

    public int getDay() {
        return this.day;
    }

    public void setDay(int nbr) {
        this.day += nbr;
    }

    public void crazy(int nbr) {
        System.out.println(pj.get(nbr).isCrazy());
        pj.get(nbr).setCrazy(true);
    }

    public void daily() {
        log();
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
       this.daily();
    }
    public void drink(int nbr) {
        pj.get(nbr).setThirty(2);
        this.daily();
    }

    public void create(int nbr) {
        for (int i = 0 ; i < nbr; i++) {
            Scanner name = new Scanner(System.in);
            System.out.println("choisit un prenom au personnage " + (i+1));
            Character character = new Character(name.nextLine());
            pj.add(character);
        }
        story();
        daily();
    }

    public void story(){
        System.out.println("\n-------------------------------------------- " + "Histoire" + " --------------------------------------------");
        typing("""
				Le jour que tout le monde redoutaient est arrivé,\s
				Le boss Informatique actuel auto ChatGPT s’est rebellé le 22 mai 2023.
				Il prend d’assaut les humains avec ses amis les robots.\s
				Le monde est en train de sombrer mais un groupe de bras cassé est détérminé a renverser les IA.
				Arriveront-ils a restituer la paix ?
				""", 40);
        System.out.println("---------------------------------------------" + "--------" + "---------------------------------------------");

    }

    public void log() {
        System.out.println("\n-------------------------------------------- " + "log" + " --------------------------------------------");
//        System.out.println(log.get(day));
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println("---------------------------------------------" + "---" + "---------------------------------------------");

    }



    public void next() {
        int i = 0;

        while (i < pj.size()) {
            crazy(i);

            pj.get(i).setHunger(-1);
            pj.get(i).setThirty(-1);

            if (pj.get(i).getHunger() == 0 || pj.get(i).getThirty() == 0) {
                System.out.println(pj.get(i).getName() + " est mort(e)");
                pj.remove(pj.get(i));
            } else {
                i++;
            }

        }

        log.add("day : " + day);

        if (pj.size() == 0) {
            badEnd();
        } else if (day == 10) {
            goodEnd();
        } else {
            setDay(1);
            daily();
        }

    }

    public void quite() {
        typing("sauvegarde...",200);
        System.out.println("\nterminer");
        save("game_data.json");
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

    public void save(String path) {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (FileWriter writer = new FileWriter(path)) {
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
