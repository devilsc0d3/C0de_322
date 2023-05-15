package console;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Game class represents the game state and functionality.
 * It manages the game day, characters, log, and various actions.
 * It also provides methods for saving and loading the game.
 */
public class Game implements Loggable, Eatable, Drinkable, Creatable, StoryTeller, EndHandler, Saveable {
    private int day = 0;
    private int timeExpedition = 2;
    private final List<String> log = new ArrayList<>();
    private String logTemp = "";
    List<Character> characters = new ArrayList<>();
    List<Character> expedition = new ArrayList<>();

    /**
     * Returns the current day of the game.
     *
     * @return The current day.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Sets the current day of the game.
     *
     * @param nbr The number of days to add to the current day.
     */
    public void setDay(int nbr) {
        this.day += nbr;
    }

    /**
     * Generates the daily menu with options for feeding and hydrating characters.
     *
     * @return The daily menu.
     */
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

    /**
     * Displays the daily menu and waits for user input.
     */
    public void menuDaily() {
        Menu menu = this.daily();
        menu.displayAndWaitChoice();
    }

    /**
     * Feeds a character.
     *
     * @param nbr The index of the character.
     */
    public void eat(int nbr) {
        characters.get(nbr).setHunger(3);
        menuDaily();
    }

    /**
     * Hydrates a character.
     *
     * @param nbr The index of the character.
     */
    public void drink(int nbr) {
        characters.get(nbr).setThirty(2);
        menuDaily();
    }

    /**
     * Creates characters based on the given number.
     *
     * @param nbr The number of characters to create.
     */
    public void create(int nbr) {
        for (int i = 0 ; i < nbr; i++) {
            Scanner name = new Scanner(System.in);
            System.out.println("choisit un prenom au personnage " + (i+1));
            Character character = new Character(name.nextLine());
            characters.add(character);
        }
        story();
        logCharacter();
        menuDaily();
    }

    /**
     * Displays the game story.
     */
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

    /**
     * Prints the log for the current day.
     */
    public void log() {
        System.out.println("\n--------------------- " + "log" + " ------------------------------");
        System.out.print(log.get(day));
        System.out.println("-----------------------" + "---" + "-------------------------------");
    }

    /**
     * Continues the story and progresses the game.
     * It handles the passage of time, character actions, and checks for game endings.
     */
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
                    logTemp += (characters.get(i).getName() + " est mort(e)\n");
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

    /**
     * Performs a graceful exit from the game by saving the game data and displaying a message.
     * The game data is saved to a JSON file named "game_data.json".
     */
    public void quite() {
        typing("sauvegarde...",200);
        System.out.println("\nterminer");
        save("game_data.json");
    }

    /**
     * Ends the game with a bad ending and performs necessary actions.
     */
    public void badEnd() {
        typing("ils sont venus me chercher et il ne rester plus personne pour dire quelque chose...\n", 190);
        restart();
    }

    /**
     * Ends the game with a good ending and performs necessary actions.
     */
    public void goodEnd() {
        typing("ils sont venus me chercher mais il ne mon pas trouvé...\n", 40);
        restart();
    }

    /**
     * Restarts the game by launching a new instance of the application.
     */
    public void restart() {
        App app = new App();
        app.launch();
    }

    /**
     * Saves the game data to a JSON file at the specified path.
     *
     * @param path The path of the file to save the game data to.
     */
    public void save(String path) {
        Gson gson = new Gson();
        String json = gson.toJson(this);

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays typing effect for the given text.
     *
     * @param txt  The text to be displayed.
     * @param time The duration of pause between each character in milliseconds.
     */
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

    /**
     * Creates and returns a menu for character expedition.
     *
     * @return The menu for character expedition.
     */
    public Menu expedition() {
        Menu menu = new Menu("\n=-- Expedition --=");
        for (int i = 0; i < characters.size() ; i++) {
            int finalI = i;
            menu.addItem(new MenuItem(characters.get(i).getName(), () -> moveCharacterToExpedition(finalI)));
        }
        return menu;
    }

    /**
     * Moves a character to the expedition based on the provided index.
     *
     * @param index The index of the character to be moved to the expedition.
     */
    public void moveCharacterToExpedition(int index) {
        if (index >= 0 && index < characters.size()) {
            Character character = characters.remove(index);
            expedition.add(character);
        }
        logCharacter();
        setDay(1);
        menuDaily();

    }

    /**
     * Updates the log for the characters' actions for the current day.
     */
    public void logCharacter() {
            StringBuilder logday = new StringBuilder();
        for (Character character : characters) {

            if (character.getThirty() == 0) {
                logday.append(character.getName()).append(" est désydrater").append("\n");
            } else if (character.getThirty() == 1) {
                logday.append(character.getName()).append(" commence a avoir soif").append("\n");
            } else {
                logday.append(character.getName()).append(" n'a pas envie de boire").append("\n");
            }

            if (character.getHunger() == 0) {
                logday.append(character.getName()).append(" est affamé").append("\n");
            } else if (character.getHunger() == 1) {
                logday.append(character.getName()).append(" commence a avoir faim").append("\n");
            } else {
                logday.append(character.getName()).append(" n'a pas envie de manger").append("\n");
            }
        }
            logday.append(logTemp);
            log.add(logday.toString());
            logTemp = "";
        }

}


