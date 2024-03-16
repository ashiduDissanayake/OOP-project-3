package Character;

public class Soother extends Healer implements Sunchild{
    public Soother() {
        super("Soother", 95, 10, 8, 9.0, 6.0, "SunChildren");
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
