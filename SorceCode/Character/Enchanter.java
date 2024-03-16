package Character;
//class for making objects of enchanter
public class Enchanter extends Mage implements Highlander{
    public Enchanter() {
        super("Enchanter", 160, 16, 10, 13.0, 16.0, "HighLander");
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
