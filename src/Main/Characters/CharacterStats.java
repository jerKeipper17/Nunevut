package Main.Characters;

public class CharacterStats {
    
    private int Chp, Mhp, Cmp, Mmp,
            skillPt = 0, expPt = 0,
            lvl = 1;
    
    private int str, tou, spr, res, eva, spe, hit, luc;

    public CharacterStats() {
        str = 1; tou = 1;
        spr = 1; res = 1;
        eva = 1; spe = 1;
        hit = 1; luc = 1;
    }
    
    public CharacterStats(int Str, int Tou, int Spr, int Res, int Eva, int Spe, int Hit, int Luc) {
        str = Str; tou = Tou;
        spr = Spr; res = Res;
        eva = Eva; spe = Spe;
        hit = Hit; luc = Luc;
    }
    
    //Skill Point
    public void setSkillPoint(int Skill){
        skillPt = Skill;
    }
    public void addSkillPoint(int Skill){
        skillPt = skillPt + Skill;
    }
    public int getSkillPointt(){
        return skillPt;
    }

    //Exp
    public void setExp(int Exp){
        expPt = Exp;
    }
    public void addExp(int Exp){
        expPt = expPt + Exp;
    }
    public int getExp(){
        return expPt;
    }
    
    //Level
    public void setLevel(int Level){
        lvl = Level;
    }
    public void addLevel(int Level){
        lvl = expPt + Level;
    }
    public int getLevel(){
        return lvl;
    }
    
    //Hitpoints
    public void setCHP(int CHP){
        Chp = CHP;
    }
    public void takeDamage(int dmg){
        Chp = Chp - dmg;
        if (Chp <= 0){
            Chp = 0;
        }
    }
    public void fullHeal(){
        Chp = Mhp;
    }
    public int getCHP(){
        return Chp;
    }
    //Maximum
    public void setMHP(int MHP){
        Mhp = MHP;
    }
    public int getMHP(){
        return Mhp;
    }
    
    //Magic Points
    public void setCMP(int CMP){
        Cmp = CMP;
    }
    public void useMagic(int mag){
        Cmp = Cmp - mag;
        if (Cmp <= 0){
            Cmp = 0;
        }
    }
    public void fullMagic(){
        Cmp = Mmp;
    }
    public int getCMP(){
        return Cmp;
    }
    //Maximum
    public void setMMP(int MMP){
        Mmp = MMP;
    }
    public int getMMP(){
        return Mmp;
    }

    //Skills
    public void setStr(int Str){
        str = Str;
    }
    public int getStr(){
        return str;
    }

    public void setTou(int Tou){
        tou = Tou;
    }
    public int getTou(){
        return tou;
    }
    
    public void setSpr(int Spr){
        spr = Spr;
    }
    public int getSpr(){
        return spr;
    }
    
    public void setRes(int Res){
        res = Res;
    }
    public int getRes(){
        return res;
    }
    
    public void setEva(int Eva){
        eva = Eva;
    }
    public int getEva(){
        return eva;
    }
    
    public void setSpe(int Spe){
        spe = Spe;
    }
    public int getSpe(){
        return spe;
    }

    public void setHit(int Hit){
        hit = Hit;
    }
    public int getHit(){
        return hit;
    }
    
    public void setLuc(int Luc){
        luc = Luc;
    }
    public int getLuc(){
        return luc;
    }

    public void setupLevel(int Skill, int CHP, int MHP, int CMP, int MMP){
        skillPt = Skill;
        Chp = CHP;
        Mhp = MHP;
        Cmp = CMP;
        Mmp = MMP;
    }

    public void setupStats(int Str, int Tou, int Spr, int Res, int Eva, int Spe, int Hit, int Luk){
        str = Str; tou = Tou; 
        spr = Spr; res = Res;
        eva = Eva; spe = Spe;
        hit = Hit; luc = Luk;
    }
}
