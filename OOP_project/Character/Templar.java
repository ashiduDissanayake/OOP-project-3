package Character;

public class Templar extends Knight implements Sunchild{
    public Templar() {
        super("Templar", 155, 14, 16, 12.0, 12.0, "SunChildren");
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
