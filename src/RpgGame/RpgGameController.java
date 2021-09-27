package RpgGame;
import CharacterController.CharacterController;
import CharacterModels.Character;
import CharacterItems.Items;
import EnemiesModels.Enemy;
import EnemiesModels.TheFallen;

import java.util.*;

public class RpgGameController {
    private final Character character;
    private final CharacterController characterController;
    private final String GREEN = "\u001b[32m";
    private final String RESET = "\u001b[0m";
    private final String CYAN = "\u001b[36m";
    private final String RED = "\u001b[31m";
    final String YELLOW = "\u001b[33m";

    ArrayList<Items> randomItems = new ArrayList<Items>();

    private Scanner input;
    public RpgGameController(Character character){
        this.character = character;
        this.characterController = new CharacterController(character);
    }

// STAGE ONE CODES
    public void stageOne(){
        boolean validation = false;
        characterController.itemStatRefresh();
        showAll();
        System.out.println(YELLOW + "\n\nThis is where Story is told." + RESET);
        while(!validation) {
            System.out.println(CYAN + character.getName() + " is faced with three paths... Choose a path to proceed." + RESET);
            System.out.println("[ 1 ] Old Graveyard");
            System.out.println("[ 2 ] Church Ruins");
            System.out.println("[ 3 ] Desolated Meadow Near Church");
            try {
                input = new Scanner(System.in);
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        oldGraveYard();
                        validation = true;
                    }
                    case 2 -> {
                        churchRuins();
                        validation = true;
                    }
                    case 3 -> {
                        desolate();
                        validation = true;
                    }
                    default -> {
                        System.out.println(RED + "You must choose from the options given." + RESET);
                        validation = false;
                    }
                }
            }catch(InputMismatchException e){
                System.out.println(RED + "You must choose from the options given." + RESET);
            }
        }
    }

    public void oldGraveYard(){
        System.out.println("this is old grave yard");
        System.out.println("battle begins");
        Enemy theFallen = new TheFallen();
        battle(theFallen);
        Enemy theFallenTwo = new TheFallen();
        battle(theFallenTwo);
    }
    public void churchRuins(){
        System.out.println("this is old church ruins");
    }
    public void desolate(){
        System.out.println("this is an desolate");
    }
// STAGE TWO CODES
    public void randomFind(){

    }


    public void showAll(){
        characterController.showAllStats();
        characterController.showEquippedItems();
        characterController.showSkills();
    }

//GAME FUNCTION CODES
public boolean battle(Enemy enemy){
    boolean battleDone = false;
    boolean victory = false;
    System.out.println(character.getName() + " faces an enemy: " + enemy.getName()+"\n\n");
    while(!battleDone) {
        if(character.getSpeed() > enemy.getSpeed()) {
            battleMenu(enemy);
            if(character.getHealth() <=0 || enemy.getHealth() <= 0){
                break;
            }
            enemyMove(enemy);
        } else {
            enemyMove(enemy);
            if(character.getHealth() <=0 || enemy.getHealth() <= 0){
                break;
            }
            battleMenu(enemy);
        }
        characterController.statusEffectCalculator();
    }
    //determination for the battle, victory = false if character dies, victory = true if enemy dies
    if(character.getHealth() <= 0){
        System.out.println(RED +character.getName() + " DIED!" + RESET);
        victory = false;
    }
    else if(enemy.getHealth() <= 0){
        System.out.println(GREEN + enemy.getName() + " falls!" + RESET);
        System.out.println(GREEN + character.getName() + " gains " + enemy.getExpDrop() + " Exp!" + RESET);
        character.setExp(character.getExp()+enemy.getExpDrop());
        characterController.characterLevelCheck();
        victory = true;
    }
    character.getStatusEffects().clear();
    characterController.statusEffectCalculator();
    return victory;
}
// WHERE I LEFT OFF (BELOW AND ABOVE) MUST FINISH THE WHOLE BATTLE LOOP (MAYBE NEED A DOUBLE LOOP) READ LINE 89 ~ 100 CAREFULLY THERE IS A FLAW WHERE THE ONE WITH HIGHER SPEED WILL ALWAYS MAKE A MOVE (THE LOWER ONE WILL NOT BE ABLE TO MAKE ANY MOVE).


public void battleMenu(Enemy enemy) {
    boolean validation = false;
    System.out.println(GREEN + character.getName() + " makes a move." + RESET);
    while (validation == false) {
        try {
            System.out.println(CYAN + "Choose what to do from below" + RESET);
            System.out.println("[ 1 ] See Equipped Items");
            System.out.println("[ 2 ] Use From Inventory");
            System.out.println("[ 3 ] View Player Stats");
            System.out.println("[ 4 ] Basic Attack");
            System.out.println("[ 5 ] Use Skill");
            input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    characterController.showEquippedItems();
                    validation = false;
                    break;
                case 2:
                    boolean itemUsed = characterController.showInventory(false);
                    if(itemUsed == true){
                        validation = true;
                    }
                    else {
                        validation = false;
                    }
                    break;
                case 3:
                    characterController.showAllStats();
                    validation = false;
                    break;
                case 4:
                    System.out.println(character.getName() + " used Basic Attack on " + enemy.getName());
                    characterController.basicAttack(enemy);
                    validation = true;
                    break;
                case 5:
                    System.out.println("Pick which skill to use from below.");
                    characterController.showSkills();
                    boolean skillValidation = false;
                    while(skillValidation == false){
                        try {
                            input = new Scanner(System.in);
                            int skillChoice = input.nextInt();
                            switch (skillChoice) {
                                case 1:
                                    characterController.useSkills(1, enemy);
                                    skillValidation = true;
                                    break;
                                case 2:
                                    characterController.useSkills(2, enemy);
                                    skillValidation = true;
                                    break;
                                case 3:
                                    characterController.useSkills(3, enemy);
                                    skillValidation = true;
                                    break;
                                case 4:
                                    characterController.useSkills(4, enemy);
                                    skillValidation = true;
                                    break;
                                default:
                                    System.out.println(RED + "You must choose from the options given." + RESET);
                                    skillValidation = false;
                            }
                        } catch(InputMismatchException ex){
                            System.out.println(RED + "You must choose from the options given." + RESET);
                        }
                    }
                    validation = true;
                    break;
                default:
                    System.out.println(RED + "You must choose from the options given." + RESET);
                    validation = false;
            }
        } catch (InputMismatchException ex) {
            System.out.println(RED + "You must choose from the options given." + RESET);
        }

    }
}

public void enemyMove(Enemy enemy){
        System.out.println(YELLOW+"\nenemy battle AI happens"+RESET);
        System.out.println("character takes 3 damage");
        character.setHealth(character.getHealth() - 3 );
}

}
