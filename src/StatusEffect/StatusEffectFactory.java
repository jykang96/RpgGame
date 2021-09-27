package StatusEffect;

public class StatusEffectFactory {
    StatusEffect statusEffect;
    public StatusEffect getStatusEffect(String statusType, String statType, int value, int turn){
        statusEffect = new StatusEffect(StatusEffect.StatusType.valueOf(statusType),new Stat(Stat.Type.valueOf(statType), value), turn);
        return statusEffect;
    }
}
