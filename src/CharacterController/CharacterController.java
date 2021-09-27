package CharacterController;
import CharacterItems.Attribute;
import CharacterItems.Items;
import EnemiesModels.*;
import CharacterModels.Character;
import CharacterSkills.Skills;
import CharacterSkills.Effect;
import StatusEffect.StatusEffect;
import StatusEffect.StatusEffectFactory;

import java.util.*;

//TODO: GLOBAL TODO = YOU MUST MAKE IT SO THAT WHEN WHEN AN ITEM CANNOT BE USED, IT STILL REMAINS IN THE MENU NOT ENEMY MAKING THE MOVE.
// battle menu true vs false should be managed and adjusted better.

public class CharacterController {
    //ALL DATA BASE NEEDED FOR THE CONTROLLER
    private final Character character;
    StatusEffectFactory statusEffectFactory = new StatusEffectFactory();
    // COLOR SCHEMES
    private final String GREEN = "\u001b[32m";
    private final String RESET = "\u001b[0m";
    private final String CYAN = "\u001b[36m";
    private final String RED = "\u001b[31m";
    public final String PURPLE = "\u001B[35m";

    private Scanner input;

    //constructor for character controller
    public CharacterController(Character character){
        this.character = character;
    }
    // @@@@@ ALL ITEM RELATED METHODS @@@@@
    // MAYBE TRY ADDING LEVELS TO ITEMS (LEVEL LIMITS)


    //THIS METHOD IS USED TO REFRESH THE CHARACTER STATS AFTER ITEMS HAVE BEEN EQUIPPED
    //BASED ON THE ATTRIBUTES OF AN ITEM, IT MANIPULATES THE CHARACTER'S CURRENT HEALTH, MAX HEALTH AND
    public void itemStatRefresh(){
        for(Items item : character.getEquippedItems()) {
            for (Attribute attribute : item.getAttributes().getAttributeList()) {
                switch (attribute.getType().toLowerCase()) {
                    case "health":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxHealth(character.getMaxHealth() + attribute.getStat().getStatValue());
                            character.setItemHealth(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxHealth(character.getHealth() - attribute.getStat().getStatValue());
                        }
                        break;
                    case "mana":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxMana(character.getMana() + attribute.getStat().getStatValue());
                            character.setItemMana(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxMana(character.getMana() - attribute.getStat().getStatValue());
                            character.setItemMana(-attribute.getStat().getStatValue());
                        }
                        break;
                    case "attack":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxAttack(character.getAttack() + attribute.getStat().getStatValue());
                            character.setItemAttack(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxAttack(character.getAttack() - attribute.getStat().getStatValue());
                            character.setItemAttack(-attribute.getStat().getStatValue());
                        }
                        character.setAttack(character.getMaxAttack());
                        break;
                    case "armor":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxArmor(character.getArmor() + attribute.getStat().getStatValue());
                            character.setItemArmor(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxArmor(character.getArmor() - attribute.getStat().getStatValue());
                            character.setItemArmor(-attribute.getStat().getStatValue());
                        }
                        character.setArmor(character.getMaxArmor());
                        break;
                    case "speed":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxSpeed(character.getMaxSpeed() + attribute.getStat().getStatValue());
                            character.setItemSpeed(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxSpeed(character.getMaxSpeed() - attribute.getStat().getStatValue());
                            character.setItemSpeed(-attribute.getStat().getStatValue());
                        }
                        character.setSpeed(character.getMaxSpeed());
                        break;
                    case "luck":
                        if (attribute.getStat().getType().equalsIgnoreCase("plus")) {
                            character.setMaxLuck(character.getMaxLuck() + attribute.getStat().getStatValue());
                            character.setItemLuck(attribute.getStat().getStatValue());
                        } else {
                            character.setMaxLuck(character.getMaxLuck() - attribute.getStat().getStatValue());
                            character.setItemLuck(-attribute.getStat().getStatValue());
                        }
                        character.setLuck(character.getMaxLuck());
                        break;
                    default:
                        break;
                }
            }
        }
    }




