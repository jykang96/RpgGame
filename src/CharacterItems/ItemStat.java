package CharacterItems;

public class ItemStat {
    private final String type;
    private final int value;

    public enum Type{
        PLUS,
        MINUS
    }
    public ItemStat(Type type, int value){
        this.type = type.name();
        this.value = value;
    }

    public String getType(){
        return this.type;
    }

    public int getStatValue(){
        return this.value;
    }

}
