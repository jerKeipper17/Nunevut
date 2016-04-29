package Main.Util.MenuItems;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Box {

    int xCor, yCor, height, width, lineWidth1, lineWidth2;
    int offset;
    String label, label2;
    Color border1, border2, background, foreground;
    Color focusColor;
    boolean focus, tabFocus, able;

    Color background2, foreground2;

    public Box(String Label, int XCor, int YCor, int Height, int Width, int Offset, int LineWidth1, Color Border1, int LineWidth2, Color Border2, Color Background, Color Foreground) {
        label = Label;
        label2 = Label;
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

        able = true;
        focus = false;
        tabFocus = false;
        focusColor = Color.transparent;
    }

    public boolean getAble() {
        return able;
    }

    public boolean getEnabled() {
        return able;
    }

    public void setEnabled(boolean Able) {
        if (Able) {
            able = true;
            label = label2;
            background = background2;
            foreground = foreground2;
        } else {
            able = false;
            label = "";
            background = Color.white;
            foreground = Color.white;
        }
    }

    public void getSelected() {
        lineWidth2 = lineWidth2 + 2;
    }

    public void getUnselected() {
        lineWidth2 = lineWidth2 - 2;
    }

    public void getFocus() {
        if (able) {
            focus = true;
            focusColor = border2;
        }
    }

    public void setTabFocus(boolean tab) {
        if (able) {
            tabFocus = tab;
            if (tab == true) {
                getFocus();
            } else {
                loseFocus();
            }
        } else {
            loseFocus();
        }
    }

    public boolean hasFocus() {
        if (tabFocus == true) {
            return true;
        } else {
            return focus;
        }
    }

    public void loseFocus() {
        focus = false;
        focusColor = Color.transparent;
    }

    public boolean checkHover(int x, int y) {
        if (x > xCor && x < (xCor + width)) {
            if (y > yCor && y < (yCor + height)) {
                return true;
            }
        }
        return false;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setColor(foreground);
        g.drawString(label, xCor, yCor - 19);

        g.setColor(background);
        g.fillRect(xCor, yCor, width, height);

        g.setColor(border1);
        g.setLineWidth(lineWidth1);

        g.drawLine(xCor, yCor, xCor + width, yCor);//Top
        g.drawLine(xCor + width, yCor, xCor + width, yCor + height);//Right
        g.drawLine(xCor, yCor + height, xCor + width, yCor + height);//Bottom
        g.drawLine(xCor, yCor, xCor, yCor + height);//Left

        g.setColor(focusColor);
        g.setLineWidth(lineWidth2);

        g.drawLine(xCor - offset, yCor - offset, xCor + width + offset, yCor - offset);//Top
        g.drawLine(xCor + width + offset, yCor - offset, xCor + width + offset, yCor + height + offset);//Right
        g.drawLine(xCor - offset, yCor + height + offset, xCor + width + offset, yCor + height + offset);//Bottom
        g.drawLine(xCor - offset, yCor - offset, xCor - offset, yCor + height + offset);//Left
    }

}
