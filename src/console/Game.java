package console;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private int day = 0;
    private int timeExpedition = 2;
    private List<String> log = new ArrayList<>();
    private String logtemp = "";
    List<Character> characters = new ArrayList<>();
    List<Character> expedition = new ArrayList<>();

    public int getDay() {
        return this.day;
    }

    public void setDay(int nbr) {
        this.day += nbr;
    }

    public void crazy(int nbr) {
        System.out.println(characters.get(nbr).isCrazy());
        characters.get(nbr).setCrazy(true);
    }

    public Menu daily() {
        log();
        Menu menu = new Menu( "\n-- day : "+ getDay() + " --");
        for (int i = 0; i < characters.size() ; i++) {
            int finalI = i;
            menu.addItem(new MenuItem("nourir " + characters.get(i).getName(), () -> eat(finalI)));

        }
        for (int i = 0; i < characters.size() ; i++) {
            int finalI = i;
            menu.addItem(new MenuItem("abreuver " + characters.get(i).getName(), () -> drink(finalI)));

        }
        menu.addItem(new MenuItem("continuer", this::keepGoingStory));
        menu.addItem(new MenuItem("quitter", this::quite));
        return menu;
    }

    public void menuDaily() {
        Menu menu = this.daily();
        menu.displayAndWaitChoice();
    }

    public void eat(int nbr) {
        characters.get(nbr).setHunger(3);
        menuDaily();
    }

    public void drink(int nbr) {
        characters.get(nbr).setThirty(2);
        menuDaily();
    }

    public void create(int nbr) {
        for (int i = 0 ; i < nbr; i++) {
            Scanner name = new Scanner(System.in);
            System.out.println("choisit un prenom au personnage " + (i+1));
            Character character = new Character(name.nextLine());
            characters.add(character);
        }
//        story();
        logCharacter();
        menuDaily();
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
        System.out.println("\n--------------------- " + "log" + " ------------------------------");
        System.out.print(log.get(day));
        System.out.println("-----------------------" + "---" + "-------------------------------");
    }

    public void keepGoingStory() {
        int i = 0;
        if (timeExpedition == 1) {
            timeExpedition = 8;
            Menu menu = this.expedition();
            menu.displayAndWaitChoice();
        } else {
            timeExpedition--;
            if (expedition.size() != 0) {
                expedition.get(0).setTime(-1);
                if (expedition.get(0).getTime() == 0) {
                    Character character = expedition.remove(0);
                    characters.add(character);
                }
            }

            while (i < characters.size()) {

                characters.get(i).setHunger(-1);
                characters.get(i).setThirty(-1);

                if (characters.get(i).getHunger() == -1 || characters.get(i).getThirty() == -1) {
                    logtemp += (characters.get(i).getName() + " est mort(e)\n");
                    characters.remove(characters.get(i));
                } else {
                    i++;
                }
            }

            if (characters.size() == 0 && expedition.size() == 0) {
                badEnd();
            } else if (day == 404) {
                goodEnd();
            } else {
                logCharacter();
                setDay(1);
                menuDaily();
            }
        }
    }

    public void quite() {
        typing("sauvegarde...",200);
        System.out.println("\nterminer");
        save("game_data.json");
    }

    public void badEnd() {
        typing("ils sont venus me chercher et il ne rester plus personne pour dire quelque chose...\n", 190);
        restart();
    }

    public void goodEnd() {
        typing("ils sont venus me chercher mais il ne mon pas trouvé...\n", 40);
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

    public Menu expedition() {
        Menu menu = new Menu("\n=-- Expedition --=");
        for (int i = 0; i < characters.size() ; i++) {
            int finalI = i;
            menu.addItem(new MenuItem(characters.get(i).getName(), () -> moveCharacterToExpedition(finalI)));
        }
        return menu;
    }

    public void moveCharacterToExpedition(int index) {
        if (index >= 0 && index < characters.size()) {
            Character character = characters.remove(index);
            expedition.add(character);
        }
        logCharacter();
        setDay(1);
        menuDaily();

    }

    public void logCharacter() {
            String logday = "";
            for (int i = 0; i < characters.size(); i++) {

                if (characters.get(i).getThirty() == 0) {
                    logday += characters.get(i).getName() + " est désydrater" + "\n";
                } else if (characters.get(i).getThirty() == 1) {
                    logday += characters.get(i).getName() + " commence a avoir soif" + "\n";
                } else {
                    logday += characters.get(i).getName() + " n'a pas envie de boire" + "\n";
                }

                if (characters.get(i).getHunger() == 0) {
                    logday += (characters.get(i).getName() + " est affamé") + "\n";
                } else if (characters.get(i).getThirty() == 1) {
                    logday += (characters.get(i).getName() + " commence a avoir faim") + "\n";
                } else {
                    logday += (characters.get(i).getName() + " n'a pas envie de manger") + "\n";
                }
            }
            logday += logtemp;
            log.add(logday);
            System.out.println(log);
        }

}


