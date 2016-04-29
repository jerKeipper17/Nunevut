package Main.Util.MapClasses;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;



public class MapImageObject {
   
    private SpriteSheet charTiles;
    private Animation sprite, up, down, left, right;
    private String orient;
    private String load;
    private int width;
    private int height;
    private int subX, subY;
    
    public MapImageObject(String str, int w, int h, int X, int Y){
        load = str;
        width = w;
        height = h;
        subX = X;
        subY = Y;
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
    
    public void setupSprite() throws SlickException{
        
        charTiles = new SpriteSheet(load, width, height);
        
        Image[] movementDown = {charTiles.getSubImage(subX, subY), charTiles.getSubImage(subX + 1, subY), charTiles.getSubImage(subX + 2, subY), charTiles.getSubImage(subX + 1, subY)};
        Image[] movementLeft = {charTiles.getSubImage(subX, subY + 1), charTiles.getSubImage(subX + 1, subY + 1), charTiles.getSubImage(subX + 2, subY + 1), charTiles.getSubImage(subX + 1, subY + 1)};
        Image[] movementRight = {charTiles.getSubImage(subX, subY + 2), charTiles.getSubImage(subX + 1, subY + 2), charTiles.getSubImage(subX + 2, subY + 2), charTiles.getSubImage(subX + 1, subY + 2)};
        Image[] movementUp = {charTiles.getSubImage(subX, subY + 3), charTiles.getSubImage(subX + 1, subY + 3), charTiles.getSubImage(subX + 2, subY + 3), charTiles.getSubImage(subX + 1, subY + 3)};
        
        int[] duration = {275, 275, 275, 275};

        //false var means do not auto update until key is pressed
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
    }
    
    public void setSpriteOrientation(String str){
        if (str.toLowerCase().equals("up")){
            sprite = up;
            orient = "up";
        }
        else if (str.toLowerCase().equals("down")){
            sprite = down;
            orient = "down";
        }
        else if (str.toLowerCase().equals("right")){
            sprite = right;
            orient = "right";
        }
        else if (str.toLowerCase().equals("left")){
            sprite = left;
            orient = "left";
        }
        else{
            sprite = down;
            orient = "down";
        }
    }
    
    public String getSpriteOrientation(){
        return orient;
    }
    
    public Animation getSprite(){
        return sprite;
    }
}
