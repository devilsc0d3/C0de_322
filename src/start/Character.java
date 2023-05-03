package start;

public class Character {
    private int thirty;
    private String name;
    private int hunger;

    public Character(String name) {
        this.name = name;
        this.thirty = 2;
        this.hunger = 4;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirty() {
        return thirty;
    }

    public void setThirty(int thirty) {
        this.thirty = thirty;
    }

    public String getName() {
        return name;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setName(String name) {
        this.name = name;
    }
}

