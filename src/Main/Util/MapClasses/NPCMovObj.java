package Main.Util.MapClasses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NPCMovObj  {
    
    private MapImageObject iObj;
    private CircleCollisionObject cObj;
    
    protected float initX;
    protected float initY;
    protected int   pixel = 16;
    protected float speed;
    
    protected String orientation = "down";
    protected String IOLoad;
    protected int picWidth;
    protected int picHeight;
    protected int picX;
    protected int picY;
    
    private boolean [][] blocked;
    protected boolean bumped = false;

    public NPCMovObj(float sp) {
    }
    
    public NPCMovObj(float sp, float X, float Y, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] block){
        
        speed = sp;
        initX = X; initY = Y;
        pixel = pix; orientation = orient;
        IOLoad = load; 
        picWidth = picW; picHeight = picH;
        picX = pX; picY = pY;
        blocked = block;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
        
        iObj = new MapImageObject(IOLoad, picWidth, picHeight, picX, picY);
        iObj.setupSprite();
        iObj.setSpriteOrientation(orientation);
        cObj = new CircleCollisionObject(initX, initY, pixel);
        cObj.init(container, sbg);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        cObj.render(container, sbg, g);
        iObj.getSprite().draw(cObj.getX(), cObj.getY());
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        cObj.passVal();
        
        patrolRightLeft(delta);
        
        cObj.update(container, sbg, delta);
    }
    
    public void patrolRightLeft(int delta) {
        if (bumped == false) {
            iObj.setSpriteOrientation("right");
            if ( (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY())) && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed)))   ){
                iObj.getSprite().update(delta);
            }
            else {
                toggleBlocked();
            }
        } else {
            iObj.setSpriteOrientation("left");
            if ( (!isBlocked(cObj.getX() - (delta * speed), cObj.getY())) && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed)))){
                iObj.getSprite().update(delta);
            } 
            else {
                toggleBlocked();
            }
        }
    }
    
    public void patrolUpDown(int delta){
        if (bumped == false){
            iObj.setSpriteOrientation("up");
            if ( (!isBlocked(cObj.getX(), cObj.getY() + (delta * speed))) && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() - (delta * speed)))){
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
        else{
            iObj.setSpriteOrientation("down");
            if ( (!isBlocked(cObj.getX(), cObj.getY() + (pixel * 2) + (delta * speed))) && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed)))){
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
    }
    
    public void patrolUpLeftDownRight(int delta){
        if (bumped == false){
            iObj.setSpriteOrientation("left");
            if (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() - (delta * speed))) {
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
        else{
            iObj.setSpriteOrientation("right");
            if (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel * 2)+ (delta * speed))){
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
    }
    
    public void patrolUpRightDownLeft(int delta){
        if (bumped == false){
            iObj.setSpriteOrientation("right");
            if (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() - (delta * speed))){
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
        else{
            iObj.setSpriteOrientation("left");
            if (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed))) {
                iObj.getSprite().update(delta);
            }
            else
                toggleBlocked();
        }
    }
    
    
    public void toggleBlocked(){
        if (bumped == true){
            bumped = false;
        }
        else{
            bumped = true;
        }
    }
    
    public CircleCollisionObject getCObj(){
        return cObj;
    }
    
    public MapImageObject getIObj(){
        return iObj;
    }
    
    private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / (pixel);
         int yBlock = (int)y / (pixel);
         return blocked[xBlock][yBlock];
     }
}
