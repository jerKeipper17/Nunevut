package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class FocusContainer{
    
    FocusManager tabList;
    int xCor, yCor, height, width, lineWidth1, lineWidth2;
    int offset;
    String label, label2;
    Color border1, border2, background;
    
    public FocusContainer(
            int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background){
        
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        lineWidth1 = LineWidth1;
        lineWidth2 = LineWidth2;
        border1 = Border1;
        border2 = Border2;
        background = Background;
        offset = Offset;
        tabList = new FocusManager();
    }
    
    public FocusManager getTabList(){
        return tabList;
    }
    
    public boolean checkHover(int x, int y) {
        if (x > xCor && x < (xCor + width)) {
            if (y > yCor && y < (yCor + height)) {
                return true;
            }
        }
        return false;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        tabList.init(container, game);
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
        g.setColor(background);
        g.fillRect(xCor, yCor, width, height);
        
        g.setColor(border1);
        g.setLineWidth(lineWidth1);
        
        g.drawLine(xCor, yCor, xCor + width, yCor);//Top
        g.drawLine(xCor + width, yCor, xCor + width, yCor + height);//Right
        g.drawLine(xCor, yCor + height, xCor + width, yCor + height);//Bottom
        g.drawLine(xCor, yCor, xCor, yCor + height);//Left
        
        g.setColor(border2);
        g.setLineWidth(lineWidth2);
        
        g.drawLine(xCor - offset, yCor - offset, xCor + width + offset, yCor - offset);//Top
        g.drawLine(xCor + width + offset, yCor - offset, xCor + width + offset, yCor + height + offset);//Right
        g.drawLine(xCor - offset, yCor + height + offset, xCor + width + offset, yCor + height + offset);//Bottom
        g.drawLine(xCor - offset, yCor - offset, xCor - offset, yCor + height + offset);//Left
        
        tabList.render(container, game, g);
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        
        if (this.checkHover((int)mouseX, (int)mouseY)){
            tabList.update(container, game, delta);
        }
    }
}
