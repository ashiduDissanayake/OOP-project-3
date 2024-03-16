package Character;
//class for making objects of cavalier
public class Cavalier extends Knight implements Highlander{
    public Cavalier() {
        super("Cavalier", 110, 10, 12, 7.0, 10.0, "HighLander");
    }
    //following are buffs and debuffs when they are in homelands.
    @Override
    public void applyHillcrestBuff() {
        this.setAttack(this.getAttack()+1);
        this.setDefence(this.getDefence()+1);
    }

    @Override
    public void applyArcaneDebuff() {
        this.setSpeed(this.getSpeed()-1);
        this.setDefence(this.getDefence()-1);
    }
}
