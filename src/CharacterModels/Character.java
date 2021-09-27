package CharacterModels;

import CharacterItems.Items;
import CharacterSkills.Skills;
import StatusEffect.StatusEffect;

import java.util.ArrayList;

public class Character {
    private final String name;
    private int attack;
    private int armor;
    private int mana;
    private int health;
    private int speed;
    private int luck;
    private int exp;
    private int gold;
    private int level;
    // level
    private int statPoints;

    private int maxHealth;
    private int maxMana;
    private int maxAttack;
    private int maxArmor;
    private int maxLuck;
    private int maxSpeed;

    private int itemHealth;
    private int itemMana;
    private int itemAttack;
    private int itemArmor;
    private int itemLuck;
    private int itemSpeed;

    ArrayList<Items> inventoryItems;
    ArrayList<Items> equippedItems;

    ArrayList<Skills> availableSkills;
    ArrayList<Skills> equippedSkills;

    ArrayList<StatusEffect> statusEffects;

    public Character(String name){
        this.name = name;
        this.exp = 0;
        this.gold = 0;
        this.level = 1;
        inventoryItems = new ArrayList<Items>();
        equippedItems = new ArrayList<Items>();
        availableSkills = new ArrayList<Skills>();
        equippedSkills = new ArrayList<Skills>();
        statusEffects = new ArrayList<StatusEffect>();
    }

    // SETTERS
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
    public void setExp(int exp){
        this.exp = exp;
    }
    public void setGold(int gold){
        this.gold = gold;
    }
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }
    public void setMaxMana(int maxMana){
        this.maxMana = maxMana;
    }
    public void setMaxAttack(int maxAttack){
        this.maxAttack = maxAttack;
    }
    public void setMaxArmor(int maxArmor){
        this.maxArmor = maxArmor;
    }
    public void setMaxLuck(int maxLuck){
        this.maxLuck = maxLuck;
    }
    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    public void setItemHealth(int health){this.itemHealth = health;}
    public void setItemMana(int mana){this.itemMana = mana;}
    public void setItemAttack(int attack){this.itemAttack = attack;}
    public void setItemArmor(int armor){this.itemArmor = armor;}
    public void setItemLuck(int luck){this.itemLuck = luck;}
    public void setItemSpeed(int speed){this.itemSpeed = speed;}
    public void setLevel(int level){this.level = level;}
    public void setStatPoints(int points){this.statPoints = points;}

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
    public int getExp(){
        return exp;
    }
    public int getGold(){
        return gold;
    }
    public int getSpeed(){
        return speed;
    }
    public int getLuck(){
        return luck;
    }




    //GETTERS FOR ALL MAX STATS
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getMaxMana(){
        return maxMana;
    }
    public int getMaxAttack(){
        return maxAttack;
    }
    public int getMaxArmor(){
        return maxArmor;
    }
    public int getMaxLuck(){
        return maxLuck;
    }
    public int getMaxSpeed(){
        return maxSpeed;
    }

    //GETTERS FOR ITEMS
    public ArrayList<Items> getInventoryItems(){
        return inventoryItems;
    }
    public ArrayList<Items> getEquippedItems(){
        return equippedItems;
    }

    //GETTERS FOR SKILLS
    public ArrayList<Skills> getAvailableSkills(){ return availableSkills;}
    public ArrayList<Skills> getEquippedSkills(){ return equippedSkills;}

    //GETTERS FOR STATUS EFFECT
    public ArrayList<StatusEffect> getStatusEffects(){return statusEffects;}

    //GETTERS FOR MAX ITEM STATS
    public int getItemHealth(){return this.itemHealth;}
    public int getItemMana(){return this.itemMana;}
    public int getItemAttack(){return this.itemAttack;}
    public int getItemArmor(){return this.itemArmor;}
    public int getItemLuck(){return this.itemLuck;}
    public int getItemSpeed(){return this.itemSpeed;}

    // GETTERS FOR STAT POINTS
    public int getStatPoints(){return this.statPoints;}

}