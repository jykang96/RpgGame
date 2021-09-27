package CharacterSkills;

public class Effect {
    private final Stat stat;
    private final String type;
    public enum Type{
        //heal
        Heal,
        ManaHeal,
        //turn status up
        TurnArmorUp,
        TurnSpeedUp,
        TurnLuckUp,
        TurnAttackUp,
        TurnHealthUp,
        TurnManaUp,
        //block
        Block,
        //damage
        Damage,
        //turn damage
        TurnEnemyDamage,
        TurnEnemyArmorDown,
        TurnEnemySpeedDown,
        TurnEnemyLuckDown,
        TurnEnemyAttackDown



        //disables enemy skill (just an idea)
        //BlockEnemySkill
    }

    public Effect(Type type, Stat stat){
        this.stat = stat;
        this.type = type.name();
    }
    public String getType(){return this.type; }
    public Stat getStat(){return this.stat;}
}
