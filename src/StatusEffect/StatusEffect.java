package StatusEffect;

import CharacterSkills.Effect;
import CharacterSkills.Skills;

public class StatusEffect {
    private final String type;
    private Stat stat;
    private int turn;
    public enum StatusType{
        //turn status up
        ArmorUp,
        SpeedUp,
        LuckUp,
        AttackUp,
        HealthUp,
        ManaUp,

        //turn damage
        EnemyDamage,
        EnemyArmorDown,
        EnemySpeedDown,
        EnemyLuckDown,
        EnemyAttackDown
    }
    public StatusEffect(StatusType type, Stat stat, int turn){
        this.type = type.name();
        this.stat = stat;
        this.turn = turn;
    }

    public String getType(){
        return this.type;
    }
    public Stat getStat(){
        return this.stat;
    }
    public int getTurn() {
        return this.turn;
    }
    public void setTurn(int turn){
        this.turn = turn;
    }

}
