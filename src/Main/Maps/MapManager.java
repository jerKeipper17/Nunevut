package Main.Maps;

import Main.Util.MapClasses.WarpManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MapManager {
    
    private MapObject[] mapList;
    
    private int last, current, next;
    
    public MapManager(){
        
    }
    
    public void warpPlayer(MapObject current, WarpManager warpManager, int warpID, StateBasedGame game) throws SlickException{
        MapObject toThisMap = getMap(warpManager.returnWarpAt(warpID).getState());
        toThisMap.getPlayer().changePlayerImage(current.getPlayer().getPlayerLoader(), current.getPlayer().getPlayerImageX(), current.getPlayer().getPlayerImageY());
        toThisMap.getPlayer().setSpriteOrientation(current.getPlayer().getSpriteOrientation());
        
        game.enterState(toThisMap.getID(), new FadeOutTransition(), new FadeInTransition());
        
        toThisMap.setPlayerX(toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getX());
        toThisMap.setPlayerY(toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getY());
        //toThisMap.setPlayerY(mapList[warpID].getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getY());
        if (toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getSide() == 0){
            toThisMap.setPlayerOrientation("up");
        }
        else if (toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getSide() == 1){
            toThisMap.setPlayerOrientation("right");
        }
        else if (toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getSide() == 2){
            toThisMap.setPlayerOrientation("down");
        }
        else if (toThisMap.getWarpManager().returnWarpAt(warpManager.returnWarpAt(warpID).getTwinWarpID()).getSide() == 3){
            toThisMap.setPlayerOrientation("left");
        }
        else {
            toThisMap.setPlayerOrientation("down");
        }
        //game.enterState(toThisMap.getID(), new FadeOutTransition(), new FadeInTransition());
    }
    
    public void initList(GameContainer container, StateBasedGame sbg)throws SlickException {
        for (int x = 0; x < mapList.length; x++){
            mapList[x].init(container, sbg);
        }
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException {
        
        
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        
        mapList[current].render(container, sbg, g);
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        
        mapList[current].update(container, sbg, delta);
    }
    
    public MapObject getMap(int x){
        return mapList[x];
    }
    
    public void addToStateListAt(MapObject m, int x){
        mapList[x] = m;
    }
    
    public void setupStateList(int x){
        mapList = new MapObject[x];
    }
    
    public void mapSwitch(int x) {

        if (x < mapList.length){
            current = x;
        }
    }
}
