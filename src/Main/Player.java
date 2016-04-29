package Main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity{
    
    float speed = 0.175f;
    private boolean blocked = false;
    
    public Player() {
        
    }
    
    public Player(float X, float Y, float sp, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] blocked) throws SlickException {
        super(X, Y, sp, pix, orient, load, picW, picH, pX, pY, blocked, true);
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        super.init(container, sbg);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        super.render(container, sbg, g);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        super.update(container, sbg, delta);
    }
    
    //Negative numbers do backwards walking/directions
    public void setSpeed(float localSpeed){
        speed = localSpeed;
    }
    
    public float getSpeed(){
        return speed;
    }
    
    public int getSpriteWidth(){
        return super.picWidth;
    }
    
    public int getSpriteHeight(){
        return super.picHeight;
    }
}