    //receiving an item from a monster drop
    public void pickUpItems(Items item){
        character.getInventoryItems().add(item);
    }
    //equipping an item
    public void wearFromInventory(Items item){
        character.getEquippedItems().add(item);
        character.getInventoryItems().remove(item);
        itemStatRefresh();
    }
    //user choice on what to equip from an arraylist of inventory items, takes integer choice as a parameter
    public void pickWhatToEquip(int choice){
        if(character.getEquippedItems().size() == 0){
            wearFromInventory(character.getInventoryItems().get(choice-1));
        }
        else {
            boolean isNotWearing = true;
            for (Items item : character.getEquippedItems()) {
                if (character.getInventoryItems().get(choice - 1).getType().equalsIgnoreCase(item.getType())) {
                    isNotWearing = false;
                    break;
                }
            }
            if (!isNotWearing) {
                System.out.println(RED+"You are already equipping an item in your [" + character.getInventoryItems().get(choice - 1).getType() + "] slot."+RESET);
            }
            else {
                wearFromInventory(character.getInventoryItems().get(choice - 1));
            }
        }
    }




    //@@@@@ ALL SKILL-RELATED METHODS @@@@@
    //public void pickSkills(int choice){
    //    useSkills(character.getEquippedSkills().get(choice-1));
    //}

    //public void pickSkills(int choice, Enemy enemy){
    //    showSkills();
    //    Skills skill = character.getEquippedSkills().get(choice-1);
    //    useSkills(skill, enemy);
    //}

