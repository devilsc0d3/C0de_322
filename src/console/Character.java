package console;

/**
 The Character class represents a character in a console game.
 It stores information about the character's attributes and provides
 methods to access and modify them.
 */
public class Character {
    private int thirty;
    private final String name;
    private int hunger;
    private boolean able;
    private int time;

    /**
     * Constructs a new Character object with the specified name.
     * The character's attributes are initialized to default values.
     *
     * @param name the name of the character
     */
    public Character(String name) {
        this.name = name;
        this.thirty = 2;
        this.hunger = 5;
        this.able = true;
        this.time = 4;
    }

    /**
     * Returns the hunger level of the character.
     *
     * @return the hunger level
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Returns the thirty value of the character.
     *
     * @return the thirty value
     */
    public int getThirty() {
        return thirty;
    }

    /**
     * Increases the thirty value of the character by the specified amount.
     *
     * @param thirty the amount to increase the thirty value by
     */
    public void setThirty(int thirty) {
        this.thirty += thirty;
    }

    /**
     * Returns the name of the character.
     *
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * Increases the hunger level of the character by the specified amount.
     *
     * @param hunger the amount to increase the hunger level by
     */
    public void setHunger(int hunger) {
        this.hunger += hunger;
    }

    /**
     * Checks if the character is able.
     *
     * @return true if the character is able, false otherwise
     */
    public boolean isAble() {
        return able;
    }

    /**
     * Sets the ability status of the character.
     *
     * @param able the ability status to set
     */
    public void setAble(boolean able) {
        this.able = able;
    }

    /**
     * Returns the time value of the character.
     *
     * @return the time value
     */
    public int getTime() {
        return time;
    }

    /**
     * Increases the time value of the character by the specified amount.
     *
     * @param time the amount to increase the time value by
     */
    public void setTime(int time) {
        this.time += time;
    }
}