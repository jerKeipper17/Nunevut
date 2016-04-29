package Main.Util.MapClasses;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerMovObj {
    
    //private ImageObject iObj;
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
    
    public PlayerMovObj(float sp, MapImageObject iO){
    }
    
    public PlayerMovObj(float sp, float X, float Y, int pix, String orient, String load, int picW, int picH, int pX, int pY, boolean [][] block){
        speed = sp;
        initX = X; initY = Y;
        pixel = pix; orientation = orient;
        IOLoad = load; 
        picWidth = picW; picHeight = picH;
        picX = pX; picY = pY;
        blocked = block;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
        
        cObj = new CircleCollisionObject(initX, initY, pixel);
        cObj.init(container, sbg);
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        cObj.render(container, sbg, g);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        Input input = container.getInput();
        
        cObj.passVal();
    }
}
