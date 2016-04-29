
package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TextboxNumeric extends Box{
    
    int xCor, yCor, height, width;
    Color foreground;
    
    int stringLength;
    String userInput;
    
    
    int limit;

    //Numerical InputBox(0+)
    public TextboxNumeric(int Limit,
            String Label, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground) {
        super(Label, XCor, YCor, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
        
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        foreground = Foreground;
        
        userInput = "0";
        limit = Limit;
        this.getEnabled();
    }
  
    public void checkNumber(int limit, Input input){
        if (super.getAble()) {
            if ( (input.isKeyPressed(Input.KEY_0)) || (input.isKeyPressed(Input.KEY_NUMPAD0))) {
                input.clearKeyPressedRecord();
                addToString(limit, "0");
            } else if ((input.isKeyPressed(Input.KEY_1)) || (input.isKeyPressed(Input.KEY_NUMPAD1))) {
                input.clearKeyPressedRecord();
                addToString(limit, "1");
            } else if ((input.isKeyPressed(Input.KEY_2)) || (input.isKeyPressed(Input.KEY_NUMPAD2))) {
                input.clearKeyPressedRecord();
                addToString(limit, "2");
            } else if ((input.isKeyPressed(Input.KEY_3)) || (input.isKeyPressed(Input.KEY_NUMPAD3))) {
                input.clearKeyPressedRecord();
                addToString(limit, "3");
            } else if ((input.isKeyPressed(Input.KEY_4)) || (input.isKeyPressed(Input.KEY_NUMPAD4))) {
                input.clearKeyPressedRecord();
                addToString(limit, "4");
            } else if ((input.isKeyPressed(Input.KEY_5)) || (input.isKeyPressed(Input.KEY_NUMPAD5))) {
                input.clearKeyPressedRecord();
                addToString(limit, "5");
            } else if ((input.isKeyPressed(Input.KEY_6)) || (input.isKeyPressed(Input.KEY_NUMPAD6))) {
                input.clearKeyPressedRecord();
                addToString(limit, "6");
            } else if ((input.isKeyPressed(Input.KEY_7)) || (input.isKeyPressed(Input.KEY_NUMPAD7))) {
                input.clearKeyPressedRecord();
                addToString(limit, "7");
            } else if ((input.isKeyPressed(Input.KEY_8)) || (input.isKeyPressed(Input.KEY_NUMPAD8))) {
                input.clearKeyPressedRecord();
                addToString(limit, "8");
            } else if ((input.isKeyPressed(Input.KEY_9)) || (input.isKeyPressed(Input.KEY_NUMPAD9))) {
                input.clearKeyPressedRecord();
                addToString(limit, "9");
            } else if (input.isKeyPressed(Input.KEY_BACK)) {
                input.clearKeyPressedRecord();
                if (userInput.length() <= 1) {
                    userInput = "0";
                } else {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
            }
        }
        else{
            input.clearKeyPressedRecord();
            userInput = "0";
        }
    }
    
    //Numerical String
    public void addToString(int limit, String str) {

        if (Integer.parseInt(userInput + str) < limit) {
            if (userInput.equals("0")) {
                userInput = str;
            } else {
                userInput = userInput + str;
            }
        }
    }

    public String getUserString(){
        return userInput;
    }
    
    public int getUserStringInt(){
        return Integer.parseInt(userInput);
    }
    
    public void setUserString(String user){
        userInput = user;
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        
        final Input input = container.getInput();
        
        if (this.checkHover((int)mouseX, (int)mouseY)){
            this.checkNumber(limit, input);
        }
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        super.render(container, game, g);
        if (this.getAble()){
            g.setColor(foreground);
        }
        else{
            g.setColor(Color.white);
            
        }
        g.drawString(userInput, xCor + (width / 10), yCor + (height / 4));
    }
    
}
