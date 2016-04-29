package Main.Util.MapClasses;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ItemImageObject {
   
    private SpriteSheet charTiles;
    private Image image;
    private String load;
    private final int width;
    private final int height;
    private int subX, subY;
    private int alpha;
    private Color color;
    
    public ItemImageObject(String str, int w, int h, int X, int Y, Color col) throws SlickException{
        load = str;
        width = w;
        height = h;
        subX = X;
        subY = Y;
        color = col;
        setupImage();
    }
    
    public void setLoad(String str){
        load = str;
    }
    
    public String getLoad(){
        return load;
    }
    
    public void setSubImageInts(int X, int Y){
        subX = X;
        subY = Y;
    }
    
    public int getSubImageX(){
        return subX;
    }
    
    public int getSubImageY(){
        return subY;
    }
    
    public void setAlpha(int Alpha){
        alpha = Alpha;
    }
    
    public int getAlpha(){
        return alpha;
    }
    
    public void setColorMask(Color col){
        color = col;
    }
    
    public Color getColorMask(){
        return color;
    }
    
    public void setupImage() throws SlickException{
        charTiles = new SpriteSheet(load, width, height);
        
        image = charTiles.getSubImage(subX, subY);
    }
    
    public Image getImage(){
        return image;
    }
}