    public void useSkills(int choice, Enemy enemy) {
        Skills skill = character.getEquippedSkills().get(choice-1);
        StatusEffect statusEffect;
        if (skill.getCost() > character.getMana()) {
            System.out.println(RED+character.getName() + " has insufficient mana!" + RESET);
        }
        else {
            for (Effect effect : skill.getEffects().getEffectsList()) {
                switch (effect.getType().toLowerCase()) {
                    case "heal":
                        singleTurnSkillPrint(skill, effect, "hp");
                        break;
                    case "manaheal":
                        singleTurnSkillPrint(skill, effect, "mp");
                        break;
                    case "turnarmorup":
                        statusEffect = statusEffectFactory.getStatusEffect("ArmorUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnSkillPrint(statusEffect, skill, effect, "Armor");
                        break;
                    case "turnspeedup":
                        statusEffect = statusEffectFactory.getStatusEffect("SpeedUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnSkillPrint(statusEffect, skill, effect, "Speed");
                        break;
                    case "turnluckup":
                        statusEffect = statusEffectFactory.getStatusEffect("LuckUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnSkillPrint(statusEffect, skill, effect, "Luck");
                        break;
                    case "turnattackup":
                        statusEffect = statusEffectFactory.getStatusEffect("AttackUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnSkillPrint(statusEffect, skill, effect, "Attack");
                        break;
                    case "turnhealthup":
                        statusEffect = statusEffectFactory.getStatusEffect("HealthUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnHealPrint(statusEffect, skill, effect, "HP");
                        //System.out.println("to be done");
                        break;
                    case "turnmanaup":
                        statusEffect = statusEffectFactory.getStatusEffect("ManaUp", "PLUS", effect.getStat().getValue(), effect.getStat().getTurn());
                        multipleTurnHealPrint(statusEffect, skill, effect, "MP");
                        //System.out.println("to be done");
                        break;
                    case "damage":
                        singleTurnDamageSkillPrint(enemy, skill, effect);
                        break;
                    case "turnenemydamage":

                    default:
                        break;
                }
            }
        }
    }


    // THIS IS WHERE THE STATUS EFFECTS ARE CALCULATED AND DONE
    public void singleTurnSkillPrint(Skills skill, Effect effect, String type){
        System.out.println(character.getName() + " used " + skill.getName());
        System.out.println(character.getName() + " recovers " + effect.getStat().getValue() + " amount of " + type + ".");
        System.out.println(character.getName() + " uses " + skill.getCost() + " amount of MP.");
        if(type.equalsIgnoreCase("hp")) {
            character.setHealth(character.getHealth() + effect.getStat().getValue());
            character.setMana(character.getMana() - skill.getCost());
            if (character.getHealth() > character.getMaxHealth()) {
                character.setHealth(character.getMaxHealth());
            }
        }
        else{
            character.setMana(character.getMana() + effect.getStat().getValue());
            if (character.getMana() > character.getMaxMana()) {
                character.setMana(character.getMaxMana());
            }
        }
    }


    public void multipleTurnSkillPrint(StatusEffect statusEffect, Skills skill, Effect effect, String type){
        if(!checkStatusOverlap(statusEffect)) {
            System.out.println(character.getName() + " used " + skill.getName());
            System.out.println(character.getName() + " will gain " + effect.getStat().getValue() + " amount of "+type+" for next " + effect.getStat().getTurn() + " turns.");
            System.out.println(character.getName() + " uses " + skill.getCost() + " amount of MP.");
            character.setMana(character.getMana() - skill.getCost());
            if(type.equalsIgnoreCase("armor")) {
                character.setArmor(character.getArmor() + effect.getStat().getValue());
            }
            else if(type.equalsIgnoreCase("speed")){
                character.setSpeed(character.getSpeed() + effect.getStat().getValue());
            }
            else if(type.equalsIgnoreCase("luck")){
                character.setLuck(character.getLuck() + effect.getStat().getValue());
            }
            else if(type.equalsIgnoreCase("attack")){
                character.setAttack(character.getAttack() + effect.getStat().getValue());
            }
        }
        else{
            System.out.println(RED+"You are already under the status effect of " + effect.getType() + "." + RESET);
        }
    }

    public void multipleTurnHealPrint(StatusEffect statusEffect, Skills skill, Effect effect, String type){
        if(!checkStatusOverlap(statusEffect)) {
            System.out.println(character.getName() + " used " + skill.getName());
            System.out.println(character.getName() + " will recover " + effect.getStat().getValue() + " amount of "+type+" every turn for next " + effect.getStat().getTurn() + " turns.");
            System.out.println(character.getName() + " uses " + skill.getCost() + " amount of MP.");
            character.setMana(character.getMana() - skill.getCost());
        }
        else{
            System.out.println(RED+"You are already under the status effect of " + effect.getType() + "." + RESET);
        }
    }





    public void singleTurnDamageSkillPrint(Enemy enemy, Skills skill, Effect effect){
        System.out.println(character.getName() + " used " + skill.getName());
        System.out.println(character.getName() + " deals " + effect.getStat().getValue() + " amount of damage to " + enemy.getName());
        System.out.println(character.getName() + " uses " + skill.getCost() + " amount of MP.");
        character.setMana(character.getMana() - skill.getCost());
        enemy.setHealth(enemy.getHealth() - effect.getStat().getValue());
        if (enemy.getHealth() <= 0) {
            enemy.setHealth(0);
        }
        System.out.println(enemy.getName() + " has " + enemy.getHealth() + " HP.");
    }

    // multiple turn damage effects such as poison, bleed...etc
    public void multipleTurnDamageSkillPrint(Enemy enemy, StatusEffect statusEffect, Skills skill, Effect effect, String type){
        if(!checkStatusOverlap(statusEffect)){
            System.out.println(character.getName() + " used " + skill.getName());
            System.out.println(enemy.getName() + " will take " + effect.getStat().getValue() + " amount of damage for next " + effect.getStat().getTurn() + " turns.");
            System.out.println(character.getName() + " uses " + skill.getCost() + " amount of MP.");
            character.setMana(character.getMana() - skill.getCost());
            //character.setArmor()
        }
    }

    public void unlockSkills(Skills skill){
        character.getAvailableSkills().add(character.getEquippedSkills().size()-1,skill);
    }
    public void replaceSkillSlot(int equippedSlot, int availableSlot){
        character.getEquippedSkills().add(character.getEquippedSkills().get(equippedSlot-1));
        character.getEquippedSkills().remove(character.getEquippedSkills().get(equippedSlot-1));
    }

    // this is where the status effects are added
    public boolean checkStatusOverlap(StatusEffect effect){
        boolean overlap = false;
        if(character.getStatusEffects().size() == 0){
            character.getStatusEffects().add(effect);
            overlap = false;
        }
        else {
            boolean isNotEffected = true;
            for (StatusEffect statusEffect : character.getStatusEffects()) {
                if (effect.getType().equalsIgnoreCase(statusEffect.getType())) {
                    isNotEffected = false;
                    break;
                }
            }
            if (!isNotEffected){
                overlap = true;
            }
            else{
                character.getStatusEffects().add(effect);
            }
        }
        return overlap;
    }

    //TODO: has to be fixed, it doesnt go through the entire status effect list, once it sees a status effect that meets a certain requirement, the loop stops.
    public void statusEffectCalculator() {
        if (character.getStatusEffects().size() == 0) {
            character.setArmor(character.getMaxArmor());
            character.setSpeed(character.getMaxSpeed());
            character.setLuck(character.getMaxLuck());
            character.setAttack(character.getMaxAttack());
        }
        else {
            for (Iterator<StatusEffect> it = character.getStatusEffects().iterator(); it.hasNext();) {
                StatusEffect effect = it.next();
                if (effect.getTurn() == 0) {
                    switch (effect.getType().toLowerCase()) {
                        case "armorup":
                            System.out.println(effect.getType() + " effect fades away!");
                            character.setArmor(character.getMaxArmor());
                            break;
                        case "speedup":
                            System.out.println(effect.getType() + " effect fades away!");
                            character.setSpeed(character.getMaxSpeed());
                            break;
                        case "luckup":
                            System.out.println(effect.getType() + " effect fades away!");
                            character.setLuck(character.getMaxLuck());
                            break;
                        case "attackup":
                            System.out.println(effect.getType() + " effect fades away!");
                            character.setAttack(character.getMaxAttack());
                            break;
                        case "healthup":
                            System.out.println(effect.getType() + " effect fades away!");
                            break;
                        case "manaup":
                            System.out.println(effect.getType() + " effect fades way!");
                            break;
                        default:
                            break;
                    }
                    it.remove();
                    if(character.getStatusEffects().size() == 0){
                        break;
                    }
                }
                else {
                    if (effect.getType().equalsIgnoreCase("healthup")) {
                        System.out.println(character.getName() + " heals " + effect.getStat().getValue() + " points of HP from status effect");
                        character.setHealth(character.getHealth() + effect.getStat().getValue());
                        if (character.getHealth() > character.getMaxHealth()) {
                            character.setHealth(character.getMaxHealth());
                        } else if (effect.getType().equalsIgnoreCase("manaup")) {
                            System.out.println(character.getName() + " recovers " + effect.getStat().getValue() + " points of MP from status effect");
                            character.setMana(character.getMana() + effect.getStat().getValue());
                            if (character.getMana() > character.getMaxMana()) {
                                character.setMana(character.getMaxMana());
                            }
                        }
                    }
                    System.out.println((character.getName() + "'s " + effect.getType() + " effect has " + effect.getTurn() + " turn(s) left in its effect."));
                    effect.setTurn(effect.getTurn() - 1);
                }
            }
        }
    }
    // WHERE I LEFT OFF@@@@@@@@@@@@@@@@@@@@







    //@@@@@@ ALL SHOW METHODS @@@@@@
    //SHOW PLAYER STATS
    public String playerStats(){
        return (character.getName() + " has a health of: " + character.getHealth() + " points." + "\n" + character.getName() + " has a mana of: " + character.getMana() + " points.");
    }
    public String displayPlayerStats(){
        return (character.getName() + " has a health of: " + character.getHealth() + " points." + "\n" + character.getName() + " has a mana of: " + character.getMana() + " points.");
    }
    public String displayPlayerArmor(){
        return (character.getName() + " has an armor of: " + character.getArmor() + " points." + "\n" + character.getName() + " has a mana of: " + character.getMana() + " points.");
    }
    public String displayPlayerAttack(){
        return (character.getName() + " has an Attack Point of: " + character.getAttack() + " points.");
    }
    // TODO : MAKE IT SO THAT SHOWS IN THE FORMAT OF BASE STAT + ITEM STAT @@@@DONE
    public void showAllStats(){
        System.out.println(CYAN+ character.getName() + "'s current status: " + RESET);
        System.out.println("[ Health ] = " + character.getHealth()+"/"+character.getMaxHealth() + PURPLE + " ("+(character.getMaxHealth()-character.getItemHealth()) + " + " + character.getItemHealth()+")" + RESET);
        System.out.println("[  Mana  ] = " + character.getMana()+"/"+character.getMaxMana() + PURPLE + " ("+(character.getMaxMana()-character.getItemMana()) + " + " + character.getItemMana()+")" + RESET);
        System.out.println("[ Attack ] = " + character.getAttack()+ PURPLE + " ("+(character.getMaxAttack()-character.getItemAttack()) + " + " + character.getItemAttack()+")" + RESET);
        System.out.println("[  Armor ] = " + character.getArmor() + PURPLE + " ("+(character.getMaxArmor()-character.getItemArmor()) + " + " + character.getItemArmor()+")" + RESET);
        System.out.println("[  Speed ] = " + character.getSpeed() + PURPLE + " ("+(character.getMaxSpeed()-character.getItemSpeed()) + " + " + character.getItemSpeed()+")" + RESET);
        System.out.println("[  Luck  ] = " + character.getLuck()+ PURPLE + " ("+(character.getMaxLuck()-character.getItemLuck()) + " + " + character.getItemLuck()+")" + RESET);
        System.out.println("\n");
    }

    //SHOW PLAYER INVENTORY
    public boolean showInventory(boolean battle){
        System.out.println(CYAN + "You are currently holding..." + RESET);
        for(int i = 0; i < character.getInventoryItems().size(); i++){
            System.out.println("[" + (i+1) + "] " + character.getInventoryItems().get(i).getName() + " (Qty: " + character.getInventoryItems().get(i).getQuantity() + ")");
        }
        System.out.println(CYAN+"\nWould you like to use any of the items?"+RESET);
        System.out.println("[ 1 ] Yes\n[ 2 ] No");
        boolean validation = false;
        boolean itemUsed = false;
        while(!validation){
            try{
                input = new Scanner(System.in);
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        useFromInventory(battle);
                        validation = true;
                        itemUsed = true;
                    }
                    case 2 -> {
                        validation = true;
                        itemUsed = false;
                    }
                    default -> {
                        System.out.println(RED + "You must choose from the options given." + RESET);
                        validation = false;
                    }
                }
            }catch(InputMismatchException ex){
                System.out.println(RED + "You must choose from the options given."+RESET);
            }
        }
        return itemUsed;
    }

    public void useFromInventory(boolean battle){
        boolean validation = false;
        while(!validation){
            try{
                input = new Scanner(System.in);
                System.out.println(CYAN +"\nSelect an item you would like to use."+RESET);
                for(int i = 0; i < character.getInventoryItems().size(); i++){
                    System.out.println("[" + (i+1) + "] " + character.getInventoryItems().get(i).getName() + " (Qty: " + character.getInventoryItems().get(i).getQuantity() + ")");
                }
                int choice = input.nextInt();
                if(choice > character.getInventoryItems().size()||choice <= 0){
                    System.out.println(RED + "You must choose from the options given." + RESET);
                    validation = false;
                }
                else{
                    if(battle){
                        if(character.getInventoryItems().get(choice-1).getType().equalsIgnoreCase("CONSUMABLE")){
                            System.out.println("you use the CONSUMABLE item");
                            System.out.println("CONSUMABLE QTY - 1");
                            validation = true;
                        }
                        else{
                            System.out.println("you cannot equip an item during battle");
                            validation = false;
                        }
                    }
                    else{
                        if(character.getInventoryItems().get(choice-1).getType().equalsIgnoreCase("CONSUMABLE")){
                            useConsumable(character.getInventoryItems().get(choice-1));
                        }
                        else{
                            pickWhatToEquip(choice);
                        }
                        validation = true;
                    }
                }
            }catch(InputMismatchException ex){
                System.out.println(RED + "You must choose from the options given."+RESET);
            }
        }
    }

    public void useConsumable(Items item){
        for(Attribute attribute : item.getAttributes().getAttributeList()){
            int value = attribute.getStat().getStatValue();
            int turn = attribute.getTurn();
            switch(attribute.getType().toLowerCase()){
                case "attack":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("AttackUp", "PLUS", value, turn));
                    multipleTurnItemPrint(item, "attack", attribute);
                    break;
                case "armor":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("ArmorUp", "PLUS", value , turn));
                    multipleTurnItemPrint(item, "armor", attribute);
                    break;
                case "speed":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("SpeedUp", "PLUS", value, turn));
                    multipleTurnItemPrint(item, "speed", attribute);
                    break;
                case "luck":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("speedUp", "PLUS", value, turn));
                    multipleTurnItemPrint(item, "luck", attribute);
                    break;
                case "turnhealth":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("HealthUp", "PLUS", value, turn));
                    multipleTurnItemPrint(item, "health", attribute);
                    break;
                case "turnmana":
                    item.setQuantity(item.getQuantity()-1);
                    character.getStatusEffects().add(statusEffectFactory.getStatusEffect("manaup", "PLUS", value, turn));
                    multipleTurnItemPrint(item, "mana", attribute);
                    break;
                case "health":
                    item.setQuantity(item.getQuantity()-1);
                    character.setHealth(character.getHealth() + attribute.getStat().getStatValue());
                    if(character.getHealth() > character.getMaxHealth()){
                        character.setHealth(character.getMaxHealth());
                    }
                    singleTurnItemPrint(item, "health", attribute);
                    break;
                case "mana":
                    item.setQuantity(item.getQuantity()-1);
                    character.setMana((character.getMana())+attribute.getStat().getStatValue());
                    if(character.getMana() > character.getMaxMana()){
                        character.setMana(character.getMaxMana());
                    }
                    singleTurnItemPrint(item, "mana", attribute);
                    break;
                default:
                    break;
            }
        }
        for (Iterator<Items> it = character.getInventoryItems().iterator(); it.hasNext();) {
            Items quantity = it.next();
            if(quantity.getQuantity() <= 0){
                it.remove();
            }
        }
    }

    public void singleTurnItemPrint(Items item, String type, Attribute attribute){
        System.out.println(character.getName() + " used " + item.getName());
        System.out.println(character.getName() + " recovers " + attribute.getStat().getStatValue() + "amount of " + type);
        System.out.println(character.getName() + " now has " + item.getQuantity() + " " + item.getName()+"(s) left.");
    }
    public void multipleTurnItemPrint(Items item, String type, Attribute attribute){
        System.out.println(character.getName() + " used " + item.getName());
        System.out.println(character.getName() + " will gain " + attribute.getStat().getStatValue() + "amount of " + type + " for next " + attribute.getTurn() + " turns");
        System.out.println(character.getName() + " now has " + item.getQuantity() + " " + item.getName()+"(s) left.");
    }


    // TODO : I HAVE TO MAKE IT SO THAT EMPTY SLOTS (NON-EQUIPPED ITEMS) SHOW AS EMPTY. @@@@DONE
    public void showEquippedItems(){
        System.out.println(CYAN + "You are currently equipping..." + RESET);
        String[] array = new String[]{"HELM", "ARMOR", "LEGGINGS", "BOOTS", "WEAPON", "OFFHAND"};
        //String helm = "HELM", armor = "ARMOR", leggings = "LEGGINGS", boots = "BOOTS", weapon = "WEAPON", offhand = "OFFHAND";
        for(int i = 0; i< character.getEquippedItems().size(); i++){
            for(int j = 0; j<array.length; j++){
                if(character.getEquippedItems().get(i).getType().equalsIgnoreCase(array[j])){
                    System.out.println("[ " + character.getEquippedItems().get(i).getType() + " ] " + character.getEquippedItems().get(i).getName());
                    array[j] = "none";
                }
            }
        }
        for (String s : array) {
            if (s.equalsIgnoreCase("none")) {
                break;
            } else {
                System.out.println("[ " + s + " ] " + RED + "Nothing is worn" + RESET);
            }
        }
        System.out.println("\n");
    }


    //SHOW PLAYER SKILLS
    public void showSkills(){
        System.out.println(CYAN + character.getName() + "'s current skills are..." + RESET);
        for(int i=0; i<character.getEquippedSkills().size(); i++){
            System.out.println("[ " + (i + 1) + " ] " + character.getEquippedSkills().get(i).getName());
        }
        System.out.println("\n");
    }






    //@@@@@ ALL BASIC CHARACTER MOVEMENTS @@@@@
    // basic attack movement




    public void basicAttack(Enemy enemy) {
        enemy.setHealth(enemy.getHealth()-character.getAttack());
        System.out.println(character.getName() + " has attacked " + enemy.getName() + ".");
        System.out.println(enemy.getName() + " took " + character.getAttack() + " amount of damage.");
        System.out.println(enemy.enemyStats());
    }


    //CHARACTER LEVEL UP
    public void characterLevelCheck(){
            if(character.getExp() >= 8) {
                System.out.println(PURPLE + "Character reached level 2!" + RESET);
                character.setLevel(2);
                upgradeTimeChoice();
            }
            if(character.getExp() >= 20) {
                System.out.println(PURPLE + "Character reached level 3!" + RESET);
                character.setLevel(3);
                upgradeTimeChoice();
            }
            if(character.getExp() >= 36) {
                System.out.println(PURPLE + "Character reached level 4!" + RESET);
                character.setLevel(4);
                upgradeTimeChoice();
            }
            if(character.getExp() >= 56) {
                System.out.println(PURPLE + "Character reached level 5!" + RESET);
                character.setLevel(5);
                upgradeTimeChoice();
            }
    }
    public void upgradeTimeChoice(){
        boolean validation = false;
        while(!validation){
            System.out.println("Would you like to enhance character stats now?");
            System.out.println("[ 1 ] Yes\n[ 2 ] No");
            input = new Scanner(System.in);
            int choice = input.nextInt();
            try{
                switch (choice) {
                    case 1 -> {
                        statUpgradeType();
                        validation = true;
                    }
                    case 2 -> {
                        character.setStatPoints(character.getStatPoints() + 5);
                        System.out.println(character.getName() + " gained 5 stat enhance points for later use");
                        System.out.println(character.getName() + " has " + character.getStatPoints() + " points of stat enhance points");
                        validation = true;
                    }
                    default -> System.out.println(RED + "You must choose from the options given" + RESET);
                }
            }catch(InputMismatchException ex){
                System.out.println(RED+ "You must pick from the options above" + RESET);
            }
        }

    }
    public void statUpgradeType(){
        boolean validation = false;
        int points = 5;
        while(!validation){
            System.out.println("Select a stat that you would like to assign extra point(s) to");
            System.out.println("""
                    [ 1 ] = HEALTH
                    [ 2 ] = MANA
                    [ 3 ] = ATTACK
                    [ 4 ] = ARMOR
                    [ 5 ] = SPEED
                    [ 6 ] = LUCK""");
            input = new Scanner(System.in);
            try{
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> points = assignStat("health", points, input);
                    case 2 -> points = assignStat("mana", points, input);
                    case 3 -> points = assignStat("attack", points, input);
                    case 4 -> points = assignStat("armor", points, input);
                    case 5 -> points = assignStat("speed", points, input);
                    case 6 -> points = assignStat("luck", points, input);
                    default -> {
                        System.out.println(RED + "You must input a numeric value" + RESET);
                        validation = false;
                    }
                }
            }catch(InputMismatchException ex){
                System.out.println(RED+"You must input a numeric value" + RESET);
            }
            if(points == 0){
                validation = true;
            }
        }
    }

    public int assignStat(String type, int points, Scanner input){
        boolean validation = false;
        int spentPoints = 0;
        while(!validation) {
            try {
                System.out.println("How many points would you like to assign?");
                spentPoints = input.nextInt();
                if(spentPoints > points){
                    System.out.println(RED + "You don't have sufficient stat points" + RESET);
                }
                else{
                    switch (type.toLowerCase()) {
                        case "health":
                            character.setMaxHealth(character.getMaxHealth() + spentPoints);
                            validation = true;
                            break;
                        case "mana":
                            character.setMaxMana(character.getMaxMana() + spentPoints);
                            validation = true;
                            break;
                        case "speed":
                            character.setMaxSpeed(character.getMaxSpeed() + spentPoints);
                            character.setSpeed(character.getSpeed() + spentPoints);
                            validation = true;
                            break;
                        case "armor":
                            character.setMaxArmor(character.getMaxArmor() + spentPoints);
                            character.setArmor(character.getArmor() + spentPoints);
                            validation = true;
                            break;
                        case "luck":
                            character.setMaxLuck(character.getMaxLuck() + spentPoints);
                            character.setLuck(character.getLuck() + spentPoints);
                            validation = true;
                            break;
                        case "attack":
                            character.setMaxAttack(character.getMaxAttack() + spentPoints);
                            character.setMaxAttack(character.getAttack() + spentPoints);
                            validation = true;
                            break;
                        default:
                            break;
                    }

                }
            } catch (InputMismatchException ex) {
                System.out.println(RED + "You must input a numeric value" + RESET);
            }
        }
        points -= spentPoints;
        System.out.println("You have " + points + " stat points left to be assigned");
        return points;
    }


}

//exp =(character.level - 1) * 2 + 8;