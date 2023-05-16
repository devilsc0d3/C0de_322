package console;

import java.io.Serializable;

public class Need extends Item implements Serializable {
    int nbr;
    private String itemName;
    public Need(int nbr, String itemName){
        this.itemName = itemName;
        this.nbr = nbr;
    }
    public int getNbr(){
        return nbr;
    }

    public String getItemName(){
        return itemName;
    }
}
