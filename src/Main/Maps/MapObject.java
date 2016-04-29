package Main.Maps;

import Main.Entity;
import Main.Player;
import Main.Util.MapClasses.WarpManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class MapObject extends BasicGameState {
    
    private int stateID;
    private MapManager mapManager;
    private WarpManager warpManager;
    private TiledMap gameMap;
    private boolean [][] blocked;
    private String mapName, mapType;
    private Player player;
    private Statement stmt;
    public String dbConnectionStr, dbUser, dbPwd;
    
    public MapObject(int ID, String type, String name, MapManager mapM) throws SQLException, ClassNotFoundException{
        stateID = ID;
        mapName = name;
        mapType = type;
        mapManager = mapM;
        warpManager = new WarpManager();
        
        dbConnectionStr = "jdbc:derby://localhost:1527/Nunevut2";
        dbUser = "theKlois";
        dbPwd = "ameoba";
        
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection con = DriverManager.getConnection(dbConnectionStr, dbUser, dbPwd);
        stmt = con.createStatement();
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        gameMap = new TiledMap("res/images/Maps/" + mapType + "/" + mapName + ".tmx");
        
        //Build a collision map based on tile properties in the TiledMap
        blocked = new boolean[gameMap.getWidth()][gameMap.getHeight()];
         
        for (int xAxis=0; xAxis<gameMap.getWidth(); xAxis++){
            for (int yAxis=0; yAxis<gameMap.getHeight(); yAxis++){
                int tileID = gameMap.getTileId(xAxis, yAxis, 0);
                String value = gameMap.getTileProperty(tileID, "blocked", "false");
                if ("true".equals(value)){
                     blocked[xAxis][yAxis] = true;
                }
            }
        }
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        gameMap.render(0, 0);
        
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
    }
    
    public Statement getStatement(){
        return stmt;
    }
    
    public WarpManager getWarpManager(){
        return warpManager;
    }
    
    public void setWarpManager(WarpManager warpM){
        warpManager = warpM;
    }
    
    public MapManager getMapManager(){
        return mapManager;
    }
    
    public void setMapManager(MapManager mapM){
        mapManager = mapM;
    }
    
    protected void setPlayerX(float x){
        player.getMoveObj().getCObj().setX(x);
    }
    
    protected float getPlayerX(){
        return player.getMoveObj().getCObj().getX();
    }
    
    protected void setPlayerY(float y){
        player.getMoveObj().getCObj().setY(y);
    }
    
    protected float getPlayerY(){
        return player.getMoveObj().getCObj().getY();
    }
    
    protected void setPlayerOrientation(String str) {
        player.getMoveObj().setSpriteOrient(str);
    }
    
    public String getMapType(){
        return mapType;
    }
    
    public String getMapName(){
        return mapName;
    }
    
    public boolean[][] getBlockedList(){
        return blocked;
    }
    
    public void testCollisions(Entity a, Entity b){
        if (!(a.equals(b))){
            if (a.getCircle().intersects(b.getCircle())){
                a.blocked();
                b.blocked();
            }
        }
    }
    
    public void setupPlayer(float X, float Y, float sp, int pix, String orient, String load, int picW, int picH, int pX, int pY) throws SlickException{
        player = new Player(X, Y, sp, pix, orient, load, picW, picH, pX, pY, getBlockedList());
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void setPlayer(Player play){
        player = play;
    }

    @Override
    public int getID() {
        return stateID;
    }
    
    public void pause(GameContainer container, StateBasedGame game){
        container.setPaused(true);
    }
    
    public void resume(GameContainer container, StateBasedGame game){
        container.setPaused(false);
    }
}
