package items.pac;

public class Equipment extends Item{
    private float hpBoost;
    //Getter
    public float getHpBoost(){return hpBoost;}
    //Setter
    public void setHpBoost(float hpBoost){this.hpBoost = hpBoost;}
    public Equipment(String name, int requiredLvl){
        super(name, requiredLvl);
        setCost(requiredLvl*3* rnd.nextInt(100-1)+1);
        this.hpBoost= (float) (requiredLvl/10)*getCost()/1000;//TODO проверить цены
    }
}
