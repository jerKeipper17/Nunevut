package Main.Util.MapClasses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class WarpManager {
   
    private Warp[] warp;
    
    public WarpManager(){
        
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        for (int x = 0; x < warp.length; x++){
            warp[x].init(container, sbg);
        }
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        for (int x = 0; x < warp.length; x++){
            warp[x].render(container, sbg, g);
        }
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        for (int x = 0; x < warp.length; x++){
            warp[x].update(container, sbg, delta);
        }
    }
    
    public void setupWarpManager(int x){
        warp = new Warp[x];
    }
    
    public void addToWarpListAt(Warp w, int x){
        warp[x] = w;
    }
    
    public Warp returnWarpAt(int x){
        return warp[x];
    }
}
