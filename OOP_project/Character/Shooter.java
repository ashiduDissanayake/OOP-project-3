package Character;

public class Shooter extends Archer implements Highlander{
    public Shooter() {
        super("Shooter", 80, 11, 4, 6.0, 9.0, "HighLander");
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
