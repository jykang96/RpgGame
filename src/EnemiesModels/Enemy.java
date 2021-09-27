package EnemiesModels;
import CharacterModels.Character;
import StatusEffect.StatusEffect;
import java.util.*;

public class Enemy {
    private String name;
    private int attack;
    private int armor;
    private int mana;
    private int health;
    private int level;
    private int speed;
    private int luck;
    private int expDrop;

    private int maxHealth;
    private int maxMana;

    ArrayList<StatusEffect> statusEffects;

    public Enemy(){
        statusEffects = new ArrayList<StatusEffect>();
    }

    // SETTERS
    public void setName(String name){
        this.name = name;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public void setArmor(int armor){
        this.armor = armor;
    }
    public void setMana(int mana){
        this.mana = mana;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setSpeed(int speed){this.speed = speed;}
    public void setLuck(int luck){this.luck = luck;}
    public void setExpDrop(int expDrop){this.expDrop = expDrop;}


    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
    public void setMaxMana(int maxMana){
        this.maxMana = maxMana;
    }

    // GETTERS
    public String getName(){
        return name;
    }
    public int getAttack(){
        return attack;
    }
    public int getArmor(){
        return armor;
    }
    public int getMana(){
        return mana;
    }
    public int getHealth(){
        return health;
    }
    public int getSpeed(){return speed;}
    public int getLuck(){return luck;}
    public int getExpDrop(){return expDrop;}

    public int getMaxHealth(){
        return maxHealth;
    }
    public int getMaxMana(){
        return maxMana;
    }


    public String enemyStats(){
        return (getName() + " has a health of: " + getHealth() + " points.");
    }

    public void battle(Character character) {
        character.setHealth(character.getHealth()-getAttack());
        System.out.println("The Fallen attacked " + character.getName() + ".");
        System.out.println(character.getName() + " took " + getAttack() + " amount of damage.");
        //System.out.println(character.playerStats());
    }
}
