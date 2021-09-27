package CharacterSkills;
import java.util.*;

public class Effects {
    private final ArrayList<Effect> effects = new ArrayList<Effect>();
    //single effect spells
    public Effects(Effect effect){
        effects.add(effect);
    }
    //double effect spells
    public Effects(Effect effectOne, Effect effectTwo){
        effects.add(effectOne);
        effects.add(effectTwo);
    }
    //triple effect spells
    public Effects(Effect effectOne, Effect effectTwo, Effect effectThree){
        effects.add(effectOne);
        effects.add(effectTwo);
        effects.add(effectThree);
    }
    public ArrayList<Effect> getEffectsList(){
        return effects;
    }
}
