package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DropDownListbox {
    
    Image dropImage;
    int xCor, yCor, height, width, lineWidth1, lineWidth2;
    int offset;
    String[] str;
    String label;
    Color border1, border2, background, foreground;
    Color focusColor;
    boolean focus;
    boolean state;
    int selected;
    ItemListTextbox[] textBoxList;
    ItemListTextbox tBox;
    
    public DropDownListbox(String[] Str, String Label, int Select, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground){
        str = Str;
        label = Label;
        selected = Select;
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        offset = Offset;
        lineWidth1 = LineWidth1;
        border1 = Border1;
        lineWidth2 = LineWidth2;
        border2 = Border2;
        background = Background;
        foreground = Foreground;
        
        state = false;
        
        textBoxList = new ItemListTextbox[str.length];
        
        int dPadW = 0;
        
        for (int i = 0; str.length > i; i++){
            textBoxList[i] = new ItemListTextbox(str[i], "", XCor, YCor + dPadW, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
            dPadW += Height + 4;
        }
        
        tBox = new ItemListTextbox(textBoxList[selected].getString(), "", xCor, yCor, height, width, offset, lineWidth1, border1, lineWidth2, border2, background, foreground);
        
        focus = false;
        focusColor = Color.transparent;
    }
    
    public boolean checkHover(int x, int y) {
        if (x > xCor && x < (xCor + width)) {
            if (y > yCor && y < (yCor + (height*textBoxList.length) + (4*(textBoxList.length - 1)))) {
                return true;
            }
        }
        return false;
    }
        
    public int getSelectedInt(){
        return selected;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        textBoxList[selected].getSelected();
        
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        final Input input = container.getInput();

        if (state == false) {
            if (tBox.checkHover((int) mouseX, (int) mouseY)) {
                if (input.isMousePressed(0)) {
                    state = true;
                }
            }
        }
        if (state == true) {
            for (int i = 0; str.length > i; i++) {
                if (textBoxList[i].checkHover((int) mouseX, (int) mouseY)) {
                    textBoxList[i].getFocus();
                    if (input.isMousePressed(0)) {
                        textBoxList[selected].getUnselected();
                        selected = i;
                        textBoxList[selected].getSelected();
                    }
                } else if (textBoxList[i].checkHover((int) mouseX, (int) mouseY)) {
                    textBoxList[i].loseFocus();
                }
                textBoxList[selected].getFocus();
            }
            if (!this.checkHover((int) mouseX, (int) mouseY)) {
                state = false;
            }
        }
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
        g.setColor(foreground);
        g.drawString(label, xCor, yCor - 19);
        
        if (state == false){
            g.setColor(background);
            g.fillRect(xCor, yCor, width, height);
            g.setColor(foreground);
            tBox = new ItemListTextbox(textBoxList[selected].getString(), "", xCor, yCor, height, width, offset, lineWidth1, border1, lineWidth2, border2, background, foreground);
            tBox.render(container, game, g);
            dropImage = new Image("/res/images/Icons/dropdown.png", Color.white);
            g.drawImage(dropImage, (float)(xCor + (width - height) ), (float)yCor + 1, (float) xCor + (width - 1), (float) yCor + height, (float)0, (float)0, (float)140, (float)123);
        }
        else if (state == true){
            g.setColor(background);
            g.fillRect(xCor, yCor, width, (height*textBoxList.length) + (4*(textBoxList.length - 1)) );
            for (int n = 0; textBoxList.length > n; n++){
                g.setColor(foreground);
                textBoxList[n].render(container, game, g);
             }
        }
    }
    
}
