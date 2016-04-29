
package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TextboxString extends Box{
    
    int xCor, yCor, height, width;
    Color foreground;
    
    int stringLength;
    String userInput;
    
    boolean able;
    
    int limit;

    //Numerical InputBox(0+)
    public TextboxString(int Limit,
            String Label, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground) {
        super(Label, XCor, YCor, Height, Width, Offset, LineWidth1, Border1, LineWidth2, Border2, Background, Foreground);
        
        xCor = XCor;
        yCor = YCor;
        height = Height;
        width = Width;
        foreground = Foreground;
        able = true;
        
        userInput = "";
        limit = Limit;
        this.getEnabled();
    }
  
    public void checkKey(int limit, Input input) {
        if (super.getAble()) {

            if (input.isKeyPressed(Input.KEY_BACK)) {
                //input.clearKeyPressedRecord();
                if (userInput.length() <= 0) {
                    userInput = "";
                } else {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
            } else if (input.isKeyDown(Input.KEY_RSHIFT) || (input.isKeyDown(Input.KEY_LSHIFT))) {
                if (input.isKeyPressed((Input.KEY_Q))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "Q");
                } else if (input.isKeyPressed((Input.KEY_W))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "W");
                } else if (input.isKeyPressed((Input.KEY_E))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "E");
                } else if (input.isKeyPressed((Input.KEY_R))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "R");
                } else if (input.isKeyPressed((Input.KEY_T))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "T");
                } else if (input.isKeyPressed((Input.KEY_Y))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "Y");
                } else if (input.isKeyPressed((Input.KEY_U))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "U");
                } else if (input.isKeyPressed((Input.KEY_I))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "I");
                } else if (input.isKeyPressed((Input.KEY_O))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "O");
                } else if (input.isKeyPressed((Input.KEY_P))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "P");
                } else if (input.isKeyPressed((Input.KEY_A))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "A");
                } else if (input.isKeyPressed((Input.KEY_S))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "S");
                } else if (input.isKeyPressed((Input.KEY_D))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "D");
                } else if (input.isKeyPressed((Input.KEY_F))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "F");
                } else if (input.isKeyPressed((Input.KEY_G))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "G");
                } else if (input.isKeyPressed((Input.KEY_H))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "H");
                } else if (input.isKeyPressed((Input.KEY_J))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "J");
                } else if (input.isKeyPressed((Input.KEY_K))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "K");
                } else if (input.isKeyPressed((Input.KEY_L))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "L");
                } else if (input.isKeyPressed((Input.KEY_Z))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "Z");
                } else if (input.isKeyPressed((Input.KEY_X))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "X");
                } else if (input.isKeyPressed((Input.KEY_C))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "C");
                } else if (input.isKeyPressed((Input.KEY_V))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "V");
                } else if (input.isKeyPressed((Input.KEY_B))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "B");
                } else if (input.isKeyPressed((Input.KEY_N))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "N");
                } else if (input.isKeyPressed((Input.KEY_M))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "M");
                } //Symbols
                else if (input.isKeyPressed((Input.KEY_COLON))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ":");
                } else if (input.isKeyPressed((Input.KEY_COMMA))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "<");
                } else if (input.isKeyPressed((Input.KEY_PERIOD))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ">");
                } //Numbers
                if (input.isKeyPressed((Input.KEY_0))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ")");
                } else if (input.isKeyPressed(Input.KEY_1)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "!");
                } else if (input.isKeyPressed(Input.KEY_2)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "@");
                } else if (input.isKeyPressed(Input.KEY_3)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "#");
                } else if (input.isKeyPressed(Input.KEY_4)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "$");
                } else if (input.isKeyPressed(Input.KEY_5)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "%");
                } else if (input.isKeyPressed(Input.KEY_6)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "^");
                } else if (input.isKeyPressed(Input.KEY_7)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "&");
                } else if (input.isKeyPressed(Input.KEY_8)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "*");
                } else if (input.isKeyPressed(Input.KEY_9)) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "(");
                }
            } else //Lowercase Letters
            {
                if (input.isKeyPressed((Input.KEY_Q))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "q");
                } else if (input.isKeyPressed((Input.KEY_W))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "w");
                } else if (input.isKeyPressed((Input.KEY_E))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "e");
                } else if (input.isKeyPressed((Input.KEY_R))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "r");
                } else if (input.isKeyPressed((Input.KEY_T))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "t");
                } else if (input.isKeyPressed((Input.KEY_Y))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "y");
                } else if (input.isKeyPressed((Input.KEY_U))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "u");
                } else if (input.isKeyPressed((Input.KEY_I))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "i");
                } else if (input.isKeyPressed((Input.KEY_O))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "o");
                } else if (input.isKeyPressed((Input.KEY_P))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "p");
                } else if (input.isKeyPressed((Input.KEY_A))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "a");
                } else if (input.isKeyPressed((Input.KEY_S))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "s");
                } else if (input.isKeyPressed((Input.KEY_D))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "d");
                } else if (input.isKeyPressed((Input.KEY_F))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "f");
                } else if (input.isKeyPressed((Input.KEY_G))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "g");
                } else if (input.isKeyPressed((Input.KEY_H))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "h");
                } else if (input.isKeyPressed((Input.KEY_J))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "j");
                } else if (input.isKeyPressed((Input.KEY_K))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "k");
                } else if (input.isKeyPressed((Input.KEY_L))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "l");
                } else if (input.isKeyPressed((Input.KEY_Z))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "z");
                } else if (input.isKeyPressed((Input.KEY_X))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "x");
                } else if (input.isKeyPressed((Input.KEY_C))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "c");
                } else if (input.isKeyPressed((Input.KEY_V))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "v");
                } else if (input.isKeyPressed((Input.KEY_B))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "b");
                } else if (input.isKeyPressed((Input.KEY_N))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "n");
                } else if (input.isKeyPressed((Input.KEY_M))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "m");
                } //Symbols
                else if (input.isKeyPressed((Input.KEY_APOSTROPHE))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, "'");
                } else if (input.isKeyPressed((Input.KEY_COLON))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ";");
                } else if (input.isKeyPressed((Input.KEY_COMMA))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ",");
                } else if (input.isKeyPressed((Input.KEY_PERIOD))) {
                    input.clearKeyPressedRecord();
                    addToString(limit, ".");
                } else if (input.isKeyPressed(Input.KEY_SPACE)){
                    input.clearKeyPressedRecord();
                    addToString(limit, " ");
                } //Numbers
                else if ((input.isKeyPressed((Input.KEY_0))) || (input.isKeyPressed(Input.KEY_NUMPAD0))) {
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
                }
            }
        } else {
            input.clearKeyPressedRecord();
            userInput = "";
        }
    }
    
    //Numerical String
    public void addToString(int limit, String str) {
        if (userInput.length() + 1 < limit){
            userInput = userInput.concat(str);
        }
    }

    public String getUserString(){
        return userInput;
    }
    
    public void setUserString(String in){
        userInput = in;
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        
        final Input input = container.getInput();
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
