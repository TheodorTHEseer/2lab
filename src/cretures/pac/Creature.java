package cretures.pac;

public class Creature {
    private String name;
    protected float hp;
    protected float damage;
    protected int lvl;
    protected float dexteritySkill;
    protected float specialEffect;
    //Переменные из Hero
    private int exp,money;
    //Конструктор для hero

    public Creature(String name){
        this.name = name;
    }
    //Перегрузка.
    //Конструктор для enemy
    public Creature (String name, float hp,float dexteritySkill, float damage)
    {
        this.damage = damage;
        this.name=name;
        this.hp=100;
        this.dexteritySkill= dexteritySkill;
    }
    //Методы для получения и назначения значений в приватных переменных
    //Getters
    public String getName(){return name;}
    public float getHp(){return hp;}
    public float getDamage(){return damage;}
    public int getLvl(){return lvl;}
    public float getDexteritySkill (){return dexteritySkill;}
    public int getMoney(){return money;}
    public float getSpecialEffect(){return specialEffect;}
    //Setters
    public void setName(String name){this.name = name;}
    public void setHp(float hp){this.hp = hp;}
    public void setDamage(float damage){this.damage = damage;}
    public void setLvl(int lvl){this.lvl =lvl;}
    public  void setDexteritySkill(float dexteritySkill){this.dexteritySkill = dexteritySkill;}
    public void setMoney(int Money){this.money = money;}
    public void setSpecialEffect(float raceEffect, float powerSkill ){this.specialEffect = raceEffect*powerSkill;}

    //  TODO Delete after check
    public void DisplayCre(){
        System.out.printf(getName() +" "+ getLvl()+ " "+ getDamage());
    }

}