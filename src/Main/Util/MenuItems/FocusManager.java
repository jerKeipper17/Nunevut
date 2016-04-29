package Main.Util.MenuItems;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class FocusManager {
    
    Box[] tabList;
    int pos = 0;
    int addPos = 0;
    int max = 20;
    
    public FocusManager(){
        tabList = new Box[20];
        for (int x = 0; x < tabList.length; x++){
            tabList[x] = null;
        }
    }
    
    public void addToFocusManager(Box obj){
        tabList[addPos] = obj;
        addPos++;
    }
    
    public void loseAllFocus() {
        for (int x = 0; x < addPos; x++) {
            if (tabList[x] != null) {
                tabList[x].loseFocus();
                tabList[x].setTabFocus(false);
            }
        }
    }
    
    public int getNextAvailableBox() {
        //Within length and within valid returns
        if ((!(tabList.length == 0)) && (pos < (addPos - 1))) {
            for (int x = pos + 1; x < tabList.length; x++) {
                if (tabList[x].able) {
                    if (x == tabList.length) {
                        x = -1;
                    } else {
                        return x;
                    }
                }
            }
        }
        else if (pos == (addPos - 1)) {
            for (int x = 0; x < tabList.length; x++) {
                if (tabList[x].able) {
                    if (x == tabList.length) {
                        return 0;
                    } else {
                        return x;
                    }
                }
            }
        }
        return 0;
    }
    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        if ((tabList.length != 0) && (tabList[0] != null)) {
            tabList[0].getFocus();
        }
    }
    
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if ((tabList.length != 0) && (tabList[0].hasFocus()) && (tabList[0] != null)){
            tabList[0].setTabFocus(true);
        }
    }
    
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        float mouseX = Mouse.getX();
        float mouseY = container.getHeight() - Mouse.getY();
        
        final Input input = container.getInput();
        
        if (input.isKeyPressed(Input.KEY_TAB)) {
            tabList[pos].setTabFocus(false);
            pos = getNextAvailableBox();
            tabList[pos].setTabFocus(true);
        }
        
        if (tabList[0] != null) {
            for (int x = 0; x < addPos; x++) {
                if ((tabList[x].checkHover((int) mouseX, (int) mouseY))) {
                    if (Mouse.isButtonDown(0)){
                        this.loseAllFocus();
                        pos = x;
                        tabList[x].getFocus();
                        tabList[x].setTabFocus(true);
                        //if (tabList[x] instanceof TextboxString)
                    }
                }
            }
        }
    }
}
