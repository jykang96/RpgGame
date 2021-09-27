package CharacterItems;

public class Attribute {
    private final ItemStat stat;
    private final String type;
    private final int turn;

    public enum Type{
        ATTACK,
        ARMOR,
        HEALTH,
        MANA,
        BLEED,
        POISON,
        BLOCK,
        SPEED,
        LUCK,
        TURNHEALTH,
        TURNMANA,
    }
    public Attribute(Type type, ItemStat stat){
        this.stat = stat;
        this.type = type.name();
        this.turn = 0;
    }
    public Attribute(Type type, ItemStat stat, int turn){
        this.stat = stat;
        this.type = type.name();
        this.turn = turn;
    }

    public String getType(){
        return this.type;
    }
    public ItemStat getStat(){
        return this.stat;
    }
    public int getTurn(){return this.turn;}
}
