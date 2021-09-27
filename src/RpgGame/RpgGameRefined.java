package RpgGame;
import CharacterController.CharacterController;
import CharacterModels.*;
import CharacterItems.Items;
import CharacterItems.Attribute;
import CharacterItems.ItemStat;
import CharacterItems.Attributes;
import CharacterModels.Character;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jaeyoung
 */
public class RpgGameRefined {
    //private SkillController controller;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameStarts();
    }

    public static void gameStarts() {
        final String YELLOW = "\u001b[33m";
        final String RESET = "\u001b[0m";
        final String BLUE = "\u001b[34m";
        final String CYAN = "\u001b[36m";
        boolean validation = false;
        Character character;
        RpgGameController rpgGameController;
        CharacterInitializer initialize = new CharacterInitializer();
        Scanner input = new Scanner(System.in);
        System.out.println("\n"+ BLUE + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" + RESET);
        System.out.println(BLUE+"* "+ RESET + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"+ BLUE+" *" + RESET);
        System.out.println(BLUE+"* "+ RESET + "* * * * * * * * * * * * "+YELLOW+"W E L C O M E"+RESET+" * * * * * * * * * * * *"+ BLUE+" *" + RESET);
        System.out.println(BLUE+"* "+ RESET + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"+ BLUE+" *" + RESET);
        System.out.println(BLUE + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" + RESET + "\n");
        System.out.println(CYAN + "Please Enter your character's name" + RESET);
        String name = input.nextLine();
        while (validation == false) {
                System.out.println(CYAN + "Please pick a class of your choice: " + RESET);
                System.out.println("[p] Paladin\n[m] Mage\n[w] Warrior\n[a] Assassin");
                String type = input.nextLine().toLowerCase();
                if(type.equals("p") || type.equals("m") || type.equals("w") || type.equals("a")){
                    character = initialize.initializeCharacterType(type, name);
                    rpgGameController = new RpgGameController(character);
                    rpgGameController.stageOne();
                    validation = true;
                }
                else{
                    System.out.println("Please pick one of [p], [m], [w], or [a] for the character type.");
                    validation = false;
                }
        }
    }


}
