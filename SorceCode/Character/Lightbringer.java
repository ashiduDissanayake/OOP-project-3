package Character;

public class Lightbringer extends Healer implements Sunchild{
    public Lightbringer() {
        super("Lightbringer", 260, 17, 15, 19.0, 12.0, "SunChildren");
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
