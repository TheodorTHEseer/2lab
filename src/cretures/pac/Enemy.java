package cretures.pac;

public class Enemy extends Creature{
    private float raceEffect, rageEffect;
    public void setRaceEffect(float raceEffect){this.raceEffect = raceEffect;};
    public float getRaceEffect(){return raceEffect;};
    public void setRageEffect (float rageEffect) {this.rageEffect = rageEffect;}
    public float getRageEffect (){return rageEffect;}

    public Enemy (String name, float hp, float dexteritySkill,
                  int lvl, float rageEffect,float raceEffect,float damage) {
        super(name, hp, dexteritySkill, damage);
        this.raceEffect = raceEffect;
        this.lvl = lvl;
        this.rageEffect = rageEffect;

    }
}
