package Main.Util.MenuItems;

import Main.Util.MapClasses.ItemImageObject;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonBox extends Box{

    ItemImageObject iObj;
    int xCor1, xCor2, yCor1, yCor2, width, height;
    
    int xCor, yCor, lineWidth1, lineWidth2;
    int offset;
    String label, label2, name;
    Color border1, border2, background, foreground;
    Color focusColor;
    boolean focus, able;
    
    Color background2, foreground2;
    
    public ButtonBox(String Label, String Name, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground) throws SlickException{
        super(Label, XCor, YCor, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
        
        label = Label;
        label2 = Label;
        name = Name;
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        lineWidth1 = LineWidth1;
        lineWidth2 = LineWidth2;
        border1 = Border1;
        border2 = Border2;
        background = Background;
        background2 = Background;
        foreground = Foreground;
        foreground2 = Foreground;
        offset = Offset;
        
        focus = false;
        focusColor = Color.transparent;
    }
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();

        final Input input = container.getInput();

        if (this.checkHover((int) mouseX, (int) mouseY)) {
            this.getFocus();
        }
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        g.setColor(foreground);
        g.drawString(name, xCor + (width / 10), yCor + (height / 4));
    }
}

