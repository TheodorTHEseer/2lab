package cretures.pac;

import magic.pac.MagicAbility;

public class Hero extends Creature {
    private int exp,money,magicId;
    //Getters
    public int getMagicId(){return magicId;}
    public int getMoney(){return money;}
    public int getExp(){return exp;}
    //Setters
    public void setMagicId(int magicId){this.magicId = magicId;}
    public void setMoney(int money){this.money = money;}
    public void setExp(int exp){this.exp = exp;}
    //Конструкторы
    public Hero(String name){
        super(name);
    }


    public Hero (String name, float hp, int exp, float dexteritySkill, int money, float damage) {
        super(name, hp, dexteritySkill, damage);
        this.money = money;
        this.exp = exp;
        this.setDamage(10);
        this.lvl = exp /1000;
    }
}
