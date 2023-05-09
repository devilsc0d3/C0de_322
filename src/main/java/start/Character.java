package start;

public class Character {
    private int thirty;
    private final String name;
    private int hunger;

    private boolean crazy;

    public Character(String name) {
        this.name = name;
        this.thirty = 2;
        this.hunger = 4;
        this.crazy = true;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirty() {
        return thirty;
    }

    public void setThirty(int thirty) {
        this.thirty += thirty;
    }

    public String getName() {
        return name;
    }

    public void setHunger(int hunger) {
        this.hunger += hunger;
    }

    public void setCrazy(boolean crazy) {
        this.crazy = crazy;
    }

    public boolean isCrazy() {
        return crazy;
    }
}

