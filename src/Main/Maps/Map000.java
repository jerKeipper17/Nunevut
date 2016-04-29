package Main.Maps;

import Main.Characters.Equipment.Weapon;
import Main.Entity;
import Main.NPC;
import Main.Util.MapClasses.Warp;
import Main.Util.MapClasses.WarpManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Map000 extends MapObject{
    
    private int stateID;
    private ArrayList<Entity> entityList;
    boolean s = false;
    
    int scrollX = 0;
    int scrollY = 0;
    
    String load  = "/res/images/Tilesets/Characters/char_set01.png";
    String load2 = "/res/images/Tilesets/Characters/char_set33.png";
    String load3 = "/res/images/Tilesets/Items/rpg_Tileset.png";
    NPC e, f, ga, ha;
    
    //X & Y display
    float mouseX, mouseY;
    float playerSpeed = 0.08f;
    
    /*
    Testing
    
    ~Weapon Icon
    */
    Weapon Wooden_Short_Sword;

    public Map000(int stateID, String type, String name, MapManager mapM) throws SQLException, ClassNotFoundException {
        super(stateID, type, name, mapM);
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        super.init(container, game);
        
        setWarpManager(new WarpManager());
        getWarpManager().setupWarpManager(2); //Side 0:Up 1:R, State 0-2, State/Warp 0-1
        getWarpManager().addToWarpListAt(new Warp(16, 32, true, 1, 1, 0), 0);
        getWarpManager().addToWarpListAt(new Warp(880, 16, false, 2, 2, 0), 1);
        
        getWarpManager().init(container, game);
        
        //16's must be map tile width/height for map detection
        setupPlayer(50, 50, playerSpeed, 16, "down", load, 32, 32, 6, 0);
        e = new NPC(224, 125, 0.075f, 16, "left", load, 32, 32, 0, 4, getBlockedList(), false, getPlayer(), 10);
        f = new NPC(48, 450, 0.05f, 16, "right", load, 32, 32, 3, 0, getBlockedList(), false, getPlayer(), 12);
        ga = new NPC(112, 225, 0.075f, 16, "left", load, 32, 32, 0, 4, getBlockedList(), false, getPlayer(), 15);
        ha = new NPC(440, 300, 0.05f, 16, "right", load, 32, 32, 3, 0, getBlockedList(), false, getPlayer(), 5);
        
        /*
        Testing
        */
        
        getPlayer().init(container, game);
        e.init(container, game);f.init(container, game);ga.init(container, game);ha.init(container, game);
        
        //set NPC action list length
        e.resetActionList(2);f.resetActionList(2);ga.resetActionList(2);ha.resetActionList(2);
        
        //set actions for those lists
        e.addToActionListAt(1, 0);e.addToActionListAt(2, 1);
        f.addToActionListAt(3, 0);f.addToActionListAt(2, 1);
        ga.addToActionListAt(1, 0);ga.addToActionListAt(4, 1);
        ha.addToActionListAt(2, 0);ha.addToActionListAt(1, 1);
        
        //perform an action to begin
        e.executeAction(1);f.executeAction(3);ga.executeAction(1);ha.executeAction(2);
        
        entityList = new ArrayList();
        entityList.add(getPlayer());entityList.add(e);entityList.add(f);entityList.add(ga);entityList.add(ha);
        
        //This is how I would load items/people/wall? from a database
        try {
            ResultSet rs = this.getStatement().executeQuery("select * from THEKLOIS.WEAPONS");

            while (rs.next()) {
                String n = rs.getString("name");
                String p = rs.getString("imagePath");
                int w = rs.getInt("imageW");
                int h = rs.getInt("imageH");
                int x = rs.getInt("imageX");
                int y = rs.getInt("imageY");
                int r = rs.getInt("colorRed");
                int g = rs.getInt("colorGreen");
                int b = rs.getInt("colorBlue");
                int a = rs.getInt("colorAlpha");
                if (n.equals("Wooden Short Sword")){
                    System.out.println("Found");
                    Wooden_Short_Sword = new Weapon(n, p, w, h, x, y, new Color(r, g, b, a));
                    Wooden_Short_Sword.getImageObject().setupImage();
                }
            }
        } catch (Exception ex) {
            System.out.println("! " + ex);
        }
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        
        getWarpManager().render(container, game, g);
        
        //stateManager.render(container, game, g);
        
        g.setColor(Color.green);
        //Display X and Y of the Mouse
        g.drawString("X: " + mouseX + " Y: " + mouseY, 770, 705);
        
        //Render Items
        if (Wooden_Short_Sword != null){
            g.drawImage(Wooden_Short_Sword.getImageObject().getImage(), 100, 100, 130, 130, 0, 0, 24, 24, Wooden_Short_Sword.getImageObject().getColorMask());
        }
        
        //Render Player
        getPlayer().render(container, game, g);
        e.render(container, game, g);
        f.render(container, game, g);
        ga.render(container, game, g);
        ha.render(container, game, g);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        super.update(container, game, delta);
        
        getWarpManager().update(container, game, delta);
        
        mouseX = Mouse.getX();
        mouseY = Mouse.getY();
        
        int i,j;
        if (entityList.size() > 0) {
            for (i = 0; i < entityList.size(); i++) {
                for (j = i; j < entityList.size(); j++) {
                    testCollisions(entityList.get(i), entityList.get(j));
                }
            }
        }
        
        getPlayer().update(container, game, delta);
        e.update(container, game, delta);
        f.update(container, game, delta);
        ga.update(container, game, delta);
        ha.update(container, game, delta);
        
        if (getPlayer().getCircle().intersects(getWarpManager().returnWarpAt(0).getShape())){
            getPlayer().blocked();
            getMapManager().warpPlayer(this, getWarpManager(), 0, game);
        }
        
        if (getPlayer().getCircle().intersects(getWarpManager().returnWarpAt(1).getShape())){
            getPlayer().blocked();
            getMapManager().warpPlayer(this, getWarpManager(), 1, game);
        }
        
        Input input = container.getInput();
        doInput(input);
    }
    
    public void doInput(Input input) throws SlickException{
        if (input.isKeyPressed(input.KEY_P)){
            if (s == false){
                s = true;
                getPlayer().changePlayerImage(load, 0, 0);
                getPlayer().setSpriteOrientation(getPlayer().getSpriteOrientation());
            }
            else if (s == true){
                s = false;
                getPlayer().changePlayerImage(load3, 6, 0);
                getPlayer().setSpriteOrientation(getPlayer().getSpriteOrientation());
            }
        }
    }
}
