package items.pac;

public class Weaponry extends Item{
    private float weaponDmg, weaponEffectBoost, attackTime, specialEffect;
    private String rare;
    //Getters
    public float getWeaponDmg(){return weaponDmg;}
    public float getWeaponEffectBoost(){return weaponEffectBoost;}
    public float getAttackTime(){return attackTime;}
    public float getSpecialEffect(){return specialEffect;}
    public String getRare() {return rare;}
    //Setters
    public void setWeaponDmg(float weaponDmg){this.weaponDmg=weaponDmg;}
    public void setWeaponEffectBoost (float weaponEffectBoost){this.weaponEffectBoost=weaponEffectBoost;}
    public void setAttackTime (float attackTime){this.attackTime = attackTime;}
    public void setSpecialEffect (float specialEffect){this.specialEffect = specialEffect;}

    //Конструктор
    public Weaponry (String name, int requiredLvl,
              float weaponEffectBoost){
        super(name, requiredLvl);
        this.weaponEffectBoost=weaponEffectBoost;
        this.weaponDmg=(float) requiredLvl*10+weaponEffectBoost*5;
        this.attackTime = weaponDmg*250*weaponEffectBoost;
        setCost((requiredLvl*10) + rnd.nextInt(1000-1)+1);
    }
}
