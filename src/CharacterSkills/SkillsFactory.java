package CharacterSkills;


public class SkillsFactory {
    Skills skill;
    //for items with single attribute
    public Skills getSingleSkill(String skillName, String skillType, boolean available, int unlockLevel, int level, int cost, int firstEffectturn, String firstEffect, String firstStat, int firstValue){
        skill = new Skills(skillName, Skills.Type.valueOf(skillType), available, unlockLevel, level, cost, new Effects(new Effect(Effect.Type.valueOf(firstEffect), new Stat(Stat.Type.valueOf(firstStat), firstValue, firstEffectturn))));
        return skill;
    }
    //for items with double attributes
    public Skills getDoubleSkill(String skillName, String skillType, boolean available, int unlockLevel, int level, int cost, int firstEffectturn, int secondEffectTurn, String firstEffect, String firstStat, int firstValue, String secondEffect, String secondStat, int secondValue){
        skill = new Skills(skillName, Skills.Type.valueOf(skillType), available, unlockLevel, level, cost, new Effects(new Effect(Effect.Type.valueOf(firstEffect), new Stat(Stat.Type.valueOf(firstStat), firstValue, firstEffectturn)), new Effect(Effect.Type.valueOf(secondEffect), new Stat(Stat.Type.valueOf(secondStat), secondValue, secondEffectTurn))));
        return skill;
    }
    //for items with triple attributes
    public Skills getTripleSkill(String skillName, String skillType, boolean available, int unlockLevel, int level, int cost, int firstEffectturn, int secondEffectTurn, int thirdEffectTurn, String firstEffect, String firstStat, int firstValue, String secondEffect, String secondStat, int secondValue, String thirdEffect, String thirdStat, int thirdValue){
        skill = new Skills(skillName, Skills.Type.valueOf(skillType), available, unlockLevel, level, cost, new Effects(new Effect(Effect.Type.valueOf(firstEffect), new Stat(Stat.Type.valueOf(firstStat), firstValue, firstEffectturn)), new Effect(Effect.Type.valueOf(secondEffect), new Stat(Stat.Type.valueOf(secondStat), secondValue, secondEffectTurn)), new Effect(Effect.Type.valueOf(thirdEffect), new Stat(Stat.Type.valueOf(thirdStat), thirdValue, thirdEffectTurn))));
        return skill;
    }
}
