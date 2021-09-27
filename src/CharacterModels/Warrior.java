package CharacterModels;

public class Warrior extends Character{

    public Warrior(String name){
        super(name);

        setAttack(7);
        setArmor(1);

        setHealth(8);
        setMana(3);

        setSpeed(3);

        setMaxHealth(8);
        setMaxMana(3);
    }
}