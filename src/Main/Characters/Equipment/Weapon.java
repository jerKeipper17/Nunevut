package Main.Characters.Equipment;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Weapon extends Equipment {

    private int pow, def, mag, spd;
    private int cri;
    
    public Weapon(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException {
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
    public void setDef(int Def){
        def = Def;
    }
    public int getDef(){
        return def;
    }
}
