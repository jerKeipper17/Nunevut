package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ItemListbox {
    
    int xCor, yCor, height, width, lineWidth1, lineWidth2;
    int offset;
    String[] label;
    Color border1, border2, background, foreground;
    Color focusColor;
    boolean focus;
    int selected;
    ItemListTextbox[] textBoxList;
    
    public ItemListbox(String[] Label, int Select, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground){
        label = Label;
        selected = Select;
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        lineWidth1 = LineWidth1;
        lineWidth2 = LineWidth2;
        border1 = Border1;
        border2 = Border2;
        background = Background;
        foreground = Foreground;
        offset = Offset;
        
        textBoxList = new ItemListTextbox[label.length];
                
        int dPadW = 0;
        
        for (int i = 0; label.length > i; i++){
            
            textBoxList[i] = new ItemListTextbox(label[i], "", XCor, YCor + dPadW + 4, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
            dPadW += Height + 4;
        }
        
        focus = false;
        focusColor = Color.transparent;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        textBoxList[selected].getSelected();
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        final Input input = container.getInput();
        
        for (int i = 0; label.length > i; i++){
            if (textBoxList[i].checkHover((int)mouseX, (int)mouseY) ){
                textBoxList[i].getFocus();
                if (input.isMousePressed(0)){
                    textBoxList[selected].getUnselected();
                    selected = i;
                    textBoxList[selected].getSelected();
                }
            }
            else if (textBoxList[i].checkHover((int)mouseX, (int)mouseY) ){
                textBoxList[i].loseFocus();
            }
            
            textBoxList[selected].getFocus();
        }
        
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
        g.setColor(background);
        g.fillRect(xCor, yCor, width, (height*textBoxList.length) + (4*(textBoxList.length - 1)) );
        
        g.setColor(foreground);
        for (int i = 0; textBoxList.length > i; i++){
            textBoxList[i].render(container, game, g);
        }
    }
    
}
