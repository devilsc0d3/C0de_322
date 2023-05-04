package start;

public class Character {
    private int thirty;
    private final String name;
    private int hunger;

    private int crazy;

    public Character(String name) {
        this.name = name;
        this.thirty = 2;
        this.hunger = 4;
        this.crazy = 4;
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirty() {
        return thirty;
    }

    public void setThirty(int thirty) {
        this.thirty -= thirty;
    }

    public String getName() {
        return name;
    }

    public void setHunger(int hunger) {
        this.hunger -= hunger;
    }

    public void setCrazy(int crazy) {
        this.crazy -= crazy;
    }

    public int getCrazy() {
        return crazy;
    }
}

