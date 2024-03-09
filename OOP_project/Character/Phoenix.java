package Character;

public class Phoenix extends MythicalCreature implements Sunchild{
    public Phoenix() {
        super("Phoenix", 275, 17, 13, 17.0, 19.0, "SunChildren");
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
