package Main.Util.MapClasses;

import Main.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Warp {
    
    private RectangleCollisionObject rObj;
    private CircleCollisionObject cObj;
    private boolean circle = false;
    private boolean orient = false;
    
    private Player player;
    private int stateID, warpID;
    private int side = 0;// 0 = Up, 1 = Right, 2 = Down, 3 = Left
    
    public Warp(float X, float Y, int size, int si, int state, int warp){
        cObj = new CircleCollisionObject(X, Y, size);
        side = si;
        stateID = state;
        warpID = warp;
        circle = true;
    }
    
    public Warp(float X, float Y, float w, float h, int si, int state, int warp){
        rObj = new RectangleCollisionObject(X, Y, w, h);
        side = si;
        stateID = state;
        warpID = warp;
        circle = false;
        if (w > h){
            orient = false;
        }
        else{
            orient = true;
        }
    }
    
    public Warp(float X, float Y, boolean vert, int si, int state, int warp){
        rObj = new RectangleCollisionObject(X, Y, vert);
        side = si;
        stateID = state;
        warpID = warp;
        circle = false;
        orient = vert;
    }
    
    public void init(GameContainer container, StateBasedGame sbg) throws SlickException{
        
        if (circle == false){
            rObj.init(container, sbg);
        }
        else{
            cObj.init(container, sbg);
        }
    }
    
    public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
        //g.draw(circle);
        if (circle == false){
            rObj.render(container, sbg, g);
        }
        else{
            cObj.render(container, sbg, g);
            g.draw(cObj.getCircle());
        }
    }
    
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (circle == false){
            rObj.update(container, sbg, delta);
        }
        else{
            cObj.update(container, sbg, delta);
        }
    }
    
    public void setState(int state){
        stateID = state;
    }
    
    public int getState(){
        return stateID;
    }
    
    public void setTwinWarpID(int warp){
        warpID = warp;
    }
    
    public int getTwinWarpID(){
        return warpID;
    }
    
    public boolean getCircle(){
        return circle;
    }
    
    public Shape getShape(){
        if (circle == false){
            return rObj.getRectangle();
        }
        else{
            return cObj.getCircle();
        }
    }
    
    public void setSide(int si){
        side = si;
    }
    
    public int getSide(){
        return side;
    }
    
    public float getX(){
        if ( (side == 0) || (side == 2) ){//Up and Down
            if (circle == false) {//Rectangle
                if (orient == false){//Horizontal
                    return (rObj.getX() + (rObj.getWidth()) * 0.16f);
                }
                else {//Vertical
                    return (rObj.getX() - (rObj.getWidth() * 0.5f));
                }
            }
            else {//Circle
                return (cObj.getX() - (cObj.getSize()));
            }
        }
        else if (side == 1){//Right
            if (circle == false){
                if (orient == false){
                    return (rObj.getX() + (rObj.getWidth() * 1.16f));
                }
                else{
                    return (rObj.getX() + (rObj.getWidth() * 1.5f));
                }
            }
            else {
                return (cObj.getX() + (cObj.getSize() * 1.35f));
            }
        }
        else if (side == 3){//Left
            if (circle == false){
                if (orient == false){
                    return (rObj.getX() - (rObj.getWidth() * 0.90f));
                }
                else {
                    return (rObj.getX() - (rObj.getWidth() * 2.5f));
                }
            }
            else {
                return (cObj.getX() - (cObj.getSize() * 3.5f));
            }
        }
        else {//Left
            if (circle == false){
                if (orient == false){
                    return (rObj.getX() - (rObj.getWidth() * 0.90f));
                }
                else {
                    return (rObj.getX() - (rObj.getWidth() * 2.5f));
                }
            }
            else {
                return (cObj.getX() - (cObj.getSize() * 3.5f));
            }
        }
    }
    
    public float getY(){
        if ( (side == 1) || (side == 3) ){//Right and Left
            if (circle == false) {
                if (orient == false){
                    return (rObj.getY() - (rObj.getHeight() * 0.5f));
                }
                else{
                    return (rObj.getY() + (rObj.getHeight() * 0.16f));
                }
            }
            else {
                return (cObj.getY()) - (cObj.getSize());
            }
        }
        else if (side == 0){//Up
            if (circle == false){
                if (orient == false){
                    return (rObj.getY() - (rObj.getHeight() * 2.5f));
                }
                else{
                    return (rObj.getY() - (rObj.getHeight() * 0.85f));
                }
            }
            else {
                return (cObj.getY() - (cObj.getSize() * 3.5f));
            }
        }
        else if (side == 2){//Down
            if (circle == false){
                if (orient == false){
                    return (rObj.getY() + (rObj.getHeight() * 1.5f));
                }
                else{
                    return (rObj.getY() + (rObj.getHeight() * 1.2f));
                }
            }
            else {
                return (cObj.getY() + (cObj.getSize() * 1.5f));
            }
        }
        else {//Down
            if (circle == false){
                if (orient == false){
                    return (rObj.getY() + (rObj.getHeight() * 1.5f));
                }
                else{
                    return (rObj.getY() + (rObj.getHeight() * 1.2f));
                }
            }
            else {
                return (cObj.getY() + (cObj.getSize() * 1.5f));
            }
        }
    }
}
