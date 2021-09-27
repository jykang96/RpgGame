package CharacterSkills;

public class Stat {
    private final String type;
    private int value;
    private int turn;


    public enum Type{
        PLUS,
        MINUS,
    }
    public Stat(Type type, int value, int turn){
        this.type = type.name();
        this.value = value;
        this.turn = turn;
    }


    public String getType(){return this.type;}
    public int getValue(){ return this.value;}
    public void setValue(int value){
        this.value = value;
    }
    public int getTurn(){return this.turn;}
    public void setTurn(){this.turn = turn;}
}
