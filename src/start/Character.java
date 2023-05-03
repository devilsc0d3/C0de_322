package start;

public class Character {
    private int thirty;
    private String name;
    private int hunger;

    public int getHunger() {
        return hunger;
    }

    public int getThirty() {
        return thirty;
    }

    public Character(String name) {
        this.name = name;
        this.thirty = 3;
        this.hunger = 5;
    }
}
