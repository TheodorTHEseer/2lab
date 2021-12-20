package magic.pac;

public class Magic <T extends MagicAbility> {
    private T name;
    private int magicDmg;
    public int getMagicDmg(){return magicDmg;}
    public T getName(){return name;}
    public String [] MagicNames = {"Нет магических способностей","Огонь", "Воздух", "Вода", "Мороз", "Земля", "Эфир"};
    public Magic (T name){
        this.name = name;
    }

}
