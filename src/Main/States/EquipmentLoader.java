package Main.States;

import Main.Util.MenuItems.ButtonImage;
import Main.Util.MenuItems.DropDownListbox;
import Main.Util.MapClasses.ItemImageObject;
import Main.Util.MenuItems.FocusContainer;
import Main.Util.MenuItems.TextboxOneLineDialog;
import Main.Util.MenuItems.TextboxNumeric;
import Main.Util.MenuItems.TextboxString;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EquipmentLoader extends BasicGameState{
    
    private int stateID;
    
        //Buttons
    public ButtonImage exitBtn, upBtn, leftBtn, downBtn, rightBtn;
    public ButtonImage acceptBtn;
    public ItemImageObject viewImage;
    public int viewX, viewY, viewWH;
    public Color itemHue, acceptHue;
    public int menuX, menuY, mOff;
    public int mImageX, mImageY, mStatsX, mStatsY;
    public int mImageW, mImageH, mStatsW, mStatsH;

    public boolean textB;
    
    public TextboxOneLineDialog openMsg;
    
    public TextboxNumeric imageXBox;
    public TextboxNumeric imageYBox;
    public TextboxNumeric alphaBox;
    public TextboxNumeric rBox;
    public TextboxNumeric gBox;
    public TextboxNumeric bBox;
    
    public TextboxString  itemNameBox;
    public DropDownListbox itemTypeBox;
    public TextboxNumeric itemPhyPowBox;
    public TextboxNumeric itemPhyDefBox;
    public TextboxNumeric itemMagPowBox;
    public TextboxNumeric itemMagDefBox;
    public TextboxNumeric itemDodgeBox;
    public TextboxNumeric itemSpeedBox;
    public TextboxNumeric itemCriBox;
    public TextboxNumeric itemMaxHPBox;
    public TextboxNumeric itemMaxMPBox;
    public TextboxNumeric itemValueBox;
    
    public FocusContainer imageCont, statsCont;
    
    public String fileImage;
    //Button (+ButtonHover) placements
    public int exitX1, exitX2, exitY1, exitY2;
    String exitLoad;
    public int acceptX1, acceptX2, acceptY1, acceptY2;
    String acceptLoad;
    //Button Hover boolean
    public boolean isMouseOverPlayButton;
    //X & Y display
    float mouseX, mouseY;
    //Music
    Music menuMusic;
    //Sounds
    Sound clickSound;

    public EquipmentLoader(int state){
        stateID = state;
        
        exitX1 = 960/2 + 117; exitX2 = 960/2 - 117; 
        exitY1 = 736 - 50 + 32; exitY2 = 736 - 50 - 32;
        exitLoad = "/res/images/Tiles/Menu/ExitButtonA.png";
        
        acceptX1 = 960/2 + 117; acceptX2 = 960/2 - 117; 
        acceptY1 = 736 - 150 + 32; acceptY2 = 736 - 150 - 32;
        acceptLoad = "/res/images/Tiles/Menu/AcceptButtonA.png";
        acceptHue = new Color(125, 125, 255, 255);
    }
    
    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        //Init Buttons
        exitBtn = new ButtonImage(exitX1, exitY1, exitX2, exitY2, exitLoad, 468, 150, 0, 0, Color.white);
        acceptBtn = new ButtonImage(acceptX1, acceptY1, acceptX2, acceptY2, acceptLoad, 468, 150, 0, 0, acceptHue);
        
        mOff = 36;
        menuX = 480;
        menuY = 368;
        
        itemHue = Color.white;
        
        //Welcome Message
        String insText = "WELCOME TO THE BLACKSMITH! LOAD ITEMS HERE!";
        int insLength = mOff + (insText.length() * 10);
        openMsg = new TextboxOneLineDialog(insText, "", menuX - menuX/2, mOff, mOff, menuX, 1, 1, Color.black, 1, Color.cyan, Color.lightGray, Color.darkGray);
                
        //Image Selection
        mImageX = menuX/3 + menuX/21;mImageY = menuY/2 + menuY/4 - 10;mImageH = 6*mOff;mImageW = 7*mOff;
        
        imageCont = new FocusContainer(mImageX, mImageY - mOff, mImageH, mImageW, 1, 1, Color.black, 1, Color.darkGray, new Color(75, 75, 125, 75));
        upBtn     = new ButtonImage(mImageX + (2*mOff), mImageY + mOff,    mImageX + (mOff), mImageY, "/res/images/Tilesets/Items/ItemsIcons007.png", 24, 24, 14, 17, Color.white);
        rightBtn  = new ButtonImage(mImageX + (3*mOff), mImageY + (2*mOff), mImageX + (2*mOff), mImageY + mOff, "/res/images/Tilesets/Items/ItemsIcons007.png", 24, 24, 13, 17, Color.white);
        leftBtn   = new ButtonImage(mImageX + mOff,     mImageY + (2*mOff), mImageX, mImageY + mOff, "/res/images/Tilesets/Items/ItemsIcons007.png", 24, 24, 12, 17, Color.white);
        downBtn   = new ButtonImage(mImageX + (2*mOff), mImageY + (3*mOff), mImageX + (mOff), mImageY + (2*mOff), "/res/images/Tilesets/Items/ItemsIcons007.png", 24, 24, 15, 17, Color.white);
        
        imageXBox = new TextboxNumeric(16,  "X",     mImageX + (4*mOff), mImageY, (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        imageYBox = new TextboxNumeric(271, "Y",     mImageX + (5*mOff) + (mOff/4), mImageY, (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        alphaBox  = new TextboxNumeric(255, "A",     mImageX + (4*mOff), mImageY + (3*(mOff/2)), (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        rBox      = new TextboxNumeric(255, "R",     mImageX + (5*mOff) + (mOff/4), mImageY + (3*(mOff/2)), (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        gBox      = new TextboxNumeric(255, "G",     mImageX + (4*mOff), mImageY + (6*(mOff/2)), (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        bBox      = new TextboxNumeric(255, "B",     mImageX + (5*mOff) + (mOff/4), mImageY + (6*(mOff/2)), (mOff), (mOff), 2, 1, Color.black, 1, Color.green, Color.lightGray, Color.red);
        alphaBox.setUserString("255");
        rBox.setUserString("255");
        gBox.setUserString("255");
        bBox.setUserString("255");
        
        imageCont.getTabList().addToFocusManager(imageXBox);
        imageCont.getTabList().addToFocusManager(imageYBox);
        imageCont.getTabList().addToFocusManager(alphaBox);
        imageCont.getTabList().addToFocusManager(rBox);
        imageCont.getTabList().addToFocusManager(gBox);
        imageCont.getTabList().addToFocusManager(bBox);
        
        
        //Item Stats
        mStatsX = menuX + menuX/4 - (2*mOff);mStatsY = menuY/4;mStatsH = 9*mOff;mStatsW = 12*mOff + 12;
        
        statsCont = new FocusContainer(mStatsX - 16, mStatsY, mStatsH, mStatsW, 1, 1, Color.black, 1, Color.darkGray, new Color(75, 75, 125, 75));
        itemNameBox     = new TextboxString(21, "Name", mStatsX, mStatsY + (mOff), (mOff-6), (6*mOff), 1, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        String[] list = { "Weapon", "Armor", "Boots", "Gloves", "Shield", "Helmet", "Jewelry" };
        itemTypeBox = new DropDownListbox(list, "Type", 0, mStatsX + (13*(mOff/2)), mStatsY + (mOff), (mOff-6), (5*mOff), 1, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemTypeBox.init(container, game);
        itemPhyPowBox   = new TextboxNumeric(3, "PhyPow",   mStatsX,            mStatsY + (3*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemPhyDefBox   = new TextboxNumeric(3, "PhyDef",   mStatsX + (2*mOff), mStatsY + (3*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemMagPowBox   = new TextboxNumeric(3, "MagPow",   mStatsX + (4*mOff), mStatsY + (3*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemMagDefBox   = new TextboxNumeric(3, "MagDef",   mStatsX,            mStatsY + (9*(mOff/2)), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemDodgeBox    = new TextboxNumeric(3, "Dodge",    mStatsX + (2*mOff), mStatsY + (9*(mOff/2)), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemSpeedBox    = new TextboxNumeric(3, "Speed",    mStatsX + (4*mOff), mStatsY + (9*(mOff/2)), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemCriBox      = new TextboxNumeric(3, "Crit",     mStatsX,            mStatsY + (6*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemMaxHPBox    = new TextboxNumeric(3, "Max HP",   mStatsX + (2*mOff), mStatsY + (6*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemMaxMPBox    = new TextboxNumeric(3, "Max MP",   mStatsX + (4*mOff), mStatsY + (6*mOff), (mOff), (mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        itemValueBox    = new TextboxNumeric(3, "Value",    mStatsX,            mStatsY + (15*(mOff/2)), (mOff), (2*mOff), 2, 1, Color.red, 1, Color.green, Color.darkGray, Color.white);
        
        statsCont.getTabList().addToFocusManager(itemNameBox);
        statsCont.getTabList().addToFocusManager(itemPhyPowBox);
        statsCont.getTabList().addToFocusManager(itemPhyDefBox);
        statsCont.getTabList().addToFocusManager(itemMagPowBox);
        statsCont.getTabList().addToFocusManager(itemMagDefBox);
        statsCont.getTabList().addToFocusManager(itemDodgeBox);
        statsCont.getTabList().addToFocusManager(itemSpeedBox);
        statsCont.getTabList().addToFocusManager(itemCriBox);
        statsCont.getTabList().addToFocusManager(itemMaxHPBox);
        statsCont.getTabList().addToFocusManager(itemMaxMPBox);
        statsCont.getTabList().addToFocusManager(itemValueBox);
        
        imageCont.init(container, game);
        statsCont.init(container, game);
        
        //ItemImage Location
        viewX  = 0;
        viewY  = 0;
        viewWH = 24;
        
        //Music
        //menuMusic = new Music("/res/audio/Sample/eyes for you.wav");
        //pitch/speed(1=standard), volume(0=mute, 1=high) 
        //menuMusic.play(1, 0.05f);
        
        //Sounds
        //clickSound = new Sound("/res/audio/Sample/elvis.wav");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        
        g.drawImage(new Image("/res/images/Backgrounds/backgroundBlacksmith.png").getScaledCopy(960, 736), 0, 0);
        
        //Display X and Y of the Mouse
        g.setColor(Color.green);
        g.drawString("X: " + mouseX + " Y: " + mouseY, 805, 705);
        
        exitBtn = new ButtonImage(exitX1, exitY1, exitX2, exitY2, exitLoad, 468, 150, 0, 0, Color.white);
        exitBtn.render(container, game, g);
        
        acceptBtn = new ButtonImage(acceptX1, acceptY1, acceptX2, acceptY2, acceptLoad, 468, 150, 0, 0, acceptHue);
        acceptBtn.render(container, game, g);
        
        imageCont.render(container, game, g);
        statsCont.render(container, game, g);
        
        //background for item
        g.setColor(new Color(220, 220, 220, 120));
        g.fillRect(mImageX + mOff, mImageY + mOff, 36, 36);
        //item
        viewImage = new ItemImageObject("/res/images/Tilesets/Items/ItemsIcons007.png", viewWH, viewWH, imageXBox.getUserStringInt(), imageYBox.getUserStringInt(), Color.white);
        g.drawImage(viewImage.getImage(), mImageX + mOff, mImageY + mOff, mImageX + (mOff * 2), mImageY + (mOff * 2), 0, 0, 24, 24, itemHue);
        
        imageXBox.render(container, game, g);
        imageYBox.render(container, game, g);
        alphaBox.render(container, game, g);
        rBox.render(container, game, g);
        gBox.render(container, game, g);
        bBox.render(container, game, g);
        
        openMsg.render(container, game, g);
        
        itemNameBox.render(container, game, g);
        itemTypeBox.render(container, game, g);
        itemPhyPowBox.render(container, game, g);
        itemPhyDefBox.render(container, game, g);
        itemMagPowBox.render(container, game, g);
        itemMagDefBox.render(container, game, g);
        itemDodgeBox.render(container, game, g);
        itemSpeedBox.render(container, game, g);
        itemCriBox.render(container, game, g);
        itemMaxHPBox.render(container, game, g);
        itemMaxMPBox.render(container, game, g);
        itemValueBox.render(container, game, g);
        
        leftBtn.render(container, game, g);
        downBtn.render(container, game, g);
        rightBtn.render(container, game, g);
        upBtn.render(container, game, g);

        acceptBtn.render(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        mouseX = Mouse.getX();
        mouseY = container.getHeight() - Mouse.getY();
        
        final Input input = container.getInput();
        
        imageCont.update(container, game, delta);
        if (imageCont.checkHover((int) mouseX, (int) mouseY)) {
            if (imageXBox.hasFocus()) {
                imageXBox.checkNumber(16, input);
            }
            if (imageYBox.hasFocus()) {
                imageYBox.checkNumber(271, input);
            }
            //Image Color Mask - alpha, r, g, b
            if (alphaBox.hasFocus()) {
                alphaBox.checkNumber(256, input);
                itemHue = new Color(rBox.getUserStringInt(), gBox.getUserStringInt(), bBox.getUserStringInt(), alphaBox.getUserStringInt());
            }
            if (rBox.hasFocus()) {
                rBox.checkNumber(256, input);
                itemHue = new Color(rBox.getUserStringInt(), gBox.getUserStringInt(), bBox.getUserStringInt(), alphaBox.getUserStringInt());
            }
            if (gBox.hasFocus()) {
                gBox.checkNumber(256, input);
                itemHue = new Color(rBox.getUserStringInt(), gBox.getUserStringInt(), bBox.getUserStringInt(), alphaBox.getUserStringInt());
            }
            if (bBox.hasFocus()) {
                bBox.checkNumber(256, input);
                itemHue = new Color(rBox.getUserStringInt(), gBox.getUserStringInt(), bBox.getUserStringInt(), alphaBox.getUserStringInt());
            }
        }
        
        statsCont.update(container, game, delta);
        
        setSelectedItem(itemTypeBox.getSelectedInt());
        
        if (statsCont.checkHover((int) mouseX, (int) mouseY)) {
            //Name Box
            if (itemNameBox.hasFocus()) {
                itemNameBox.checkKey(21, input);
            }
            //Item Value Box
            if (itemValueBox.hasFocus()) {
                itemValueBox.checkNumber(1000000, input);
            }
            if (itemPhyPowBox.hasFocus()) {
                itemPhyPowBox.checkNumber(1000, input);
            }
            if (itemPhyDefBox.hasFocus()) {
                itemPhyDefBox.checkNumber(1000, input);
            }
            if (itemMagPowBox.hasFocus()) {
                itemMagPowBox.checkNumber(1000, input);
            }
            if (itemMagDefBox.hasFocus()) {
                itemMagDefBox.checkNumber(1000, input);
            }
            if (itemDodgeBox.hasFocus()) {
                itemDodgeBox.checkNumber(1000, input);
            }
            if (itemSpeedBox.hasFocus()) {
                itemSpeedBox.checkNumber(1000, input);
            }
            if (itemCriBox.hasFocus()) {
                itemCriBox.checkNumber(1000, input);
            }
            if (itemMaxHPBox.hasFocus()) {
                itemMaxHPBox.checkNumber(1000, input);
            }
            if (itemMaxMPBox.hasFocus()) {
                itemMaxMPBox.checkNumber(1000, input);
            }
        }
        
        //Self Updating
        openMsg.update(container, game, delta);
        itemTypeBox.update(container, game, delta);
        acceptBtn.update(container, game, delta);
        
        //Music Loop
        //if (menuMusic.playing() == false){
        //    menuMusic.loop(1, 0.05f);
        //}
        
        //Left, Up, Down, Right Image Buttons
        leftBtn.update(container, game, delta);
        upBtn.update(container, game, delta);
        downBtn.update(container, game, delta);
        rightBtn.update(container, game, delta);
        
        if (leftBtn.checkHover((int)mouseX, (int)mouseY)){
            if (input.isMousePressed(0)){
                if (imageXBox.getUserStringInt() > 0){
                    int num = imageXBox.getUserStringInt() - 1;
                    imageXBox.setUserString("" + num);
                }
            }
        }
        else if (upBtn.checkHover((int)mouseX, (int)mouseY)){
            if (input.isMousePressed(0)){
                if (imageYBox.getUserStringInt() > 0){
                    int num = imageYBox.getUserStringInt() - 1;
                    imageYBox.setUserString("" + num);
                }
            }
        }
        else if (downBtn.checkHover((int)mouseX, (int)mouseY)){
            if (input.isMousePressed(0)){
                if (imageYBox.getUserStringInt() < 270){
                    int num = imageYBox.getUserStringInt() + 1;
                    imageYBox.setUserString("" + num);
                }
            }
        }   
        else if (rightBtn.checkHover((int)mouseX, (int)mouseY)){
            if (input.isMousePressed(0)){
                if (imageXBox.getUserStringInt() < 15){
                    int num = imageXBox.getUserStringInt() + 1;
                    imageXBox.setUserString("" + num);
                }
            }
        }
        
        if (acceptBtn.checkHover((int) mouseX, (int) mouseY)) {
            acceptLoad = "/res/images/Tiles/Menu/AcceptButtonB.png";
            if (Mouse.isButtonDown(0)) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Nunevut", "theKlois", "ameoba");
                    Statement stmt = (Statement) con.createStatement();

                    // Build a string containing the SQL INSERT instruction to be used later
                    String SQLString = "INSERT INTO weapons VALUES ('" + itemNameBox.getUserString() + "','" + viewImage.getLoad() + "','" + 32 + "','" + 32 + "','"
                            + rBox.getUserStringInt() + "','" + gBox.getUserStringInt() + "','" + bBox.getUserStringInt() + "','" + alphaBox.getUserStringInt() + "','"
                            + itemPhyPowBox.getUserStringInt() + "','" + itemMagPowBox.getUserStringInt() + "','" + itemSpeedBox.getUserStringInt() + "','"
                            + itemCriBox.getUserStringInt() + "','" + itemValueBox.getUserStringInt() + "')";
                    stmt.execute("INSERT INTO weapons VALUES ('" + itemNameBox.getUserString() + "','" + viewImage.getLoad() + "'," + 32 + "," + 32 + ","
                            + rBox.getUserStringInt() + "," + gBox.getUserStringInt() + "," + bBox.getUserStringInt() + "," + alphaBox.getUserStringInt() + ","
                            + itemPhyPowBox.getUserStringInt() + "," + itemMagPowBox.getUserStringInt() + "," + itemSpeedBox.getUserStringInt() + ","
                            + itemCriBox.getUserStringInt() + "," + itemValueBox.getUserStringInt() + ")");
                    stmt.close();

                } catch (Exception e) {
                    System.out.println("JKError: " + e);
                }
            }
        }
        else{
            acceptLoad = "/res/images/Tiles/Menu/AcceptButtonA.png";
        }
        
        //Hover over effect using location
        if (exitBtn.checkHover((int)mouseX, (int)mouseY)){
            // 0 = left hand key         1 = right hand key
            exitLoad = "/res/images/Tiles/Menu/ExitButtonB.png";
            if (Mouse.isButtonDown(0)){
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());//Map001
                //clickSound.play();
                //menuMusic.stop();
            }
            else if (Mouse.isButtonDown(1)){
                game.enterState(999, new FadeOutTransition(), new FadeInTransition());//Start Menu
            }
        }
        else{
            exitLoad = "/res/images/Tiles/Menu/ExitButtonA.png";
        }
    }
    
    public void setSelectedItem(int sel){
        //Item Stat Updating
        //Weapon
        
        if (sel == 0){
            itemPhyPowBox.setEnabled(true);
            itemMagPowBox.setEnabled(true);
            itemSpeedBox.setEnabled(true);
            itemCriBox.setEnabled(true);
            itemPhyDefBox.setEnabled(false);
            itemMagDefBox.setEnabled(false);
            itemDodgeBox.setEnabled(false);
            itemMaxHPBox.setEnabled(false);
            itemMaxMPBox.setEnabled(false);
        }
        //Armor
        if (sel == 1){
            itemPhyPowBox.setEnabled(false);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(true);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(true);
            itemSpeedBox.setEnabled(false);
            itemCriBox.setEnabled(false);
            itemMaxHPBox.setEnabled(true);
            itemMaxMPBox.setEnabled(true);
        }
        //Boots
        if (sel == 2){
            itemPhyPowBox.setEnabled(false);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(false);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(true);
            itemSpeedBox.setEnabled(true);
            itemCriBox.setEnabled(false);
            itemMaxHPBox.setEnabled(false);
            itemMaxMPBox.setEnabled(false);
        }
        //Gloves
        if (sel == 3){
            itemPhyPowBox.setEnabled(true);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(false);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(false);
            itemSpeedBox.setEnabled(true);
            itemCriBox.setEnabled(true);
            itemMaxHPBox.setEnabled(false);;
            itemMaxMPBox.setEnabled(false);
        }
        //Shield
        if (sel == 4){
            itemPhyPowBox.setEnabled(false);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(true);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(false);
            itemSpeedBox.setEnabled(false);
            itemCriBox.setEnabled(false);
            itemMaxHPBox.setEnabled(false);
            itemMaxMPBox.setEnabled(false);
        }
        //Helmet
        if (sel == 5){
            itemPhyPowBox.setEnabled(false);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(true);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(false);
            itemSpeedBox.setEnabled(false);
            itemCriBox.setEnabled(true);
            itemMaxHPBox.setEnabled(true);
            itemMaxMPBox.setEnabled(true);
        }
        //Jewelry
        if (sel == 6){
            itemPhyPowBox.setEnabled(true);
            itemPhyDefBox.setEnabled(true);
            itemMagPowBox.setEnabled(true);
            itemMagDefBox.setEnabled(true);
            itemDodgeBox.setEnabled(true);
            itemSpeedBox.setEnabled(true);
            itemCriBox.setEnabled(true);
            itemMaxHPBox.setEnabled(true);
            itemMaxMPBox.setEnabled(true);
        }
    }
}
