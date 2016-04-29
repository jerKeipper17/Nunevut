package Main;

import Main.Util.MapClasses.MovementObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Entity {
    
    protected float initX;
    protected float initY;
    protected int   pixel = 16;
    protected float speed = 0.175f;
    
    protected String orientation = "down";
    protected String IOLoad;
    protected int picWidth;
    protected int picHeight;
    protected int picX;
    protected int picY;
    
    private MovementObject moveObj;
    
    protected boolean play = false;
    protected boolean [][] block;
    
    private Entity hero;
    
    public Entity(){
        
    }
    
    public Entity(float X, float Y, float sp, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] blocked, boolean player) throws SlickException{
        initX = X; initY = Y;
        speed = sp; pixel = pix;
        orientation = orient;
        IOLoad = load;
        picWidth = picW; picHeight = picH;
        picX = pX; picY = pY;
        block = blocked;
        play = player;
    }
    
    public Entity(float X, float Y, float sp, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] blocked, boolean player, Entity h) throws SlickException{
        initX = X; initY = Y;
        speed = sp; pixel = pix;
        orientation = orient;
        IOLoad = load;
        picWidth = picW; picHeight = picH;
        picX = pX; picY = pY;
        block = blocked;
        play = player;
        hero = h;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        moveObj = new MovementObject(speed, initX, initY, pixel, orientation, IOLoad, picWidth, picHeight, picX, picY, block, play, hero);
        moveObj.init(container, sbg);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        moveObj.render(container, sbg, g);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        moveObj.update(container, sbg, delta);
    }
    
    public void setMovement(int m){
        moveObj.setMovePattern(m);
    }
    
    public void blocked(){
        if (play == true){
            moveObj.getCObj().blocked();
        }
        else{
            moveObj.getCObj().blocked();
            moveObj.toggleBump();
            
        }
    }
    
    public void changePlayerImage(String load, int X, int Y) throws SlickException{
        getMoveObj().getIObj().setLoad(load);
        getMoveObj().getIObj().setSubImageInts(X, Y);
        getMoveObj().getIObj().setupSprite();
    }
    
    public String getPlayerLoader(){
        return getMoveObj().getIObj().getLoad();
    }
    
    public int getPlayerImageX(){
        return getMoveObj().getIObj().getSubImageX();
    }
    
    public int getPlayerImageY(){
        return getMoveObj().getIObj().getSubImageY();
    }
    
    public Shape getCircle(){
        return moveObj.getCObj().getCircle();
    }
    
    public void setSpriteOrientation(String str){
        moveObj.getIObj().setSpriteOrientation(str);
    }
    
    public String getSpriteOrientation(){
        return moveObj.getIObj().getSpriteOrientation();
    }
    
    public MovementObject getMoveObj(){
        return moveObj;
    }
    
    public void setSpeed(float speed){
        moveObj.setSpeed(speed);
    }
}
