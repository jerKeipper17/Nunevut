package Main.Util.MapClasses;

import org.lwjgl.util.Timer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



public class TimerBar {
    
    private Timer timer;
    private float timeBarPercent;
    private float timeBarFull;
    private float xLoc;
    private float yLoc;
    private float width;
    private float height;
    private Color border;
    private Color inner;
    
    public TimerBar(float barPercent, float barFull, float xL, float yL, float wid, float hei, Color bo, Color in){//Timer w/ bar must override render
        
        timeBarPercent = barPercent;
        timeBarFull = barFull;
        xLoc = xL;
        yLoc = yL;
        width = wid;
        height = hei;
        border = bo;
        inner = in;
    }
    
    public TimerBar(float barPercent, float barFull){//Timer, no bar
        
        timeBarPercent = barPercent;
        timeBarFull = barFull;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        timer = new Timer();
        timer.set(0);
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        g.setColor(border);
        g.drawRect(xLoc, yLoc, width, height);
        g.setColor(inner);
        g.fillRect(xLoc, yLoc + 1, timeBarPercent * (width/timeBarFull), height - 1);
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        timer.tick();
        timeBarPercent = timer.getTime();
    }
    
    public void pauseTimer(){
        timer.pause();
    }
    
    public void setTimer(float f){
        timer.set(f);
    }
    
    public void setTimerLength(float f){
        timeBarFull = f;
    }
    
    public void resumeTimer(){
        timer.resume();
    }
    
    public void resetTimer(){
        timer.reset();
        timeBarPercent = 0;
    }
    
    public boolean timerFull(){
        if (timeBarPercent >= timeBarFull){
            return true;
        }
        else{
            return false;
        }
    }
    public float getTime(){
        return (timer.getTime() / timeBarFull);
    }
}
