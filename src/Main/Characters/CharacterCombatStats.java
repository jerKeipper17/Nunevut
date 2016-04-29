package Main.Characters;

import java.util.Random;
import org.newdawn.slick.SlickException;

public class CharacterCombatStats {
    
    CharacterEquipment equip;
    CharacterStats stats;
    
    public CharacterCombatStats(CharacterEquipment Equip, CharacterStats Stats) {
        equip = Equip;
        stats = Stats;
    }
    
    public CharacterCombatStats() throws SlickException {
        equip = new CharacterEquipment();
        stats = new CharacterStats();
    }
    
    public CharacterEquipment getEquipment(){
        return equip;
    }
    
    public CharacterStats getStats(){
        return stats;
    }
    
        //Combat Stats
    public int getMaxHP(){
        return (equip.getMaxHp() + stats.getMHP());
    }
    
    public int getMaxMP(){
        return (equip.getMaxMp() + stats.getMMP());
    }
    
    public int getPhyAtk(){
        return (equip.getPhyPow() + ( (stats.getStr() / 2) + 1) );
    }
    
    public int getPhyDef(){
        return (equip.getPhyDef() + ( (stats.getTou() / 2) + 1) );
    }

    public int getMagPow(){
        return (equip.getMagPow() + ( (stats.getSpr() / 2) + 1) );
    }
    
    public int getMagDef(){
        return (equip.getMagDef() + ( (stats.getRes() / 2) + 1) );
    }
    
    public int getDodge(){
        return (equip.getDodge() + ( (stats.getEva() / 2) + 1) );
    }

    public int getCritical(){
        return (equip.getCritical() + ( (stats.getHit() / 2) + 1) );
    }
    
    public int getAtkSpeed() {
        return (equip.getSpeed() + ( (stats.getSpe() / 2) + 1) );
    }
    
    public int getPhysicalAttackNumber(){
        Random generator = new Random(System.currentTimeMillis());
        
        int rand = generator.nextInt( (equip.getCritical() / 2) + stats.getLuc() + 1);
        
        return (getPhyAtk() + rand);
    }
}
