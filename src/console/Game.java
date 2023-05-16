package console;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;

/**
 * The Game class represents the game state and functionality.
 * It manages the game day, characters, log, and various actions.
 * It also provides methods for saving and loading the game.
 */
public class Game implements Loggable, Eatable, Drinkable, Creatable, StoryTeller, EndHandler, Saveable, Serializable {
    private int day;
    private int timeExpedition;
    private final List<String> log;
    private String logTemp;
    private final List<Character> characters;
    private final List<Character> expedition;
    private final Map<String, List<Item>> items;


    public Game(int difficulty) {
        day = 0;
        timeExpedition = 2;
        log = new ArrayList<>();
        logTemp = "";
        characters = new ArrayList<>();
        expedition = new ArrayList<>();
        items = new HashMap<>();
        items.put("food", new ArrayList<>());
        items.put("drink", new ArrayList<>());
        items.put("weapon", new ArrayList<>());
        items.put("other", new ArrayList<>());


        int nbrItems;
        if (difficulty == 1) {
            nbrItems = 10;
        } else if (difficulty == 2) {
            nbrItems = 8;
        } else {
            nbrItems = 5;
        }
        addWater(nbrItems);
        addConservatoryBox(nbrItems);
    }


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
        inventory();
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
        if (items.get("food").size() > 0) {
            items.get("food").remove(0);
            characters.get(nbr).setHunger(3);
        } else {
            System.out.println("\nc'est la hess niveau bouffe..");
        }
        menuDaily();
    }

    /**
     * Hydrates a character.
     *
     * @param nbr The index of the character.
     */
    public void drink(int nbr) {
        if (items.get("drink").size() > 0) {
            characters.get(nbr).setThirty(2);
            items.get("drink").remove(0);
        } else {
            System.out.println("\nc'est la secheresse...");
        }
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
        System.out.println("\n----------------- " + "log of day "+ day + " --------------------------");
        System.out.print(log.get(day));
        System.out.println("-----------------------" + "---" + "-------------------------------");
    }

    public void inventory() {
        System.out.println("\n--- " + "inventaire" + " ---");
        System.out.println("nouriture : " + items.get("food").size());
        System.out.println("eau : " + items.get("drink").size());
        System.out.println("----" + "----------" + "----");
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
                    expedition.get(0).setTime(4);
                    Character character = expedition.remove(0);
                    characters.add(character);

                    Random random = new Random();
                    int randomNumber = random.nextInt(6);
                    addWater(randomNumber);
                    randomNumber = random.nextInt(6);
                    addConservatoryBox(randomNumber);
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject gameData = new JsonObject();

        gameData.addProperty("day", day);
        gameData.addProperty("timeExpedition", timeExpedition);
        gameData.addProperty("logTemp", logTemp);

        // Convertir la liste log en un tableau JSON
        JsonArray logJson = gson.toJsonTree(log).getAsJsonArray();
        gameData.add("log", logJson);

        // Convertir la liste characters en un tableau JSON
        JsonArray charactersJson = gson.toJsonTree(characters).getAsJsonArray();
        gameData.add("characters", charactersJson);

        // Convertir la liste expedition en un tableau JSON
        JsonArray expeditionJson = gson.toJsonTree(expedition).getAsJsonArray();
        gameData.add("expedition", expeditionJson);

        // Convertir la map d'items en un objet JSON
        JsonObject itemsJson = new JsonObject();
        for (Map.Entry<String, List<Item>> entry : items.entrySet()) {
            String key = entry.getKey();
            List<Item> value = entry.getValue();
            JsonArray itemListJson = gson.toJsonTree(value).getAsJsonArray();
            itemsJson.add(key, itemListJson);
        }
        gameData.add("items", itemsJson);

        try (Writer writer = new FileWriter(path)) {
            gson.toJson(gameData, writer);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
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


    public Item createWeapon(String txt, String desc, int durability) {
        return new Weapon(txt,desc,durability);
    }
    public void addWeapon(String txt, String desc, int durability){
        Item weapon = createWeapon(txt, desc, durability);
        items.get("weapon").add(weapon);
    }
    public Item createWater(){
        return new Need(2, "Water");
    }
    public void addWater(int nbr){
        for (int i = 0 ; i < nbr ; i++) {
            Item water = createWater();
            items.get("drink").add(water);
        }

    }
    public Item createConservatoryBox(){
        return new Need(2, "ConservativBox");
    }
    public void addConservatoryBox(int nbr){
        for (int i = 0 ; i < nbr ; i++) {
            Item conservatoryBox = createConservatoryBox();
            items.get("food").add(conservatoryBox);
        }
    }
}


