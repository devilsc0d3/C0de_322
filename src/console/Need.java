package console;

public class Need extends Item{
    int nbr;
    String name;
    public Need(int nbr, String name){
        this.name = name;
        this.nbr = nbr;
    }
    public int getNbr(){
        return nbr;
    }

    public String getName(){
        return name;
    }
}
