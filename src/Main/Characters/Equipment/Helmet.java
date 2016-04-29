package Main.Characters.Equipment;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Helmet extends Equipment{
    
    private int def, mag, mdef;
    private int cri, Mhp, Mmp;

    public Helmet(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException {
        super(Name, load, w, h, X, Y, col);
    }
    
        //Stats
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
}
