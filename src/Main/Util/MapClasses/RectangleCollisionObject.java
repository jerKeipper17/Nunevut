
package Main.Util.MapClasses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class RectangleCollisionObject {
    
    private Rectangle rectangle;
    
    private float objX, width;
    private float objY, height;
    
    public RectangleCollisionObject(float X, float Y, float w, float h){
        objX = X;
        objY = Y;
        width = w;
        height = h;
    }
    
    public RectangleCollisionObject(float X, float Y, boolean vert){
        if (vert == true){
            objX = X;
            objY = Y;
            width = 16;
            height = 48;
        }
        else{
            objX = X;
            objY = Y;
            width = 48;
            height = 16;
        }
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
        
        //Collision is detected by width ~~~ important if width and height are different
        rectangle = new Rectangle(objX, objY, width, height);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        g.draw(rectangle);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
    }
    
    public Rectangle getRectangle(){
        return rectangle;
    }
    
    public float getX(){
        return objX;
    }
    
    public void setX(float X){
        X = objX;
    }
    
    public float getY(){
        return objY;
    }
    
    public void setY(float Y){
        Y = objY;
    }
    
    public float getWidth(){
        return width;
    }
    
    public float getHeight(){
        return height;
    }
}