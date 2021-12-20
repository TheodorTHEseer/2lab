package magic.pac;

public class AirSpell implements MagicAbility{

    @Override
    public int regularMagicAttack(int lvl) {
        return lvl*5;
    }

    @Override
    public int ultimateMagicAttack(int lvl) {
        return lvl*7;
    }

    @Override
    public String getName() {
        return "Air";
    }
}
