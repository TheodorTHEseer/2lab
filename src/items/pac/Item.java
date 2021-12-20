package items.pac;

import java.util.Random;

public class Item {
    protected String name;
    protected int cost;
    protected int requiredLvl;
    private float weaponDmg;
    private float weaponEffectBoost;
    private int attackTime;

    Random rnd = new Random();
    //Конструктор
    public Item (String name, int requiredLvl){
        this.name = name;
        this.requiredLvl = requiredLvl;
        this.cost = requiredLvl*10* rnd.nextInt(100-1)+1;
    }
    //Getters
    public String getName(){return name;}
    public int getCost(){return cost;}
    public int getRequiredLvl(){return requiredLvl;}
    //Для weapon
    public float getAttackTime(){return attackTime;}
    public float getWeaponDmg(){return weaponDmg;}
    public float getWeaponEffectBoost(){return weaponEffectBoost;}
    //Setters
    public void setName(String name){this.name = name;}
    public void setCost(int cost){this.cost = cost;}
    public void setRequiredLvl(int requiredLvl){this.requiredLvl = requiredLvl;}
    //  TODO Delete after check
    public void Dipslay(){
        System.out.printf(name + " " + cost);
    }
}
