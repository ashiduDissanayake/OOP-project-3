package Character;

public class Sunfire extends Archer implements Sunchild{
    public Sunfire() {
        super("Sunfire", 160, 15, 5, 7.0, 14.0, "SunChildren");
    }
    @Override
    public void applyHillcrestDebuff() {
        this.setSpeed(this.getSpeed()-1);
    }

    @Override
    public void applyMarshlandDebuff() {
        this.setAttack(this.getAttack()-1);
    }

    @Override
    public void applyDesertBuff() {
        this.setAttack(this.getAttack()+1);
    }
}
