package StatusEffect;

public class Stat {
    private final String type;
    private int value;
    public enum Type{
        PLUS,
        MINUS
    }
    public Stat(Type type, int value){
        this.type = type.name();
        this.value = value;
    }


    public String getType(){return this.type;}
    public int getValue(){ return this.value;}
    public void setValue(int value){
        this.value = value;
    }

}
