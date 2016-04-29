package Main.Util.MapClasses;

import Main.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MovementObject {
    
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
    private boolean player = false;
    private boolean bumped = false;
    private int movePattern = 0;
    
    private MapImageObject iObj;
    private CircleCollisionObject cObj;
    
    private Entity hero;

    public MovementObject(float sp, float X, float Y, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] block, boolean play, Entity h) {
        speed = sp;
        initX = X; initY = Y;
        pixel = pix; orientation = orient;
        IOLoad = load; 
        picWidth = picW; picHeight = picH;
        picX = pX; picY = pY;
        blocked = block;
        player = play;
        hero = h;
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
        
        if (player == true){
            playerMovement(container, delta);
        }
        else{
            npcMovement(delta);
        }
        
        cObj.update(container, sbg, delta);
    }
    
    public void setSpeed(float sp){
        speed = sp;
    }
    
    private void playerMovement(GameContainer container,  int delta){
        
        Input input = container.getInput();
        
        if ((input.isKeyDown(Input.KEY_A)) && (input.isKeyDown(Input.KEY_W))) {
            moveUpLeft(delta);
        } else if ((input.isKeyDown(Input.KEY_A)) && (input.isKeyDown(Input.KEY_S))) {
            moveDownLeft(delta);
        } else if ((input.isKeyDown(Input.KEY_D)) && (input.isKeyDown(Input.KEY_W))) {
            moveUpRight(delta);
        } else if ((input.isKeyDown(Input.KEY_D)) && (input.isKeyDown(Input.KEY_S))) {
            moveDownRight(delta);
        } else if (input.isKeyDown(Input.KEY_W)) {
            moveUp(delta);
        } else if (input.isKeyDown(Input.KEY_S)) {
            moveDown(delta);
        } else if (input.isKeyDown(Input.KEY_A)) {
            moveLeft(delta);
        } else if (input.isKeyDown(Input.KEY_D)) {
            moveRight(delta);
        }
    }
    
    private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / (pixel);
         int yBlock = (int)y / (pixel);
         return blocked[xBlock][yBlock];
     }
    
    public CircleCollisionObject getCObj(){
        return cObj;
    }
    
    public MapImageObject getIObj(){
        return iObj;
    }
    
    public void setSpriteOrient(String str) {
        iObj.setSpriteOrientation(str);
    }
    
    public void setMovePattern(int m){
        movePattern = m;
    }
    
    public void npcMovement(int delta) {
        if (movePattern == 0) {
            stopMoving();
        } else if (movePattern == 1) {
            patrolRightLeft(delta);
        } else if (movePattern == 2) {
            patrolUpDown(delta);
        } else if (movePattern == 3) {
            patrolUpLeftDownRight(delta);
        } else if (movePattern == 4) {
            patrolUpRightDownLeft(delta);
        } else if (movePattern == 5) {
            followPlayer(delta);
        } else {
            stopMoving();
        }
    }
    
    public void stopMoving(){
        
    }
    
    
    public void patrolRightLeft(int delta) {
        if (cObj.getCircle().intersects(hero.getMoveObj().getCObj().getCircle())) {
            stopMoving();
        }
        else if (bumped == false) {
            moveRight(delta);
        } else {
            moveLeft(delta);
        }
    }
    
    public void patrolUpDown(int delta){
        if (cObj.getCircle().intersects(hero.getMoveObj().getCObj().getCircle())) {
            stopMoving();
        }
        else if (bumped == false){
            moveUp(delta);
        }
        else{
            moveDown(delta);
        }
    }
    
    public void patrolUpLeftDownRight(int delta){
        if (cObj.getCircle().intersects(hero.getMoveObj().getCObj().getCircle())) {
            stopMoving();
        }
        else if (bumped == false){
            moveUpLeft(delta);
        }
        else{
            moveDownRight(delta);
        }
    }
    
    public void patrolUpRightDownLeft(int delta){
        if (cObj.getCircle().intersects(hero.getMoveObj().getCObj().getCircle())) {
            stopMoving();
        }
        if (bumped == false){
            moveUpRight(delta);
        }
        else{
            moveDownLeft(delta);
        }
    }
    
    public void followPlayer(int delta){
        
        if (cObj.getX() + 2 < hero.getMoveObj().getCObj().getX()){
            moveRight(delta);
        }
        else if (cObj.getX() - 2 > hero.getMoveObj().getCObj().getX()){
            moveLeft(delta);
        }
        else {
            //Stand
        }
        
        if (cObj.getY() + 2 < hero.getMoveObj().getCObj().getY()) {
            moveDown(delta);
        } 
        else if (cObj.getY() - 2 > hero.getMoveObj().getCObj().getY()) {
            moveUp(delta);
        } 
        else {
            //Stand
        }
    }
    
    public void toggleBump(){
        if (bumped == true){
            bumped = false;
        }
        else{
            bumped = true;
        }
    }
    
    public void moveUpRight(int delta){
        iObj.setSpriteOrientation("right");
            if ( (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() - (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel) - (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() - (delta * speed)))
                    && (!isBlocked(cObj.getX() + (pixel) + (delta * speed), cObj.getY() - (delta * speed)))  
                    ){
                iObj.getSprite().update(delta);
                cObj.addToX(delta * speed);
                cObj.addToY(-(delta * speed));
            }
            else {
                toggleBump();
            }
    }
    
    public void moveUpLeft(int delta){
        iObj.setSpriteOrientation("left");
            if ( (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() - (delta * speed))) 
                    && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() - (delta * speed)))
                    && (!isBlocked(cObj.getX() + (pixel) - (delta * speed), cObj.getY()))
                    ){
                iObj.getSprite().update(delta);
                cObj.addToX(-(delta * speed));
                cObj.addToY(-(delta * speed));
            }
            else {
                toggleBump();
            }
    }
    
    public void moveDownLeft(int delta){
        iObj.setSpriteOrientation("left");
            if ( (!isBlocked(cObj.getX() + (pixel) - (delta * speed), cObj.getY() + (pixel * 2))) 
                    && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed)))
                    && (!isBlocked(cObj.getX(), cObj.getY() + (pixel * 2) + (delta * speed))) 
                    && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel) + (delta * speed))) 
                    ){
                iObj.getSprite().update(delta);
                cObj.addToX(-(delta * speed));
                cObj.addToY(delta * speed);
            }
            else {
                toggleBump();
            }
    }
    
    public void moveDownRight(int delta){
        iObj.setSpriteOrientation("right");
            if ( (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel) + (delta * speed), cObj.getY() + (pixel * 2) + (delta * speed)))
                    && (!isBlocked(cObj.getX() +  (pixel) + (delta * speed), cObj.getY() + (pixel) + (delta * speed)))
                    && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel) + (delta * speed)))
                    ){
                iObj.getSprite().update(delta);
                cObj.addToX(delta * speed);
                cObj.addToY(delta * speed);
            }
            else {
                toggleBump();
            }
    }
    
    public void moveUp(int delta){
        iObj.setSpriteOrientation("up");
            if ( (!isBlocked(cObj.getX(), cObj.getY() - (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel) - 1, cObj.getY() - (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) - 1, cObj.getY() - (delta * speed))) ){
                iObj.getSprite().update(delta);
                cObj.addToY(-(delta * speed));
            }
            else {
                toggleBump();
            }
    }
    
    public void moveLeft(int delta){
        iObj.setSpriteOrientation("left");
            if ( (!isBlocked(cObj.getX() - (delta * speed), cObj.getY())) 
                    && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel) - 1))
                    && (!isBlocked(cObj.getX() - (delta * speed), cObj.getY() + (pixel * 2) - 1)) ){
                iObj.getSprite().update(delta);
                cObj.addToX(-(delta * speed));
            }
            else {
                toggleBump();
            }
    }
    
    public void moveRight(int delta){
        iObj.setSpriteOrientation("right");
            if ( (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY())) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel) - 1)) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) + (delta * speed), cObj.getY() + (pixel * 2) - 1)) ){
                iObj.getSprite().update(delta);
                cObj.addToX(delta * speed);
            }
            else {
                toggleBump();
            }
    }
    
    public void moveDown(int delta){
        iObj.setSpriteOrientation("down");
            if ( (!isBlocked(cObj.getX(), cObj.getY() + (pixel * 2) + (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel) - 1, cObj.getY() + (pixel * 2) + (delta * speed))) 
                    && (!isBlocked(cObj.getX() + (pixel * 2) - 1, cObj.getY() + (pixel * 2) + (delta * speed))) ){
                iObj.getSprite().update(delta);
                cObj.addToY(delta * speed);
            }
            else {
                toggleBump();
            }
    }
}
