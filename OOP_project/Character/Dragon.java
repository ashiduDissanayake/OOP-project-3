package Character;

public class Dragon extends MythicalCreature implements Sunchild{
    public Dragon() {
        super("Dragon", 120, 12, 14, 15.0, 8.0, "SunChildren");
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
