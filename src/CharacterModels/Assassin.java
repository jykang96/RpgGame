package CharacterModels;

public class Assassin extends Character{

    public Assassin(String name) {
        super(name);

        setAttack(1);
        setArmor(0);

        setHealth(6);
        setMana(12);

        setSpeed(3);

        setMaxHealth(6);
        setMaxMana(12);
    }
}

