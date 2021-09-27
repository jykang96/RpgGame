package RpgGame;
import CharacterModels.Paladin;
import CharacterModels.Character;
import CharacterModels.Mage;
import CharacterModels.Warrior;
import CharacterModels.Assassin;
import CharacterItems.*;
import CharacterSkills.SkillsFactory;
/**
 *
 * This is a Factory Class for Employee class.
 * Factory class is useful when you want to make multiple objects of a class.
 *
 * @author Jaeyoung Kang, 2021
 */
public class CharacterInitializer {

    //getSingleSkill(String skillName, String skillType, boolean available, int unlockLevel, int level, int cost, int turn, String firstEffect, String firstStat, int firstValue)

    public Character initializeCharacterType(String characterType, String name){
        ItemsFactory itemsFactory = new ItemsFactory();
        SkillsFactory skillsFactory = new SkillsFactory();
        Character character = null;
        // in case of Paladin
        if (characterType.equalsIgnoreCase("p")){
            character = new Paladin(name);
            character.getEquippedItems().add(itemsFactory.getSingleItems("Basic Hammer", "WEAPON", 1, "ATTACK", "PLUS", 2, 0));
            character.getEquippedItems().add(itemsFactory.getSingleItems("Rusty Shield", "OFFHAND", 1, "ARMOR", "PLUS", 1, 0));
            character.getEquippedItems().add(itemsFactory.getSingleItems("Worn Boots", "BOOTS", 1, "ARMOR", "PLUS", 0 , 0));
            character.getEquippedItems().add(itemsFactory.getSingleItems("Rusted Leggings", "LEGGINGS", 1, "ARMOR", "PLUS", 0 , 0));
            character.getEquippedItems().add(itemsFactory.getSingleItems("Old Plate Armor", "ARMOR", 1, "ARMOR", "PLUS", 1, 0));
            character.getInventoryItems().add(itemsFactory.getSingleItems("Minor Health Potion", "CONSUMABLE", 5, "HEALTH", "PLUS", 5, 0));
            character.getInventoryItems().add(itemsFactory.getSingleItems("Minor Mana Potion", "CONSUMABLE", 3, "MANA", "PLUS", 6, 0));
            character.getInventoryItems().add(itemsFactory.getSingleItems("A PAIR OF NIKES NIGGA", "BOOTS", 3, "MANA", "PLUS", 6, 0));
            character.getEquippedSkills().add(skillsFactory.getSingleSkill("Holy Heal", "SELF", true, 1, 1, 2, 1, "Heal", "PLUS", 3));
            character.getEquippedSkills().add(skillsFactory.getSingleSkill("Shield Up", "SELF", true, 1, 1, 2, 2, "TurnArmorUp", "PLUS", 2));
            character.getEquippedSkills().add(skillsFactory.getSingleSkill("Holy Hammer", "ENEMY", true, 1, 1, 2, 1, "Damage", "MINUS", 6));
            character.getEquippedSkills().add(skillsFactory.getSingleSkill("Holy Water", "SELF", true, 1, 1, 2, 2, "TurnHealthUp", "PLUS", 2));
        }
        // in case of Warrior
        else if (characterType.equalsIgnoreCase("w")){
            character = new Warrior(name);
            character.getEquippedItems().add(itemsFactory.getSingleItems("sword", "WEAPON", 1, "ATTACK", "PLUS", 3, 0));
        }
        // in case of Mage
        else if (characterType.equalsIgnoreCase("m")){
            character = new Mage(name);
        }
        // in case of Assassin
        else if (characterType.equalsIgnoreCase("a")){
            character = new Assassin(name);
        }
        return character;
    }
}
