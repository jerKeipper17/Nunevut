package Main.Characters.Equipment;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Boots extends Equipment{  
    
    private int def, mdef, dod, spd;

    public Boots(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException {
        super(Name, load, w, h, X, Y, col);
    }
    
        //Stats
    public void setSpd(int Spd){
        spd = Spd;
    }
    public int getSpd(){
        return spd;
    }
    public void setPhyDef(int Def){
        def = Def;
    }
    public int getPhyDef(){
        return def;
    }
    public void setMagDef(int MDef){
        mdef = MDef;
    }
    public int getMagDef(){
        return mdef;
    }
    public void setDod(int Dod){
        dod = Dod;
    }
    public int getDod(){
        return dod;
    }
}
