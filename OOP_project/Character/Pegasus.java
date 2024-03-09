package Character;

public class Pegasus extends MythicalCreature implements Mystic{
    public Pegasus() {
        super("Pegasus", 340, 14, 18, 20.0, 20.0, "Mystic");
    }

    @Override
    public void applyMarshlandDebuff() {
        this.setSpeed(this.getSpeed()-1);
    }

    @Override
    public void applyArcaneBuff() {
        this.setAttack(this.getAttack()+2);
    }
}
