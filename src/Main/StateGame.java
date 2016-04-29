package Main;

import Main.Maps.Map000;
import Main.Maps.Map001;
import Main.Maps.Map002;
import Main.Maps.MapManager;
import Main.States.EquipmentCreator;
import Main.States.EquipmentLoader;
import Main.States.StartMenu;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {
    
    private static AppGameContainer appgc;
    
    public static final String title = "Nunuvet";
    private MapManager mapManager;
    
    public static final int DEFAULT_WIDTH = 960;
    public static final int DEFAULT_HEIGHT = 736;
    
    Music menuMusic;
    
    public static int m000      = 0;
    public static int m001      = 1;
    public static int m002      = 2;
    public static int loadEquip = 997;
    public static int equip     = 998;
    public static int menu      = 999;
    
    public StateGame(String title) {
        super(title);
        
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        try {
            //initialize each state
            
            //menuMusic = new Music("/res/audio/Sample/eyes for you.wav");
            //pitch/speed(1=standard), volume(0=mute, 1=high)
            //menuMusic.play(1, 0.05f);
            
            mapManager = new MapManager();
            mapManager.setupStateList(3);
            
            Map000 map001 = new Map000(m000, "World", "grassMap01", mapManager);
            Map001 map002 = new Map001(m001, "World", "grassMap02", mapManager);
            Map002 map003 = new Map002(m002, "World", "grassMap03", mapManager);
            
            mapManager.addToStateListAt(map001, m000);
            mapManager.addToStateListAt(map002, m001);
            mapManager.addToStateListAt(map003, m002);
            
            mapManager.initList(gc, this);
            
            this.addState(new StartMenu(menu));
            this.addState(new EquipmentCreator(equip));
            this.addState(new EquipmentLoader(loadEquip));
            //this.addState(new Options(options));
            //this.addState(new Credits(credits));
            this.addState(map001);
            this.addState(map002);
            this.addState(map003);
            
            this.getState(menu).init(gc, this);
            //this.getState(options).init(gc, this);
            //this.getState(credits).init(gc, this);
            //this.getState(map0001).init(gc, this);
            //starts on this state
            this.enterState(menu);
        } catch (SQLException ex) {
            Logger.getLogger(StateGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StateGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        try{//height, width, maintain aspect ratio?
            appgc = new AppGameContainer(new StateGame(title), DEFAULT_WIDTH, DEFAULT_HEIGHT, false);
            String[] icons = {"/res/images/Icons/JKIcon64.png", "/res/images/Icons/JKIcon32.png"};
            appgc.setIcons(icons);
            appgc.setDisplayMode(960, 736, false);
            //appgc.setShowFPS(false);
            appgc.start();
        }
        catch(SlickException se){
            se.printStackTrace();
        }
    }
}
