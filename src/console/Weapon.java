package console;

public class Weapon extends Item{
    String name;
    String description;
    int durability;
    boolean broken = true;
    public Weapon(String name, String description,int durability){
        this.name = name;
        this.description = description;
        this.durability = durability;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }

    public void Broken(){
        broken = false;
        if (durability == 1){
            broken = true;
        }
    }
}
