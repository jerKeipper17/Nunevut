package Main.Util.MapClasses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.StateBasedGame;

public class CircleCollisionObject {
    
    private Circle circle;
    
    private float objX;
    private float preX;
    private float ppX;
    private float objY;
    private float preY;
    private float ppY;
    private int objSize;
    
    public CircleCollisionObject(float X, float Y, int size){
        objX = X;
        objY = Y;
        objSize = size;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
        
        //Collision is detected by width ~~~ important if width and height are different
        circle = new Circle(objX, objY, objSize);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        //Displays Circle Object
        //g.draw(circle);
        
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        circle.setX(objX);
        circle.setY(objY);
        
        passVal();
    }
    
    public Circle getCircle(){
        return circle;
    }
    
    public float getX(){
        return objX;
    }
    
    public void setX(float X){
        preX = objX;
        objX = X;
    }
    
    public void addToX(float X){
        preX = objX;
        objX += X;
    }
    
    public float getY(){
        return objY;
    }
    
    public void setY(float Y){
        preY = objY;
        objY = Y;
    }
    
    public void addToY(float Y){
        preY = objY;
        objY += Y;
    }
    
    public float getPreX(){
        return preX;
    }
    
    public void setPreX(float X){
        preX = X;
    }
    
    public float getPreY(){
        return preY;
    }
    
    public void setPreY(float Y){
        preY = Y;
    }
    
    public void setSize(int s){
        objSize = s;
    }
    
    public int getSize(){
        return objSize;
    }
    
    public void passVal(){
        ppX = preX;
        ppY = preY;
        preX = objX;
        preY = objY;
    }
    
    public void blocked(){
        objX = ppX;
        objY = ppY;
    }
}
