package magic.pac;

import java.util.ArrayList;

public class Magicinventory <A extends Magic<?>>{
    private ArrayList<Magic<?>> spellslist = new ArrayList<>();
    public void addSpell(A magic){
        if(spellslist.size()>=3){
            System.out.printf("Слоты заняты");
        }
        else {
            spellslist.add(magic);
        }
    }

}
