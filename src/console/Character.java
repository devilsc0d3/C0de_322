package console;

public class Character {
    private int thirty;
    private final String name;
    private int hunger;
    private boolean able;
    private boolean crazy;
    private int time;

    public Character(String name) {
        this.name = name;
        this.thirty = 2;
        this.hunger = 5;
        this.crazy = true;
        this.able = true;
        this.time = 4;
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

    public boolean isAble() {
        return able;
    }

    public void setAble(boolean able) {
        this.able = able;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time += time;
    }
}

