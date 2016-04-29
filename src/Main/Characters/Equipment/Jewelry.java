package Main.Characters.Equipment;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Jewelry extends Equipment{ 
    
    private int pow, def, mag, mdef, dod, spd;
    private int cri, Mhp, Mmp;

    public Jewelry(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException {
        super(Name, load, w, h, X, Y, col);
    }
    
        //Stats
    public void setPhyPow(int Pow){
        pow = Pow;
    }
    public int getPhyPow(){
        return pow;
    }
    public void setCri(int Cri){
        cri = Cri;
    }
    public int getCri(){
        return cri;
    }
    public void setMagPow(int Mag){
        mag = Mag;
    }
    public int getMagPow(){
        return mag;
    }
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
