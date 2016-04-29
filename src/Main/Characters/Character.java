package Main.Characters;

import org.newdawn.slick.SlickException;

public class Character {

    private String name;
    private CharacterCombatStats combat;

    public Character(String Name) throws SlickException{//new character
        name = Name;

        combat = new CharacterCombatStats();
    }

    //Name
    public String getName(){
        return name;
    }
    public void setName(String Name){
        name = Name;
    }
    
    public CharacterCombatStats getCombatStats(){
        return combat;
    }
}
