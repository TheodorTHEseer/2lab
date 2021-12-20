package magic.pac;

public interface MagicAbility {
    abstract int regularMagicAttack(int lvl);
    abstract int ultimateMagicAttack(int lvl);
    abstract String getName();
}