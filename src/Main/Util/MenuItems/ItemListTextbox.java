package Main.Util.MenuItems;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ItemListTextbox extends Box{
    
    String txt01;

    public ItemListTextbox(String name, String Label, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground) {
        super(Label, XCor, YCor, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
        txt01 = name;
        this.getEnabled();
    }
    
    public String getString(){
        return txt01;
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        g.setColor(foreground);
        g.drawString(txt01, xCor + (width / 10), yCor + (height / 4));
    }
}
