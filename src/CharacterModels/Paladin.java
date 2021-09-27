package CharacterModels;

public class Paladin extends Character {



    public Paladin(String name) {
        super(name);

        setAttack(3);
        setArmor(3);

        setHealth(13);
        setMana(6);

        setSpeed(3);
        setLuck(2);

        setLevel(1);
        setExp(0);
        setStatPoints(0);

        // all max stats in case of status effect
        setMaxHealth(13);
        setMaxMana(6);
        setMaxArmor(3);
        setMaxAttack(3);
        setMaxLuck(2);
        setMaxSpeed(3);
    }

}