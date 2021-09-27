package CharacterSkills;

public class Skills {
    private String name;
    private String type;
    private Effects effects;
    private boolean available;
    private int unlockLevel;
    private int level;
    //private int turn;
    private int cost;

    public enum Type{
        SELF,
        ENEMY
    }
    public Skills(String name, Type type, boolean available, int unlockLevel, int level, int cost, Effects effects){
        this.name = name;
        this.type = type.name();
        this.effects = effects;
        this.available = available;
        this.unlockLevel = unlockLevel;
        this.level = level;
        this.cost = cost;

    }

    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }
    public Effects getEffects(){
        return this.effects;
    }
    public boolean getAvailability(){return available;}
    public int getUnlockLevel(){return unlockLevel;}
    public int getLevel(){return level;}

    public int getCost(){return cost;}



    public void setLevel(int level){this.level = level;}

    public void setCost(int cost){this.cost = cost;}
    public void setAvailable(boolean available){this.available = available;}
}
