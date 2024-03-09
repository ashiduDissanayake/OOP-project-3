package Character;

public class Medic extends Healer implements Highlander{
    public Medic() {
        super("Medic", 125, 12, 9, 10.0, 7.0, "HighLander");
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
