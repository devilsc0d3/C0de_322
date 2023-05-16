package console;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    private final String itemName;
    private final String description;
    private final int durability;

    public Weapon(String name, String description,int durability){
        this.itemName = name;
        this.description = description;
        this.durability = durability;
    }
    public String getDescription(){
        return description;
    }
    public String getItemName(){
        return itemName;
    }

    public void Broken(){
        boolean broken = false;
        if (durability == 1){
            broken = true;
        }
    }
}
