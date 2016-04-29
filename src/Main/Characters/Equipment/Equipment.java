package Main.Characters.Equipment;

import Main.Util.MapClasses.ItemImageObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Equipment {
    
    private ItemImageObject iObj;
    private String name;
    
    private int buy, sell;

    public Equipment(String Name, String load, int w, int h, int X, int Y, Color col) throws SlickException{
        name = Name;
        iObj = new ItemImageObject(load, w, h, X, Y, col);
    }
    
    public void setImageObject(String load, int w, int h, int X, int Y, Color col) throws SlickException{
        iObj = new ItemImageObject(load, w, h, X, Y, col);
    }
    
    public ItemImageObject getImageObject(){
        return iObj;
    }
    
    //Name
    public String getName(){
        return name;
    }
    public void setName(String Name){
        name = Name;
    }
    
    //Buy/Sell
    public int getBuy(){
        return buy;
    }
    public void setBuy(int Buy){
        buy = Buy;
    }
    public int getSell(){
        return sell;
    }
    public void setSell(int Sell){
        sell = Sell;
    }

    public void setMon(int Buy, int Sell){
        buy = Buy;
        sell = Sell;
    }

    public boolean LoadItem(){
        return true;
    }

    //Save to File and Enter into MasterList
    public void SaveItem(){
        
    }
}

