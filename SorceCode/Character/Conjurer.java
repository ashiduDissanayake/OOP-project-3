package Character;
//class for making objects of conjurer
public class Conjurer extends Mage implements Highlander{
    public Conjurer() {
        super("Conjurer", 195, 18, 15, 14.0, 12.0, "HighLander");
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
