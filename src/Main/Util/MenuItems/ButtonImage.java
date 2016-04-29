package Main.Util.MenuItems;

import Main.Util.MapClasses.ItemImageObject;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonImage {
    
    ItemImageObject iObj;
    int xCor1, xCor2, yCor1, yCor2, width, height;
    Color mask;
    boolean secImage;
    
    public ButtonImage(int xcor1, int ycor1, int xcor2, int ycor2, String load, int Width, int Height, int imgX, int imgY, Color col) throws SlickException{
        iObj = new ItemImageObject(load, Width, Height, imgX, imgY, col);
        xCor1 = xcor1;
        xCor2 = xcor2;
        yCor1 = ycor1;
        yCor2 = ycor2;
        width = Width;
        height = Height;
        mask = col;
    }
    
    public ItemImageObject getImageObject(){
        return iObj;
    }
    
    public boolean checkHover(int x, int y){
        
        if (x > xCor2 && x < xCor1){
            if (y > yCor2 && y < yCor1){
                return true;
            }
        }
        return false;
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();

        final Input input = container.getInput();
        
        Color hover;
        
        hover = new Color(200, 180, 120, 200);
            if (this.checkHover((int) mouseX, (int) mouseY)) {
                this.getImageObject().setColorMask(hover);
            } else {
                this.getImageObject().setColorMask(mask);
            }
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
            g.drawImage(this.getImageObject().getImage(), xCor1, yCor1, xCor2, yCor2, width, height, 0, 0, this.getImageObject().getColorMask());
        
    }
}