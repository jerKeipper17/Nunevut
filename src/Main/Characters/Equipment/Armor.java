package Main.Characters.Equipment;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Armor extends Equipment{    
    
    private int pow, def, mag, mdef, dod, spd;
    private int cri, Mhp, Mmp;

    public Armor(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException {
        super(Name, load, w, h, X, Y, col);
    }
    
        //Stats
    public void setMagPow(int Mag){
        mag = Mag;
    }
    public int getMagPow(){
        return mag;
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
    public void setMaxHP(int HP){
        Mhp = HP;
    }
    public int getMaxHP(){
        return Mhp;
    }
    public void setMaxMP(int MP){
        Mmp = MP;
    }
    public int getMaxMP(){
        return Mmp;
    }
    public void setDod(int Dod){
        dod = Dod;
    }
    public int getDod(){
        return dod;
    }
}
