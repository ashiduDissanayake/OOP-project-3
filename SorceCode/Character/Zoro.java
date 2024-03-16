package Character;

public class Zoro extends Knight implements Highlander{
    public Zoro() {
        super("Zoro", 180, 17, 16, 13.0, 14.0, "HighLander");
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
