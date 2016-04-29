package Main.Characters;

import Main.Characters.Equipment.Armor;
import Main.Characters.Equipment.Boots;
import Main.Characters.Equipment.Helmet;
import Main.Characters.Equipment.Jewelry;
import Main.Characters.Equipment.Gloves;
import Main.Characters.Equipment.Shield;
import Main.Characters.Equipment.Weapon;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class CharacterEquipment {
    
    private Armor armor;
    private Boots boots;
    private Helmet helmet;
    private Jewelry jewelry;
    private Gloves gloves;
    private Shield shield;
    private Weapon weapon;
    
    public CharacterEquipment(Weapon wpn, Armor amr, Boots bts, Helmet hlm, Gloves leg, Shield shd, Jewelry jew) throws SlickException{
        weapon   = wpn;
        armor    = amr;
        boots    = bts;
        helmet   = hlm;
        gloves = leg;
        shield   = shd;
        jewelry  = jew;
    }
    
    public CharacterEquipment() throws SlickException{
        weapon   = new Weapon("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        armor    = new Armor("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        boots    = new Boots("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        helmet   = new Helmet("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        jewelry  = new Jewelry("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        gloves   = new Gloves("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
        shield   = new Shield("Wooden Sword", "/res/images/Tilesets/Items/ItemsIcons01.png", 16, 16, 0, 1, Color.black);
    }
    
    //Weapon
    public void setWeapon(Weapon wpn){
        weapon = wpn;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    //Shield
    public void setShield(Shield shd){
        shield = shd;
    }
    public Shield getShield(){
        return shield;
    }
    //Armor
    public void setArmor(Armor amr){
        armor = amr;
    }
    public Armor getArmor(){
        return armor;
    }
    //Leggings
    public void setGloves(Gloves glv){
        gloves = glv;
    }
    public Gloves getGloves(){
        return gloves;
    }
    //Helmet
    public void setHelmet(Helmet helm){
        helmet = helm;
    }
    public Helmet getHelmet(){
        return helmet;
    }
    //Boots
    public void setBoots(Boots bts){
        boots = bts;
    }
    public Boots getBoots(){
        return boots;
    }
    //Jewelry
    public void setJewelry(Jewelry jew){
        jewelry = jew;
    }
    public Jewelry getJewelry(){
        return jewelry;
    }
    
    //Get Stat Attributes
    public int getPhyPow(){
        return ( weapon.getPhyPow() + gloves.getPhyPow() + jewelry.getPhyPow() );
    }
    public int getPhyDef(){
        return ( armor.getPhyDef() + boots.getPhyDef() + gloves.getPhyDef() +
                shield.getPhyDef() + helmet.getPhyDef() + jewelry.getPhyDef() );
    }
    public int getMagPow(){
        return ( weapon.getMagPow() + armor.getMagPow() +
                shield.getMagPow() + helmet.getMagPow() + jewelry.getMagPow() );
    }
    public int getMagDef(){
        return ( armor.getMagDef() + boots.getMagDef() + gloves.getMagDef() +
                shield.getMagDef() + helmet.getMagDef() + jewelry.getMagDef() );
    }
    public int getDodge(){
        return ( armor.getDod() + boots.getDod() + jewelry.getPhyPow() );
    }
    public int getSpeed(){
        return ( weapon.getSpd() + boots.getSpd() + gloves.getSpd() +
                jewelry.getSpd() );
    }
    public int getCritical(){
        return ( weapon.getCri() + helmet.getCri() + gloves.getCri() + 
                jewelry.getCri() );
    }
    public int getMaxHp(){
        return ( armor.getMaxHP() + helmet.getMaxHP() + jewelry.getMaxHP() );
    }
    public int getMaxMp(){
        return ( armor.getMaxMP() + helmet.getMaxMP() + jewelry.getMaxMP() );
    }
}