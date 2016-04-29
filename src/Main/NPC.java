package Main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import Main.Util.MapClasses.TimerBar;

public class NPC extends Entity{
    
    float speed = 0.175f;
    
    private boolean blocked = false;
    private boolean player = false;
    private TimerBar timer;
    private float timerLength;
    private int act = 0;
    private int[] bList, aList;
    
    public NPC() {
        
    }
    
    public NPC(float X, float Y, float sp, int pix, String orient, String load, int picW, int picH, int picX, int picY, boolean [][] block, boolean player, Entity hero, float length) throws SlickException {
        super(X, Y, sp, pix, orient, load, picW, picH, picX, picY, block, player, hero);
        timerLength = length;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        timer = new TimerBar(0, timerLength);
        timer.init(container, sbg);
        super.init(container, sbg);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        super.render(container, sbg, g);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        if (timer.timerFull()){
            if (aList.length != 0){
                if (act == (aList.length - 1)){
                    act = 0;
                    executeAction(aList[act]);
                }
                else {
                    act = act + 1;
                    executeAction(aList[act]);
                }
            }
            else{
                executeAction(0);
            }
            timer.resetTimer();
        }
        else {
            timer.update(container, sbg, delta);
        }
        super.update(container, sbg, delta);
    }
    
    public TimerBar getTimer(){
        return timer;
    }
    
    public void setTimerLength(float length){
        timer.setTimerLength(length);
    }
    
    public void setCurrentAction(int a){
        act = a;
    }
    
    public void executeAction(int x){
        super.getMoveObj().setMovePattern(x);
    }
    
    //Negative numbers do backwards walking/directions
    public void setSpeed(float speed){
        super.setSpeed(speed);
    }
    
    public void addToActionList(int action){
        for(int x = 0; x < aList.length; x++){
            bList[x] = aList[x];
        }
        aList = new int[bList.length + 1];
        for(int x = 0; x < bList.length; x++){
            aList[x] = bList[x];
        }
        aList[bList.length] = action;
    }
    
    public void addToActionListAt(int action, int place){
        aList[place] = action;
    }
    
    public void resetActionList(int size){
        aList = new int[size];
        for(int x = 0; x < size; x++){
            aList[x] = 0;
        }
        bList = new int[size];
        for(int x = 0; x < size; x++){
            bList[x] = 0;
        }
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