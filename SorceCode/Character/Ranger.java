package Character;

public class Ranger extends Archer implements Highlander{
    public Ranger() {
        super("Ranger", 115, 14, 5, 8.0, 10.0, "HighLander");
    }
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
