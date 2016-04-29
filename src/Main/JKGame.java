
package Main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class JKGame extends BasicGame
{
    private TiledMap grassMap;
    private Animation sprite, up, down, left, right;
    private float x = 32f, y = 32f;
    
    public final String title = "Nunevut";
    
    //Collision map indicating which blocks restrict movement
    private boolean [][] blocked;
    private static final int SIZE = 32;
    
	public JKGame(String Nunevut) {
		super(Nunevut);
	}
        
	@Override
	public void init(GameContainer gc) throws SlickException {
            
        
        Image [] movementUp = {new Image("res/images/Characters/knight01/char01U01.png"), new Image("res/images/Characters/knight01/char01U02.png"), new Image("res/images/Characters/knight01/char01U03.png")};
        Image [] movementDown = {new Image("res/images/Characters/knight01/char01D01.png"), new Image("res/images/Characters/knight01/char01D02.png"), new Image("res/images/Characters/knight01/char01D03.png")};
        Image [] movementLeft = {new Image("res/images/Characters/knight01/char01L01.png"), new Image("res/images/Characters/knight01/char01L02.png"), new Image("res/images/Characters/knight01/char01L03.png")};
        Image [] movementRight = {new Image("res/images/Characters/knight01/char01R01.png"), new Image("res/images/Characters/knight01/char01R02.png"), new Image("res/images/Characters/knight01/char01R03.png")};
        int [] duration = {300, 300, 300};    
        grassMap = new TiledMap("res/images/grassMap.tmx");
        
        //false var means do not auto update until key is pressed
        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);
         
        //Original Orientation of sprite
        sprite = down;
        
        
        
        //Build a collision map based on tile properties in the TiledMap
        blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
         
        for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
                int tileID = grassMap.getTileId(xAxis, yAxis, 0);
                String value = grassMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)) {
                    blocked[xAxis][yAxis] = true;
                }
            }
        }
    }

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
        
            
            Input input = container.getInput();
            float fdelta = delta * 0.1f;
            
            if (input.isKeyDown(Input.KEY_W)){
                sprite = up;
                if (!isBlocked(x, y - fdelta)) {
                    sprite.update(delta);
                    // The lower the delta the slowest the sprite will animate.
                    y -= delta * 0.1f;
                }
            }
            else if (input.isKeyDown(Input.KEY_S)) {
                sprite = down;
                if (!isBlocked(x, y + SIZE + fdelta)) {
                    sprite.update(delta);
                    y += delta * 0.1f;
                }
            }
            else if (input.isKeyDown(Input.KEY_A)) {
                sprite = left;
                if (!isBlocked(x - fdelta, y)) {
                    sprite.update(delta);
                    x -= delta * 0.1f;
                }  
            }
            else if (input.isKeyDown(Input.KEY_D)) {
                sprite = right;
                if (!isBlocked(x + SIZE + fdelta, y)) {
                   sprite.update(delta);
                   x += delta * 0.1f;
                }
            }
            
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
        
            grassMap.render(0, 0);      
        }

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new JKGame("Nunevut"));
			appgc.setDisplayMode(960, 720, false);
                        appgc.setShowFPS(false);
                        appgc.setTargetFrameRate(160);
			appgc.start();
		}
		catch (SlickException ex)
		{
                    ex.printStackTrace();
                }
	}
        
        private boolean isBlocked(float x, float y)
     {
         int xBlock = (int)x / SIZE;
         int yBlock = (int)y / SIZE;
         return blocked[xBlock][yBlock];
     }

}
