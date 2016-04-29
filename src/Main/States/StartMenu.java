package Main.States;

import Main.Util.MenuItems.ButtonImage;
import net.java.games.input.Component;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartMenu extends BasicGameState {

    private int stateID;
    
    //Buttons
    public ButtonImage enterBtn;
    //Button (+ButtonHover) placements
    public int xCor1, xCor2, yCor1, yCor2;
    String load;
    //X & Y display
    float mouseX, mouseY;
    //Music
    Music menuMusic;
    //Sounds
    Sound clickSound;
    
    public StartMenu(int state){
        stateID = state;
        
        xCor1 = 960/2 + 117; xCor2 = 960/2 - 117; 
        yCor1 = 736 - 50 + 32; yCor2 = 736 - 50 - 32;
        load = "/res/images/Tiles/Menu/EnterButtonA.png";
    }
    
    @Override
    public int getID() {
        return stateID;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //Init Buttons
        
        enterBtn = new ButtonImage(xCor1, yCor1, xCor2, yCor2, load, 468, 150, 0, 0, Color.white);
        
        //Music
        //menuMusic = new Music("/res/audio/Sample/eyes for you.wav");
        //pitch/speed(1=standard), volume(0=mute, 1=high) 
        //menuMusic.play(1, 0.05f);
        
        //Sounds
        //clickSound = new Sound("/res/audio/Sample/elvis.wav");
        
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(new Image("/res/images/Backgrounds/background.jpg"), 0, 0);
        //Display X and Y of the Mouse
        g.setColor(Color.green);
        g.drawString("X: " + mouseX + " Y: " + mouseY, 805, 705);
        enterBtn = new ButtonImage(xCor1, yCor1, xCor2, yCor2, load, 468, 150, 0, 0, Color.white);
        enterBtn.render(container, game, g);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouseX = Mouse.getX();
        mouseY = container.getHeight() - Mouse.getY();
        
        Input input = container.getInput();
        
        //Music Loop
        //if (menuMusic.playing() == false){
        //    menuMusic.loop(1, 0.05f);
        //}
        
        //Hover over effect using location
        if (enterBtn.checkHover((int)mouseX, (int)mouseY)){
            // 0 = left hand key         1 = right hand key
            load = "/res/images/Tiles/Menu/EnterButtonB.png";
            if (Mouse.isButtonDown(0)){
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());//Map001
                //clickSound.play();
                //menuMusic.stop();
            }
            else if (Mouse.isButtonDown(1)){
                game.enterState(998, new FadeOutTransition(), new FadeInTransition());//Equipment Creator
            }
        }
        else{
            load = "/res/images/Tiles/Menu/EnterButtonA.png";
        }
    }
}
